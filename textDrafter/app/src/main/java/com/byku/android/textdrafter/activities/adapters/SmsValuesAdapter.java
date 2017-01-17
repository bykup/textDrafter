package com.byku.android.textdrafter.activities.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.byku.android.textdrafter.activities.adapters.models.KeyValueModel;
import com.byku.android.textdrafter.activities.adapters.models.SmsValuesModel;
import com.byku.android.textdrafter.databinding.RecyclerItemBinding;
import com.byku.android.textdrafter.utils.parsers.TextParser;

import java.util.ArrayList;
import java.util.List;

public class SmsValuesAdapter extends RecyclerView.Adapter<SmsValuesAdapter.SmsValuesHolder> {

    private List<KeyValueModel> inputs;
    private List<KeyValueModel> outputs;

    Context context;

    public SmsValuesAdapter(List<KeyValueModel> list, Context context) {
        this.context = context;
        inputs = new ArrayList<KeyValueModel>();
        outputs = new ArrayList<KeyValueModel>();
        if (list != null)
            setInputOutputLists(list);
    }

    public SmsValuesAdapter(String smsText, Context context) {
        this(TextParser.textToKeyValue(smsText), context);
    }

    @Override
    public SmsValuesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerItemBinding binding = RecyclerItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new SmsValuesHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(SmsValuesHolder holder, int position) {
        KeyValueModel keyValueModel = inputs.get(position);
        SmsValuesModel model = new SmsValuesModel(keyValueModel.getKey());
        holder.getBinding().setSmsvaluesmodel(model);
    }

    @Override
    public int getItemCount() {
        return inputs.size();
    }

    public void setList(List<KeyValueModel> list){
        inputs.clear();
        outputs.clear();
        setInputOutputLists(list);
        notifyDataSetChanged();
    }

    private void setInputOutputLists(List<KeyValueModel> list) {
        for (KeyValueModel keyValueModel : list) {
            if (keyValueModel.getType() == KeyValueModel.INPUT)
                inputs.add(keyValueModel);
            else if (keyValueModel.getType() == KeyValueModel.OUTPUT)
                outputs.add(keyValueModel);
        }
    }

    class SmsValuesHolder extends RecyclerView.ViewHolder {

        private RecyclerItemBinding binding;

        SmsValuesHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }

        public RecyclerItemBinding getBinding() {
            return binding;
        }
    }
}
