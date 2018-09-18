package com.bdswiss.bdswiss.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.View.OnClickListener;
import com.bdswiss.bdswiss.R;
import com.bdswiss.bdswiss.databinding.MainActivityBinding;
import com.bdswiss.bdswiss.model.ui.RatesUIM;
import com.bdswiss.bdswiss.ui.MainContract.Presenter;
import com.bdswiss.bdswiss.ui.base.DaggerAppCompatActivityBase;
import javax.inject.Inject;

public class MainActivity extends DaggerAppCompatActivityBase implements MainContract.View {

  @Inject Presenter mPresenter;
  private RatesRVAdapter mAdapter;
  private AutoRefresher autoRefresher;
  private MainActivityBinding mViewBinding;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mViewBinding = DataBindingUtil.setContentView(this, R.layout.main_activity);
  }

  @Override
  public void setUpViews() {
    final int[] colors = {
      R.color.colorPrimaryDark, R.color.colorPrimary, R.color.colorPrimaryLight
    };
    mViewBinding.swipeRefreshLayout.setColorSchemeResources(colors);
    mViewBinding.swipeRefreshLayout.setOnRefreshListener(
        new SwipeRefreshLayout.OnRefreshListener() {
          @Override
          public void onRefresh() {
            mPresenter.onSwipeToRefresh();
          }
        });
    mViewBinding.recyclerView.setHasFixedSize(true);
    mViewBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
    autoRefresher =
        new AutoRefresher(
            new OnRefreshListener() {
              @Override
              public void onRefresh() {
                mPresenter.onRefresh();
              }
            });
    autoRefresher.start();
    mViewBinding.error.setOnClickListener(
        new OnClickListener() {
          @Override
          public void onClick(View v) {
            mPresenter.onRetryClick();
          }
        });
  }

  @Override
  public void showAsRefreshing() {
    mViewBinding.swipeRefreshLayout.setRefreshing(true);
  }

  @Override
  public void showAsLoading() {
    mViewBinding.error.setVisibility(View.GONE);
    mViewBinding.recyclerView.setVisibility(View.GONE);
    mViewBinding.progressBar.setVisibility(View.VISIBLE);
  }

  @Override
  public void stopShowingAsLoading() {
    mViewBinding.progressBar.setVisibility(View.GONE);
    mViewBinding.swipeRefreshLayout.setRefreshing(false);
  }

  @Override
  public void showRates(@NonNull final RatesUIM ratesUIM) {
    mViewBinding.swipeRefreshLayout.setEnabled(true);
    mViewBinding.recyclerView.setVisibility(View.VISIBLE);
    if (mViewBinding.recyclerView.getAdapter() == null) {
      mAdapter = new RatesRVAdapter(ratesUIM.getRateUIMs());
      mViewBinding.recyclerView.setAdapter(mAdapter);
    } else {
      mAdapter.updateData(ratesUIM.getRateUIMs());
    }
  }

  @Override
  public void showAsErrorLoading() {
    mViewBinding.swipeRefreshLayout.setEnabled(false);
    mViewBinding.error.setVisibility(View.VISIBLE);
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    autoRefresher.stop();
  }
}
