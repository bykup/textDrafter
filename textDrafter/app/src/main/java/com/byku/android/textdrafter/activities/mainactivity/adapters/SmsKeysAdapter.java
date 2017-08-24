package com.byku.android.textdrafter.activities.mainactivity.adapters;

import java.util.List;

public interface SmsKeysAdapter {

    void setList(List<String> smsKeysList);

    void setCurrentItemTo(String key);

    int getKeyPosition(String key);

    void itemRemoved(String key);

}
