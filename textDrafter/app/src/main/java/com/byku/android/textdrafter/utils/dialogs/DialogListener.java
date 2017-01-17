package com.byku.android.textdrafter.utils.dialogs;

public interface DialogListener {
    void onPositiveClick(DialogSmsInput dialogSmsInput, String smsText);
    void onNegativeClick(DialogSmsInput dialogSmsInput);
}
