package com.byku.android.textdrafter.utils.parsers;

import android.text.TextUtils;

import com.byku.android.textdrafter.activities.mainactivity.MainFragmentModel;
import com.byku.android.textdrafter.activities.mainactivity.adapters.models.KeyValueModel;
import com.byku.android.textdrafter.activities.mainactivity.adapters.models.RecyclerSmsModel;
import com.byku.android.textdrafter.activities.mainactivity.views.MainRecycler;

import java.util.List;

public class SmsParser {

    private List<RecyclerSmsModel> recyclerSmsModels;
    private List<KeyValueModel> outputModel;
    private String smsText;

    public SmsParser(MainFragmentModel mainFragmentModel, MainRecycler mainRecycler) {
        if (mainRecycler.getSmsValuesAdapter() != null) {
            recyclerSmsModels = mainRecycler.getSmsValuesAdapter().getModels();
            outputModel = mainRecycler.getSmsValuesAdapter().getOutputs();
        }
        smsText = mainFragmentModel.getSmsText();
    }

    public String parseToSms() {
        if (recyclerSmsModels == null || TextUtils.isEmpty(smsText))
            return "";
        for (RecyclerSmsModel recyclerSmsModel : recyclerSmsModels) {
            smsText = replaceAllSlashes(recyclerSmsModel);
        }
        return getFullTextWithCalculations(smsText);
    }

    private String replaceAllSlashes(RecyclerSmsModel recyclerSmsModel){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\\[")
                .append(recyclerSmsModel.getKey())
                .append("\\]");
        return smsText.replaceAll(stringBuilder.toString(),
                TextUtils.isEmpty(recyclerSmsModel.getValue())
                        ? "" : recyclerSmsModel.getValue());
    }

    private String getFullTextWithCalculations(String smsText) {
        String finalSms = smsText;
        for (KeyValueModel model : outputModel) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("[").append(model.getKey()).append("]");
            try {
                finalSms = finalSms.replace(stringBuilder.toString(),
                        getCalculatedText(getTextForMathOperation(model.getKey())));
            } catch (RuntimeException ex) {
                ex.printStackTrace();
            }
        }
        return finalSms;
    }

    private String getTextForMathOperation(String mathOp) {
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
                    text = new StringBuilder()
                            .append(text.substring(0, index)).append(model.getValue())
                            .append(text.substring(index + model.getKey().length()))
                            .toString();
                }
                if(shouldExit)
                    break;
                else if (noNumberAfter && noNumberBefore)
                    index = text.indexOf(model.getKey(), model.getValue().length());
                else
                    index = text.indexOf(model.getKey(), index + 1);
            }
        }
        return text;
    }

    private String getCalculatedText(String textToCalc){
        return String.valueOf(String.valueOf(MathParser.eval(textToCalc)));
    }
}
