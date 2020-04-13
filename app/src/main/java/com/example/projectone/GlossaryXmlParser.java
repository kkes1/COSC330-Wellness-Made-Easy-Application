package com.example.projectone;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class GlossaryXmlParser {
    private String namespace = null;
    private InputStream in;

    public GlossaryXmlParser(InputStream i){
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

    private GlossaryTerm readEntry(XmlPullParser parser) throws XmlPullParserException, IOException{
        parser.require(XmlPullParser.START_TAG, namespace, "entry");
        String term = null;
        String def = null;
        int chap = -1;
        String drawable = null;
        while(parser.next() != XmlPullParser.END_TAG){
            if(parser.getEventType() != XmlPullParser.START_TAG){
                continue;
            }
            String name = parser.getName();
            if(name.equals("term")){
                term = readTerm(parser);
            } else if(name.equals("definition")){
                def = readDefinition(parser);
            } else if(name.equals("chapter")){
                chap = readChapter(parser); //TODO: add drawable parse
            } else if(name.equals("drawable")){
                drawable = readDrawable(parser);
            } else{
                skip(parser);
            }
        }
        return new GlossaryTerm(term, def, chap, drawable);
    }

    private String readTerm(XmlPullParser parser) throws XmlPullParserException, IOException{
        parser.require(XmlPullParser.START_TAG, namespace, "term");
        String term = readText(parser);
        parser.require(XmlPullParser.END_TAG, namespace, "term");
        return term;
    }

    private String readDefinition(XmlPullParser parser) throws XmlPullParserException, IOException{
        parser.require(XmlPullParser.START_TAG, namespace, "definition");
        String def = readText(parser);
        parser.require(XmlPullParser.END_TAG, namespace, "definition");
        return def;
    }

    private int readChapter(XmlPullParser parser) throws XmlPullParserException, IOException{
        parser.require(XmlPullParser.START_TAG, namespace, "chapter");
        int chapter = Integer.parseInt(readText(parser));
        parser.require(XmlPullParser.END_TAG, namespace, "chapter");
        return chapter;
    }

    private String readDrawable(XmlPullParser parser) throws XmlPullParserException, IOException{
        parser.require(XmlPullParser.START_TAG, namespace, "drawable");
        String drawable = readText(parser);
        parser.require(XmlPullParser.END_TAG, namespace, "drawable");
        return drawable;
    }

    private String readText(XmlPullParser parser) throws XmlPullParserException, IOException{
        String result = "";
        if(parser.next() == XmlPullParser.TEXT){
            result = parser.getText();
            parser.nextTag();
        }
        return result;
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
