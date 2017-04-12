package com.byku.android.textdrafter.activities.mainactivity;

import android.app.Activity;

import com.byku.android.textdrafter.utils.dialogs.DialogHelper;

public class MainHandler {
    Activity activity;

    public MainHandler(Activity activity){
        this.activity = activity;
    }

    public void onNewSmsKeyClick(MainView mainView){
        new DialogHelper().createAddKeyValueEditDialog(mainView).showDialog();
    }

}
