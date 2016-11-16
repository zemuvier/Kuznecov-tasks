package com.lungesoft.larch.utils;

import com.lungesoft.larch.models.News;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Vadim on 21.09.2016.
 */
public class Utils {

    public static News readFile(File file) {
        StringBuilder stringJSON = new StringBuilder();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                stringJSON.append(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        News news = new News();
        JSONObject news_json = new JSONObject(stringJSON.toString());
        if (news_json.has("title") && news_json.has("author") && news_json.has("link") && news_json.has("submit") && news_json.has("text")) {
            news.setTitle(news_json.getString("title"));
            news.setAuthor(news_json.getString("author"));
            news.setLink(news_json.getString("link"));
            news.setSubmit(news_json.getString("submit"));
            news.setText(news_json.getString("text"));
            return news;
        } else {
            return null;
        }
    }

}
