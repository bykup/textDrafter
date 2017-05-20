package com.byku.android.textdrafter.activities.mainactivity.activity;

import android.app.Activity;

public interface MainView {

    Activity getActivity();

    void refreshViews();

    void setPagerPage(int pageNumber);

    void setRecyclerPage(int pageNumber);
}
