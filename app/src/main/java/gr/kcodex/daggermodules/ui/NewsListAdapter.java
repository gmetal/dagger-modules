package gr.kcodex.daggermodules.ui;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import gr.kcodex.daggermodules.R;
import gr.kcodex.daggermodules.io.model.Story;

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.NewsListViewHolder> {

    private final LayoutInflater mLayoutInflater;
    private final View.OnClickListener mOnClickListener;
    private SparseArray<Story> mData;

    @Inject
    public NewsListAdapter(final LayoutInflater layoutInflater, final View.OnClickListener onClickListener) {
        mData = new SparseArray<>();
        mLayoutInflater = layoutInflater;
        mOnClickListener = onClickListener;
    }

    public void setData(List<Story> data) {

        for (int i = 0; i < data.size(); i++) {
            Story story = data.get(i);
            mData.put(story.id, story);
        }
    }

    public void addData(Story story) {
        if (story == null) {
            return;
        }

        boolean newItem = true;
        if (mData.get(story.id, null) != null) {
            newItem = false;
        }

        mData.put(story.id, story);
        int keyIndex = mData.indexOfKey(story.id);

        if (newItem) {
            notifyItemInserted(keyIndex);
        } else {
            notifyItemChanged(keyIndex);
        }

    }

    public boolean isEmpty() {
        return mData.size() == 0;
    }

    @Override
    public NewsListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NewsListViewHolder(mLayoutInflater.inflate(R.layout.cell_newslist_item, parent, false), mOnClickListener);
    }

    @Override
    public void onBindViewHolder(NewsListViewHolder holder, int position) {

        Story story = mData.get(mData.keyAt(position));
        holder.itemTitle.setText(story.title);
        holder.itemText.setText(story.text);

        holder.itemView.setTag(story.id);
    }

    @Override
    public int getItemCount() {
        return mData.size();
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
