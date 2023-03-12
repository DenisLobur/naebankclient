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
        Success(it.response)
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

}