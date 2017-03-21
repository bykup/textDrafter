package com.byku.android.textdrafter.database;


import com.byku.android.textdrafter.activities.mainactivity.MainFragmentModel;

import java.util.List;

interface SmsTextDbHelper {

    SmsTextDbHelper writeToDatabase(String smsKey, String smsText, String smsRecipent);

    SmsTextDbHelper writeToDatabase(String smsKey, MainFragmentModel mainFragmentModel);

    String readValueFromDatabase(String key);

    String readRecipentFromDatabase(String key);

    List<String> readAllKeysFromDb();

}
