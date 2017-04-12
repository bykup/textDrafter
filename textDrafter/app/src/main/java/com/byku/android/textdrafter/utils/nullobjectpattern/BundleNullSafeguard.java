package com.byku.android.textdrafter.utils.nullobjectpattern;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;

import com.byku.android.textdrafter.R;

public class BundleNullSafeguard {
    private Bundle bundle;
    private Activity activity;

    public BundleNullSafeguard(Bundle bundle, Activity activity) {
        this.bundle = bundle;
        this.activity = activity;
    }

    public String getString(String key){
        if(bundle == null)
            return activity.getString(R.string.common_first_draft);
        String string = bundle.getString(key);
        if(TextUtils.isEmpty(string))
            return activity.getString(R.string.common_first_draft);
        else
            return string;
    }
}
