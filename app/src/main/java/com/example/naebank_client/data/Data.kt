package com.example.naebank_client.data

import com.google.gson.annotations.SerializedName

object Data {

  // Requests
  data class RegisterRequest(
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String,
    @SerializedName("name") val name: String,
    @SerializedName("role") val role: String
  )

  data class LoginRequest(
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String
  )

  // Response
  data class RegisterResponse(
    @SerializedName("response") val response: String
  )

  data class LoginResponse(
    @SerializedName("response") val response: String,
    @SerializedName("error") val error: String?
  )

  data class UserResponse(
    @SerializedName("name") val name: String,
    @SerializedName("email") val email: String,
    @SerializedName("role") val role: String
  )
}