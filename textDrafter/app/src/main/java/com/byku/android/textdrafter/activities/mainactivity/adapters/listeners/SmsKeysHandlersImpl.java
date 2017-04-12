package com.byku.android.textdrafter.activities.mainactivity.adapters.listeners;

import android.view.View;

import com.byku.android.textdrafter.activities.mainactivity.MainView;
import com.byku.android.textdrafter.activities.mainactivity.adapters.SmsKeysAdapter;
import com.byku.android.textdrafter.database.SmsTextDbHelper;

public class SmsKeysHandlersImpl implements SmsKeysHandlers{

    public static int NOT_INITIALIZED_POSITION = -1;

    private MainView mainView;
    private SmsTextDbHelper smsTextDbHelper;
    private SmsKeysAdapter smsKeysAdapter;

    private int position = NOT_INITIALIZED_POSITION;

    public SmsKeysHandlersImpl(MainView activityWithPageViewer, int positionOfClickedItem, SmsTextDbHelper databaseWithSmsKeys, SmsKeysAdapter smsKeysAdapter){
        this.mainView = activityWithPageViewer;
        this.position = positionOfClickedItem;
        this.smsTextDbHelper = databaseWithSmsKeys;
        this.smsKeysAdapter = smsKeysAdapter;
    }

    @Override
    public SmsKeysHandlers setCurrentPosition(int position){
        this.position = position;
        return this;
    }

    @Override
    public View.OnClickListener getOnClickListener() throws IllegalArgumentException{
        if(position == NOT_INITIALIZED_POSITION)
            throw new IllegalArgumentException("Position not initialized, position = -1");

        return new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                smsKeysAdapter.setCurrentItemTo(position);
                mainView.setPagerPage(position);
            }
        };
    }

    @Override
    public View.OnLongClickListener getOnLongClickListener(final String smsKey) throws IllegalArgumentException{
        if(position == NOT_INITIALIZED_POSITION)
            throw new IllegalArgumentException("Position not initialized, position = -1");

        return new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View view) {
                smsTextDbHelper.removeFromDatabase(smsKey);
                smsKeysAdapter.dataSetChanged();
                return true;
            }
        };
    }
}
