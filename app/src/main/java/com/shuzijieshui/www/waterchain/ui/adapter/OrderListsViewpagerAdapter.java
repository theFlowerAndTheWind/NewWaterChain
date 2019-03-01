package com.shuzijieshui.www.waterchain.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.shuzijieshui.www.waterchain.ui.fragment.OrderListsTabFragment;

import java.util.ArrayList;


public class OrderListsViewpagerAdapter extends FragmentPagerAdapter {
    private ArrayList<OrderListsTabFragment>fragments;
    private String[] titles;

    public OrderListsViewpagerAdapter(FragmentManager fm, ArrayList<OrderListsTabFragment> fragments, String[] titles) {
        super(fm);
        this.fragments = fragments;
        this.titles=titles;
        fm.beginTransaction().commitAllowingStateLoss();
    }


    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    //ViewPager与TabLayout绑定后，这里获取到PageTitle就是Tab的Text
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
