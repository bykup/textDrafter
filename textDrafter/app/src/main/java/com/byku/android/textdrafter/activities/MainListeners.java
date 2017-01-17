package com.byku.android.textdrafter.activities;

import com.byku.android.textdrafter.utils.parsers.TextParser;
import com.byku.android.textdrafter.utils.dialogs.DialogListener;
import com.byku.android.textdrafter.utils.dialogs.DialogSmsInput;

public class MainListeners {

    public DialogListener getListener(final MainModel mainModel) {
        return new DialogListener() {
            @Override
            public void onPositiveClick(DialogSmsInput dialogSmsInput, String smsText) {
                if (smsText != null && smsText.length() > 0 && mainModel.getSmsValuesAdapter() != null) {
                    mainModel.setSmsText(smsText);
                    mainModel.getList().clear();
                    mainModel.getList().addAll(TextParser.textToKeyValue(smsText));
                    mainModel.getSmsValuesAdapter().setList(mainModel.getList());
                }
            }

            @Override
            public void onNegativeClick(DialogSmsInput dialogSmsInpup) {

            }
        };
    }
}