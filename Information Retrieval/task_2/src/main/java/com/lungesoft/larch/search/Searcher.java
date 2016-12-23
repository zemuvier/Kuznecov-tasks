package com.lungesoft.larch.search;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.CharArraySet;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.analysis.miscellaneous.PerFieldAnalyzerWrapper;
import org.apache.lucene.analysis.ru.RussianAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Searcher {

    private static final int MAX_SEARCH = 10;

    private IndexSearcher indexSearcher;


    public Searcher(String indexDirectoryPath) throws IOException{
        Directory indexDirectory = FSDirectory.open(new File(indexDirectoryPath).toPath());
        indexSearcher = new IndexSearcher(DirectoryReader.open(indexDirectory));
    }

    public TopDocs search(String searchQuery, String ... selections) throws IOException, ParseException {
        ArrayList<QueryParser> queryParsers = new ArrayList<>();

        for (String selection : selections) {
            queryParsers.add(new QueryParser(selection, new SynonymAnalyzer()));
        }

        BooleanQuery.Builder result_query = new BooleanQuery.Builder();
        for (QueryParser query : queryParsers )
            result_query.add(query.parse(searchQuery), BooleanClause.Occur.SHOULD);

        return indexSearcher.search(result_query.build(), MAX_SEARCH);
    }

    public Document getDocument(ScoreDoc scoreDoc) throws IOException {
        return indexSearcher.doc(scoreDoc.doc);
    }


}