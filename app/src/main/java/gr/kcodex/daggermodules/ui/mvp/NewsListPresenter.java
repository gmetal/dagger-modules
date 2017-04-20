package gr.kcodex.daggermodules.ui.mvp;

import java.util.List;

import javax.inject.Inject;

import gr.kcodex.applib.io.HackerNewsServiceClient;
import gr.kcodex.applib.io.model.Item;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsListPresenter extends BasePresenter {

    private HackerNewsServiceClient mHackerNewsServiceClient;

    @Inject
    NewsListPresenter(HackerNewsServiceClient hackerNewsServiceClient, ItemsListView view) {
        super(view);
        mHackerNewsServiceClient = hackerNewsServiceClient;
    }

    public void loadData() {

        if (!isViewAttached()) {
            return;
        }

        mView.showLoading();

        mHackerNewsServiceClient.topStoryIds().enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                if (call.isExecuted()) {
                    List<String> topStoryIds = response.body();
                    for (int i = 0; i < topStoryIds.size(); i++) {
                        mHackerNewsServiceClient.getItem(Integer.parseInt(topStoryIds.get(i))).enqueue(new Callback<Item>() {
                            @Override
                            public void onResponse(Call<Item> call, Response<Item> response) {
                                if (isViewAttached() && call.isExecuted()) {
                                    mView.hideLoading();
                                    mView.addData(response.body());
                                }
                            }

                            @Override
                            public void onFailure(Call<Item> call, Throwable t) {
                                if (isViewAttached()) {
                                    mView.showError(t);
                                }
                            }
                        });

                    }
                }
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                if (isViewAttached()) {
                    mView.showError(t);
                }
            }
        });
    }
}
