package com.byku.android.textdrafter.activities.mainactivity;

import com.byku.android.textdrafter.activities.mainactivity.adapters.models.ContactModel;

import java.util.List;

public interface FragmentView {

    void onContatsListReady(List<ContactModel> models);

}
