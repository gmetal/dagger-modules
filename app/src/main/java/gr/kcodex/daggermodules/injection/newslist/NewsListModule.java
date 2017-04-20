package gr.kcodex.daggermodules.injection.newslist;

import android.view.LayoutInflater;
import android.view.View;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import gr.kcodex.daggermodules.ui.NewsListActivity;
import gr.kcodex.daggermodules.ui.mvp.ItemsListView;

@Module
public abstract class NewsListModule {
    @Binds
    abstract View.OnClickListener provideListClickListener(NewsListActivity newsListActivity);

    @Binds
    abstract ItemsListView provideNewsListView(NewsListActivity newsListActivity);

    @Provides
    static LayoutInflater provideLayoutInflater(NewsListActivity newsListActivity) {
        return LayoutInflater.from(newsListActivity);
    }
}
