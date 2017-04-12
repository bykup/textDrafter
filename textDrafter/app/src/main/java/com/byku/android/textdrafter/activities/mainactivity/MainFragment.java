package com.byku.android.textdrafter.activities.mainactivity;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.byku.android.textdrafter.R;
import com.byku.android.textdrafter.activities.mainactivity.views.MainRecycler;
import com.byku.android.textdrafter.database.SmsTextDbHelper;
import com.byku.android.textdrafter.database.SmsTextDbHelperImpl;
import com.byku.android.textdrafter.databinding.FragmentMainBinding;
import com.byku.android.textdrafter.utils.dialogs.DialogHandlers;
import com.byku.android.textdrafter.utils.dialogs.DialogListeners;
import com.byku.android.textdrafter.utils.nullobjectpattern.BundleNullSafeguard;

public class MainFragment extends Fragment {

    private FragmentMainBinding binding;
    private MainFragmentModel mainFragmentModel;
    private MainFragmentHandler mainFragmentHandler;
    private MainFragmentListeners mainFragmentListeners;
    private MainRecycler mainRecycler;
    private DialogListeners dialogListeners;
    private DialogHandlers dialogHandlers;
    private SmsTextDbHelper dbHelper;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(
                savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_main,
                container, false);
        initDb();
        initViews();
        return binding.getRoot();
    }

    private void initDb(){
        dbHelper = new SmsTextDbHelperImpl(getActivity());
    }

    private void initViews() {
        initMainModel();
        initMainModelVariables();
        mainHandler();
        dialogListeners();
    }

    private void initMainModel() {
        mainFragmentModel = new MainFragmentModel(
                getActivity());
        mainFragmentListeners = new MainFragmentListeners(
                binding);
        mainRecycler = new MainRecycler(
                mainFragmentModel,
                binding.recyclerviewSmsValues);
        binding.setMainmodel(
                mainFragmentModel);
        binding.setMainrecycler(
                mainRecycler);
        binding.textviewSmsDraft.addTextChangedListener(
                mainFragmentListeners.getTextWatcherSmsText(
                        mainFragmentModel,
                        mainRecycler));
        binding.edittextTelNumber.addTextChangedListener(
                mainFragmentListeners.getTextWatcherTelText(
                        mainFragmentModel));
    }

    private void initMainModelVariables() {
        BundleNullSafeguard bundle = new BundleNullSafeguard(
                getArguments(),
                getActivity());
        mainFragmentModel.setSmsKey(
                bundle.getString(MainActivityConstants.SMS_KEYS));
        mainFragmentModel.setSmsText(dbHelper.readValueFromDatabase(mainFragmentModel.getSmsKey()));
        mainFragmentModel.setTelText(dbHelper.readRecipentFromDatabase(mainFragmentModel.getSmsKey()));
    }

    private void mainHandler() {
        mainFragmentHandler = new MainFragmentHandler();
        binding.setHandlers(mainFragmentHandler);
    }

    private void dialogListeners() {
        dialogListeners = new DialogListeners();
        dialogHandlers = new DialogHandlers();
    }

}
