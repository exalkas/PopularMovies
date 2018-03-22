package com.alkas_software.popularmovies;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Movie>> {

    public static List<Movie> movies;

    public static final String LOG_TAG = MainActivity.class.getName();


    /**
     * Random value for the loader ID. I am using only 1 loader
     */
    private static final int LOADER_ID = 1;

    /** TextView that is displayed when the list is empty */
    private TextView mEmptyStateTextView;


    private String REQUEST_URL="http://api.themoviedb.org/3/movie/popular?api_key=";

    /** Custom Adapter */
    private CustomArrayAdapter mAdapter;

    Switch mSwitch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEmptyStateTextView=findViewById(R.id.empty_view);
        mSwitch=findViewById(R.id.switch1);

        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        // If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected()) {
            // Get a reference to the LoaderManager, in order to interact with loaders.
            LoaderManager loaderManager = getLoaderManager();

            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).

            loaderManager.initLoader(LOADER_ID, null, MainActivity.this);
        } else {
            // Otherwise, display error
            // First, hide loading indicator so error message will be visible
            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);

            // Update empty state with no connection error message
            mEmptyStateTextView.setText(R.string.no_internet_connection);
        }

        // Find a reference to the {@link GridView} in the layout
        final GridView gridView = findViewById(R.id.gridView);
        gridView.setEmptyView(mEmptyStateTextView);

        // Create a new adapter that takes an empty list of movies as input
        mAdapter = new CustomArrayAdapter(this, new ArrayList<Movie>());

        gridView.setAdapter(mAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                launchDetailActivity(i);
            }
        });

        //Listener for the switch view
        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    Collections.sort(movies,new SortTop());
                } else {
                    Collections.sort(movies,new SortPopular());
                }
                gridView.setAdapter(mAdapter);
                mAdapter.clear();
                mAdapter.addAll(movies);
            }
        });

    }
        @Override
    public Loader<List<Movie>> onCreateLoader(int i, Bundle bundle) {
        // Create a new loader for the given URL
            REQUEST_URL+=getResources().getString(R.string.api_key);
        return new MoviesLoaderManager(this, REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<Movie>> loader, List<Movie> movies) {
        // Hide loading indicator because the data has been loaded
        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);

        // Set empty state text to display "No Movies found."
        mEmptyStateTextView.setText(R.string.no_movies);

        // Clear the adapter of previous data
        mAdapter.clear();

        // If there is a valid list of {@link movies}, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (movies != null && !movies.isEmpty()) {
            if (mSwitch.isChecked()){
                Collections.sort(movies,new SortTop());}
            else {
                Collections.sort(movies,new SortPopular());
            }
            mAdapter.addAll(movies);
        }

    }

    @Override
    public void onLoaderReset(Loader<List<Movie>> loader) {
        // Loader reset, so we can clear out our existing data.
        mAdapter.clear();
    }

    private void launchDetailActivity(int position) {
        Intent intent = new Intent(this, DetailsActivity.class);

        intent.putExtra(DetailsActivity.EXTRA_POSITION, position);
        startActivity(intent);
    }

}
