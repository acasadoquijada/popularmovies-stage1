# Popular Movies Stage 1

## General Overview

This repo contains all the work done for the **Popular Movies Stage 1** project of the Udacity's Android Developer Nanodegree.

## Application description

This applicaction contains the following functiyonaly:

* Presents a grid view with different movie posters. All the movie information has been obtain from themoviedb.org API. Please see [this link](https://www.themoviedb.org/documentation/api)   for more infomation about the API used

* Allow your user to change sort order:
	* Most popular movies
	* Top rated movies
	
* Once the user clicks on a movie poster a new Activity is created presenting more details about the movie:
	* Original title
	* Movie poster image thumbnail
	* Synopsis
	* User rating
	* Release date
	
## Application class structure

    |-- activities package
    |   |-- DetailActivity.java
    |   |-- MainActivity.java
    |-- movie package
    |   |-- Movie.java
    |   |-- MovieAdapter.java
    |-- utilities package
    |   |-- JsonMovieUtils.java
    |   |-- NetworkUtils.java
    
TODO: Add an small description of each package/class