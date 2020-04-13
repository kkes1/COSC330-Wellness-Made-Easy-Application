package com.example.projectone;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class TakePartsResults extends AppCompatActivity {

    private Button back;
    private MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_takeparts_results);

        player = MediaPlayer.create(this, R.raw.bye_bye);

        // BUTTON SETUPS
        back = findViewById(R.id.back_button);
        back.setOnClickListener(sendBack);

    }

    private void playPlayer(){
        player.start();
    }

    private final View.OnClickListener sendBack = // sends user to take parts menu
            new View.OnClickListener() {
                @Override
                public void onClick(View btn) {
                    //playPlayer(); // WILL NOT WORK+
                    Intent intent = new Intent(getApplicationContext(), TakePartsScreen.class);
                    startActivity(intent);
                }
            };
}
