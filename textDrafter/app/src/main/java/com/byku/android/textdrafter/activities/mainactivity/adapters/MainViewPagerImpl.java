package com.byku.android.textdrafter.activities.mainactivity.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.byku.android.textdrafter.activities.mainactivity.activity.MainActivityConstants;
import com.byku.android.textdrafter.activities.mainactivity.fragment.MainFragment;

import java.util.List;

public class MainViewPagerImpl extends FragmentStatePagerAdapter {

    List<String> smsTypes;

    public MainViewPagerImpl(FragmentManager fm) {
        super(fm);
    }

    public MainViewPagerImpl(FragmentManager fm, List<String> smsTypes) {
        super(fm);
        this.smsTypes = smsTypes;
    }

    @Override
    public Fragment getItem(int position) {
        MainFragment mainFragment = new MainFragment();
        Bundle bundle = new Bundle();
        bundle.putString(MainActivityConstants.SMS_KEYS,smsTypes.get(position));
        mainFragment.setArguments(bundle);
        return mainFragment;
    }

    @Override
    public int getCount() {
        if(smsTypes == null)
            return 0;
        return smsTypes.size();
    }

}

