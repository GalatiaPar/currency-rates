package com.bdswiss.bdswiss.ui.base;

import android.content.Context;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class ContextModule {

  @Provides
  static Context provideContext() {
    return BDSwissApplication.getInstance();
  }
}
