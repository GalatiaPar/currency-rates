package com.bdswiss.bdswiss.ui;

import android.os.Handler;
import android.support.annotation.NonNull;

public class AutoRefresher {

  private static final int sDEFAULT_REFRESH_RATE_IN_MILLISECONDS = 10000;
  private final Handler mPeriodicExecution = new Handler();
  private final long mRefreshRate;
  private final OnRefreshListener mOnRefreshListener;
  private final Runnable mPeriodicRunnable =
      new Runnable() {
        @Override
        public void run() {
          mOnRefreshListener.onRefresh();
          scheduleNextRefresh ();
        }
      };

  public AutoRefresher(@NonNull final OnRefreshListener onRefreshListener) {
    this(onRefreshListener, sDEFAULT_REFRESH_RATE_IN_MILLISECONDS);
  }

  private AutoRefresher(
      @NonNull final OnRefreshListener onRefreshListener, final int refreshIntervalInMilliseconds) {
    mRefreshRate =
        refreshIntervalInMilliseconds > 0
            ? refreshIntervalInMilliseconds
            : sDEFAULT_REFRESH_RATE_IN_MILLISECONDS;
    mOnRefreshListener = onRefreshListener;
  }

  public void start() {
    mPeriodicExecution.removeCallbacks(mPeriodicRunnable);
    scheduleNextRefresh();
  }

  public void stop() {
    mPeriodicExecution.removeCallbacks(mPeriodicRunnable);
  }

  private void scheduleNextRefresh() {
    mPeriodicExecution.postDelayed(
        mPeriodicRunnable,
        mRefreshRate == 0 ? sDEFAULT_REFRESH_RATE_IN_MILLISECONDS : mRefreshRate);
  }
}
