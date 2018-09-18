package com.bdswiss.bdswiss.data.rates.repository;

import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;
import com.bdswiss.bdswiss.model.domain.RateDM;
import java.util.ArrayList;

public interface RatesRepository {

  @WorkerThread
  void loadRates(@NonNull final OnLoadRateCallbacks onLoadRateCallbacks);

  @WorkerThread
  void cancel();

  interface OnLoadRateCallbacks {
    void onSuccessLoadingRates(@NonNull final ArrayList<RateDM> rateDM);

    void onErrorLoadingRates();
  }
}
