package com.bdswiss.bdswiss.ui;

import android.arch.lifecycle.Lifecycle.Event;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;
import android.support.annotation.NonNull;
import com.bdswiss.bdswiss.model.ui.RatesUIM;
import com.bdswiss.bdswiss.ui.MainContract.Coordinator;
import com.bdswiss.bdswiss.ui.MainContract.Coordinator.OnRatesCallbacks;
import com.bdswiss.bdswiss.ui.MainContract.Presenter;
import com.bdswiss.bdswiss.ui.MainContract.View;

class MainPresenter implements Presenter, OnRatesCallbacks, LifecycleObserver {

  private View mView;
  private boolean mIsViewEmpty = true;
  private final Coordinator mCoordinator;

  MainPresenter(@NonNull final View view, @NonNull final Coordinator coordinator) {
    mView = view;
    mCoordinator = coordinator;
    if (view instanceof LifecycleOwner) {
      ((LifecycleOwner) view).getLifecycle().addObserver(this);
    }
  }

  @Override
  @OnLifecycleEvent(Event.ON_START)
  public void onCreate() {
    mView.setUpViews();
    mCoordinator.loadRates(this);
  }

  @Override
  public void onRetryClick() {
    refresh();
  }

  @Override
  public void onSwipeToRefresh() {
    mView.showAsRefreshing();
    refresh();
  }

  @Override
  public void onRefresh() {
    refresh();
  }

  private void refresh() {
    if (mIsViewEmpty) {
      mView.showAsLoading();
    }
    mCoordinator.loadRates(this);
  }

  @Override
  public void onRatesUpdate(@NonNull final RatesUIM ratesUIM) {
    if (mView != null) {
      mView.stopShowingAsLoading();
      mView.showRates(ratesUIM);
      mIsViewEmpty = false;
    }
  }

  @Override
  public void onErrorLoadingRates() {
    if (mView != null) {
      mView.stopShowingAsLoading();
      if (mIsViewEmpty) {
        mView.showAsErrorLoading();
      }
    }
  }

  @Override
  @OnLifecycleEvent(Event.ON_STOP)
  public void onStop() {
    mCoordinator.cancel();
  }

  @Override
  @OnLifecycleEvent(Event.ON_DESTROY)
  public void onDestroy() {
    mView = null;
  }
}
