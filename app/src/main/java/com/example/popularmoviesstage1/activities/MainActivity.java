package com.example.popularmoviesstage1.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.popularmoviesstage1.databinding.ActivityMainBinding;
import com.example.popularmoviesstage1.movie.Movie;
import com.example.popularmoviesstage1.movie.MovieAdapter;
import com.example.popularmoviesstage1.R;
import com.example.popularmoviesstage1.utilities.NetworkUtils;
import com.example.popularmoviesstage1.viewmodel.MovieViewModel;

import java.util.List;

/**
 * Main Activity of the Popular Movies Stage 1 application
 */
public class MainActivity extends AppCompatActivity implements MovieAdapter.GridItemClickListener {

    private MovieAdapter mAdapter;

    private ActivityMainBinding mBinding;

    private MovieViewModel mViewModel;

    public static final String bundle_token = "token";
    public static final String parcelable_token = "parcelable";
    public static final String pos_key = "pos";

    /**
     * Creates different objects needed for the MovieAdapter and request the popular movies
     * using a AsyncTask class
     * @param savedInstanceState bundle object
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setupDataBinding();

        setupViewModel();

        setActivityTitle(getResources().getString(R.string.app_name_sort_popular));

        setQuery(NetworkUtils.popular);

        setupRecyclerView();

    }

    private void setQuery(String s){
        mViewModel.setQuery(s);
    }

    private void setupRecyclerView(){
        createAdapter();
        setAdapter();
        setLayoutManager();
    }

    private void createAdapter(){
        mAdapter = new MovieAdapter(this);
    }

    private void setAdapter(){
        mBinding.MovieRecyclerView.setAdapter(mAdapter);
    }

    private void setLayoutManager(){
        mBinding.MovieRecyclerView.setLayoutManager(createLayoutManager());
    }

    private GridLayoutManager createLayoutManager(){
        return new GridLayoutManager(this, 2);
    }

    private void setupDataBinding(){
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
    }

    private void setActivityTitle(String s){
        this.setTitle(s);
    }

    /**
     * To setup the ViewModel and observe the query and movies to update the adapter
     */

    private void setupViewModel(){
        createViewModel();
        observeQuery();
        observeMovies();
    }

    private void createViewModel(){
        mViewModel = new ViewModelProvider(this).get(MovieViewModel.class);
    }

    private void observeQuery(){
        mViewModel.getQuery().observe(this, this::searchMovies);
    }

    private void searchMovies(String s){
        mViewModel.searchMovies(s);

    }

    private void observeMovies(){
        mViewModel.getMovies().observe(this, this::setMovies);
    }

    private void setMovies(List<Movie> movies){
        mAdapter.setMovies(movies);
    }


    /**
     * Inflates the menu that handles the movie sorting
     * @param menu to inflate
     * @return results of the menu inflating
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.movie_sort_menu, menu);
        return true;
    }

    /**
     * Method run when a menu item is clicked to sort the movies
     * @param item clicked
     * @return boolean result of the operation
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemThatWasClickedId = item.getItemId();

        if (itemThatWasClickedId == R.id.sort_popularity){
            setActivityTitle(getResources().getString(R.string.app_name_sort_popular));
            setQuery(NetworkUtils.popular);
            return true;
        }

        if (itemThatWasClickedId == R.id.sort_rate){
            setActivityTitle(getResources().getString(R.string.app_name_sort_rate));
            setQuery(NetworkUtils.top_rated);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * Method in charge of creating a DetailActivity to presents the details of the movie
     * clicked by the user
     * @param clickedItemIndex index of the MovieViewHolder object clicked
     */

    @Override
    public void onGridItemClick(int clickedItemIndex) {

        Bundle bundle = new Bundle();

        bundle.putParcelable(parcelable_token, mViewModel.getMovie(clickedItemIndex));

        bundle.putInt(pos_key,clickedItemIndex);

        Intent intent = new Intent(MainActivity.this, DetailActivity.class);

        intent.putExtra(bundle_token,bundle);

        startActivity(intent);

    }
}
