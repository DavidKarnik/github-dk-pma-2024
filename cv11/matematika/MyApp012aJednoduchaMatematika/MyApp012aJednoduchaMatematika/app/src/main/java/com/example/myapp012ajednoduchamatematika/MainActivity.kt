package com.example.myapp012ajednoduchamatematika

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.myapp012ajednoduchamatematika.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    var TimeTextView: TextView? = null
    var QuestionTextText: TextView? = null
    var ScoreTextView: TextView? = null
    var AlertTextView: TextView? = null
    var FinalScoreTextView: TextView? = null
    var btn0: Button? = null
    var btn1: Button? = null
    var btn2: Button? = null
    var btn3: Button? = null
    var countDownTimer: CountDownTimer? = null
    var random: Random = Random
    var a = 0
    var b = 0
    var indexOfCorrectAnswer = 0
    var answers = ArrayList<Int>()
    var points = 0
    var totalQuestions = 0
    var cals = ""

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializace View Bindingu
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // ošetření null intentu ... pokud by byl null, tak se použije "+"
        val calInt = intent.getStringExtra("cals") ?: "+"
        cals = calInt

        TimeTextView = binding.TimeTextView
        QuestionTextText = binding.QuestionTextText
        ScoreTextView = binding.ScoreTextView
        AlertTextView = binding.AlertTextView
        //FinalScoreTextView = findViewById(R.id.FinalScoreTextView)
        btn0 = binding.button0
        btn1 = binding.button1
        btn2 = binding.button2
        btn3 = binding.button3

        start()

    }

    fun NextQuestion(cal: String) {
        a = random.nextInt(10)
        b = random.nextInt(10)
        QuestionTextText!!.text = "$a $cal $b"
        indexOfCorrectAnswer = random.nextInt(4)
        answers.clear()

        for (i in 0..3) {
            if (indexOfCorrectAnswer == i) {

                when (cal) {
                    "+" -> {
                        answers.add(a + b)
                    }

                    "-" -> {
                        answers.add(a - b)
                    }

                    "*" -> {
                        answers.add(a * b)
                    }

                    "/" -> {
                        if (b != 0) {
                            answers.add(a / b)
                        } else {
                            answers.add(0)
                        }
                    }

                }
            } else {
                var wrongAnswer = random.nextInt(20)
                try {
                    while (
                        wrongAnswer == a + b
                        || wrongAnswer == a - b
                        || wrongAnswer == a * b
                        || wrongAnswer == a / b
                    ) {
                        wrongAnswer = random.nextInt(20)
                    }
                    answers.add(wrongAnswer)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        try {
            btn0!!.text = "${answers[0]}"
            btn1!!.text = "${answers[1]}"
            btn2!!.text = "${answers[2]}"
            btn3!!.text = "${answers[3]}"
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun optionSelect(view: View?) {
        totalQuestions++
        if (indexOfCorrectAnswer.toString() == view!!.tag.toString()) {
            points++
            AlertTextView!!.text = "Correct"
        } else {
            AlertTextView!!.text = "Wrong"
        }
        ScoreTextView!!.text = "$points/$totalQuestions"
        NextQuestion(cals)

    }

    fun PlayAgain(view: View?) {
        points = 0
        totalQuestions = 0
        ScoreTextView!!.text = "$points/$totalQuestions"
        start() // Přidáno
    }


    private fun start() {
        NextQuestion(cals)
        countDownTimer = object : CountDownTimer(10000, 500) {
            override fun onTick(p0: Long) {
                TimeTextView!!.text = (p0 / 1000).toString() + "s"
            }

            override fun onFinish() {
                TimeTextView!!.text = "Konec času"
                openDilog()
            }
        }.start()
    }

    private fun openDilog() {
        val inflate = LayoutInflater.from(this)
        var winDialog = inflate.inflate(R.layout.win_layout, null)
        FinalScoreTextView = winDialog.findViewById(R.id.FinalScoreTextView)
        val btnPlayAgain = winDialog.findViewById<Button>(R.id.buttonPlayAgain)
        val btnBack = winDialog.findViewById<Button>(R.id.buttonBack)
        var dialog = AlertDialog.Builder(this)
        dialog.setCancelable(false)
        dialog.setView(winDialog)
        FinalScoreTextView!!.text = "$points/$totalQuestions"
        btnPlayAgain.setOnClickListener {
            PlayAgain(it)
        }
        btnBack.setOnClickListener {
            onBackPressed()
        }
        val showDialog = dialog.create()
        showDialog.show()
    }

}