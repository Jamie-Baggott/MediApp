package com.example.MediApp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class InsuranceSignUp extends AppCompatActivity {

    TextInputEditText textInputInsuranceName;
    Button buttonSignUp, home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insurance_sign_up);

        textInputInsuranceName = findViewById(R.id.insuranceCompany);

        buttonSignUp = findViewById(R.id.buttonSignUp);


        home = findViewById(R.id.returnIntro);

        home.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), Intro.class);
            startActivity(intent);
            finish();
        });



        buttonSignUp.setOnClickListener(view -> {

            final String Name;
            Name = String.valueOf(textInputInsuranceName.getText());


            if(!Name.equals("")) {

                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(() -> {
                    //Starting Write and Read data with URL
                    //Creating array for parameters
                    String[] field = new String[1];
                    field[0] = "Name";
                    //Creating array for data
                    String[] data = new String[1];
                    data[0] = Name;

                    PutData putData = new PutData("http://192.168.1.100/MediApp/insurancesignup.php", "POST", field, data);
                    if (putData.startPut()) {
                        if (putData.onComplete()) {
                            String result = putData.getResult();
                            if (result.equals("Sign Up Success")){
                                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), Intro.class);
                                startActivity(intent);
                                finish();
                            }
                            else{
                                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();                                    }
                        }
                    }
                    //End Write and Read data with URL
                });
            }
            else{
                Toast.makeText(getApplicationContext(), "All fields are required", Toast.LENGTH_SHORT).show();
            }
        });
    }
}