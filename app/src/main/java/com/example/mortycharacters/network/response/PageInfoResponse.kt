package com.example.mortycharacters.network.response

data class PageInfoResponse(
    val info: Info = Info(),
    val results: List<CharacterResponse> = emptyList()
) {
    data class Info(
        val count: Int = 0,
        val pages: Int = 0,
        val next: String? = null,
        val prev: String? = null
    )
}