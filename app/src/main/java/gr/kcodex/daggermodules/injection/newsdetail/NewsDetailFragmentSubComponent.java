package gr.kcodex.daggermodules.injection.newsdetail;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import gr.kcodex.daggermodules.ui.NewsDetailFragment;

@Subcomponent(modules = {NewsDetailModule.class})
public interface NewsDetailFragmentSubComponent extends AndroidInjector<NewsDetailFragment> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<NewsDetailFragment> {

    }
}
