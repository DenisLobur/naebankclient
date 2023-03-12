package com.example.naebank_client.ui

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.example.naebank_client.R
import com.example.naebank_client.databinding.ActivityAddBinding
import com.example.naebank_client.databinding.ActivityMainBinding
import com.example.naebank_client.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddActivity : BaseActivity() {

  private lateinit var binding: ActivityAddBinding
  protected var tag: String? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding = ActivityAddBinding.inflate(layoutInflater).apply {
      lifecycleOwner = this@AddActivity
      vm = mainVM
    }

    topUpFragment(AddCardFragment(), true)

    setContentView(binding.root)
  }

  fun topUpFragment(fragment: BaseFragment, addToBackStack: Boolean) {
    val ft = supportFragmentManager.beginTransaction()
    ft.add(R.id.content, fragment, tag)
    if (addToBackStack) {
      ft.addToBackStack(null)
    }
    ft.commitAllowingStateLoss()
  }


}