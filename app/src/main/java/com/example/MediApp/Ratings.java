package com.example.MediApp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

public class Ratings extends AppCompatActivity {

    TextView rating, rateCount;
    EditText review;
    RatingBar ratingBar;
    String temp;
    Button home, submit;
    float rateValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ratings);


        rateCount = findViewById(R.id.rateCount);
        ratingBar = findViewById(R.id.ratingBar);
        rating = findViewById(R.id.rating);
        review = findViewById(R.id.review);
        submit = findViewById(R.id.submitButton);


        home = findViewById(R.id.returnIntro);

        home.setOnClickListener(view -> {
            Intent home = new Intent(getApplicationContext(), Intro.class);
            startActivity(home);
            finish();
        });

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                rateValue = ratingBar.getRating();

                if (rateValue <=1 && rateValue > 0)
                    rateCount.setText("Bad " + rateValue + " / 5.0");
                else if (rateValue <=2 && rateValue > 1)
                    rateCount.setText("OK " + rateValue + " / 5.0");
                else if (rateValue <=3 && rateValue > 2)
                    rateCount.setText("Good " + rateValue + " / 5.0");
                else if (rateValue <=4 && rateValue > 3)
                    rateCount.setText("Very Good " + rateValue + " / 5.0");
                else if (rateValue <=5 && rateValue > 4)
                    rateCount.setText("Excellent" + rateValue + " / 5.0");

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                temp = rateCount.getText().toString();
                rating.setText("Your Review: \n" + temp + "\n" + review.getText());
                review.setText("");
                ratingBar.setRating(0);
                rateCount.setText("");
            }
        });
    }
}