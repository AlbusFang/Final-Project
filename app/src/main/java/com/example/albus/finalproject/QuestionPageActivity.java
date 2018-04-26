package com.example.albus.finalproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import java.net.URLDecoder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class QuestionPageActivity extends AppCompatActivity {
private static final String TAG = "QuestionPageActivity";
public static int questionCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_page);
        final String[] answerArray = MainActivity.questions[questionCount].shuffleAnswers();

        final TextView questionView = findViewById(R.id.question_view);
        questionView.setText(MainActivity.questions[questionCount].getQuestion());

        final Button answer_1 = findViewById(R.id.answer_1);
        answer_1.setText(answerArray[0]);
        answer_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (answerArray[0].equals(MainActivity.questions[questionCount]
                        .getCorrectAnswer())) {
                    AnswerPageActivity.rightOrWrong = "right!";
                } else {
                    AnswerPageActivity.rightOrWrong = "wrong!";
                }
                Intent answerPageIntent = new Intent(getApplicationContext(),
                        AnswerPageActivity.class);
                startActivity(answerPageIntent);
            }
        });

        final Button answer_2 = findViewById(R.id.answer_2);
        answer_2.setText(answerArray[1]);
        answer_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (answerArray[1].equals(MainActivity.questions[questionCount]
                        .getCorrectAnswer())) {
                    AnswerPageActivity.rightOrWrong = "right!";
                } else {
                    AnswerPageActivity.rightOrWrong = "wrong!";
                }
                Intent answerPageIntent = new Intent(getApplicationContext(),
                        AnswerPageActivity.class);
                startActivity(answerPageIntent);
            }
        });
        final Button answer_3 = findViewById(R.id.answer_3);
        answer_3.setText(answerArray[2]);
        answer_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (answerArray[2].equals(MainActivity.questions[questionCount]
                        .getCorrectAnswer())) {
                    AnswerPageActivity.rightOrWrong = "right!";
                } else {
                    AnswerPageActivity.rightOrWrong = "wrong!";
                }
                Intent answerPageIntent = new Intent(getApplicationContext(),
                        AnswerPageActivity.class);
                startActivity(answerPageIntent);
            }
        });
        final Button answer_4 = findViewById(R.id.answer_4);
        answer_4.setText(answerArray[3]);
        answer_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (answerArray[3].equals(MainActivity.questions[questionCount]
                        .getCorrectAnswer())) {
                    AnswerPageActivity.rightOrWrong = "right!";
                } else {
                    AnswerPageActivity.rightOrWrong = "wrong!";
                }
                Intent answerPageIntent = new Intent(getApplicationContext(),
                        AnswerPageActivity.class);
                startActivity(answerPageIntent);
            }
        });
    }
}
