package com.example.mortycharacters

import com.example.mortycharacters.model.MortyCharacter
import com.example.mortycharacters.network.NetworkLayer

class SharedRepository {

    suspend fun getCharacterById(characterId: Int): MortyCharacter? {
        val request = NetworkLayer.apiClient.getCharacterbyId(characterId)

        if(request.failed){
            return null
        }
        if(!request.isSuccessful){
            return null
        }
        return request.body
    }
}
