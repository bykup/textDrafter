package com.byku.android.textdrafter.activities.mainactivity;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.byku.android.textdrafter.R;
import com.byku.android.textdrafter.activities.mainactivity.adapters.MainViewPager;
import com.byku.android.textdrafter.activities.mainactivity.adapters.SmsKeysAdapter;
import com.byku.android.textdrafter.activities.mainactivity.adapters.SmsKeysAdapterImpl;
import com.byku.android.textdrafter.database.SmsTextDbHelperImpl;
import com.byku.android.textdrafter.databinding.ActivityMainBinding;
import com.byku.android.textdrafter.utils.views.ViewSizeCalculatorImpl;
import com.byku.android.textdrafter.utils.views.ViewsSource;
import com.byku.android.textdrafter.utils.views.observers.ViewDimensions;
import com.byku.android.textdrafter.utils.views.observers.ViewMetricsSourceSubject;

public class MainActivity extends AppCompatActivity implements MainView, ViewsSource{

    private ActivityMainBinding binding;
    private SmsTextDbHelperImpl smsTextDbHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initVariables();
        initBinding();
        initBindingVariables();
        initRecyclerView();
        initPageViewer();
        initRecyclerSizeListeners();
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
    public void setPagerPage(int pageNumber) {
        try {
            binding.pagerMain.setCurrentItem(pageNumber);
        } catch(Exception ex){
            initPageViewer();
        }
    }

    @Override
    public void setRecyclerPage(int pageNumber) {
        try{
            ((SmsKeysAdapter)binding.recyclerSmsList.getAdapter()).setCurrentItemTo(pageNumber);
            binding.recyclerSmsList.smoothScrollToPosition(pageNumber);
        }catch (Exception ex){
            initRecyclerView();
        }
    }

    @Override
    public View getRestrictingView() {
        return binding.recyclerSmsList;
    }

    @Override
    public void setButtonWidth(ViewDimensions viewDimensions) {
        ViewMetricsSourceSubject.getInstance().setAddKeyDimensions(viewDimensions);
    }

    private void initVariables(){
        smsTextDbHelper = new SmsTextDbHelperImpl(this);
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
                new SmsKeysAdapterImpl(
                        smsTextDbHelper,
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
        binding.pagerMain.addOnPageChangeListener(new MainActivityListner().getOnPageChangeListener(this));
    }

    private void initRecyclerSizeListeners(){
        new ViewSizeCalculatorImpl(this).calcViewSize();
    }
}
