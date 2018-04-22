package com.example.albus.finalproject;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.json.JSONObject;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.*;




// Comment.
//


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
            Request.Method.GET,
            "",
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
}
