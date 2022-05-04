package com.example.mortycharacters.repository

import com.example.mortycharacters.network.NetworkLayer
import com.example.mortycharacters.network.response.PageInfoResponse

class CharactersRepository {

    suspend fun getCharactersPage(pageIndex: Int): PageInfoResponse?{
        val request = NetworkLayer.apiClient.getCharactersPage(pageIndex)

        if(request.failed || !request.isSuccessful){
            return null
        }
        return request.body
    }
}