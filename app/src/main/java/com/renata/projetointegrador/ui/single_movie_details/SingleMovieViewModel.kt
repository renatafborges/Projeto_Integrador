package com.renata.projetointegrador.ui.single_movie_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.renata.projetointegrador.data.repository.NetworkState
import com.renata.projetointegrador.data.vo.MovieDetails
import io.reactivex.disposables.CompositeDisposable



class SingleMovieViewModel (private val movieRepository: MovieDetailsRepository, movieId: Int) : ViewModel() {

    //initialize the compositeDisposable
    private val compositeDisposable = CompositeDisposable()

    //fetch single movie details by lazy
    //we get the movie details when we need it, not when this view model class is initialized
    //using lazy for better performance
    val movieDetails : LiveData<MovieDetails> by lazy {
        movieRepository.fetchSingleMovieDetails(compositeDisposable, movieId)
    }

    val networkState : LiveData<NetworkState> by lazy {
        movieRepository.getMovieDetailsNetworkState()
    }

    //is called when the activity or fragment get destroyed
    //we dispose our composite disposable and there won't be any memory leaks
    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}