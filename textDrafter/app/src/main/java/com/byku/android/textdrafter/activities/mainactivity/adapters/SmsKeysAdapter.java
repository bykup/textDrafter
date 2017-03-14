package com.byku.android.textdrafter.activities.mainactivity.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.byku.android.textdrafter.databinding.SmskeyRecyclerItemBinding;

import java.util.List;

public class SmsKeysAdapter extends RecyclerView.Adapter<SmsKeysAdapter.SmsKeysHolder> {

    Context context;
    List<String> smsKeys;

    public SmsKeysAdapter(List<String> list, Context context) {
        this.context = context;
        this.smsKeys = list;
    }

    @Override
    public SmsKeysHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        SmskeyRecyclerItemBinding binding = SmskeyRecyclerItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new SmsKeysHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(SmsKeysHolder holder, int position) {
        holder.getBinding().smskey.setText(smsKeys.get(0));
    }

    @Override
    public int getItemCount() {
        return smsKeys.size();
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
