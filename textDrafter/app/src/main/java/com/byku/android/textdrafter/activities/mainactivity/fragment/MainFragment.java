package com.byku.android.textdrafter.activities.mainactivity.fragment;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.byku.android.textdrafter.R;
import com.byku.android.textdrafter.activities.mainactivity.activity.MainPresenterInterface;
import com.byku.android.textdrafter.activities.mainactivity.adapters.SmsContactsAdapterImpl;
import com.byku.android.textdrafter.activities.mainactivity.adapters.models.ContactModel;
import com.byku.android.textdrafter.activities.mainactivity.views.MainRecycler;
import com.byku.android.textdrafter.database.Models.KeyValueRecipentModel;
import com.byku.android.textdrafter.database.SmsTextDbHelper;
import com.byku.android.textdrafter.databinding.FragmentMainBinding;
import com.byku.android.textdrafter.utils.dialogs.DialogHandlers;
import com.byku.android.textdrafter.utils.dialogs.DialogListeners;
import com.byku.android.textdrafter.utils.parsers.ContactTelParserImpl;

import java.util.List;

public class MainFragment extends Fragment implements FragmentView {

    private KeyValueRecipentModel model;
    private FragmentMainBinding binding;

    private MainFragmentModel mainFragmentModel;
    private MainFragmentHandler mainFragmentHandler;
    private MainFragmentListeners mainFragmentListeners;
    private MainRecycler mainRecycler;


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
        initViews();
        return binding.getRoot();
    }

    @Override
    public void onPause() {
        new SmsTextDbHelper(getActivity())
                .writeToDatabase(mainFragmentModel.getSmsKey(), mainFragmentModel.getSmsText(), mainFragmentModel.getTelText())
                .close();
        super.onPause();
    }

    @Override
    public void onContatsListReady(List<ContactModel> models) {
        SmsContactsAdapterImpl smsContactsAdapter = new SmsContactsAdapterImpl(models, this);
        binding.recyclerContactsList.setLayoutManager(getReverseLinearLayout());
        binding.recyclerContactsList.setAdapter(smsContactsAdapter);
    }

    @Override
    public void setCurrentContact(ContactModel model) {
        binding.edittextTelNumber.setText(new ContactTelParserImpl(binding.edittextTelNumber).append(model.contactNumber).getOperationResult());
    }

    @Override
    public void setViewVisibility(int tag, int visibility) {
        binding.recyclerContactsList.setVisibility(visibility);
    }

    @Override
    public void setCurrentModel(KeyValueRecipentModel model) {
        this.model = model;
    }

    private void initViews() {
        initMainModel();
        initMainModelVariables();
        mainHandler();
    }

    private void initMainModel() {
        mainFragmentModel = new MainFragmentModel(
                getActivity(), model);
        mainFragmentListeners = new MainFragmentListeners();
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
        binding.edittextTelNumber.setOnFocusChangeListener(mainFragmentListeners.getFocusChangeListener(this));
    }

    private void initMainModelVariables() {
        mainFragmentModel.setSmsKey(model.key);
        mainFragmentModel.setSmsText(model.value);
        mainFragmentModel.setTelText(model.recipent);
    }

    private void mainHandler() {
        mainFragmentHandler = new MainFragmentHandler();
        binding.setHandlers(mainFragmentHandler);
    }

    private LinearLayoutManager getReverseLinearLayout() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setReverseLayout(true);
        return linearLayoutManager;
    }
}
