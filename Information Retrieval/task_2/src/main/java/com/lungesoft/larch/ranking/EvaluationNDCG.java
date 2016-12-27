package com.lungesoft.larch.ranking;

import com.lungesoft.larch.models.News;

import java.util.*;


public class EvaluationNDCG {

    static Map<String, Map<String, Integer>> data = new HashMap<>();

    public static void main(String[] args) throws Exception {
        RankingNDCG rankingNDCG = new RankingNDCG();
        List<List<Integer>> oldData = new ArrayList<>();
        for (String request : getMyData().keySet()) {
            oldData.add(getMyData().get(request));
        }
        System.out.println("Old: ");
        rankingNDCG.ranking(oldData);

    }

    public static Map<String, List<Integer>> getMyData() {
        List<Integer> a =  Arrays.asList(1, 2, 4, 3, 5, 3, 2, 3, 5, 5);
        List<Integer> b =  Arrays.asList(4, 2, 2, 3, 5, 1, 1, 1, 5, 5);
        List<Integer> c =  Arrays.asList(5, 1, 4, 5, 5, 5, 4, 1, 3, 1);
        List<Integer> d =  Arrays.asList(5, 1, 1, 4, 4, 4, 3, 3, 5, 5);
        List<Integer> e =  Arrays.asList(1, 3, 5, 4, 2, 3, 2, 4, 1, 5);
        List<Integer> f =  Arrays.asList(1, 1, 4, 1, 5, 2, 1, 3, 5, 3);
        List<Integer> g =  Arrays.asList(2, 3, 1, 4, 3, 1, 4, 5, 2, 1);
        List<Integer> h =  Arrays.asList(2, 1, 2, 3, 3, 1, 4, 2, 2, 3);
        List<Integer> i =  Arrays.asList(1, 4, 5, 1, 2, 1, 1, 1, 1, 3);
        List<Integer> j =  Arrays.asList(1, 3, 1, 1, 5, 4, 2, 2, 5, 5);

        Map<String, List<Integer>> rel = new HashMap<>();
        rel.put("вокзал", a);
        rel.put("рождество", b);
        rel.put("самолет", c);
        rel.put("Москва", d);
        rel.put("погода", e);
        rel.put("iphone 7", f);
        rel.put("сериал", g);
        rel.put("мандарины", h);
        rel.put("часы", i);
        rel.put("молоко", j);

        return rel;
    }


}