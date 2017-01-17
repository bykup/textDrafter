package com.byku.android.textdrafter.activities;

import android.app.Activity;

import com.byku.android.textdrafter.utils.dialogs.DialogListener;
import com.byku.android.textdrafter.utils.dialogs.DialogSmsInput;
import com.byku.android.textdrafter.utils.tags.FragmentTags;

public class MainHandler {

    public void onClick(Activity activity, DialogListener listener){
        new DialogSmsInput().setListener(listener).show(activity.getFragmentManager(), FragmentTags.dialogTag);
    }
}
