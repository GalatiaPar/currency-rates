package com.bdswiss.bdswiss.ui;

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.bdswiss.bdswiss.databinding.RateVhBinding;
import com.bdswiss.bdswiss.model.ui.RateUIM;

public class RatesRVAdapter extends RecyclerView.Adapter<RateVH> {

  private ArrayMap<String, RateUIM> mOldRates;
  private ArrayMap<String, RateUIM> mCurrentRates;

  public RatesRVAdapter(@NonNull final ArrayMap<String, RateUIM> rates) {
    mCurrentRates = rates;
  }

  public void updateData(@NonNull final ArrayMap<String, RateUIM> rates) {
    DiffUtil.calculateDiff(
        new RatesDiffCallback(mCurrentRates, rates))
        .dispatchUpdatesTo(this);
    mOldRates = mCurrentRates;
    mCurrentRates = rates;
  }

  @NonNull
  @Override
  public RateVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    return new RateVH(
        RateVhBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
  }

  @Override
  public void onBindViewHolder(@NonNull RateVH holder, int position) {
    final RateUIM currentRateUIM = mCurrentRates.valueAt(position);
    holder.onBind(currentRateUIM, mOldRates != null ? mOldRates.get(currentRateUIM.getSymbol()) : null);
  }

  @Override
  public int getItemCount() {
    return mCurrentRates.size();
  }

}
