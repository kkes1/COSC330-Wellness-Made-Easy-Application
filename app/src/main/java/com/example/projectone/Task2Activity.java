package com.example.projectone;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Task2Activity extends AppCompatActivity {

    private String activity = "Task2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task2);
        Log.i(activity, "onCreate ends");
    }

    public void openPuzzle(View v){
        Log.i(activity, "openPuzzle");
        Intent intent = null;
        if(v.getId() == R.id.to_word_search_button){
            intent = new Intent(this, WordSearchActivity.class);
        } else if(v.getId() == R.id.to_crossword_button){
            intent = new Intent(this, MainMenu.class);
        }
        if(intent != null) {
            Log.i(activity, "launching intent");
            startActivity(intent);
        } else {
            Log.i(activity, "Sorry, nothing prepared for that");
        }
    }

}
