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
    setContentView(binding.root)

    val navController = findNavController(R.id.nav_host_fragment_content_main)
  }



//  fun topUpFragment(fragment: BaseFragment, addToBackStack: Boolean) {
//    val ft = supportFragmentManager.beginTransaction()
//    ft.add(R.id.content, fragment, tag)
//    if (addToBackStack) {
//      ft.addToBackStack(null)
//    }
//    ft.commitAllowingStateLoss()
//  }
}