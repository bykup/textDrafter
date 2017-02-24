package com.byku.android.textdrafter.activities;

import com.byku.android.textdrafter.utils.dialogs.DialogHelper;
import com.byku.android.textdrafter.utils.dialogs.interfaces.DialogListenerInterface;
import com.byku.android.textdrafter.utils.parsers.SmsParser;

public class MainHandler {

    public void onPrepareClick(DialogListenerInterface listener, MainModel model) {
        new DialogHelper().createAcceptCancelEditDialog(model,null).setListener(listener).showDialog();
    }

    public void onSendSmsClick(DialogListenerInterface listener, MainModel model) {
        new DialogHelper().createShareSendEditDialog(model,new SmsParser(model).parseToSms()).setListener(listener).showDialog();
    }
}
