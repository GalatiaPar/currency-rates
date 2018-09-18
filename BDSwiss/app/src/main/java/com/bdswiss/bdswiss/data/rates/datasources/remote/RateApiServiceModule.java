package com.bdswiss.bdswiss.data.rates.datasources.remote;

import android.content.Context;
import android.support.annotation.NonNull;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class RateApiServiceModule {

  @Provides
  static RateApiService provideLeagueTablesApiService(@NonNull final Context context) {
    return new RateApiService(context);
  }
}
