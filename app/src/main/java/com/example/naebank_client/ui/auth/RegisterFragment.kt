package com.example.naebank_client.ui.auth

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.naebank_client.databinding.FragmentRegisterBinding
import com.example.naebank_client.ui.BaseFragment
import com.example.naebank_client.ui.PageableFragment
import com.example.naebank_client.ui.PagerActivity

class RegisterFragment : BaseFragment() {

  private lateinit var binding: FragmentRegisterBinding

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    binding = FragmentRegisterBinding.inflate(inflater, container, false)

    vm.onRegisterResult.observe(viewLifecycleOwner) {
      PagerActivity.getIntent(requireActivity())
      requireActivity().finish()
    }

    vm.onError.observe(viewLifecycleOwner) {
      Log.d("denys", "error: $it")
    }

    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    with(binding) {

      registerButton.setOnClickListener {
        vm.registerUser(name.text.toString(), email.text.toString(), password.text.toString())
      }
    }
  }

  companion object {
    const val TAG = "RegisterFragment"
  }
}