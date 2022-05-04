package com.example.mortycharacters.network.response

data class EpisodeIdResponse(
    val air_date: String,
    val characters: List<String> = listOf(),
    val created: String,
    val episode: String,
    val id: Int = 0,
    val name: String,
    val url: String
)