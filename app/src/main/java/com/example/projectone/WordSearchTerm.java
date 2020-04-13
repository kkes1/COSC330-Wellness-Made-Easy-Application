package com.example.projectone;

import android.util.Pair;

public class WordSearchTerm {
    private String word;
    private Pair<Integer, Integer> startPos;
    private int dir; //0 for across, 1 for down

    public WordSearchTerm(String s, Pair<Integer, Integer> p, int d){
        word = s;
        startPos = p;
        dir = d;
    }

    public String getWord() {
        return word;
    }

    public Pair<Integer, Integer> getStartPos(){
        return startPos;
    }

    public int getDir(){
        return dir;
    }
}
