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

public class GPSignUp extends AppCompatActivity {

    TextInputEditText textInputEditTextFName, textInputEditTextLName, textInputEditTextEmail, textInputEditTextPassword, textInputEditTextTel, textInputEditTextUID;
    Button buttonSignUp, home, gpLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_gp);

        textInputEditTextFName = findViewById(R.id.gpFName);
        textInputEditTextLName = findViewById(R.id.gpLName);
        textInputEditTextEmail = findViewById(R.id.gpEmailAddress);
        textInputEditTextPassword = findViewById(R.id.gpPassword);
        textInputEditTextTel = findViewById(R.id.gpNumber);
        textInputEditTextUID = findViewById(R.id.gpUserID);

        buttonSignUp = findViewById(R.id.buttonSignUp);

        gpLogin = findViewById(R.id.buttonGPLogin);

        home = findViewById(R.id.returnIntro);

        home.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), Intro.class);
            startActivity(intent);
            finish();
        });



        gpLogin.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), GPLogin.class);
            startActivity(intent);
            finish();
        });

        buttonSignUp.setOnClickListener(view -> {

            final String gpfname, gplname, gptel, gp_email, gpuid, gp_pwd;
            gpfname = String.valueOf(textInputEditTextFName.getText());
            gplname = String.valueOf(textInputEditTextLName.getText());
            gp_email = String.valueOf(textInputEditTextEmail.getText());
            gp_pwd = String.valueOf(textInputEditTextPassword.getText());
            gptel = String.valueOf(textInputEditTextTel.getText());
            gpuid = String.valueOf(textInputEditTextUID.getText());


            if(!gpfname.equals("") && !gplname.equals("") && !gp_pwd.equals("") && !gp_email.equals("") && !gptel.equals("") && !gpuid.equals("")) {

                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(() -> {
                    //Starting Write and Read data with URL
                    //Creating array for parameters
                    String[] field = new String[6];
                    field[0] = "gpfname";
                    field[1] = "gplname";
                    field[2] = "gptel";
                    field[3] = "gp_email";
                    field[4] = "gpuid";
                    field[5] = "gp_pwd";
                    //Creating array for data
                    String[] data = new String[6];
                    data[0] = gpfname;
                    data[1] = gplname;
                    data[2] = gptel;
                    data[3] = gp_email;
                    data[4] = gpuid;
                    data[5] = gp_pwd;

                    PutData putData = new PutData("http://192.168.1.100/MediApp/gpsignup.php", "POST", field, data);
                    if (putData.startPut()) {
                        if (putData.onComplete()) {
                            String result = putData.getResult();
                            if (result.equals("Sign Up Success")){
                                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(GPSignUp.this, GPLogin.class);
                                intent.putExtra("gpfname", gp_email);
                                intent.putExtra("gplname", gp_pwd);
                                intent.putExtra("gptel", gptel);
                                intent.putExtra("gp_email", gp_email);
                                intent.putExtra("gpepuid", gpuid);
                                intent.putExtra("gp_pwd", gp_pwd);
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