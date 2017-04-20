package gr.kcodex.daggermodules.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.AndroidSupportInjection;
import gr.kcodex.applib.io.model.Item;
import gr.kcodex.daggermodules.R;
import gr.kcodex.daggermodules.ui.mvp.CommentsListPresenter;
import gr.kcodex.daggermodules.ui.mvp.CommentsListView;

public class NewsDetailFragment extends Fragment implements View.OnClickListener, CommentsListView {

    @Inject
    CommentsListPresenter mPresenter;

    @Inject
    CommentsListAdapter mCommentsListAdapter;

    @BindView(R.id.news_detail_title)
    TextView mNewsDetailTitle;

    @BindView(R.id.news_detail_text)
    TextView mNewsDetailText;

    @BindView(R.id.news_detail_comment_list)
    RecyclerView mNewsCommentList;

    @BindView(R.id.comment_list_progress_bar)
    ProgressBar mCommentsLoading;

    @BindView(R.id.comment_list_empty_state)
    TextView mCommentsEmptyState;

    private Item mItem;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        AndroidSupportInjection.inject(this);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, final Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_news_detail, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        mItem = getActivity().getIntent().getParcelableExtra(ParamsContract.PARAM_ITEM);

        mNewsDetailTitle.setText(mItem.title);
        if (TextUtils.isEmpty(mItem.text)) {
            mNewsDetailText.setVisibility(View.GONE);
        } else {
            mNewsDetailText.setText(mItem.text);
        }

        mNewsCommentList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mNewsCommentList.setAdapter(mCommentsListAdapter);
        if (mItem.kids.length > 0) {
            mPresenter.loadData(mItem);
        } else {
            mNewsCommentList.setVisibility(View.GONE);
            mCommentsLoading.setVisibility(View.GONE);
            mCommentsEmptyState.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void addData(Item item) {

        if (item != null) {
            mCommentsListAdapter.addData(item);
        }
    }

    @Override
    public void showLoading() {

        mNewsCommentList.setVisibility(View.GONE);
        mCommentsLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {

        mNewsCommentList.setVisibility(View.VISIBLE);
        mCommentsLoading.setVisibility(View.GONE);
    }

    @Override
    public void showError(Throwable t) {

    }
}
