package com.example.naebank_client.data.repository

import com.example.naebank_client.data.Data
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

  @POST("/auth/register")
  suspend fun register(
    @Body request: Data.RegisterRequest
  ): Response<Data.RegisterResponse>

  @POST("auth/login")
  suspend fun login(
    @Body request: Data.LoginRequest
  ): Response<Data.GeneralResponse>

  @GET("users/current")
  suspend fun getCurrentUser(): Response<Data.UserResponse>

  @GET("cards")
  suspend fun getCards(): Response<List<Data.CardResponse>>

  @POST("cards")
  suspend fun addCard(
    @Body request: Data.CardRequest
  ): Response<Data.GeneralResponse>
}