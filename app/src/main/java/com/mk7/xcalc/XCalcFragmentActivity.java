package com.mk7.xcalc;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.mk7.xcalc.views.XCalcFragmentAdapter;

public class XCalcFragmentActivity extends FragmentActivity {

    final String TAG = "XCalcFragmentActivity";

    ViewPager mViewPager;
    XCalcFragmentAdapter adapter;

    public XCalcFragmentActivity() {
        adapter = new XCalcFragmentAdapter(getSupportFragmentManager(), this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xcalc);
        Log.d(TAG, ">>> onCreate");
        mViewPager = (ViewPager) this.findViewById(R.id.pager);
        mViewPager.setAdapter(adapter);



    }

    public void onClick_Command(View view) {
        adapter.onClick_Command(view);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.calc_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
