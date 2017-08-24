package com.byku.android.textdrafter.activities.mainactivity.activity;

import android.app.Activity;

public interface MainView {

    Activity getActivity();

    void refreshViews();

    void setPagerPage(String key);

    void setRecyclerPage(String key);
}
