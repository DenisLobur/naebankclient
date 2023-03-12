package com.example.naebank_client.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.naebank_client.viewmodel.MainViewModel

abstract class BaseFragment: Fragment() {
  protected lateinit var vm: MainViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    vm = (requireActivity() as BaseActivity).getVM()
  }


}