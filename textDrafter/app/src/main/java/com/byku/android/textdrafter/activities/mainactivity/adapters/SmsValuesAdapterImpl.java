package com.byku.android.textdrafter.activities.mainactivity.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.byku.android.textdrafter.activities.mainactivity.adapters.listeners.SmsValuesListeners;
import com.byku.android.textdrafter.activities.mainactivity.adapters.models.KeyValueModel;
import com.byku.android.textdrafter.activities.mainactivity.adapters.models.RecyclerSmsModel;
import com.byku.android.textdrafter.databinding.SmsvalueRecyclerItemBinding;
import com.byku.android.textdrafter.utils.parsers.TextParser;

import java.util.ArrayList;
import java.util.List;

public class SmsValuesAdapterImpl extends RecyclerView.Adapter<SmsValuesAdapterImpl.SmsValuesHolder> {

    private List<KeyValueModel> inputs;
    private List<KeyValueModel> outputs;
    private List<RecyclerSmsModel> models;

    Context context;

    public SmsValuesAdapterImpl(List<KeyValueModel> list, Context context) {
        this.context = context;
        inputs = new ArrayList<KeyValueModel>();
        outputs = new ArrayList<KeyValueModel>();
        models = new ArrayList<RecyclerSmsModel>();
        if (list != null)
            setInputOutputLists(
                    list);
    }

    public SmsValuesAdapterImpl(String smsText, Context context) {
        this(new TextParser().textToKeyValue(smsText),
                context);
    }

    @Override
    public SmsValuesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        SmsvalueRecyclerItemBinding binding = SmsvalueRecyclerItemBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false);
        return new SmsValuesHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(SmsValuesHolder holder, int position) {
        KeyValueModel keyValueModel = inputs.get(position);
        RecyclerSmsModel model = new RecyclerSmsModel(keyValueModel.getKey());
        models.add(model);
        SmsValuesListeners listeners = new SmsValuesListeners();
        holder.getBinding().setSmsvaluesmodel(model);
        holder.getBinding().edittextVariableValue.addTextChangedListener(listeners.getTextWatcher(model));
    }

    @Override
    public int getItemCount() {
        if (inputs != null)
            return inputs.size();
        else return 0;
    }

    public void notifyDataModelChanged(){
        models.clear();
        notifyDataSetChanged();
    }

    public void setList(List<KeyValueModel> list) {
        inputs.clear();
        outputs.clear();
        setInputOutputLists(list);
        notifyDataModelChanged();
    }

    private void setInputOutputLists(List<KeyValueModel> list) {
        for (KeyValueModel keyValueModel : list) {
            if (keyValueModel.getType() == KeyValueModel.INPUT)
                inputs.add(keyValueModel);
            else if (keyValueModel.getType() == KeyValueModel.OUTPUT)
                outputs.add(keyValueModel);
        }
    }

    public List<RecyclerSmsModel> getModels() {
        return models;
    }

    public List<KeyValueModel> getInputs() {
        return inputs;
    }

    public List<KeyValueModel> getOutputs() {
        return outputs;
    }

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
