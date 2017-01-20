package com.byku.android.textdrafter.activities.adapters.listeners;

import android.text.Editable;
import android.text.TextWatcher;

import com.byku.android.textdrafter.activities.adapters.models.RecyclerSmsModel;

public class SmsValuesListeners {
    private TextWatcher textWatcher;


    public TextWatcher getTextWatcher(final RecyclerSmsModel recyclerSmsModel) {
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
                    recyclerSmsModel.setValue(editable.toString());
                }
            };

        return textWatcher;
    }

}
