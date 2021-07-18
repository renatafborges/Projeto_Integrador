package com.renata.projetointegrador.data.api

import com.renata.projetointegrador.data.vo.MovieDetails
import io.reactivex.Single //type of observable in rxJava
import retrofit2.http.GET
import retrofit2.http.Path

interface TheMovieDBInterface {

// https://api.themoviedb.org/3/movie/popular?api_key=893ae533a711d05e99a29d52a30419ed&page=1 **movieList
// https://api.themoviedb.org/3/movie/497698?api_key=893ae533a711d05e99a29d52a30419ed **movieDetails
// https://api.themoviedb.org/3/genre/movie/list?api_key=893ae533a711d05e99a29d52a30419ed **genreList
//cast?
//https://api.themoviedb.org/3 BASE URL

    //this function will return a single of type movie details
    @GET("movie/{movie_id}")
    fun getMovieDetails(@Path("movie_id") id: Int): Single<MovieDetails> //two key components: Observable and Observer
}