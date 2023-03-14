package com.example.naebank_client.ui

import android.os.Bundle
import com.example.naebank_client.databinding.ActivityMainBinding
import com.example.naebank_client.ui.auth.LoginFragment
import com.example.naebank_client.ui.auth.RegisterFragment

class MainActivity : BaseActivity() {

  private lateinit var binding: ActivityMainBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding = ActivityMainBinding.inflate(layoutInflater).apply {
      lifecycleOwner = this@MainActivity
      vm = mainVM
    }

    switchFragment(LoginFragment(), true)
    setContentView(binding.root)
  }

  override fun switchFragment(fragment: BaseFragment, addToBackStack: Boolean) {
    tag = LoginFragment.TAG // default fragment
    when(fragment) {
      is RegisterFragment -> {
        tag = RegisterFragment.TAG
      }
      is PageableFragment -> {
        tag = PageableFragment.TAG
      }
    }

    super.switchFragment(fragment, addToBackStack)
  }
}