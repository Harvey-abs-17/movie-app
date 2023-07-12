package com.example.movieapp.repository

import com.example.movieapp.api.ApiServices
import javax.inject.Inject

class SearchRepository @Inject constructor(private val api :ApiServices) {

    suspend fun getSearchResultRepository(name :String) = api.getSearchResult(name)

}