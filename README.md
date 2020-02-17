# Popular Movies Stage 1

## General Overview

This repo contains all the work done for the **Popular Movies Stage 1** project of the Udacity's Android Developer Nanodegree.

## Application description

This applicaction contains the following functionalities:

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
    

**Activities package**

* DetailActivity: Activity where the details of a movie are shown
* MainActivity: Main Activity of the app. All the network request are done here

**Movie package**

* Movie: Represents a movie with all its info
* MovieAdapter: RecyclerView in charge of present the movies to the user

**Utilities package**

* JsonMovieUtils: Parses the movie info obtained from JSON as results of network requets
* NetWorkUtils: Request movie info to themoviedb.org
   
## Add your themoviedb.org API key

As mentioned before, you need to register in themoviedb.org to obtain a key for their API in order to use this app.

The key goes in the NetworkUtils.java class of the utilties package line 26. [shortcut](https://github.com/acasadoquijada/popularmovies-stage1/blob/master/app/src/main/java/com/example/popularmoviesstage1/utilities/NetworkUtils.java#L26)