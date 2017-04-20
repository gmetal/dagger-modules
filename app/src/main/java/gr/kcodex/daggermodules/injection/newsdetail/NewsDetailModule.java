package gr.kcodex.daggermodules.injection.newsdetail;

import android.view.LayoutInflater;
import android.view.View;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import gr.kcodex.daggermodules.ui.NewsDetailFragment;
import gr.kcodex.daggermodules.ui.mvp.CommentsListView;

@Module
public abstract class NewsDetailModule {

    @Binds
    abstract View.OnClickListener provideListClickListener(NewsDetailFragment newsDetailFragment);

    @Binds
    abstract CommentsListView provideCommentsListView(NewsDetailFragment newsDetailFragment);

    @Provides
    static LayoutInflater provideLayoutInflater(NewsDetailFragment newsDetailFragment) {
        return LayoutInflater.from(newsDetailFragment.getActivity());
    }
}
