package gr.kcodex.daggermodules.ui;

import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import gr.kcodex.applib.io.model.Item;
import gr.kcodex.daggermodules.R;

public class NewsListAdapter extends BaseSparseArrayAdapter<NewsListAdapter.NewsListViewHolder> {


    @Inject
    public NewsListAdapter(final LayoutInflater layoutInflater, final View.OnClickListener onClickListener) {
        super(layoutInflater, onClickListener);
    }

    @Override
    public NewsListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new NewsListViewHolder(mLayoutInflater.inflate(R.layout.cell_newslist_item, parent, false), mOnClickListener);
    }

    @Override
    public void onBindViewHolder(NewsListViewHolder holder, int position) {

        Item story = mData.get(mData.keyAt(position));
        holder.itemTitle.setText(story.title);
        @DrawableRes int drawableIconRes = R.drawable.ic_new_releases_white_24dp;
        @ColorRes int colorIconRes = R.color.storyColor;
        if ("ask".equals(story.type)) {
            drawableIconRes = R.drawable.ic_live_help_white_24dp;
            colorIconRes = R.color.askColor;
        } else if ("comment".equals(story.type)) {
            drawableIconRes = R.drawable.ic_comment_white_24dp;
            colorIconRes = R.color.commentColor;
        } else if ("job".equals(story.type)) {
            drawableIconRes = R.drawable.ic_work_white_24dp;
            colorIconRes = R.color.jobColor;
        } else if ("poll".equals(story.type)) {
            drawableIconRes = R.drawable.ic_poll_white_24dp;
            colorIconRes = R.color.pollColor;
        }


        holder.itemIcon.setImageDrawable(ContextCompat.getDrawable(mLayoutInflater.getContext(), drawableIconRes));
        holder.itemIcon.setBackgroundColor(ContextCompat.getColor(mLayoutInflater.getContext(), colorIconRes));

        holder.itemView.setTag(story);
    }

    static class NewsListViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.news_item_icon)
        ImageView itemIcon;
        @BindView(R.id.news_item_title)
        TextView itemTitle;

        public NewsListViewHolder(View itemView, View.OnClickListener onClickListener) {

            super(itemView);

            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(onClickListener);
        }
    }
}
