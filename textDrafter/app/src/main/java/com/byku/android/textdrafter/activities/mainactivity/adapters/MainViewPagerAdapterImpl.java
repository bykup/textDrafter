package com.byku.android.textdrafter.activities.mainactivity.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.byku.android.textdrafter.activities.mainactivity.activity.MainPresenterInterface;
import com.byku.android.textdrafter.activities.mainactivity.fragment.MainFragment;
import com.byku.android.textdrafter.database.Models.KeyValueRecipentModel;

import java.util.List;

public class MainViewPagerAdapterImpl extends FragmentStatePagerAdapter {

    private List<KeyValueRecipentModel> smsTypes;
    private MainPresenterInterface mainPresenterInterface;


    public MainViewPagerAdapterImpl(FragmentManager fm) {
        super(fm);
    }

    public MainViewPagerAdapterImpl(FragmentManager fm, List<KeyValueRecipentModel> smsTypes, MainPresenterInterface mainPresenterInterface) {
        super(fm);
        this.smsTypes = smsTypes;
        this.mainPresenterInterface = mainPresenterInterface;
    }

    @Override
    public Fragment getItem(int position) {
        MainFragment mainFragment = new MainFragment();
        mainFragment.setCurrentModel(smsTypes.get(position));
        mainFragment.setMainPresenter(mainPresenterInterface);
        return mainFragment;
    }

    @Override
    public int getCount() {
        if (smsTypes == null)
            return 0;
        return smsTypes.size();
    }

}

