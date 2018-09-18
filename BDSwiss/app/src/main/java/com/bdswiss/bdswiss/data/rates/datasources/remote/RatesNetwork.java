package com.bdswiss.bdswiss.data.rates.datasources.remote;

import android.content.Context;
import android.support.annotation.NonNull;
import com.bdswiss.bdswiss.data.CommonNetwork;
import com.squareup.moshi.Moshi;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

class RatesNetwork {

  private static Moshi sRatesMoshi;
  private static Retrofit sRatesRetrofit;

  @NonNull
  public static Retrofit getRatesRetrofit(@NonNull final Context context) {
    if (sRatesRetrofit == null) {
      sRatesRetrofit =
          CommonNetwork.getRetrofit(context)
              .newBuilder()
              .addConverterFactory(MoshiConverterFactory.create(getRatesMoshi()))
              .build();
    }
    return sRatesRetrofit;
  }

  @NonNull
  private static Moshi getRatesMoshi() {
    if (sRatesMoshi == null) {
      sRatesMoshi =
          CommonNetwork.getMoshi().newBuilder().add(new RatesJsonAdapter()).build();
    }
    return sRatesMoshi;
  }
}
