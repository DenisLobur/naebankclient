package com.example.naebank_client.data.repository

import com.example.naebank_client.ErrorParser
import com.example.naebank_client.RequestHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.example.naebank_client.Result.*
import com.example.naebank_client.data.Data

class Repo(val api: ApiService, val errorParser: ErrorParser) {
  suspend fun registerUser(name: String, email: String, password: String) = withContext(Dispatchers.IO) {
    RequestHandler(
      errorParser = errorParser,
      onRequest = {
        api.register(
          Data.RegisterRequest(
            email = email,
            password = password,
            name = name,
            role = "user"
          )
        )
      },
      onResult = {
        Success(it)
      }
    ).request()
  }

  suspend fun loginUser(email: String, password: String) = withContext(Dispatchers.IO) {
    RequestHandler(
      errorParser = errorParser,
      onRequest = {
        api.login(
          Data.LoginRequest(
            email = email,
            password = password
          )
        )
      },
      onResult = {
        Success(it)
      }
    ).request()
  }

  suspend fun getUser() = withContext(Dispatchers.IO) {
    RequestHandler(
      errorParser = errorParser,
      onRequest = {
        api.getCurrentUser()
      },
      onResult = {
        Success(it)
      }
    ).request()
  }

  suspend fun getCards(id: Long) = withContext(Dispatchers.IO) {
    RequestHandler(
      errorParser = errorParser,
      onRequest = {
        api.getCards(userId = id)
      },
      onResult = {
        Success(it)
      }
    ).request()
  }

  suspend fun getCardById(userId: Long, cardId: Long) = withContext(Dispatchers.IO) {
    RequestHandler(
      errorParser = errorParser,
      onRequest = {
        api.getCardById(
          id = cardId
        )
      },
      onResult = {
        Success(it)
      }
    ).request()
  }

  suspend fun addCard(type: String, mask: String, expMonth: Int, expYear: Int, isDefault: Boolean) = withContext(Dispatchers.IO) {
    RequestHandler(
      errorParser = errorParser,
      onRequest = {
        api.addCard(
          Data.CardRequest(
            type, mask, expMonth, expYear, isDefault
          )
        )
      },
      onResult = {
        Success(it)
      }
    ).request()
  }

  suspend fun deleteCard(id: Long) = withContext(Dispatchers.IO) {
    RequestHandler(
      errorParser = errorParser,
      onRequest = {
        api.deleteCard(id)
      },
      onResult = {
        Success(it)
      }
    ).request()
  }

}