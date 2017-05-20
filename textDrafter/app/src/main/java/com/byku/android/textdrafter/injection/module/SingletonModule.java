package com.byku.android.textdrafter.injection.module;


import android.content.Context;

import com.byku.android.textdrafter.database.SmsTextDbHelper;
import com.byku.android.textdrafter.database.SmsTextDbHelperImpl;
import com.byku.android.textdrafter.utils.views.observers.ViewMetricsSourceSubjectSingleton;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class SingletonModule {

    Context context;

    public SingletonModule(Context context){
        this.context = context;
    }

    @Provides
    @Singleton
    public ViewMetricsSourceSubjectSingleton providesViewMetricsSourceSubjectSingleton(){
        return ViewMetricsSourceSubjectSingleton.getInstance();
    }

    @Provides
    @Singleton
    public Context providesContext(){
        return context;
    }

    @Provides
    @Singleton
    public SmsTextDbHelper provideSmsTextDbHelper(Context context){
        return new SmsTextDbHelperImpl(context);
    }

}
