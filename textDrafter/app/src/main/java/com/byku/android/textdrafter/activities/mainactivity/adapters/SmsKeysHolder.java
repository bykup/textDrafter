package com.byku.android.textdrafter.activities.mainactivity.adapters;

import android.databinding.DataBindingUtil;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.byku.android.textdrafter.R;
import com.byku.android.textdrafter.activities.mainactivity.activity.MainView;
import com.byku.android.textdrafter.databinding.SmskeyRecyclerItemBinding;

public class SmsKeysHolder extends RecyclerView.ViewHolder {
    private SmskeyRecyclerItemBinding binding;
    private MainView mainView;

    SmsKeysHolder(View itemView, MainView mainView) {
        super(itemView);
        this.mainView = mainView;
        binding = DataBindingUtil.bind(itemView);
    }

    public void setBindingActive() {
        binding.smskeyLayout.setBackgroundColor(ContextCompat.getColor(mainView.getActivity(), R.color.grey_foreground));
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) {
            binding.smskeyLayout.setElevation(2);
        }
    }

    public void setBindingInactive() {
        binding.smskeyLayout.setBackgroundColor(ContextCompat.getColor(mainView.getActivity(), R.color.grey_middle));
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) {
            binding.smskeyLayout.setElevation(1);
        }
    }

    public SmskeyRecyclerItemBinding getBinding() {
        return binding;
    }
}
