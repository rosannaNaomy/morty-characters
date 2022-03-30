package com.example.mortycharacters.network

import com.example.mortycharacters.model.CharacterPage
import com.example.mortycharacters.model.MortyCharacter
import retrofit2.Response

class ApiClient(private val rMService: RMortyService) {

    suspend fun getCharacterbyId(characterId: Int): NetworkResponse<MortyCharacter>{
        return safeApiCall { rMService.getCharacterbyId(characterId)}
    }

    suspend fun getCharactersPage(pageIndex: Int): NetworkResponse<CharacterPage>{
        return safeApiCall { rMService.getCharactersPage(pageIndex) }
    }

    private inline fun <T> safeApiCall(apiCall: () -> Response<T>): NetworkResponse<T> {
        return try {
            NetworkResponse.success(apiCall.invoke())
        }catch (e: Exception){
            NetworkResponse.failure(e)
        }
    }
}
