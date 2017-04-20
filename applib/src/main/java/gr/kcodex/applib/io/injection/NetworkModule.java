package gr.kcodex.applib.io.injection;

import com.github.aurae.retrofit2.LoganSquareConverterFactory;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import gr.kcodex.applib.io.HackerNewsService;
import gr.kcodex.applib.io.HackerNewsServiceClient;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

@Module
public class NetworkModule {

    @Provides
    static @Named("baseUrl") String providesBaseUrl(HackerNewsServiceClient hackerNewsServiceClient) {
        return hackerNewsServiceClient.getBaseUrl();
    }

    @Provides
    static OkHttpClient provideOkHttpClient(HackerNewsServiceClient hackerNewsServiceClient) {
        return hackerNewsServiceClient.getOkHttpClient();
    }

    @Provides
    static Retrofit providesRetrofit(@Named("baseUrl") String baseUrl, OkHttpClient okHttpClient) {

        return new Retrofit.Builder()
                .addConverterFactory(LoganSquareConverterFactory.create())
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .build();
    }

    @Provides
    static HackerNewsService providesHackerNewsService(Retrofit retrofit) {

        HackerNewsService hackerNewsService = retrofit.create(HackerNewsService.class);
        return hackerNewsService;
    }
}
