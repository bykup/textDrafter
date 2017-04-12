package com.byku.android.textdrafter.activities.mainactivity.adapters.listeners;

import android.view.View;

import com.byku.android.textdrafter.activities.mainactivity.MainView;

public interface SmsKeysHandlers {

    SmsKeysHandlers setCurrentPosition(int position);

    View.OnClickListener getOnClickListener();

    View.OnLongClickListener getOnLongClickListener(String smsKey);
}
