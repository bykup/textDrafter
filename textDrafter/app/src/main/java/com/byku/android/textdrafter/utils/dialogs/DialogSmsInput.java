package com.byku.android.textdrafter.utils.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;

import com.byku.android.textdrafter.R;
import com.byku.android.textdrafter.databinding.InputDialogTextBinding;
import com.byku.android.textdrafter.utils.dialogs.interfaces.DialogListenerInterface;

public class DialogSmsInput extends DialogFragment {

    private DialogModel dialogModel;
    private AlertDialog dialog;
    private DialogListeners dialogListeners;
    private DialogListenerInterface listener;
    private String smsText;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        InputDialogTextBinding inputDialogTextBinding = DataBindingUtil.inflate(inflater, R.layout.input_dialog_text, null, false);
        dialogModel = new DialogModel(smsText);
        dialogListeners = new DialogListeners();

        inputDialogTextBinding.setDialogmodel(dialogModel);
        inputDialogTextBinding.dialogSmstext.addTextChangedListener(dialogListeners.getTextWatcher(dialogModel));

        dialog = new AlertDialog.Builder(getActivity())
                .setView(inputDialogTextBinding.getRoot())
                .setPositiveButton(R.string.dialog_accept, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (listener != null)
                            listener.onPositiveClick(DialogSmsInput.this, dialogModel.getSmsText());
                    }
                })
                .setNegativeButton(R.string.dialog_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (listener != null)
                            listener.onNegativeClick(DialogSmsInput.this);
                        DialogSmsInput.this.getDialog().cancel();
                    }
                })
                .create();
        return dialog;
    }

    public DialogSmsInput setDefaultText(String smsText){
        this.smsText = smsText;
        return this;
    }

    public DialogSmsInput setListener(DialogListenerInterface listener){
        this.listener = listener;
        return this;
    }

    public void setDialogText(String string) {
        if (dialogModel != null)
            dialogModel.setSmsText(string);
    }
}
