package com.example.popularmoviesstage1.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavAction;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.popularmoviesstage1.R;
import com.example.popularmoviesstage1.databinding.MovieListFragmentLayoutBinding;
import com.example.popularmoviesstage1.movie.Movie;
import com.example.popularmoviesstage1.movie.MovieAdapter;
import com.example.popularmoviesstage1.utilities.NetworkUtils;
import com.example.popularmoviesstage1.viewmodel.MovieViewModel;

import java.util.List;

public class MovieListFragment extends Fragment implements MovieAdapter.GridItemClickListener{

    private MovieListFragmentLayoutBinding mBinding;
    private MovieViewModel mViewModel;
    private MovieAdapter mAdapter;

    public MovieListFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.movie_list_fragment_layout,container,false);

        setupRecyclerView();

        return mBinding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setupViewModel();

        setActivityTitle(getResources().getString(R.string.app_name_sort_popular));


        setQuery(NetworkUtils.popular);

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
        mBinding.movieListRecyclerView.setAdapter(mAdapter);
    }

    private void setLayoutManager(){
        mBinding.movieListRecyclerView.setLayoutManager(createLayoutManager());
    }

    private GridLayoutManager createLayoutManager(){
        return new GridLayoutManager(requireContext(), 2);
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
        mViewModel = new ViewModelProvider(requireActivity()).get(MovieViewModel.class);
    }

    private void observeQuery(){
        mViewModel.getQuery().observe(requireActivity(), this::searchMovies);
    }

    private void searchMovies(String s){
        mViewModel.searchMovies(s);

    }

    private void observeMovies(){
        mViewModel.getMovies().observe(requireActivity(), this::setMovies);
    }

    private void setMovies(List<Movie> movies){
        mAdapter.setMovies(movies);
    }

    private void setActivityTitle(String s){
        requireActivity().setTitle(s);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.movie_sort_menu, menu);
        super.onCreateOptionsMenu(menu,inflater);
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


    @Override
    public void onGridItemClick(int clickedItemIndex) {

        NavDirections action =
                MovieListFragmentDirections.actionMovieListFragmentToMovieDetailFragment(clickedItemIndex);

        NavHostFragment.findNavController(this).navigate(action);

    }
}
