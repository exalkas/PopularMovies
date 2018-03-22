package com.alkas_software.popularmovies;

import java.math.BigDecimal;

/**
 * Created by alkas on 3/3/2018.
 */

public class Movie {
    String mOriginalTtitle;
    String mImageThumbnail;
    String mOverview;
    BigDecimal mVoteAverage;
    Integer mVoteCount;
    String mReleaseDate;

    public Movie(String originalTitle, String imageThumbnail, String overview, BigDecimal voteAverage, Integer voteCount,String releaseDate) {
        mOriginalTtitle=originalTitle;
        mImageThumbnail=imageThumbnail;
        mOverview=overview;
        mVoteAverage=voteAverage;
        mVoteCount=voteCount;
        mReleaseDate=releaseDate;
    }

    public String getOriginalTtitle(){return mOriginalTtitle;}
    public String getmImageThumbnail(){return mImageThumbnail;}
    public String getmOverview() {return  mOverview;}
    public BigDecimal getmVoteAverage() {return mVoteAverage;}
    public Integer getmVoteCount() {return mVoteCount;}
    public String getmReleaseDate() {return mReleaseDate;}
}
