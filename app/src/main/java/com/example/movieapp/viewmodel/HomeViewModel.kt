package com.example.movieapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.model.GenresResponse
import com.example.movieapp.model.MoviesResponse
import com.example.movieapp.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository :HomeRepository) :ViewModel() {

    val topMoviesList = MutableLiveData<MoviesResponse>()
    val genresList = MutableLiveData<GenresResponse>()
    val moviesList = MutableLiveData<MoviesResponse>()

    fun getTopMoviesViewModel() = viewModelScope.launch {
        val response = repository.getTopMoviesRepository()
        if (response.isSuccessful) {
            topMoviesList.postValue(response.body())
        }
    }

    fun getGenresViewModel() = viewModelScope.launch {
        val response = repository.getGenresRepository()
        if (response.isSuccessful) {
            genresList.postValue(response.body())
        }
    }

    fun getMoviesViewModel() = viewModelScope.launch {
        val response = repository.getMoviesRepository()
        if (response.isSuccessful) {
            moviesList.postValue(response.body())
        }
    }

}