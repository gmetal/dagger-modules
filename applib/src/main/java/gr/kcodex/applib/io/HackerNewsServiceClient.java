package gr.kcodex.applib.io;

import java.util.List;

import javax.inject.Inject;

import gr.kcodex.applib.io.injection.DaggerNetworkComponent;
import gr.kcodex.applib.io.injection.DependencyProvider;
import gr.kcodex.applib.io.injection.NetworkComponent;
import gr.kcodex.applib.io.model.Item;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.http.Path;

public class HackerNewsServiceClient {

    private DependencyProvider mDependencyProvider;
    private NetworkComponent mNetworkComponent;
    @Inject
    HackerNewsService mHackerNewsService;

    @Inject
    public HackerNewsServiceClient(DependencyProvider dependencyProvider) {

        mDependencyProvider = dependencyProvider;

        mNetworkComponent = DaggerNetworkComponent.builder()
                .hackerNewsServiceClient(this)
                .build();
        mNetworkComponent.inject(this);
    }

    public String getBaseUrl() {

        return mDependencyProvider.getBaseUrl();
    }

    public OkHttpClient getOkHttpClient() {

        return mDependencyProvider.getOkHttpClient();

    }

    public Call<List<String>> topStoryIds() {

        return mHackerNewsService.topStoryIds();
    }

    public Call<Item> getItem(@Path("itemId") int itemId) {

        return mHackerNewsService.getItem(itemId);
    }
}
