package com.byku.android.textdrafter.utils.dialogs;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

public class DialogModel extends BaseObservable {
    private String smsText;

    public DialogModel() {
        smsText = null;
    }

    public DialogModel(String smsText) {
        this.smsText = smsText;
    }

    @Bindable
    public String getSmsText() {
        return smsText;
    }

    public void setSmsText(String smsText) {
        this.smsText = smsText;
    }
}
