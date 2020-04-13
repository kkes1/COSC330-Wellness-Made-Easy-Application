package com.example.projectone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class JohnAnswers extends AppCompatActivity {

    private TextView answer1, answer2, answer3, answer4; // TextViews that hold the user's answers
    private TextView feedbackOne, feedbackTwo, feedbackThree; // TextViews that display if a user's answer is right/wrong
    private Button reread, retake, back;

    private static final String[] qOneAnswers = { // holds correct answers to question 1
            "vegetables",
            "fruits",
            "exercise",
            "exercises",
            "water",
            "vegetales",
            "frutas",
            "ejercicio",
            "ejercicios",
            "agua"
    };

    private static final String[] qTwoAnswers = { // holds correct answers to question 2
            "schedule",
            "appointment",
            "horario",
            "cita"
    };

    private static final String[] qThreeAnswers = { // holds correct answers to question 3
            "allergies",
            "allergy",
            "medicines",
            "medicine",
            "alergias",
            "alergia",
            "medicamentos",
            "medicamento"
    };

    private static final int[] feedbackIDs = {
            R.id.feedback1,
            R.id.feedback2,
            R.id.feedback3,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_john_answers);

        /////// VIEW SETUP
        answer1 = findViewById(R.id.answerbox1);
        answer2 = findViewById(R.id.answerbox2);
        answer3 = findViewById(R.id.answerbox3);
        answer4 = findViewById(R.id.answerbox4);

        Intent intent = getIntent();
        answer1.setText(intent.getStringExtra("sent_answer1"));
        answer2.setText(intent.getStringExtra("sent_answer2"));
        answer3.setText(intent.getStringExtra("sent_answer3"));
        answer4.setText(intent.getStringExtra("sent_answer4"));

        feedbackOne = findViewById(R.id.feedback1);
        feedbackTwo = findViewById(R.id.feedback2);
        feedbackThree = findViewById(R.id.feedback3);

        /////// BUTTON SETUP
        reread = findViewById(R.id.storybutton);
        retake = findViewById(R.id.retakebutton);
        back = findViewById(R.id.back_button);
        reread.setOnClickListener(rereadStory);
        retake.setOnClickListener(retakeQuiz);
        back.setOnClickListener(sendBack);

        checkAnswer(answer1.getText().toString(), 1);
        checkAnswer(answer2.getText().toString(), 2);
        checkAnswer(answer3.getText().toString(), 3);
    }

    private final View.OnClickListener rereadStory = // sends user to picture story
            new View.OnClickListener() {
                @Override
                public void onClick(View btn) {
                    Intent intent = new Intent(getApplicationContext(), JohnStory.class);
                    startActivity(intent);
                }
            };

    private final View.OnClickListener retakeQuiz = // sends user back to quiz
            new View.OnClickListener() {
                @Override
                public void onClick(View btn) {
                    Intent intent = new Intent(getApplicationContext(), JohnQuestions.class);
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

    private boolean isCorrect(ArrayList<String> tokenList, String[] arr, int arrLen){
        int tokens = tokenList.size();
        for (int i = 0; i < tokens; i++) // read through user's answers tokens
        {
            for(int counter = 0; counter < arrLen; counter++){
                if(tokenList.get(i).equals(arr[counter])){ // if any match is found
                    return true;
                }
            }
        }
        return false; // returns false if it reads through and does not find and correct matches,
    }

    public void checkAnswer(String str, int questionNumber){
        boolean flag = false;
        str = str.toLowerCase(); // convert string to all lowercase for ease of use
        StringTokenizer defaultTokenizer = new StringTokenizer(str, " "); // tokenize answer by white space
        ArrayList<String> tokens = new ArrayList<String>();
        while(defaultTokenizer.hasMoreTokens()) {// iterate through StringTokenizer tokens
            tokens.add(defaultTokenizer.nextToken());// add tokens to AL
        }
        String[] arr; // USED IN FUNCTION BELOW
        int arrLen; // will be hardcoded because the answers for each question are hardcoded.
        switch (questionNumber) { // this loads the correct answer tokens for the respective question's answer
            case 1:
                arr = qOneAnswers; // load question 1's answers
                arrLen = 10;
                flag = isCorrect(tokens, arr, arrLen);
                if (flag == true) {
                    feedbackOne.setText(R.string.correct);
                    feedbackOne.setTextColor(0xFF00C853); // this is right_answer color specified in colors.xml
                }
                else {
                    feedbackOne.setText(R.string.incorrect);
                    feedbackOne.setTextColor(0xFFFF0000); // this is wrong_answer color specified in colors.xml
                }
                break;
            case 2:
                arr = qTwoAnswers; // load question 2's answers
                arrLen = 4;
                flag = isCorrect(tokens, arr, arrLen);
                if (flag == true) {
                    feedbackTwo.setText(R.string.correct);
                    feedbackTwo.setTextColor(0xFF00C853); // this is right_answer color specified in colors.xml
                }
                else {
                    feedbackTwo.setText(R.string.incorrect);
                    feedbackTwo.setTextColor(0xFFFF0000); // this is wrong_answer color specified in colors.xml
                }
                break;
            case 3:
                arr = qThreeAnswers; // load question 3's answers
                arrLen = 8;
                flag = isCorrect(tokens, arr, arrLen);
                if (flag == true) {
                    feedbackThree.setText(R.string.correct);
                    feedbackThree.setTextColor(0xFF00C853); // this is right_answer color specified in colors.xml
                }
                else {
                    feedbackThree.setText(R.string.incorrect);
                    feedbackThree.setTextColor(0xFFFF0000); // this is wrong_answer color specified in colors.xml
                }
                break;
            default:
                System.out.println("Hi Dr. Wang :)");
                break;
        }

    }
}
