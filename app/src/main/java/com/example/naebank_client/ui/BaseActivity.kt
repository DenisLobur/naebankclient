package com.example.naebank_client.ui

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import com.example.naebank_client.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

abstract class BaseActivity : AppCompatActivity(), LifecycleOwner {

  protected val mainVM: MainViewModel by viewModel()

  fun getVM(): MainViewModel {
    return mainVM
  }
}