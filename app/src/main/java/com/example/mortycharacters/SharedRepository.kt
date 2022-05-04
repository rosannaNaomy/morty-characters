package com.example.mortycharacters

import com.example.mortycharacters.domain.mappers.CharacterMapper
import com.example.mortycharacters.domain.models.Character
import com.example.mortycharacters.network.NetworkLayer
import com.example.mortycharacters.network.response.CharacterResponse
import com.example.mortycharacters.network.response.EpisodeIdResponse

class SharedRepository {

    suspend fun getCharacterById(characterId: Int): Character? {
        val request = NetworkLayer.apiClient.getCharacterbyId(characterId)

        if(request.failed || !request.isSuccessful){
            return null
        }
        val networkEpisodes = getEpisodesFromCharacterResponse(request.body)

        return CharacterMapper.buildFrom(
            response = request.body,
        episodes = networkEpisodes)
    }

    private suspend fun getEpisodesFromCharacterResponse(
        characterResponse: CharacterResponse
    ): List<EpisodeIdResponse>{
        val episodeRange = characterResponse.episode.map{
            it.substring(it.lastIndexOf("/") + 1)
        }.toString()
        val request = NetworkLayer.apiClient.getEpisodeRange(episodeRange)

        if(request.failed || !request.isSuccessful){
            return emptyList()
        }
        return request.body
    }
}
