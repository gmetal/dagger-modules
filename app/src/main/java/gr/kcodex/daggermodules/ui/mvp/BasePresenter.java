package gr.kcodex.daggermodules.ui.mvp;

public class BasePresenter {

    protected BaseItemListView mView;

    public BasePresenter(BaseItemListView view) {
        mView = view;
    }

    public boolean isViewAttached() {
        return mView != null;
    }

    public void detachView(BaseItemListView view) {
        if (mView == view) {
            mView = null;
        }
    }
}
