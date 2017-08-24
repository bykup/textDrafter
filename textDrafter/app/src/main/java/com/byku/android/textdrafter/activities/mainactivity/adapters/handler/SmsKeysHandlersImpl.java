package com.byku.android.textdrafter.activities.mainactivity.adapters.handler;

import android.view.View;

import com.byku.android.textdrafter.activities.mainactivity.activity.MainView;
import com.byku.android.textdrafter.activities.mainactivity.adapters.SmsKeysAdapter;
import com.byku.android.textdrafter.activities.mainactivity.adapters.SmsKeysHolder;
import com.byku.android.textdrafter.database.SmsTextDbHelperInterface;

public class SmsKeysHandlersImpl implements SmsKeysHandlers{

    private MainView mainView;
    private SmsTextDbHelperInterface smsTextDbHelper;
    private SmsKeysAdapter smsKeysAdapter;
    private SmsKeysHolder smsKeysHolder;

    private String key = "";

    public SmsKeysHandlersImpl(MainView activityWithPageViewer, String key, SmsTextDbHelperInterface databaseWithSmsKeys, SmsKeysAdapter smsKeysAdapter, SmsKeysHolder smsKeysHolder){
        this.mainView = activityWithPageViewer;
        this.key = key;
        this.smsTextDbHelper = databaseWithSmsKeys;
        this.smsKeysAdapter = smsKeysAdapter;
        this.smsKeysHolder = smsKeysHolder;
    }

    @Override
    public View.OnClickListener getOnClickListener() throws IllegalArgumentException{
        return new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                smsKeysAdapter.setCurrentItemTo(key);
                mainView.setPagerPage(key);
                smsKeysHolder.setBindingActive();
            }
        };
    }

    @Override
    public View.OnLongClickListener getOnLongClickListener() throws IllegalArgumentException{
        return new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View view) {
                smsTextDbHelper.removeFromDatabase(key);
                smsKeysAdapter.itemRemoved(key);
                return true;
            }
        };
    }
}
