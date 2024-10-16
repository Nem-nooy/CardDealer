package kr.ac.kumoh.ce.s20220736.carddealer

import androidx.lifecycle.ViewModel
import kotlin.random.Random

class CardViewModel : ViewModel() {
    private val _cards = IntArray(5) {0}

    val cards
        get() = _cards

    fun shuffle() {
        for (i in _cards.indices)
            _cards[i] = Random.nextInt(52)
    }
}