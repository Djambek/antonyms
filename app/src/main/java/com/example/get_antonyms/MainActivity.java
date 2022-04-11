package com.example.get_antonyms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b = findViewById(R.id.button);
        EditText editText = findViewById(R.id.editTextTextPersonName);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Thread thread = new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        String url = "https://antonymonline.ru/antonyms.json?word="+editText.getText();
                        try {
                            Document res = Jsoup.connect(url).get();
                            JSONObject jsonObject = new JSONObject(res.text());
                            Log.d("AAA", res.text());
                            Intent intent = new Intent(MainActivity.this, Result.class);
                            intent.putExtra("status", jsonObject.getString("status"));
                            if (!res.text().contains("antonyms")) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getApplicationContext(), "Ничего не найдено", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }else{
                                intent.putExtra("antonyms", jsonObject.getString("antonyms"));
                                startActivity(intent);
                            }

                        } catch (IOException | JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                thread.start();
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        });
    }
}