package com.byku.android.textdrafter.utils.dialogs;

import android.app.Activity;
import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.text.Editable;
import android.text.TextWatcher;

import com.android.databinding.library.baseAdapters.BR;

public class DialogModel extends BaseObservable {
    private String smsText;
    private Activity activity;

    public DialogModel(Activity activity) {
        this.activity = activity;
    }

    public DialogModel(String smsText, Activity activity) {
        this.smsText = smsText;
        this.activity = activity;
    }

    @Bindable
    public String getSmsText() {
        return smsText;
    }

    public void setSmsText(String smsText) {
        this.smsText = smsText;
        notifyPropertyChanged(BR.smsText);
    }

    public Context getActivity() {
        return activity;
    }

    public TextWatcher getTextWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                smsText = editable.toString();
            }
        };
    }
}
