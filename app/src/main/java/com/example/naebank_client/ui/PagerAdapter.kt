package com.example.naebank_client.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.naebank_client.ui.card.CardsFragment
import com.example.naebank_client.ui.transaction.TransactionsFragment
import com.example.naebank_client.ui.user.UserFragment

class PagerAdapter(fm: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fm, lifecycle) {
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