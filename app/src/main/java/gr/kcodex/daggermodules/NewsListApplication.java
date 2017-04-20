package gr.kcodex.daggermodules;

import android.app.Activity;
import android.app.Application;
import android.support.v4.app.Fragment;

import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasDispatchingActivityInjector;
import dagger.android.support.HasDispatchingSupportFragmentInjector;
import gr.kcodex.applib.io.injection.DependencyProvider;
import gr.kcodex.daggermodules.injection.DaggerAppComponent;
import okhttp3.OkHttpClient;

public class NewsListApplication extends Application implements HasDispatchingActivityInjector, HasDispatchingSupportFragmentInjector, DependencyProvider {

    @Inject
    DispatchingAndroidInjector<Activity> mActivityDispatchingAndroidInjector;
    @Inject
    DispatchingAndroidInjector<Fragment> mFragmentDispatchingAndroidInjector;
    @Inject
    OkHttpClient mOkHttpClient;

    @Override
    public void onCreate() {

        super.onCreate();

        DaggerAppComponent
                .builder()
                .application(this)
                .build()
                .inject(this);
    }

    public DispatchingAndroidInjector<Activity> activityInjector() {

        return mActivityDispatchingAndroidInjector;
    }

    @Override
    public DispatchingAndroidInjector<Fragment> supportFragmentInjector() {

        return mFragmentDispatchingAndroidInjector;
    }

    @Override
    public String getBaseUrl() {

        return getResources().getString(R.string.base_url);
    }

    @Override
    public OkHttpClient getOkHttpClient() {

        return mOkHttpClient;
    }
}
