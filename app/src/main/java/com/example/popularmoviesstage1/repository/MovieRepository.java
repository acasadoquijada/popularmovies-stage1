package com.example.popularmoviesstage1.repository;

import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.popularmoviesstage1.movie.Movie;
import com.example.popularmoviesstage1.utilities.JsonMovieUtils;
import com.example.popularmoviesstage1.utilities.NetworkUtils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.List;

public class MovieRepository {

    private MutableLiveData<List<Movie>> movies;

    public MovieRepository(){}

    public MutableLiveData<List<Movie>> getMovies() {

        if(movies == null){
            movies = new MutableLiveData<>();
            new FetchMoviesTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,NetworkUtils.top_rated);
        }
        return movies;
    }

    class FetchMoviesTask extends AsyncTask<String, Void, List<Movie>> {


        /**
         * Checks if there is internet connection.
         * This method has been obtaion from this StackOverflow post:
         * https://stackoverflow.com/questions/1560788/how-to-check-internet-access-on-android-inetaddress-never-times-out
         * @return true if there is internet connection, false otherwise
         */

        private boolean isOnline() {
            try {
                int timeoutMs = 1500;
                Socket sock = new Socket();
                SocketAddress sockaddr = new InetSocketAddress("8.8.8.8", 53);

                sock.connect(sockaddr, timeoutMs);
                sock.close();

                return true;
            } catch (IOException e) { return false; }
        }

        /**
         * Fetch the movie data from the API and returns an ArrayList of Movies
         *
         * It uses previousSortOption and currentSortOption to avoid unnecessary calls to the API
         * @param strings option to sort the movies
         * @return ArrayList of Movies
         */

        @Override
        protected List<Movie> doInBackground(String... strings) {

            if (isOnline()) {

                if (strings.length == 0) {
                    return null;
                }

                try {

                    String jsonMovies;

                    switch (strings[0]) {
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

            return null;
        }

        /**
         * Stores the movies in the mMovies variable from the MainActivity class and initialize
         * the MovieAdapter if needed. Updates previousSortOption
         */

        @Override
        protected void onPostExecute(List<Movie> m) {
            if(m != null){
                movies.setValue(m);
            }
        }
    }
}
