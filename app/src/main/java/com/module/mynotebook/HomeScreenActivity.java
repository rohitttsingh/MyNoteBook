package com.module.mynotebook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class HomeScreenActivity extends AppCompatActivity {
FloatingActionButton floatingActionButton;
RecyclerView recyclerView;
    Adapter adapter;
    private ArrayList<Note> courseModalArrayList;

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        floatingActionButton=findViewById(R.id.fab);
        recyclerView=findViewById(R.id.recycle);

        loadData();
        buildRecyclerView();

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),AddNotesActivity.class));
            }
        });
    }
    private void buildRecyclerView() {
        // initializing our adapter class.
        adapter = new Adapter(courseModalArrayList, HomeScreenActivity.this);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(manager);

        recyclerView.setAdapter(adapter);
    }
    private void loadData() {

        Gson gson = new Gson();
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);

        String json = sharedPreferences.getString("courses", null);
        Type type = new TypeToken<ArrayList<Note>>() {}.getType();

        courseModalArrayList = gson.fromJson(json, type);

        if (courseModalArrayList == null) {

            courseModalArrayList = new ArrayList<>();
        }
    }
}