package com.example.naebank_client.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.example.naebank_client.R
import com.example.naebank_client.databinding.FragmentPageableBinding
import com.google.android.material.navigation.NavigationBarView

class PageableFragment : BaseFragment() {
  private lateinit var binding: FragmentPageableBinding
  private lateinit var pagerAdapter: PagerAdapter

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    binding = FragmentPageableBinding.inflate(inflater, container, false)
    binding.bottomNavigation.setOnItemSelectedListener(navListener)
    //pagerAdapter = PagerAdapter(this)
    binding.pager.adapter = pagerAdapter

    return binding.root
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

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
      override fun handleOnBackPressed() {
        requireActivity().finish()
      }
    })
  }

  companion object {
    const val TAG = "PageableFragment"
  }
}