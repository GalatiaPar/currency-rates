package com.bdswiss.bdswiss.ui.base;

import android.content.Context;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

public class BDSwissApplication extends DaggerApplication {

  private static BDSwissApplication mInstance;

  public static Context getInstance() {
    return mInstance;
  }

  @Override
  public void onCreate() {
    super.onCreate();
    mInstance = this;
  }

  @Override
  protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
    final AppComponent appComponent = DaggerAppComponent.builder().application(this).build();
    appComponent.inject(this);
    return appComponent;
  }
}
