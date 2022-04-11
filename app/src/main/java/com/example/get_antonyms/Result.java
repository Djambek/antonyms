package com.example.get_antonyms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Result extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Intent intent = getIntent();
        String status = intent.getStringExtra("status");
        String ant = intent.getStringExtra("antonyms");
        Log.d("AAA", "Activity started");
        ArrayList<String> list = new ArrayList<>();
        if (!status.equals("ok")){
            list.add("Ничего не найдено");
            Log.d("AAA", String.valueOf(list));
        }else{
            ant = ant.replace("[", "").replace("]", "");
            for(String el: ant.split(",")){
                list.add(el);
            }

        }
        ListView listView = findViewById(R.id.listview);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);

    }
}