package kr.ac.kumoh.ce.s20220736.carddealer

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kr.ac.kumoh.ce.s20220736.carddealer.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var mainBinding: ActivityMainBinding

    @SuppressLint("DiscouragedApi")     // '쓰면 안 좋은 것 알지만, 그럼에도 쓰겠다'는 의지 표명
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // setContentView(R.layout.activity_main)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        mainBinding.btnDeal.setOnClickListener {
//            Log.i("Card is distributed.", getCardName(32))
//            Log.i("Card is distributed.", R.drawable.c_2_of_clubs.toString())
//            Log.i("Card is distributed.", R.drawable.c_2_of_diamonds.toString())

            val c = Random.nextInt(52)

            val res = resources.getIdentifier(
                getCardName(c),
                "drawable",
                packageName
            )
            mainBinding.imgCard1.setImageResource(res)
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
            in 1 .. 9 -> (c % 13 + 1).toString()
            10 -> "jack"
            11 -> "queen"
            12 -> "king"
            else -> "error"
        }

        return "c_${number}_of_${shape}"
    }
}