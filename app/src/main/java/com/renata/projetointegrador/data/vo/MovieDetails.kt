package com.renata.projetointegrador.data.vo

data class MovieDetails(
    val adult: Boolean,
    val genres: List<Genre>,
    val id: Int,
    val imdb_id: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val runtime: Int,
    val title: String,
    val vote_average: Double,
    val vote_count: Int

)