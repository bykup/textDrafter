package com.byku.android.textdrafter.activities.views;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;

import com.byku.android.textdrafter.activities.MainModel;
import com.byku.android.textdrafter.activities.adapters.SmsValuesAdapter;
import com.byku.android.textdrafter.activities.adapters.models.KeyValueModel;
import com.byku.android.textdrafter.utils.parsers.TextParser;
import java.util.ArrayList;
import java.util.List;

public class MainRecycler {

    private MainModel mainModel;
    private RecyclerView recyclerView;
    private SmsValuesAdapter smsValuesAdapter;
    private List<KeyValueModel> list;

    public MainRecycler(MainModel mainModel, RecyclerView recyclerView) {
        this.mainModel = mainModel;
        this.recyclerView = recyclerView;
        initRecyclerView();
    }

    private void initRecyclerView() {
        list = new ArrayList<>();
        if (!TextUtils.isEmpty(mainModel.getSmsText())) {
            list = new TextParser().textToKeyValue(mainModel.getSmsText());
        }
        smsValuesAdapter = new SmsValuesAdapter(list, mainModel.getActivity());
        recyclerView.setAdapter(smsValuesAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(mainModel.getActivity()));
    }

    public SmsValuesAdapter getSmsValuesAdapter() {
        return smsValuesAdapter;
    }

    public List<KeyValueModel> getList() {
        return list;
    }
}
