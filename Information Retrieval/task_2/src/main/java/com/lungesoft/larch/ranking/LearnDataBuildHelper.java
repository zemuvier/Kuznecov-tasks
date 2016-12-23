package com.lungesoft.larch.ranking;

import com.lungesoft.larch.models.Factors;
import com.lungesoft.larch.models.News;
import org.apache.lucene.queryparser.classic.ParseException;

import java.io.IOException;

public class LearnDataBuildHelper {
    private static String request = "Президент Путин";

    public static void main(String[] args) throws IOException, ParseException {
        TaskSearch taskSearch = new TaskSearch();

        for (News news : taskSearch.doUsualSearch(request)) {
            double[] factor = taskSearch.calcNewsFactors(news, request);
            double evaluation = factor[0] + factor[1] + factor[2];

            System.out.print(factor[0] + " : ");
            System.out.print(factor[1] + " : ");
            System.out.print(factor[2] + " : ");
            System.out.print(factor[3] + " : ");

            //подбор выражания для вхождения в нужный range оценки. У меня было от (1 до 5)
            double roundEvaluation = Math.floor(evaluation * 0.9) ;
            if (roundEvaluation > 5) {
                System.out.print(5.0 + " : ");
            } else {
                System.out.print(roundEvaluation + " : ");

            }
            System.out.print(request + " : ");
            System.out.println(news.getFilePath() + " ");
        }

    }



}
