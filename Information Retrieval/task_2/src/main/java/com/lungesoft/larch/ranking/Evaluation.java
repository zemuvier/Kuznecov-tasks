package com.lungesoft.larch.ranking;

import com.lungesoft.larch.models.News;

import java.util.*;

public class Evaluation {

    static Map<String, Map<String, Integer>> data = new HashMap<>();

    public static void main(String[] args) throws Exception {
        RankingNDCG rankingNDCG = new RankingNDCG();
        List<List<Integer>> oldData = new ArrayList<>();
        for (String request : getMyData().keySet()) {
            oldData.add(getMyData().get(request));
        }
        System.out.println("Old: ");
        rankingNDCG.ranking(oldData);


        TaskSearch taskSearch = new TaskSearch();
        for (String request : getMyData().keySet()) {
            Map<String, Integer> document = data.getOrDefault(request, new HashMap<>());
            List<Integer> relDocs = getMyData().get(request);
            for (int i = 0; i < taskSearch.doUsualSearch(request).size(); i++) {
                document.put(taskSearch.doUsualSearch(request).get(i).getFilePath(), relDocs.get(i));
            }
            data.put(request, document);
        }

        List<List<Integer>> updateEvaluation = new ArrayList<>();
        for (String request : getMyData().keySet()) {
            List<Integer> rels = new ArrayList<>();
            for (News news : taskSearch.doUpdateSearch(request)) {
                rels.add(data.get(request).get(news.getFilePath()));
            }
            updateEvaluation.add(rels);
        }

        System.out.println();
        System.out.println("New: ");
        rankingNDCG.ranking(updateEvaluation);

    }


    private static Map<String, List<Integer>> getMyData() {
        List<Integer> a =  Arrays.asList(1, 5, 5, 3, 1, 1, 2, 2, 2, 2); // памятник
        List<Integer> b =  Arrays.asList(4, 4, 1, 1, 5, 5, 5, 2, 1, 4); // директор
        List<Integer> c =  Arrays.asList(5, 1, 4, 5, 5, 5, 4, 1, 3, 1); // компьютер
        List<Integer> d =  Arrays.asList(5, 1, 1, 4, 4, 4, 3, 3, 5, 5); // победа
        List<Integer> e =  Arrays.asList(2, 3, 5, 5, 2, 1, 2, 2, 2, 4); // звезда
        List<Integer> f =  Arrays.asList(1, 1, 4, 1, 5, 2, 1, 3, 5, 3); // фото
        List<Integer> g =  Arrays.asList(5, 3, 3, 2, 1, 3, 2, 5, 1, 2); // samsung s7
        List<Integer> h =  Arrays.asList(2, 1, 2, 3, 3, 1, 4, 2, 2, 3); // смартфон
        List<Integer> i =  Arrays.asList(1, 4, 5, 1, 2, 1, 1, 1, 1, 3); // новый год
        List<Integer> j =  Arrays.asList(5, 1, 1, 5, 2, 2, 4, 3, 4, 2); // интернет

        Map<String, List<Integer>> rel = new HashMap<>();
        rel.put("памятник", a);
        rel.put("директор", b);
        rel.put("компьютер", c);
        rel.put("победа", d);
        rel.put("звезда", e);
        rel.put("фото", f);
        rel.put("samsung s7", g);
        rel.put("смартфон", h);
        rel.put("новый год", i);
        rel.put("интернет", j);

        return rel;
    }


}
