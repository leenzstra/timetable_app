package com.leenz.pnrpu;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = findViewById(R.id.textView);
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest r = new JsonObjectRequest(Request.Method.GET, "https://dog.ceo/api/breeds/image/random",null,new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                textView.setText("Response: " + response.toString());
            }
        }, error -> Log.e("Error", error.toString()));
        queue.add(r);
    }



}