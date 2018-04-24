package com.example.albus.finalproject;
import android.app.Activity;
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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
//import com.google.JsonParser;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static RequestQueue requestQueue;
    public static String category = "none";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestQueue = Volley.newRequestQueue(this);
        setContentView(R.layout.activity_main);

        final Spinner spinner = (Spinner) findViewById(R.id.ChooseCategories);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Choose_Categories, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                category = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        final Button startGame = findViewById(R.id.start);
        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Log.d(TAG, "Make Call");
  //              makeAPICall("none");

            }

        });
    }


  //  public static void makeAPICall(String category) {
  //      if (category.equals("none")) {
//            try {
  //              JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
    //                    Request.Method.GET,
    //                    "https://opentdb.com/api.php?amount=10&difficulty=medium&type=multiple&encode=url3986",
    //                    null,
    //                    new Response.Listener<JSONObject>() {
    //                        @Override
    //                        public void onResponse(final JSONObject response) {
    //                           Log.d(TAG, "Response Received");
     //                          String[] questions = getQuestion(response);
    //                           makeQuestionPage(questions);
    //                        }
    //                    }, new Response.ErrorListener() {
    //                @Override
   //                 public void onErrorResponse(final VolleyError error) {
    //                    Log.w(TAG, error.toString());
    //                }
    //            });
    //            requestQueue.add(jsonObjectRequest);
    //        } catch (Exception e) {
    //            e.printStackTrace();
    //        }
    //    } else {
    //        try {
    //            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
    //                    Request.Method.GET,
    //                    "https://opentdb.com/api.php?amount=10&difficulty=medium&type=multiple&encode=url3986&" + category,
    //                    null,
    //                    new Response.Listener<JSONObject>() {
    //                        @Override
    //                        public void onResponse(final JSONObject response) {
    //                            Log.d(TAG, response.toString());
     //                       }
      //                  }, new Response.ErrorListener() {
      //              @Override
       //             public void onErrorResponse(final VolleyError error) {
       //                 Log.w(TAG, error.toString());
       //             }
        //        });
         //       requestQueue.add(jsonObjectRequest);
          //  } catch (Exception e) {
           //     e.printStackTrace();
      //      }
      //  }
  //  }
///*
//Log.d(TAG, response.toString());
  //      String[] questions = new String[10];
  //      try {
  //          JSONArray results = response.getJSONArray("results");
  //          for (int i = 0; i < results.length(); i++) {
  //              JSONObject question = results.getJSONObject(i);
  //              String question1 = question.get("question").toString();
  //              questions[i] = URLDecoder.decode(question1);
  //          }
  //      } catch (Exception e) {
 //           e.printStackTrace();
 //       }
 //       //Log.d(TAG, questions[1]);
 //       return questions;
// */
    //private static JSONObject getQuestion(JSONObject response) { }

    //private static void makeQuestionPage(final String[] questions, final String[] answers) { }


}
