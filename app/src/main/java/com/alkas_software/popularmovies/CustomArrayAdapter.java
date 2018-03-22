package com.alkas_software.popularmovies;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by alkas on 4/9/2017.
 */

public class CustomArrayAdapter extends ArrayAdapter<Movie> {
    public static final String LOG_TAG = CustomArrayAdapter.class.getName();

    public static final String imageBasePath="https://image.tmdb.org/t/p/w185/";

    Context mContext;

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        View listItemView = convertView;
        if(listItemView == null) {listItemView = LayoutInflater.from(getContext()).inflate(R.layout.movie_item, parent, false);}

        final Movie currentMovie = getItem(position);

        ImageView iv= listItemView.findViewById(R.id.iView);
        Log.d(LOG_TAG,"image path="+currentMovie.getmImageThumbnail());

        String imagePath=imageBasePath+currentMovie.getmImageThumbnail();

        Picasso.with(mContext).load(imagePath).into(iv);


        // Find the TextView with view title
        TextView titleView = listItemView.findViewById(R.id.title);
        titleView.setText(currentMovie.getOriginalTtitle());

        return listItemView;
    }

    public CustomArrayAdapter(Context context, List<Movie> movies) {
        super(context,0,movies);
        mContext=context;
    }
}
