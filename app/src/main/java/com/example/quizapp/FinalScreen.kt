package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class FinalScreen : AppCompatActivity() {

    private lateinit var nameView : TextView
    private lateinit var scoreView : TextView
    private lateinit var finishButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_final_screen)


        nameView = findViewById(R.id.name_view)
        scoreView = findViewById(R.id.score_view)
        finishButton = findViewById(R.id.finish_button)

        nameView.text = "${intent.getStringExtra("name")}"
        scoreView.text = "Your score is ${intent.getStringExtra("score")} out of 10"

        finishButton.setOnClickListener {
            handleFinish()
        }

    }

    private fun handleFinish() {


        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)


    }
}