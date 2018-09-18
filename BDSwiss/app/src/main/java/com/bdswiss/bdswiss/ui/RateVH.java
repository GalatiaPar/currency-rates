package com.bdswiss.bdswiss.ui;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import com.bdswiss.bdswiss.databinding.RateVhBinding;
import com.bdswiss.bdswiss.model.ui.RateUIM;
import com.bdswiss.bdswiss.model.ui.RateUIMDecorator;

public class RateVH extends RecyclerView.ViewHolder {

  private final RateVhBinding mViewBinding;

  public RateVH(RateVhBinding viewBinding) {
    super(viewBinding.getRoot());
    mViewBinding = viewBinding;
  }

  public void onBind(@NonNull final RateUIM currentRateUIM, @Nullable final RateUIM oldRateUIM) {
    mViewBinding.setRateUIMDecorator(
        new RateUIMDecorator(mViewBinding.getRoot().getContext(), currentRateUIM, oldRateUIM));
    mViewBinding.executePendingBindings();
  }
}
