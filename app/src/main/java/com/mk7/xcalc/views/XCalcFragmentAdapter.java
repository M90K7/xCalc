package com.mk7.xcalc.views;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by MK7 on 5/28/2015.
 */
public class XCalcFragmentAdapter extends FragmentPagerAdapter {


    Activity activity;
    ArrayList<Fragment> fragments = new ArrayList<>();


    public XCalcFragmentAdapter(FragmentManager fm, Activity activity) {
        super(fm);

        this.activity = activity;
        fragments.add(new XCalcFragment());
        fragments.add(new DescriptionFragment());
        fragments.add(new AboutFragment());
    }

    @Override
    public Fragment getItem(int position) {


        //Bundle args = new Bundle();
        //args.putString(ProductFragment.PRODUCT_DESC, ListFragmentProducts.listProducts[position]);
        //fragmentItem.setArguments(args);

        return getFragment(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        ISetupFragment fragment = (ISetupFragment) getFragment(position);
        return fragment.getTitle();
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    Fragment getFragment(int position) {
        Fragment fragmentItem = fragments.get(position);
        return fragmentItem;
    }

    public void onClick_Command(View view) {
        ((XCalcFragment) getFragment(0)).onClick_Command(view);
    }
}
