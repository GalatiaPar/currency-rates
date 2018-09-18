package com.bdswiss.bdswiss.ui;

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.util.ArrayMap;
import com.bdswiss.bdswiss.model.ui.RateUIM;

class RatesDiffCallback extends DiffUtil.Callback {

  private final ArrayMap<String, RateUIM> mOldRates;
  private final ArrayMap<String, RateUIM> mNewRates;

  RatesDiffCallback(
      @NonNull final ArrayMap<String, RateUIM> oldRates,
      @NonNull final ArrayMap<String, RateUIM> newRates) {
    mOldRates = oldRates;
    mNewRates = newRates;
  }

  @Override
  public int getOldListSize() {
    return mOldRates.size();
  }

  @Override
  public int getNewListSize() {
    return mNewRates.size();
  }

  @Override
  public boolean areItemsTheSame(final int oldItemPosition, final int newItemPosition) {
    return mOldRates.valueAt(oldItemPosition).getPrice()
        == (mNewRates.valueAt(newItemPosition).getPrice());
  }

  @Override
  public boolean areContentsTheSame(final int oldItemPosition, final int newItemPosition) {
    return mOldRates.valueAt(oldItemPosition).equals(mNewRates.valueAt(newItemPosition));
  }
}
