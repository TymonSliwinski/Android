package com.example.myapplication

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private val total: Int = QuestionAnswer().questions.size

    companion object {
        var currentQuestion: Int = 0
        var points: Int = 0
        var correct: Int = 0
        var cheats: Int = 0
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val buttonTrue = findViewById<Button>(R.id.button_true)
        val buttonFalse = findViewById<Button>(R.id.button_false)
        val buttonCheat = findViewById<Button>(R.id.button_cheat)
        val question = findViewById<TextView>(R.id.question)

        // show first question
        nextQuestion()

        buttonTrue.setOnClickListener {
            onAnswerClick(true)
        }

        buttonFalse.setOnClickListener {
            onAnswerClick(false)
        }

        buttonCheat.setOnClickListener {
            onCheatClick()
        }

        // clicking on question text launches search query in browser
        question.setOnClickListener {
            searchWeb(question.text.toString())
        }
    }

    private fun nextQuestion() {
        if (currentQuestion == total) endGame() else
            findViewById<TextView>(R.id.question).text = QuestionAnswer().questions[currentQuestion]
    }

    private fun checkAnswer(answer: Boolean): Boolean = QuestionAnswer().answers[currentQuestion] == answer

    private fun onAnswerClick(answer: Boolean) {
        if (checkAnswer(answer)) {
            points += 10
            correct += 1
        }
        currentQuestion += 1
        nextQuestion()
    }

    private fun onCheatClick() {
        cheats += 1
        val intent = Intent(this@MainActivity, CheatActivity::class.java)
        startActivity(intent)
    }

    private fun endGame() {
        var message: String = "Your score: $points\n"
        message += "Correct answers: $correct/$total\n"
        message += "Cheats used: $cheats"
        findViewById<TextView>(R.id.question).text = message
        findViewById<Button>(R.id.button_true).visibility = View.INVISIBLE
        findViewById<Button>(R.id.button_false).visibility = View.INVISIBLE
        findViewById<Button>(R.id.button_cheat).visibility = View.INVISIBLE

        points = 0
        currentQuestion = 0
        cheats = 0
        correct = 0
    }

    private fun searchWeb(question: String) {
        val intent =Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("https://www.google.com/search?q=$question")
        startActivity(intent)
    }
}


