package com.example.albus.finalproject;

import android.content.Intent;
import android.os.TestLooperManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class AnswerPageActivity extends AppCompatActivity {
    public static String rightOrWrong = "-----";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_page);

        final ImageView answerImage = findViewById(R.id.answerImage);
        if (rightOrWrong.equals("right!")) {
            answerImage.setImageResource(R.drawable.btn_star_big_on);
        } else {
            answerImage.setImageResource(R.drawable.ic_delete);
        }

        final TextView answerText = findViewById(R.id.answerValue);
        answerText.setText(rightOrWrong);

        final TextView correctAnswerView = findViewById(R.id.correctAnswer);
        if (rightOrWrong.equals("wrong!")) {
            correctAnswerView.setVisibility(View.VISIBLE);
            correctAnswerView.setText("The correct answer was: "
                    + MainActivity.questions[QuestionPageActivity.questionCount]
                    .getCorrectAnswer());
        }

        /** Button to take the player to the next question page.
         * If the player has arrived at the last question, they are returned to the main activity.
         * If the last question was answered incorrectly, this button is not visible.
         */
        final Button nextQuestion = findViewById(R.id.nextQuestion);
        if (rightOrWrong.equals("right!")) {
            nextQuestion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MainActivity.currentScore++;
                    if (MainActivity.currentScore > MainActivity.highScore) {
                        MainActivity.highScore = MainActivity.currentScore;
                    }
                    if (QuestionPageActivity.questionCount == 9) {
                        QuestionPageActivity.questionCount = 0;
                        MainActivity.currentScore = 0;
                        Intent returnToMainIntent = new Intent(getApplicationContext(),
                                MainActivity.class);
                        startActivity(returnToMainIntent);
                    } else {
                        QuestionPageActivity.questionCount++;
                        Intent nextQuestionIntent = new Intent(getApplicationContext(),
                                QuestionPageActivity.class);
                        startActivity(nextQuestionIntent);
                    }
                }
            });
        } else {
            nextQuestion.setVisibility(View.GONE);
        }


        final Button returnButton = findViewById(R.id.returnToMain);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QuestionPageActivity.questionCount = 0;
                if (rightOrWrong.equals("right!")) {
                    MainActivity.currentScore++;
                    if (MainActivity.currentScore > MainActivity.highScore) {
                        MainActivity.highScore = MainActivity.currentScore;
                    }
                }
                // else display correct answer
                MainActivity.currentScore = 0;
                Intent returnToMainIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(returnToMainIntent);
            }
        });
    }
}
