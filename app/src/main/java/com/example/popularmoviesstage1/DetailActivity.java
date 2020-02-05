package com.example.popularmoviesstage1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
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

        Intent intent = getIntent();

        Bundle b = intent.getBundleExtra("movie");

        Parcelable p = b.getParcelable("movie");

        Movie m = intent.getExtras().getBundle("movie").getParcelable("movie");

        TextView textViewTitle = findViewById(R.id.title);
        ImageView imageViewMoviePoster = findViewById(R.id.movie_poster);

        textViewTitle.setText(m.getOriginal_title());

        String imageUri = "https://image.tmdb.org/t/p/w185//nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg";

        Picasso.get().load(imageUri).into(imageViewMoviePoster);
    }
}
