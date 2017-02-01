package com.byku.android.textdrafter.utils.parsers;

import android.text.TextUtils;

import com.byku.android.textdrafter.activities.MainModel;
import com.byku.android.textdrafter.activities.adapters.models.KeyValueModel;
import com.byku.android.textdrafter.activities.adapters.models.RecyclerSmsModel;

import java.util.List;

public class SmsParser {

    private List<RecyclerSmsModel> recyclerSmsModels;
    private List<KeyValueModel> outputModel;
    private String smsText;

    public SmsParser(MainModel mainModel) {
        if (mainModel.getSmsValuesAdapter() != null) {
            recyclerSmsModels = mainModel.getSmsValuesAdapter().getModels();
            outputModel = mainModel.getSmsValuesAdapter().getOutputs();
        }
        smsText = mainModel.getSmsText();
    }

    public String parseToSms() throws NullPointerException {
        if (recyclerSmsModels == null || TextUtils.isEmpty(smsText))
            throw new NullPointerException("Mandarory value null or empty");

        for (RecyclerSmsModel recyclerSmsModel : recyclerSmsModels) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("\\[").append(recyclerSmsModel.getKey()).append("\\]");
            smsText = smsText.replaceAll(stringBuilder.toString(), recyclerSmsModel.getValue());
        }
        return prepTextWithCalculations();
    }

    private String prepTextWithCalculations() {
        String finalSms = smsText;
        for (KeyValueModel model : outputModel) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("[").append(model.getKey()).append("]");
            finalSms = finalSms.replace(stringBuilder.toString(),prepSingleMathOperation(model.getKey()));
        }
        return finalSms;
    }

    private String prepSingleMathOperation(String mathOp) {
        String text = mathOp;
        for (RecyclerSmsModel model : recyclerSmsModels) {
            boolean noNumberBefore = false;
            boolean noNumberAfter = false;
            int index = text.indexOf(model.getKey());
            while (index > -1) {
                if ((index - 1 < 0) || !Character.isDigit(text.charAt(index - 1)))
                    noNumberBefore = true;
                if ((index + model.getKey().length()) < mathOp.length()) {
                    if (!Character.isDigit(text.charAt(index + model.getKey().length()))) {
                        noNumberAfter = true;
                    }
                } else {
                    noNumberAfter = true;
                }
                if (noNumberAfter && noNumberBefore) {
                    text = new StringBuilder().append(text.substring(0,index)).append(model.getValue()).append(text.substring(index+model.getKey().length())).toString();
                }
                index = text.indexOf(model.getKey(), index+1);
            }
        }
        String value = String.valueOf(MathParser.eval(text));
        return value;
    }
}
