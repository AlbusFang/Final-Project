package com.example.albus.finalproject;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.net.URLDecoder;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestQueue = Volley.newRequestQueue(this);
        setContentView(R.layout.activity_main);
        final Button rules = (Button) findViewById(R.id.rules);
        rules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Pop.class));
            }
        });
        final Button startGame = findViewById(R.id.start);
        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Log.d(TAG, "Make Call");
                makeAPICall("none");

            }

        });
        Intent intent = getIntent();
        int score = intent.getIntExtra("Score:",0);
        TextView textView = (TextView) this.findViewById(R.id.score);
        textView.setText("Score:  "+ String.valueOf(score));
    }

    /**
     * makes an api call, triggering the beginning of the game.
     * @param category sets the category of questions
     */
    public static void makeAPICall(String category) {
        if (category.equals("none")) {
            try {
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                        Request.Method.GET,
                        "https://opentdb.com/api.php?amount=10&difficulty=medium&type=multiple&encode=url3986",
                        null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(final JSONObject response) {
                               Log.d(TAG, "Request Received");
                               Question[] questions = getQuestions(response);
                               //makeQuestionPage(questions);

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(final VolleyError error) {
                        Log.w(TAG, error.toString());
                    }
                });
                requestQueue.add(jsonObjectRequest);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                        Request.Method.GET,
                        "https://opentdb.com/api.php?amount=10&difficulty=medium&type=multiple&encode=url3986&" + category,
                        null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(final JSONObject response) {
                                Log.d(TAG, response.toString());
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(final VolleyError error) {
                        Log.w(TAG, error.toString());
                    }
                });
                requestQueue.add(jsonObjectRequest);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * reads and parses the return json, creating question objects for all received questions
     * @param response the json received from api call
     * @return an array of question objects
     */
    private static Question[] getQuestions(JSONObject response) {
        Question[] questions = new Question[10];
        try {
            JSONArray result = response.getJSONArray("results");
            for (int index = 0; index < questions.length; index++) {
                String q = result.getJSONObject(index).get("question").toString();
                q = URLDecoder.decode(q);
                String correct_answer = result.getJSONObject(index).get("correct_answer").toString();
                String[] incorrect_answers = new String[3];
                for (int j = 0; j < incorrect_answers.length; j++) {
                    incorrect_answers[j] = result.getJSONObject(index).getJSONArray("incorrect_answers").get(j).toString();
                }
                questions[index] = new Question(q, correct_answer, incorrect_answers);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return questions;
    }

    /**
     * tbd.
     * @param questions an array of question objects
     */
    private static void makeQuestionPage(final Question[] questions) {

    }
}
