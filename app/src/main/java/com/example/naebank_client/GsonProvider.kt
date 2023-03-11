package com.example.naebank_client

import com.example.naebank_client.di.Provider
import com.google.gson.Gson
import com.google.gson.GsonBuilder

class GsonProvider : Provider<Gson> {

  override fun provide(): Gson = gson

  val gson: Gson = GsonBuilder()
    .create()

}
