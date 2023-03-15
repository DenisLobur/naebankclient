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

  data class CardRequest(
    @SerializedName("type") val type: String,
    @SerializedName("cardMask") val mask: String,
    @SerializedName("expMonth") val expMonth: Int,
    @SerializedName("expYear") val expYear: Int,
    @SerializedName("isDefault") val isDefault: Boolean
  )

  data class CardAmountRequest(
    @SerializedName("id") val id: Long,
    @SerializedName("amount") val amount: Int
  )

  // Response
  data class RegisterResponse(
    @SerializedName("response") val response: String,
    @SerializedName("error") val error: String?
  )

  data class GeneralResponse(
    @SerializedName("response") val response: String,
    @SerializedName("error") val error: String?
  )

  data class UserResponse(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("email") val email: String,
    @SerializedName("role") val role: String
  )

  data class CardResponse(
    @SerializedName("id") val id: Long,
    @SerializedName("type") val type: String,
    @SerializedName("cardMask") val mask: String,
    @SerializedName("expMonth") val expMonth: Int,
    @SerializedName("expYear") val expYear: Int,
    @SerializedName("isDefault") val isDefault: Boolean,
    @SerializedName("amount") val amount: Int
  )
}