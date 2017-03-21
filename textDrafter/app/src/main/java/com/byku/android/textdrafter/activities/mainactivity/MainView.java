package com.byku.android.textdrafter.activities.mainactivity;

import android.app.Activity;

public interface MainView {

    Activity getActivity();

    void refreshViews();

    void setPage(int pageNumber);
}
