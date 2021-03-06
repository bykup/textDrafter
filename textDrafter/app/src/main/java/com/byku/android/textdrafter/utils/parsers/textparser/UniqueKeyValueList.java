package com.byku.android.textdrafter.utils.parsers.textparser;


import com.byku.android.textdrafter.activities.mainactivity.adapters.models.KeyValueModel;

import java.util.ArrayList;
import java.util.List;

public class UniqueKeyValueList {

    List<KeyValueModel> list;

    public UniqueKeyValueList() {
        list = new ArrayList<>();
    }

    public void add(KeyValueModel keyValueModel) {
        for (KeyValueModel thisKeyValueModel : list) {
            if (thisKeyValueModel.getKey().equalsIgnoreCase(keyValueModel.getKey())) {
                return;
            }
        }
        list.add(keyValueModel);
    }

    public List<KeyValueModel> returnList() {
        return list;
    }
}
