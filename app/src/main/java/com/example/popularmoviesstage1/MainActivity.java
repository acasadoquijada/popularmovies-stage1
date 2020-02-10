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
    private ArrayList<Movie> mMovies;

    private String previousSortOption = "";
    private String currentSortOption = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        previousSortOption = "";
        currentSortOption = NetworkUtils.popular;

        mMovies = new ArrayList<>();

        mMovieGrid = findViewById(R.id.recyclerView);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);

        mMovieGrid.setLayoutManager(gridLayoutManager);

        new FetchMoviesTask().execute(currentSortOption);

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
            new FetchMoviesTask().execute(NetworkUtils.popular);
            return true;
        }

        if (itemThatWasClickedId == R.id.sort_rate){
            new FetchMoviesTask().execute(NetworkUtils.top_rated);
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

        Bundle bundle = new Bundle();

        bundle.putParcelable("movie", mMovies.get(clickedItemIndex));

        Intent intent = new Intent(MainActivity.this,DetailActivity.class);

        intent.putExtra("bundle",bundle);

        startActivity(intent);

    }

    class FetchMoviesTask extends AsyncTask<String, Void, ArrayList<Movie> >{

        @Override
        protected ArrayList<Movie> doInBackground(String... strings) {

            if (strings.length == 0) {
                return null;
            }

            if (!previousSortOption.equals(strings[0])) {

                try {

                    currentSortOption = strings[0];

                    String jsonMovies;

                    switch (strings[0]){
                        case NetworkUtils.top_rated:
                            jsonMovies = NetworkUtils.getTopRateMovies();
                            break;
                        case NetworkUtils.popular:
                            jsonMovies = NetworkUtils.getPopularMovies();
                            break;
                        default:
                            jsonMovies = "";
                            break;
                    }

                    return JsonMovieUtils.parseMoviesJsonArray(jsonMovies);

                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }

            }

            return mMovies;
        }

        @Override
        protected void onPostExecute(ArrayList<Movie> movies) {

            if (movies != null) {

                if(!currentSortOption.equals(previousSortOption)){
                    mMovies = movies;
                    initializeAdapter();
                }
                previousSortOption = currentSortOption;
            }
        }
    }
}
