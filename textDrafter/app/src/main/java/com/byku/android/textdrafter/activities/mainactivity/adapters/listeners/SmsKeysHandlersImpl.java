package com.byku.android.textdrafter.activities.mainactivity.adapters.listeners;

import android.view.View;

import com.byku.android.textdrafter.activities.mainactivity.MainView;

public class SmsKeysHandlersImpl implements SmsKeysHandlers{

    public static int NOT_INITIALIZED_POSITION = -1;

    private MainView mainView;
    private int position = NOT_INITIALIZED_POSITION;

    public SmsKeysHandlersImpl(MainView mainView){
        this.mainView = mainView;
    }

    public SmsKeysHandlersImpl(MainView mainView, int position){
        this.mainView = mainView;
        this.position = position;
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
                mainView.setPage(position);
            }
        };
    }
}
