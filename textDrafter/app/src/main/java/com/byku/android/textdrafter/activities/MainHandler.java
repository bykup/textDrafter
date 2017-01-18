package com.byku.android.textdrafter.activities;

import android.app.Activity;

import com.byku.android.textdrafter.utils.dialogs.interfaces.DialogListenerInterface;
import com.byku.android.textdrafter.utils.dialogs.DialogSmsInput;
import com.byku.android.textdrafter.utils.tags.FragmentTags;

public class MainHandler {

    public void onClick(Activity activity, DialogListenerInterface listener, MainModel model){
        new DialogSmsInput().setDefaultText(model.getSmsText()).setListener(listener).show(activity.getFragmentManager(), FragmentTags.dialogTag);
    }
}
