package com.example.naebank_client.viewmodel

import com.example.naebank_client.Result.*
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.naebank_client.data.Data
import com.example.naebank_client.data.repository.Repo
import kotlinx.coroutines.launch

class MainViewModel(val repo: Repo) : ViewModel() {

  val isLoading: ObservableBoolean = ObservableBoolean(false)
  val onError = MutableLiveData<String>()
  val onRegisterResult = MutableLiveData<String?>()
  val onLoginResult = MutableLiveData<Data.LoginResponse>()
  val onCurrentUser = MutableLiveData<Data.UserResponse>()

  fun registerUser(name: String, email: String, password: String) {
    isLoading.set(true)

    viewModelScope.launch {
      when (val register = repo.registerUser(name, email, password)) {
        is Success -> {
          onRegisterResult.value = register.data
        }
        is Error -> {
          onError.value = register.toString()
        }
      }

      isLoading.set(false)
    }
  }

  fun loginUser(email: String, password: String) {
    isLoading.set(true)

    viewModelScope.launch {
      when (val login = repo.loginUser(email, password)) {
        is Success -> {
          onLoginResult.value = login.data!!
        }
        is Error -> {
          onError.value = login.toString()
        }
      }

      isLoading.set(false)
    }
  }

  fun getUser() {
    isLoading.set(true)

    viewModelScope.launch {
      when (val user = repo.getUser()) {
        is Success -> {
          onCurrentUser.value = user.data!!
        }
        is Error -> {
          onError.value = user.toString()
        }
      }

      isLoading.set(false)
    }
  }
}