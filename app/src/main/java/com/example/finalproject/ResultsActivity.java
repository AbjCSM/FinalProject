package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ResultsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_acitivity);

        // Get the user's score from the intent
        int correctAnswersCount = getIntent().getIntExtra("correctAnswersCount", 0);
        int totalQuestions = getIntent().getIntExtra("totalQuestions", 0);

        // Display the user's score
        TextView textViewScore = findViewById(R.id.textViewScore);
        textViewScore.setText("Your Score: " + correctAnswersCount + "/" + totalQuestions);

        // Set up play again button
        Button buttonPlayAgain = findViewById(R.id.buttonPlayAgain);
        buttonPlayAgain.setOnClickListener(v -> {
            // Restart the game by navigating back to the MainQuestionsActivity
            Intent intent = new Intent(ResultsActivity.this, MainQuestionsActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // Clear the activity stack
            startActivity(intent);
            finish(); // Finish the current activity
        });
    }
}

