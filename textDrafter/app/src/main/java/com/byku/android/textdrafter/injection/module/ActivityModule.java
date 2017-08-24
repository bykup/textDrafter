package com.byku.android.textdrafter.injection.module;


import android.content.Context;

import com.byku.android.textdrafter.activities.mainactivity.activity.MainPresenter;
import com.byku.android.textdrafter.activities.mainactivity.activity.MainPresenterInterface;
import com.byku.android.textdrafter.database.SmsTextDbHelper;
import com.byku.android.textdrafter.database.SmsTextDbHelperInterface;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    Context context;

    public ActivityModule(Context context) {
        this.context = context;
    }

//    @Provides
//    @Singleton
//    public ViewMetricsSourceSubjectSingleton providesViewMetricsSourceSubjectSingleton() {
//        return ViewMetricsSourceSubjectSingleton.getInstance();
//    }

    @Provides
    @Singleton
    public Context providesContext() {
        return context;
    }

    @Provides
    @Singleton
    public SmsTextDbHelperInterface provideSmsTextDbHelper(Context context) {
        return new SmsTextDbHelper(context);
    }

    @Provides
    public MainPresenterInterface providesMainPresenter(SmsTextDbHelperInterface smsTextDbHelper){
        return new MainPresenter(smsTextDbHelper);
    }
}
