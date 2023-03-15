package com.example.naebank_client.ui.card

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.naebank_client.R
import com.example.naebank_client.databinding.FragmentCardsBinding
import com.example.naebank_client.ui.AddActivity
import com.example.naebank_client.ui.BaseFragment

class CardsFragment : BaseFragment() {

  private lateinit var binding: FragmentCardsBinding

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    binding = FragmentCardsBinding.inflate(inflater, container, false)

    vm.onCardsResult.observe(viewLifecycleOwner) {
      binding.hasCards = it.isNotEmpty()

      val adapter = CardListAdapter(object : CardListAdapter.OnCardClick {
        override fun onCardClick(id: Long) {
//          val intent = AddActivity.getIntent(requireActivity(), AddActivity.Companion.SCREEN.CARD_DETAILS, cardId = id)
//          startActivity(intent)
//          findNavController().navigate(R.id.na)

          findNavController().navigate(
            CardsFragmentDirections.actionCardsToCardDetails(id)
          )
        }
      })
      adapter.setData(it)
      binding.cardsRv.adapter = adapter
    }

    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    binding.addCardButton.setOnClickListener {
//      val intent = AddActivity.getIntent(requireActivity(), AddActivity.Companion.SCREEN.ADD_CARD)
//      startActivity(intent)
      findNavController().navigate(R.id.action_cards_to_add_card)
      //val navController = requireActivity().supportFragmentManager.getNav
    }
  }

  override fun onResume() {
    super.onResume()
    val userId = prefs.getLong(USER_ID, 0)
    vm.getCardsByUserId(userId)
  }
}