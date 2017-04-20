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

public class CommentsListAdapter extends BaseSparseArrayAdapter<CommentsListAdapter.CommentViewHolder> {

    @Inject
    public CommentsListAdapter(final LayoutInflater layoutInflater, final View.OnClickListener onClickListener) {
        super(layoutInflater, onClickListener);
    }

    @Override
    public CommentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CommentViewHolder(mLayoutInflater.inflate(R.layout.cell_comment, parent, false));
    }

    @Override
    public void onBindViewHolder(CommentViewHolder holder, int position) {

        Item item = mData.get(mData.keyAt(position));
        holder.commentTitle.setText(item.text);
    }

    static class CommentViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.comment_title)
        TextView commentTitle;

        public CommentViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
