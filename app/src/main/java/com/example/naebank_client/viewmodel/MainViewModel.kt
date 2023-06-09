package com.example.naebank_client.viewmodel

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.naebank_client.HttpProvider
import com.example.naebank_client.Result.Error
import com.example.naebank_client.Result.Success
import com.example.naebank_client.data.Data
import com.example.naebank_client.data.repository.Repo
import com.example.naebank_client.ui.card.CardOperationsFragment
import kotlinx.coroutines.launch
import java.util.Optional

class MainViewModel(val repo: Repo) : ViewModel() {

  val isLoading: ObservableBoolean = ObservableBoolean(false)
  val onError = MutableLiveData<String>()
  val onRegisterResult = MutableLiveData<Data.RegisterResponse>()
  val onLoginResult = MutableLiveData<Data.GeneralResponse?>()
  val onCurrentUser = MutableLiveData<Data.UserResponse>()
  val onCardsResult = MutableLiveData<List<Data.CardResponse>>()
  val onCardResult = MutableLiveData<Data.CardResponse>()
  val onCardAdded = MutableLiveData<Data.GeneralResponse?>()
  val onCardUpdated = SingleLiveEvent<Data.CardResponse>()
  val onCardDeleted = MutableLiveData<Data.GeneralResponse>()
  val onTransactionsResult = MutableLiveData<List<Data.TransactionResponse>>()
  val onTransactionAdded = MutableLiveData<Data.GeneralResponse?>()


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

  fun getCardsByUserId(id: Long) {
    isLoading.set(true)

    viewModelScope.launch {
      when (val cards = repo.getCardsByUserId(id = id)) {
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

  fun getCardById(cardId: Long) {
    isLoading.set(true)

    viewModelScope.launch {
      when (val cards = repo.getCardById(cardId)) {
        is Success -> {
          onCardResult.value = cards.data!!
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

  fun updateCardBalance(id: Long, amount: Int, operation: CardOperationsFragment.Companion.OPERATION) {
    isLoading.set(true)

    viewModelScope.launch {
      when (val updateCard = repo.updateCardBalance(id, amount, operation)) {
        is Success -> {
          onCardUpdated.value = updateCard.data!!
        }
        is Error -> {
          onError.value = updateCard.toString()
        }
      }

      isLoading.set(false)
    }
  }

  fun deleteCard(id: Long) {
    isLoading.set(true)

    viewModelScope.launch {
      when (val deleteCard = repo.deleteCard(id)) {
        is Success -> {
          onCardDeleted.value = deleteCard.data!!
        }
        is Error -> {
          onError.value = deleteCard.toString()
        }
      }

      isLoading.set(false)
    }
  }

  fun addTransaction(cardId: Long, amount: Int, transactionType: String) {
    isLoading.set(true)

    viewModelScope.launch {
      when (val card = repo.addTransaction(cardId, amount, transactionType)) {
        is Success -> {
          onTransactionAdded.value = card.data
        }
        is Error -> {
          onError.value = card.toString()
        }
      }

      isLoading.set(false)
    }
  }

  fun getTransactionsByUserId(id: Long, cardId: Long? = null) {
    isLoading.set(true)

    viewModelScope.launch {
      when (val trans = repo.getTransactionsByUserId(id = id, cardId = cardId)) {
        is Success -> {
          onTransactionsResult.value = trans.data!!
        }
        is Error -> {
          onError.value = trans.toString()
        }
      }

      isLoading.set(false)
    }
  }
}