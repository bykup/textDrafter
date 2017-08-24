package com.byku.android.textdrafter.activities.mainactivity.activity;

import android.support.v4.view.ViewPager;
import android.view.ViewTreeObserver;

import javax.inject.Inject;

public class MainActivityListner implements ViewPager.OnPageChangeListener{

    private MainView mainView;

    @Inject
    public MainActivityListner() {
    }

    public void attachView(MainView mainView){
        this.mainView = mainView;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
//        mainView.setRecyclerPage(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
