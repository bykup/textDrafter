package com.byku.android.textdrafter.utils.parsers;

import android.text.TextUtils;

import com.byku.android.textdrafter.activities.MainModel;
import com.byku.android.textdrafter.activities.adapters.models.KeyValueModel;
import com.byku.android.textdrafter.activities.adapters.models.RecyclerSmsModel;

import java.util.List;

public class SmsParser {

    private List<RecyclerSmsModel> recyclerSmsModels;
    private List<KeyValueModel> keysModel;
    private String smsText;

    public SmsParser(MainModel mainModel) {
        if (mainModel.getSmsValuesAdapter() != null)
            recyclerSmsModels = mainModel.getSmsValuesAdapter().getModels();
        keysModel = mainModel.getList();
        smsText = mainModel.getSmsText();
    }

    public String parseToSms() throws NullPointerException {
        if (recyclerSmsModels == null || keysModel == null || TextUtils.isEmpty(smsText))
            throw new NullPointerException("Mandarory value null or empty");

        for (RecyclerSmsModel recyclerSmsModel : recyclerSmsModels) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("\\[").append(recyclerSmsModel.getKey()).append("\\]");
            smsText = smsText.replaceAll(stringBuilder.toString(),recyclerSmsModel.getValue());
        }
        return smsText;
    }
}
