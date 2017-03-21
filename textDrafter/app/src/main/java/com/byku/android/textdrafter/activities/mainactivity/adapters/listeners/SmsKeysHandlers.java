package com.byku.android.textdrafter.activities.mainactivity.adapters.listeners;

import android.view.View;

import com.byku.android.textdrafter.activities.mainactivity.MainView;

public interface SmsKeysHandlers {

    View.OnClickListener getOnClickListener();

    SmsKeysHandlers setCurrentPosition(int position);
}
