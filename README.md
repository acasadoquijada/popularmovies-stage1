#Popular Movies Stage 1

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

´´´
java.lang.Object
   |--- java.awt.Component
   |       |--- java.awt.Container
   |               |--- java.awt.Window
   |                       |--- java.awt.Dialog
   |                               |--- javax.swing.JDialog
   |--- java.io.InputStream
   |       |--- java.io.FileInputStream
   |--- java.io.OutputStream
   |       |--- java.io.FileOutputStream
   |       |--- java.io.FilterOutputStream
   |               |--- java.io.PrintStream
   |--- java.util.AbstractCollection
   |       |--- java.util.AbstractList
   |               |--- javafx.collections.ObservableListBase
   |                       |--- javafx.collections.ModifiableObservableListBase
   |                               |--- com.sun.javafx.collections.ObservableListWrapper
   |                                       |--- com.sun.javafx.collections.TrackableObservableList
   |--- jdk.nashorn.internal.runtime.PropertyMap
   
   ´´´