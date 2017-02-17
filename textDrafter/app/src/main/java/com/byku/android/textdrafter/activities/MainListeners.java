package com.byku.android.textdrafter.activities;

import android.text.Editable;
import android.text.TextWatcher;

public class MainListeners {
    private TextWatcher textWatcher;

    public TextWatcher getTextWatcher(final MainModel model) {
        if(textWatcher == null)
            textWatcher = new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void afterTextChanged(Editable editable) {
                    model.setTelText(editable.toString());
                }
            };
        return textWatcher;
    }
}
