package com.example.naebank_client.ui

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import com.example.naebank_client.R
import com.example.naebank_client.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

abstract class BaseActivity : AppCompatActivity(), LifecycleOwner {

  protected val mainVM: MainViewModel by viewModel()
  protected var tag: String? = null

  fun getVM(): MainViewModel {
    return mainVM
  }

  open fun switchFragment(fragment: BaseFragment, addToBackStack: Boolean) {
    val ft = supportFragmentManager.beginTransaction()
    ft.replace(R.id.content, fragment, tag)
    if (addToBackStack) {
      ft.addToBackStack(null)
    }
    ft.commitAllowingStateLoss()
  }

  open fun topUpFragment(fragment: BaseFragment, addToBackStack: Boolean) {
    val ft = supportFragmentManager.beginTransaction()
    ft.add(R.id.content, fragment, tag)
    if (addToBackStack) {
      ft.addToBackStack(null)
    }
    ft.commitAllowingStateLoss()
  }
}