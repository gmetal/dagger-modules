package gr.kcodex.daggermodules.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;
import gr.kcodex.applib.io.model.Item;
import gr.kcodex.daggermodules.R;
import gr.kcodex.daggermodules.ui.mvp.ItemsListView;
import gr.kcodex.daggermodules.ui.mvp.NewsListPresenter;

public class NewsListActivity extends AppCompatActivity implements ItemsListView, View.OnClickListener {

    private static final String KEY_DATA = "activity.data";

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
    protected void onRestoreInstanceState(final Bundle savedInstanceState) {

        super.onRestoreInstanceState(savedInstanceState);
        mAdapter.setData(savedInstanceState.<Item>getParcelableArrayList(KEY_DATA));
    }

    @Override
    protected void onSaveInstanceState(final Bundle outState) {

        super.onSaveInstanceState(outState);

        ArrayList<Item> items = new ArrayList<>();
        final SparseArray<Item> data = mAdapter.getData();
        for (int i = 0; i < data.size(); i++) {
            items.add(data.get(data.keyAt(i)));
        }
        outState.putParcelableArrayList(KEY_DATA, items);
    }

    @Override
    protected void onStop() {

        super.onStop();

        mPresenter.detachView(this);
    }

    @Override
    public void addData(Item item) {

        if (item != null) {
            mAdapter.addData(item);
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

        Item item = (Item) v.getTag();
        Snackbar.make(v, "Clicked: " + item.id, Snackbar.LENGTH_SHORT).show();

        Intent detailActivityIntent = new Intent(Intent.ACTION_VIEW);
        detailActivityIntent.addCategory(Intent.CATEGORY_DEFAULT);
        detailActivityIntent.setData(new Uri.Builder()
                .scheme("daggerapp")
                .authority("hackernews")
                .appendPath("item")
                .appendPath(String.valueOf(v.getTag())).build());
        detailActivityIntent.putExtra(ParamsContract.PARAM_ITEM, item);

        startActivity(detailActivityIntent);
    }
}
