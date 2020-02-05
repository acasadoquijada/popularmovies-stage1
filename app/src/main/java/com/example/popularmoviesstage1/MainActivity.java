package com.example.popularmoviesstage1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.popularmoviesstage1.utilities.JsonMovieUtils;
import com.example.popularmoviesstage1.utilities.NetworkUtils;

import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MovieAdapter.GridItemClickListener {


    private MovieAdapter mAdapter;
    private RecyclerView mMovieGrid;
    private Toast mToast;
    private ArrayList<Movie> mMovies;
    private int numberOfMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMovies = new ArrayList<>();

        new FetchWeatherTask().execute(NetworkUtils.top_rated);


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

    /*
    As the Movie adapter needs a list of Movies we need to make sure we have that information
    BEFORE creating the adaptaer
     */

    private void initializeAdapter(){
        mMovieGrid = findViewById(R.id.recyclerView);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);

        mMovieGrid.setLayoutManager(gridLayoutManager);

        mAdapter = new MovieAdapter(numberOfMovies,this, mMovies);

        mMovieGrid.setAdapter(mAdapter);

    }

    @Override
    public void onGridItemClick(int clickedItemIndex) {

        if (mToast != null) {
            mToast.cancel();
        }

        // TODO CREATE INTENT PASSING THE MOVIE INFO
        String toastMessage = mMovies.get(clickedItemIndex).getOriginal_title();
        mToast = Toast.makeText(this, toastMessage, Toast.LENGTH_LONG);

        mToast.show();
    }

    class FetchWeatherTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {

            if (strings.length == 0) {
                return null;
            }

            try {

                URL url = NetworkUtils.buildUrl(NetworkUtils.top_rated);

                return NetworkUtils
                        .getResponseFromHttpUrl(url);

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String jsonMovieResponse) {

            if (jsonMovieResponse != null) {

                Log.d("MOVIES", jsonMovieResponse);

                mMovies = JsonMovieUtils.parseMoviesJsonArray(jsonMovieResponse);

                numberOfMovies = mMovies.size();

                if (mAdapter == null){
                    initializeAdapter();
                }

            }
        }
    }
}
