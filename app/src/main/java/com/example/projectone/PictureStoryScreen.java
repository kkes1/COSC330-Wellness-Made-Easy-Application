package com.example.projectone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class PictureStoryScreen extends AppCompatActivity {

    private Button maria, john, alicia, larisa, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picturestory_selectionscreen);

        /////////////// BUTTON SETUP
        maria = findViewById(R.id.button);
        john = findViewById(R.id.button2);
        alicia = findViewById(R.id.button3);
        larisa = findViewById(R.id.button4);
        back = findViewById(R.id.back_button);
        maria.setOnClickListener(loadMaria);
        john.setOnClickListener(loadJohn);
        alicia.setOnClickListener(loadAlicia);
        larisa.setOnClickListener(loadLarisa);
        back.setOnClickListener(sendBack);
    }

    private final View.OnClickListener loadMaria = // sends user to maria's picture story
            new View.OnClickListener() {
                @Override
                public void onClick(View btn) {
                    Intent intent = new Intent(getApplicationContext(), MariaStory.class);
                    startActivity(intent);
                }
            };

    private final View.OnClickListener loadJohn = // sends user to john's picture story
            new View.OnClickListener() {
                @Override
                public void onClick(View btn) {
                    Intent intent = new Intent(getApplicationContext(), JohnStory.class);
                    startActivity(intent);
                }
            };

    private final View.OnClickListener loadAlicia = // sends user to alicia's picture story
            new View.OnClickListener() {
                @Override
                public void onClick(View btn) {
                    Intent intent = new Intent(getApplicationContext(), AliciaStory.class);
                    startActivity(intent);
                }
            };

    private final View.OnClickListener loadLarisa = // sends user to larisa's picture story
            new View.OnClickListener() {
                @Override
                public void onClick(View btn) {
                    Intent intent = new Intent(getApplicationContext(), LarisaStory.class);
                    startActivity(intent);
                }
            };

    private final View.OnClickListener sendBack = // sends user to picture story menu
            new View.OnClickListener() {
                @Override
                public void onClick(View btn) {
                    Intent intent = new Intent(getApplicationContext(), MainMenu.class);
                    startActivity(intent);
                }
            };

}
