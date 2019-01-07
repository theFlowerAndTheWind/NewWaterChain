package com.quanminjieshui.waterchain.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class TableViewpagerAdapter extends FragmentPagerAdapter {
    private FragmentManager fragmentManager;
    private List<Fragment> fragments;
    private String[] titles;

    public TableViewpagerAdapter(FragmentManager fm, List<Fragment> fragments, String[] titles) {
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
        return titles[position];//数组越界
    }
}
