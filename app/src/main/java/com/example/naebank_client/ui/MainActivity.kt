package com.example.naebank_client.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.example.naebank_client.R
import com.example.naebank_client.databinding.ActivityMainBinding
import com.example.naebank_client.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

  private lateinit var binding: ActivityMainBinding
  private val mainVM: MainViewModel by viewModel()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding = ActivityMainBinding.inflate(layoutInflater).apply {
      lifecycleOwner =
      vm = mainVM
    }
    setContentView(binding.root)

    val navController = findNavController(R.id.nav_host_fragment_content_main)
  }

  fun getVM(): MainViewModel {
    return mainVM
  }
}