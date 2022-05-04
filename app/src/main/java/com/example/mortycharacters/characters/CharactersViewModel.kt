package com.example.mortycharacters.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.mortycharacters.Constants
import com.example.mortycharacters.network.response.CharacterResponse
import com.example.mortycharacters.repository.CharactersRepository

class CharactersViewModel: ViewModel() {

    private val repository = CharactersRepository()
    private val pageListconfig: PagedList.Config = PagedList.Config.Builder()
        .setPageSize(Constants.PAGE_SIZE)
        .setPrefetchDistance(Constants.PREFECTCH_DISTANCE)
        .build()
    private val  dataSourceFactory = CharactersDataSourceFactory(viewModelScope, repository)
    val charactersPagedListLiveData: LiveData<PagedList<CharacterResponse>> = LivePagedListBuilder(
        dataSourceFactory, pageListconfig
    ).build()
}