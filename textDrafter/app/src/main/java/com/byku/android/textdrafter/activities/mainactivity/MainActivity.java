package com.byku.android.textdrafter.activities.mainactivity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.byku.android.textdrafter.R;
import com.byku.android.textdrafter.activities.mainactivity.adapters.MainViewPager;
import com.byku.android.textdrafter.activities.mainactivity.adapters.SmsKeysAdapter;
import com.byku.android.textdrafter.database.SmsTextDbHelperImpl;
import com.byku.android.textdrafter.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBinding();
    }

    private void initBinding(){
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.recyclerSmsList.setAdapter(new SmsKeysAdapter(new SmsTextDbHelperImpl(this).readAllKeysFromDb(),this));
        binding.recyclerSmsList.setLayoutManager(new LinearLayoutManager(this));
        binding.pagerMain.setAdapter(new MainViewPager(getSupportFragmentManager(),new SmsTextDbHelperImpl(this).readAllKeysFromDb()));
    }
}
