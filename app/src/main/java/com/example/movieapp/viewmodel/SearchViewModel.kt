package com.example.movieapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.model.MoviesResponse
import com.example.movieapp.repository.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val repository: SearchRepository) : ViewModel() {

    val searchListResult = MutableLiveData<MoviesResponse>()
    val loading = MutableLiveData<Boolean>()
    val isEmpty = MutableLiveData<Boolean>()

    fun getSearchResultViewModel(name: String) = viewModelScope.launch {
        loading.postValue(true)
        val response = repository.getSearchResultRepository(name)
        if (response.isSuccessful) {
            searchListResult.postValue(response.body())
            if (response.body()?.data?.isEmpty()!!) {
                isEmpty.postValue(true)
            } else {
                isEmpty.postValue(false)
            }

        }
        loading.postValue(false)
    }

}