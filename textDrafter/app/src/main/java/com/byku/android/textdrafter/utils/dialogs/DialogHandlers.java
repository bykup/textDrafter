package com.byku.android.textdrafter.utils.dialogs;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.byku.android.textdrafter.R;
import com.byku.android.textdrafter.activities.MainModel;

public class DialogHandlers {

    public DialogInterface.OnClickListener sendText(final MainModel mainModel, final String smsText){
        return new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int id) {
                if (smsText != null && smsText.length() > 0) {
                    if (ContextCompat.checkSelfPermission(mainModel.getActivity(), Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
                        Intent intent = new Intent(Intent.ACTION_SENDTO);
                        intent.setData(Uri.parse("smsto:" + mainModel.getTelText()));  // This ensures only SMS apps respond
                        intent.putExtra("sms_body", smsText);
                        if (intent.resolveActivity(mainModel.getActivity().getPackageManager()) != null) {
                            mainModel.getActivity().startActivity(intent);
                        }
                    } else {
                        Toast.makeText(mainModel.getActivity(), mainModel.getActivity().getString(R.string.toast_no_sms_send_perm), Toast.LENGTH_LONG).show();
                    }
                }
            }
        };
    }

    public DialogInterface.OnClickListener shareText(MainModel mainModel){
        return new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int id) {

            }
        };
    }

    public DialogInterface.OnClickListener cancelDialog(){
        return new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int id) {

            }
        };
    }
}
