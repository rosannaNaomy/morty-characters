package com.example.mortycharacters.domain.mappers

import com.example.mortycharacters.domain.models.Character
import com.example.mortycharacters.network.response.CharacterResponse
import com.example.mortycharacters.network.response.EpisodeIdResponse

object CharacterMapper {

    fun buildFrom(
        response: CharacterResponse,
        episodes: List<EpisodeIdResponse>
    ): Character {
        return Character(
            episodeList = episodes.map {
                EpisodeMapper.buildFrom(it)
            },
            gender = response.gender,
            id = response.id,
            image = response.image,
            location = Character.Location(
                name = response.location.name,
                url = response.location.url
            ),
            name = response.name,
            origin = Character.Origin(
                name = response.origin.name,
                url = response.origin.url
            ),
            species = response.species,
            status = response.status
        )
    }
}