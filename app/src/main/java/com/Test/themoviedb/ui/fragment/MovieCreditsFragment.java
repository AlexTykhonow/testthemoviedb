package com.Test.themoviedb.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.Test.themoviedb.R;
import com.Test.themoviedb.Utils;
import com.Test.themoviedb.model.MovieCredits;
import com.Test.themoviedb.ui.activity.MovieCreditsActivity;
import com.Test.themoviedb.ui.fragment.base.LoadDataFragment;
import com.Test.themoviedb.ui.presenter.MovieCreditsPresenter;
import com.Test.themoviedb.ui.presenter.base.IPresenter;

public class MovieCreditsFragment extends LoadDataFragment<MovieCredits> {

    private MovieCreditsPresenter creditsPresenter;
    private ListView actors_list;
    private ListView crew_list;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View viewContainer = super.onCreateView(inflater, container, savedInstanceState);

        this.creditsPresenter = (MovieCreditsPresenter) this.presenter;

        actors_list = (ListView) this.mainView.findViewById(R.id.actors_list);
        crew_list = (ListView) this.mainView.findViewById(R.id.crew_list);

        if (!getArguments().isEmpty()) {

        }

        return viewContainer;
    }

    @Override
    protected int getLayout() {
        return R.layout.movie_credits;
    }

    @Override
    public void setData(MovieCredits data) {
        this.showResults();

        String[] actors = new String[data.getActors().length];
        for (int i = 0; i < data.getActors().length; i++) {
            actors[i] = data.getActors()[i].getName();
        }

        String[] crew = new String[data.getCrew().length];
        for (int i = 0; i < data.getCrew().length; i++) {
            crew[i] = data.getCrew()[i].getName();
        }

        actors_list.setAdapter(
                new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,
                                         actors));
        crew_list.setAdapter(
                new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, crew));

        Utils.setDynamicHeight(actors_list);
        Utils.setDynamicHeight(crew_list);
    }

    @Override
    protected IPresenter createPresenter() {
        return new MovieCreditsPresenter(this);
    }
}
