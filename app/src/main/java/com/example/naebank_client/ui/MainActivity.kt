package com.example.naebank_client.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.findNavController
import com.example.naebank_client.R
import com.example.naebank_client.databinding.ActivityMainBinding
import com.example.naebank_client.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

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