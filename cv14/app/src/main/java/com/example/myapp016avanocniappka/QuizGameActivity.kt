package com.example.myapp016avanocniappka

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class QuizGameActivity : AppCompatActivity() {

    private lateinit var resultText: TextView
    private lateinit var questionCounter: TextView
    private lateinit var questionText: TextView
    private lateinit var answerButtons: List<Button>

    private var questions: MutableList<Question> = mutableListOf()
    private var currentQuestion: Question? = null
    private var correctAnswers = 0
    private var totalQuestions = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_game)

        resultText = findViewById(R.id.resultText)
        questionCounter = findViewById(R.id.questionCounter)
        questionText = findViewById(R.id.questionText)
        answerButtons = listOf(
            findViewById(R.id.answer1),
            findViewById(R.id.answer2),
            findViewById(R.id.answer3)
        )

        // Přidání otázek
        addQuestions()

        // Zobrazení první otázky
        showNextQuestion()

        // Nastavení kliknutí na tlačítka odpovědí
        answerButtons.forEachIndexed { index, button ->
            button.setOnClickListener {
                checkAnswer(index)
                showNextQuestion()
            }
        }
    }

//    private fun addQuestions() {
//        questions.add(Question("Jaká je barva nebe?", listOf("Modrá", "Zelená", "Červená"), 0))
//        questions.add(Question("Kolik je 2 + 2?", listOf("3", "4", "5"), 1))
//        questions.add(Question("Hlavní město ČR?", listOf("Brno", "Praha", "Ostrava"), 1))
//        questions.shuffle() // Náhodné pořadí otázek
//    }

    private fun addQuestions() {
        questions.add(
            Question(
                "Jaké zvíře podle tradice doprovází svatého Mikuláše?",
                listOf("Čert", "Sob", "Pes"),
                0
            )
        )
        questions.add(
            Question(
                "Jak se jmenuje oblíbená vánoční píseň o zvonění?",
                listOf("Jingle Bells", "Silent Night", "Deck the Halls"),
                0
            )
        )
        questions.add(
            Question(
                "Jak se jmenuje hlavní postava ve filmu Sám doma?",
                listOf("Kevin", "Mikey", "Tommy"),
                0
            )
        )
        questions.add(
            Question(
                "Který den se v Česku tradičně jí bramborový salát a kapr?",
                listOf("23. prosince", "24. prosince", "25. prosince"),
                1
            )
        )
        questions.add(
            Question(
                "Kdo podle tradice nosí dárky ve Španělsku?",
                listOf("Tři králové", "Santa Claus", "Děda Mráz"),
                0
            )
        )
        questions.add(
            Question(
                "Jak se jmenuje tradiční vánoční rostlina s červenými listy?",
                listOf("Vánoční hvězda", "Cesmína", "Jmelí"),
                0
            )
        )
        questions.add(
            Question(
                "Kdo napsal vánoční koledu *Rolničky, rolničky*?",
                listOf("James Lord Pierpont", "Bing Crosby", "John Lennon"),
                0
            )
        )
        questions.add(
            Question(
                "Co symbolizuje adventní věnec?",
                listOf("Čas do Vánoc", "Ochranu před zlými duchy", "Rodinnou pohodu"),
                0
            )
        )
        questions.add(
            Question(
                "Kde byste našli dům Santa Clause?",
                listOf("V Grónsku", "Na Severním pólu", "V Laponsku"),
                2
            )
        )
        questions.shuffle() // Náhodné pořadí otázek
    }

    private fun showNextQuestion() {
        if (questions.isNotEmpty()) {
            currentQuestion = questions.removeAt(0)
            currentQuestion?.let { question ->
                questionText.text = question.text
                question.answers.forEachIndexed { index, answer ->
                    answerButtons[index].text = answer
                }
                questionCounter.text = "${correctAnswers}/${++totalQuestions}"
//                resultText.visibility = TextView.INVISIBLE
            }
        } else {
            // Konec kvízu
            questionText.text = "Kvíz dokončen! Správně: $correctAnswers z $totalQuestions"
            answerButtons.forEach { it.isEnabled = false }
        }
    }

    private fun checkAnswer(selectedIndex: Int) {
        currentQuestion?.let { question ->
            if (selectedIndex == question.correctAnswerIndex) {
                correctAnswers++
                resultText.text = "Správně!"
            } else {
                resultText.text = "Špatně!"
            }
            resultText.visibility = TextView.VISIBLE
        }
    }

    data class Question(
        val text: String,
        val answers: List<String>,
        val correctAnswerIndex: Int
    )
}
