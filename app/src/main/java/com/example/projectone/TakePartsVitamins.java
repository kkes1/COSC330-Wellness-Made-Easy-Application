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

public class TakePartsVitamins extends AppCompatActivity {

    private TextView head, shoulder, chest, stomach, arm, hand, error;
    private TextView headinput, shoulderinput, chestinput, stomachinput, arminput, handinput;
    private Button next, back, reset;

    private static final String[] correctAnswers = { // used for answer checking later. hard coded
            "Vitamin B2",
            "Vitamin K",
            "Vitamin B3",
            "Vitamin E",
            "Vitamin D",
            "Vitamin C"
    };

    private ArrayList<TextView> userAnswers = new ArrayList<>(); // used to store user's answers for checking later

    //private MediaPlayer player;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_takeparts_vitamins);


        //views to drag on to
        head = findViewById(R.id.HeadBox);
        shoulder = findViewById(R.id.ShoulderBox);
        chest = findViewById(R.id.ChestBox);
        stomach = findViewById(R.id.StomachBox);
        arm = findViewById(R.id.ArmBox);
        hand = findViewById(R.id.HandBox);

        //views to drag
        headinput = findViewById(R.id.headInput);
        shoulderinput = findViewById(R.id.shoulderInput);
        chestinput = findViewById(R.id.chestInput);
        stomachinput = findViewById(R.id.stomachInput);
        arminput = findViewById(R.id.armInput);
        handinput = findViewById(R.id.handInput);

        // SET WORD BANK TEXT VIEWS AS DRAGGABLE
        headinput.setOnTouchListener(new ChoiceTouchListener());
        shoulderinput.setOnTouchListener(new ChoiceTouchListener());
        chestinput.setOnTouchListener(new ChoiceTouchListener());
        stomachinput.setOnTouchListener(new ChoiceTouchListener());
        arminput.setOnTouchListener(new ChoiceTouchListener());
        handinput.setOnTouchListener(new ChoiceTouchListener());

        // SET TEXTVIEW TARGETS AS BEING ABLE TO RECEIVE DRAGGED ANSWERS
        head.setOnDragListener(new ChoiceDragListener());
        shoulder.setOnDragListener(new ChoiceDragListener());
        chest.setOnDragListener(new ChoiceDragListener());
        stomach.setOnDragListener(new ChoiceDragListener());
        arm.setOnDragListener(new ChoiceDragListener());
        hand.setOnDragListener(new ChoiceDragListener());

        // BUTTON SETUPS
        back = findViewById(R.id.back_button);
        next = findViewById(R.id.forward_button);;
        reset = findViewById(R.id.reset_button);
        next.setOnClickListener(nextExercise);
        back.setOnClickListener(sendBack);
        reset.setOnClickListener(resetBank);

