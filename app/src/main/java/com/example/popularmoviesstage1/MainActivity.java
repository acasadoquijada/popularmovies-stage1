package com.example.popularmoviesstage1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.popularmoviesstage1.utilities.JsonMovieUtils;
import com.example.popularmoviesstage1.utilities.NetworkUtils;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements MovieAdapter.GridItemClickListener {


    private MovieAdapter mAdapter;
    private RecyclerView mMovieGrid;
    private Toast mToast;
    private Movie mMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        URL url = NetworkUtils.buildUrl(NetworkUtils.popular);

        new FetchWeatherTask().execute(url);


/*        String imageUri = "https://i.imgur.com/tGbaZCY.jpg";
        ImageView ivBasicImage = (ImageView) findViewById(R.id.imageView);
        Picasso.with(this).load(imageUri).into(ivBasicImage);*/

//
       // Intent intent = new Intent(MainActivity.this,DetailActivity.class);


        /*
         * Once the Intent has been created, we can use Activity's method, "startActivity"
         * to start the ChildActivity.
         */
       // startActivity(intent);


    }

    private void initializeAdapter(){
        mMovieGrid = (RecyclerView) findViewById(R.id.recyclerView);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);

        mMovieGrid.setLayoutManager(gridLayoutManager);

        mAdapter = new MovieAdapter(10,this,mMovie);

        mMovieGrid.setAdapter(mAdapter);

    }

    @Override
    public void onGridItemClick(int clickedItemIndex) {

        Log.d("TEST","IM HERE");
        if (mToast != null) {
            mToast.cancel();
        }

        // COMPLETED (12) Show a Toast when an item is clicked, displaying that item number that was clicked
        /*
         * Create a Toast and store it in our Toast field.
         * The Toast that shows up will have a message similar to the following:
         *
         *                     Item #42 clicked.
         */
        String toastMessage = "Item #" + clickedItemIndex + " clicked.";
        mToast = Toast.makeText(this, toastMessage, Toast.LENGTH_LONG);

        mToast.show();
    }

    public class FetchWeatherTask extends AsyncTask<URL, Void, String> {

        @Override
        protected String doInBackground(URL... urls) {

            if (urls.length == 0) {
                return null;
            }

            try {
                String jsonMovieResponse = NetworkUtils
                        .getResponseFromHttpUrl(urls[0]);

                return jsonMovieResponse;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String jsonMovieResponse) {

            if(mMovieGrid.getAdapter() != mAdapter){
                initializeAdapter();
            }

            if (jsonMovieResponse != null) {

                Log.d("MOVIES", jsonMovieResponse);

                mMovie = JsonMovieUtils.parseMoviesJson(jsonMovieResponse);

                if(mMovie != null){
                    mAdapter.setMovie(mMovie);
                }


                String p = "hola";

                // I should create the movie objects here. Why?

                // Once I got everything,

                /*
                 * Iterate through the array and append the Strings to the TextView. The reason why we add
                 * the "\n\n\n" after the String is to give visual separation between each String in the
                 * TextView. Later, we'll learn about a better way to display lists of data.
                 */
            }
        }
    }
}
