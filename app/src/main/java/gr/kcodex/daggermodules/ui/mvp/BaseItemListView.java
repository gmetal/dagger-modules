package gr.kcodex.daggermodules.ui.mvp;

import gr.kcodex.applib.io.model.Item;

public interface BaseItemListView {

    void addData(Item item);

    void showLoading();

    void hideLoading();

    void showError(Throwable t);
}
