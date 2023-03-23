package com.example.naebank_client.ui.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.content.edit
import com.example.naebank_client.HttpProvider
import com.example.naebank_client.databinding.FragmentUserBinding
import com.example.naebank_client.ui.BaseFragment
import com.example.naebank_client.ui.auth.AuthActivity
import com.example.naebank_client.ui.auth.LoginFragment

class UserFragment : BaseFragment() {

  private lateinit var binding: FragmentUserBinding

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    binding = FragmentUserBinding.inflate(inflater, container, false)
    vm.getUser()

    vm.onCurrentUser.observe(viewLifecycleOwner) {
      binding.userName.text = it.name
      binding.userEmail.text = it.email
      binding.userRoleValue.text = it.role

      prefs.edit {
        putLong(USER_ID, it.id)
      }
    }

    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    binding.logoutButton.setOnClickListener {
      HttpProvider.authHeader = ""
      startActivity(AuthActivity.getIntent(requireActivity()))
      requireActivity().finish()
    }

    requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
      override fun handleOnBackPressed() {
        requireActivity().finish()
      }
    })
  }
}