package com.example.movieapp.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.movieapp.model.FavoriteEntity

@Database(entities = [FavoriteEntity::class], version = 1, exportSchema = false)
abstract class FavoriteDatabase : RoomDatabase() {
    abstract fun dao(): FavDao
}