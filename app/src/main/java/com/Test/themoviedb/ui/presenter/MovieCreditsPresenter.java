package com.Test.themoviedb.ui.presenter;

import android.os.AsyncTask;
import android.util.Log;

import com.Test.themoviedb.data.exception.FailedGettingDataException;
import com.Test.themoviedb.data.repository.base.ICloudMovieRepository;
import com.Test.themoviedb.data.repository.base.MovieRepositoryFactory;
import com.Test.themoviedb.model.MovieCredits;
import com.Test.themoviedb.ui.contract.ILoadDataView;
import com.Test.themoviedb.ui.presenter.base.Presenter;

/**
 * Presenter class for setting data to the credits view
 */
public class MovieCreditsPresenter extends Presenter<MovieCredits> {

    private int movieId;

    public MovieCreditsPresenter(
            ILoadDataView<MovieCredits> view) {
        super(view);
    }

    public void setMovieId(int id) {
        this.movieId = id;
    }

    @Override
    public void execute() {
        if(movieId != 0)
            new LoadDataTask().execute();
    }

    /**
     * Download movie credits in a worker thread using an AsyncTask
     * From cloud repo
     */
    private class LoadDataTask extends AsyncTask<Void, Void, MovieCredits> {

        @Override
        protected MovieCredits doInBackground(Void... params) {
            Log.d(TAG, "doInBackground: Getting movie credits from the api");
            ICloudMovieRepository repo = MovieRepositoryFactory.getCloudRepository();

            try {
                return repo.getCreditsOfMovie(movieId);
            } catch (FailedGettingDataException e) {
                Log.d(TAG, "Failed getting data! Error: " + e.getMessage());
            }

            return null;
        }

        @Override
        protected void onPostExecute(MovieCredits credits) {
            super.onPostExecute(credits);

            if(credits != null)
                view.setData(credits);
            else
                view.showNoConnection();
        }
    }
}
