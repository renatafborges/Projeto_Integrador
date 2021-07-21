package com.renata.projetointegrador.ui.single_movie_details

import androidx.lifecycle.LiveData
import com.renata.projetointegrador.data.api.TheMovieDBInterface
import com.renata.projetointegrador.data.repository.MovieDetailsNetworkDataSource
import com.renata.projetointegrador.data.repository.NetworkState
import com.renata.projetointegrador.data.vo.MovieDetails
import io.reactivex.disposables.CompositeDisposable

//the constructor has api service wich is the moviedb interface

class MovieDetailsRepository (private val apiService : TheMovieDBInterface) {

    //a variable for movieDetailsNetworkDataSource, late initialization
    lateinit var movieDetailsNetworkDataSource: MovieDetailsNetworkDataSource

    //have parameters compositeDisposable and movieId, the return type is Live Data Movie Details
    fun fetchSingleMovieDetails (compositeDisposable: CompositeDisposable, movieId: Int) :LiveData<MovieDetails> {

        //inicialize movie movieDetailsNetworkDataSource with API service
        movieDetailsNetworkDataSource = MovieDetailsNetworkDataSource(apiService, compositeDisposable)
        movieDetailsNetworkDataSource.fetchMovieDetails(movieId)

        return movieDetailsNetworkDataSource.downloadedMovieResponse
    }
    //function to get networdState LiveData
    //if we want to cache the data in local storage this is where you do it
    fun getMovieDetailsNetworkState(): LiveData<NetworkState> {
        return movieDetailsNetworkDataSource.networkState

    }
}