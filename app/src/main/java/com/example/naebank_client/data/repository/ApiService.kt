package com.example.naebank_client.data.repository

import com.example.naebank_client.data.Data
import retrofit2.Response
import retrofit2.http.*

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
  suspend fun getCards(
    @Query("user_id") userId: Long
  ): Response<List<Data.CardResponse>>

  @GET("cards/{id}")
  suspend fun getCardById(
    @Path("id") id: Long,
  ): Response<Data.CardResponse>

  @POST("cards")
  suspend fun addCard(
    @Body request: Data.CardRequest
  ): Response<Data.GeneralResponse>

  @PATCH("cards")
  suspend fun updateCard(
    @Body request: Data.CardAmountRequest
  ): Response<Data.CardResponse>

  @DELETE("cards/{id}")
  suspend fun deleteCard(
    @Path("id") id: Long
  ): Response<String>
}