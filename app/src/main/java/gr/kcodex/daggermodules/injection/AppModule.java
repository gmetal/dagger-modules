package gr.kcodex.daggermodules.injection;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import gr.kcodex.daggermodules.NewsListApplication;
import gr.kcodex.daggermodules.injection.newslist.NewsListActivitySubComponent;

@Module(subcomponents = {NewsListActivitySubComponent.class})
public class AppModule {
    @Provides
    Context providesContext(NewsListApplication application) {
        return application.getApplicationContext();
    }
}
