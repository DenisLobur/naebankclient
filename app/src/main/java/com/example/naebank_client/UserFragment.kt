package com.example.naebank_client

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.naebank_client.databinding.FragmentUserBinding

class UserFragment : Fragment() {

  private lateinit var binding: FragmentUserBinding

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    binding = FragmentUserBinding.inflate(inflater, container, false)

    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    binding.buttonSecond.setOnClickListener {
      //findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
    }
  }
}