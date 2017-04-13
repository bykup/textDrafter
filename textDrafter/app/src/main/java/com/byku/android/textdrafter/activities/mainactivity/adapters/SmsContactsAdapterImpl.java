package com.byku.android.textdrafter.activities.mainactivity.adapters;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.byku.android.textdrafter.databinding.SmsvalueRecyclerItemBinding;

import java.util.List;

public class SmsContactsAdapterImpl extends RecyclerView.Adapter<SmsValuesAdapter.SmsValuesHolder> {

    public SmsContactsAdapterImpl() {
    }

    @Override
    public void onBindViewHolder(SmsValuesAdapter.SmsValuesHolder holder, int position) {

    }

    @Override
    public SmsValuesAdapter.SmsValuesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public int getItemCount() {
        return 0;
    }

//    TODO change to proper binding - create proper view
    class SmsValuesHolder extends RecyclerView.ViewHolder {

        private SmsvalueRecyclerItemBinding binding;

        SmsValuesHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }

        public SmsvalueRecyclerItemBinding getBinding() {
            return binding;
        }
    }
}
