package com.example.androidlabs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class NameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);
    }
    // Get the user's name passed from the previous activity
    String name = getIntent().getStringExtra("userName");

    // Update the TextView to welcome the user
    TextView welcomeTextView = findViewById(R.id.welcomeTextView);
welcomeTextView.setText("Welcome " + name + "!");

    // "Don't call me that" button click listener
    Button dontCallMeThatButton = findViewById(R.id.changeNameButton);
dontCallMeThatButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // Set the result to 0 and return to the previous activity
            setResult(0);
            finish();
        }
    });

    // "Thank You" button click listener
    Button thankYouButton = findViewById(R.id.thankYouButton);
thankYouButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // Set the result to 1 and return to the previous activity
            setResult(1);
            finish();
        }
    });

}