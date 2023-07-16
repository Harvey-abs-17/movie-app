package com.example.movieapp.repository

import com.example.movieapp.local.FavDao
import com.example.movieapp.model.FavoriteEntity
import javax.inject.Inject

class FavoriteRepository @Inject constructor(private val dao :FavDao) {

    fun getAllMoviesRepository() :List<FavoriteEntity> = dao.getAllFavMovies()

}