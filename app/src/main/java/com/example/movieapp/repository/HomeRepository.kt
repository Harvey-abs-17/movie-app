package com.example.movieapp.repository

import com.example.movieapp.api.ApiServices
import javax.inject.Inject

class HomeRepository @Inject constructor(private val api :ApiServices) {

    suspend fun getTopMoviesRepository() = api.getTopMovies()

}