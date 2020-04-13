package com.example.projectone;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

public class Task1Activity extends AppCompatActivity {
    private TextView termView, definitionView, chapterView;
    private GlossaryClass glossary;
    private String activity;
    private ImageView picView;
    private TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = "Task1";
        Log.i(activity, "onCreate starts");
        setContentView(R.layout.activity_task1);
        termView = findViewById(R.id.word_text);
        definitionView = findViewById(R.id.definition_text);
        chapterView = findViewById(R.id.chapter_text);
        picView = findViewById(R.id.imageView);
        InputStream in = null;
        try {
            Log.i(activity, "trying inputstream/glossary");
            in = getAssets().open("term_glossary.xml");
            glossary = new GlossaryClass(in);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                tts.setLanguage(Locale.US);
            }
        });
        pickWord(findViewById(R.id.random_word_button));
        Log.i(activity, "onCreateEnds");
    }

    public void showDefinition(View v){
        Log.i(activity, "showDefinition");
        definitionView.setVisibility(View.VISIBLE);
    }

    public void pickWord(View v){
        Log.i(activity, "pickWord");
        GlossaryTerm word = glossary.randomTerm();
        if(v.getId() == R.id.previous_word_button) {
            word = glossary.previousTerm();
        } else if(v.getId() == R.id.next_word_button){
            word = glossary.nextTerm();
        }
        termView.setText(getString(R.string.term_placeholder, word.getTerm()));
        chapterView.setText(getString(R.string.chapter_placeholder, word.getChapter()));
        definitionView.setVisibility(View.INVISIBLE);
        definitionView.setText(getString(R.string.definition_placeholder, word.getDefinition()));
        picView.setImageResource(getResources().getIdentifier(word.getDrawableString(), "drawable", "com.example.projectone"));
    }

    public void addToTTS(View v){
        if(v.getId() == R.id.playWordButton){
            tts.speak(termView.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
        } else if(v.getId() == R.id.playDefinitionButton && definitionView.getVisibility() == View.VISIBLE){
            tts.speak(definitionView.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
        }
    }
}
