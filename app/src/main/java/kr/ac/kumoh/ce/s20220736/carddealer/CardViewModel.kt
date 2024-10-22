package kr.ac.kumoh.ce.s20220736.carddealer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class CardViewModel : ViewModel() {
    private val _cards = MutableLiveData<IntArray>(IntArray(5) { 0 })

    val cards: LiveData<IntArray>
        get() = _cards

    fun shuffle() {
        val newCards = mutableSetOf<Int>()

        while (newCards.size < 5) {
            newCards.add(Random.nextInt(52))
        }

        _cards.value = newCards.sorted().toIntArray()
    }
}