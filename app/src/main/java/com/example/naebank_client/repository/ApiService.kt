package com.example.naebank_client.repository

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

  @POST("/auth/register")
  suspend fun register(
    @Body request: String
  ): Response<String>
}