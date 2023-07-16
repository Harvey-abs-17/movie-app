package com.example.movieapp.repository

import com.example.movieapp.api.ApiServices
import com.example.movieapp.local.FavDao
import com.example.movieapp.model.FavoriteEntity
import javax.inject.Inject

class DetailRepository @Inject constructor(private val api :ApiServices, private val dao :FavDao) {

    suspend fun getDetailsMovieRepository(id :Int) = api.getDetailsMovie(id)

    suspend fun insertMovieToFavListRepository(movie :FavoriteEntity) = dao.insertMovieToFavList(movie)

    suspend fun deleteMovieFromFavListRepository(movie :FavoriteEntity) = dao.deleteMovieFromFavList(movie)

    suspend fun checkIsExistRepository(id :Int) = dao.checkIsExist(id)

}