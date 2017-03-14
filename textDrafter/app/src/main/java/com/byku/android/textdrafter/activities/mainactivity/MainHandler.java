package com.byku.android.textdrafter.activities.mainactivity;

import com.byku.android.textdrafter.activities.mainactivity.views.MainRecycler;
import com.byku.android.textdrafter.database.SmsTextDbHelperImpl;
import com.byku.android.textdrafter.database.Tables.SmsTextContract;
import com.byku.android.textdrafter.utils.dialogs.DialogHandlers;
import com.byku.android.textdrafter.utils.dialogs.DialogHelper;
import com.byku.android.textdrafter.utils.parsers.SmsParser;

public class MainHandler {

    public void onSendSmsClick(DialogHandlers listener, MainModel model, MainRecycler mainRecycler) {
        new SmsTextDbHelperImpl(model.getActivity())
                .writeToDatabase(SmsTextContract.TEMP_KEY, model.getSmsText(), model.getTelText())
                .close();
        new DialogHelper().setDialogHandlers(listener).createShareSendEditDialog(model,new SmsParser(model,mainRecycler).parseToSms()).setDialogHandlers(listener).showDialog();
    }
}
