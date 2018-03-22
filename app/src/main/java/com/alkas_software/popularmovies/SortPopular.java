package com.alkas_software.popularmovies;

import java.util.Comparator;

/**
 * Created by alkas on 14/10/2017.
To sort the custom object ascending

 */


public class SortPopular implements Comparator<Movie> {

        @Override
        public int compare(Movie m1, Movie m2) {
            int i=m2.getmVoteCount().compareTo(m1.getmVoteCount());
            return i;
        }
}
