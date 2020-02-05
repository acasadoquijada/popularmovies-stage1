package com.example.popularmoviesstage1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView textViewTitle = findViewById(R.id.title);
        ImageView imageViewMoviePoster = findViewById(R.id.movie_poster);

        textViewTitle.setText("Super cool title");

        String imageUri = "https://image.tmdb.org/t/p/w185//nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg";

        Picasso.get().load(imageUri).into(imageViewMoviePoster);
    }
}
