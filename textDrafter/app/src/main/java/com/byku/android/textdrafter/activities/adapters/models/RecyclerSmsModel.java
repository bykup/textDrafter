package com.byku.android.textdrafter.activities.adapters.models;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import com.android.databinding.library.baseAdapters.BR;

public class RecyclerSmsModel extends BaseObservable {
    private String key;
    private String value;

    public RecyclerSmsModel(String key) {
        setKey(key);
        setValue("");
    }

    public RecyclerSmsModel(String key, String value) {
        setKey(key);
        setValue(value);
    }

    @Bindable
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
        notifyPropertyChanged(BR.key);
    }

    @Bindable
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
