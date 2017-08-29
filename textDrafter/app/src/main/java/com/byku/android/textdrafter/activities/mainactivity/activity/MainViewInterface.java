package com.byku.android.textdrafter.activities.mainactivity.activity;

import android.app.Activity;

import com.byku.android.textdrafter.database.Models.KeyValueRecipentModel;

import java.util.List;

public interface MainViewInterface {

    Activity getActivity();

    void refreshViews();

    void setPagerPage(String key);

    void setRecyclerPage(String key);

    void setItemList(List<KeyValueRecipentModel> keys);
}
