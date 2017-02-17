package com.byku.android.textdrafter.database.Tables;

import android.provider.BaseColumns;

public class SmsTextContract {

    public static String TEMP_KEY = "smsTempKey";

    private SmsTextContract(){}

    public static class FeedEntry implements BaseColumns{
        public static final String TABLE_NAME = "smsdata";
        public static final String COLUMN_NAME_SMSKEY = "smsKey";
        public static final String COLUMN_NAME_SMSVALUE = "smsValue";
        public static final String COLUMN_NAME_SMSRECIPENT = "smsRecipent";
    }
}
