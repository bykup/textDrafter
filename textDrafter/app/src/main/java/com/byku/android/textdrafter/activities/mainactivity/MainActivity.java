package com.byku.android.textdrafter.activities.mainactivity;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.byku.android.textdrafter.R;
import com.byku.android.textdrafter.activities.mainactivity.adapters.MainViewPager;
import com.byku.android.textdrafter.activities.mainactivity.adapters.SmsKeysAdapter;
import com.byku.android.textdrafter.activities.mainactivity.adapters.listeners.SmsKeysHandlersImpl;
import com.byku.android.textdrafter.database.SmsTextDbHelperImpl;
import com.byku.android.textdrafter.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements MainView {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBinding();
        initBindingVariables();
        initRecyclerView();
        initPageViewer();
    }

    private void initBinding() {
        binding = DataBindingUtil.setContentView(
                this,
                R.layout.activity_main);
        binding.setHandlers(new MainHandler(this));
    }

    private void initBindingVariables() {
        binding.setMainview(this);
    }

    private void initRecyclerView() {
        binding.recyclerSmsList.setAdapter(
                new SmsKeysAdapter(
                        new SmsTextDbHelperImpl(this).readAllKeysFromDb(),
                        this,
                        this));
        binding.recyclerSmsList.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));
    }

    private void initPageViewer() {
        binding.pagerMain.setAdapter(
                new MainViewPager(
                        getSupportFragmentManager(),
                        new SmsTextDbHelperImpl(this).readAllKeysFromDb()));
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
    }

    @Override
    public void setPage(int pageNumber) {
        try {
            binding.pagerMain.setCurrentItem(pageNumber);
        } catch(Exception ex){
            initPageViewer();
        }
    }
}
