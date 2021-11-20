package com.example.MediApp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class GPLogin extends AppCompatActivity {

    TextInputEditText textInputEditTextGPEmail, textInputEditTextGPPassword;
    Button buttonLogin, home, buttonGPSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_gp);

        textInputEditTextGPEmail = findViewById(R.id.gpEmail);
        textInputEditTextGPPassword = findViewById(R.id.gpPassword);

        buttonGPSignUp = findViewById(R.id.gpSignUp);

        buttonLogin = findViewById(R.id.buttonLogin);


        home = findViewById(R.id.returnIntro);

        home.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), Intro.class);
            startActivity(intent);
            finish();
        });


        buttonGPSignUp.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), GPSignUp.class);
            startActivity(intent);
            finish();
        });


        buttonLogin.setOnClickListener(view -> {

            final String  gp_email, gp_pwd;
            gp_email = String.valueOf(textInputEditTextGPEmail.getText());
            gp_pwd = String.valueOf(textInputEditTextGPPassword.getText());


            if(!gp_email.equals("") && !gp_pwd.equals("")) {
                Handler handler = new Handler();
                handler.post(() -> {
                    String[] field = new String[2];
                    field[0] = "gp_email";
                    field[1] = "gp_pwd";
                    String[] data = new String[2];
                    data[0] = gp_email;
                    data[1] = gp_pwd;
                    PutData putData = new PutData("http://192.168.1.100/MediApp/gplogin.php", "POST", field, data);
                    if (putData.startPut()) {
                        if (putData.onComplete()) {
                            String result = putData.getResult();
                            if (result.equals("Login Success")){
                                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(GPLogin.this, MainActivity.class);
                                intent.putExtra("gp_email", gp_email);
                                intent.putExtra("gp_pwd", gp_pwd);
                                startActivity(intent);
                                finish();
                            }
                            else{
                                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();                                    }
                        }
                    }
                });
            }
            else{
                Toast.makeText(getApplicationContext(), "All fields are required", Toast.LENGTH_SHORT).show();
            }
        });
    }
}