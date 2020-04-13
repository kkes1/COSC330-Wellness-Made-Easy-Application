package com.example.projectone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LarisaAnswers extends AppCompatActivity {

    private TextView answer1, answer2, answer3, answer4, answer5; // TextViews that hold the user's answers
    private Button reread, retake, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_larisa_answers);

        /////// VIEW SETUP
        answer1 = findViewById(R.id.answerbox1);
        answer2 = findViewById(R.id.answerbox2);
        answer3 = findViewById(R.id.answerbox3);
        answer4 = findViewById(R.id.answerbox4);
        answer5 = findViewById(R.id.answerbox5);

        Intent intent = getIntent();
        answer1.setText(intent.getStringExtra("sent_answer1"));
        answer2.setText(intent.getStringExtra("sent_answer2"));
        answer3.setText(intent.getStringExtra("sent_answer3"));
        answer4.setText(intent.getStringExtra("sent_answer4"));
        answer5.setText(intent.getStringExtra("sent_answer5"));

        /////// BUTTON SETUP
        reread = findViewById(R.id.storybutton);
        retake = findViewById(R.id.retakebutton);
        back = findViewById(R.id.back_button);
        reread.setOnClickListener(rereadStory);
        retake.setOnClickListener(retakeQuiz);
        back.setOnClickListener(sendBack);

    }

    private final View.OnClickListener rereadStory = // sends user to picture story
            new View.OnClickListener() {
                @Override
                public void onClick(View btn) {
                    Intent intent = new Intent(getApplicationContext(), LarisaStory.class);
                    startActivity(intent);
                }
            };

    private final View.OnClickListener retakeQuiz = // sends user back to quiz
            new View.OnClickListener() {
                @Override
                public void onClick(View btn) {
                    Intent intent = new Intent(getApplicationContext(), LarisaQuestions.class);
                    startActivity(intent);
                }
            };

    private final View.OnClickListener sendBack = // sends user to picture story menu
            new View.OnClickListener() {
                @Override
                public void onClick(View btn) {
                    Intent intent = new Intent(getApplicationContext(), PictureStoryScreen.class);
                    startActivity(intent);
                }
            };
}
