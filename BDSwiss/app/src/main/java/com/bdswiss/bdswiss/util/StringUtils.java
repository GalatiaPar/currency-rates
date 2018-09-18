package com.bdswiss.bdswiss.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public final class StringUtils {

  private StringUtils() {
    throw new AssertionError();
  }

  public static boolean isEmptyOrNull(@Nullable final String string) {
    return string == null || string.isEmpty();
  }

  @NonNull
  public static String makeNonNull(@Nullable final String string) {
    return string == null ? "" : string.trim();
  }

  @NonNull
  public static int[] makeNonNull(@Nullable final int[] array) {
    return array == null ? new int[0] : array;
  }

  public static boolean isNumber(@Nullable final String string) {
    if (string == null || string.isEmpty()) {
      return false;
    }
    for (char c : string.toCharArray()) {
      if (c < '0' || c > '9') {
        return false;
      }
    }
    return true;
  }
}
