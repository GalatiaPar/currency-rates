package com.bdswiss.bdswiss.data.rates.datasources;

import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;
import com.bdswiss.bdswiss.model.domain.RateDM;
import java.util.ArrayList;

public interface RateDS {

  interface Remote {

    @WorkerThread
    void loadRates(@NonNull final OnLoadRatesCallbacks onLoadRatesCallbacks);

    @WorkerThread
    void cancel();

    interface OnLoadRatesCallbacks {

      void onSuccessLoadingRates(@NonNull final ArrayList<RateDM> rateDMS);

      void onErrorLoadingRates();
    }
  }

}
