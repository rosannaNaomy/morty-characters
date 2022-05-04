package com.example.mortycharacters.network

import com.example.mortycharacters.network.response.CharacterResponse
import com.example.mortycharacters.network.response.EpisodeIdResponse
import com.example.mortycharacters.network.response.PageInfoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RMortyService {

    @GET("character/{character-id}")
    suspend fun getCharacterbyId(
        @Path("character-id") characterId: Int
    ): Response<CharacterResponse>

    @GET("character")
    suspend fun getCharactersPage(
        @Query("page") pageIndex: Int
    ): Response<PageInfoResponse>

    @GET("episode/{episode-id}")
    suspend fun getEpisodeById(
        @Path("episode-id") episodeId: Int
    ): Response<EpisodeIdResponse>

    @GET("episode/{episode-range}")
    suspend fun getEpisodeRange(
        @Path("episode-range") episodeRange: String
    ): Response<List<EpisodeIdResponse>>

    @GET("episode/")
    suspend fun getEpisodesPage(
        @Query("page") pageIndex: Int
    ): Response<EpisodeIdResponse>
}