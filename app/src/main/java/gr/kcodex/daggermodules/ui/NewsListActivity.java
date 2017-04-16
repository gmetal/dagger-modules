package gr.kcodex.daggermodules.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;
import gr.kcodex.daggermodules.R;
import gr.kcodex.daggermodules.io.model.Story;
import gr.kcodex.daggermodules.ui.mvp.NewsListPresenter;
import gr.kcodex.daggermodules.ui.mvp.NewsListView;

public class NewsListActivity extends AppCompatActivity implements NewsListView, View.OnClickListener {

    @BindView(R.id.news_list)
    RecyclerView mNewsList;

    @BindView(R.id.loading)
    ProgressBar mLoadingBar;

    @BindView(R.id.empty_state_text)
    TextView mEmptyStateText;

    @Inject
    NewsListAdapter mAdapter;

    @Inject
    NewsListPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mNewsList.setLayoutManager(new LinearLayoutManager(this));
        mNewsList.setAdapter(mAdapter);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        mPresenter.loadData();
    }

    @Override
    protected void onStop() {
        super.onStop();

        mPresenter.detachView(this);
    }

    @Override
    public void addData(Story story) {
        if (story != null) {
            mAdapter.addData(story);
        }
    }

    @Override
    public void showLoading() {
        mEmptyStateText.setVisibility(View.GONE);
        mNewsList.setVisibility(View.GONE);
        mLoadingBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        if (mAdapter.isEmpty()) {
            mEmptyStateText.setVisibility(View.VISIBLE);
            mNewsList.setVisibility(View.GONE);
        } else {
            mEmptyStateText.setVisibility(View.GONE);
            mNewsList.setVisibility(View.VISIBLE);
        }
        mLoadingBar.setVisibility(View.GONE);
    }

    @Override
    public void showError(Throwable t) {
        Snackbar.make(mNewsList.getRootView(), t.getMessage(), Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        Snackbar.make(v, "Clicked: " + v.getTag(), Snackbar.LENGTH_SHORT).show();
        Intent detailActivityIntent = new Intent(Intent.ACTION_VIEW);
        detailActivityIntent.addCategory(Intent.CATEGORY_DEFAULT);
        detailActivityIntent.setData(new Uri.Builder()
                .scheme("daggerapp")
                .authority("hackernews")
                .appendPath("item")
                .appendPath(String.valueOf(v.getTag())).build());
        startActivity(detailActivityIntent);
    }
}
