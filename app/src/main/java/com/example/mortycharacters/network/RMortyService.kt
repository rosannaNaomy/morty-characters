package com.example.mortycharacters.network

import com.example.mortycharacters.model.MortyCharacter
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RMortyService {

    @GET("character/{character-id}")
    fun getCharacterbyId(@Path("character-id") characterId: Int): Call<MortyCharacter>

}