package com.example.movieapp.api

import com.example.movieapp.model.MoviesResponse
import com.example.movieapp.model.UserRegisterBody
import com.example.movieapp.model.UserRegisterResponse
import com.example.movieapp.utils.Constants
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServices {

    @POST(Constants.POST_USER_URL)
    suspend fun postUserData(@Body userBody: UserRegisterBody): Response<UserRegisterResponse>

    @GET("${Constants.GET_MOVIE_URL}/{genre_id}/movies/")
    suspend fun getTopMovies(
        @Path("genre_id") id: Int = 1
    ): Response<MoviesResponse>

}