package com.example.MediApp;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView email = findViewById(R.id.email);


        Intent gp = getIntent();
        String gpEmail = gp.getStringExtra("gp_email");
        Intent patient = getIntent();
        String patientEmail = patient.getStringExtra("email");

        email.setText(patientEmail);
        email.setText(gpEmail);


        Button home1 = findViewById(R.id.returnIntro);

        home1.setOnClickListener(view -> {
            Intent home = new Intent(getApplicationContext(), Intro.class);
            startActivity(home);
            finish();
        });

    }
}