package com.example.MediApp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class AboutActivity extends AppCompatActivity {

    Button home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        home = findViewById(R.id.returnIntro);

        home.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), Intro.class);
            startActivity(intent);
            finish();
        });
    }
}