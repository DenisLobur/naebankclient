package com.example.naebank_client

import com.example.naebank_client.di.Provider
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

class HttpProvider : Provider<OkHttpClient> {

  override fun provide(): OkHttpClient = network



  val network = createClient()

  private fun createClient(timeout: Long = 60): OkHttpClient {
    val headersInterceptor = Interceptor { chain ->
      val builder = chain.request().newBuilder()
        .addHeader("Accept", "*/*")
        .addHeader("Content-type", "application/json")
      builder.addHeader("Authorization", authHeader)
      chain.proceed(builder.build())
    }
    val builder = OkHttpClient.Builder()
      .connectTimeout(timeout, TimeUnit.SECONDS)
      .readTimeout(timeout, TimeUnit.SECONDS)
      .writeTimeout(timeout, TimeUnit.SECONDS)
      .retryOnConnectionFailure(true)
      .addInterceptor(headersInterceptor)

    builder.addInterceptor(HttpLoggingInterceptor().apply {
      level = HttpLoggingInterceptor.Level.BODY
    })

    return builder.build()
  }

  companion object {
    var authHeader = ""
      set(key) {
        field = "Bearer $key"
      }
  }
}
