package com.lungesoft.larch.ranking;

import com.lungesoft.larch.models.News;

import java.util.*;


public class EvaluationMain extends EvaluationNDCG {

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

        System.out.println("New: ");
        rankingNDCG.ranking(updateEvaluation);

    }



}