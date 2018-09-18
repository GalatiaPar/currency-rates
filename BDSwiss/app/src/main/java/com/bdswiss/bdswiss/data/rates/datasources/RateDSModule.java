package com.bdswiss.bdswiss.data.rates.datasources;

import com.bdswiss.bdswiss.data.rates.datasources.remote.RateApiService;
import com.bdswiss.bdswiss.data.rates.datasources.remote.RateApiServiceModule;
import com.bdswiss.bdswiss.data.rates.datasources.remote.RatesDSApi;
import dagger.Module;
import dagger.Provides;

@Module(includes = RateApiServiceModule.class)
public abstract class RateDSModule {
  @Provides
  public static RateDS.Remote provideRateRemoteDS(RateApiService rateApiService) {
    return new RatesDSApi(rateApiService);
  }
}
