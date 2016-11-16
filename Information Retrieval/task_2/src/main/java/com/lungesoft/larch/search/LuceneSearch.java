package com.lungesoft.larch.search;

import com.lungesoft.larch.models.News;
import com.lungesoft.larch.models.ResponseList;
import com.lungesoft.larch.utils.Utils;
import org.apache.lucene.document.Document;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class LuceneSearch {

    private final String indexDir = "index";
    private final String dataDir = "data";
    private Indexer indexer;
    private Searcher searcher;

    public void createIndex() throws IOException{
        System.out.println("Starting indexing");
        indexer = new Indexer(indexDir);
        int numIndexed;
        long startTime = System.currentTimeMillis();
        numIndexed = indexer.createIndex(dataDir);
        long endTime = System.currentTimeMillis();
        indexer.close();
        System.out.println(numIndexed + " File indexed, time taken: " + (endTime-startTime) + " ms");
    }

    public ResponseList<News> search(String searchQuery, String ... selections) throws IOException, ParseException {
        searcher = new Searcher(indexDir);
        long startTime = System.currentTimeMillis();


        TopDocs hits = searcher.search(searchQuery, selections);
        long endTime = System.currentTimeMillis();

        System.out.println(hits.totalHits + " documents with - "  + searchQuery +  " - found. Time :" + (endTime - startTime));

        ArrayList<News> newsList = new ArrayList<>();
        for(ScoreDoc scoreDoc : hits.scoreDocs) {
            Document doc = searcher.getDocument(scoreDoc);
            String filepath = doc.get("filepath");

            News news = Utils.readFile(new File(filepath));
            if (news != null) {
                news.setScore(scoreDoc.score);
                newsList.add(news);
            }

            System.out.println("File: " + scoreDoc.score + " " + filepath);
        }
        return new ResponseList<>((endTime - startTime), hits.totalHits, newsList);
    }


}