package com.example.naebank_client.ui.card

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.widget.doAfterTextChanged
import com.example.naebank_client.databinding.FragmentCardOperationsBinding
import com.example.naebank_client.ui.AddActivity
import com.example.naebank_client.ui.BaseFragment

class CardOperationsFragment : BaseFragment() {
  private lateinit var binding: FragmentCardOperationsBinding
  private var operation: OPERATION = OPERATION.WITHDRAW
  private var cardId: Long = 0L
  private var balance: Int = 0
  private var cardName: String = ""

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    binding = FragmentCardOperationsBinding.inflate(inflater, container, false)

    cardId = arguments?.getLong(CARD_ID) ?: 0L
    balance = arguments?.getInt(BALANCE) ?: 0
    operation = OPERATION.values()[arguments?.getInt(OP, OPERATION.WITHDRAW.ordinal)!!]
    cardName = arguments?.getString(CARD_NAME) ?: ""

    binding.balanceValue.text = balance.toString() + " UAH"
    when (operation) {
      OPERATION.WITHDRAW -> {
        binding.title.text = "how much to withdraw"
        binding.doIt.text = "withdraw"
      }
      OPERATION.TOPUP -> {
        binding.title.text = "how much to topup"
        binding.doIt.text = "top-up"
      }
    }

    vm.onCardUpdated.observe(viewLifecycleOwner) {
      vm.addTransaction(
        cardId = cardId,
        amount = binding.amount.text.toString().toInt(),
        type = operation.name,
        cardName = cardName,
        status = "success"
      )

      Toast.makeText(requireActivity(), "SUCCESS", Toast.LENGTH_SHORT).show()
      (requireActivity() as AddActivity).supportFragmentManager.popBackStack()
    }
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    binding.close.setOnClickListener {
      (requireActivity() as AddActivity).supportFragmentManager.popBackStack()
    }

    binding.doIt.setOnClickListener {
      vm.updateCardBalance(cardId, binding.amount.text.toString().toInt(), operation)
    }

    binding.amount.doAfterTextChanged { checkAmount(it) }
  }

  val checkAmount: (Editable?) -> Unit = {
    if (operation == OPERATION.WITHDRAW) {
      if (it.toString().toInt() > balance) {
        binding.doIt.isEnabled = false
        Toast.makeText(requireActivity(), "Insufficient funds", Toast.LENGTH_SHORT).show()
      } else {
        binding.doIt.isEnabled = true
      }
    } else {
      binding.doIt.isEnabled = true
    }
  }

  companion object {
    const val TAG = "card_operations"
    const val OP = "operation"
    const val CARD_ID = "card_id"
    const val BALANCE = "balance"
    const val CARD_NAME = "card_name"

    enum class OPERATION {
      TOPUP, WITHDRAW
    }

    fun getInstance(cardId: Long, cardName: String, operation: OPERATION, balance: Int): CardOperationsFragment {
      val fragment = CardOperationsFragment()
      fragment.arguments = bundleOf(
        CARD_ID to cardId,
        OP to operation.ordinal,
        BALANCE to balance,
        CARD_NAME to cardName
      )

      return fragment
    }
  }
}