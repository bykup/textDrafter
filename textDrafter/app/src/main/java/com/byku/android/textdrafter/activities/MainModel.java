package com.byku.android.textdrafter.activities;


import android.app.Activity;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.WindowManager;

import com.android.databinding.library.baseAdapters.BR;
import com.byku.android.textdrafter.activities.adapters.SmsValuesAdapter;
import com.byku.android.textdrafter.activities.adapters.models.KeyValueModel;
import com.byku.android.textdrafter.database.SmsTextDbHelper;
import com.byku.android.textdrafter.database.Tables.SmsTextContract;
import com.byku.android.textdrafter.utils.parsers.TextParser;

import java.util.ArrayList;
import java.util.List;

public class MainModel extends BaseObservable {

    private Activity activity;
    private String smsText;
    private String telText;

    public MainModel(Activity activity) {
        this.activity = activity;
        setSmsText(new SmsTextDbHelper(activity).readValueFromDatabase(SmsTextContract.TEMP_KEY));
        setTelText(new SmsTextDbHelper(activity).readRecipentFromDatabase(SmsTextContract.TEMP_KEY));
        initViewsBehaviour();
    }

    @Bindable
    public String getSmsText() {
        return smsText;
    }

    public void setSmsText(String smsText) {
        this.smsText = smsText;
        notifyPropertyChanged(BR.smsText);
    }

    @Bindable
    public String getTelText() {
        return telText;
    }

    public void setTelText(String telText) {
        this.telText = telText;
        notifyPropertyChanged(BR.telText);
    }

    public Activity getActivity() {
        return activity;
    }

    private void initViewsBehaviour(){
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    }
}
