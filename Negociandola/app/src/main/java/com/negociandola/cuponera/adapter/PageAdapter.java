package com.negociandola.cuponera.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by ignacio on 04/07/16.
 */
public class PageAdapter extends FragmentPagerAdapter {
    private List<String> mTitleList;
    private List<Fragment> mFragments;

    public PageAdapter(FragmentManager manager, List<String> titleList, List<Fragment> fragments) {
        super(manager);
        mTitleList = titleList;
        mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mTitleList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitleList.get(position);
    }
}
