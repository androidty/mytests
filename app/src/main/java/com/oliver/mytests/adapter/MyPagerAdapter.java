package com.oliver.mytests.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by android_1 on 2016/12/27.
 */

public class MyPagerAdapter extends FragmentStatePagerAdapter{
//    private String[] tabs = {"首页","分类","朋友圈","个人"};
    private List<Fragment> fragments = new ArrayList<>();
    public MyPagerAdapter(FragmentManager fm,List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

//    @Override
//    public CharSequence getPageTitle(int position) {
//        return tabs[position];
//    }


}
