package com.example.naebank_client.viewmodel

import com.example.naebank_client.Result.*
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.naebank_client.HttpProvider
import com.example.naebank_client.data.Data
import com.example.naebank_client.data.repository.Repo
import kotlinx.coroutines.launch

class MainViewModel(val repo: Repo) : ViewModel() {

  val isLoading: ObservableBoolean = ObservableBoolean(false)
  val onError = MutableLiveData<String>()
  val onRegisterResult = MutableLiveData<Data.RegisterResponse>()
  val onLoginResult = MutableLiveData<Data.GeneralResponse?>()
  val onCurrentUser = MutableLiveData<Data.UserResponse>()
  val onCardsResult = MutableLiveData<List<Data.CardResponse>>()
  val onCardAdded = MutableLiveData<Data.GeneralResponse?>()

  fun registerUser(name: String, email: String, password: String) {
    isLoading.set(true)

    viewModelScope.launch {
      when (val register = repo.registerUser(name, email, password)) {
        is Success -> {
          onRegisterResult.value = register.data!!
          HttpProvider.authHeader = register.data.response
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
          HttpProvider.authHeader = login.data.response
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

  fun getCards() {
    isLoading.set(true)

    viewModelScope.launch {
      when (val cards = repo.getCards()) {
        is Success -> {
          onCardsResult.value = cards.data!!
        }
        is Error -> {
          onError.value = cards.toString()
        }
      }

      isLoading.set(false)
    }
  }

  fun addCard(type: String, mask: String, expMonth: Int, expYear: Int, isDefault: Boolean) {
    isLoading.set(true)

    viewModelScope.launch {
      when (val card = repo.addCard(type, mask, expMonth, expYear, isDefault)) {
        is Success -> {
          onCardAdded.value = card.data
        }
        is Error -> {
          onError.value = card.toString()
        }
      }

      isLoading.set(false)
    }
  }
}