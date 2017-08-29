package com.byku.android.textdrafter.activities.mainactivity.activity;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.byku.android.textdrafter.R;
import com.byku.android.textdrafter.activities.TextDrafterApp;
import com.byku.android.textdrafter.activities.mainactivity.adapters.SmsKeysAdapterImpl;
import com.byku.android.textdrafter.activities.mainactivity.managers.SpanningLinearLayoutManager;
import com.byku.android.textdrafter.database.Models.KeyValueRecipentModel;
import com.byku.android.textdrafter.databinding.ActivityMainBinding;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements MainViewInterface {

    @Inject
    MainActivityListner mainActivityListener;
    @Inject
    MainPresenterInterface mainPresenter;

    private ActivityMainBinding binding;
    private SmsKeysAdapterImpl smsKeysAdapter;
    private String currentKey;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initVariables();
        initBinding();
        initBindingVariables();
//        initRecyclerSizeListeners();
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
    public void setPagerPage(String key) {
//        currentKey = key;
//        try {
//            binding.pagerMain.setCurrentItem(((SmsKeysAdapter) binding.recyclerSmsList.getAdapter()).getKeyPosition(key));
//        } catch (Exception ex) {
//            initPageViewer();
//        }
    }

    @Override
    public void setRecyclerPage(String key) {
//        try {
//            ((SmsKeysAdapter) binding.recyclerSmsList.getAdapter()).setCurrentItemTo(key);
//            binding.recyclerSmsList.smoothScrollToPosition(((SmsKeysAdapter) binding.recyclerSmsList.getAdapter()).getKeyPosition(key));
//        } catch (Exception ex) {
//            initRecyclerView();
//        }
    }

    @Override
    public void setItemList(List<KeyValueRecipentModel> keys) {
        initAdapterAndAttach(keys);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(TextUtils.isEmpty(currentKey))
            return;
        setPagerPage(currentKey);
        setRecyclerPage(currentKey);
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

    private void initPageViewer() {
//        binding.pagerMain.setAdapter(
//                new MainViewPagerImpl(
//                        getSupportFragmentManager(),
//                        smsTextDbHelper.readAllKeysFromDb()));
//        binding.pagerMain.addOnPageChangeListener(mainActivityListener);
    }

//    private void initRecyclerSizeListeners() {
//        new ViewSizeCalculatorImpl(this).calcViewSize();
//    }
}
