package com.example.naebank_client.di

import com.example.naebank_client.ErrorParser
import com.example.naebank_client.GsonProvider
import com.example.naebank_client.data.repository.ApiService
import com.example.naebank_client.data.repository.Repo
import com.example.naebank_client.viewmodel.MainViewModel
import com.google.gson.GsonBuilder
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val viewModelModule = module {
  viewModel { MainViewModel(get<Repo>()) }
}

val repoModule = module {
  factory<Repo> { Repo(get(), get()) }
}

val remoteModule = module {
  single<Retrofit> {
    Retrofit.Builder()
      .baseUrl("http://10.0.2.2:8080/")
      .addConverterFactory(GsonConverterFactory.create())
      .build()
  }

  single<ApiService> { get<Retrofit>().create(ApiService::class.java) }
}

val errorModule = module {
  single { GsonProvider().provide() }
  single { ErrorParser(gson = get()) }
}

val appModule = listOf(viewModelModule, repoModule, remoteModule, errorModule)