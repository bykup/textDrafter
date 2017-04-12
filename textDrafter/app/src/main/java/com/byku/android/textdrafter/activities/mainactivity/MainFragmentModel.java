package com.byku.android.textdrafter.activities.mainactivity;


import android.app.Activity;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.WindowManager;

import com.android.databinding.library.baseAdapters.BR;
import com.byku.android.textdrafter.database.SmsTextDbHelperImpl;
import com.byku.android.textdrafter.database.Tables.SmsTextContract;

public class MainFragmentModel extends BaseObservable {

    private Activity activity;
    private String smsKey;
    private String smsText;
    private String telText;

    public MainFragmentModel(Activity activity) {
        this.activity = activity;
        setSmsText(new SmsTextDbHelperImpl(activity).readValueFromDatabase(SmsTextContract.TEMP_KEY));
        setTelText(new SmsTextDbHelperImpl(activity).readRecipentFromDatabase(SmsTextContract.TEMP_KEY));
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

    public String getSmsKey() {
        return smsKey;
    }

    public void setSmsKey(String smsKey) {
        this.smsKey = smsKey;
    }

    public Activity getActivity() {
        return activity;
    }

    private void initViewsBehaviour(){
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    }
}
