package com.byku.android.textdrafter.activities.mainactivity.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.byku.android.textdrafter.activities.TextDrafterApp;
import com.byku.android.textdrafter.activities.mainactivity.activity.MainPresenterInterface;
import com.byku.android.textdrafter.activities.mainactivity.activity.MainViewInterface;
import com.byku.android.textdrafter.activities.mainactivity.adapters.handler.SmsKeysHandlers;
import com.byku.android.textdrafter.activities.mainactivity.adapters.handler.SmsKeysHandlersImpl;
import com.byku.android.textdrafter.database.Models.KeyValueRecipentModel;
import com.byku.android.textdrafter.database.SmsTextDbHelperInterface;
import com.byku.android.textdrafter.databinding.SmskeyRecyclerItemBinding;

import java.util.List;

import javax.inject.Inject;

public class SmsKeysAdapterImpl extends RecyclerView.Adapter<SmsKeysHolder> implements SmsKeysAdapter {

    @Inject
    SmsTextDbHelperInterface smsTextDbHelper;

    private List<KeyValueRecipentModel> smsKeys;
    private SmsKeysHolder activeHolder;
    private MainViewInterface mainView;
    private MainPresenterInterface mainPresenter;
    private String activeKey = "";

    public SmsKeysAdapterImpl(MainViewInterface mainView, MainPresenterInterface mainPresenter, List<KeyValueRecipentModel> smsKeys) {
        this.mainView = mainView;
        this.mainPresenter = mainPresenter;
        this.smsKeys = smsKeys;
        initInjection();
    }

    @Override
    public SmsKeysHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        SmskeyRecyclerItemBinding binding = SmskeyRecyclerItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new SmsKeysHolder(binding.getRoot(), mainView, this);
    }

    @Override
    public void onBindViewHolder(SmsKeysHolder holder, int position) {
        initText(holder, position);
        initListeners(holder, position);
        initView(holder, position);
    }

    @Override
    public int getItemCount() {
        return smsKeys.size();
    }

    @Override
    public void setActiveHolderTo(SmsKeysHolder smsKeysHolder) {
        if (activeHolder != null && activeHolder != smsKeysHolder)
            activeHolder.setBindingInactive();
        activeHolder = smsKeysHolder;
    }

    @Override
    public void setCurrentItemTo(String key) {
        activeKey = key;
    }

    @Override
    public int getKeyPosition(String key) {
        for (int i = 0; i < smsKeys.size(); i++) {
            if (smsKeys.get(i).key.equalsIgnoreCase(key))
                return i;
        }
        return -1;
    }

    @Override
    public void itemRemoved(String key) {
        int keyIndex = getKeyPosition(key);
        smsKeys.remove(keyIndex);
        mainPresenter.removeKey(key);
        notifyItemRemoved(keyIndex);
    }

    private void initInjection() {
        ((TextDrafterApp) mainView.getActivity().getApplication()).getActivityComponent().inject(this);
    }

    private void initText(SmsKeysHolder holder, int position) {
        holder.setText(smsKeys.get(position).key);
    }

    private void initListeners(SmsKeysHolder holder, int position) {
        SmsKeysHandlers smsKeysHandlers = new SmsKeysHandlersImpl(mainView, smsKeys.get(position).key, smsTextDbHelper, this, holder);
        holder.setOnClickListener(smsKeysHandlers.getOnClickListener());
        holder.setOnLongClickListener(smsKeysHandlers.getOnLongClickListener());
    }

    private void initView(SmsKeysHolder holder, int position) {
        if (activeKey.equalsIgnoreCase(smsKeys.get(position).key) || (position == 0 && activeKey.isEmpty())) {
            holder.setBindingActive();
        } else {
            holder.setBindingInactive();
        }
    }
}
