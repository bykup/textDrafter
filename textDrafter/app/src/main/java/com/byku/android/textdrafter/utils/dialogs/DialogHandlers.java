package com.byku.android.textdrafter.utils.dialogs;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v4.text.TextUtilsCompat;
import android.text.TextUtils;
import android.widget.Toast;

import com.byku.android.textdrafter.R;
import com.byku.android.textdrafter.activities.mainactivity.MainFragmentModel;
import com.byku.android.textdrafter.activities.mainactivity.MainView;
import com.byku.android.textdrafter.database.SmsTextDbHelperImpl;

public class DialogHandlers {

    public DialogHandlers getDialogHandlers() {
        return this;
    }

    public DialogInterface.OnClickListener sendText(final MainFragmentModel mainFragmentModel, final String smsText) {
        return new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int id) {
                if (smsText != null && smsText.length() > 0) {
                    if (ContextCompat.checkSelfPermission(mainFragmentModel.getActivity(),
                            Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
                        Intent intent = new Intent(Intent.ACTION_SENDTO);
                        intent.setData(Uri.parse("smsto:" + mainFragmentModel.getTelText()));  // This ensures only SMS apps respond
                        intent.putExtra("sms_body", smsText);
                        if (intent.resolveActivity(
                                mainFragmentModel.getActivity().getPackageManager()) != null) {
                            mainFragmentModel.getActivity().startActivity(intent);
                        }
                    } else {
                        Toast.makeText(mainFragmentModel.getActivity(), mainFragmentModel.getActivity().getString(R.string.toast_no_sms_send_perm),
                                Toast.LENGTH_LONG).show();
                    }
                }
            }
        };
    }

    public DialogInterface.OnClickListener saveKeyValue(final MainView mainFragmentModel, final DialogModel dialogModel) {
        return new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int id) {
                if (TextUtils.isEmpty(dialogModel.getSmsText()))
                    return;
                new SmsTextDbHelperImpl(mainFragmentModel.getActivity()).writeToDatabase(dialogModel.getSmsText(), "", "");
                mainFragmentModel.refreshViews();
            }
        };
    }

    public DialogInterface.OnClickListener shareText(final MainFragmentModel mainFragmentModel, final String smsText) {
        return new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int id) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, smsText);
                sendIntent.setType("text/plain");
                mainFragmentModel.getActivity().startActivity(Intent.createChooser(sendIntent, mainFragmentModel.getActivity().getResources().getText(R.string.sharing_send_to)));
            }
        };
    }

    public DialogInterface.OnClickListener cancelDialog() {
        return new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int id) {

            }
        };
    }
}
