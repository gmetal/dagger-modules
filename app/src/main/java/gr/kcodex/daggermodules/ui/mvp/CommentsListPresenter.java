package gr.kcodex.daggermodules.ui.mvp;

import javax.inject.Inject;

import gr.kcodex.applib.io.HackerNewsServiceClient;
import gr.kcodex.applib.io.model.Item;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentsListPresenter extends BasePresenter {

    private HackerNewsServiceClient mHackerNewsServiceClient;

    @Inject
    public CommentsListPresenter(HackerNewsServiceClient hackerNewsServiceClient, CommentsListView view) {

        super(view);
        mHackerNewsServiceClient = hackerNewsServiceClient;
    }

    public void loadData(Item item) {

        if (!isViewAttached()) {
            return;
        }

        mView.showLoading();

        for (int i = 0; i < item.kids.length; i++) {
            mHackerNewsServiceClient.getItem(item.kids[i]).enqueue(new Callback<Item>() {

                @Override
                public void onResponse(final Call<Item> call, final Response<Item> response) {

                    if (!isViewAttached()) {
                        return;
                    }

                    if (call.isExecuted()) {
                        mView.hideLoading();

                        mView.addData(response.body());
                    }
                }

                @Override
                public void onFailure(final Call<Item> call, final Throwable t) {

                    if (!isViewAttached()) {
                        return;
                    }

                    mView.hideLoading();

                    if (call.isExecuted()) {
                        mView.showError(t);
                    }
                }
            });
        }
    }
}
