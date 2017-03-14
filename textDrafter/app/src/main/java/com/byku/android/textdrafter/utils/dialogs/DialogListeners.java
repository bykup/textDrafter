package com.byku.android.textdrafter.utils.dialogs;

import android.text.Editable;
import android.text.TextWatcher;

public class DialogListeners {
    private TextWatcher textWatcher;

    public TextWatcher getTextWatcher(final DialogModel model) {
        if (textWatcher == null)
            textWatcher = new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void afterTextChanged(Editable editable) {
                    model.setSmsText(editable.toString());
                }
            };

        return textWatcher;
    }
}
