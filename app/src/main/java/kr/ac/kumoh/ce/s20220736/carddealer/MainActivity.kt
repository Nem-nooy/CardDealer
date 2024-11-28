// MainActivity.kt
package kr.ac.kumoh.ce.s20220736.carddealer

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kr.ac.kumoh.ce.s20220736.carddealer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var mainBinding: ActivityMainBinding

    @SuppressLint("DiscouragedApi")     // '쓰면 안 좋은 것 알지만, 그럼에도 쓰겠다'는 의지 표명
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("Lifecycle is started!!!!!!!", "onCreate()")
        enableEdgeToEdge()

        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        val imgCards = arrayOf(
            mainBinding.imgCard1,
            mainBinding.imgCard2,
            mainBinding.imgCard3,
            mainBinding.imgCard4,
            mainBinding.imgCard5,
        )

        val model = ViewModelProvider(this)[CardViewModel::class.java]

        model.cards.observe(this, Observer {
            // cards 값이 바뀌면 옵저버 발동
            model.cards.value!!.forEachIndexed { index, num ->
                imgCards[index].setImageResource(
                    resources.getIdentifier(
                        getCardName(num),
                        "drawable",
                        packageName
                    )
                )
            }
        })

        model.handRank.observe(this, Observer { rank ->
            Toast.makeText(this, "당신의 족보는 $rank 입니다.", Toast.LENGTH_SHORT).show()
        })

        mainBinding.btnDeal.setOnClickListener {
            model.shuffle()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun getCardName(c: Int): String {
        val shape = when (c / 13) {
            0 -> "spades"
            1 -> "diamonds"
            2 -> "hearts"
            3 -> "clubs"
            else -> "error"
        }
        val number = when (c % 13) {
            0 -> "ace"
            in 1..9 -> (c % 13 + 1).toString()
            10 -> "jack"
            11 -> "queen"
            12 -> "king"
            else -> "error"
        }
        return if (c % 13 in 10..12)
            "c_${number}_of_${shape}2"
        else
            "c_${number}_of_${shape}"
    }
}
