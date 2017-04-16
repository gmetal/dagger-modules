package gr.kcodex.daggermodules.injection.newslist;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import gr.kcodex.daggermodules.ui.NewsListActivity;

@Subcomponent(modules = {NewsListModule.class})
public interface NewsListActivitySubComponent extends AndroidInjector<NewsListActivity> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<NewsListActivity> {

    }
}
