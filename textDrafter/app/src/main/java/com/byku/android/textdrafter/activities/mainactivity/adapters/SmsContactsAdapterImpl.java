package com.byku.android.textdrafter.activities.mainactivity.adapters;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.byku.android.textdrafter.activities.mainactivity.adapters.handler.ContactHandlers;
import com.byku.android.textdrafter.activities.mainactivity.adapters.models.ContactModel;
import com.byku.android.textdrafter.activities.mainactivity.fragment.FragmentView;
import com.byku.android.textdrafter.databinding.ContactlistRecyclerItemBinding;

import java.util.List;

public class SmsContactsAdapterImpl extends RecyclerView.Adapter<SmsContactsAdapterImpl.ContactsHolder> implements SmsContactsAdapter{

    private List<ContactModel> models;
    private FragmentView fragmentView;

    public SmsContactsAdapterImpl(List<ContactModel> models, FragmentView fragmentView) {
        this.models = models;
        this.fragmentView = fragmentView;
    }

    @Override
    public void onBindViewHolder(SmsContactsAdapterImpl.ContactsHolder holder, int position) {
        holder.getBinding().contactName.setText(models.get(position).contactName);
        holder.getBinding().contactName.setText(models.get(position).contactName);
        holder.getBinding().contactLayout.setOnClickListener(
                ContactHandlers.getOnContactClick(
                        models.get(position),
                        fragmentView));
    }

    @Override
    public SmsContactsAdapterImpl.ContactsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    @Override
    public void setContactModelsList(List<ContactModel> models){
        this.models = models;
        notifyDataSetChanged();
    }

    class ContactsHolder extends RecyclerView.ViewHolder {

        private ContactlistRecyclerItemBinding binding;

        ContactsHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }

        public ContactlistRecyclerItemBinding getBinding() {
            return binding;
        }
    }
}
