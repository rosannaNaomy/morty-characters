package com.example.mortycharacters.network

import com.example.mortycharacters.model.MortyCharacter
import retrofit2.Response

class ApiClient(private val rMService: RMortyService) {
    suspend fun getCharacterbyId(characterId: Int): Response<MortyCharacter>{
        return rMService.getCharacterbyId(characterId)
    }
}
