package com.example.projectone;

import android.util.Pair;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class WordSearchXmlParser {
    private String namespace = null;
    private InputStream in;

    public WordSearchXmlParser(InputStream i){
        in = i;
    }

    public List parse() throws XmlPullParserException, IOException {
        try{
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            parser.nextTag();
            return readFile(parser);
        } catch (Error e){
            e.printStackTrace();
        } finally {
            in.close();
        }
        return null;
    }

    private List readFile(XmlPullParser parser) throws XmlPullParserException, IOException{
        List entries = new ArrayList();
        parser.require(XmlPullParser.START_TAG, namespace, "assets");
        while(parser.next() != XmlPullParser.END_TAG){
            if(parser.getEventType() != XmlPullParser.START_TAG){
                continue;
            }
            String name = parser.getName();
            if(name.equals("entry")){
                entries.add(readEntry(parser));
            } else {
                skip(parser);
            }
        }
        return entries;
    }

    private WordSearchTerm readEntry(XmlPullParser parser) throws XmlPullParserException, IOException{
        parser.require(XmlPullParser.START_TAG, namespace, "entry");
        String term = null;
        Pair<Integer, Integer> pos = null;
        int dir = -1;
        while(parser.next() != XmlPullParser.END_TAG){
            if(parser.getEventType() != XmlPullParser.START_TAG){
                continue;
            }
            String name = parser.getName();
            if(name.equals("term")){
                term = readTerm(parser);
            } else if(name.equals("startPos")){
                pos = readPos(parser);
            } else if(name.equals("direction")){
                dir = readDir(parser);
            } else {
                skip(parser);
            }
        }
        System.out.println("term: " + term);
        System.out.println("pos: " + pos.first + pos.second);
        System.out.println("dir: " + dir);
        parser.require(XmlPullParser.END_TAG, namespace, "entry");
        return new WordSearchTerm(term, pos, dir);
    }

    private String readTerm(XmlPullParser parser) throws XmlPullParserException, IOException{
        parser.require(XmlPullParser.START_TAG, namespace, "term");
        String term = readText(parser);
        parser.require(XmlPullParser.END_TAG, namespace, "term");
        return term;
    }

    private Pair<Integer, Integer> readPos(XmlPullParser parser) throws XmlPullParserException, IOException{
        parser.require(XmlPullParser.START_TAG, namespace, "startPos");
        Pair<Integer, Integer> pos = readPair(parser);
        parser.require(XmlPullParser.END_TAG, namespace, "startPos");
        return pos;
    }

    private int readDir(XmlPullParser parser) throws XmlPullParserException, IOException{
        parser.require(XmlPullParser.START_TAG, namespace, "direction");
        String d = readText(parser);
        int dir;
        if(d.equals("across")){
            dir = 0;
        } else {
            dir = 1;
        }
        parser.require(XmlPullParser.END_TAG, namespace, "direction");
        return dir;
    }

    private String readText(XmlPullParser parser) throws XmlPullParserException, IOException{
        String result = "";
        if(parser.next() == XmlPullParser.TEXT){
            result = parser.getText();
            parser.nextTag();
        }
        return result;
    }

    private Pair<Integer, Integer> readPair(XmlPullParser parser) throws XmlPullParserException, IOException{
        String temp = "";
        Pair<Integer, Integer> p;
        int x, y, commaPos;
        if(parser.next() == XmlPullParser.TEXT){
            temp = parser.getText();
            parser.nextTag();
        }
        commaPos = temp.indexOf(",");
        x = Integer.parseInt(temp.substring(0, commaPos));
        y = Integer.parseInt(temp.substring(commaPos+1));
        p = new Pair<>(x,y);
        return p;
    }

    private void skip(XmlPullParser parser) throws XmlPullParserException, IOException{
        if(parser.getEventType() != XmlPullParser.START_TAG){
            throw new IllegalStateException();
        }
        int depth = 1;
        while(depth != 0){
            switch(parser.next()){
                case XmlPullParser.END_TAG:
                    depth--;
                    break;
                case XmlPullParser.START_TAG:
                    depth++;
                    break;
            }
        }
    }
}
