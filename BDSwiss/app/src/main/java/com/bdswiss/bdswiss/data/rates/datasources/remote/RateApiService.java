package com.bdswiss.bdswiss.data.rates.datasources.remote;

import android.content.Context;
import android.support.annotation.NonNull;
import com.bdswiss.bdswiss.data.rates.model.RateAMWrapper;
import retrofit2.Call;
import retrofit2.http.GET;

public class RateApiService {

  private RatesService mRateApiService;

  RateApiService(@NonNull final Context context) {
    mRateApiService =
        RatesNetwork.getRatesRetrofit(context).create(RatesService.class);
  }

  public Call<RateAMWrapper> loadRates() {
    return mRateApiService.loadRates();
  }

  private interface RatesService {

    @GET("/rates")
    Call<RateAMWrapper> loadRates();

  }
}
