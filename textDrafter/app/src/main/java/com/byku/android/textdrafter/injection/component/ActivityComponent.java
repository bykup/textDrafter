package com.byku.android.textdrafter.injection.component;

import com.byku.android.textdrafter.activities.mainactivity.activity.MainActivity;
import com.byku.android.textdrafter.activities.mainactivity.adapters.SmsKeysAdapterImpl;
import com.byku.android.textdrafter.activities.mainactivity.fragment.MainFragment;
import com.byku.android.textdrafter.injection.module.ActivityModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ActivityModule.class})
public interface ActivityComponent {

    void inject(MainActivity activity);

    void inject(SmsKeysAdapterImpl smsKeysAdapter);

    void inject(MainFragment mainFragment);
}
