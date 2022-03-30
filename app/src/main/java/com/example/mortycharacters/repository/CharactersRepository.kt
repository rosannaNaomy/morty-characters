package com.example.mortycharacters.repository

import com.example.mortycharacters.model.CharacterPage
import com.example.mortycharacters.model.MortyCharacter
import com.example.mortycharacters.network.NetworkLayer

class CharactersRepository {

    suspend fun getCharactersPage(pageIndex: Int): CharacterPage?{
        val request = NetworkLayer.apiClient.getCharactersPage(pageIndex)

        if(request.failed || !request.isSuccessful){
            return null
        }
        return request.body
    }
}