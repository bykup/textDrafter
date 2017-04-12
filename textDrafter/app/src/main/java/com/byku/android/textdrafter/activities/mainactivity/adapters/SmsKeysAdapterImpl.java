package com.byku.android.textdrafter.activities.mainactivity.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.util.SparseArray;

import java.util.List;

import com.byku.android.textdrafter.R;
import com.byku.android.textdrafter.activities.mainactivity.MainView;
import com.byku.android.textdrafter.activities.mainactivity.adapters.listeners.SmsKeysHandlersImpl;
import com.byku.android.textdrafter.database.SmsTextDbHelperImpl;
import com.byku.android.textdrafter.databinding.SmskeyRecyclerItemBinding;
import com.byku.android.textdrafter.utils.views.observers.ViewDimensions;
import com.byku.android.textdrafter.utils.views.observers.ViewMetricsSourceObserver;
import com.byku.android.textdrafter.utils.views.observers.ViewMetricsSourceSubject;

public class SmsKeysAdapterImpl extends RecyclerView.Adapter<SmsKeysAdapterImpl.SmsKeysHolder> implements SmsKeysAdapter, ViewMetricsSourceObserver {

    private Context context;
    private List<String> smsKeys;
    private MainView mainView;
    private SmsTextDbHelperImpl smsTextDbHelper;
    private SparseArray<SmskeyRecyclerItemBinding> bindings;

    public SmsKeysAdapterImpl(SmsTextDbHelperImpl smsTextDbHelper, Context context, MainView mainView) {
        this.context = context;
        this.smsKeys = smsTextDbHelper.readAllKeysFromDb();
        this.mainView = mainView;
        this.smsTextDbHelper = smsTextDbHelper;
        this.bindings = new SparseArray<>();
        ViewMetricsSourceSubject.getInstance().attach(this);
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
        for (int i = 0; i < bindings.size(); i++) {
            ViewGroup.LayoutParams layoutParams = bindings.get(i).smskeyLayout.getLayoutParams();
            layoutParams.width = viewDimensions.width/(smsKeys.size() > 3 ? 3 : smsKeys.size());
            layoutParams.height = viewDimensions.height;
            bindings.get(i).smskeyLayout.setLayoutParams(layoutParams);
        }
        ViewMetricsSourceSubject.getInstance().detach(this);
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

        for(int i = 0; i < bindings.size() ; i++){
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
        ViewMetricsSourceSubject sourceSubject = ViewMetricsSourceSubject.getInstance();
        if(sourceSubject.areDimensionsReady()) {
            finalInitDimensions(sourceSubject,holder);
        }
    }

    private void finalInitDimensions(ViewMetricsSourceSubject sourceSubject, SmsKeysHolder holder){
        ViewGroup.LayoutParams layoutParams = holder.getBinding().smskeyLayout.getLayoutParams();
        layoutParams.width = sourceSubject.getViewDimensions().width/(smsKeys.size() > 3 ? 3 : smsKeys.size());
        layoutParams.height = sourceSubject.getViewDimensions().height;
        holder.getBinding().smskeyLayout.setLayoutParams(layoutParams);
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

    private void colorCurrentItem(int position){

    }
}
