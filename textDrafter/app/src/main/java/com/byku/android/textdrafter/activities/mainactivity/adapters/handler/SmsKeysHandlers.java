package com.byku.android.textdrafter.activities.mainactivity.adapters.handler;

import android.view.View;

public interface SmsKeysHandlers {

    SmsKeysHandlers setCurrentPosition(int position);

    View.OnClickListener getOnClickListener();

    View.OnLongClickListener getOnLongClickListener(String smsKey);
}
