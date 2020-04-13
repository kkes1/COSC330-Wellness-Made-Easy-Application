package com.example.projectone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LarisaStory extends AppCompatActivity {

    private int tracker; // used to keep track of where the user is in the story
    private ImageView storyPicture;
    private TextView storyText;
    private Button forward, backward, back;

    private static final int[] fileNames = { // this will be used to load images
            R.drawable.larisaone,
            R.drawable.larisatwo
    };

    private static final int[] textIDs = { // this will be used to load strings from strings.xml
            R.string.larisaone,
            R.string.larisatwo
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_larisa_story);

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
                    if (tracker < 2) { // de-increment tracker as we are going back
                        storyPicture.setImageResource(fileNames[tracker]); // calls the correct picture from the list of picture file names, saving code reuse
                        storyText.setText(textIDs[tracker]);
                    }
                    if (tracker == 1) { // if hit next on second to last picture...
                        forward.setText(R.string.load_quiz); // change button text to indicate loading quiz on next selection
                        storyText.setTextSize(22); // change text size for formatting
                    }
                    if (tracker == 2){ // if hit Next on final picture...
                        Intent intent = new Intent(getApplicationContext(), LarisaQuestions.class); // load quiz
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
                    if (tracker == 0) { // if hit back on last picture...
                        forward.setText(R.string.forward_button_text); // change button text to normal
                        storyText.setTextSize(20); // change text size for formatting
                    }
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
