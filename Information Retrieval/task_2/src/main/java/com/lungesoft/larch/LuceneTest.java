package com.lungesoft.larch;

import com.lungesoft.larch.search.LuceneSearch;
import org.apache.lucene.queryparser.classic.ParseException;

import java.io.IOException;

public class LuceneTest {
    private static String search_request = "aliexpress";

    public static void main(String[] args) {

        LuceneSearch tester;
        try {
            tester = new LuceneSearch();
            tester.search(search_request, "title", "text");
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
