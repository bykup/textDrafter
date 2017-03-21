package com.byku.android.textdrafter.activities.mainactivity.views;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;

import com.byku.android.textdrafter.activities.mainactivity.MainFragmentModel;
import com.byku.android.textdrafter.activities.mainactivity.adapters.SmsValuesAdapter;
import com.byku.android.textdrafter.activities.mainactivity.adapters.models.KeyValueModel;
import com.byku.android.textdrafter.utils.parsers.TextParser;
import java.util.ArrayList;
import java.util.List;

public class MainRecycler {

    private MainFragmentModel mainFragmentModel;
    private RecyclerView recyclerView;
    private SmsValuesAdapter smsValuesAdapter;
    private List<KeyValueModel> list;

    public MainRecycler(MainFragmentModel mainFragmentModel, RecyclerView recyclerView) {
        this.mainFragmentModel = mainFragmentModel;
        this.recyclerView = recyclerView;
        initRecyclerView();
    }

    private void initRecyclerView() {
        list = new ArrayList<>();
        if (!TextUtils.isEmpty(mainFragmentModel.getSmsText())) {
            list = new TextParser().textToKeyValue(mainFragmentModel.getSmsText());
        }
        smsValuesAdapter = new SmsValuesAdapter(list, mainFragmentModel.getActivity());
        recyclerView.setAdapter(smsValuesAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(mainFragmentModel.getActivity()));
    }

    public SmsValuesAdapter getSmsValuesAdapter() {
        return smsValuesAdapter;
    }

    public List<KeyValueModel> getList() {
        return list;
    }
}
