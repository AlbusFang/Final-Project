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

        final Button returnButton = findViewById(R.id.returnToMain);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnToMainIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(returnToMainIntent);
            }
        });
    }
}
