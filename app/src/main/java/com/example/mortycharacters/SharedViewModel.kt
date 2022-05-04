package com.example.mortycharacters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mortycharacters.domain.models.Character
import com.example.mortycharacters.network.response.CharacterResponse
import kotlinx.coroutines.launch

class SharedViewModel: ViewModel() {

    private val repository = SharedRepository()

    private val _charByIdLiveData = MutableLiveData<Character>()
    val characterByIdLiveData: LiveData<Character> = _charByIdLiveData //just consume information, no change

    fun refreshCharacter(characterId: Int){
        viewModelScope.launch {
            val response = repository.getCharacterById(characterId)
            _charByIdLiveData.postValue(response)
        }
    }
}