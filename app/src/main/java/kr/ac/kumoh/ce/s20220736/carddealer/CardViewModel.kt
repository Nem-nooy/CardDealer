package kr.ac.kumoh.ce.s20220736.carddealer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class CardViewModel : ViewModel() {
    private val _cards = MutableLiveData<IntArray>(IntArray(5) { 0 })
    val cards: LiveData<IntArray>
        get() = _cards

    private val _handRank = MutableLiveData<String>()
    val handRank: LiveData<String>
        get() = _handRank

    fun shuffle() {
        val newCards = mutableSetOf<Int>()
        while (newCards.size < 5) {
            newCards.add(Random.nextInt(52))
        }
        _cards.value = newCards.sorted().toIntArray()
        evaluateHandRank()
    }

    private fun evaluateHandRank() {
        val cards = _cards.value ?: return

        // 카드 숫자와 무늬를 분리
        val numbers = cards.map { it % 13 }
        val suits = cards.map { it / 13 }

        // 숫자별 개수 세기
        val numberCounts = numbers.groupingBy { it }.eachCount()

        // 무늬별 개수 세기
        val suitCounts = suits.groupingBy { it }.eachCount()

        val isFlush = suitCounts.size == 1
        val sortedNumbers = numbers.sorted()
        val isStraight = sortedNumbers.zipWithNext().all { (a, b) -> b == a + 1 } ||
                (sortedNumbers == listOf(0, 9, 10, 11, 12)) // A,10,J,Q,K

        val rank = when {
            isFlush && isStraight && sortedNumbers.contains(12) -> "로열 스트레이트 플러시"
            isFlush && isStraight -> "스트레이트 플러시"
            4 in numberCounts.values -> "포카드"
            numberCounts.values.containsAll(listOf(3, 2)) -> "풀하우스"
            isFlush -> "플러시"
            isStraight -> "스트레이트"
            3 in numberCounts.values -> "쓰리카드"
            numberCounts.values.count { it == 2 } == 2 -> "투페어"
            2 in numberCounts.values -> "원페어"
            else -> "하이카드"
        }

        _handRank.value = rank
    }
}
