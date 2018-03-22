package com.alkas_software.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {

    /** Tag for log messages */
    private static final String LOG_TAG = DetailsActivity.class.getName();

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    TextView tvTitle;
    TextView tvPlot;
    TextView tvRelease;
    TextView tvVotes;
    ImageView iv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            Log.d(LOG_TAG,"Oncreate "+ String.valueOf(position));
            closeOnError();
            return;
        }

        tvTitle=findViewById(R.id.tvTitle);
        tvRelease=findViewById(R.id.tvRelease);
        tvVotes=findViewById(R.id.tvVotes);
        iv=findViewById(R.id.ivMovie);
        tvPlot=findViewById(R.id.tvSynopsis);

        Movie movie=MainActivity.movies.get(position);

        tvTitle.setText(movie.getOriginalTtitle());
        tvRelease.setText(movie.getmReleaseDate());
        tvVotes.setText(movie.getmVoteAverage().toString());
        tvPlot.setText(movie.getmOverview());

        String imagePath=CustomArrayAdapter.imageBasePath+movie.getmImageThumbnail();

        Picasso.with(this).load(imagePath).into(iv);

    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }
}
