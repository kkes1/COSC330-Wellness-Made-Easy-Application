package com.example.projectone;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Task5Activity extends AppCompatActivity {
    private String activity = "Task5";
    private int ch1Reading, ch1ReadingLen;
    private int ch1Speaking, ch1SpeakingLen;
    private int ch2Reading1, ch2Reading1Len;
    private int ch2Reading2, ch2Reading2Len;
    private int ch2Speaking, ch2SpeakingLen;
    private int ch2BodyParts, ch2BodyPartsLen;
    private int ch3Speaking, ch3SpeakingLen;
    private int ch4Reading, ch4ReadingLen;
    private int ch4Reading2, ch4Reading2Len;
    private int ch4Reading3, ch4Reading3Len;
    private int ch4Speaking, ch4SpeakingLen;
    private int pageNum = 0, len;
    private int resourceString;
    private TextView title, text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task5);
        ch1Reading = R.string.ch1ReadingLine1;
        ch1ReadingLen = 3;

        ch1Speaking = R.string.ch1SpeakingLine1;
        ch1SpeakingLen = 10;

        ch2Reading1 = R.string.ch2ReadingLine1;
        ch2Reading1Len = 3;

        ch2Reading2 = R.string.ch2Reading2Line1;
        ch2Reading2Len = 9;

        ch2Speaking = R.string.ch2SpeakingLine1;
        ch2SpeakingLen = 9;

        ch2BodyParts = R.string.ch2BodyParts1;
        ch2BodyPartsLen = 11;

        ch3Speaking = R.string.ch3SpeakingLine1;
        ch3SpeakingLen = 10;

        ch4Reading = R.string.ch4ReadingLine1;
        ch4ReadingLen = 2;

        ch4Reading2 = R.string.ch4Reading2Line1;
        ch4Reading2Len = 1;

        ch4Reading3 = R.string.ch4Reading3Line1;
        ch4Reading3Len = 3;

        ch4Speaking = R.string.ch4SpeakingLine1;
        ch4SpeakingLen = 9;

        title = findViewById(R.id.title);
        text = findViewById(R.id.text);

        Log.i(activity, "onCreate ends");
    }

    public void doActivity(View v){
        pageNum = 0;
        switch(v.getId()){
            case R.id.ch1Read: resourceString = ch1Reading; len = ch1ReadingLen;
            case R.id.ch1Speak: resourceString = ch1Speaking; len = ch1SpeakingLen;
            case R.id.ch2Read1: resourceString = ch2Reading1; len = ch2Reading1Len;
            case R.id.ch2Read2: resourceString = ch2Reading2; len = ch2Reading2Len;
            case R.id.ch2Speak1: resourceString = ch2Speaking; len = ch2SpeakingLen;
            case R.id.ch2Speak2: resourceString = ch2BodyParts; len = ch2BodyPartsLen;
            case R.id.ch3Speak: resourceString = ch3Speaking; len = ch3SpeakingLen;
            case R.id.ch4Read1: resourceString = ch4Reading; len = ch4ReadingLen;
            case R.id.ch4Read2: resourceString = ch4Reading2; len = ch4Reading2Len;
            case R.id.ch4Read3: resourceString = ch4Reading3; len = ch4Reading3Len;
            case R.id.ch4Speak: resourceString = ch4Speaking; len = ch4SpeakingLen;
        }
        setContentView(R.layout.activity_task5_activity);
        title.setText(resourceString-1);
        text.setText(resourceString);
    }

    public void turnPage(View v){
        if(v.getId() == R.id.nextPage){
            if(pageNum < len-1){
                pageNum++;
            }
        } else {
            if(pageNum > 0){
                pageNum--;
            }
        }
    }

}
