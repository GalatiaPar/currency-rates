package com.bdswiss.bdswiss.data.rates.model;

import android.support.annotation.NonNull;
import java.util.ArrayList;
import lombok.Getter;

@Getter
public class RateAMWrapper {

  private final ArrayList<RateAM> mRatesAM;

  public RateAMWrapper(@NonNull final ArrayList<RateAM> ratesAM) {
    mRatesAM = ratesAM;
  }
}
