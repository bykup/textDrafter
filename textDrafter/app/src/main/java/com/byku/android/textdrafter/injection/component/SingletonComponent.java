package com.byku.android.textdrafter.injection.component;

import com.byku.android.textdrafter.activities.mainactivity.activity.MainActivity;
import com.byku.android.textdrafter.activities.mainactivity.adapters.SmsKeysAdapterImpl;
import com.byku.android.textdrafter.injection.module.SingletonModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Byku on 25.04.2017.
 */

@Singleton
@Component(modules = {SingletonModule.class})
public interface SingletonComponent {

    void inject(MainActivity activity);

    void inject(SmsKeysAdapterImpl smsKeysAdapter);
}
