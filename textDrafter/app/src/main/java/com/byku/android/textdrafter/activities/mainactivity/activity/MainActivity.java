package com.byku.android.textdrafter.activities.mainactivity.activity;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.byku.android.textdrafter.R;
import com.byku.android.textdrafter.activities.TextDrafterApp;
import com.byku.android.textdrafter.activities.mainactivity.adapters.SmsKeysAdapter;
import com.byku.android.textdrafter.activities.mainactivity.adapters.SmsKeysAdapterImpl;
import com.byku.android.textdrafter.activities.mainactivity.managers.SpanningLinearLayoutManager;
import com.byku.android.textdrafter.databinding.ActivityMainBinding;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements MainView {

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
        initRecyclerView();
        initPageViewer();
//        initRecyclerSizeListeners();
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void refreshViews() {
        if (binding == null)
            return;
        initRecyclerView();
        initPageViewer();
    }

    @Override
    public void setPagerPage(String key) {
        currentKey = key;
        try {
            binding.pagerMain.setCurrentItem(((SmsKeysAdapter) binding.recyclerSmsList.getAdapter()).getKeyPosition(key));
        } catch (Exception ex) {
            initPageViewer();
        }
    }

    @Override
    public void setRecyclerPage(String key) {
        try {
            ((SmsKeysAdapter) binding.recyclerSmsList.getAdapter()).setCurrentItemTo(key);
            binding.recyclerSmsList.smoothScrollToPosition(((SmsKeysAdapter) binding.recyclerSmsList.getAdapter()).getKeyPosition(key))
            ;
        } catch (Exception ex) {
            initRecyclerView();
        }
    }

//    @Override
//    public View getRestrictingView() {
//        return binding.recyclerSmsList;
//    }
//
//    @Override
//    public void setButtonWidth(ViewDimensions viewDimensions) {
//        viewMetricsSingleton.setAddKeyDimensions(viewDimensions);
//    }

    @Override
    protected void onResume() {
        setPagerPage(currentKey);
        setRecyclerPage(currentKey);
        super.onResume();
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

    private void initRecyclerView() {
        smsKeysAdapter = new SmsKeysAdapterImpl(this);
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
