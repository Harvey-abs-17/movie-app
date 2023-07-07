package com.example.movieapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.model.UserRegisterBody
import com.example.movieapp.model.UserRegisterResponse
import com.example.movieapp.repository.RegisterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val repository :RegisterRepository) :ViewModel() {

    val userDataViewModel = MutableLiveData<UserRegisterResponse>()
    val loadingViewModel = MutableLiveData<Boolean>()

    fun getUserDataViewModel(userBody :UserRegisterBody) = viewModelScope.launch {
        loadingViewModel.postValue(true)
        val response = repository.postUserDataRepository(userBody)
        if (response.isSuccessful) {
            userDataViewModel.postValue(response.body())
        }
        loadingViewModel.postValue(false)
    }
}