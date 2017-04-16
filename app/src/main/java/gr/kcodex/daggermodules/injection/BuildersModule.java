package gr.kcodex.daggermodules.injection;

import android.app.Activity;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;
import gr.kcodex.daggermodules.injection.newslist.NewsListActivitySubComponent;
import gr.kcodex.daggermodules.ui.NewsListActivity;

@Module
public abstract class BuildersModule {
    @Binds
    @IntoMap
    @ActivityKey(NewsListActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindNewsListActivityInjectorFactory(NewsListActivitySubComponent.Builder builder);

    // Add more bindings here for other subcomponents
}