        error = findViewById(R.id.errorBox); // set up error message
        //player = MediaPlayer.create(getApplicationContext(), R.raw.ohyeah);
    }


    private final class ChoiceTouchListener implements View.OnTouchListener {

        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.startDrag(data, shadowBuilder, view, 0);
                return true;
            } else {
                return false;
            }
        }
    }


    private class ChoiceDragListener implements View.OnDragListener {

        @Override
        public boolean onDrag(View v, DragEvent event) {
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    //no action necessary
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    //no action necessary
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    //no action necessary
                    break;
                case DragEvent.ACTION_DROP:
                    View view = (View) event.getLocalState();
                    TextView dropTarget = (TextView) v; // view being dragged onto
                    TextView dropped = (TextView) view; // view being dragged and dropped
                    view.setVisibility(View.INVISIBLE); // stop displaying the view where it was before it was dragged
                    dropTarget.setText(dropped.getText().toString()); // update the text in the target view to reflect the data being dropped
                    dropTarget.setTypeface(Typeface.DEFAULT_BOLD); // make it bold to highlight the fact that an item has been dropped
                    Object tag = dropTarget.getTag(); // if an item has already been dropped here, there will be a tag
                    if (tag != null) { // if there is already an item here, set it back visible in its original place
                        int existingID = (int)tag; // the tag is the view id already dropped here
                        findViewById(existingID).setVisibility(View.VISIBLE); // set the original view visible again
                    }
                    dropTarget.setTag(dropped.getId()); //set the tag in the target view being dropped on - to the ID of the view being dropped
                    //dropTarget.setOnDragListener(null);//remove setOnDragListener by setting OnDragListener to null, so that no further drag & dropping on this TextView can be done
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    //no action necessary
                    break;
                default:
                    break;
            }
            return true;
        }
    }


    private final View.OnClickListener nextExercise = // sends user to next take parts exercise if answers are correct
            new View.OnClickListener() {
                @Override
                public void onClick(View btn) {
                    // ADD ALL ANSWERS TEXTVIEWS TO ARRAYLIST
                    userAnswers.add(head);
                    userAnswers.add(shoulder);
                    userAnswers.add(chest);
                    userAnswers.add(stomach);
                    userAnswers.add(arm);
                    userAnswers.add(hand);

                    boolean flag = false;

                    for (int i = 0; i < correctAnswers.length; i++){ // read through all correct answers
                        if (userAnswers.get(i).getText().toString().equals(correctAnswers[i])) // if the text in the answer box matches the correct answer
                            flag = true;
                        else
                            flag = false;
                    }
                    if (flag == true){ // if all the answers are correct
                        //player.start(); // no need to call prepare(); create() does that for you
                        Intent intent = new Intent(getApplicationContext(), TakePartsResults.class); // move on to the next exercise
                        startActivity(intent);
                    }
                    else // display error message
                        error.setText(R.string.incorrect);
                }
            };

    private final View.OnClickListener sendBack = // sends user to take parts menu
            new View.OnClickListener() {
                @Override
                public void onClick(View btn) {
                    Intent intent = new Intent(getApplicationContext(), TakePartsScreen.class);
                    startActivity(intent);
                }
            };

    private final View.OnClickListener resetBank = // resets word bank and answer boxes
            new View.OnClickListener() {
                @Override
                public void onClick(View btn) {
                    // RESET STORED ANSWERS IN NEXT BUTTON LISTENER ARRAYLIST
                    userAnswers.clear();

                    //CLEAR ERROR MESSAGE IF ANY
                    error.setText(R.string.empty);

                    // RESET WORD BANK BOXES TO ORIGINAL POSITION
                    headinput.setVisibility(TextView.VISIBLE);
                    shoulderinput.setVisibility(TextView.VISIBLE);
                    chestinput.setVisibility(TextView.VISIBLE);
                    stomachinput.setVisibility(TextView.VISIBLE);
                    arminput.setVisibility(TextView.VISIBLE);
                    handinput.setVisibility(TextView.VISIBLE);

                    // SET DRAG-TARGET BOXES BACK TO EMPTY
                    head.setText(R.string.empty);
                    shoulder.setText(R.string.empty);
                    chest.setText(R.string.empty);
                    stomach.setText(R.string.empty);
                    arm.setText(R.string.empty);
                    hand.setText(R.string.empty);

                    // RESET ANSWER TAGS TO NULL
                    head.setTag(null);
                    shoulder.setTag(null);
                    chest.setTag(null);
                    stomach.setTag(null);
                    arm.setTag(null);
                    hand.setTag(null);

                    head.setTypeface(Typeface.DEFAULT);
                    shoulder.setTypeface(Typeface.DEFAULT);
                    chest.setTypeface(Typeface.DEFAULT);
                    stomach.setTypeface(Typeface.DEFAULT);
                    arm.setTypeface(Typeface.DEFAULT);
                    hand.setTypeface(Typeface.DEFAULT);

                    arm.setOnDragListener(new TakePartsVitamins.ChoiceDragListener());
                    shoulder.setOnDragListener(new TakePartsVitamins.ChoiceDragListener());
                    chest.setOnDragListener(new TakePartsVitamins.ChoiceDragListener());
                    stomach.setOnDragListener(new TakePartsVitamins.ChoiceDragListener());
                    arm.setOnDragListener(new TakePartsVitamins.ChoiceDragListener());
                    hand.setOnDragListener(new TakePartsVitamins.ChoiceDragListener());
                }
            };
}
