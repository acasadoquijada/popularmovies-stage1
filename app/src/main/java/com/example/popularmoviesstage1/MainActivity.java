package com.example.popularmoviesstage1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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
    private final int colums = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMovies = new ArrayList<>();

        new FetchWeatherTask().execute(NetworkUtils.top_rated);

        mMovieGrid = findViewById(R.id.recyclerView);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,colums);

        mMovieGrid.setLayoutManager(gridLayoutManager);


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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemThatWasClickedId = item.getItemId();

        if (itemThatWasClickedId == R.id.sort_popularity){
            new FetchWeatherTask().execute(NetworkUtils.popular);
            return true;
        }

        if (itemThatWasClickedId == R.id.sort_rate){
            new FetchWeatherTask().execute(NetworkUtils.top_rated);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void initializeAdapter(){

        mAdapter = new MovieAdapter(mMovies.size(),this, mMovies);

        mMovieGrid.setAdapter(mAdapter);

    }

    @Override
    public void onGridItemClick(int clickedItemIndex) {

        if (mToast != null) {
            mToast.cancel();
        }

        // TODO CREATE INTENT PASSING THE MOVIE INFO


        Bundle bundle = new Bundle();
        bundle.putParcelable("movie", mMovies.get(clickedItemIndex));

        Intent intent = new Intent(MainActivity.this,DetailActivity.class);

        intent.putExtra("bundle",bundle);

        startActivity(intent);

        //String toastMessage = mMovies.get(clickedItemIndex).getOriginal_title();
        //mToast = Toast.makeText(this, toastMessage, Toast.LENGTH_LONG);

        //mToast.show();
    }

    class FetchWeatherTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {

            if (strings.length == 0) {
                return null;
            }

            try {

                URL url = NetworkUtils.buildUrl(strings[0]);

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

                if (mMovies != null && mMovies.size() > 0) {
                    initializeAdapter();


              //      Intent intent = new Intent(MainActivity.this,DetailActivity.class);


             //       intent.putExtra("movie",mMovies.get(1));

                    //startActivity(intent);
                }


            }
        }
    }
}
