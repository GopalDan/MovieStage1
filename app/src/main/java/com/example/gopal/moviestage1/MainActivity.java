package com.example.gopal.moviestage1;

import android.app.LoaderManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements android.app.LoaderManager.LoaderCallbacks<List<Movie>> {



    private  String mUrl;
    CustomAdapter mCustomAdapter;
    ProgressBar mProgressbar;
    private final int ID_MAIN_LOADER = 0;
    private final int ID_POPULAR_MOVIE_LOADER = 1;
    private final int ID_TOP_RATED_MOVIE_LOADER = 2;

    public static final String MOVIE_TITLE_KEY = "movieTitle";
    public static final String MOVIE_POSTER_KEY = "moviePoster";
    public static final String MOVIE_RATING_KEY = "movieRating";
    public static final String MOVIE_RELEASE_DATE_KEY = "movieReleaseDate";
    public static final String MOVIE_OVERVIEW_KEY = "movieOverview";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mProgressbar = findViewById(R.id.progress_bar);
        Log.v("MainActivity","onCreate " );

        setTitle("Movies");

        final GridView gridView = findViewById(R.id.gridView);
        mCustomAdapter = new CustomAdapter(this, new ArrayList<Movie>());
        gridView.setAdapter(mCustomAdapter);

        getLoaderManager().initLoader(ID_MAIN_LOADER,null, this);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Movie currentMovie = mCustomAdapter.getItem(position);
                String movieTitle = currentMovie.getMovieTitle();
                String moviePoster = currentMovie.getMoviePoster();
                String movieRating = currentMovie.getMovieRating();
                String movieReleaseDate = currentMovie.getMovieReleaseDate();
                String movieOverview = currentMovie.getMovieOverview();

                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                intent.putExtra(MOVIE_TITLE_KEY,movieTitle);
                intent.putExtra(MOVIE_POSTER_KEY, moviePoster);
                intent.putExtra(MOVIE_RATING_KEY, movieRating);
                intent.putExtra(MOVIE_RELEASE_DATE_KEY, movieReleaseDate);
                intent.putExtra(MOVIE_OVERVIEW_KEY, movieOverview);
                startActivity(intent);

            }
        });
    }


    @Override
    public android.content.Loader<List<Movie>> onCreateLoader(int id, Bundle bundle) {
        if(id == ID_POPULAR_MOVIE_LOADER){
            mUrl = "https://api.themoviedb.org/3/movie/popular?api_key=7bf68dbc7833ff0567ea231ec0cef88f";
        }
        else if(id == ID_TOP_RATED_MOVIE_LOADER){
            mUrl = "https://api.themoviedb.org/3/movie/top_rated?api_key=7bf68dbc7833ff0567ea231ec0cef88f";
        }
        else {
            mUrl = "https://api.themoviedb.org/3/movie/now_playing?api_key=7bf68dbc7833ff0567ea231ec0cef88f";
        }
        return new CustomLoader(MainActivity.this,mUrl);
    }

    @Override
    public void onLoadFinished(android.content.Loader<List<Movie>> loader, List<Movie> movies) {
        mProgressbar.setVisibility(View.GONE);
        if(movies==null){
            Log.e("MainActivity","No movie found");
            return;
        }
        mCustomAdapter.addAll(movies);
    }

    @Override
    public void onLoaderReset(android.content.Loader<List<Movie>> loader) {
        mProgressbar.setVisibility(View.VISIBLE);
        mCustomAdapter.clear();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.setting, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.sort_by_popularity:
                getLoaderManager().restartLoader(1,null,this);
                break;
            case R.id.sort_by_rating:
                getLoaderManager().restartLoader(2,null,this);
                break;

        }
        return super.onOptionsItemSelected(item);
    }

}
