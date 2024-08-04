package com.bignerdranch.android.geoquiz

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.bignerdranch.android.geoquiz.databinding.ActivityMainBinding
import com.bignerdranch.android.geoquiz.ui.Question

class MainActivity : ComponentActivity() {

    private lateinit var binding : ActivityMainBinding

    private val questionBank = listOf(
        Question(R.string.question_poland, true),
        Question(R.string.question_mazovia, false),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false)
    )
    private var currentIndex = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.trueButton.setOnClickListener {
            checkAnswer(true)
        }

        binding.falseButton.setOnClickListener {
            checkAnswer(false)
        }

        binding.nextButton.setOnClickListener {
            cycleQuestion()
        }

        binding.previousButton.setOnClickListener{
            cycleQuestion(false)
        }

        binding.questionTextview.setOnClickListener {
            cycleQuestion()
        }
        updateQuestion()
    }

    private fun updateQuestion() {
        val questionTextResId = questionBank[currentIndex].textResId
        binding.questionTextview.setText(questionTextResId)
    }

    private fun cycleQuestion(forward : Boolean = true) {
        currentIndex = if (forward) {
            (currentIndex + 1) % questionBank.size
        } else {
            if (currentIndex == 0) {
                questionBank.size - 1
            } else {
                (currentIndex - 1) % questionBank.size
            }
        }
        updateQuestion()
    }

    private fun checkAnswer(userAnswer : Boolean) {
        val correctAnswer = questionBank[currentIndex].answer
        val messageResId = if (userAnswer == correctAnswer) {
            R.string.correct_toast
        } else {
            R.string.incorrect_toast
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()
    }
}
