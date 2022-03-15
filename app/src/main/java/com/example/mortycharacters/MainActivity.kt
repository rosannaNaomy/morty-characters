package com.example.mortycharacters

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.example.mortycharacters.model.MortyCharacter
import com.example.mortycharacters.network.RMortyService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        networkCall()
    }

    private fun networkCall() {
        val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

        val rMortyService: RMortyService = retrofit.create(RMortyService::class.java)

        rMortyService.getCharacterbyId(5).enqueue(object : Callback<MortyCharacter> {
            override fun onResponse(call: Call<MortyCharacter>, response: Response<MortyCharacter>) {

                if(!response.isSuccessful){
                    Log.d("MainActivity", "unsuccessful")
                }
                Log.d("MainActivity", response.toString())
                val body = response.body()!!

                fillViews(body)
            }

            override fun onFailure(call: Call<MortyCharacter>, t: Throwable) {
                Log.d("MainActivity", t.message ?: "Null message")
            }
        })
    }

    private fun fillViews(body: MortyCharacter) {
        val name = name_textview
        name_textview.text = body.name
    }
}