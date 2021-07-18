package com.renata.projetointegrador.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.renata.projetointegrador.R
import com.renata.projetointegrador.ui.single_movie_details.SingleMovie

class MainActivity : AppCompatActivity() {

     private lateinit var submitSearch : ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        submitSearch = findViewById(R.id.submitSearch)

        submitSearch.setOnClickListener {
            val intent = Intent(this, SingleMovie::class.java)
            intent.putExtra("id", 497698)
            this.startActivity(intent)
        }


        }
    }