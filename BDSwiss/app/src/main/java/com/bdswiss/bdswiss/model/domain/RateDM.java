package com.bdswiss.bdswiss.model.domain;

import android.support.annotation.NonNull;
import lombok.Getter;

@Getter
public class RateDM {

  private double mPrice;
  private String mSymbol;

  public RateDM(final double price, @NonNull final String symbol) {
    mPrice = price;
    mSymbol = symbol;
  }
}
