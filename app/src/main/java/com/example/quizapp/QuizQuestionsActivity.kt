package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat


class QuizQuestionsActivity : AppCompatActivity(){
    private lateinit var questionView : TextView
    private lateinit var imageView : ImageView
    private lateinit var optionOneView: TextView
    private lateinit var optionTwoView: TextView
    private lateinit var optionThreeView: TextView
    private lateinit var optionFourView: TextView
    private lateinit var submitButton: Button
    private lateinit var progressBar: ProgressBar
    private lateinit var progressView : TextView
    private var selectedOptionText = ""
    var counter = 0
    val questionsList = DataHolder.getQuestion()
    var correctAnswerCount = 0




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)


        questionView = findViewById(R.id.questionText)
        imageView = findViewById(R.id.image_view)
        optionOneView = findViewById(R.id.tv_option_one)
        optionTwoView = findViewById(R.id.tv_option_two)
        optionThreeView = findViewById(R.id.tv_option_three)
        optionFourView = findViewById(R.id.tv_option_four)
        submitButton = findViewById(R.id.btn_submit)
        progressBar = findViewById(R.id.progressBar)
        progressView = findViewById(R.id.tv_Progress)


        optionOneView.setOnClickListener {

            handleSelection(it)

        }

        optionTwoView.setOnClickListener {

            handleSelection(it)

        }

        optionThreeView.setOnClickListener {

            handleSelection(it)

        }

        optionFourView.setOnClickListener {

            handleSelection(it)

        }



        submitButton.setOnClickListener {

            if(selectedOptionText.isNotEmpty()){

                if(counter == questionsList.size - 1 && it is Button) {

                    checkCorrectAnswer()
                    it.text = "FINISH"
                    var optionsArray = mutableListOf(optionOneView, optionTwoView, optionThreeView, optionFourView)

                    optionsArray.forEach {
                        it.isEnabled = false
                    }

                    var name = intent.getStringExtra("name")

                    Toast.makeText(this, "Your score is $correctAnswerCount", Toast.LENGTH_LONG).show()
                    val intent = Intent(this, FinalScreen::class.java)
                    intent.putExtra("score", correctAnswerCount.toString())

                    intent.putExtra("name", name)
                    startActivity(intent)

                } else if(it is Button && it.text == "SUBMIT") {

                    checkCorrectAnswer()
                    it.text = "NEXT"
                    var optionsArray = mutableListOf(optionOneView, optionTwoView, optionThreeView, optionFourView)

                    optionsArray.forEach {
                        it.isEnabled = false
                    }

                }
                else {

                    handleButtonClick()
                }
            } else {


                Toast.makeText(this, "Please select an option", Toast.LENGTH_LONG).show()
            }

        }

        setContent()


    }

    private fun setContent() {

        reset()
        questionView.text = questionsList[counter].question

        imageView.setImageResource(questionsList[counter].image)
        optionOneView.text = questionsList[counter].optionOne
        optionTwoView.text = questionsList[counter].optionTwo
        optionThreeView.text = questionsList[counter].optionThree
        optionFourView.text = questionsList[counter].optionFour

        progressBar.progress = counter
        progressView.text = "$counter/${questionsList.size}"

    }


    private fun handleButtonClick() {
        counter++
        setContent()

    }


    private fun handleSelection(view: View) {

        when(view){

            optionOneView -> {

                optionOneView.background = ContextCompat.getDrawable(this, R.drawable.selected_option_border_bg)
                optionTwoView.background = ContextCompat.getDrawable(this, R.drawable.default_option_border_bg)
                optionThreeView.background = ContextCompat.getDrawable(this, R.drawable.default_option_border_bg)
                optionFourView.background = ContextCompat.getDrawable(this, R.drawable.default_option_border_bg)

                selectedOptionText = optionOneView.text.toString()



            }

            optionTwoView -> {

                optionTwoView.background = ContextCompat.getDrawable(this, R.drawable.selected_option_border_bg)
                optionOneView.background = ContextCompat.getDrawable(this, R.drawable.default_option_border_bg)
                optionThreeView.background = ContextCompat.getDrawable(this, R.drawable.default_option_border_bg)
                optionFourView.background = ContextCompat.getDrawable(this, R.drawable.default_option_border_bg)

                selectedOptionText = optionTwoView.text.toString()

            }

            optionThreeView -> {

                optionThreeView.background = ContextCompat.getDrawable(this, R.drawable.selected_option_border_bg)
                optionOneView.background = ContextCompat.getDrawable(this, R.drawable.default_option_border_bg)
                optionTwoView.background = ContextCompat.getDrawable(this, R.drawable.default_option_border_bg)
                optionFourView.background = ContextCompat.getDrawable(this, R.drawable.default_option_border_bg)
                selectedOptionText = optionThreeView.text.toString()

            }

            optionFourView -> {

                optionFourView.background = ContextCompat.getDrawable(this, R.drawable.selected_option_border_bg)
                optionOneView.background = ContextCompat.getDrawable(this, R.drawable.default_option_border_bg)
                optionThreeView.background = ContextCompat.getDrawable(this, R.drawable.default_option_border_bg)
                optionTwoView.background = ContextCompat.getDrawable(this, R.drawable.default_option_border_bg)
                selectedOptionText = optionFourView.text.toString()
            }



        }

    }

    private fun checkForSelectedOption() {

        if(optionOneView.text == selectedOptionText && questionsList[counter].correctAnswer != 1) {
            optionOneView.background = ContextCompat.getDrawable(this, R.drawable.wrong_option_border_bg)
        } else if(optionTwoView.text == selectedOptionText && questionsList[counter].correctAnswer != 2) {
            optionTwoView.background = ContextCompat.getDrawable(this, R.drawable.wrong_option_border_bg)
        } else if(optionThreeView.text == selectedOptionText && questionsList[counter].correctAnswer != 3) {
            optionThreeView.background = ContextCompat.getDrawable(this, R.drawable.wrong_option_border_bg)
        } else if(optionFourView.text == selectedOptionText && questionsList[counter].correctAnswer != 4) {
            optionFourView.background = ContextCompat.getDrawable(this, R.drawable.wrong_option_border_bg)
        } else {

            correctAnswerCount += 1
        }
    }


    private fun checkCorrectAnswer() {

        when(questionsList[counter].correctAnswer) {

            1 -> {

                optionOneView.background = ContextCompat.getDrawable(this, R.drawable.correct_option_border_bg)
                checkForSelectedOption()

            }

            2 -> {
                optionTwoView.background = ContextCompat.getDrawable(this, R.drawable.correct_option_border_bg)
                checkForSelectedOption()

            }

            3 -> {


                optionThreeView.background = ContextCompat.getDrawable(this, R.drawable.correct_option_border_bg)
                checkForSelectedOption()
            }


            else -> {


                optionFourView.background = ContextCompat.getDrawable(this, R.drawable.correct_option_border_bg)
                checkForSelectedOption()
            }
        }
    }


    private fun reset() {

        var options = mutableListOf<TextView>(
            optionOneView,
            optionTwoView,
            optionThreeView,
            optionFourView,
        )

        options.forEach {

            it.background = ContextCompat.getDrawable(this, R.drawable.default_option_border_bg)
            it.isEnabled = true
        }

        submitButton.text = "SUBMIT"
        selectedOptionText = ""

    }

}