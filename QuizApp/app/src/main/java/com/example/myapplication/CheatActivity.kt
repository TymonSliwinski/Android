package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import org.w3c.dom.Text

class CheatActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat)

        showAnswer()

        val backButton: Button = findViewById<Button>(R.id.button_back)

        backButton.setOnClickListener {
            val intent = Intent(this@CheatActivity, MainActivity::class.java)
            startActivity(intent)
        }

        MainActivity.points -= 15
    }

    fun showAnswer() {
        val current = MainActivity.currentQuestion
        findViewById<TextView>(R.id.question).text = QuestionAnswer().questions[current]
        findViewById<TextView>(R.id.answer).text = QuestionAnswer().answers[current].toString()
    }
}