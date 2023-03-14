package com.example.naebank_client.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.naebank_client.viewmodel.MainViewModel

abstract class BaseFragment: Fragment() {
  protected lateinit var vm: MainViewModel
  protected lateinit var prefs: SharedPreferences


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    prefs = requireContext().getSharedPreferences("naebank", Context.MODE_PRIVATE)
    vm = (requireActivity() as BaseActivity).getVM()
  }

  companion object {
    const val USER_ID = "user_id"
  }

}