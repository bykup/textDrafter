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

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements MainViewInterface {

    @Inject
    MainActivityListner mainActivityListener;
    @Inject
    MainPresenterInterface mainPresenter;

    private ActivityMainBinding binding;
    private SmsKeysAdapterImpl smsKeysAdapter;
    private MainViewPagerAdapterImpl mainViewPager;
    private String currentKey = "";

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
    public void initPageViewer(List<KeyValueRecipentModel> models) {
        mainViewPager = new MainViewPagerAdapterImpl(getSupportFragmentManager(), models, mainPresenter);
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
        initAdapterAndAttach(keys);
        initPageViewer(keys);
        // TODO: 06.09.2017 implement notify item inserted! Mandarory!
        int position =  ((SmsKeysAdapter) binding.recyclerSmsList.getAdapter()).getKeyPosition(currentKey);
        ((SmsKeysAdapter) binding.recyclerSmsList.getAdapter()).setCurrentItemTo(position);
        binding.recyclerSmsList.smoothScrollToPosition(position);
        setPagerPage(currentKey);
    }


    private void initVariables() {
        ((TextDrafterApp) getApplication()).getActivityComponent().inject(this);
        mainActivityListener.attachView(this);
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

    private void initAdapterAndAttach(List<KeyValueRecipentModel> models) {
        smsKeysAdapter = new SmsKeysAdapterImpl(this, mainPresenter, models);
        binding.recyclerSmsList.setAdapter(smsKeysAdapter);
        binding.recyclerSmsList.setLayoutManager(new SpanningLinearLayoutManager(this, SpanningLinearLayoutManager.HORIZONTAL, false));
    }

}
