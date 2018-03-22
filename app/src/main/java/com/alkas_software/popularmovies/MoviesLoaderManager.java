package com.alkas_software.popularmovies;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Loads a list of movies using an AsyncTask to perform the
 * network request to the given URL.
 */
public class MoviesLoaderManager extends AsyncTaskLoader<List<Movie>> {

    /** Tag for log messages */
    private static final String LOG_TAG = MoviesLoaderManager.class.getName();

    /** Query URL */
    private String mUrl;

    /**
     * Constructs a new {@link MoviesLoaderManager}.
     *
     * @param context of the activity
     * @param url to load data from
     */
    public MoviesLoaderManager(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    /**
     * This is on a background thread.
     */
    @Override
    public List<Movie> loadInBackground() {
        if (mUrl == null) {
            return null;
        }

        // Perform the network request, parse the response, and extract Movies.
        MainActivity.movies = com.alkas_software.popularmovies.QueryUtils.fetchData(mUrl);
        return MainActivity.movies;
    }
}