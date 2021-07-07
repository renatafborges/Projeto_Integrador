package com.renata.projetointegrador.data.model

import kotlinx.serialization.Serializable

@Serializable

data class Movie (
    val title: String,  //titulo
    val release_date: String, //ano
    val popularity: Number, //avaliação
    val runtime: Int, //duração
    val id: String, //id do film
    val overview: String,   //sinopse
    val adult: Boolean,

//    val "elenco" -> não encontrei
//    val gender: Array //genero é um Array
//    val vote_average: Number, //avaliação?
//    val vote_count: Integer, //avaliação?

)


