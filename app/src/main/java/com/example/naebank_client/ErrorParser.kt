package com.example.naebank_client

import com.google.gson.Gson
import retrofit2.Response

class ErrorParser(val gson: Gson) {

  fun <T> handleError(response: Response<T>): Result.Error {
    val code = response.code()

    return if (code in 500..599) {
      Result.Error.ServerError(code)
    } else {
      return getErrorMessage(response.errorBody()?.string())
    }
  }

  private fun parseErrorMessage(rawBody: String?): String = gson.fromJson(rawBody, String::class.java)

  private fun getErrorMessage(rawBody: String?): Result.Error {
    if (rawBody.isNullOrEmpty()) {
      return Result.Error.GenericError(
        IllegalArgumentException("Empty error body")
      )
    }
    val error = parseErrorMessage(rawBody)
    return Result.Error.RequestError(
      "Error: $error"
    )
  }
}