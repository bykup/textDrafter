package com.byku.android.textdrafter.activities.mainactivity.views;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;

import com.byku.android.textdrafter.activities.mainactivity.adapters.SmsValuesAdapterImpl;
import com.byku.android.textdrafter.activities.mainactivity.adapters.models.KeyValueModel;
import com.byku.android.textdrafter.activities.mainactivity.fragment.MainFragmentModel;
import com.byku.android.textdrafter.utils.parsers.TextParser;

import java.util.ArrayList;
import java.util.List;

public class MainRecycler {

    private MainFragmentModel mainFragmentModel;
    private RecyclerView recyclerView;
    private SmsValuesAdapterImpl smsValuesAdapterImpl;
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
        smsValuesAdapterImpl = new SmsValuesAdapterImpl(list, mainFragmentModel.getActivity());
        recyclerView.setAdapter(smsValuesAdapterImpl);
        recyclerView.setLayoutManager(new LinearLayoutManager(mainFragmentModel.getActivity()));
    }

    public SmsValuesAdapterImpl getSmsValuesAdapterImpl() {
        return smsValuesAdapterImpl;
    }

    public List<KeyValueModel> getList() {
        return list;
    }
}
