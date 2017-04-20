package gr.kcodex.daggermodules.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        holder.itemText.setText(story.text);

        holder.itemView.setTag(story);
    }

    static class NewsListViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.news_item_title)
        TextView itemTitle;

        @BindView(R.id.news_item_text)
        TextView itemText;

        public NewsListViewHolder(View itemView, View.OnClickListener onClickListener) {

            super(itemView);

            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(onClickListener);
        }
    }
}
