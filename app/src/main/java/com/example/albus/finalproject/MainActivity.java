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
import java.util.Hashtable;

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
    private static Hashtable<String,Integer> categories = new Hashtable<>(); //maps category strings to their ids
    private static int category; //we need the category to be the matching id number
    private static Question[] questions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        buildMap();
        super.onCreate(savedInstanceState);
        requestQueue = Volley.newRequestQueue(this);
        setContentView(R.layout.activity_main);

        final Spinner spinner = findViewById(R.id.ChooseCategories);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Choose_Categories, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try {
                    String temp = parent.getItemAtPosition(position).toString(); //gets string from spinner
                    if (temp.equalsIgnoreCase("Choose a category")) {
                        temp = "General Knowledge"; //this is the key for the random category
                    }
                    category = categories.get(temp); //get id number from hashtable
                    Log.d(TAG, "Category set to " + category);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.d(TAG, "Nothing selected");
            }
        });

        final Button startGame = findViewById(R.id.start);
        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Log.d(TAG, "Make Call");
                makeAPICall();
            }

        });
    }

    /**
     * makes an api call, triggering the beginning of the game.
     *
     */
    public void makeAPICall() {
            try {
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                        Request.Method.GET,
                        "https://opentdb.com/api.php?amount=10&difficulty=medium&type=multiple&encode=url3986&category=" + category,
                        null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(final JSONObject response) {
                               Log.d(TAG, "Request Received:"+ response.toString());
                                //create class objects for every question in response
                               questions = getQuestions(response);

                               setContentView(R.layout.question_page);

                               final TextView question = findViewById(R.id.question_view);
                               question.setText(questions[0].getQuestion());
                                //randomly shuffle answer array so every answer is a different letter
                               String[] answers = questions[0].shuffleAnswers();

                               final Button answer1 = findViewById(R.id.answer_1);
                               answer1.setText(answers[0]);

                               final Button answer2 = findViewById(R.id.answer_2);
                               answer2.setText(answers[1]);

                               final Button answer3 = findViewById(R.id.answer_3);
                               answer3.setText(answers[2]);

                               final Button answer4 = findViewById(R.id.answer_4);
                               answer4.setText(answers[3]);

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
    /**
     * reads and parses the return json, creating question objects for all received questions
     * @param response the json received from api call
     * @return an array of question objects
     */
    private static Question[] getQuestions(JSONObject response) {
        Question[] questions = new Question[10];
        try {
            JSONArray result = response.getJSONArray("results");
            for (int i = 0; i < questions.length; i++) {
                //parse and decode each question and the respective answers
                String question = result.getJSONObject(i).get("question").toString();
                question = URLDecoder.decode(question);

                String correct_answer = result.getJSONObject(i).get("correct_answer").toString();
                correct_answer = URLDecoder.decode(correct_answer);

                //put each answer, including the correct answer, into an array
                String[] answers = new String[4];
                answers[0] = correct_answer;

                for (int j = 1; j < answers.length; j++) {

                    answers[j] = result.getJSONObject(i).getJSONArray("incorrect_answers").get(j - 1).toString();
                    answers[j] = URLDecoder.decode(answers[j]);  //i could do this in one line but this
                                                                // looks so much easier to read

                }
                //create an instance of question for every individual question
                questions[i] = new Question(question, correct_answer, answers);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return questions;
    }

    /**
     * i didn't know how to do this any other way. There is probably a better way to initialize the map but idk.
     * also im sorry the ids are out of order, im an inherently lazy person :).
     */
    private static void buildMap() {
        categories.put("General Knowledge",9);
        categories.put("Entertainment: Books",11);
        categories.put("Entertainment: Music",12);
        categories.put("Science: Computer",18);
        categories.put("Science: Math",19);
        categories.put("Vehicle",28);
        categories.put("Entertainment: Musicals and Theatres", 13);
        categories.put("Entertainment: Television", 14);
        categories.put("Entertainment: Video Games", 15);
        categories.put("Entertainment: Board Games", 16);
        categories.put("Science and Nature", 17);
        categories.put("Mythology", 20);
        categories.put("Sports", 21);
        categories.put("Geography", 22);
        categories.put("History", 23);
        categories.put("Politics", 24);
        categories.put("Art", 25);
        categories.put("Celebrities", 26);
        categories.put("Animals", 27);
        categories.put("Entertainment: Comics", 29);
        categories.put("Science: Gadgets", 30);
        categories.put("Entertainment: Japanese Anime and Manga", 31);
        categories.put("Entertainment: Cartoon and Animations", 32);
    }
}