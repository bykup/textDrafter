package com.byku.android.textdrafter.activities.mainactivity;

import android.text.Editable;
import android.text.TextWatcher;

import com.byku.android.textdrafter.activities.mainactivity.views.MainRecycler;
import com.byku.android.textdrafter.databinding.ActivityMainBinding;
import com.byku.android.textdrafter.databinding.FragmentMainBinding;
import com.byku.android.textdrafter.utils.parsers.TextParser;

public class MainListeners {
    private FragmentMainBinding binding;

    public MainListeners(FragmentMainBinding binding){
        this.binding = binding;
    }

    public TextWatcher getTextWatcherTelText(final MainModel model) {
      return new TextWatcher() {
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
    }

    public TextWatcher getTextWatcherSmsText(final MainModel model, final MainRecycler recycler) {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                model.setSmsText(editable.toString());
                recycler.getList().clear();
                recycler.getList().addAll(new TextParser().textToKeyValue(model.getSmsText()));
                recycler.getSmsValuesAdapter().setList(recycler.getList());
            }
        };
    }
}
