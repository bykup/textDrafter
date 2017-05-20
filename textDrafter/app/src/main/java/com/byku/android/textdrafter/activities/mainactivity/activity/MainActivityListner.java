package com.byku.android.textdrafter.activities.mainactivity.activity;

import android.support.v4.view.ViewPager;

public class MainActivityListner {

    public static ViewPager.OnPageChangeListener getOnPageChangeListener(final MainView mainView){
        return new ViewPager.OnPageChangeListener(){
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mainView.setRecyclerPage(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        };
    }
}
