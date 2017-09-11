package com.byku.android.textdrafter.utils.dialogs;

import android.app.AlertDialog;
import android.databinding.DataBindingUtil;
import android.text.InputType;
import android.view.LayoutInflater;

import com.byku.android.textdrafter.R;
import com.byku.android.textdrafter.activities.mainactivity.activity.MainViewInterface;
import com.byku.android.textdrafter.activities.mainactivity.fragment.MainFragmentModel;
import com.byku.android.textdrafter.databinding.InputDialogTextBinding;

public class DialogHelper {

    protected AlertDialog dialog;
    protected DialogHandlers handlers;
    protected DialogModel dialogModel;
    protected DialogListeners dialogListener;

    public DialogHelper() {
        handlers = new DialogHandlers();
        dialogModel = new DialogModel();
        dialogListener = new DialogListeners();

    }

    public DialogHelper createShareSendEditDialog(MainFragmentModel mainFragmentModel, String smsText) {
        LayoutInflater inflater = mainFragmentModel.getActivity().getLayoutInflater();
        InputDialogTextBinding inputDialogTextBinding = DataBindingUtil.inflate(inflater, R.layout.input_dialog_text, null, false);
        dialogModel.setSmsText(smsText);

        inputDialogTextBinding.setDialogmodel(dialogModel);
        inputDialogTextBinding.dialogSmstext.addTextChangedListener(dialogListener.getTextWatcher(dialogModel));

        dialog = new AlertDialog.Builder(mainFragmentModel.getActivity())
                .setView(inputDialogTextBinding.getRoot())
                .setPositiveButton(R.string.dialog_send, handlers.sendText(mainFragmentModel, smsText))
                .setNeutralButton(R.string.dialog_share, handlers.shareText(mainFragmentModel, smsText))
                .setNegativeButton(R.string.dialog_cancel, handlers.cancelDialog())
                .create();
        return this;
    }

    public DialogHelper createAddKeyValueEditDialog(MainViewInterface mainView) {
        LayoutInflater inflater = mainView.getActivity().getLayoutInflater();
        InputDialogTextBinding inputDialogTextBinding = DataBindingUtil.inflate(inflater, R.layout.input_dialog_text, null, false);
        inputDialogTextBinding.dialogSmstext.setInputType(InputType.TYPE_TEXT_VARIATION_SHORT_MESSAGE);
        inputDialogTextBinding.dialogSmstext.setLines(1);
        inputDialogTextBinding.setDialogmodel(dialogModel);
        inputDialogTextBinding.dialogSmstext.addTextChangedListener(dialogListener.getTextWatcher(dialogModel));
        inputDialogTextBinding.dialogSmstext.setHint(R.string.dialog_new_hint);

        dialog = new AlertDialog.Builder(mainView.getActivity())
                .setView(inputDialogTextBinding.getRoot())
                .setPositiveButton(R.string.dialog_accept, handlers.saveNewKey(mainView, dialogModel))
                .setNegativeButton(R.string.dialog_cancel, handlers.cancelDialog())
                .create();
        return this;
    }

    public DialogHelper setText(String smsText) {
        if (dialogModel == null)
            return null;
        dialogModel.setSmsText(smsText);
        return this;
    }

    public DialogHelper showDialog() {
        if (dialog == null)
            return null;
        dialog.show();
        return this;
    }

}
