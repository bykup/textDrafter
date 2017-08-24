package com.byku.android.textdrafter.activities.mainactivity.fragment;

import com.byku.android.textdrafter.activities.mainactivity.views.MainRecycler;
import com.byku.android.textdrafter.database.SmsTextDbHelper;
import com.byku.android.textdrafter.utils.dialogs.DialogHelper;
import com.byku.android.textdrafter.utils.parsers.SmsParser;

public class MainFragmentHandler {

    public void onSendSmsClick(MainFragmentModel model, MainRecycler mainRecycler) {
        new SmsTextDbHelper(model.getActivity())
                .writeToDatabase(model.getSmsKey(), model.getSmsText(), model.getTelText())
                .close();
        new DialogHelper().createShareSendEditDialog(model,new SmsParser(model,mainRecycler).parseToSms()).showDialog();
    }
}
