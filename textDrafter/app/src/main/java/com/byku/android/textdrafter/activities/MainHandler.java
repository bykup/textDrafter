package com.byku.android.textdrafter.activities;

import android.app.Activity;

import com.byku.android.textdrafter.utils.dialogs.interfaces.DialogListenerInterface;
import com.byku.android.textdrafter.utils.dialogs.DialogSmsInput;
import com.byku.android.textdrafter.utils.parsers.SmsParser;
import com.byku.android.textdrafter.utils.tags.FragmentTags;

public class MainHandler {

    public void onPrepareClick(Activity activity, DialogListenerInterface listener, MainModel model) {
        new DialogSmsInput().setDefaultText(model.getSmsText()).setListener(listener).show(activity.getFragmentManager(), FragmentTags.dialogTag);
    }

    public void onSendSmsClick(Activity activity, DialogListenerInterface listener, MainModel mainModel) {
        new DialogSmsInput().setDefaultText(new SmsParser(mainModel).parseToSms()).setListener(listener).show(activity.getFragmentManager(), FragmentTags.dialogTag);
    }
}
