package com.example.naebank_client.ui

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class PagerAdapter(fa: Fragment) : FragmentStateAdapter(fa) {

  override fun getItemCount(): Int = 3

  override fun createFragment(position: Int): Fragment {
    return when (position) {
      0 -> UserFragment()
      1 -> CardsFragment()
      2 -> TransactionsFragment()
      else -> UserFragment()
    }
  }

}