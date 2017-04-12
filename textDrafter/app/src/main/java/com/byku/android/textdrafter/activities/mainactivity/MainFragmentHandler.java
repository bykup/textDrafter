package com.byku.android.textdrafter.activities.mainactivity;

import com.byku.android.textdrafter.activities.mainactivity.views.MainRecycler;
import com.byku.android.textdrafter.database.SmsTextDbHelperImpl;
import com.byku.android.textdrafter.database.Tables.SmsTextContract;
import com.byku.android.textdrafter.utils.dialogs.DialogHandlers;
import com.byku.android.textdrafter.utils.dialogs.DialogHelper;
import com.byku.android.textdrafter.utils.parsers.SmsParser;

public class MainFragmentHandler {

    public void onSendSmsClick(MainFragmentModel model, MainRecycler mainRecycler) {
        new SmsTextDbHelperImpl(model.getActivity())
                .writeToDatabase(model.getSmsKey(), model.getSmsText(), model.getTelText())
                .close();
        new DialogHelper().createShareSendEditDialog(model,new SmsParser(model,mainRecycler).parseToSms()).showDialog();
    }
}
