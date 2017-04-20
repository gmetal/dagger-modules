package gr.kcodex.daggermodules.injection;

import dagger.Module;
import dagger.Provides;
import gr.kcodex.applib.io.HackerNewsServiceClient;
import gr.kcodex.applib.io.injection.DependencyProvider;
import okhttp3.OkHttpClient;

@Module
public class NetworkModule {

    @Provides
    static OkHttpClient providesOkHttp() {

        OkHttpClient okhttp = new OkHttpClient.Builder()
                .build();
        return okhttp;
    }

    @Provides
    static HackerNewsServiceClient provideHackerNewsServiceClient(DependencyProvider dependencyProvider) {

        return new HackerNewsServiceClient(dependencyProvider);
    }
}
