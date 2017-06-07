package com.byku.android.textdrafter.utils;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.widget.Toast;

import com.byku.android.textdrafter.activities.mainactivity.adapters.models.ContactModel;

import java.util.ArrayList;
import java.util.List;

public class ContactDbHelper {

    public List<ContactModel> getContacts(Activity activity){
        List<ContactModel> contactModels = new ArrayList<>();
        ContentResolver cr = activity.getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);

        if (cur.getCount() > 0) {
            while (cur.moveToNext()) {
                String id = cur.getString(
                        cur.getColumnIndex(ContactsContract.Contacts._ID));
                if (cur.getInt(cur.getColumnIndex(
                        ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {
                    String name = cur.getString(cur.getColumnIndex(
                            ContactsContract.Contacts.DISPLAY_NAME));
                    Cursor pCur = cr.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = ?",
                            new String[]{id}, null);
                    while (pCur.moveToNext()) {
                        String phoneNo = pCur.getString(pCur.getColumnIndex(
                                ContactsContract.CommonDataKinds.Phone.NUMBER));
                        ContactModel contactModel = new ContactModel();
                        contactModel.contactName = name;
                        contactModel.contactNumber = phoneNo;
                        contactModels.add(contactModel);
                    }
                    pCur.close();
                }
            }
        }
        return contactModels;
    }
}
