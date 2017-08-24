package com.byku.android.textdrafter.activities.mainactivity.activity;

import com.byku.android.textdrafter.database.SmsTextDbHelperInterface;

public class MainPresenter implements MainPresenterInterface {

    private SmsTextDbHelperInterface smsTextDbHelper;
    private MainView mainView;

    public MainPresenter(SmsTextDbHelperInterface smsTextDbHelper) {
        this.smsTextDbHelper = smsTextDbHelper;
    }

    @Override
    public void attachView(MainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void onCreate() {

    }
}
