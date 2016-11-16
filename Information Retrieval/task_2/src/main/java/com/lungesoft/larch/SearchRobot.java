package com.lungesoft.larch;

import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.*;
import java.sql.ResultSet;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class SearchRobot {

    private List<String> links = new LinkedList<>();
    private List<String> documents = new LinkedList<>();
    private List<String> exceptions = new LinkedList<>();

    {
        exceptions.add("http://lifehacker.ru/");
        exceptions.add("http://lifehacker.ru/top/");
        exceptions.add("http://lifehacker.ru/list/");
        exceptions.add("https://lifehacker.ru/topics/relax/testy/");
        exceptions.add("https://lifehacker.ru/tag/kartochki/");
        exceptions.add("https://lifehacker.ru/tag/obzor/");
        exceptions.add("https://lifehacker.ru/topics/relax/books/");
        exceptions.add("https://lifehacker.ru/special/longreads/");
    }

    private int count;
    private int count_downloads = 0;

    public String downloadSites(String page, int current) throws IOException {
        System.out.println(links.size());

        if (current > 10000) {
            return "";
        }


        Document doc = Jsoup.connect(page).timeout(1000000).get();


        //Iterator<Element> iterator = doc.select("link").iterator();
        Iterator<Element> iterator = doc.select("a").iterator();

        while (iterator.hasNext()) {

            String link = iterator.next().attr("href");
            System.out.println(link != null && link.length() > 7);
            System.out.println(link);
            if (!link.equals("/"))
                if ((link.length() > 4 && link.substring(0, 4).compareTo("http") == 0 && link.contains("lifehacker.ru/")
                        && !link.contains(".png") && !link.contains(".php") && !link.contains("wp-")
                        && !link.contains("comments"))
                        || link.contains("http://lifehacker.ru/list/")
                        || link.contains("http://lifehacker.ru/top/")) {

                    System.out.println(link);

                    if (!links.contains(link)) {
                        try {
                            downloadNews(link);
                            links.add(link);
                            writeToCache(link);
                            downloadSites(link, count++);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }

                }

        }

        return "";
    }


    private void downloadNews(String link) throws IOException {
        Document document = Jsoup.connect(link).timeout(1000000).get();
        JSONObject news = new JSONObject();

        String file_title = transliteration(document.title().toLowerCase()).replaceAll("[^a-zA-Z0-9]", "_") + ".json";


        String title = document.select("h1.single__title").text();
        news.put("author", document.select("div.single__author").text());
        news.put("title", title);
        news.put("submit", document.select("div.single__excerpt").text());
        news.put("link", link);

        Iterator<Element> iteratorw = document.select("div.post-content").select("p").iterator();

        StringBuilder text = new StringBuilder();
        while (iteratorw.hasNext()) {
            text.append(iteratorw.next().text() + "\n");
        }

        news.put("text", text.toString());

        if (title.compareTo("") != 0) {
            if (!documents.contains(file_title)) {
                Writer output = new BufferedWriter(new PrintWriter(new File("data/" + file_title)));
                System.out.println("Запись " + ++count_downloads);
                System.out.println(title);

                output.write(news.toString());
                output.flush();
                output.close();
            }
            documents.add(file_title);

        }

    }

    public static String transliteration(String text) {
        char[] abcCyr = {'а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з', 'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ы', 'ь', 'ъ', 'э', 'ю', 'я'};
        String[] abcLat = {"a", "b", "v", "g", "d", "e", "jo", "zh", "z", "i", "i", "k", "l", "m", "n", "o", "p", "r", "s", "t", "u", "f", "h", "ts", "ch", "sh", "sch", "", "", "", "e", "ju", "ja"};
        StringBuilder builder = new StringBuilder();

        Map<Character, String> dict = IntStream.range(0, abcCyr.length)
                .boxed().collect(Collectors.toMap(i -> abcCyr[i], i -> abcLat[i]));

        for (int i = 0; i < text.length(); i++) {
            char current_char = Character.toLowerCase(text.charAt(i));
            if (dict.containsKey(current_char)) {
                builder.append(dict.get(current_char));
            } else {
                builder.append(current_char);
            }

        }
        return builder.toString();
    }


    private void loadFromCache() throws FileNotFoundException {
        Scanner scanner = new Scanner(new BufferedReader(new FileReader("robot_cache/cache.txt")));
        while (scanner.hasNext())
            links.add(scanner.next());

        System.out.println(links);
    }

    private void writeToCache(String url) {
        if (!exceptions.contains(url)) {
            try {
                Writer output = new BufferedWriter(new PrintWriter(new FileWriter("robot_cache/cache.txt", true)));
                output.write("\n" + url);
                output.flush();
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) throws Exception {
        SearchRobot docs = new SearchRobot();
        docs.loadFromCache();

        docs.downloadSites("http://lifehacker.ru/", 0);


    }

}
