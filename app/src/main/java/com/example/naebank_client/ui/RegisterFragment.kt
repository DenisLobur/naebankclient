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

class RegisterFragment : Fragment() {

  private lateinit var binding: FragmentRegisterBinding
  private lateinit var vm: MainViewModel

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    binding = FragmentRegisterBinding.inflate(inflater, container, false)

    vm = (requireActivity() as MainActivity).getVM()
    vm.onRegisterResult.observe(viewLifecycleOwner) {
      Log.d("denys", "result: $it")
      findNavController().navigate(R.id.action_RegisterFragment_to_PageableFragment)
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
        //findNavController().navigate(R.id.action_RegisterFragment_to_UserFragment)
      }
    }
  }
}