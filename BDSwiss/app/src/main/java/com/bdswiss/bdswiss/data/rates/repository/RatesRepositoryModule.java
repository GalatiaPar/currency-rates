package com.bdswiss.bdswiss.data.rates.repository;

import com.bdswiss.bdswiss.data.rates.datasources.RateDS;
import com.bdswiss.bdswiss.data.rates.datasources.RateDSModule;
import com.bdswiss.bdswiss.data.rates.datasources.remote.RatesDSApi;
import dagger.Module;
import dagger.Provides;

@Module(includes = RateDSModule.class)
public abstract class RatesRepositoryModule {

  @Provides
  public static RatesRepository provideRepository(RateDS.Remote ratesRemoteDS) {
    return new RatesRepositoryImpl(ratesRemoteDS);
  }
}
