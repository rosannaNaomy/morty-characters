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
            val page = repository.getCharactersPage(1)

            if(page == null){
                callback.onResult(emptyList(),null, null)
                return@launch
            }
            callback.onResult(page.results, null, getPageIndexFromNext(page.info.next))
        }
    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, MortyCharacter>) {
        coroutineScope.launch {
            val page = repository.getCharactersPage(params.key)

            if (page == null){
                callback.onResult(emptyList(), null)
                return@launch
            }
            callback.onResult(page.results, getPageIndexFromNext(page.info.next))
        }
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, MortyCharacter>
    ) {
        TODO("Not yet implemented")
    }

    private fun getPageIndexFromNext(next: String?): Int?{
        return next?.split("?page=")?.get(1)?.toInt()
    }

}