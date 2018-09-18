package com.bdswiss.bdswiss.model.ui;

import android.support.annotation.NonNull;
import android.util.ArrayMap;
import com.bdswiss.bdswiss.model.domain.RateDM;
import com.google.auto.value.AutoValue;
import java.util.ArrayList;
import java.util.Collections;

@AutoValue
public abstract class RatesUIM {

  public static RatesUIM create(@NonNull ArrayList<RateDM> ratesDM) {
    return new AutoValue_RatesUIM(mapToUIM(ratesDM));
  }

  private static ArrayMap<String, RateUIM> mapToUIM(@NonNull final ArrayList<RateDM> ratesDM) {
    final int size = ratesDM.size();
    final ArrayList<RateUIM> rateUIMsArrayList = new ArrayList<>(size);
    RateUIM rateUIM;
    for (int i = 0; i < size; i++) {
      rateUIM = RateUIM.create(ratesDM.get(i));
      rateUIMsArrayList.add(rateUIM);
    }
    Collections.sort(rateUIMsArrayList);
    final ArrayMap<String, RateUIM> rateUIMs = new ArrayMap<>(size);

    for (int i = 0; i < size; i++) {
      rateUIM = rateUIMsArrayList.get(i);
      rateUIMs.put(rateUIM.getSymbol(), rateUIM);
    }

    return rateUIMs;
  }

  public abstract ArrayMap<String, RateUIM> getRateUIMs();
}
