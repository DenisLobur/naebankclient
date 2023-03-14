package com.example.naebank_client.ui.auth

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.naebank_client.databinding.FragmentLoginBinding
import com.example.naebank_client.ui.BaseFragment
import com.example.naebank_client.ui.MainActivity
import com.example.naebank_client.ui.PageableFragment

class LoginFragment : BaseFragment() {

  private lateinit var binding: FragmentLoginBinding

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    binding = FragmentLoginBinding.inflate(inflater, container, false)

    vm.onLoginResult.observe(viewLifecycleOwner) {
      (requireActivity() as MainActivity).switchFragment(PageableFragment(), true)
    }

    vm.onError.observe(viewLifecycleOwner) {
      Log.d("denys", "login error: $it")
    }

    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    with(binding) {

      loginButton.setOnClickListener {
        vm.loginUser(email.text.toString(), password.text.toString())
      }

      noAccount.setOnClickListener {
        (requireActivity() as MainActivity).switchFragment(RegisterFragment(), true)
      }
    }
  }

  companion object {
    const val TAG = "LoginFragment"
  }
}