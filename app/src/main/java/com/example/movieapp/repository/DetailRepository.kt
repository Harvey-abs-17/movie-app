package com.example.movieapp.repository

import com.example.movieapp.api.ApiServices
import javax.inject.Inject

class DetailRepository @Inject constructor(private val api :ApiServices) {

    suspend fun getDetailsMovieRepository(id :Int) = api.getDetailsMovie(id)

}