package com.mk7.xcalc.views;


import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.mk7.xcalc.R;

/**
 * Created by MK7 on 5/28/2015.
 */


public class DescriptionFragment extends ListFragment implements ISetupFragment {

    private String[] titleDsc;
    private String[] infoDesc;
    private ArrayAdapter<String> adapter;

    public DescriptionFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        titleDsc = getActivity().getResources().getStringArray(R.array.title_desc);
        infoDesc = getActivity().getResources().getStringArray(R.array.info_desc);

        adapter = new ArrayAdapter<String>(this.getActivity(), R.layout.fragemnt_description_template, titleDsc) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = getActivity().getLayoutInflater().inflate(R.layout.fragemnt_description_template, null);

                String title = titleDsc[position];
                String info = infoDesc[position];

                TextView titleTextView = (TextView) view.findViewById(R.id.title_textView);
                titleTextView.setText(title);

                TextView infoTextView = (TextView) view.findViewById(R.id.info_textView);
                infoTextView.setText(info);

                return view;
            }
        };

        this.setListAdapter(adapter);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
    }

    @Override
    public String getTitle() {
        return "Descriptions";
    }
}
