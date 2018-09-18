package com.bdswiss.bdswiss.ui;

import android.os.Handler;
import com.bdswiss.bdswiss.data.rates.repository.RatesRepository;
import com.bdswiss.bdswiss.data.rates.repository.RatesRepositoryModule;
import com.bdswiss.bdswiss.ui.MainContract.Coordinator;
import com.bdswiss.bdswiss.ui.MainContract.View;
import com.bdswiss.bdswiss.util.ExecutorsModule;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import java.util.concurrent.Executor;

@Module(
    includes = {
        RatesRepositoryModule.class,
        ExecutorsModule.class
    }
)
public abstract class MainModule {

  @Provides
  static MainContract.Presenter providePresenter(View view, Coordinator coordinator) {
    return new MainPresenter(view, coordinator);
  }

  @Provides
  static MainContract.Coordinator provideCoordinator(
      RatesRepository ratesRepository,
      Handler mainThreadHandler,
      Executor backgroundExecutor) {
    return new MainCoordinator(
        ratesRepository,
        mainThreadHandler,
        backgroundExecutor);
  }

  @Binds
  abstract MainContract.View bindView(MainActivity mainActivity);
}
