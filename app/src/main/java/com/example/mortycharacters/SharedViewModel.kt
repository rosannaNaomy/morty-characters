package com.example.mortycharacters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mortycharacters.model.MortyCharacter
import kotlinx.coroutines.launch

class SharedViewModel: ViewModel() {

    private val repository = SharedRepository()

    private val _charByIdLiveData = MutableLiveData<MortyCharacter>()
    val characterByIdLiveData: LiveData<MortyCharacter> = _charByIdLiveData //just consume information, no change

    fun refreshCharacter(characterId: Int){
        viewModelScope.launch {
            val response = repository.getCharacterById(characterId)
            _charByIdLiveData.postValue(response)
        }
    }
}