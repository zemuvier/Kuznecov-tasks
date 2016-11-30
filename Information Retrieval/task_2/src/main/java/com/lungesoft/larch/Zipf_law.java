package com.lungesoft.larch;

import java.io.*;
import java.util.*;


import com.sun.javafx.binding.StringFormatter;
import org.apache.lucene.index.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.BytesRef;

public class Zipf_law {

    private static TreeMap<String, Long> multiset = new TreeMap<>();
    private static ArrayList<String> fields = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        Directory indexDirectory = FSDirectory.open(new File("index").toPath());
        IndexReader reader = DirectoryReader.open(indexDirectory);
        MultiFields.getFields(reader).forEach(fields::add);

        TermsEnum termEnum = MultiFields.getTerms(reader, "contents").iterator();
        BytesRef bytesRef;
        while ((bytesRef = termEnum.next()) != null) {
            String term = bytesRef.utf8ToString();
            if (!fields.contains(term)) {
                multiset.put(term, termEnum.totalTermFreq());
            }
        }

        List<Map.Entry<String, Long>> l = new ArrayList<>(multiset.entrySet());
        Collections.sort(l, (o1, o2) ->  -1 * o1.getValue().compareTo(o2.getValue()));

        Writer writer = new OutputStreamWriter(new FileOutputStream(new File("Result.csv")), "utf-8");
        Writer get_diagram = new OutputStreamWriter(new FileOutputStream(new File("get_diagram.txt")), "utf-8");
        String first = "Rank" + "," + "Word" + "," + "Freq" + "\n";
        writer.write(first);
        int rank = 1;
        for( Map.Entry<String, Long> e : l ){
            String res = rank + "," + e.getValue()  + "," + e.getKey()+ "\n";
            writer.write(res);
            get_diagram.write(e.getValue().toString() + "\n");
            rank++;
        }
        ;
        writer.flush();
        writer.close();
    }
}
