package com.byku.android.textdrafter.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;

import com.byku.android.textdrafter.activities.mainactivity.MainModel;
import com.byku.android.textdrafter.database.Tables.SmsTextContract;
import com.byku.android.textdrafter.database.Tables.SmsTextContract.FeedEntry;

import java.util.ArrayList;
import java.util.List;

public class SmsTextDbHelperImpl extends SQLiteOpenHelper implements SmsTextDbHelper {

    public static final int DATABASE_VERSION = 4;
    public static final String DATABASE_NAME = "SmsTextDb.db";

    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + FeedEntry.TABLE_NAME + " (" + FeedEntry._ID + " INTEGER PRIMARY KEY," + FeedEntry.COLUMN_NAME_SMSKEY + " TEXT," + FeedEntry.COLUMN_NAME_SMSVALUE + " TEXT, " + FeedEntry.COLUMN_NAME_SMSRECIPENT + " TEXT, UNIQUE (" + FeedEntry.COLUMN_NAME_SMSKEY + ") ON CONFLICT REPLACE)";
    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME;

    String[] projectionAll = {
            FeedEntry._ID,
            FeedEntry.COLUMN_NAME_SMSKEY,
            FeedEntry.COLUMN_NAME_SMSVALUE,
            FeedEntry.COLUMN_NAME_SMSRECIPENT
    };

    String[] projectionKeys = {
            FeedEntry.COLUMN_NAME_SMSKEY,
    };

    public SmsTextDbHelperImpl(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    @Override
    public SmsTextDbHelperImpl writeToDatabase(String smsKey, String smsText, String smsRecipent) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(
                FeedEntry.COLUMN_NAME_SMSKEY,
                smsKey);
        contentValues.put(
                FeedEntry.COLUMN_NAME_SMSVALUE,
                smsText);
        contentValues.put(
                FeedEntry.COLUMN_NAME_SMSRECIPENT,
                smsRecipent);
        this.getWritableDatabase().insert(
                FeedEntry.TABLE_NAME,
                null,
                contentValues);
        return this;
    }

    @Override
    public SmsTextDbHelperImpl writeToDatabase(String smsKey, MainModel mainModel) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(
                FeedEntry.COLUMN_NAME_SMSKEY,
                smsKey);
        contentValues.put(
                FeedEntry.COLUMN_NAME_SMSVALUE,
                mainModel.getSmsText());
        contentValues.put(
                FeedEntry.COLUMN_NAME_SMSRECIPENT,
                mainModel.getTelText());
        this.getWritableDatabase().insert(
                FeedEntry.TABLE_NAME,
                null,
                contentValues);
        return this;
    }

    @Override
    public String readValueFromDatabase(String key) {
        if (TextUtils.isEmpty(key))
            return "";
        String selection = FeedEntry.COLUMN_NAME_SMSKEY + " = ?";
        String[] selectionArgs = {key};
        Cursor cursor = this.getReadableDatabase().query(
                FeedEntry.TABLE_NAME,
                projectionAll,
                selection, selectionArgs, null, null, null);
        StringBuilder builder = new StringBuilder();
        while (cursor.moveToNext()) {
            builder.append(cursor.getString(
                    cursor.getColumnIndexOrThrow(
                            FeedEntry.COLUMN_NAME_SMSVALUE)));
        }
        cursor.close();
        return builder.toString();
    }

    @Override
    public String readRecipentFromDatabase(String key) {
        if (TextUtils.isEmpty(key))
            return "";
        String selection = FeedEntry.COLUMN_NAME_SMSKEY + " = ?";
        String[] selectionArgs = {key};
        Cursor cursor = this.getReadableDatabase().query(
                FeedEntry.TABLE_NAME,
                projectionAll,
                selection,
                selectionArgs,
                null, null, null);
        StringBuilder builder = new StringBuilder();
        while (cursor.moveToNext()) {
            builder.append(cursor.getString(
                    cursor.getColumnIndexOrThrow(
                            FeedEntry.COLUMN_NAME_SMSRECIPENT)));
        }
        cursor.close();
        return builder.toString();
    }

    @Override
    public List<String> readAllKeysFromDb() {
        List<String> allKeys = new ArrayList<>();
        Cursor cursor = this.getReadableDatabase().query(
                FeedEntry.TABLE_NAME,
                projectionKeys,
                null, null, null, null, null
        );
        while(cursor.moveToNext()){
            allKeys.add(cursor.getString(cursor.getColumnIndexOrThrow(FeedEntry.COLUMN_NAME_SMSKEY)));
        }
        cursor.close();
        if(allKeys.isEmpty()) {
            writeToDatabase(SmsTextContract.TEMP_KEY, "", "");
            return readAllKeysFromDb();
        }
        return allKeys;
    }
}
