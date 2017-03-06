package com.byku.android.textdrafter.activities;

import com.byku.android.textdrafter.activities.views.MainRecycler;
import com.byku.android.textdrafter.database.SmsTextDbHelper;
import com.byku.android.textdrafter.database.Tables.SmsTextContract;
import com.byku.android.textdrafter.utils.dialogs.DialogHandlers;
import com.byku.android.textdrafter.utils.dialogs.DialogHelper;
import com.byku.android.textdrafter.utils.dialogs.interfaces.DialogListenerInterface;
import com.byku.android.textdrafter.utils.parsers.SmsParser;

public class MainHandler {

    public void onSendSmsClick(DialogHandlers listener, MainModel model, MainRecycler mainRecycler) {
        new SmsTextDbHelper(model.getActivity())
                .writeToDatabase(SmsTextContract.TEMP_KEY, model.getSmsText(), model.getTelText())
                .close();
        new DialogHelper().setDialogHandlers(listener).createShareSendEditDialog(model,new SmsParser(model,mainRecycler).parseToSms()).setDialogHandlers(listener).showDialog();
    }
}
