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
        // var로 변경, size를 0으로 변경
        var newCards = IntArray(0)

        // 중복 검사
        while (newCards.size < 5) {
            val num = Random.nextInt(52)
            if (!newCards.contains(num))
                newCards = newCards.plus(num)
        }
        
        // 정렬
        newCards.sort()

        _cards.value = newCards
    }
}