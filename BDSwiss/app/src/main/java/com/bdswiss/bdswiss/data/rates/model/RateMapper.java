package com.bdswiss.bdswiss.data.rates.model;


import static com.bdswiss.bdswiss.util.StringUtils.makeNonNull;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.bdswiss.bdswiss.model.domain.RateDM;
import java.util.ArrayList;

public class RateMapper {

  @NonNull
  public static ArrayList<RateDM> mapToDM(
      @NonNull final RateAMWrapper rateAMWrapper) {
    final ArrayList<RateAM> rates = rateAMWrapper.getMRatesAM();
    RateAM rateAM;
    RateDM rateDM;
    final int size = rates.size();
    final ArrayList<RateDM> currentRateDMs = new ArrayList<>(size);
    for (int i = 0; i < size; i++) {
      rateAM = rates.get(i);
      rateDM = mapToDM(rateAM);
      currentRateDMs.add(rateDM);
    }
    return currentRateDMs;
  }

  @NonNull
  private static RateDM mapToDM(@Nullable final RateAM rateAM) {
    return new RateDM(
        rateAM.getMPrice(),
        makeNonNull(rateAM.getMSymbol()));
  }

}
