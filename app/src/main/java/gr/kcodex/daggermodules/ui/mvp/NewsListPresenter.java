package gr.kcodex.daggermodules.ui.mvp;

import java.util.List;

import javax.inject.Inject;

import gr.kcodex.daggermodules.io.HackerNewsService;
import gr.kcodex.daggermodules.io.model.Story;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsListPresenter {

    private HackerNewsService mHackerNewsService;
    private NewsListView mView;

    @Inject
    NewsListPresenter(HackerNewsService hackerNewsService, NewsListView view) {
        mHackerNewsService = hackerNewsService;
        mView = view;
    }

    public void detachView(NewsListView view) {

        if (mView == view) {
            mView = null;
        }
    }

    public boolean isViewAttached() {

        return mView != null;
    }

    public void loadData() {

        if (!isViewAttached()) {
            return;
        }

        mView.showLoading();

        mHackerNewsService.topStoryIds().enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                if (call.isExecuted()) {
                    List<String> topStoryIds = response.body();
                    for (int i = 0; i < topStoryIds.size(); i++) {
                        mHackerNewsService.getStory(topStoryIds.get(i)).enqueue(new Callback<Story>() {
                            @Override
                            public void onResponse(Call<Story> call, Response<Story> response) {
                                if (isViewAttached() && call.isExecuted()) {
                                    mView.hideLoading();
                                    mView.addData(response.body());
                                }
                            }

                            @Override
                            public void onFailure(Call<Story> call, Throwable t) {
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
