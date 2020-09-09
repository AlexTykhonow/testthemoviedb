package com.Test.themoviedb.data.repository.base;

import java.util.List;

import com.Test.themoviedb.data.exception.FailedGettingDataException;
import com.Test.themoviedb.model.Movie;
import com.Test.themoviedb.model.MovieCredits;
import com.Test.themoviedb.model.MovieDetails;

/**
 * This interface defines the contract with the data layer
 * and the presentation layer for requesting data synchronously
 */
public interface ICloudMovieRepository {

    /**
     * Get movies list in theaters synchronously
     * And convert to a model entity
     *
     * @param page api page
     * @return Entity model
     * @throws FailedGettingDataException
     */
    List<Movie> getTheatersMovies(int page) throws FailedGettingDataException;

    /**
     * Get movies list that will be in theaters soon synchronously
     * And convert to a model entity
     *
     * @param page api page
     * @return Entity model
     * @throws FailedGettingDataException
     */
    List<Movie> getSoonMovies(int page) throws FailedGettingDataException;

    /**
     * Get top movies list synchronously
     * And convert to a model entity
     *
     * @param page api page
     * @return Entity model
     * @throws FailedGettingDataException
     */
    List<Movie> getTopMovies(int page) throws FailedGettingDataException;

    /**
     * Get movies list for a search query synchronously
     * And convert to a model entity
     *
     * @param search search query
     * @param page   api page
     * @return Entity model
     * @throws FailedGettingDataException
     */
    List<Movie> getMovieSearch(String search, int page)
            throws FailedGettingDataException;

    /**
     * Get movie by id synchronously
     * And convert to a model entity
     *
     * @param id
     * @return Entity model
     * @throws FailedGettingDataException
     */
    MovieDetails getMovieById(int id) throws FailedGettingDataException;

    /**
     * Get movie credits synchronously
     * And convert to a model entity
     *
     * @param id
     * @return
     * @throws FailedGettingDataException
     */
    MovieCredits getCreditsOfMovie(int id) throws FailedGettingDataException;
}
