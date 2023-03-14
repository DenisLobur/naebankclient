package com.example.naebank_client.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.naebank_client.R
import com.example.naebank_client.databinding.FragmentRegisterBinding
import com.example.naebank_client.viewmodel.MainViewModel

class RegisterFragment : BaseFragment() {

  private lateinit var binding: FragmentRegisterBinding

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    binding = FragmentRegisterBinding.inflate(inflater, container, false)

    vm.onRegisterResult.observe(viewLifecycleOwner) {
      Log.d("denys", "result: $it")
      (requireActivity() as MainActivity).switchFragment(PageableFragment(), true)
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