package com.bdswiss.bdswiss.data;

import android.support.annotation.NonNull;
import com.squareup.moshi.Moshi;

class MoshiClient {

  private static Moshi sMoshi;

  @NonNull
  public static Moshi getDefaultMoshiClient() {
    if (sMoshi == null) {
      sMoshi = new Moshi.Builder().build();
    }
    return sMoshi;
  }
}
