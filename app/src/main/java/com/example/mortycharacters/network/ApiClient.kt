package com.example.mortycharacters.network

import com.example.mortycharacters.network.response.CharacterResponse
import com.example.mortycharacters.network.response.EpisodeIdResponse
import com.example.mortycharacters.network.response.PageInfoResponse
import retrofit2.Response

class ApiClient(private val rMService: RMortyService) {

    suspend fun getCharacterbyId(characterId: Int): NetworkResponse<CharacterResponse>{
        return safeApiCall { rMService.getCharacterbyId(characterId)}
    }

    suspend fun getCharactersPage(pageIndex: Int): NetworkResponse<PageInfoResponse>{
        return safeApiCall { rMService.getCharactersPage(pageIndex) }
    }

    private inline fun <T> safeApiCall(apiCall: () -> Response<T>): NetworkResponse<T> {
        return try {
            NetworkResponse.success(apiCall.invoke())
        }catch (e: Exception){
            NetworkResponse.failure(e)
        }
    }

    suspend fun getEpisodeById(episodeId: Int): NetworkResponse<EpisodeIdResponse> {
        return safeApiCall { rMService.getEpisodeById(episodeId) }
    }

    suspend fun getEpisodeRange(episodeRange: String): NetworkResponse<List<EpisodeIdResponse>> {
        return safeApiCall { rMService.getEpisodeRange(episodeRange) }
    }

//    suspend fun getEpisodesPage(pageIndex: Int): NetworkResponse<GetEpisodesPageResponse> {
//        return safeApiCall { rickAndMortyService.getEpisodesPage(pageIndex) }
//    }
}
