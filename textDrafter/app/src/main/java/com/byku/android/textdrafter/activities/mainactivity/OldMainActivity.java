//package com.byku.android.textdrafter.activities.mainactivity;
//
//import android.databinding.DataBindingUtil;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//
//import com.byku.android.textdrafter.R;
//import com.byku.android.textdrafter.activities.mainactivity.views.MainRecycler;
//import com.byku.android.textdrafter.databinding.ActivityMainBinding;
//import com.byku.android.textdrafter.utils.dialogs.DialogHandlers;
//import com.byku.android.textdrafter.utils.dialogs.DialogListeners;
//
//public class MainActivity extends AppCompatActivity {
//
//    private ActivityMainBinding binding;
//    private MainModel mainModel;
//    private MainHandler mainHandler;
//    private MainListeners mainListeners;
//    private MainRecycler mainRecycler;
//    private DialogListeners dialogListeners;
//    private DialogHandlers dialogHandlers;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        initBinding();
//    }
//
//    private void initBinding() {
//        binding = DataBindingUtil.setContentView(this, R.layout.fragment_main);
//        mainModel();
//        mainHandler();
//        dialogListeners();
//    }
//
//    private void mainModel() {
//        mainModel = new MainModel(this);
//        mainListeners = new MainListeners(binding);
//        mainRecycler = new MainRecycler(mainModel, binding.recyclerviewSmsValues);
//        binding.setMainmodel(mainModel);
//        binding.setMainrecycler(mainRecycler);
//        binding.textviewSmsDraft.addTextChangedListener(mainListeners.getTextWatcherSmsText(mainModel, mainRecycler));
//        binding.edittextTelNumber.addTextChangedListener(mainListeners.getTextWatcherTelText(mainModel));
//    }
//
//    private void mainHandler() {
//        mainHandler = new MainHandler();
//        binding.setHandlers(mainHandler);
//    }
//
//    private void dialogListeners() {
//        dialogListeners = new DialogListeners();
//        dialogHandlers = new DialogHandlers();
//        binding.setDlisteners(dialogListeners);
//        binding.setDhandlers(dialogHandlers);
//    }
//}
