package com.example.gopal.moviestage1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);


        TextView title = findViewById(R.id.movie_title);
        TextView rating = findViewById(R.id.movie_rating);
        TextView releaseDate = findViewById(R.id.movie_release_date);
        TextView overview = findViewById(R.id.movie_overview);
        ImageView poster = findViewById(R.id.movie_poster);

        String movieTitle = getIntent().getStringExtra(MainActivity.MOVIE_TITLE_KEY);
        String moviePoster = getIntent().getStringExtra(MainActivity.MOVIE_POSTER_KEY);
        String movieRating = getIntent().getStringExtra(MainActivity.MOVIE_RATING_KEY);
        String movieReleaseDate = getIntent().getStringExtra(MainActivity.MOVIE_RELEASE_DATE_KEY);
        String movieOverview = getIntent().getStringExtra(MainActivity.MOVIE_OVERVIEW_KEY);

        title.setText(movieTitle);
        rating.setText(movieRating + "/10,");
        releaseDate.setText(movieReleaseDate);
        overview.setText(movieOverview);
        Log.e("DetailsActivity", movieReleaseDate);

        //Extracting image from Url by Picasso library
        String imageUrl = "https://image.tmdb.org/t/p/original" + moviePoster;
        Picasso.get().load(imageUrl)
                .into(poster);
      /*  Glide.with(this).load(imageUrl)
                .into(poster);
*/
    }
}
