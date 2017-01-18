package com.byku.android.textdrafter.utils.dialogs.interfaces;

import com.byku.android.textdrafter.utils.dialogs.DialogSmsInput;

public interface DialogListenerInterface {
    void onPositiveClick(DialogSmsInput dialogSmsInput, String smsText);
    void onNegativeClick(DialogSmsInput dialogSmsInput);
}
