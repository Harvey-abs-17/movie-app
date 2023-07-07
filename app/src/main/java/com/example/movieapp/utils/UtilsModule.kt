package com.example.movieapp.utils

import com.example.movieapp.model.UserRegisterBody
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UtilsModule {

    @Provides
    @Singleton
    fun provideUserRegisterBody() = UserRegisterBody()
}