package com.example.naebank_client.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.naebank_client.databinding.FragmentUserBinding

class UserFragment : BaseFragment() {

  private lateinit var binding: FragmentUserBinding


  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    binding = FragmentUserBinding.inflate(inflater, container, false)
    vm.getUser()

    vm.onCurrentUser.observe(viewLifecycleOwner) {
      binding.userName.text = it.name
      binding.userEmail.text = it.email
      binding.userRole.text = "Your role: ${it.role}"
    }

    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    Log.d("denys", "in user fragment")
  }
}