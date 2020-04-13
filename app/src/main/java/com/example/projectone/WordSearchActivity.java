package com.example.projectone;

import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GestureDetectorCompat;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class WordSearchActivity extends AppCompatActivity {
    private String activity;
    private InputStream is;
    private ArrayList<WordSearchTerm> terms;
    private WordSearchXmlParser wsxp;
    private WordSearchGrid grid;
    private ConstraintLayout layout;
    private TableLayout tbl;
    private TableLayout wordBank;
    private GestureDetectorCompat detector;
    private int cellWidth, cellHeight, leftEdge, topEdge, rightEdge, bottomEdge;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_search);
        activity = "WordSearch";
        try {
            is = getAssets().open("word_search_terms.xml");
            wsxp = new WordSearchXmlParser(is);
            terms = (ArrayList<WordSearchTerm>) wsxp.parse();
        } catch (IOException | XmlPullParserException e) {
            e.printStackTrace();
        }
        layout = findViewById(R.id.layout);
        grid = new WordSearchGrid(15,13,terms);
        Log.i(activity, "grid.populate ends");
        tbl = findViewById(R.id.gridTable);
        buildGrid();
        wordBank = findViewById(R.id.wordBank);
        buildBank();
        detector = new GestureDetectorCompat(getApplicationContext(), new GridGestureListener());
        Log.i(activity, "onCreate ends");
    }

    @Override
    protected void onResume() {
        super.onResume();
        getInfo();
    }

    private void getInfo(){
        leftEdge = tbl.getLeft();
        topEdge = tbl.getTop();
        rightEdge = tbl.getRight();
        bottomEdge = tbl.getBottom();
        cellWidth = tbl.getWidth()/grid.getRows();
        cellHeight = tbl.getHeight()/grid.getCols();
    }

    private void buildGrid(){
        for(int i = 0; i < grid.getRows(); i++){
            TableRow tr = new TableRow(this);
            tr.setId(tbl.getId() + ((grid.getRows()+1)*i));
            tr.setBackgroundColor(0xFF000000);
            for(int j = 0; j < grid.getCols(); j++){
                TextView tv = new TextView(this);
                tv.setBackgroundColor(0xFFFFFFFF);
                tv.setText(String.valueOf(grid.getLetter(i,j)));
                tv.setId(tr.getId() + j);
                tv.setPadding(5,5,5,5);
                TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(5,5,5,5);
                tv.setLayoutParams(layoutParams);
                tv.setTextAppearance(this, R.style.TextAppearance_AppCompat_Medium);
                tv.setTextColor(0xFF000000);
                tr.addView(tv);
            }
            tbl.addView(tr);
        }
    }

    private void buildBank(){
        int wordBankRows = (int) Math.sqrt(terms.size());
        int wordBankCols = terms.size()/wordBankRows;
        for(int i = 0; i < wordBankRows; i++){
            TableRow tr = new TableRow(this);
            tr.setId(wordBank.getId() + ((wordBankRows+1)*i));
            for(int j = 0; j < wordBankCols; j++){
                TextView tv = new TextView(this);
                tv.setId(tr.getId() + j);
                tv.setText(terms.get((wordBankRows*i)+j).getWord());
                tv.setPadding(20,0,20,0);
                tv.setTextAppearance(this, R.style.TextAppearance_AppCompat_Medium);
                tr.addView(tv);
            }
            wordBank.addView(tr);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        this.detector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    class GridGestureListener extends GestureDetector.SimpleOnGestureListener{
        @Override
        public boolean onDown(MotionEvent ev){
            return true;
        }

        @Override
        public boolean onFling(MotionEvent ev1, MotionEvent ev2, float velocityX, float velocityY){
            if((ev1.getX() > leftEdge && ev1.getX() < rightEdge) && (ev1.getY() > topEdge && ev1.getY() < bottomEdge)) {
                if((ev2.getX() > leftEdge && ev2.getX() < rightEdge) && (ev2.getY() > topEdge && ev2.getY() < bottomEdge)){
                    int e1GridX = ((int)ev1.getX()-leftEdge)/cellWidth;
                    int e1GridY = ((int)ev1.getY()-topEdge)/cellHeight;
                    int e2GridX = ((int)ev1.getX()-leftEdge)/cellWidth;
                    int e2GridY = ((int)ev1.getY()-topEdge)/cellHeight;
                    System.out.println(e1GridX + "\n" + e1GridY + "\n" + e2GridX + "\n" + e2GridY);
                    if(Math.abs(velocityX) > Math.abs(velocityY)){ //check across
                        if(velocityX < 0){ //right to left
                            grid.checkPos(e2GridX, e1GridY);
                        } else {
                            grid.checkPos(e1GridX, e1GridY);
                        }
                    } else { //check down
                        if(velocityY < 0){ //down to up
                            grid.checkPos(e1GridX, e2GridY);
                        } else {
                            grid.checkPos(e1GridX, e1GridY);
                        }
                    }
                }
            }
            return true;
        }
    }

}
