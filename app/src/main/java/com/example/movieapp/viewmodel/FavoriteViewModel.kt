package com.example.movieapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.model.FavoriteEntity
import com.example.movieapp.repository.FavoriteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val repository: FavoriteRepository) :ViewModel() {

    val favoriteList = MutableLiveData<List<FavoriteEntity>>()
    val loading = MutableLiveData<Boolean>()

    fun getAllMoviesViewModel(){
        loading.postValue(true)
        val data = repository.getAllMoviesRepository()
        favoriteList.postValue(data)
        loading.postValue(false)
    }

}