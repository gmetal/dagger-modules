package gr.kcodex.daggermodules.injection;

import android.content.Context;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import gr.kcodex.applib.io.injection.DependencyProvider;
import gr.kcodex.daggermodules.NewsListApplication;
import gr.kcodex.daggermodules.injection.newsdetail.NewsDetailFragmentSubComponent;
import gr.kcodex.daggermodules.injection.newslist.NewsListActivitySubComponent;

@Module(subcomponents = {NewsListActivitySubComponent.class, NewsDetailFragmentSubComponent.class})
public abstract class AppModule {

    @Provides
    static Context providesContext(NewsListApplication application) {

        return application.getApplicationContext();
    }

    @Binds
    abstract DependencyProvider provideExternalModulesDependecyProvider(NewsListApplication newsListApplication);
}
