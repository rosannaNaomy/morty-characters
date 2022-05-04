package com.example.mortycharacters.model

data class CharacterPage (
    val info: Info = Info(),
    val results: List<MortyCharacter> = emptyList()
){
    data class Info(
        val count: Int = 0,
        val pages: Int = 0,
        val next: String? = null,
        val prev: String? = null
    )
}