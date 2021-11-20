package com.example.MediApp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class PatientSignUp extends AppCompatActivity {

    TextInputEditText textInputEditTextFName, textInputEditTextLName, textInputEditTextAge, textInputEditTextPassword, textInputEditTextEmail
            , textInputEditTextTel, textInputEditTextHD, textInputEditTextDIA, textInputEditTextAL, textInputEditTextGP, textInputEditTextINS;
    Button buttonSignUp, home, buttonPatientLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_patient);

        textInputEditTextFName = findViewById(R.id.patientFName);
        textInputEditTextLName = findViewById(R.id.patientLName);
        textInputEditTextEmail = findViewById(R.id.patientEmail);
        textInputEditTextPassword = findViewById(R.id.patientPassword);
        textInputEditTextAge = findViewById(R.id.patientAge);
        textInputEditTextTel = findViewById(R.id.patientPhoneNumber);
        textInputEditTextAL = findViewById(R.id.patientAL);
        textInputEditTextDIA = findViewById(R.id.patientDIA);
        textInputEditTextHD = findViewById(R.id.patientHD);
        textInputEditTextGP = findViewById(R.id.patientGP);
        textInputEditTextINS = findViewById(R.id.patientINS);


        buttonSignUp = findViewById(R.id.buttonSignUp);

        buttonPatientLogin = findViewById(R.id.buttonPatientLogin);

        home = findViewById(R.id.returnIntro);

        home.setOnClickListener(view -> {
            Intent home = new Intent(getApplicationContext(), Intro.class);
            startActivity(home);
            finish();
        });


        buttonPatientLogin.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), PatientLogin.class);
            startActivity(intent);
            finish();
        });

        buttonSignUp.setOnClickListener(view -> {


            final String Fname;
            final String Lname;
            final String email;
            final String age;
            final String tel;
            final String al_flag;
            final String hd_flag;
            final String dia_flag;
            String al_risk;
            String hd_risk;
            String dia_risk;
            final String pat_pwd;
            final String gpno;
            final String insno;
            final String ref;
            Fname = String.valueOf(textInputEditTextFName.getText());
            Lname = String.valueOf(textInputEditTextLName.getText());
            email = String.valueOf(textInputEditTextEmail.getText());
            age = String.valueOf(textInputEditTextAge.getText());
            tel = String.valueOf(textInputEditTextTel.getText());
            al_flag = String.valueOf(textInputEditTextAL.getText());
            hd_flag = String.valueOf(textInputEditTextHD.getText());
            dia_flag = String.valueOf(textInputEditTextDIA.getText());
            al_risk = "";
            hd_risk = "";
            dia_risk = "";
            pat_pwd = String.valueOf(textInputEditTextPassword.getText());
            gpno = String.valueOf(textInputEditTextGP.getText());
            insno = String.valueOf(textInputEditTextINS.getText());
            ref = String.valueOf(0);

            int al = Integer.parseInt(al_flag);
            if (al < 30)
                al_risk ="Low";
            else if (al >30 && al <70 )
                al_risk = "Medium";
            else if (al > 70)
                al_risk = "High";

            int hd = Integer.parseInt(hd_flag);
            if (hd < 30)
                hd_risk ="Low";
            else if (hd >30 && hd <70 )
                hd_risk = "Medium";
            else if (hd >= 70)
                hd_risk = "High";

            int dia = Integer.parseInt(dia_flag);
            if (dia < 30)
                dia_risk ="Low";
            else if (dia >30 && dia <70 )
                dia_risk = "Medium";
            else if (dia > 70)
                dia_risk = "High";

            if(!Fname.equals("") && !Lname.equals("") && !email.equals("") && !age.equals("") && !tel.equals("") && !al_flag.equals("") &&
                    !hd_flag.equals("") && !dia_flag.equals("") && !pat_pwd.equals("") && !gpno.equals("") && !insno.equals("") ) {
                Handler handler = new Handler(Looper.getMainLooper());
                String finalAl_risk = al_risk;
                String finalHd_risk = hd_risk;
                String finalDia_risk = dia_risk;
                handler.post(() -> {
                    String[] field = new String[15];
                    field[0] = "Fname";
                    field[1] = "Lname";
                    field[2] = "email";
                    field[3] = "age";
                    field[4] = "tel";
                    field[5] = "al_flag";
                    field[6] = "hd_flag";
                    field[7] = "dia_flag";
                    field[8] = "al_risk";
                    field[9] = "hd_risk";
                    field[10] = "dia_risk";
                    field[11] = "pat_pwd";
                    field[12] = "gpno";
                    field[13] = "insno";
                    field[14] = "ref";


                    String[] data = new String[15];
                    data[0] = Fname;
                    data[1] = Lname;
                    data[2] = email;
                    data[3] = age;
                    data[4] = tel;
                    data[5] = al_flag;
                    data[6] = hd_flag;
                    data[7] = dia_flag;
                    data[8] = finalAl_risk;
                    data[9] = finalHd_risk;
                    data[10] = finalDia_risk;
                    data[11] = pat_pwd;
                    data[12] = gpno;
                    data[13] = insno;
                    data[14] = ref;

                    PutData putData = new PutData("http://192.168.1.100/MediApp/patientsignup.php", "POST", field, data);
                    if (putData.startPut()) {
                        if (putData.onComplete()) {
                            String result = putData.getResult();
                            if (result.equals("Sign Up Success")){
                                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), GPLogin.class);
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
                Toast.makeText(getApplicationContext(), "All fields are Required", Toast.LENGTH_SHORT).show();
            }
        });
    }
}