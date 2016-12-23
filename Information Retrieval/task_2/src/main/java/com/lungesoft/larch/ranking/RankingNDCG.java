package com.lungesoft.larch.ranking;

import javax.validation.constraints.Null;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.*;

public class RankingNDCG {

    private double lg2(double x) {
        return Math.log(x)/Math.log(2.0);
    }


    public double nDCG(List<? extends Number> rel) {
        int length = rel.size();

        double DCG = rel.get(0).doubleValue();
        for (int i = 1; i < length; i++) {
            DCG += rel.get(i).doubleValue() / (lg2(i + 1));
        }

        rel.sort((o1, o2) -> -1 * new Double(o1.doubleValue()).compareTo(o2.doubleValue()));
        double IDCG = rel.get(0).doubleValue();
        for (int i = 1; i < length; i++) {
            IDCG += rel.get(i).doubleValue() / (lg2(i + 1));
        }

        return DCG / IDCG;
    }


    public void ranking(List<List<Integer>> rel) {
        List<Double> result = new ArrayList<>();
        for (List<Integer> request : rel) {
            List<Number> s = new LinkedList<>();
            s.addAll(request);
            Double ndcg = nDCG(request);
            s.add(ndcg);
            result.add(ndcg);
        }

        double relResult = 0;
        for (double v : result) {
            relResult += v;
        }

        System.out.println((relResult / result.size()));

    }



}
