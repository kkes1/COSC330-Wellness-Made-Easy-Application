package com.example.projectone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class TakePartsScreen extends AppCompatActivity {

    private Button problem1, problem2, problem3, problem4, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_takeparts_selectionscreen);

        /////////////// BUTTON SETUP
        problem1 = findViewById(R.id.button);
        problem2= findViewById(R.id.button2);
        problem3 = findViewById(R.id.button3);
        problem4 = findViewById(R.id.button4);
        back = findViewById(R.id.back_button);
        problem1.setOnClickListener(loadFirst);
        problem2.setOnClickListener(loadSecond);
        problem3.setOnClickListener(loadThird);
        problem4.setOnClickListener(loadFourth);
        back.setOnClickListener(sendBack);
    }

    private final View.OnClickListener loadFirst = // sends user to body exercise
            new View.OnClickListener() {
                @Override
                public void onClick(View btn) {
                    Intent intent = new Intent(getApplicationContext(), TakePartsBody.class);
                    startActivity(intent);
                }
            };

    private final View.OnClickListener loadSecond =
            new View.OnClickListener() {
                @Override
                public void onClick(View btn) {
                    Intent intent = new Intent(getApplicationContext(), MainMenu.class);
                    startActivity(intent);
                }
            };

    private final View.OnClickListener loadThird =
            new View.OnClickListener() {
                @Override
                public void onClick(View btn) {
                    Intent intent = new Intent(getApplicationContext(), MainMenu.class);
                    startActivity(intent);
                }
            };

    private final View.OnClickListener loadFourth =
            new View.OnClickListener() {
                @Override
                public void onClick(View btn) {
                    Intent intent = new Intent(getApplicationContext(), TakePartsVitamins.class);
                    startActivity(intent);
                }
            };

    private final View.OnClickListener sendBack =
            new View.OnClickListener() {
                @Override
                public void onClick(View btn) {
                    Intent intent = new Intent(getApplicationContext(), MainMenu.class);
                    startActivity(intent);
                }
            };

}
