package gr.kcodex.daggermodules.injection;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;
import gr.kcodex.daggermodules.NewsListApplication;

@Component(modules = {NetworkModule.class,
        AndroidSupportInjectionModule.class,
        AppModule.class,
        BuildersModule.class})
public interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(NewsListApplication application);

        AppComponent build();
    }

    void inject(NewsListApplication application);
}
