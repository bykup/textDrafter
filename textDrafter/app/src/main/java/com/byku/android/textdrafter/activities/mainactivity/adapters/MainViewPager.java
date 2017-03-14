package com.byku.android.textdrafter.activities.mainactivity.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.byku.android.textdrafter.activities.mainactivity.MainActivityFragment;

import java.util.List;

public class MainViewPager extends FragmentStatePagerAdapter {

    List<String> smsTypes;

    public MainViewPager(FragmentManager fm) {
        super(fm);
    }

    public MainViewPager(FragmentManager fm,List<String> smsTypes) {
        super(fm);
        this.smsTypes = smsTypes;
    }

    @Override
    public Fragment getItem(int position) {
        return new MainActivityFragment();
    }

    @Override
    public int getCount() {
        if(smsTypes == null)
            return 0;
        return smsTypes.size();
    }

}

