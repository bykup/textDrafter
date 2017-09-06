package com.byku.android.textdrafter.activities.mainactivity.adapters;

import com.byku.android.textdrafter.database.Models.KeyValueRecipentModel;

import java.util.List;

public interface SmsKeysAdapter {

    void setActiveHolderTo(SmsKeysHolder smsKeysHolder, String key);

    void setCurrentItemTo(String key);

    void setCurrentItemTo(int position);

    int getKeyPosition(String key);

    void itemRemoved(String key);

}
