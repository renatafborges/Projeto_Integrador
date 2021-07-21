package com.renata.projetointegrador.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.renata.projetointegrador.data.api.TheMovieDBInterface
import com.renata.projetointegrador.data.vo.MovieDetails
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

//here we will call our api using RXJAVA
//and the api will return the movieDetails
//then we'll asign the movie details in LiveData

//the constructor has the movieDBInterface
// composite disposable -> RxJava component we can use to dispose our API calls
//when we want to dispose a rxJava thread we can use composite disposable

class MovieDetailsNetworkDataSource (private val apiService : TheMovieDBInterface, private val compositeDisposable: CompositeDisposable)  {

    //create mutableLiveData of NetworkState
    //using mutable live data because live data is not mutable by nature so we can't change it
    //underscore_ means its private

    private val _networkState = MutableLiveData<NetworkState>()

    //live data of network state and this gate will be called every time we access this network state variable
    //with this get, no need to implement get function to get networkSate (setter and getter)
    val networkState: LiveData<NetworkState>

    get() = _networkState

    //here we have downloadedMovieDetailsResponse Live data
    private val _downloadedMovieDetailsResponse =  MutableLiveData<MovieDetails>()
    val downloadedMovieResponse: LiveData<MovieDetails>
        get() = _downloadedMovieDetailsResponse

    fun fetchMovieDetails(movieId: Int) {

        _networkState.postValue(NetworkState.LOADING)

        //use RxJava to make Network Calls
        //we want this thread to be disposable
        //returns a single observable

        try {
            compositeDisposable.add(
                apiService.getMovieDetails(movieId)
                        //Schedulers.io is a thread pool
                        //returns a single observable
                        // schedulers will observe this network Call
                    .subscribeOn(Schedulers.io())
                        //two parameters one for sucess and one for trouble
                    .subscribe(
                        {
                            _downloadedMovieDetailsResponse.postValue(it) //Sucess ->we post moviesdetails on MutableLiveData downloadedMovieDetailsResponse
                            _networkState.postValue(NetworkState.LOADED)
                        },
                        {
                            _networkState.postValue(NetworkState.ERROR)
                            //print a log message fot the error
                            Log.e("MovieDetailsDataSource", it.message.toString())
                        }
                    )
            )

        }
//we also print a log
        catch (e: Exception){
            Log.e("MovieDetailsDataSource", e.message.toString())
        }

    }
}