package com.example.movieapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.model.DetailsResponse
import com.example.movieapp.repository.DetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val repository :DetailRepository) :ViewModel(){

    val detail = MutableLiveData<DetailsResponse>()
    val loading = MutableLiveData<Boolean>()

    fun getDetailsMovieViewModel(id :Int) = viewModelScope.launch {
        val response = repository.getDetailsMovieRepository(id)
        loading.postValue(true)
        if (response.isSuccessful){
            detail.postValue(response.body())
        }
        loading.postValue(false)
    }


}