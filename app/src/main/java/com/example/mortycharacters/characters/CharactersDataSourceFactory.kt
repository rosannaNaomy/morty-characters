package com.example.mortycharacters.characters

import androidx.paging.DataSource
import com.example.mortycharacters.model.MortyCharacter
import com.example.mortycharacters.repository.CharactersRepository
import kotlinx.coroutines.CoroutineScope

class CharactersDataSourceFactory(
    private val coroutineScope: CoroutineScope,
    private val repository: CharactersRepository
): DataSource.Factory<Int, MortyCharacter>() {

    override fun create(): DataSource<Int, MortyCharacter> {
        return CharactersDataSource(coroutineScope, repository)
    }
}