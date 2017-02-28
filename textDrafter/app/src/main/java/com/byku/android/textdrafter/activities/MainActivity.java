package com.byku.android.textdrafter.activities;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.byku.android.textdrafter.R;
import com.byku.android.textdrafter.activities.views.MainRecycler;
import com.byku.android.textdrafter.databinding.ActivityMainBinding;
import com.byku.android.textdrafter.utils.dialogs.DialogHandlers;
import com.byku.android.textdrafter.utils.dialogs.DialogListeners;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MainModel mainModel;
    private MainHandler mainHandler;
    private MainListeners mainListeners;
    private MainRecycler mainRecycler;
    private DialogListeners dialogListeners;
    private DialogHandlers dialogHandlers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBinding();
    }

    private void initBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        ButterKnife.bind(this);
        mainModel();
        mainHandler();
        mainListeners();
    }

    private void mainModel() {
        mainModel = new MainModel(this);
        mainRecycler = new MainRecycler(mainModel, binding.recyclerviewSmsValues);
        binding.setMainmodel(mainModel);
        binding.setMainrecycler(mainRecycler);
    }

    private void mainHandler() {
        mainHandler = new MainHandler();
        binding.setHandlers(mainHandler);
    }

    private void mainListeners() {
        dialogListeners = new DialogListeners();
        mainListeners = new MainListeners();
        dialogHandlers = new DialogHandlers();
        binding.setDlisteners(dialogListeners);
        binding.setDhandlers(dialogHandlers);
        binding.edittextTelNumber.addTextChangedListener(mainListeners.getTextWatcher(mainModel));
    }
}
