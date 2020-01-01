package com.mk7.xcalc.views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TableRow;
import android.widget.TextView;

import com.mk7.xcalc.R;
import com.mk7.xcalc.presenters.CalcActivityPresenter;
import com.mk7.xcalc.presenters.ExDataModel;
import com.mk7.xcalc.presenters.ICalcActivityPresenter;
import com.mk7.xcalc.presenters.SettingListener;
import com.mk7.xcalc.presenters.Settings;

public class XCalcFragment extends Fragment implements ISetupFragment, ICalcActivityPresenter {

    private static final String TAG = "XCalcFragment";

    EditText editText = null;
    EditTextHelper textHelper;
    ExDataModel modelSelected;

    ListView listView;
    ArrayAdapter adapter;
    CalcActivityPresenter presenter;

    public XCalcFragment() {
        Log.d(TAG, ">>> cons.XCalcFragment");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        adapter = new ArrayAdapter(this.getActivity(), R.layout.xcalc_simple_template, R.id.answer_textView) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                ExDataModel exData = (ExDataModel) getItem(position);

                if (exData.isError()) {
                    View view = getActivity().getLayoutInflater().inflate(R.layout.xcalc_error_template, null);
                    TextView expTextView = (TextView) view.findViewById(R.id.error_exp_textView);
                    TextView errorTextView = (TextView) view.findViewById(R.id.error_textView);
                    expTextView.setText(exData.getExpression());
                    errorTextView.setText(exData.getError());
                    return view;
                } else {
                    View view = getActivity().getLayoutInflater().inflate(R.layout.xcalc_simple_template, null);
                    TextView questionTextView = ((TextView) view.findViewById(R.id.question_textView));
                    TextView answerTextView = ((TextView) view.findViewById(R.id.answer_textView));
                    questionTextView.setText(exData.getExpression());
                    answerTextView.setText(exData.getValueToString());
                    return view;
                }
            }
        };

        Log.d(TAG, ">>> XCalcFragment.onCreate");
    }

    View this_View = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, ">>> XCalcFragment.onCreateView");

        this_View = inflater.inflate(R.layout.fragment_xcalc, container, false);

        listView = (ListView) this_View.findViewById(R.id.expression_listView);
        listView.setTextFilterEnabled(true);
        listView.setAdapter(adapter);
        listView.setLongClickable(false);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                modelSelected = (ExDataModel) adapter.getItem(position);
                return false;
            }
        });
        registerForContextMenu(listView);

        //Change background of shift TableRow textView
        Settings.getInstance().addListener(new SettingListener() {

            TableRow row = (TableRow) this_View.findViewById(R.id.shift_tableRow);

            @Override
            public void isShiftChanged(Settings settings) {

                if (settings.isShiftMode())
                    row.setBackgroundColor(getResources().getColor(R.color.rowTable_isShiftMode));
                else
                    row.setBackgroundColor(getResources().getColor(R.color.rowTable_default));
            }
        });


        //TextView In Shift Mode
        TextView shiftTextView = (TextView) this_View.findViewById(R.id.asin_textView);
        setSpanned(shiftTextView);

        shiftTextView = (TextView) this_View.findViewById(R.id.acos_textView);
        setSpanned(shiftTextView);

        shiftTextView = (TextView) this_View.findViewById(R.id.atan_textView);
        setSpanned(shiftTextView);

        shiftTextView = (TextView) this_View.findViewById(R.id.asinh_textView);
        setSpanned(shiftTextView);

        shiftTextView = (TextView) this_View.findViewById(R.id.acosh_textView);
        setSpanned(shiftTextView);

        shiftTextView = (TextView) this_View.findViewById(R.id.atanh_textView);
        setSpanned(shiftTextView);

        //Equal Button
        Button equalButton = (Button) this_View.findViewById(R.id.equal_button);
        equalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String text = getTextHelper().getText();
                    if (text.length() < 0) return;

                    getPresenter().parse(text);
                } catch (Throwable e) {
                    Log.e(TAG, ">>> onClick_Equal", e);
                }
            }
        });

        //Clear ListView
        Button clearListButton = (Button) this_View.findViewById(R.id.clearList_button);
        clearListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.clear();
            }
        });

        //Shift Button
        Button shiftButton = (Button) this_View.findViewById(R.id.shift_button);
        shiftButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Settings.getInstance().setIsShiftMode(!Settings.getInstance().isShiftMode());
            }
        });

        editText = (EditText) this_View.findViewById(R.id.expression_editText);
        textHelper = null;

        return this_View;
    }

    void setSpanned(TextView textView) {
        textView.setText(Html.fromHtml((String) textView.getText()));
    }

    public void onClick_Command(View view) {
        if (view.getTag() == null)
            return;

        String[] args = view.getTag().toString().split("[:]");
        String commandName = args[0];
        String commandArg = "";
        if (args.length > 1) {
            if (Settings.getInstance().isShiftMode() && args.length > 2)
                commandArg = args[2];
            else
            commandArg = args[1];
        }

        getPresenter().execCommand(commandName, commandArg);
        Log.d(TAG, ">>> Tag: " + view.getTag().toString());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        //inflater.inflate(R.menu.calc_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.calc_list_context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        boolean value = true;
        switch (item.getItemId()) {
            case R.id.set_context_menuItem:
                editText.setText(modelSelected.getExpression());
                break;
            case R.id.delete_context_menuItem:
                adapter.remove(modelSelected);
                break;
            case R.id.clear_list_context_menu:
                adapter.clear();
                break;
            default:
                value = super.onContextItemSelected(item);
        }

        return value;
    }

    @Override
    public EditTextHelper getTextHelper() {
        if (textHelper == null)
            textHelper = new EditTextHelper(getEditText());
        return textHelper;
    }

    @Override
    public void setExpressionValue(ExDataModel exData) {
        try {
            if (exData == null) return;
            adapter.add(exData);
            listView.smoothScrollToPosition(adapter.getCount() - 1);
            listView.setSelection(adapter.getCount() - 1);
        } catch (Throwable throwable) {
            Log.e(TAG, ">>> setExpressionValue Error: ", throwable);
        }
    }

    @Override
    public String getTitle() {
        return "xCalculator";
    }

    public CalcActivityPresenter getPresenter() {
        if (presenter == null) {
            presenter = new CalcActivityPresenter(this);
        }
        return presenter;
    }

    /**
     * EditText editable in ListView
     *
     * @return EditText
     */
    private EditText getEditText() {
        if (editText == null) {
            editText = (EditText) getActivity().findViewById(R.id.expression_editText);
        }
        return editText;
    }


}
