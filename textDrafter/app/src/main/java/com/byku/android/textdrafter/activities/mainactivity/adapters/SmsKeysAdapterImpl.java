package com.byku.android.textdrafter.activities.mainactivity.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.byku.android.textdrafter.R;
import com.byku.android.textdrafter.activities.TextDrafterApp;
import com.byku.android.textdrafter.activities.mainactivity.activity.MainView;
import com.byku.android.textdrafter.activities.mainactivity.adapters.handler.SmsKeysHandlersImpl;
import com.byku.android.textdrafter.database.SmsTextDbHelper;
import com.byku.android.textdrafter.databinding.SmskeyRecyclerItemBinding;
import com.byku.android.textdrafter.utils.views.observers.ViewDimensions;
import com.byku.android.textdrafter.utils.views.observers.ViewMetricsSourceObserver;
import com.byku.android.textdrafter.utils.views.observers.ViewMetricsSourceSubjectSingleton;

import java.util.List;

import javax.inject.Inject;

public class SmsKeysAdapterImpl extends RecyclerView.Adapter<SmsKeysAdapterImpl.SmsKeysHolder> implements SmsKeysAdapter, ViewMetricsSourceObserver {

    @Inject ViewMetricsSourceSubjectSingleton viewMetricsSourceSubjectSingleton;
    @Inject SmsTextDbHelper smsTextDbHelper;

    private List<String> smsKeys;
    private MainView mainView;
    private SparseArray<SmskeyRecyclerItemBinding> bindings;

    public SmsKeysAdapterImpl(MainView mainView) {
        this.mainView = mainView;
        initInjection();
        this.bindings = new SparseArray<>();
        this.smsKeys = smsTextDbHelper.readAllKeysFromDb();
    }

    @Override
    public SmsKeysHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        SmskeyRecyclerItemBinding binding = SmskeyRecyclerItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new SmsKeysHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(SmsKeysHolder holder, int position) {
        initText(holder, position);
        initListeners(holder, position);
        initDimensions(holder);
        bindings.put(position, holder.getBinding());
    }

    @Override
    public int getItemCount() {
        return smsKeys.size();
    }

    @Override
    public void update(ViewDimensions viewDimensions) {
        int bindingSize = bindings.size();
        for (int i = 0; i < bindingSize; i++) {
            ViewGroup.LayoutParams layoutParams = bindings.get(i).smskeyLayout.getLayoutParams();
            layoutParams.width = viewDimensions.width/(smsKeys.size() > 3 ? 3 : smsKeys.size());
            layoutParams.height = viewDimensions.height;
            bindings.get(i).smskeyLayout.setLayoutParams(layoutParams);
        }
        viewMetricsSourceSubjectSingleton.detach(this);
    }

    @Override
    public void dataSetChanged() {
        smsKeys = smsTextDbHelper.readAllKeysFromDb();
        this.notifyDataSetChanged();
        mainView.refreshViews();
    }

    @Override
    public void setCurrentItemTo(int position){
        if(position >= bindings.size())
            return;

        int bindingSize = bindings.size();
        for(int i = 0; i < bindingSize; i++){
            if(i != position)
                bindings.get(i).smskeyLayout.setBackground(ContextCompat.getDrawable(mainView.getActivity(),R.drawable.rectangle_for_buttons));
        }
        this.bindings.get(position).smskeyLayout.setBackground(ContextCompat.getDrawable(mainView.getActivity(),R.drawable.rectangle_for_buttons_current));
    }

    private void initText(SmsKeysHolder holder, int position) {
        holder.getBinding().smskey.setText(smsKeys.get(position));
    }

    private void initListeners(SmsKeysHolder holder, int position) {
        holder.getBinding().smskeyLayout.setOnClickListener(new SmsKeysHandlersImpl(mainView, position, smsTextDbHelper, this).getOnClickListener());
        holder.getBinding().smskeyLayout.setOnLongClickListener(new SmsKeysHandlersImpl(mainView, position, smsTextDbHelper, this).getOnLongClickListener(smsKeys.get(position)));
    }

    private void initDimensions(SmsKeysHolder holder) {
        if(viewMetricsSourceSubjectSingleton.areDimensionsReady()) {
            finalInitDimensions(viewMetricsSourceSubjectSingleton,holder);
        }
    }

    private void finalInitDimensions(ViewMetricsSourceSubjectSingleton sourceSubject, SmsKeysHolder holder){
        ViewGroup.LayoutParams layoutParams = holder.getBinding().smskeyLayout.getLayoutParams();
        layoutParams.width = sourceSubject.getViewDimensions().width/(smsKeys.size() > 3 ? 3 : smsKeys.size());
        layoutParams.height = sourceSubject.getViewDimensions().height;
        holder.getBinding().smskeyLayout.setLayoutParams(layoutParams);
    }

    private void initInjection(){
        ((TextDrafterApp) mainView.getActivity().getApplication()).getSingletonComponent().inject(this);
        viewMetricsSourceSubjectSingleton.attach(this);
    }

    class SmsKeysHolder extends RecyclerView.ViewHolder {
        private SmskeyRecyclerItemBinding binding;

        SmsKeysHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }

        public SmskeyRecyclerItemBinding getBinding() {
            return binding;
        }
    }
}
