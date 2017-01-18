package com.byku.android.textdrafter.utils.dialogs;

import android.text.Editable;
import android.text.TextWatcher;

public class DialogListeners {

    public TextWatcher getTextWatcher(final DialogModel model) {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                model.smsText = editable.toString();
            }
        };
    }
}
