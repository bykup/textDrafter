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

    public String parseToSms() {
        if (recyclerSmsModels == null || TextUtils.isEmpty(smsText))
            return "";

        for (RecyclerSmsModel recyclerSmsModel : recyclerSmsModels) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("\\[").append(recyclerSmsModel.getKey()).append("\\]");
            smsText = smsText.replaceAll(stringBuilder.toString(), TextUtils.isEmpty(recyclerSmsModel.getValue()) ? "" : recyclerSmsModel.getValue());
        }

        return prepTextWithCalculations(smsText);
    }

    private String prepTextWithCalculations(String smsText) {
        String finalSms = smsText;
        for (KeyValueModel model : outputModel) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("[").append(model.getKey()).append("]");
            try {
                finalSms = finalSms.replace(stringBuilder.toString(), prepSingleMathOperation(model.getKey()));
            } catch (RuntimeException ex) {
                ex.printStackTrace();
            }
        }
        return finalSms;
    }

    private String prepSingleMathOperation(String mathOp) {
        String text = mathOp;
        for (RecyclerSmsModel model : recyclerSmsModels) {
            int index = text.indexOf(model.getKey());
            while (index > -1) {
                boolean noNumberBefore = false;
                boolean noNumberAfter = false;
                boolean shouldExit = false;
                if ((index - 1 < 0) || !Character.isDigit(text.charAt(index - 1)))
                    noNumberBefore = true;
                if ((index + model.getKey().length()) < text.length()) {
                    if (!Character.isDigit(text.charAt(index + model.getKey().length()))) {
                        noNumberAfter = true;
                    }
                } else {
                    noNumberAfter = true;
                    shouldExit = true;
                }
                if (noNumberAfter && noNumberBefore) {
                    text = new StringBuilder().append(text.substring(0, index)).append(model.getValue()).append(text.substring(index + model.getKey().length())).toString();
                }
                if(shouldExit)
                    break;
                else if (noNumberAfter && noNumberBefore)
                    index = text.indexOf(model.getKey(), model.getValue().length());
                else
                    index = text.indexOf(model.getKey(), index + 1);
            }
        }
        String value = String.valueOf(MathParser.eval(text));
        return value;
    }
}
