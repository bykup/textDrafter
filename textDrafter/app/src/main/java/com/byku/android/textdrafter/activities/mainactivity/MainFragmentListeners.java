package com.byku.android.textdrafter.activities.mainactivity;

import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.byku.android.textdrafter.activities.mainactivity.adapters.SmsKeysAdapter;
import com.byku.android.textdrafter.activities.mainactivity.views.MainRecycler;
import com.byku.android.textdrafter.databinding.FragmentMainBinding;
import com.byku.android.textdrafter.utils.parsers.TextParser;

public class MainFragmentListeners {
    private FragmentMainBinding binding;

    public MainFragmentListeners(FragmentMainBinding binding){
        this.binding = binding;
    }

    public TextWatcher getTextWatcherTelText(final MainFragmentModel model) {
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

    public TextWatcher getTextWatcherSmsText(final MainFragmentModel model, final MainRecycler recycler) {
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

    public View.OnFocusChangeListener getEditTelTextOnFocusChangeListener(){
        return new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                System.out.println("Focus change: " + b);
            }
        };
    }
}
