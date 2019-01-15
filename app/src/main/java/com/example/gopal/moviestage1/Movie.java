package com.example.gopal.moviestage1;

/**
 * Created by Gopal on 1/13/2019.
 */

public class Movie {
    private String mMovieTitle;
    private String mMoviePoster;
    private String mMovieOverview;
    private String mMovieRating;
    private String mMovieReleaseDate;

    public Movie( String mMovieTitle, String mMoviePoster, String mMovieOverview, String mMovieRating, String mMovieReleaseDate) {
        this.mMovieTitle = mMovieTitle;
        this.mMoviePoster = mMoviePoster;
        this.mMovieOverview = mMovieOverview;
        this.mMovieRating = mMovieRating;
        this.mMovieReleaseDate = mMovieReleaseDate;
    }

    public String getMovieTitle() {
        return mMovieTitle;
    }

    public String getMoviePoster() {
        return mMoviePoster;
    }

    public String getMovieOverview() {
        return mMovieOverview;
    }

    public String getMovieRating() {
        return mMovieRating;
    }

    public String getMovieReleaseDate() {
        return mMovieReleaseDate;
    }
}
