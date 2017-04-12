package com.byku.android.textdrafter.activities.mainactivity;

import android.support.v4.view.ViewPager;

import com.byku.android.textdrafter.activities.mainactivity.MainFragmentModel;
import com.byku.android.textdrafter.activities.mainactivity.adapters.SmsKeysAdapter;

public class MainActivityListner {

    public ViewPager.OnPageChangeListener getOnPageChangeListener(final MainView mainView){
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
