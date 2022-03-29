package com.example.mortycharacters

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.ViewModelProvider
import com.example.mortycharacters.epoxy.CharacterDetailsEpoxyController
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
    private val epoxyController = CharacterDetailsEpoxyController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.characterByIdLiveData.observe(this){ response ->
            epoxyController.characterResponse = response
            if(response == null){
                Toast.makeText(this@MainActivity, "Unsuccessful network call.", Toast.LENGTH_SHORT).show()
                return@observe
            }
        }
        viewModel.refreshCharacter(50)

        val epoxyRecyclerV = epoxyRecyclerView
        epoxyRecyclerV.setControllerAndBuildModels(epoxyController)
    }

}