package com.example.popularmoviesstage1.viewmodel;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.popularmoviesstage1.movie.Movie;
import com.example.popularmoviesstage1.repository.MovieRepository;

import java.util.List;
import java.util.Objects;

public class MovieViewModel extends ViewModel {

    private MovieRepository repository;
    private MutableLiveData<List<Movie>> movies;
    private MutableLiveData<String> query;

    public MovieViewModel(){
        repository = new MovieRepository();
        query = new MutableLiveData<>();
        movies = new MutableLiveData<>();
    }

    public MutableLiveData<List<Movie>> getMovies() {
        if(movies.getValue() == null){
            movies = repository.getMovies();
        }

        return movies;
    }

    public void searchMovies(String s){
        repository.obtainMovies(s);
    }

    public MutableLiveData<String> getQuery() {
        return query;
    }

    public void setQuery(String query){
        this.query.setValue(query);
    }

    public Movie getMovie(int pos){
        
        if(movies.getValue() != null){
            return movies.getValue().get(pos);
        }

        return new Movie();
    }
}
