package com.example.MediApp;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class PatientLogin extends AppCompatActivity {

    TextInputEditText textInputEditTextPatientEmail, textInputEditTextPatientPassword;
    Button buttonLogin, home, buttonPatientSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_patient);

        textInputEditTextPatientEmail = findViewById(R.id.patientEmail);
        textInputEditTextPatientPassword = findViewById(R.id.patientPassword);


        buttonPatientSignUp = findViewById(R.id.patientSignUp);

        buttonLogin = findViewById(R.id.buttonLogin);

        home = findViewById(R.id.returnIntro);

        home.setOnClickListener(view -> {
            Intent home = new Intent(getApplicationContext(), Intro.class);
            startActivity(home);
            finish();
        });


        buttonPatientSignUp.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), PatientSignUp.class);
            startActivity(intent);
            finish();
        });


        buttonLogin.setOnClickListener(view -> {

            final String  email, pat_pwd;
            email = String.valueOf(textInputEditTextPatientEmail.getText());
            pat_pwd = String.valueOf(textInputEditTextPatientPassword.getText());


            if(!email.equals("") && !pat_pwd.equals("")) {
                Handler handler = new Handler();
                handler.post(() -> {
                    String[] field = new String[2];
                    field[0] = "email";
                    field[1] = "pat_pwd";
                    String[] data = new String[2];
                    data[0] = email;
                    data[1] = pat_pwd;
                    PutData putData = new PutData("http://192.168.1.100/MediApp/patientlogin.php", "POST", field, data);
                    if (putData.startPut()) {
                        if (putData.onComplete()) {
                            String result = putData.getResult();
                            if (result.equals("Login Success")){
                                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                intent.putExtra("email", email);
                                intent.putExtra("pat_pwd", pat_pwd);
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