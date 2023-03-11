package com.example.naebank_client

import android.app.Application
import com.example.naebank_client.di.appModule
import org.koin.core.context.GlobalContext.startKoin

class NaebankApp: Application() {
  override fun onCreate() {
    super.onCreate()

    startKoin{
      modules(appModule)
    }
  }
}