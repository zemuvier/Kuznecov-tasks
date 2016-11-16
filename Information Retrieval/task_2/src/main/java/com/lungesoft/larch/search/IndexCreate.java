package com.lungesoft.larch.search;

import java.io.IOException;


public class IndexCreate {

    public static void main(String[] args) throws IOException {

        LuceneSearch tester;

            tester = new LuceneSearch();
            tester.createIndex();

    }
}
