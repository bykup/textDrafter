package com.byku.android.textdrafter.utils.dialogs;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;

import com.byku.android.textdrafter.activities.MainModel;
import com.byku.android.textdrafter.activities.views.MainRecycler;
import com.byku.android.textdrafter.database.SmsTextDbHelper;
import com.byku.android.textdrafter.database.Tables.SmsTextContract;
import com.byku.android.textdrafter.utils.dialogs.interfaces.DialogListenerInterface;
import com.byku.android.textdrafter.utils.parsers.TextParser;

public class DialogListeners {
    private TextWatcher textWatcher;

    public TextWatcher getTextWatcher(final DialogModel model) {
        if (textWatcher == null)
            textWatcher = new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void afterTextChanged(Editable editable) {
                    model.setSmsText(editable.toString());
                }
            };

        return textWatcher;
    }

    public DialogListenerInterface getDialogListener(final MainModel mainModel, final MainRecycler mainRecycler) {
        return new DialogListenerInterface() {
            @Override
            public void onPositiveClick(DialogHelper dialogSmsInput, String smsText) {
                if (smsText != null && smsText.length() > 0 && mainRecycler.getSmsValuesAdapter() != null) {
                    mainModel.setSmsText(smsText);
                    mainRecycler.getList().clear();
                    mainRecycler.getList().addAll(new TextParser().textToKeyValue(smsText));
                    mainRecycler.getSmsValuesAdapter().setList(mainRecycler.getList());
                    new SmsTextDbHelper(mainModel.getActivity())
                            .writeToDatabase(SmsTextContract.TEMP_KEY, smsText, mainModel.getTelText())
                            .close();
                }
            }

            @Override
            public void onNegativeClick(DialogHelper dialogSmsInpup) {

            }
        };
    }
}
