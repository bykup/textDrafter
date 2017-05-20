package com.byku.android.textdrafter.activities;

import android.app.Application;

import com.byku.android.textdrafter.injection.component.DaggerSingletonComponent;
import com.byku.android.textdrafter.injection.component.SingletonComponent;
import com.byku.android.textdrafter.injection.module.SingletonModule;

public class TextDrafterApp extends Application {

    private SingletonComponent singletonComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        singletonComponent = DaggerSingletonComponent.builder()
                .singletonModule(new SingletonModule(getBaseContext()))
                .build();

    }

    public SingletonComponent getSingletonComponent() {
        return singletonComponent;
    }
}
