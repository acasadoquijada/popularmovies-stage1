package com.example.popularmoviesstage1.utilities;


import com.example.popularmoviesstage1.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

// I get a String with several movies. I do my job and I create
public class JsonMovieUtils {

    private final static String popularity_token = "popularity";
    private final static String vote_count_token = "vote_count";
    private final static String video_token = "video";
    private final static String poster_path_token = "poster_path";
    private final static String id_token = "id";
    private final static String adult_token = "adult";
    private final static String backdrop_path_token = "backdrop_path";
    private final static String original_language_token = "original_language";
    private final static String original_title_token= "original_title";
    private final static String title_token = "title";
    private final static String vote_average_token = "vote_average";
    private final static String overview_token= "overview";
    private final static String release_date_token = "release_date";


    public static Movie parseMoviesJson(String json) {

        try {

            Movie movie = new Movie();
            int popularity;
            int vote_count;
            boolean video;
            String poster_path;
            int id;
            boolean adult;
            String backdrop_path;
            String original_language;
            String original_title;
            int[] genre_ids;
            String title;
            double vote_average;
            String overview;
            String release_date;


            JSONObject JSONObjectResultQuery = new JSONObject(json);
            JSONArray JSONArrayMovies = JSONObjectResultQuery.getJSONArray("results");

            for(int i = 0; i < 1; i++){

                JSONObject JSONMovie = JSONArrayMovies.getJSONObject(i);

                // Popularity
                popularity = JSONMovie.getInt(popularity_token);

                movie.setPopularity(popularity);

                // Vote count
                vote_count = JSONMovie.getInt(vote_count_token);

                movie.setVote_count(vote_count);

                // Video
                video = JSONMovie.getBoolean(video_token);

                movie.setVideo(video);

                // Poster path
                poster_path = JSONMovie.getString(poster_path_token);

                movie.setPoster_path(poster_path);

                // ID
                id = JSONMovie.getInt(id_token);

                movie.setId(id);

                // Adult
                adult = JSONMovie.getBoolean(adult_token);

                movie.setAdult(adult);

                // Backdrop path
                backdrop_path = JSONMovie.getString(backdrop_path_token);

                movie.setBackdrop_path(backdrop_path);

                // Original language
                original_language = JSONMovie.getString(original_language_token);

                movie.setOriginal_language(original_language);

                // Original title
                original_title = JSONMovie.getString(original_title_token);

                movie.setOriginal_language(original_title);

                // Title
                title = JSONMovie.getString(title_token);

                movie.setOriginal_language(title);

                // Vote average
                vote_average = JSONMovie.getDouble(vote_average_token);

                movie.setVote_average(vote_average);

                // Overview
                overview = JSONMovie.getString(overview_token);

                movie.setOverview(overview);

                // Release date
                release_date = JSONMovie.getString(release_date_token);

                movie.setRelease_date(release_date);

            }

            return movie;

        } catch (JSONException e) {
            e.printStackTrace();

            return null;
        }
    }

}

