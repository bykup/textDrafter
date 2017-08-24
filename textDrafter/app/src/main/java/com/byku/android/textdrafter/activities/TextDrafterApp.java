package com.byku.android.textdrafter.activities;

import android.app.Application;

import com.byku.android.textdrafter.injection.component.DaggerActivityComponent;
import com.byku.android.textdrafter.injection.component.ActivityComponent;
import com.byku.android.textdrafter.injection.module.ActivityModule;

public class TextDrafterApp extends Application {

    private ActivityComponent activityComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        activityComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(getBaseContext()))
                .build();

    }

    public ActivityComponent getActivityComponent() {
        return activityComponent;
    }
}
