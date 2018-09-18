package com.bdswiss.bdswiss.ui.base;

import com.bdswiss.bdswiss.ui.MainActivity;
import com.bdswiss.bdswiss.ui.MainModule;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
abstract class ActivityBuilder {

  @ContributesAndroidInjector(modules = MainModule.class)
  abstract MainActivity bindMainActivity();

}
