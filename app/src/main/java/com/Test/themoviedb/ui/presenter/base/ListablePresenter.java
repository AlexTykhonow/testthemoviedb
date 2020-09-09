package com.Test.themoviedb.ui.presenter.base;

import com.Test.themoviedb.ui.contract.ILoadDataView;

/**
 * Class used
 */
public abstract class ListablePresenter<T> extends Presenter<T> {

    public ListablePresenter(ILoadDataView<T> view) {
        super(view);
    }

    public abstract void getMoreData(int page);

    public abstract void refresh();
}
