package com.example.popularmoviesstage1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

// REVIEW THIS:

// https://www.sitepoint.com/transfer-data-between-activities-with-android-parcelable/

public class DetailActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView textViewTitle;
        TextView textViewOverview;
        TextView textViewReleaseDate;
        TextView textViewVoteAverage;
        ImageView imageViewMoviePoster;
        Intent intent;
        Movie movie;

        /*
        *
        * To avoid Android E/Parcel﹕ Class not found when unmarshalling
        * https://stackoverflow.com/questions/28589509/android-
        * e-parcel-class-not-found-when-unmarshalling-only-on-samsung-tab3
        *
        */

        intent = getIntent();

        Bundle b = intent.getBundleExtra("bundle");

        if(b != null && b.getParcelable("movie") != null){

            movie = b.getParcelable("movie");

            if(movie != null){
                // Movie poster
                imageViewMoviePoster = findViewById(R.id.movie_poster);
                Picasso.get().load(movie.getPoster_path()).into(imageViewMoviePoster);

                // Movie original_title
                textViewTitle = findViewById(R.id.title);
                textViewTitle.setText(movie.getOriginal_title());

                // Movie overview
                textViewOverview = findViewById(R.id.overview);
                textViewOverview.setText(movie.getOverview());

                // Movie release date
                String release_date_help_text = "Release date: ";
                textViewReleaseDate = findViewById(R.id.release_date);
                textViewReleaseDate.setText(release_date_help_text);
                textViewReleaseDate.append(movie.getRelease_date());

                // Movie vote average
                String vote_average_help_text = "Vote average: ";
                textViewVoteAverage = findViewById(R.id.vote_average);
                textViewVoteAverage.setText(vote_average_help_text);
                textViewVoteAverage.append(movie.getVote_average() + "/10");
            }

        }

    }
}
