package com.byku.android.textdrafter.activities.mainactivity.activity;

import com.byku.android.textdrafter.utils.dialogs.DialogHelper;

public class MainHandler {

    public void onNewSmsKeyClick(MainViewInterface mainView){
        new DialogHelper().createAddKeyValueEditDialog(mainView).showDialog();
    }

}
