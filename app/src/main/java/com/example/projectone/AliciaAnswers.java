package com.example.projectone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AliciaAnswers extends AppCompatActivity {

    private TextView answer1, answer2, answer3, answer4, answer5; // TextViews that hold the user's answers
    private TextView feedbackOne, feedbackTwo, feedbackThree, feedbackFour, feedbackFive; // TextViews that display if a user's answer is right/wrong
    private Button reread, retake, back;
    private String qOneAnswer, qTwoAnswer, qThreeAnswer,qFourAnswer,qFiveAnswer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alicia_answers);

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

        feedbackOne = findViewById(R.id.feedback1);
        feedbackTwo = findViewById(R.id.feedback2);
        feedbackThree = findViewById(R.id.feedback3);
        feedbackFour = findViewById(R.id.feedback4);
        feedbackFive = findViewById(R.id.feedback5);

        /////// BUTTON SETUP
        reread = findViewById(R.id.storybutton);
        retake = findViewById(R.id.retakebutton);
        back = findViewById(R.id.back_button);
        reread.setOnClickListener(rereadStory);
        retake.setOnClickListener(retakeQuiz);
        back.setOnClickListener(sendBack);

        qOneAnswer = "minocycline";
        qTwoAnswer = "200 mg";
        qThreeAnswer = "twice daily";
        qFourAnswer = "30";
        qFiveAnswer = "finish all medicine unless otherwise directed by your doctor";

        checkAnswer(answer1.getText().toString(), 1);
        checkAnswer(answer2.getText().toString(), 2);
        checkAnswer(answer3.getText().toString(), 3);
        checkAnswer(answer4.getText().toString(), 4);
        checkAnswer(answer5.getText().toString(), 5);
    }

    public void checkAnswer(String str, int questionNumber){
        str = str.toLowerCase(); // convert string to all lowercase for ease of use
        switch (questionNumber) { // this loads the correct answer tokens for the respective question's answer
            case 1:
                if (str.compareTo(qOneAnswer) == 0) {
                    feedbackOne.setText(R.string.correct);
                    feedbackOne.setTextColor(0xFF00C853); // this is right_answer color specified in colors.xml
                }
                else {
                    feedbackOne.setText(R.string.incorrect);
                    feedbackOne.setTextColor(0xFFFF0000); // this is wrong_answer color specified in colors.xml
                }
                break;
            case 2:
                if (str.compareTo(qTwoAnswer) == 0) {
                    feedbackTwo.setText(R.string.correct);
                    feedbackTwo.setTextColor(0xFF00C853); // this is right_answer color specified in colors.xml
                }
                else {
                    feedbackTwo.setText(R.string.incorrect);
                    feedbackTwo.setTextColor(0xFFFF0000); // this is wrong_answer color specified in colors.xml
                }
                break;
            case 3:
                if (str.compareTo(qThreeAnswer) == 0) {
                    feedbackThree.setText(R.string.correct);
                    feedbackThree.setTextColor(0xFF00C853); // this is right_answer color specified in colors.xml
                }
                else {
                    feedbackThree.setText(R.string.incorrect);
                    feedbackThree.setTextColor(0xFFFF0000); // this is wrong_answer color specified in colors.xml
                }
                break;
            case 4:
                if (str.compareTo(qFourAnswer) == 0) {
                    feedbackFour.setText(R.string.correct);
                    feedbackFour.setTextColor(0xFF00C853); // this is right_answer color specified in colors.xml
                }
                else {
                    feedbackFour.setText(R.string.incorrect);
                    feedbackFour.setTextColor(0xFFFF0000); // this is wrong_answer color specified in colors.xml
                }
                break;
            case 5:
                if (str.compareTo(qFiveAnswer) == 0) {
                    feedbackFive.setText(R.string.correct);
                    feedbackFive.setTextColor(0xFF00C853); // this is right_answer color specified in colors.xml
                }
                else {
                    feedbackFive.setText(R.string.incorrect);
                    feedbackFive.setTextColor(0xFFFF0000); // this is wrong_answer color specified in colors.xml
                }
                break;
            default:
                System.out.println("Hi Dr. Wang :)");
                break;
        }
    }

    private final View.OnClickListener rereadStory = // sends user to picture story
            new View.OnClickListener() {
                @Override
                public void onClick(View btn) {
                    Intent intent = new Intent(getApplicationContext(), AliciaStory.class);
                    startActivity(intent);
                }
            };

    private final View.OnClickListener retakeQuiz = // sends user back to quiz
            new View.OnClickListener() {
                @Override
                public void onClick(View btn) {
                    Intent intent = new Intent(getApplicationContext(), AliciaQuestions.class);
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
