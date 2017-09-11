package com.byku.android.textdrafter.activities.mainactivity.fragment;

import android.widget.Toast;

import com.byku.android.textdrafter.R;
import com.byku.android.textdrafter.activities.mainactivity.views.MainRecycler;
import com.byku.android.textdrafter.database.SmsTextDbHelper;
import com.byku.android.textdrafter.utils.dialogs.DialogHelper;
import com.byku.android.textdrafter.utils.parsers.SmsParser;

public class MainFragmentHandler {

    public void onSendSmsClick(MainFragmentModel model, MainRecycler mainRecycler) {
        new SmsTextDbHelper(model.getActivity())
                .writeToDatabase(model.getSmsKey(), model.getSmsText(), model.getTelText())
                .close();
        new DialogHelper().createShareSendEditDialog(model,new SmsParser(model,mainRecycler).parseToSms()).showDialog();
    }

    public void onSaveClick(MainFragmentModel model, MainRecycler mainRecycler) {
        new SmsTextDbHelper(model.getActivity())
                .writeToDatabase(model.getSmsKey(), model.getSmsText(), model.getTelText())
                .close();
        Toast.makeText(model.getActivity(), model.getActivity().getString(R.string.toast_draft_saved), Toast.LENGTH_SHORT).show();
    }
}
