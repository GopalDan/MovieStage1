package com.example.gopal.moviestage1;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;

/**
 * Created by Gopal on 1/13/2019.
 */

public class CustomLoader extends AsyncTaskLoader<List<Movie>> {
    private String mUrl;
    public CustomLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }
    @Override
    protected void onStartLoading() {
        //  Log.e(LOG_TAG,"onStartLoading is called: ");
        forceLoad();
    }


    @Override
    public List<Movie> loadInBackground() {
        if (mUrl == null) {
            return null;
        }
        // Log.e(LOG_TAG,"loadInBackground is called: ");

        // Perform the network request, parse the response, and extract a list of movies.
        Log.v("CustomLoader", "URL for data fetching- " + mUrl);
        List<Movie> movies = QueryUtils.fetchTrainData(mUrl);
        return movies;
    }
}
