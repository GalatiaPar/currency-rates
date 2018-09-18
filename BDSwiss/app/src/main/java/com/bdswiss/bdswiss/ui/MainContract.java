package com.bdswiss.bdswiss.ui;

import android.support.annotation.NonNull;
import com.bdswiss.bdswiss.model.ui.RatesUIM;

public interface MainContract {

  interface View {

    void setUpViews();

    void showAsLoading();

    void stopShowingAsLoading();

    void showRates(@NonNull final RatesUIM currentRatesUIM);

    void showAsErrorLoading();

    void showAsRefreshing();
  }

  interface Presenter {

    void onCreate();

    void onRetryClick();

    void onSwipeToRefresh();

    void onRefresh();

    void onStop();

    void onDestroy();
  }

  interface Coordinator {

    void loadRates(@NonNull final OnRatesCallbacks onRatesCallbacks);

    void cancel();

    interface OnRatesCallbacks {
      void onRatesUpdate(@NonNull final RatesUIM ratesUIM);

      void onErrorLoadingRates();
    }
  }
}
