package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {


    lateinit var startButton : Button
    lateinit var textInput : EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startButton = findViewById(R.id.start_button)
        textInput = findViewById(R.id.text_input)




        startButton.setOnClickListener {

            if(textInput.text.isNotEmpty()) {
                startQuestionsActivity()

            } else {

                Toast.makeText(
                    this,
                    "Please enter a valid name",
                    Toast.LENGTH_LONG).show()
            }

        }

    }


    private fun startQuestionsActivity() {

        var intent = Intent(this, QuizQuestionsActivity::class.java)
        intent.putExtra("name", textInput.text.toString())
        startActivity(intent)
        finish()
    }
}