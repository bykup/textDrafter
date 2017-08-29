package com.byku.android.textdrafter.database;


import com.byku.android.textdrafter.activities.mainactivity.fragment.MainFragmentModel;
import com.byku.android.textdrafter.database.Models.KeyValueRecipentModel;

import java.util.List;
import java.util.NoSuchElementException;

public interface SmsTextDbHelperInterface {

    SmsTextDbHelperInterface writeToDatabase(String smsKey, String smsText, String smsRecipent);

    SmsTextDbHelperInterface writeToDatabase(String smsKey, MainFragmentModel mainFragmentModel);

    String readValueFromDatabase(String key);

    String readRecipentFromDatabase(String key);

    List<KeyValueRecipentModel> readAllFullModelsFromDatabase();

    List<String> readAllKeysFromDb();

    void removeFromDatabase(String smsKey)throws NoSuchElementException;

}
