package com.example.movieapp.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movieapp.model.FavoriteEntity

@Dao
interface FavDao {

    //select all favorite movies
    @Query("SELECT * FROM favoriteentity")
    fun getAllFavMovies(): List<FavoriteEntity>

    //insert new movie to favorite list
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMovieToFavList(movie: FavoriteEntity)

    //delete movie from favorite list
    @Delete
    suspend fun deleteMovieFromFavList(movie: FavoriteEntity)

    //check that movie is exist in list
    @Query("SELECT EXISTS (SELECT * FROM favoriteentity WHERE id = :id)")
    suspend fun checkIsExist(id: Int): Boolean


}