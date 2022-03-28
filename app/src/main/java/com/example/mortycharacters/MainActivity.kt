package com.example.mortycharacters

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.ViewModelProvider
import com.example.mortycharacters.model.MortyCharacter
import com.example.mortycharacters.network.NetworkLayer
import com.example.mortycharacters.network.NetworkLayer.rMortyService
import com.example.mortycharacters.network.NetworkLayer.retrofit
import com.example.mortycharacters.network.RMortyService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainActivity : AppCompatActivity() {
    val viewModel: SharedViewModel by lazy {
        ViewModelProvider(this)[SharedViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.refreshCharacter(5)
        viewModel.characterByIdLiveData.observe(this){ response ->
            if(response == null){
                Toast.makeText(this@MainActivity, "Unsuccessful network call.", Toast.LENGTH_SHORT).show()
                return@observe
            }
            fillViews(response)
        }
    }


    private fun fillViews(body: MortyCharacter) {
        Picasso.get().load(body.image).into(header_imageview)
        val name = name_textview
        name.text = body.name

        if(body.gender.equals("male", true)){
            gender_imageview.setImageResource(R.drawable.ic_baseline_male_24)
        }else{
            gender_imageview.setImageResource(R.drawable.ic_baseline_female_24)
        }

    }
}