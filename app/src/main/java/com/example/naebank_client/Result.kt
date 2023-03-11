package com.example.naebank_client

sealed class Result<out T> {

  data class Success<out T>(val data: T) : Result<T>()

  sealed class Error : Result<Nothing>() {
    data class GenericError(val exception: Throwable) : Error()
    data class RequestError(val errorMessage: String) : Error()
    data class ServerError(val code: Int) : Error()
  }
}