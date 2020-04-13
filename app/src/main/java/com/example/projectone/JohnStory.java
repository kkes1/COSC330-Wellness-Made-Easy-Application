package com.example.projectone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class JohnStory extends AppCompatActivity {

    private int tracker; // used to keep track of where the user is in the story
    private ImageView storyPicture;
    private TextView storyText;
    private Button forward, backward, back;

    private static final int[] fileNames = { // this will be used to load images
            R.drawable.johnone,
            R.drawable.johntwo,
            R.drawable.johnthree,
            R.drawable.johnfour,
            R.drawable.johnfour, // repeated
            R.drawable.johnfive,
            R.drawable.johnsix,
            R.drawable.johnsix, // repeated
            R.drawable.johnseven,
            R.drawable.johneight,
    };
    private static final int[] textIDs = { // this will be used to load strings from strings.xml
            R.string.johnone,
            R.string.johntwo,
            R.string.johnthree,
            R.string.johnfour,
            R.string.johnfive,
            R.string.johnsix,
            R.string.johnseven,
            R.string.johneight,
            R.string.johnnine,
            R.string.johnten,
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_john_story);

        tracker = 0; //STORY TRACKER SET TO 0 BY DEFAULT

        /////// VIEW SETUP
        storyText = findViewById(R.id.textbox);
        storyPicture = findViewById(R.id.imagebox);

        /////// BUTTON SETUP
        forward = findViewById(R.id.forward_button);
        backward = findViewById(R.id.backward_button);
        back = findViewById(R.id.back_button);
        forward.setOnClickListener(loadNextText);
        backward.setOnClickListener(loadPreviousText);
        back.setOnClickListener(sendBack);
    }

    private final View.OnClickListener loadNextText = // sends user to picture story menu
            new View.OnClickListener() {
                @Override
                public void onClick(View btn) {
                    if(tracker == 1)
                        storyText.setTextSize(18); // MAKE ALL TEXT FOLLOWING THIS SMALLER TO FIT TEXTVIEW
                    if (tracker == 8) // if hit next on second to last picture...
                        forward.setText(R.string.load_quiz); // change button text to indicate loading quiz on next selection
                    if (tracker == 9){ // if hit Next on final picture...
                        Intent intent = new Intent(getApplicationContext(), JohnQuestions.class); // load quiz
                        startActivity(intent);
                    }

                    tracker++;
                    if (tracker < 10) { // de-increment tracker as we are going back
                        storyPicture.setImageResource(fileNames[tracker]); // calls the correct picture from the list of picture file names, saving code reuse
                        storyText.setText(textIDs[tracker]);
                    }
                }
            };

    private final View.OnClickListener loadPreviousText = // sends user to picture story menu
            new View.OnClickListener() {
                @Override
                public void onClick(View btn) {
                    if (tracker > 0) { // de-increment tracker as we are going back
                        tracker--;
                        storyPicture.setImageResource(fileNames[tracker]); // calls the correct picture from the list of picture file names, saving code reuse
                        storyText.setText(textIDs[tracker]);
                    }
                    if(tracker == 1) // return reduced text to normal size
                        storyText.setTextSize(24);
                    if (tracker == 9) // if hit back on last picture...
                        forward.setText(R.string.forward_button_text); // change button text to normal
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
