package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
//import static com.example.finalproject.Questions.TriviaQuestion;
// ^<-- I Deleted the Static inner class above. Its One class now Named TriviaQuestion
public class MainQuestionsActivity extends AppCompatActivity {

    int selectedAnswerIndex = -1; //to keep track of the selected answer
    int correctAnswersCount = 0; //to keep a count of the questions answered correctly
    int currentQuestionIndex = 0; //the index of the question being displayed
    ArrayList<TriviaQuestion> questionsList = new ArrayList<TriviaQuestion>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_questions);

        //Reads questions from input file
        questionsList = readQuestionsFromAssets();

        //Initializes the first question display
        if(questionsList !=null && !questionsList.isEmpty()) {
            displayQuestion();
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private ArrayList<TriviaQuestion> readQuestionsFromAssets(){

        ArrayList<TriviaQuestion> questions = new ArrayList<>();
        TriviaQuestion question_Placeholder = new TriviaQuestion("How many times have played Trivia","0","1","2","More than Twice ", 3);
        try {
            InputStream is = getAssets().open("questions.JSON");
            InputStreamReader isr = new InputStreamReader(is);
            Type questionListType = new
                    TypeToken<ArrayList<TriviaQuestion>>() {
                    }.getType();
            questions = new Gson().fromJson(isr, questionListType);
            isr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
            return questions;
        }

    public void submitAnswer(View v){
        if (selectedAnswerIndex != -1) {
            TriviaQuestion currentQuestion = questionsList.get(currentQuestionIndex);
            if (selectedAnswerIndex == currentQuestion.getCorrectAnswerIndex()) {
                correctAnswersCount++;
            }
            selectedAnswerIndex = -1; //Reset the selected answer index

            currentQuestionIndex++;
            if (currentQuestionIndex < questionsList.size()) {
                displayQuestion();
            } else {
                Intent intent = new Intent(this, ResultsActivity.class);
                intent.putExtra("correctAnswersCount", correctAnswersCount);
                intent.putExtra("totalQuestions", questionsList.size());
                startActivity(intent);
                finish();
            }
        }
    }


    public void radioButtonClick(View view){
        if (view.getId() == R.id.radioButtonOption0) selectedAnswerIndex = 0;
        if (view.getId() == R.id.radioButtonOption1) selectedAnswerIndex = 1;
        if (view.getId() == R.id.radioButtonOption2) selectedAnswerIndex = 2;
        if (view.getId() == R.id.radioButtonOption3) selectedAnswerIndex = 3;
    }

    public void displayQuestion() {
        if (currentQuestionIndex < questionsList.size()) {
            TriviaQuestion currentQuestion = questionsList.get(currentQuestionIndex);
            TextView tv = findViewById(R.id.textViewQuestion);
            tv.setText(currentQuestion.getQuestion());

            RadioButton rb0 = findViewById(R.id.radioButtonOption0);
            rb0.setText(currentQuestion.getOption(0));
            RadioButton rb1 = findViewById(R.id.radioButtonOption1);
            rb1.setText(currentQuestion.getOption(1));
            RadioButton rb2 = findViewById(R.id.radioButtonOption2);
            rb2.setText(currentQuestion.getOption(2));
            RadioButton rb3 = findViewById(R.id.radioButtonOption3);
            rb3.setText(currentQuestion.getOption(3));
        }
    }
}
