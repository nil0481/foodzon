package com.example.foodzon;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class welcomescreen extends AppCompatActivity {
    private static final String url="http://www.json-generator.com/api/json/get/cemZzxcqhu?indent=2";

    //private static final String url="https://api.github.com/users";
    RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcoming);
        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        //recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(welcomescreen.this));
        StringRequest stringRequest=new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                GsonBuilder gsonBuilder=new GsonBuilder();
                Gson gson=gsonBuilder.create();
                Example[] examples=gson.fromJson(response,Example[].class);
                recyclerView.setAdapter(new adapterclass(welcomescreen.this,examples));
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(welcomescreen.this,"something went wrong",Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue queue= Volley.newRequestQueue(this);
        queue.add(stringRequest);

    }
}
