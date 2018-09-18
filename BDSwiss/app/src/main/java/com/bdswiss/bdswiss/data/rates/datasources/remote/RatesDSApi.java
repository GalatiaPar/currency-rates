package com.bdswiss.bdswiss.data.rates.datasources.remote;

import android.support.annotation.NonNull;
import com.bdswiss.bdswiss.data.rates.datasources.RateDS.Remote;
import com.bdswiss.bdswiss.data.rates.model.RateAMWrapper;
import com.bdswiss.bdswiss.data.rates.model.RateMapper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RatesDSApi implements Remote {

  private RateApiService mService;
  private Call<RateAMWrapper> mLoadCurrentRates;

  public RatesDSApi(RateApiService service) {
    mService = service;
  }

  @Override
  public void loadRates(@NonNull final OnLoadRatesCallbacks onLoadRatesCallbacks) {
    mLoadCurrentRates = mService.loadRates();
    mLoadCurrentRates.enqueue(
        new Callback<RateAMWrapper>() {
          @Override
          public void onResponse(
              @NonNull Call<RateAMWrapper> call,
              @NonNull Response<RateAMWrapper> response) {
            final RateAMWrapper body = response.body();
            if (response.isSuccessful() && body != null) {
              onLoadRatesCallbacks.onSuccessLoadingRates(RateMapper.mapToDM(body));
            } else {
              onLoadRatesCallbacks.onErrorLoadingRates();
            }
          }

          @Override
          public void onFailure(@NonNull Call<RateAMWrapper> call, @NonNull Throwable t) {
            if (!call.isCanceled()) {
              onLoadRatesCallbacks.onErrorLoadingRates();
            }
          }
        });
  }

  @Override
  public void cancel() {
    if (mLoadCurrentRates != null) {
      mLoadCurrentRates.cancel();
    }
  }
}
