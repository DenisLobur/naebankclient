package com.example.naebank_client.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.naebank_client.R
import com.example.naebank_client.databinding.ActivityPagerBinding
import com.google.android.material.navigation.NavigationBarView

class PagerActivity : BaseActivity() {

  private lateinit var binding: ActivityPagerBinding
  private lateinit var pagerAdapter: PagerAdapter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding = ActivityPagerBinding.inflate(layoutInflater).apply {
      lifecycleOwner = this@PagerActivity
    }

    binding.bottomNavigation.setOnItemSelectedListener(navListener)
    pagerAdapter = PagerAdapter(supportFragmentManager, lifecycle)
    binding.pager.adapter = pagerAdapter

    //switchFragment(LoginFragment(), true)
    setContentView(binding.root)
  }

  private val navListener = NavigationBarView.OnItemSelectedListener { item ->
    when (item.itemId) {
      R.id.action_user -> {
        binding.pager.currentItem = 0
      }
      R.id.action_cards -> {
        binding.pager.currentItem = 1
      }
      R.id.action_transactions -> {
        binding.pager.currentItem = 2
      }
      else -> {
        binding.pager.currentItem = 0
      }
    }

    true
  }

  companion object {
    fun getIntent(context: Context): Intent =
      Intent(context, PagerActivity::class.java)
  }

//  override fun switchFragment(fragment: BaseFragment, addToBackStack: Boolean) {
//    tag = LoginFragment.TAG // default fragment
//    when(fragment) {
//      is RegisterFragment -> {
//        tag = RegisterFragment.TAG
//      }
//      is PageableFragment -> {
//        tag = PageableFragment.TAG
//      }
//    }
//
//    super.switchFragment(fragment, addToBackStack)
//  }
}