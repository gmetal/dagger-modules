package gr.kcodex.daggermodules.injection;

import android.app.Activity;
import android.support.v4.app.Fragment;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.android.support.FragmentKey;
import dagger.multibindings.IntoMap;
import gr.kcodex.daggermodules.injection.newsdetail.NewsDetailFragmentSubComponent;
import gr.kcodex.daggermodules.injection.newslist.NewsListActivitySubComponent;
import gr.kcodex.daggermodules.ui.NewsDetailFragment;
import gr.kcodex.daggermodules.ui.NewsListActivity;

@Module
public abstract class BuildersModule {
    @Binds
    @IntoMap
    @ActivityKey(NewsListActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindNewsListActivityInjectorFactory(NewsListActivitySubComponent.Builder builder);

    @Binds
    @IntoMap
    @FragmentKey(NewsDetailFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment> bindNewsDetailFragmentInjectorFactory(NewsDetailFragmentSubComponent.Builder builder);

    // Add more bindings here for other subcomponents
}
