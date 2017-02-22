package com.byku.android.textdrafter.utils.dialogs;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Toast;

import com.byku.android.textdrafter.R;
import com.byku.android.textdrafter.activities.MainModel;
import com.byku.android.textdrafter.database.SmsTextDbHelper;
import com.byku.android.textdrafter.database.Tables.SmsTextContract;
import com.byku.android.textdrafter.utils.dialogs.interfaces.DialogListenerInterface;
import com.byku.android.textdrafter.utils.parsers.TextParser;

public class DialogListeners {
    private TextWatcher textWatcher;
    private DialogListenerInterface dialogListenerInterface;


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

    public DialogListenerInterface getDialogListener(final MainModel mainModel) {
        if (dialogListenerInterface == null)
            dialogListenerInterface = new DialogListenerInterface() {
                @Override
                public void onPositiveClick(DialogSmsInput dialogSmsInput, String smsText) {
                    if (smsText != null && smsText.length() > 0 && mainModel.getSmsValuesAdapter() != null) {
                        mainModel.setSmsText(smsText);
                        mainModel.getList().clear();
                        mainModel.getList().addAll(new TextParser().textToKeyValue(smsText));
                        mainModel.getSmsValuesAdapter().setList(mainModel.getList());
                        new SmsTextDbHelper(mainModel.getActivity()).writeToDatabase(SmsTextContract.TEMP_KEY, smsText, mainModel.getTelText()).close();
                    }
                }

                @Override
                public void onNegativeClick(DialogSmsInput dialogSmsInpup) {

                }
            };
        return dialogListenerInterface;
    }

    public DialogListenerInterface getDialogListenerSendText(final MainModel mainModel) {
        if (dialogListenerInterface == null)
            dialogListenerInterface = new DialogListenerInterface() {
                @Override
                public void onPositiveClick(DialogSmsInput dialogSmsInput, String smsText) {
                    if (smsText != null && smsText.length() > 0) {
                        if (ContextCompat.checkSelfPermission(mainModel.getActivity(), Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
                            SmsManager smsManager = SmsManager.getDefault();
                            smsManager.sendTextMessage(mainModel.getTelText(), null, smsText, null, null);
                        } else{
                            Toast.makeText(mainModel.getActivity(), mainModel.getActivity().getString(R.string.toast_no_sms_send_perm), Toast.LENGTH_LONG).show();
                        }
                    }
                }
                @Override
                public void onNegativeClick(DialogSmsInput dialogSmsInpup) {

                }
            };
        return dialogListenerInterface;
    }
}
