package de.ramunno.mylittlefactory.factory;

/**
 * Created by MitarbeiterISZ on 16.02.2017.
 */


import android.content.Context;
import android.content.res.Resources;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import de.ramunno.mylittlefactory.R;


public class ItemFactory {
    private class ItemsXMLParser {
        private final String ns = null;

        public List<Item> parse(InputStream in) throws XmlPullParserException, IOException {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            parser.nextTag();
            return readItems(parser);
        }

        private List<Item> readItems(XmlPullParser parser) throws XmlPullParserException, IOException {
            List<Item> entries = new ArrayList();

            parser.require(XmlPullParser.START_TAG, ns, "items");
            while (parser.next() != XmlPullParser.END_TAG) {
                if (parser.getEventType() != XmlPullParser.START_TAG) {
                    continue;
                }
                String name = parser.getName();
                // Start by looking for the item tag
                if (name.equalsIgnoreCase("item")) {
                    entries.add(readItem(parser));

                } else {
                    skip(parser);
                }

            }
            return entries;
        }

        private Item readItem(XmlPullParser parser) throws XmlPullParserException, IOException {
            parser.require(XmlPullParser.START_TAG, ns, "item");
            String Name = null;
            Integer ID = null;
            while (parser.next() != XmlPullParser.END_TAG) {
                if (parser.getEventType() != XmlPullParser.START_TAG) {
                    continue;
                }
                String tag = parser.getName();
                if (tag.equalsIgnoreCase("id")) {
                    ID = readItemID(parser);
                } else if (tag.equalsIgnoreCase("name")) {
                    Name = readItemName(parser);
                } else {
                    skip(parser);
                }
            }
            return new Item(ID, Name);
        }

        private String readItemName(XmlPullParser parser) throws XmlPullParserException, IOException {
            parser.require(XmlPullParser.START_TAG, ns, "name");
            String Name = readText(parser);
            parser.require(XmlPullParser.END_TAG, ns, "name");
            return Name;
        }

        private Integer readItemID(XmlPullParser parser) throws XmlPullParserException, IOException {
            parser.require(XmlPullParser.START_TAG, ns, "id");
            Integer ID = readNumber(parser);
            parser.require(XmlPullParser.END_TAG, ns, "id");
            return ID;

        }

        private String readText(XmlPullParser parser) throws XmlPullParserException, IOException {
            String res = "";
            if (parser.next() == XmlPullParser.TEXT) {
                res = parser.getText();
                parser.nextTag();
            }
            return res;
        }

        private Integer readNumber(XmlPullParser parser) throws XmlPullParserException, IOException {
            Integer res = null;
            if (parser.next() == XmlPullParser.TEXT) {
                res = Integer.parseInt(parser.getText());
                parser.nextTag();
            }
            return res;
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

    public List<Item> generate(Context ctx){
        Resources res = ctx.getResources();
        InputStream in_s = res.openRawResource(R.raw.items);

        try{
            List<Item> items = null;
            ItemsXMLParser parser = new ItemsXMLParser();
            items = parser.parse(in_s);
            in_s.close();
            return items;
        }catch (Exception e0) {
            try {
                in_s.close();
            }catch(IOException e1)
            {

            }
        }
        return null;
    }
}
