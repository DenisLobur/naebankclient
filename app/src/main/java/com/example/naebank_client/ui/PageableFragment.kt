package com.example.naebank_client.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.naebank_client.R
import com.example.naebank_client.databinding.FragmentPageableBinding
import com.google.android.material.navigation.NavigationBarView

class PageableFragment : Fragment() {
  private lateinit var binding: FragmentPageableBinding
  private lateinit var pagerAdapter: PagerAdapter

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    binding = FragmentPageableBinding.inflate(inflater, container, false)
    binding.bottomNavigation.setOnItemSelectedListener(navListener)
    pagerAdapter = PagerAdapter(this)
    binding.pager.adapter = pagerAdapter

    return binding.root

  }

  private val navListener = NavigationBarView.OnItemSelectedListener { item ->
    when (item.itemId) {
      R.id.action_user -> {
        binding.pager.currentItem = 0
        //pagerAdapter.createFragment(0)
        //switchFragment(GPSMapFragment.getInstance(), false)
      }
      R.id.action_cards -> {
        binding.pager.currentItem = 1
        //pagerAdapter.createFragment(1)
        //switchFragment(ViewMediaFragment.getInstance(-1),false)
      }
      R.id.action_transactions -> {
        binding.pager.currentItem = 2
        //pagerAdapter.createFragment(2)
        //switchFragment(ProfileFragment.getInstance(), false)
      }
      else -> {
        binding.pager.currentItem = 0
        //pagerAdapter.createFragment(0)
        // keep it for more tabs
      }
    }

    true
  }
}