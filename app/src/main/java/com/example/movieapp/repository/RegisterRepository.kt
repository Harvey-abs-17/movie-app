package com.example.movieapp.repository

import com.example.movieapp.api.ApiServices
import com.example.movieapp.model.UserRegisterBody
import javax.inject.Inject

class RegisterRepository @Inject constructor(private val api :ApiServices) {

    suspend fun postUserDataRepository(userBody :UserRegisterBody) = api.postUserData(userBody)

}