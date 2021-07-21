package com.renata.projetointegrador.ui.single_movie_details

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.renata.projetointegrador.R
import com.renata.projetointegrador.data.api.POSTER_BASE_URL
import com.renata.projetointegrador.data.api.TheMovieDBClient
import com.renata.projetointegrador.data.api.TheMovieDBInterface
import com.renata.projetointegrador.data.repository.NetworkState
import com.renata.projetointegrador.data.vo.MovieDetails
import java.util.*


class SingleMovie: AppCompatActivity() {

    private lateinit var movieTitle : TextView
    private lateinit var ratingMovieInfoAct : TextView
    private lateinit var movieYear: TextView
    private lateinit var ageRestriction : TextView
    private lateinit var movieDuration : TextView
    private lateinit var movieSynopsis: TextView
    private lateinit var posterMovie: ImageView

    private lateinit var viewModel: SingleMovieViewModel
    private lateinit var movieRepository : MovieDetailsRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        movieTitle = findViewById(R.id.movieTitle)
        ratingMovieInfoAct = findViewById(R.id.ratingMovieInfoAct)
        movieYear = findViewById(R.id.movieYear)
        ageRestriction = findViewById(R.id.ageRestriction)
        movieDuration = findViewById(R.id.movieDuration)
        movieSynopsis = findViewById(R.id.movieSynopsis)
        posterMovie = findViewById(R.id.posterMovie)

        val movieId: Int = intent.getIntExtra("id", 1)

        val apiService: TheMovieDBInterface = TheMovieDBClient.getClient()
        movieRepository = MovieDetailsRepository(apiService)

        viewModel = getViewModel(movieId)
        viewModel.movieDetails.observe(this, Observer {
            bindUI(it)
        })

//        viewModel.networkState.observe(this, Observer {
//            progress_bar.visibility = if (it == NetworkState.LOADING) View.VISIBLE else View.GONE
//            txt_error.visibility = if (it == NetworkState.ERROR) View.VISIBLE else View.GONE
//
//        })

    }


    fun bindUI( it: MovieDetails){

        movieTitle.text = it.title
        movieYear.text = it.release_date
        ageRestriction.text = it.adult.toString()
        movieDuration.text = it.runtime.toString() + " minutes"
        movieSynopsis.text = it.overview
        ratingMovieInfoAct.text = it.popularity.toString()

        val moviePosterURL: String = POSTER_BASE_URL + it.poster_path
        Glide.with(this)
            .load(moviePosterURL)
            .into(posterMovie);

    }

    private fun getViewModel(movieId:Int): SingleMovieViewModel {
        return ViewModelProvider(this, object: ViewModelProvider.Factory {
            override fun<T : ViewModel?> create(modelClass: Class<T>): T {
                return SingleMovieViewModel(movieRepository,movieId) as T
            }
        })[SingleMovieViewModel::class.java]
    }
}