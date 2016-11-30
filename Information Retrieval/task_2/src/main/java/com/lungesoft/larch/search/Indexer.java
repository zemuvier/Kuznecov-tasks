package com.lungesoft.larch.search;

import com.lungesoft.larch.models.News;
import com.lungesoft.larch.utils.Utils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.miscellaneous.PerFieldAnalyzerWrapper;
import org.apache.lucene.analysis.ru.RussianAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Indexer {

    private IndexWriter writer;

    protected Indexer(String indexDirectoryPath) throws IOException{
        //this directory will contain the indexes
        Directory indexDirectory = FSDirectory.open(new File(indexDirectoryPath).toPath());
        //create the indexer
        IndexWriterConfig indexConfig = new IndexWriterConfig(new SynonymAnalyzer());
        indexConfig.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
        writer = new IndexWriter(indexDirectory,  indexConfig);

    }

    protected void close() throws IOException{
        writer.close();
    }

    private Document getDocument(File file) throws IOException{
        Document document = new Document();

        News news = Utils.readFile(file);
        //index file contents
        Field contentField = new TextField("contents", new FileReader(file));

        Field titleField = new TextField("title", news.getTitle(), Field.Store.YES);
        Field authorField = new TextField("author", news.getAuthor(), Field.Store.YES);
        Field submitField = new TextField("submit", news.getSubmit(), Field.Store.YES);
        Field textField = new TextField("text", news.getAuthor(), Field.Store.YES);


        //index file name
        Field fileNameField = new TextField("filename", file.getName(), Field.Store.YES);
        //index file path
        Field filePathField = new TextField("filepath", file.getCanonicalPath(), Field.Store.YES);

        document.add(contentField);
        document.add(titleField);
        document.add(authorField);
        document.add(submitField);
        document.add(textField);
        document.add(fileNameField);
        document.add(filePathField);

        return document;
    }

    private void indexFile(File file) throws IOException{
        Document document = getDocument(file);
        writer.addDocument(document);
    }

    protected int createIndex(String dataDirPath) throws IOException{
        File[] files = new File(dataDirPath).listFiles();

        for (File file : files) {
            if(!file.isDirectory() && !file.isHidden() && file.exists() && file.canRead()){
                indexFile(file);
            }
        }
        return writer.numDocs();
    }
}