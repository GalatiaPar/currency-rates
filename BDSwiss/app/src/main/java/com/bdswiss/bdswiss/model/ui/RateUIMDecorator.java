package com.bdswiss.bdswiss.model.ui;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import com.bdswiss.bdswiss.R;

public class RateUIMDecorator {

  private final Context mContext;
  private final RateUIM mOldRateUIM;
  private final RateUIM mCurrentRateUIM;

  public RateUIMDecorator(
      @NonNull final Context context,
      @NonNull final RateUIM currentRateUIM,
      @Nullable final RateUIM oldRateUIM) {
    mContext = context;
    mOldRateUIM = oldRateUIM;
    mCurrentRateUIM = currentRateUIM;
  }

  @Nullable
  public Drawable getDrawable() {
    return mOldRateUIM != null
        ? mCurrentRateUIM.getPrice() > mOldRateUIM.getPrice()
            ? ContextCompat.getDrawable(mContext, R.drawable.ic_arrow_drop_up_green_24dp)
            : ContextCompat.getDrawable(mContext, R.drawable.ic_arrow_drop_down_red_24dp)
        : ContextCompat.getDrawable(mContext, R.drawable.ic_arrow_drop_down_red_24dp);
  }

  public int getDrawableVisibility() {
    return mOldRateUIM == null || mCurrentRateUIM.getPrice() == mOldRateUIM.getPrice()
        ? GONE
        : VISIBLE;
  }

  public String getPrice() {
    return String.valueOf(mCurrentRateUIM.getPrice());
  }

  public String getSymbol() {
    return String.valueOf(mCurrentRateUIM.getSymbol());
  }
}
