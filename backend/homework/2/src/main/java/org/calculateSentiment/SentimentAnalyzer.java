package org.calculateSentiment;

import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SentimentAnalyzer {

    private static final Logger logger = LoggerFactory.getLogger(SentimentAnalyzer.class);

    public static int[] detectProsAndCons(String review, String[][] featureSet, String[] posOpinionWords, String[] negOpinionWords) {
        int[] featureOpinions = new int[featureSet.length];

        for (int i = 0; i < featureSet.length; i++) {
            String[] features = featureSet[i];
            for (String feature : features) {
                int opinion = getOpinionOnFeature(review, feature, posOpinionWords, negOpinionWords);
                if (opinion != 0) {
                    featureOpinions[i] = opinion;
                    logger.info("Opinion on feature '{}' is '{}'", feature, opinion);
                    System.out.println("Opinion on feature '" + feature + "' is '" + opinion + "'");
                    break; // Move to the next feature set
                }
            }
        }

        return featureOpinions;
    }

    private static int getOpinionOnFeature(String review, String feature, String[] posOpinionWords, String[] negOpinionWords) {
        int opinion = checkForWasPhrasePattern(review, feature, posOpinionWords, negOpinionWords);

        if (opinion == 0) {
            opinion = checkForOpinionFirstPattern(review, feature, posOpinionWords, negOpinionWords);
        }

        return opinion;
    }

    private static int checkForWasPhrasePattern(String review, String feature, String[] posOpinionWords, String[] negOpinionWords) {
        int opinion = 0;
        String pattern = feature + " was ";

        if (review.toLowerCase().contains(pattern)) {
            for (String posOpinion : posOpinionWords) {
                if (review.toLowerCase().contains(pattern + posOpinion)) {
                    opinion = 1;
                    logger.debug("Found positive opinion '{}' in the pattern '{}'", posOpinion, pattern);
                    break;
                }
            }

            if (opinion == 0) {
                for (String negOpinion : negOpinionWords) {
                    if (review.toLowerCase().contains(pattern + negOpinion)) {
                        opinion = -1;
                        logger.debug("Found negative opinion '{}' in the pattern '{}'", negOpinion, pattern);
                        break;
                    }
                }
            }
        }

        return opinion;
    }

    private static int checkForOpinionFirstPattern(String review, String feature, String[] posOpinionWords, String[] negOpinionWords) {
        int opinion = 0;

        for (String sentence : review.split("\\.")) {
            for (String posOpinion : posOpinionWords) {
                if (sentence.toLowerCase().contains(posOpinion + " " + feature)) {
                    opinion = 1;
                    logger.debug("Found positive opinion '{}' in the sentence '{}'", posOpinion, sentence);
                    return opinion;
                }
            }

            for (String negOpinion : negOpinionWords) {
                if (sentence.toLowerCase().contains(negOpinion + " " + feature)) {
                    opinion = -1;
                    logger.debug("Found negative opinion '{}' in the sentence '{}'", negOpinion, sentence);
                    return opinion;
                }
            }
        }

        return opinion;
    }

    public static void main(String[] args) {
        String review = "Haven't been here in years! Fantastic service and the food was delicious! Definitely will be a frequent flyer! Francisco was very attentive";
        String[][] featureSet = {
                {"ambiance", "ambience", "atmosphere", "decor"},
                {"dessert", "ice cream", "desert"},
                {"food"},
                {"soup"},
                {"service", "management", "waiter", "waitress", "bartender", "staff", "server"}
        };
        String[] posOpinionWords = {"good", "fantastic", "friendly", "great", "excellent", "amazing", "awesome", "delicious"};
        String[] negOpinionWords = {"slow", "bad", "horrible", "awful", "unprofessional", "poor"};

        int[] featureOpinions = detectProsAndCons(review, featureSet, posOpinionWords, negOpinionWords);
        logger.info("Opinions on Features: {}", Arrays.toString(featureOpinions));
        System.out.println("Opinions on Features: " + Arrays.toString(featureOpinions));
    }
}
