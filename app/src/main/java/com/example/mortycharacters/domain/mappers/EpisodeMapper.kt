package com.example.mortycharacters.domain.mappers

import com.example.mortycharacters.domain.models.Episode
import com.example.mortycharacters.network.response.EpisodeIdResponse

object EpisodeMapper {

    fun buildFrom(networkEpisode: EpisodeIdResponse): Episode {
        return Episode(
            id = networkEpisode.id,
            name = networkEpisode.name,
            airDate = networkEpisode.air_date,
            episode = networkEpisode.episode
        )
    }
}