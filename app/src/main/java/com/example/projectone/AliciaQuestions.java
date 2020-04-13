package com.example.projectone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AliciaQuestions extends AppCompatActivity {

    private int tracker; // used to keep track of where the user is in the quiz
    private String a1, a2, a3, a4, a5; // will be used to store answers to pass to results screen
    private TextView questionText;
    private EditText answer;
    private Button forward, backward;

    private static final int[] textIDs = { // this will be used to load strings from strings.xml
            R.string.aq1,
            R.string.aq2,
            R.string.aq3,
            R.string.aq4,
            R.string.aq5
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alicia_questions);

        tracker = 0; // QUESTION TRACKER SET TO 0 BY DEFAULT

        /////// VIEW SETUP
        questionText = findViewById(R.id.instruction);
        answer = findViewById(R.id.answerBox);

        /////// BUTTON SETUP
        forward = findViewById(R.id.forward_button);
        backward = findViewById(R.id.backward_button);
        forward.setOnClickListener(loadNextText);
        backward.setOnClickListener(loadPreviousText);
    }

    private final View.OnClickListener loadNextText = // sends user to next question
            new View.OnClickListener() {
                @Override
                public void onClick(View btn) {

                    if (tracker == 0) {
                        a1 = answer.getText().toString();
                        if(a1.equals(""))
                            a1 = "No answer given.";
                        questionText.setTextSize(18); // change text size for formatting
                    } else if (tracker == 1) {
                        a2 = answer.getText().toString();
                        if(a2.equals(""))
                            a2 = "No answer given.";
                        questionText.setTextSize(24); // change text size for formatting
                    } else if (tracker == 2) {
                        a3 = answer.getText().toString();
                        if(a3.equals(""))
                            a3 = "No answer given.";
                    } else if(tracker == 3) {
                        a4 = answer.getText().toString();
                        if(a4.equals(""))
                            a4 = "No answer given.";
                        forward.setText(R.string.submit); // change button text to indicate loading quiz on next selection
                    } else if (tracker == 4) { // if hit next on second to last picture...
                        a5 = answer.getText().toString();
                        if(a5.equals(""))
                            a5 = "No answer given.";
                        Intent intent = new Intent(getApplicationContext(), AliciaAnswers.class); // submit answers by going to result screen
                        intent.putExtra("sent_answer1", a1);
                        intent.putExtra("sent_answer2", a2);
                        intent.putExtra("sent_answer3", a3);
                        intent.putExtra("sent_answer4", a4);
                        intent.putExtra("sent_answer5", a5);
                        startActivity(intent);
                    }
                    tracker++;
                    if (tracker < 5) { // de-increment tracker as we are going back
                        questionText.setText(textIDs[tracker]);
                        answer.setText(""); // clear answer box's text
                    }
                }
            };

    private final View.OnClickListener loadPreviousText = // returns to previous question
            new View.OnClickListener() {
                @Override
                public void onClick(View btn) {
                    if (tracker > 0) { // de-increment tracker as we are going back
                        tracker--;
                        questionText.setText(textIDs[tracker]);
                        answer.setText(""); // clear text in answer box
                    }
                    if(tracker == 1)
                        questionText.setTextSize(24); // change text size for formatting
                    if(tracker == 3) {
                        forward.setText(R.string.forward_button_text); // change button text to normal
                    }
                }
            };
}
