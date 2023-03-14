package com.example.naebank_client.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.example.naebank_client.databinding.ActivityAddBinding
import com.example.naebank_client.ui.card.AddCardFragment
import com.example.naebank_client.ui.card.CardDetailsFragment

class AddActivity : BaseActivity() {

  private lateinit var binding: ActivityAddBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding = ActivityAddBinding.inflate(layoutInflater).apply {
      lifecycleOwner = this@AddActivity
      vm = mainVM
    }

    val screen = SCREEN.values()[intent.getIntExtra(SCREEN_TO_GO, SCREEN.ADD_CARD.ordinal)]
    val cardId = intent.getLongExtra(CARD_ID, 0)
    Log.d("denys", "cardId: $cardId")

    when (screen) {
      SCREEN.ADD_CARD -> topUpFragment(AddCardFragment(), true)
      SCREEN.CARD_DETAILS -> topUpFragment(CardDetailsFragment.getInstance(cardId), true)
    }

    setContentView(binding.root)
  }

  companion object {

    const val SCREEN_TO_GO = "screen_to_go"
    const val CARD_ID = "card_id"

    enum class SCREEN {
      ADD_CARD, CARD_DETAILS
    }

    fun getIntent(context: Context, screenToGo: SCREEN, cardId: Long? = null): Intent =
      Intent(context, AddActivity::class.java)
        .apply {
          putExtra(SCREEN_TO_GO, screenToGo.ordinal)
          putExtra(CARD_ID, cardId)
        }
  }
}