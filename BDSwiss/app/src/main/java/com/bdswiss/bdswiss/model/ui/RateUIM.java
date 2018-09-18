package com.bdswiss.bdswiss.model.ui;

import android.support.annotation.NonNull;
import com.bdswiss.bdswiss.model.domain.RateDM;
import com.google.auto.value.AutoValue;

@AutoValue
public abstract class RateUIM implements Comparable<RateUIM>{

  public static RateUIM create(@NonNull final RateDM rateDM) {
    return new AutoValue_RateUIM(rateDM.getMPrice(), rateDM.getMSymbol());
  }

  public abstract double getPrice();

  public abstract String getSymbol();

  @Override
  public int compareTo(@NonNull final RateUIM rateUIM) {
    @NonNull final String symbolOne = getSymbol();
    @NonNull final String symbolTwo = rateUIM.getSymbol();
    return symbolOne.compareTo(symbolTwo);
  }
}
