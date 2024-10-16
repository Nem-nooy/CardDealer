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
        val newCards = IntArray(5) { 0 }

        for (i in newCards.indices)
        {
            // TODO: 중복 방지 및 정렬 처리 必要
            newCards[i] = Random.nextInt(52)
        }

        _cards.value = newCards
    }
}