package com.byku.android.textdrafter.database;


import com.byku.android.textdrafter.activities.mainactivity.MainModel;

import java.util.List;

interface SmsTextDbHelper {

    SmsTextDbHelper writeToDatabase(String smsKey, String smsText, String smsRecipent);

    SmsTextDbHelper writeToDatabase(String smsKey, MainModel mainModel);

    String readValueFromDatabase(String key);

    String readRecipentFromDatabase(String key);

    List<String> readAllKeysFromDb();

}
