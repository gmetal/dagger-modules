package gr.kcodex.daggermodules.ui.mvp;

import gr.kcodex.daggermodules.io.model.Story;

public interface NewsListView {

    void addData(Story story);

    void showLoading();

    void hideLoading();

    void showError(Throwable t);
}

