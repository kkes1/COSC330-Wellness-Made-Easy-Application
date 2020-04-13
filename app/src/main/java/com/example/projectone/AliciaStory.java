package com.example.projectone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AliciaStory extends AppCompatActivity {

    private int tracker; // used to keep track of where the user is in the story
    private ImageView storyPicture;
    private TextView storyText;
    private Button forward, backward, back;

    private static final int[] fileNames = { // this will be used to load images
            R.drawable.aliciaone,
            R.drawable.aliciaone, // repeated
            R.drawable.aliciatwo,
            R.drawable.aliciathree,
            R.drawable.aliciafour,
            R.drawable.aliciafive,
            R.drawable.aliciasix,
    };
    private static final int[] textIDs = { // this will be used to load strings from strings.xml
            R.string.aliciaone,
            R.string.aliciatwo,
            R.string.aliciathree,
            R.string.aliciafour,
            R.string.aliciafive,
            R.string.aliciasix,
            R.string.aliciaseven,
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alicia_story);

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
                    tracker++;
                    if (tracker < 7) { // de-increment tracker as we are going back
                        storyPicture.setImageResource(fileNames[tracker]); // calls the correct picture from the list of picture file names, saving code reuse
                        storyText.setText(textIDs[tracker]);

                    }
                    if (tracker == 6) // if hit next on second to last picture...
                        forward.setText(R.string.load_quiz); // change button text to indicate loading quiz on next selection
                    if (tracker == 7){ // if hit Next on final picture...
                        Intent intent = new Intent(getApplicationContext(), AliciaQuestions.class); // load quiz
                        startActivity(intent);
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
                    if (tracker == 5) // if hit back on last picture...
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
