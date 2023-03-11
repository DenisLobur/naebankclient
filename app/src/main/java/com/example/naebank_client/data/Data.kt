package com.example.naebank_client.data

import com.google.gson.annotations.SerializedName

object Data {

  data class RegisterRequest(
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String,
    @SerializedName("name") val name: String,
    @SerializedName("role") val role: String
  )
}