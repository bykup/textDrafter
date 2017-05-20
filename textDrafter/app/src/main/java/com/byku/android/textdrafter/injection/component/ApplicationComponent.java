package com.byku.android.textdrafter.injection.component;

import com.byku.android.textdrafter.injection.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules={ApplicationModule.class})
public interface ApplicationComponent {
}
