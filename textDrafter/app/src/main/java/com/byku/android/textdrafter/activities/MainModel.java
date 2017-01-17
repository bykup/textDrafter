package com.byku.android.textdrafter.activities;


import android.app.Activity;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.databinding.library.baseAdapters.BR;
import com.byku.android.textdrafter.activities.adapters.SmsValuesAdapter;
import com.byku.android.textdrafter.activities.adapters.models.KeyValueModel;

import java.util.ArrayList;
import java.util.List;

public class MainModel extends BaseObservable {
    private String smsText;
    private Activity activity;
    private RecyclerView recyclerView;
    private SmsValuesAdapter smsValuesAdapter;
    private List<KeyValueModel> list;

    public MainModel(Activity activity, RecyclerView recyclerView) {
        this.activity = activity;
        this.recyclerView = recyclerView;
        initRecyclerView();
    }

    @Bindable
    public String getSmsText() {
        return smsText;
    }

    public void setSmsText(String smsText) {
        this.smsText = smsText;
        notifyPropertyChanged(BR.smsText);
    }

    public Activity getActivity() {
        return activity;
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    public SmsValuesAdapter getSmsValuesAdapter() {
        return smsValuesAdapter;
    }

    public List<KeyValueModel> getList() {
        return list;
    }

    private void initRecyclerView() {
        list = new ArrayList<>();
        smsValuesAdapter = new SmsValuesAdapter(list, activity);
        recyclerView.setAdapter(smsValuesAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
    }
}
