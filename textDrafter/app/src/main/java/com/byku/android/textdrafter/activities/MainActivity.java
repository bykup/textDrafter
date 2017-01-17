package com.byku.android.textdrafter.activities;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.byku.android.textdrafter.R;
import com.byku.android.textdrafter.databinding.ActivityMainBinding;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recyclerview_sms_values)
    RecyclerView recyclerViewSmsValues;
    @BindView(R.id.textview_sms_draft)
    TextView textView;

    private MainModel mainView;
    private MainHandler mainHandler;
    private MainListeners mainListeners;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBinding();
    }

    private void initBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        ButterKnife.bind(this);
        mainView = new MainModel(this,recyclerViewSmsValues);
        mainHandler = new MainHandler();
        mainListeners = new MainListeners();

        binding.setMainmodel(mainView);
        binding.setHandlers(mainHandler);
        binding.setListeners(mainListeners);
    }
}
