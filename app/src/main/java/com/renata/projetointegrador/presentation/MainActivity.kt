package com.renata.projetointegrador.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.renata.projetointegrador.data.model.Genre
import com.renata.projetointegrador.R

class MainActivity : AppCompatActivity() {

    private lateinit var rvGenre : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        rvGenre = findViewById<RecyclerView>(R.id.rvGenre)

        //DATASET
        val genrelist = mutableListOf<Genre>(
            Genre( "AÇÃO") ,
            Genre( "AVENTURA"),
            Genre( "ANIMAÇÃO"),
            Genre( "FANTASIA")
        )
//        rvGenre.adapter
//        rvGenre.layoutManager

        }
    }