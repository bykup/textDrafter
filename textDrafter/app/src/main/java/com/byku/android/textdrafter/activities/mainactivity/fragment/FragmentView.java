package com.byku.android.textdrafter.activities.mainactivity.fragment;

import android.app.Activity;

import com.byku.android.textdrafter.activities.mainactivity.adapters.models.ContactModel;

import java.util.List;

public interface FragmentView {

    void onContatsListReady(List<ContactModel> models);

    void setCurrentContact(ContactModel model);

    void setViewVisibility(int tag, int visibility);

    Activity getActivity();

}
