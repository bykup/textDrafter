package com.byku.android.textdrafter.activities.mainactivity.adapters;

import android.databinding.DataBindingUtil;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.byku.android.textdrafter.R;
import com.byku.android.textdrafter.activities.mainactivity.activity.MainViewInterface;
import com.byku.android.textdrafter.databinding.SmskeyRecyclerItemBinding;

public class SmsKeysHolder extends RecyclerView.ViewHolder {
    private SmskeyRecyclerItemBinding binding;
    private MainViewInterface mainView;
    private SmsKeysAdapter adapter;
    private String key;

    SmsKeysHolder(View itemView, MainViewInterface mainView, SmsKeysAdapter smsKeysAdapter) {
        super(itemView);
        this.mainView = mainView;
        this.adapter = smsKeysAdapter;
        binding = DataBindingUtil.bind(itemView);
    }

    public void setBindingActive() {
        binding.smskeyLayout.setBackgroundColor(ContextCompat.getColor(mainView.getActivity(), R.color.grey_foreground));
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) {
            binding.smskeyLayout.setElevation(2);
        }

        adapter.setActiveHolderTo(this);
        adapter.setCurrentItemTo(key);
        mainView.setPagerPage(key);
    }

    public void setBindingInactive() {
        binding.smskeyLayout.setBackgroundColor(ContextCompat.getColor(mainView.getActivity(), R.color.grey_middle));
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) {
            binding.smskeyLayout.setElevation(1);
        }
    }

    public void setText(String key){
        this.key = key;
        binding.smskey.setText(key);

    }

    public void setOnClickListener(View.OnClickListener onClickListener){
        binding.smskeyLayout.setOnClickListener(onClickListener);
        binding.smskey.setOnClickListener(onClickListener);
    }

    public void setOnLongClickListener(View.OnLongClickListener onLongClickListener){
        binding.smskeyLayout.setOnLongClickListener(onLongClickListener);
        binding.smskey.setOnLongClickListener(onLongClickListener);
    }
}
