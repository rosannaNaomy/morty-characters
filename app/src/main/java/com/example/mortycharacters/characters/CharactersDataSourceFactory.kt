package com.example.mortycharacters.characters

import androidx.paging.DataSource
import com.example.mortycharacters.network.response.CharacterResponse
import com.example.mortycharacters.repository.CharactersRepository
import kotlinx.coroutines.CoroutineScope

class CharactersDataSourceFactory(
    private val coroutineScope: CoroutineScope,
    private val repository: CharactersRepository
): DataSource.Factory<Int, CharacterResponse>() {

    override fun create(): DataSource<Int, CharacterResponse> {
        return CharactersDataSource(coroutineScope, repository)
    }
}