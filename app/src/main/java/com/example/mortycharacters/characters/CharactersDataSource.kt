package com.example.mortycharacters.characters

import androidx.paging.PageKeyedDataSource
import com.example.mortycharacters.SharedRepository
import com.example.mortycharacters.SharedViewModel
import com.example.mortycharacters.model.MortyCharacter
import com.example.mortycharacters.repository.CharactersRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class CharactersDataSource(
    private val coroutineScope: CoroutineScope,
    private val repository: CharactersRepository
): PageKeyedDataSource<Int, MortyCharacter>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, MortyCharacter>
    ) {
        coroutineScope.launch {
            val characterList = repository.getCharactersList(1)
            callback.onResult(characterList,null, 2)
        }
    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, MortyCharacter>) {
        coroutineScope.launch {
            val characterList = repository.getCharactersList(params.key)
            callback.onResult(characterList, params.key + 1)
        }    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, MortyCharacter>) {
        TODO("Not yet implemented")
    }

}