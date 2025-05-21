package com.example.mathspuzzle

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mathspuzzle.R
import kotlin.random.Random



class MainActivity : AppCompatActivity() {

    private var correctAnswer: Int = 0 // To store the correct answer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initializing UI components
        val gameTitle = findViewById<TextView>(R.id.gameTitle)
        val instructionText = findViewById<TextView>(R.id.instructionText)
        val mathProblem = findViewById<TextView>(R.id.mathProblem)
        val userInput = findViewById<EditText>(R.id.userInput)
        val submitButton = findViewById<Button>(R.id.submitButton)
        val answerText = findViewById<TextView>(R.id.answerText)
        val rewardImage = findViewById<ImageView>(R.id.rewardImage)

        // Ensuring that the answer text and reward image are initially hidden
        answerText.visibility = View.GONE
        rewardImage.visibility = View.GONE

        // Dynamically generate a math problem
        generateMathProblem()

        // Set up click listener for the submit button
        submitButton.setOnClickListener {
            // Retrieve the user input
            val userAnswer = userInput.text.toString()

            if (userAnswer.isNotEmpty()) {
                // Check if the answer is correct
                if (userAnswer.toInt() == correctAnswer) {
                    answerText.text = "Correct! The answer is: $correctAnswer"
                    answerText.setTextColor(resources.getColor(android.R.color.holo_green_dark))
                    rewardImage.visibility = View.VISIBLE
                } else {
                    answerText.text = "Incorrect! Try again."
                    answerText.setTextColor(resources.getColor(android.R.color.holo_red_dark))
                }

                // Show the answer text
                answerText.visibility = View.VISIBLE
            } else {
                // If input is empty, show a warning
                Toast.makeText(this, "Please enter an answer", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Function to generate a random math problem and set the correct answer
    private fun generateMathProblem() {
        val num1 = Random.nextInt(1, 10)
        val num2 = Random.nextInt(1, 10)
        val operation = Random.nextInt(1, 4) // 1: addition, 2: subtraction, 3: multiplication

        // Create the math problem and calculate the answer
        val problemText: String
        correctAnswer = when (operation) {
            1 -> {
                problemText = "Solve: $num1 + $num2"
                num1 + num2
            }
            2 -> {
                problemText = "Solve: $num1 - $num2"
                num1 - num2
            }
            else -> {
                problemText = "Solve: $num1 * $num2"
                num1 * num2
            }
        }

        // Update the math problem in the UI
        val mathProblem = findViewById<TextView>(R.id.mathProblem)
        mathProblem.text = problemText
    }
}
