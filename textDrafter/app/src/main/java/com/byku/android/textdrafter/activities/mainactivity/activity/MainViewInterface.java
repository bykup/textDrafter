package com.byku.android.textdrafter.activities.mainactivity.activity;

import android.app.Activity;

import com.byku.android.textdrafter.database.Models.KeyValueRecipentModel;

import java.util.List;

public interface MainViewInterface {

    Activity getActivity();

    void refreshViews();

    void initPageViewer();

    void setPagerPage(String key);

    void setRecyclerPage(int position);

    void setItemList(List<KeyValueRecipentModel> keys);
}
