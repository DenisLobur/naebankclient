package com.example.naebank_client.di

import com.example.naebank_client.repository.ApiService
import com.example.naebank_client.repository.Repo
import com.example.naebank_client.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val viewModelModule = module {
  viewModel { MainViewModel(get<Repo>()) }
}

val repoModule = module {
  factory<Repo> { Repo(get()) }
}

val remoteModule = module {
  single<Retrofit> {
    Retrofit.Builder()
      .baseUrl("localhost:8080")
      .addConverterFactory(GsonConverterFactory.create())
      .build()
  }

  single<ApiService> { get<Retrofit>().create(ApiService::class.java) }
}

val appModule = listOf(viewModelModule, repoModule, remoteModule)