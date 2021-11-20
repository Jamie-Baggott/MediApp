package com.example.MediApp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class Intro extends AppCompatActivity {

    Button gpHome, patientHome, about, support, insuranceReg, ratings ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        gpHome = findViewById(R.id.gpButton);
        patientHome = findViewById(R.id.patientButton);
        about = findViewById(R.id.aboutButton);
        support = findViewById(R.id.supportButton);
        ratings = findViewById(R.id.ratingsButton);
        insuranceReg = findViewById(R.id.insuranceButton);


        gpHome.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), GPLogin.class);
            startActivity(intent);
            finish();
        });
        patientHome.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), PatientLogin.class);
            startActivity(intent);
            finish();
        });
        about.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), AboutActivity.class);
            startActivity(intent);
            finish();
        });
        support.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), SupportActivity.class);
            startActivity(intent);
            finish();
        });
        ratings.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), Ratings.class);
            startActivity(intent);
            finish();
        });
        insuranceReg.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), InsuranceSignUp.class);
            startActivity(intent);
            finish();
        });
    }
}