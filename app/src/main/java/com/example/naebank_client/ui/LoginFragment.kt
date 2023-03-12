package com.example.naebank_client.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.naebank_client.R
import com.example.naebank_client.databinding.FragmentLoginBinding
import com.example.naebank_client.viewmodel.MainViewModel
import org.koin.android.ext.android.get

class LoginFragment : Fragment() {

  private lateinit var binding: FragmentLoginBinding
  private lateinit var vm: MainViewModel


  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    binding = FragmentLoginBinding.inflate(inflater, container, false)
    vm = (requireActivity() as MainActivity).getVM()

    vm.onLoginResult.observe(viewLifecycleOwner) {
      Log.d("denys", "login result: $it")
      findNavController().navigate(R.id.action_LoginFragment_to_PageableFragment)
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
      //findNavController().navigate(R.id.action_LoginFragment_to_UserFragment)
      }

      noAccount.setOnClickListener {
        findNavController().navigate(R.id.action_LoginFragment_to_RegisterFragment)
      }

    }
  }
}