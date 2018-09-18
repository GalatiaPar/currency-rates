package com.bdswiss.bdswiss.ui;

import android.os.Handler;
import android.support.annotation.NonNull;
import com.bdswiss.bdswiss.data.rates.repository.RatesRepository;
import com.bdswiss.bdswiss.data.rates.repository.RatesRepository.OnLoadRateCallbacks;
import com.bdswiss.bdswiss.model.domain.RateDM;
import com.bdswiss.bdswiss.model.ui.RatesUIM;
import java.util.ArrayList;
import java.util.concurrent.Executor;

public class MainCoordinator implements MainContract.Coordinator {

  private final Handler mMainThreadHandler;
  private final Executor mBackgroundExecutor;
  private final RatesRepository mRatesRepository;

  MainCoordinator(
      RatesRepository repository, Handler mainThreadHandler, Executor backgroundExecutor) {
    mRatesRepository = repository;
    mMainThreadHandler = mainThreadHandler;
    mBackgroundExecutor = backgroundExecutor;
  }

  @Override
  public void loadRates(@NonNull final OnRatesCallbacks onRatesCallbacks) {
    mBackgroundExecutor.execute(
        new Runnable() {
          @Override
          public void run() {
            mRatesRepository.loadRates(
                new OnLoadRateCallbacks() {
                  @Override
                  public void onSuccessLoadingRates(@NonNull final ArrayList<RateDM> rateDM) {
                    final RatesUIM ratesUIM = RatesUIM.create(rateDM);
                    mMainThreadHandler.post(
                        new Runnable() {
                          @Override
                          public void run() {
                            onRatesCallbacks.onRatesUpdate(ratesUIM);
                          }
                        });
                  }

                  @Override
                  public void onErrorLoadingRates() {
                    mMainThreadHandler.post(
                        new Runnable() {
                          @Override
                          public void run() {
                            onRatesCallbacks.onErrorLoadingRates();
                          }
                        });
                  }
                });
          }
        });
  }

  @Override
  public void cancel() {
    mRatesRepository.cancel();
  }
}
