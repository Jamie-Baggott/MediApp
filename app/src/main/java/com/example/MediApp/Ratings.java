package com.example.MediApp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class Ratings extends AppCompatActivity {


    Button home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ratings);

        home = findViewById(R.id.returnIntro);

        home.setOnClickListener(view -> {
            Intent home = new Intent(getApplicationContext(), Intro.class);
            startActivity(home);
            finish();
        });
    }
}