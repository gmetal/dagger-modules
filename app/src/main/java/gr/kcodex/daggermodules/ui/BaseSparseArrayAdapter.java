package gr.kcodex.daggermodules.ui;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;

import java.util.List;

import gr.kcodex.applib.io.model.Item;

public abstract class BaseSparseArrayAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    protected final LayoutInflater mLayoutInflater;
    protected final View.OnClickListener mOnClickListener;
    protected SparseArray<Item> mData;

    public BaseSparseArrayAdapter(final LayoutInflater layoutInflater, final View.OnClickListener onClickListener) {
        mLayoutInflater = layoutInflater;
        mOnClickListener = onClickListener;
        mData = new SparseArray<>();
    }

    public void setData(List<Item> data) {

        for (int i = 0; i < data.size(); i++) {
            Item item = data.get(i);
            mData.put(item.id, item);
        }

        notifyDataSetChanged();
    }

    public void addData(Item story) {

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

    public SparseArray<Item> getData() {

        return mData;
    }

    public boolean isEmpty() {

        return mData.size() == 0;
    }

    @Override
    public int getItemCount() {

        return mData.size();
    }
}
