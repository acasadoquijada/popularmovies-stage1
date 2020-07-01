package com.example.popularmoviesstage1.viewmodel;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.popularmoviesstage1.movie.Movie;
import com.example.popularmoviesstage1.repository.MovieRepository;

import java.util.List;

public class MovieViewModel extends ViewModel {

    private MutableLiveData<List<Movie>> movies;
    private MovieRepository repository;

    public MovieViewModel(){

        Log.d("TESTING__", "CREATE VIEWMODEL");


        repository = new MovieRepository();
    }

    public MutableLiveData<List<Movie>> getMovies() {
        if(movies == null){
            movies = new MutableLiveData<>();
            movies = repository.getMovies();
        }
        return movies;
    }

    public Movie getMovie(int pos){
        if(movies.getValue().get(pos) != null){
            return movies.getValue().get(pos);
        }
        return new Movie();
    }
}
