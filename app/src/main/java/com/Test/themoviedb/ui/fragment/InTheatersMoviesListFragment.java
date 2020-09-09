package com.Test.themoviedb.ui.fragment;

import com.Test.themoviedb.ui.fragment.common.MovieListableFragment;
import com.Test.themoviedb.ui.presenter.InTheatersMoviesListPresenter;
import com.Test.themoviedb.ui.presenter.base.IPresenter;

/**
 * Fragment representing the movies list in theaters
 */
public class InTheatersMoviesListFragment extends MovieListableFragment {

    @Override
    protected IPresenter createPresenter() {
        return new InTheatersMoviesListPresenter(this);
    }
}
