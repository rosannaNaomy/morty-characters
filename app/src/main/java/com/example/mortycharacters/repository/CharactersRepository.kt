package com.example.mortycharacters.repository

import com.example.mortycharacters.model.MortyCharacter

class CharactersRepository {

    suspend fun getCharactersList(pageIndex: Int):  List<MortyCharacter>{
        return emptyList()
    }
}