package com.example.projectone;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

class GlossaryTerm{
    private String term;
    private String definition, drawable;
    private int chapter;

    GlossaryTerm(String word, String meaning, int c, String d){
        term = word;
        definition = meaning;
        chapter = c;
        drawable = d;
    }

    public String getTerm(){
        return term;
    }

    public String getDefinition(){
        return definition;
    }

    public int getChapter(){
        return chapter;
    }

    public String getDrawableString(){
        return drawable;
    }

}

public class GlossaryClass {
    private ArrayList<GlossaryTerm> words;
    private InputStream is;
    private int which_term = -1;
    //TODO: add sound/picture arrays and relevant getters

    public GlossaryClass(InputStream input) throws IOException, XmlPullParserException {
        is = input;
        GlossaryXmlParser gxp = new GlossaryXmlParser(is);
        words = (ArrayList<GlossaryTerm>) gxp.parse();
    }

    public GlossaryTerm randomTerm(){
        Random r = new Random();
        int rand = r.nextInt(words.size());
        while(rand == which_term){
            rand = r.nextInt(words.size());
        }
        which_term = rand;
        return words.get(which_term);
    }

    public GlossaryTerm previousTerm(){
        which_term--;
        if(which_term < 0){
            which_term = words.size()-1;
        }
        return words.get(which_term);
    }

    public GlossaryTerm nextTerm(){
        which_term++;
        if(which_term > words.size()-1){
            which_term = 0;
        }
        return words.get(which_term);
    }
}
