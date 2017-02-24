package com.byku.android.textdrafter.utils.dialogs;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;

import com.byku.android.textdrafter.R;
import com.byku.android.textdrafter.activities.MainModel;
import com.byku.android.textdrafter.databinding.InputDialogTextBinding;
import com.byku.android.textdrafter.utils.dialogs.interfaces.DialogListenerInterface;

public class DialogHelper {

    protected AlertDialog dialog;
    protected DialogModel dialogModel;
    protected DialogListenerInterface listener;

    public DialogHelper createAcceptCancelEditDialog(MainModel mainModel,String smsText){
        LayoutInflater inflater = mainModel.getActivity().getLayoutInflater();
        InputDialogTextBinding inputDialogTextBinding = DataBindingUtil.inflate(inflater, R.layout.input_dialog_text, null, false);
        dialogModel = new DialogModel(smsText);
        DialogListeners dialogListeners = new DialogListeners();

        inputDialogTextBinding.setDialogmodel(dialogModel);
        inputDialogTextBinding.dialogSmstext.addTextChangedListener(dialogListeners.getTextWatcher(dialogModel));

        dialog = new AlertDialog.Builder(mainModel.getActivity())
                .setView(inputDialogTextBinding.getRoot())
                .setPositiveButton(R.string.dialog_accept, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (listener != null)
                            listener.onPositiveClick(DialogHelper.this,dialogModel.getSmsText());
                    }
                })
                .setNegativeButton(R.string.dialog_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (listener != null)
                            listener.onNegativeClick(DialogHelper.this);
                        dialog.dismiss();
                    }
                })
                .create();
        return this;
    }

    public DialogHelper createShareSendEditDialog(MainModel mainModel,String smsText){
        LayoutInflater inflater = mainModel.getActivity().getLayoutInflater();
        InputDialogTextBinding inputDialogTextBinding = DataBindingUtil.inflate(inflater, R.layout.input_dialog_text, null, false);
        dialogModel = new DialogModel(smsText);
        DialogListeners dialogListeners = new DialogListeners();

        inputDialogTextBinding.setDialogmodel(dialogModel);
        inputDialogTextBinding.dialogSmstext.addTextChangedListener(dialogListeners.getTextWatcher(dialogModel));

        dialog = new AlertDialog.Builder(mainModel.getActivity())
                .setView(inputDialogTextBinding.getRoot())
                .setPositiveButton(R.string.dialog_send, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (listener != null)
                            listener.onPositiveClick(DialogHelper.this,dialogModel.getSmsText());
                    }
                })
                .setNegativeButton(R.string.dialog_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (listener != null)
                            listener.onNegativeClick(DialogHelper.this);
                        dialog.dismiss();
                    }
                })
                .create();
        return this;
    }

    public DialogHelper setListener(DialogListenerInterface listener){
        if(dialog == null || dialogModel == null)
            return null;
        this.listener = listener;
        return this;
    }

    public DialogHelper setText(String smsText){
        if(dialog == null || dialogModel == null)
            return null;
        dialogModel.setSmsText(smsText);
        return this;
    }

    public DialogHelper showDialog(){
        if(dialog == null || dialogModel == null)
            return null;
        dialog.show();
        return this;
    }

}
