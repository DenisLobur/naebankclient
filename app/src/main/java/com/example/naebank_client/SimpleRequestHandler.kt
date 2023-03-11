package com.example.naebank_client

import retrofit2.Response

class SimpleRequestHandler<ResponseType, ResultType : Any>(
  private val errorParser: ErrorParser,
  private val onRequest: suspend () -> Response<ResponseType>,
  private val onResult: suspend (responseBody: ResponseType) -> Result<ResultType>
) {
  suspend fun request(): Result<ResultType> {
    val response: Response<ResponseType>

    try {
      response = onRequest.invoke()
    } catch (e: Exception) {
      return Result.Error.GenericError(e)
    }

    if (response.isSuccessful) {
      val responseBody = response.body()!!
      return onResult.invoke(responseBody)
    } else {
      return errorParser.handleError(response)
    }
  }
}