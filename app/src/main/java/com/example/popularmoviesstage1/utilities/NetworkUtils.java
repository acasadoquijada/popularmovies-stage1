package com.example.popularmoviesstage1.utilities;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * These utilities will be used to communicate with the network.
 */


public class NetworkUtils {

    private static final String BASE_URL =
            "http://api.themoviedb.org/3/movie/";

    public static final String top_rated = "top_rated";
    public static final String popular = "popular";


    private static final String KEY = ""; // Your key goes here!!
    private static final String api_key_sort = "api_key";


    public static URL buildUrl(String sort_option){
        Uri builtUri = Uri.parse(BASE_URL).buildUpon()
                .appendPath(sort_option)
                .appendQueryParameter(api_key_sort,KEY)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;

    }

    /**
     * This method returns the entire result from the HTTP response.
     *
     * @param url The URL to fetch the HTTP response from.
     * @return The contents of the HTTP response.
     * @throws IOException Related to network and stream reading
     */


    public static String getResponseFromHttpUrl(URL url) throws IOException {

        Log.d("URL", url.toString());
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}