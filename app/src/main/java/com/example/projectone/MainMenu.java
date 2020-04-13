package com.example.projectone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainMenu extends AppCompatActivity {

    private Button glossary, crossword, parts, pictures, readingspeaking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        ///////////////////////////////////////// BUTTON SETUP
        glossary = findViewById(R.id.button);
        crossword = findViewById(R.id.button2);
        parts = findViewById(R.id.button3);
        pictures = findViewById(R.id.button4);
        readingspeaking = findViewById(R.id.button5);
        glossary.setOnClickListener(sendGlossary);
        crossword.setOnClickListener(sendPuzzle);
        parts.setOnClickListener(sendTakeParts);
        pictures.setOnClickListener(sendPicture);
        readingspeaking.setOnClickListener(sendReadSpeak);
    }


    private final View.OnClickListener sendGlossary = // sends user to glossary
            new View.OnClickListener() {
                @Override
                public void onClick(View btn) {
                    Intent intent = new Intent(getApplicationContext(), Task1Activity.class);
                    startActivity(intent);
                }
            };

    private final View.OnClickListener sendPuzzle = // sends user to crossword menu
            new View.OnClickListener() {
                @Override
                public void onClick(View btn) {
                    Intent intent = new Intent(getApplicationContext(), Task2Activity.class);
                    startActivity(intent);
                }
            };

    private final View.OnClickListener sendTakeParts = // sends user to take parts menu
            new View.OnClickListener() {
                @Override
                public void onClick(View btn) {
                    Intent intent = new Intent(getApplicationContext(), TakePartsScreen.class);
                    startActivity(intent);
                }
            };

    private final View.OnClickListener sendPicture = // sends user to picture story menu
            new View.OnClickListener() {
                @Override
                public void onClick(View btn) {
                    Intent intent = new Intent(getApplicationContext(), PictureStoryScreen.class);
                    startActivity(intent);
                }
            };

    private final View.OnClickListener sendReadSpeak = // sends user to reading/speaking exercises menu
            new View.OnClickListener() {
                @Override
                public void onClick(View btn) {
                    Intent intent = new Intent(getApplicationContext(), Task5Activity.class);
                    startActivity(intent);
                }
            };

}
