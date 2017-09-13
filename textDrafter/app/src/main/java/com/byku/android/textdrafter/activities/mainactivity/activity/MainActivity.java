package com.byku.android.textdrafter.activities.mainactivity.activity;

import android.Manifest;
import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

import com.byku.android.textdrafter.R;
import com.byku.android.textdrafter.activities.TextDrafterApp;
import com.byku.android.textdrafter.activities.mainactivity.adapters.MainViewPagerAdapterImpl;
import com.byku.android.textdrafter.activities.mainactivity.adapters.SmsKeysAdapter;
import com.byku.android.textdrafter.activities.mainactivity.adapters.SmsKeysAdapterImpl;
import com.byku.android.textdrafter.activities.mainactivity.managers.SpanningLinearLayoutManager;
import com.byku.android.textdrafter.database.Models.KeyValueRecipentModel;
import com.byku.android.textdrafter.databinding.ActivityMainBinding;
import com.byku.android.textdrafter.utils.PermissionHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

// TODO: 11.09.2017 - lock size of activity when keyboard is present - change so it will move up,  
// TODO: 11.09.2017 - parsing doubles - change to x decimal places - depending on precision of data
// TODO: 11.09.2017 - not compatibile with multi-window - change it

public class MainActivity extends AppCompatActivity implements MainViewInterface {

    @Inject
    MainActivityListner mainActivityListener;
    @Inject
    MainPresenterInterface mainPresenter;

    private ActivityMainBinding binding;
    private SmsKeysAdapterImpl smsKeysAdapter;
    private MainViewPagerAdapterImpl mainViewPager;
    private String currentKey = "";
    private List<KeyValueRecipentModel> models;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initVariables();
        initBinding();
        initBindingVariables();
        if (!PermissionHelper.hasContactPermissions(this))
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, 0);
        mainPresenter.onCreate(this).refresh();
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void refreshViews() {
        if (binding == null)
            return;
        mainPresenter.refresh();
    }

    @Override
    public void initPageViewer() {
        mainViewPager = new MainViewPagerAdapterImpl(getSupportFragmentManager(), models);
        binding.pagerMain.setAdapter(mainViewPager);
        binding.pagerMain.addOnPageChangeListener(mainActivityListener);
    }

    @Override
    public void setPagerPage(String key) {
        this.currentKey = key;
        try {
            binding.pagerMain.setCurrentItem(smsKeysAdapter.getKeyPosition(key));
        } catch (NullPointerException ex) {
            mainPresenter.refresh();
        }
    }

    @Override
    public void setRecyclerPage(int position) {
        try {
            ((SmsKeysAdapter) binding.recyclerSmsList.getAdapter()).setCurrentItemTo(position);
            binding.recyclerSmsList.smoothScrollToPosition(position);
        } catch (Exception ex) {
            mainPresenter.refresh();
        }
    }

    @Override
    public void setItemList(List<KeyValueRecipentModel> keys) {
        if (smsKeysAdapter == null)
            initAdapterAndAttach();
        initListAndNotifyAdapter(keys);
        initPageViewer();
        setPagerPage(currentKey);
    }


    private void initVariables() {
        ((TextDrafterApp) getApplication()).getActivityComponent().inject(this);
        mainActivityListener.attachView(this);
        models = new ArrayList<>();
    }

    private void initBinding() {
        binding = DataBindingUtil.setContentView(
                this,
                R.layout.activity_main);
        binding.setHandlers(new MainHandler());
    }

    private void initBindingVariables() {
        binding.setMainview(this);
    }

    private void initAdapterAndAttach() {
        smsKeysAdapter = new SmsKeysAdapterImpl(this, mainPresenter, models);
        binding.recyclerSmsList.setAdapter(smsKeysAdapter);
        binding.recyclerSmsList.setLayoutManager(new SpanningLinearLayoutManager(this, SpanningLinearLayoutManager.HORIZONTAL, false));
    }

    private void initListAndNotifyAdapter(List<KeyValueRecipentModel> models) {
        Collections.sort(models);
        int itemsAdded = 0;
        for (KeyValueRecipentModel model : models) {
            if (!this.models.contains(model)) {
                this.models.add(model);
                itemsAdded++;
            }
        }
        smsKeysAdapter.notifyItemRangeInserted(this.models.size() - itemsAdded, itemsAdded);
    }

}
