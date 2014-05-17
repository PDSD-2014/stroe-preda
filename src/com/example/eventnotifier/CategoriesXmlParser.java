package com.example.eventnotifier;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import android.util.Xml;

public class CategoriesXmlParser {
    
	private static final String namespace = null;   
   
    public ArrayList<Category> parse(InputStream in) throws XmlPullParserException, IOException {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            parser.nextTag();
            return readCategory(parser);
        } finally {
            in.close();
        }
    }
    
    private ArrayList<Category> readCategory(XmlPullParser parser) throws XmlPullParserException, IOException {
        ArrayList<Category> categories = new ArrayList<Category>();

        parser.require(XmlPullParser.START_TAG, namespace, Constants.ROOT_TAG);
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals(Constants.START_TAG)) {
            	categories.add(addCategory(parser));
            } else {
                skip(parser);
            }
        }  
        return categories;
    }
    
    private Category addCategory(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, namespace, Constants.START_TAG);
        Category category = new Category();
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String attribute = parser.getName();
            String value = readAttribute(parser, attribute);
            category.set(attribute, value);
        }
        return category;
    }
    
    private String readAttribute(XmlPullParser parser, String attribute) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, namespace, attribute);
        String title = readText(parser);
        parser.require(XmlPullParser.END_TAG, namespace, attribute);
        return title;
    }
    
    private String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
        String result = "";
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }
        return result;
    }
    
    private void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            throw new IllegalStateException();
        }
        int depth = 1;
        while (depth != 0) {
            switch (parser.next()) {
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