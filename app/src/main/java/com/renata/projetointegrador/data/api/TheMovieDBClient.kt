package com.renata.projetointegrador.data.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


const val API_KEY = "893ae533a711d05e99a29d52a30419ed"
const val BASE_URL = "https://api.themoviedb.org/3/"
const val POSTER_BASE_URL = "https://image.tmdb.org/t/p/w342"

// https://api.themoviedb.org/3/movie/497698?api_key=893ae533a711d05e99a29d52a30419ed **movieDetails
// https://api.themoviedb.org/3/movie/popular?api_key=893ae533a711d05e99a29d52a30419ed&page=1 **movieList
// "https://image.tmdb.org/t/p/w342/qAZ0pzat24kLdO3o8ejmbLxyOac.jpg"

object TheMovieDBClient {

//create a interceptor to put our api key in the url
    fun getClient(): TheMovieDBInterface {

        val requestInterceptor = Interceptor { chain ->
            //Interceptor take only one argument wich is Lambda function so parenthesis can be omitted


            //put the api_key in the URL
            val url = chain.request()
                .url()
                .newBuilder()
                .addQueryParameter("api_key", API_KEY)
                .build()

            //build the request with our URL and return chain.proceed with our request
            val request: Request = chain.request()
                .newBuilder()
                .url(url)
                .build()

            return@Interceptor chain.proceed(request) //explicitily return a value from whit @ annotation.
            //lambda always returns the value of the last expression implicitly
        }
//add the interceptor in okhttpClient, connectiontimeoUt for 60s
    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(requestInterceptor)
        .connectTimeout(60, TimeUnit.SECONDS)
        .build()


    //return retrofit builder, o tipo okHttpClient, the base URL, adapter factory (rxJava to call a doctor factory)
    //converter factory is Gson converter factory
    //create the movie interface with class Java reference
    return Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(BASE_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(TheMovieDBInterface::class.java)


    }
}