package com.example.movieapp.api

import com.example.movieapp.model.UserRegisterBody
import com.example.movieapp.model.UserRegisterResponse
import com.example.movieapp.utils.Constants
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiServices {

    @POST(Constants.POST_USER_URL)
    suspend fun postUserData(@Body userBody :UserRegisterBody) :Response<UserRegisterResponse>

}