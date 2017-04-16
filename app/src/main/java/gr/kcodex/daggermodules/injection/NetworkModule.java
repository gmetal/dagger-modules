package gr.kcodex.daggermodules.injection;

import com.github.aurae.retrofit2.LoganSquareConverterFactory;

import dagger.Module;
import dagger.Provides;
import gr.kcodex.daggermodules.io.HackerNewsService;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

@Module
public class NetworkModule {

    @Provides
    static OkHttpClient providesOkHttp() {

        OkHttpClient okhttp = new OkHttpClient.Builder()
                .build();
        return okhttp;
    }

    @Provides
    static Retrofit providesRetrofit(OkHttpClient okHttpClient) {

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(LoganSquareConverterFactory.create())
                .baseUrl("https://hacker-news.firebaseio.com/")
                .client(okHttpClient)
                .build();

        return retrofit;
    }

    @Provides
    static HackerNewsService providesHackerNewsService(Retrofit retrofit) {

        HackerNewsService hackerNewsService = retrofit.create(HackerNewsService.class);
        return hackerNewsService;
    }
}
