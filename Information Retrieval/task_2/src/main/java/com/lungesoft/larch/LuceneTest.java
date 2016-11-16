package com.lungesoft.larch;

import com.lungesoft.larch.search.LuceneSearch;
import org.apache.commons.logging.Log;
import org.apache.lucene.queryparser.classic.ParseException;

import java.io.IOException;


public class LuceneTest {
    private static String search_request = "AliExpress";

    public static void main(String[] args) {

        LuceneSearch tester;
        try {
            tester = new LuceneSearch();
            tester.createIndex();
            tester.search(search_request, "title", "text");
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
