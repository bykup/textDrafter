package com.byku.android.textdrafter.utils.dialogs.interfaces;

import com.byku.android.textdrafter.utils.dialogs.DialogHelper;

public interface DialogListenerInterface {
    void onPositiveClick(DialogHelper dialogSmsInput, String smsText);
    void onNegativeClick(DialogHelper dialogSmsInput);
}
