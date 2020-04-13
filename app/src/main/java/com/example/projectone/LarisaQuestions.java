package com.example.projectone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LarisaQuestions extends AppCompatActivity {

    private int tracker; // used to keep track of where the user is in the quiz
    private String a1, a2, a3, a4, a5; // will be used to store answers to pass to results screen
    private TextView questionText, incorrectMessage;
    private RadioButton radio1, radio2, radio3, radio4, radio5;
    private Button forward, backward, back;

    private final int arrSize = 6; // the size of the following arrays will always be 6.

    private static final int[] fruitIDs = { // this will be used to load fruit strings from strings.xml
            R.string.fruit,
            R.string.apples,
            R.string.bananas,
            R.string.grapes,
            R.string.oranges,
            R.string.strawberries,
    };


    private static final int[] veggieIDs = { // this will be used to load vegetable strings from strings.xml
            R.string.vegetable,
            R.string.broccoli,
            R.string.carrots,
            R.string.peas,
            R.string.spinach,
            R.string.string_beans,
    };


    private static final int[] grainIDs = { // this will be used to load grain strings from strings.xml
            R.string.grain,
            R.string.brown_rice,
            R.string.corn_bread,
            R.string.pasta,
            R.string.tortillas,
            R.string.wheat_bread,
    };


    private static final int[] proteinIDs = { // this will be used to load protein strings from strings.xml
            R.string.protein,
            R.string.beef,
            R.string.black_beans,
            R.string.chicken,
            R.string.nuts,
            R.string.fish,
    };


    private static final int[] dairyIDs = { // this will be used to load dairy strings from strings.xml
                                            // not that there are only three available options, as only three are given in manual.
            R.string.dairy,
            R.string.cheese,
            R.string.milk,
            R.string.yogurt,
            R.string.empty, // not a valid choice
            R.string.empty, // not a valid choice
    };

    private static final int[][] wordSets = {fruitIDs, veggieIDs, grainIDs, proteinIDs, dairyIDs};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_larisa_questions);

        tracker = 0; //QUESTION TRACKER SET TO 0 BY DEFAULT

        /////// VIEW SETUP
        questionText = findViewById(R.id.questionPrompt);
        incorrectMessage = findViewById(R.id.incorrectBox);

        /////// BUTTON SETUP
        forward = findViewById(R.id.forward_button);
        backward = findViewById(R.id.backward_button);
        back = findViewById(R.id.back_button);
        forward.setOnClickListener(loadNextText);
        backward.setOnClickListener(loadPreviousText);
        back.setOnClickListener(sendBack);

        /////// RADIO BUTTON SETUP
        radio1 = findViewById(R.id.radioButton1);
        radio2 = findViewById(R.id.radioButton2);
        radio3 = findViewById(R.id.radioButton3);
        radio4 = findViewById(R.id.radioButton4);
        radio5 = findViewById(R.id.radioButton5);

        radio1.setText(fruitIDs[1]);
        radio2.setText(fruitIDs[2]);
        radio3.setText(fruitIDs[3]);
        radio4.setText(fruitIDs[4]);
        radio5.setText(fruitIDs[5]);


    }

    private void setTexts(int[] arr){ // this function is called to change the food group text and radio button's text.
                                        // this is done by passing one of the 5 int id arrays in above section.
        questionText.setText(arr[0]);
        radio1.setText(arr[1]);
        radio2.setText(arr[2]);
        radio3.setText(arr[3]);
        radio4.setText(arr[4]);
        radio5.setText(arr[5]);
    }

    private String getSelection(){ // takes the chosen text from the selected radio button and returns to loadNextText
        if(radio1.isChecked()){
            return radio1.getText().toString();
        }
        else if (radio2.isChecked()){
            return radio2.getText().toString();
        }
        else if (radio3.isChecked()){
            return radio3.getText().toString();
        }
        else if (radio4.isChecked()){
            return radio4.getText().toString();
        }
        else if (radio5.isChecked()){
            return radio5.getText().toString();
        }
        else
            return "No selection";
    }
    // a1 = answer.getText().toString(); USE WHEN PULLING TEXT FROM RADIO BUTTONS
    private final View.OnClickListener loadNextText = // sends user to next question
            new View.OnClickListener() {
                @Override
                public void onClick(View btn) {
                    //setTexts(wordSets[tracker]); // this pulls code reuse out of if-else statements
                    if (tracker == 0) {
                        a1 = getSelection();
                        setTexts(veggieIDs);
                    } else if (tracker == 1) {
                        a2 = getSelection();
                        setTexts(grainIDs);
                    } else if(tracker == 2) {
                        a3 = getSelection();
                        setTexts(proteinIDs);
                    } else if(tracker == 3) {
                        a4 = getSelection();
                        setTexts(dairyIDs);
                        // since there are only three choices for dairy, disable clicking buttons 4 and 5
                        radio4.setClickable(false);
                        radio5.setClickable(false);
                        forward.setText(R.string.submit); // change button text to indicate loading quiz on next selection
                    }else if (tracker == 4) { // if hit next on second to last picture...
                        a5 = getSelection();
                        Intent intent = new Intent(getApplicationContext(), LarisaAnswers.class); // submit answers by going to result screen
                        intent.putExtra("sent_answer1", a1);
                        intent.putExtra("sent_answer2", a2);
                        intent.putExtra("sent_answer3", a3);
                        intent.putExtra("sent_answer4", a4);
                        intent.putExtra("sent_answer5", a5);
                        startActivity(intent);
                    }
                    tracker++;
                }
            };

    private final View.OnClickListener loadPreviousText = // returns to previous question
            new View.OnClickListener() {
                @Override
                public void onClick(View btn) {
                    if (tracker > 0) { // de-increment tracker as we are going back
                        tracker--;
                    }
                    if (tracker == 0) {
                        setTexts(fruitIDs);
                    } else if (tracker == 1) {
                        setTexts(veggieIDs);
                    } else if(tracker == 2) {
                        setTexts(grainIDs);
                    } else if(tracker == 3) {
                        setTexts(proteinIDs);
                        forward.setText(R.string.forward_button_text); // change button text to normal
                        //re-enable clicking for buttons 4 and 5 now that we are out of dairy selection
                        radio4.setClickable(true);
                        radio5.setClickable(true);
                    }else if (tracker == 4) { // if hit next on second to last picture...
                        setTexts(dairyIDs);
                    }
                }
            };

    private final View.OnClickListener sendBack = // sends user to larisa's story
            new View.OnClickListener() {
                @Override
                public void onClick(View btn) {
                    Intent intent = new Intent(getApplicationContext(), LarisaStory.class);
                    startActivity(intent);
                }
            };
}
