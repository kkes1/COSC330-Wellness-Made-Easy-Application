package com.example.projectone;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class TakePartsHead extends AppCompatActivity {

    private TextView eyes, ear, nose, lips, neck,  error;
    private TextView  eyesinput, earinput, noseinput, lipsinput, neckinput;
    private Button next, back, reset;

    private static final int[] correctAnswers = { // used for answer checking later. hard coded
            R.string.eyes,
            R.string.ear,
            R.string.nose,
            R.string.lips,
            R.string.neck,
    };

    private ArrayList<TextView> userAnswers = new ArrayList<>(); // used to store user's answers for checking later

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_takeparts_head);


        //views to drag on to
        eyes = findViewById(R.id.HeadBox);
        ear = findViewById(R.id.ShoulderBox);
        nose = findViewById(R.id.StomachBox);
        lips = findViewById(R.id.ChestBox);
        neck = findViewById(R.id.ArmBox);

        //views to drag
        eyesinput = findViewById(R.id.eyesInput);
        earinput = findViewById(R.id.earInput);
        noseinput = findViewById(R.id.noseInput);
        lipsinput = findViewById(R.id.lipsInput);
        neckinput = findViewById(R.id.neckInput);

        // SET WORD BANK TEXT VIEWS AS DRAGGABLE
        eyesinput.setOnTouchListener(new ChoiceTouchListener());
        earinput.setOnTouchListener(new ChoiceTouchListener());
        noseinput.setOnTouchListener(new ChoiceTouchListener());
        lipsinput.setOnTouchListener(new ChoiceTouchListener());
        neckinput.setOnTouchListener(new ChoiceTouchListener());

        // SET TEXTVIEW TARGETS AS BEING ABLE TO RECEIVE DRAGGED ANSWERS
        eyes.setOnDragListener(new ChoiceDragListener());
        ear.setOnDragListener(new ChoiceDragListener());
        nose.setOnDragListener(new ChoiceDragListener());
        lips.setOnDragListener(new ChoiceDragListener());
        neck.setOnDragListener(new ChoiceDragListener());

        // BUTTON SETUPS
        back = findViewById(R.id.back_button);
        next = findViewById(R.id.forward_button);;
        reset = findViewById(R.id.reset_button);
        next.setOnClickListener(nextExercise);
        back.setOnClickListener(sendBack);
        reset.setOnClickListener(resetBank);

        error = findViewById(R.id.errorBox); // set up error message
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
                    userAnswers.add(eyes);
                    userAnswers.add(ear);
                    userAnswers.add(nose);
                    userAnswers.add(lips);
                    userAnswers.add(neck);

                    boolean flag = false;

                    for (int i = 0; i < correctAnswers.length; i++){ // read through all correct answers
                        if (userAnswers.get(i).getText().toString().equals(getString(correctAnswers[i]))) // if the text in the answer box matches the correct answer
                            flag = true;
                        else
                            flag = false;
                    }
                    if (flag == true){ // if all the answers are correct
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
                    eyesinput.setVisibility(TextView.VISIBLE);
                    earinput.setVisibility(TextView.VISIBLE);
                    noseinput.setVisibility(TextView.VISIBLE);
                    lipsinput.setVisibility(TextView.VISIBLE);
                    neckinput.setVisibility(TextView.VISIBLE);

                    // SET DRAG-TARGET BOXES BACK TO EMPTY
                    eyes.setText(R.string.empty);
                    ear.setText(R.string.empty);
                    nose.setText(R.string.empty);
                    lips.setText(R.string.empty);
                    neck.setText(R.string.empty);

                    // RESET ANSWER TAGS TO NULL
                    eyes.setTag(null);
                    ear.setTag(null);
                    nose.setTag(null);
                    lips.setTag(null);
                    neck.setTag(null);

                    eyes.setTypeface(Typeface.DEFAULT);
                    ear.setTypeface(Typeface.DEFAULT);
                    nose.setTypeface(Typeface.DEFAULT);
                    lips.setTypeface(Typeface.DEFAULT);
                    neck.setTypeface(Typeface.DEFAULT);

                    eyes.setOnDragListener(new TakePartsHead.ChoiceDragListener());
                    ear.setOnDragListener(new TakePartsHead.ChoiceDragListener());
                    nose.setOnDragListener(new TakePartsHead.ChoiceDragListener());
                    lips.setOnDragListener(new TakePartsHead.ChoiceDragListener());
                    neck.setOnDragListener(new TakePartsHead.ChoiceDragListener());
                }
            };
}
