package com.example.naebank_client.ui.auth

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import com.example.naebank_client.databinding.FragmentLoginBinding
import com.example.naebank_client.ui.BaseFragment
import com.example.naebank_client.ui.PageableFragment
import com.example.naebank_client.ui.PagerActivity

class LoginFragment : BaseFragment() {

  private lateinit var binding: FragmentLoginBinding

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    binding = FragmentLoginBinding.inflate(inflater, container, false)

    vm.onLoginResult.observe(viewLifecycleOwner) {
      startActivity(PagerActivity.getIntent(requireActivity()))
      requireActivity().finish()
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
        (requireActivity() as AuthActivity).switchFragment(RegisterFragment(), true)
      }
    }

    requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
      override fun handleOnBackPressed() {
        requireActivity().finish()
      }
    })
  }

  companion object {
    const val TAG = "LoginFragment"
  }
}