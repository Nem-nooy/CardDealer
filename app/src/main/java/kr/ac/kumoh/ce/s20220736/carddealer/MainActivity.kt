package kr.ac.kumoh.ce.s20220736.carddealer

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
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

        Log.i("Lifecycle is started!!!!!!!", "onCreate()")
        enableEdgeToEdge()
        // setContentView(R.layout.activity_main)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        mainBinding.btnDeal.setOnClickListener {
//            Log.i("Card is distributed.", getCardName(32))
//            Log.i("Card is distributed.", R.drawable.c_2_of_clubs.toString())
//            Log.i("Card is distributed.", R.drawable.c_2_of_diamonds.toString())

            val c = IntArray(5)
            val res = IntArray(5)

//            for (i in 0..4) {
            for (i in c.indices) {
                c[i] = Random.nextInt(52)

                Log.i("Test", "${c[i]} : " + getCardName(c[i]))

                res[i] = resources.getIdentifier(
                    getCardName(c[i]),
                    "drawable",
                    packageName
                )
            }

            mainBinding.imgCard1.setImageResource((res[0]))
            mainBinding.imgCard2.setImageResource((res[1]))
            mainBinding.imgCard3.setImageResource((res[2]))
            mainBinding.imgCard4.setImageResource((res[3]))
            mainBinding.imgCard5.setImageResource((res[4]))

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

    override fun onStart() {
        super.onStart()

        Log.i("Lifecycle is started!!!!!!!", "onStart()")
    }

    override fun onResume() {
        super.onResume()

        Log.i("Lifecycle is started!!!!!!!", "onResume()")
    }

    override fun onPause() {
        super.onPause()

        Log.i("Lifecycle is started!!!!!!!", "onPause()")
    }

    override fun onStop() {
        super.onStop()

        Log.i("Lifecycle is started!!!!!!!", "onStop()")
    }

    override fun onRestart() {
        super.onRestart()

        Log.i("Lifecycle is started!!!!!!!", "onRestart()")
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.i("Lifecycle is started!!!!!!!", "onDestroy()")
    }
}