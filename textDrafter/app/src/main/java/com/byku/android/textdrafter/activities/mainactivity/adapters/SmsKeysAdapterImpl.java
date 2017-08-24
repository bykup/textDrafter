package com.byku.android.textdrafter.activities.mainactivity.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.byku.android.textdrafter.activities.TextDrafterApp;
import com.byku.android.textdrafter.activities.mainactivity.activity.MainView;
import com.byku.android.textdrafter.activities.mainactivity.adapters.handler.SmsKeysHandlers;
import com.byku.android.textdrafter.activities.mainactivity.adapters.handler.SmsKeysHandlersImpl;
import com.byku.android.textdrafter.database.SmsTextDbHelperInterface;
import com.byku.android.textdrafter.databinding.SmskeyRecyclerItemBinding;

import java.util.List;

import javax.inject.Inject;

public class SmsKeysAdapterImpl extends RecyclerView.Adapter<SmsKeysHolder> implements SmsKeysAdapter {

    @Inject
    SmsTextDbHelperInterface smsTextDbHelper;

    private List<String> smsKeys;
    private MainView mainView;
    private String activeKey = "";

    public SmsKeysAdapterImpl(MainView mainView) {
        this.mainView = mainView;
        initInjection();
    }

    @Override
    public SmsKeysHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        SmskeyRecyclerItemBinding binding = SmskeyRecyclerItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new SmsKeysHolder(binding.getRoot(), mainView);
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
    public void setList(List<String> smsKeysList) {
        smsKeys = smsKeysList;
        notifyDataSetChanged();
    }

    @Override
    public void setCurrentItemTo(String key) {
        int keyIndex = getKeyPosition(key);
    }

    @Override
    public int getKeyPosition(String key) {
        for (int i = 0; i < smsKeys.size(); i++) {
            if (smsKeys.get(i).equalsIgnoreCase(key))
                return i;
        }
        return -1;
    }

    @Override
    public void itemRemoved(String key) {
        int keyIndex = getKeyPosition(key);
        smsKeys.remove(keyIndex);
        notifyItemRemoved(keyIndex);
    }

    private void initInjection() {
        ((TextDrafterApp) mainView.getActivity().getApplication()).getActivityComponent().inject(this);
    }

    private void initText(SmsKeysHolder holder, int position) {
        holder.getBinding().smskey.setText(smsKeys.get(position));
    }

    private void initListeners(SmsKeysHolder holder, int position) {
        SmsKeysHandlers smsKeysHandlers = new SmsKeysHandlersImpl(mainView, smsKeys.get(position), smsTextDbHelper, this, holder);
        holder.getBinding().smskeyLayout.setOnClickListener(smsKeysHandlers.getOnClickListener());
        holder.getBinding().smskeyLayout.setOnLongClickListener(smsKeysHandlers.getOnLongClickListener());
    }

    private void initView(SmsKeysHolder holder, int position) {
        if (activeKey.equalsIgnoreCase(smsKeys.get(position))) {
            holder.setBindingActive();
        } else {
            holder.setBindingInactive();
        }
    }
}
