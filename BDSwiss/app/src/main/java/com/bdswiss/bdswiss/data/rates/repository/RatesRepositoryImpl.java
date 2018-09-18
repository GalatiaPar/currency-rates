package com.bdswiss.bdswiss.data.rates.repository;

import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;
import com.bdswiss.bdswiss.data.rates.datasources.RateDS;
import com.bdswiss.bdswiss.data.rates.datasources.RateDS.Remote.OnLoadRatesCallbacks;
import com.bdswiss.bdswiss.model.domain.RateDM;
import java.util.ArrayList;

public class RatesRepositoryImpl implements RatesRepository {

  private final RateDS.Remote mRemoteDS;

  public RatesRepositoryImpl(RateDS.Remote remoteDS) {
    mRemoteDS = remoteDS;
  }

  @Override
  @WorkerThread
  public void loadRates(@NonNull final OnLoadRateCallbacks onLoadRateCallbacks) {
    mRemoteDS.loadRates(
        new OnLoadRatesCallbacks() {
          @Override
          public void onSuccessLoadingRates(@NonNull final ArrayList<RateDM> rateDMS) {
            onLoadRateCallbacks.onSuccessLoadingRates(rateDMS);
          }

          @Override
          public void onErrorLoadingRates() {
            onLoadRateCallbacks.onErrorLoadingRates();
          }
        });
  }

  @Override
  @WorkerThread
  public void cancel() {
    mRemoteDS.cancel();
  }
}
