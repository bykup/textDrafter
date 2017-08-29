package com.byku.android.textdrafter.activities.mainactivity.activity;

public interface MainPresenterInterface {

    MainPresenterInterface onCreate(MainViewInterface mainView);

    MainPresenterInterface refresh();

    MainPresenterInterface removeKey(String key);

}
