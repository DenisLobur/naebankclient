package com.example.naebank_client.ui.auth

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.naebank_client.databinding.ActivityAuthBinding
import com.example.naebank_client.ui.BaseActivity
import com.example.naebank_client.ui.BaseFragment
import com.example.naebank_client.ui.PageableFragment
import com.example.naebank_client.ui.PagerActivity

class AuthActivity : BaseActivity() {

  private lateinit var binding: ActivityAuthBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding = ActivityAuthBinding.inflate(layoutInflater).apply {
      lifecycleOwner = this@AuthActivity
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



  companion object {
    fun getIntent(context: Context): Intent =
      Intent(context, AuthActivity::class.java)
  }
}