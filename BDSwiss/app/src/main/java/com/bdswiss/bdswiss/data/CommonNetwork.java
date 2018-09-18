package com.bdswiss.bdswiss.data;

import android.content.Context;
import android.support.annotation.NonNull;
import com.squareup.moshi.Moshi;
import java.util.concurrent.Executors;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

public class CommonNetwork {

  private static final String sBASE_URL = "https://mt4-api-staging.herokuapp.com/";

  private static Moshi sMoshi;
  private static Retrofit sRetrofit;
  private static OkHttpClient sOkHttp;

  @NonNull
  private static OkHttpClient getOkHttp(@NonNull final Context context) {
    if (sOkHttp == null) {
      final int cacheSize = 15 * 1024 * 1024;
      final Cache cache = new Cache(context.getCacheDir(), cacheSize);
      sOkHttp = new OkHttpClient.Builder().cache(cache).build();
    }
    return sOkHttp;
  }

  @NonNull
  public static Retrofit getRetrofit(@NonNull final Context context) {
    if (sRetrofit == null) {
      sRetrofit =
          new Retrofit.Builder()
              .client(getOkHttp(context))
              .baseUrl(sBASE_URL)
              .callbackExecutor(Executors.newSingleThreadExecutor())
              .build();
    }
    return sRetrofit;
  }

  @NonNull
  public static Moshi getMoshi() {
    if (sMoshi == null) {
      sMoshi = new Moshi.Builder().build();
    }
    return sMoshi;
  }
}
