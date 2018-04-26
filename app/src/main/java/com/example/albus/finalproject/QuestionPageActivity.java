package com.example.albus.finalproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_page);

        final TextView question = findViewById(R.id.question_view);
        final Button answer_1 = findViewById(R.id.answer_1);
        answer_1.setText("Check AnswerPageActivity");
        answer_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent answerPageIntent = new Intent(getApplicationContext(),
                        AnswerPageActivity.class);
                startActivity(answerPageIntent);
            }
        });

        final Button answer_2 = findViewById(R.id.answer_2);
        answer_2.setText("Check Button Click");
        answer_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer_2.setText("Clicked!");
            }
        });
        final Button answer_3 = findViewById(R.id.answer_3);
        final Button answer_4 = findViewById(R.id.answer_4);

    }
}
