package com.example.androidlabs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Load user's name from SharedPreferences and put it in the EditText
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String userName = sharedPreferences.getString("userName", "");
        EditText editText = findViewById(R.id.editText);
        editText.setText(userName);

// "Next" button click listener
        Button nextButton = findViewById(R.id.buttonNext);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override


            public void onClick(View v) {
                // Get the current value of the EditText
                String name = editText.getText().toString();

                // Save the name to SharedPreferences
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("userName", name);
                editor.apply();

                // Launch NameActivity with startActivityForResult
                Intent intent = new Intent(MainActivity.this, NameActivity.class);
                startActivityForResult(intent, 1);
            }
            @Override
            protected void onPause() {
                super.onPause();

                // Save the current value inside the EditText to SharedPreferences
                String name = editText.getText().toString();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("userName", name);
                editor.apply();
            }
            @Override
            protected void onActivityResult(int requestCode, int resultCode, Intent data) {
                super.onActivityResult(requestCode, resultCode, data);

                if (requestCode == 1) {
                    if (resultCode == 0) {
                        // User wants to change their name
                        // Implement the

                    });

    }
}
