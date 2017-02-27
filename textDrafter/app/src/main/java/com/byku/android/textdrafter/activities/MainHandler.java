package com.byku.android.textdrafter.activities;

import com.byku.android.textdrafter.utils.dialogs.DialogHandlers;
import com.byku.android.textdrafter.utils.dialogs.DialogHelper;
import com.byku.android.textdrafter.utils.dialogs.interfaces.DialogListenerInterface;
import com.byku.android.textdrafter.utils.parsers.SmsParser;

public class MainHandler {

    public void onPrepareClick(DialogListenerInterface listener, MainModel model) {
        new DialogHelper().setListener(listener).createAcceptCancelEditDialog(model,null).showDialog();
    }

    public void onSendSmsClick(DialogHandlers listener, MainModel model) {
        new DialogHelper().setDialogHandlers(listener).createShareSendEditDialog(model,new SmsParser(model).parseToSms()).setDialogHandlers(listener).showDialog();
    }
}
