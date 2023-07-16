package com.example.movieapp.local

import android.content.Context
import androidx.room.Room
import com.example.movieapp.model.FavoriteEntity
import com.example.movieapp.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context :Context) = Room.databaseBuilder(
        context,
        FavoriteDatabase::class.java,
        Constants.DATA_BASE_NAME
    ).allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun provideDao(db: FavoriteDatabase) = db.dao()

    @Provides
    fun provideFavEntity() = FavoriteEntity()

}