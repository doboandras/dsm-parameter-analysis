package dsm.util;

import static dsm.parameters.DimRed.*;
import static dsm.parameters.MiscParam.*;
import static dsm.parameters.VecSim.*;
import static dsm.util.MiscUtil.createOutputFile;
import static dsm.util.MiscUtil.out;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import util.ComparablePair;
import util.SimilarityScoreParts;

/**
 *
 * @author Dobó
 */
public class Evaluation {
    
    
    /**
     * This function is responsible for computing the semantic similarity of words. It computes the semantic similarity of all the word pairs in the selected test dataset.
     */
    public static void evaluate(){

        evaluate(allNounQuestionsWithAllAnswers, objVerbTuples, subjVerbTuples, nounNcmodTuples, new HashMap<String, HashMap<String, Double>>(), 
                verbObjectTuples, verbSubjectTuples, ncmodNounTuples, new HashMap<String, Long>(), 
                verbObjectInformation, verbSubjectInformation, ncmodNounInformation, new HashMap<String, Double>(), 
                nounFrequencies, nounVectorElementSums, nounVectorElementAbsValueSums, nounVectorLengthSquares, 
                (dimensionalityReductionType==DimensionalityReductionType.SVD ? dimensionalityReductionParameter.intValue() : allNounFeatureCount), 
                allNounTypeCount, semanticCategoriesNouns);
        evaluate(allVerbQuestionsWithAllAnswers, verbDobjTuples, verbNcsubjTuples, verbPrepTuples, verbObj2Tuples, 
                dobjVerbTuples, ncsubjVerbTuples, prepVerbTuples, obj2VerbTuples, 
                dobjVerbInformation, ncsubjVerbInformation, prepVerbInformation, obj2VerbInformation, 
                verbFrequencies, verbVectorElementSums, verbVectorElementAbsValueSums, verbVectorLengthSquares, 
                (dimensionalityReductionType==DimensionalityReductionType.SVD ? dimensionalityReductionParameter.intValue() : allVerbFeatureCount), 
                allVerbTypeCount, semanticCategoriesVerbs);
        evaluate(allAdjectiveQuestionsWithAllAnswers, adjNounTuples, new HashMap<String, HashMap<String, Double>>(), new HashMap<String, HashMap<String, Double>>(), new HashMap<String, HashMap<String, Double>>(), 
                nounAdjTuples, new HashMap<String, Long>(), new HashMap<String, Long>(), new HashMap<String, Long>(), 
                nounAdjInformation, new HashMap<String, Double>(), new HashMap<String, Double>(), new HashMap<String, Double>(), 
                adjectiveFrequencies, adjectiveVectorElementSums, adjectiveVectorElementAbsValueSums, adjectiveVectorLengthSquares, 
                (dimensionalityReductionType==DimensionalityReductionType.SVD ? dimensionalityReductionParameter.intValue() : allAdjFeatureCount), 
                allAdjectiveTypeCount, semanticCategoriesAdjectives);
        evaluate(allAdverbQuestionsWithAllAnswers, advWordTuples, new HashMap<String, HashMap<String, Double>>(), new HashMap<String, HashMap<String, Double>>(), new HashMap<String, HashMap<String, Double>>(), 
                wordAdvTuples, new HashMap<String, Long>(), new HashMap<String, Long>(), new HashMap<String, Long>(), 
                wordAdvInformation, new HashMap<String, Double>(), new HashMap<String, Double>(), new HashMap<String, Double>(), 
                adverbFrequencies, adverbVectorElementSums, adverbVectorElementAbsValueSums, adverbVectorLengthSquares, 
                (dimensionalityReductionType==DimensionalityReductionType.SVD ? dimensionalityReductionParameter.intValue() : allAdvFeatureCount), 
                allAdverbTypeCount, semanticCategoriesAdverbs);

    }
    
    
    /**
     * This function computes the semantic similarity of all the word pairs of a given POS in the selected test dataset.
     * @param <V> the type of keys in @param tuplesMap1
     * @param <X> the type of keys in @param tuplesMap2
     * @param <Y> the type of keys in @param tuplesMap3
     * @param <Z> the type of keys in @param tuplesMap4
     * @param allQuestionsWithAllAnswersMap a map, in which the question words are stored with all the possible answers
     * @param tuplesMap1 a map, in which (word, feature) pairs of a given type are stored
     * @param tuplesMap2 a map, in which (word, feature) pairs of a given type are stored
     * @param tuplesMap3 a map, in which (word, feature) pairs of a given type are stored
     * @param tuplesMap4 a map, in which (word, feature) pairs of a given type are stored
     * @param featureMap1 a map, in which the features of a given type are stored together with the number of word types with which they occur
     * @param featureMap2 a map, in which the features of a given type are stored together with the number of word types with which they occur
     * @param featureMap3 a map, in which the features of a given type are stored together with the number of word types with which they occur
     * @param featureMap4 a map, in which the features of a given type are stored together with the number of word types with which they occur
     * @param informationMap1 a map, in which the information content of features of a given type are stored
     * @param informationMap2 a map, in which the information content of features of a given type are stored
     * @param informationMap3 a map, in which the information content of features of a given type are stored
     * @param informationMap4 a map, in which the information content of features of a given type are stored
     * @param wordFrequencies the map containing the frequency of words of a given POS
     * @param vectorElementSums a map, in which the sum of the elements in feature vectors of a given type are stored
     * @param vectorElementAbsValueSums a map, in which the sum of the absolute value of the elements in feature vectors of a given type are stored
     * @param vectorLengthSquares a map, in which the square of the length of feature vectors of a given type are stored
     * @param numberOfAllFeatures the number of all features of the given type
     * @param allWordTypeCount the number of all words with the given POS, for which a word vector in the extracted information exists
     * @param semanticCategoryMap the map, in which the semantic similarity of the word pairs will be stored
     */
    public static <V, X, Y, Z> void evaluate(HashMap<String, ArrayList<ArrayList<String>>> allQuestionsWithAllAnswersMap, HashMap<String, HashMap<V, Double>> tuplesMap1, 
            HashMap<String, HashMap<X, Double>> tuplesMap2, HashMap<String, HashMap<Y, Double>> tuplesMap3, HashMap<String, HashMap<Z, Double>> tuplesMap4, 
            HashMap<V, Long> featureMap1, HashMap<X, Long> featureMap2, HashMap<Y, Long> featureMap3, HashMap<Z, Long> featureMap4, 
            HashMap<V, Double> informationMap1, HashMap<X, Double> informationMap2, HashMap<Y, Double> informationMap3, HashMap<Z, Double> informationMap4, 
            HashMap<String, Double> wordFrequencies, HashMap<String, Double> vectorElementSums, HashMap<String, Double> vectorElementAbsValueSums, HashMap<String, Double> vectorLengthSquares, 
            long numberOfAllFeatures, long allWordTypeCount, HashMap<String, HashMap<String, Double>> semanticCategoryMap){
        
        int i=0;
        double d=0d;

        //This loop goes through all the question words.
        for(Map.Entry<String, ArrayList<ArrayList<String>>> word1Entry: allQuestionsWithAllAnswersMap.entrySet()){
            
            String word1 = word1Entry.getKey();
            
            HashMap<V, Double> word1Map1 = tuplesMap1.get(word1);
            HashMap<X, Double> word1Map2 = tuplesMap2.get(word1);
            HashMap<Y, Double> word1Map3 = tuplesMap3.get(word1);
            HashMap<Z, Double> word1Map4 = tuplesMap4.get(word1);
            HashMap<String, Double> semanticCategory = new HashMap<String, Double>();
            
            //This loop goes thwough all the possible answers for the question word.
            for(ArrayList<String> list: word1Entry.getValue()){
                
                for(String word2: list){
                    
                    if(vectorLengthSquares.get(word1)==0d || vectorLengthSquares.get(word2)==0d){
                        
                        semanticCategory.put(word2, 0d);
                        
                    }else{
                    
                        HashMap<V, Double> word2Map1 = tuplesMap1.get(word2);
                        HashMap<X, Double> word2Map2 = tuplesMap2.get(word2);
                        HashMap<Y, Double> word2Map3 = tuplesMap3.get(word2);
                        HashMap<Z, Double> word2Map4 = tuplesMap4.get(word2);

                        Double vectorMean1 = null;
                        Double vectorMean2 = null;

                        Double vectorElementSum1 = vectorElementSums.get(word1);
                        if(vectorElementSum1!=null){
                            vectorMean1 = vectorElementSum1/numberOfAllFeatures;
                        }

                        Double vectorElementSum2 = vectorElementSums.get(word2);
                        if(vectorElementSum2!=null){
                            vectorMean2 = vectorElementSum2/numberOfAllFeatures;
                        }

                        /*
                         * The similarity score of word1 and wors2 is computed. The pearson and spearman similarty of the numerical method is calculated 
                         * differently than all the other similarities.
                         */
                        if(similarityMeasure==SimilarityMeasure.Pears){
                            d=pearsonCorrelationForHashMaps(word1Map1, word1Map2, word1Map3, word1Map4, word2Map1, word2Map2, word2Map3, word2Map4, featureMap1, featureMap2, featureMap3, featureMap4, vectorMean1, vectorMean2, true);
                        }else if(similarityMeasure==SimilarityMeasure.Spearm){
                            V[] featureList1 = (V[]) featureMap1.keySet().toArray();
                            X[] featureList2 = (X[]) featureMap2.keySet().toArray();
                            Y[] featureList3 = (Y[]) featureMap3.keySet().toArray();
                            Z[] featureList4 = (Z[]) featureMap4.keySet().toArray();
                            double[] rankMap1 = rankMapForSpearmanCorrelationFromArray(buildConcatenatedFeatureVector(word1Map1, word1Map2, word1Map3, word1Map4, featureList1, featureList2, featureList3, featureList4));
                            double[] rankMap2 = rankMapForSpearmanCorrelationFromArray(buildConcatenatedFeatureVector(word2Map1, word2Map2, word2Map3, word2Map4, featureList1, featureList2, featureList3, featureList4));
                            d=pearsonCorrelationForArrays(rankMap1, rankMap2, ((double) rankMap1.length+1d)/2d, ((double) rankMap2.length+1d)/2d);
                        }else if(similarityMeasure==SimilarityMeasure.CorrKiela){
                            d=pearsonCorrelationForHashMaps(word1Map1, word1Map2, word1Map3, word1Map4, word2Map1, word2Map2, word2Map3, word2Map4, featureMap1, featureMap2, featureMap3, featureMap4, vectorMean1, vectorMean2, false)/(Math.sqrt(vectorLengthSquares.get(word1))*Math.sqrt(vectorLengthSquares.get(word2)));
                        }else if(similarityMeasure==SimilarityMeasure.Covariance){
                            d=pearsonCorrelationForHashMaps(word1Map1, word1Map2, word1Map3, word1Map4, word2Map1, word2Map2, word2Map3, word2Map4, featureMap1, featureMap2, featureMap3, featureMap4, vectorMean1, vectorMean2, false);
                        }else if(similarityMeasure==SimilarityMeasure.Rbo){
                            d=rbo(word1Map1, word1Map2, word1Map3, word1Map4, word2Map1, word2Map2, word2Map3, word2Map4);
                        }else if(similarityMeasure==SimilarityMeasure.Dfvmb || 
                                pearsModPattern.matcher(similarityMeasureString).matches() || similarityMeasureString.matches("PenroseShape(Mod_.*)?") || 
                                pearsCombPattern.matcher(similarityMeasureString).matches() || pearsMbAdjCosModPattern.matcher(similarityMeasureString).matches() || 
                                pearsMbModPattern.matcher(similarityMeasureString).matches() || pearsMbAdjCosPfModPattern.matcher(similarityMeasureString).matches()){
                            //The numerical similarity of the feature vectors of word1 and word2 are calculated for each type of features separately (without normalization).
                            SimilarityScoreParts similarityScoreParts1 = calculateSimilarityByRelationForDoubleZeroValuesToo(word1Map1, word2Map1, featureMap1, vectorMean1, vectorMean2, numberOfAllFeatures, informationMap1);
                            SimilarityScoreParts similarityScoreParts2 = calculateSimilarityByRelationForDoubleZeroValuesToo(word1Map2, word2Map2, featureMap2, vectorMean1, vectorMean2, numberOfAllFeatures, informationMap2);
                            SimilarityScoreParts similarityScoreParts3 = calculateSimilarityByRelationForDoubleZeroValuesToo(word1Map3, word2Map3, featureMap3, vectorMean1, vectorMean2, numberOfAllFeatures, informationMap3);
                            SimilarityScoreParts similarityScoreParts4 = calculateSimilarityByRelationForDoubleZeroValuesToo(word1Map4, word2Map4, featureMap4, vectorMean1, vectorMean2, numberOfAllFeatures, informationMap4);

                            d = calculateFinalSimilarityScore(word1, word2, similarityScoreParts1, similarityScoreParts2, similarityScoreParts3, similarityScoreParts4, 
                                    wordFrequencies, vectorElementSums, vectorElementAbsValueSums, vectorLengthSquares, numberOfAllFeatures, allWordTypeCount);
                        }else{
                            //The numerical and Lin similarity of the feature vectors of word1 and word2 are calculated for each type of features separately (without normalization).
                            SimilarityScoreParts similarityScoreParts1 = calculateSimilarityByRelation(word1Map1, word2Map1, vectorMean1, vectorMean2, numberOfAllFeatures, informationMap1);
                            SimilarityScoreParts similarityScoreParts2 = calculateSimilarityByRelation(word1Map2, word2Map2, vectorMean1, vectorMean2, numberOfAllFeatures, informationMap2);
                            SimilarityScoreParts similarityScoreParts3 = calculateSimilarityByRelation(word1Map3, word2Map3, vectorMean1, vectorMean2, numberOfAllFeatures, informationMap3);
                            SimilarityScoreParts similarityScoreParts4 = calculateSimilarityByRelation(word1Map4, word2Map4, vectorMean1, vectorMean2, numberOfAllFeatures, informationMap4);

                            d = calculateFinalSimilarityScore(word1, word2, similarityScoreParts1, similarityScoreParts2, similarityScoreParts3, similarityScoreParts4, 
                                    wordFrequencies, vectorElementSums, vectorElementAbsValueSums, vectorLengthSquares, numberOfAllFeatures, allWordTypeCount);

                        }

                        if(Double.isInfinite(d) || Double.isNaN(d)){
                            System.out.println("Infinite or NaN similarity score: " + word1 + " " + word2 + " " + d);
                            System.exit(1);
                        }

                        semanticCategory.put(word2, d);
                        
                    }
                    
                }
            }
            
            semanticCategoryMap.put(word1, semanticCategory);
            
        }
        
    }
    
    
    
    
    
    
    
    
    
    
    

    /**
     * This method calculates the performance of my method on the input synonym question dataset and prints out the results.
     */
    public static void printOutResultsSynonymQuestions(){
        
        double numberOfCorrectAnswers = 0d;
        
        //The original question words are sorted alphabetically.
        ArrayList<String> word1List = new ArrayList<String>(nounLemmas.keySet());
        Collections.sort(word1List);
        
        int numberOfQuestions = 0;
        
        //This loop iterates through the original question words.
        for(String word1: word1List){
            
            //The lemma of the original question word is determined for all the 4 possible POS.
            String nounLemma=nounLemmas.get(word1);
            String verbLemma=verbLemmas.get(word1);
            String adjectiveLemma=adjectiveLemmas.get(word1);
            String adverbLemma=adverbLemmas.get(word1);

            //All the possible answers are determined for all the 4 possible POS.
            ArrayList<ArrayList<String>> allNounAnswers = allNounQuestionsWithAllAnswers.get(nounLemma);
            ArrayList<ArrayList<String>> allVerbAnswers = allVerbQuestionsWithAllAnswers.get(verbLemma);
            ArrayList<ArrayList<String>> allAdjectiveAnswers = allAdjectiveQuestionsWithAllAnswers.get(adjectiveLemma);
            ArrayList<ArrayList<String>> allAdverbAnswers = allAdverbQuestionsWithAllAnswers.get(adverbLemma);
            
            for(int i=0;i<allNounAnswers.size();i++){
            
                //The product of the logarithm of the frequency of the words in the question is calculated for all the 4 possible POS.
                double frequencyLogProductNoun=computeFrequencyLogProduct(nounLemma, allNounAnswers.get(i), nounFrequencies);
                double frequencyLogProductVerb=computeFrequencyLogProduct(verbLemma, allVerbAnswers.get(i), verbFrequencies);
                double frequencyLogProductAdjective=computeFrequencyLogProduct(adjectiveLemma, allAdjectiveAnswers.get(i), adjectiveFrequencies);
                double frequencyLogProductAdverb=computeFrequencyLogProduct(adverbLemma, allAdverbAnswers.get(i), adverbFrequencies);

                if(createOutputFile) out.println(frequencyLogProductNoun + " " + frequencyLogProductVerb + " " + frequencyLogProductAdjective + " " + frequencyLogProductAdverb);

                /*
                 * Based on the product of the logarithm of the frequency of the words in the question, the most suitable POS for the question is selected.
                 * After this, my method is evaluated on the question and the relevant information is printed out.
                 */
                if(frequencyLogProductNoun==Math.max(Math.max(frequencyLogProductNoun, frequencyLogProductVerb), Math.max(frequencyLogProductAdjective, frequencyLogProductAdverb))){
                    numberOfCorrectAnswers+=printOutResultsSynonymQuestions(nounLemma, "(noun)", semanticCategoriesNouns.get(nounLemma), allNounAnswers.get(i));
                }else if(frequencyLogProductVerb==Math.max(Math.max(frequencyLogProductNoun, frequencyLogProductVerb), Math.max(frequencyLogProductAdjective, frequencyLogProductAdverb))){
                    numberOfCorrectAnswers+=printOutResultsSynonymQuestions(verbLemma, "(verb)", semanticCategoriesVerbs.get(verbLemma), allVerbAnswers.get(i));
                }else if(frequencyLogProductAdjective==Math.max(Math.max(frequencyLogProductNoun, frequencyLogProductVerb), Math.max(frequencyLogProductAdjective, frequencyLogProductAdverb))){
                    numberOfCorrectAnswers+=printOutResultsSynonymQuestions(adjectiveLemma, "(adjective)", semanticCategoriesAdjectives.get(adjectiveLemma), allAdjectiveAnswers.get(i));
                }else if(frequencyLogProductAdverb==Math.max(Math.max(frequencyLogProductNoun, frequencyLogProductVerb), Math.max(frequencyLogProductAdjective, frequencyLogProductAdverb))){
                    numberOfCorrectAnswers+=printOutResultsSynonymQuestions(adverbLemma, "(adverb)", semanticCategoriesAdverbs.get(adverbLemma), allAdverbAnswers.get(i));
                }
                
                numberOfQuestions++;
                
            }

        }       
        
        //The final accuracy of my method on the input synonym question dataset is printed out.
        if(createOutputFile) out.println("Accuracy: " + numberOfCorrectAnswers/numberOfQuestions);    
        if(createOutputFile) out.close();
        System.out.println(numberOfCorrectAnswers/numberOfQuestions);

    }
    
    
    
    /**
     * This function evaluates my method on a question according to the selected pos and prints out the relevant information.
     * @param word1 the question word
     * @param pos the pos of the question
     * @param semanticCategoryMap the map, which contains the semantic similarity of word pairs for the question (determined by my method)
     * @param answers a list, in which the possible answers are stored
     * @return 0 if the anser is incorrect, 1 if it is correct
     */
    public static int printOutResultsSynonymQuestions(String word1, String pos, HashMap<String, Double> semanticCategoryMap, ArrayList<String> answers){
        
        int numberOfCorrectAnswers = 0;
        
        //The word pairs are sorted according to their semantic similarity (determined by my method) in descending order.
        ArrayList<ComparablePair<String, Double>> word2WithScoreList = new ArrayList<ComparablePair<String, Double>>();
        for(String answer: answers){
            word2WithScoreList.add(new ComparablePair<String, Double>(answer, semanticCategoryMap.get(answer)));
        }
        Collections.sort(word2WithScoreList);
        Collections.reverse(word2WithScoreList);
        
        if(createOutputFile) out.println(word1 + "\t" + answers.get(0) + "\t" + pos);
        for(int i=0;i<word2WithScoreList.size();i++){
            ComparablePair<String, Double> word2WithScore = word2WithScoreList.get(i);
            if(createOutputFile) out.println(word1 + "\t" + word2WithScore.first + "\t" + word2WithScore.second);
        }
        
        //If the answer is correct, the numberOfCorrectAnswers is incremented.
        if(answers.get(0).equals(word2WithScoreList.get(0).first)){
            numberOfCorrectAnswers++;
        }
        
        if(createOutputFile) out.println();
        
        return numberOfCorrectAnswers;
        
    }
    
    
    /**
     * This method calculates the performance of my method on the input word pair similarity dataset and prints out the results.
     */
    public static void printOutResultsWordPairSimilarities(){
        
        HashMap<ComparablePair<String, String>, Double> ownScores = new HashMap<ComparablePair<String, String>, Double>();
        HashMap<ComparablePair<String, String>, Double> goldStandardScores = new HashMap<ComparablePair<String, String>, Double>();
        
        //The original question words are sorted alphabetically.
        ArrayList<String> word1List = new ArrayList<String>(nounLemmas.keySet());
        Collections.sort(word1List);
        
        //This loop iterates through the original question words (the first word in the word pair).
        for(String word1: word1List){
            
            //The lemma of the original question word is determined for all the 4 possible POS.
            String nounLemma=nounLemmas.get(word1);
            String verbLemma=verbLemmas.get(word1);
            String adjectiveLemma=adjectiveLemmas.get(word1);
            String adverbLemma=adverbLemmas.get(word1);

            //All the possible answers are determined for all the 4 possible POS (the possible second word in the word pair, there might be more than one).
            ArrayList<ArrayList<String>> allNounAnswers = allNounQuestionsWithAllAnswers.get(nounLemma);
            ArrayList<ArrayList<String>> allVerbAnswers = allVerbQuestionsWithAllAnswers.get(verbLemma);
            ArrayList<ArrayList<String>> allAdjectiveAnswers = allAdjectiveQuestionsWithAllAnswers.get(adjectiveLemma);
            ArrayList<ArrayList<String>> allAdverbAnswers = allAdverbQuestionsWithAllAnswers.get(adverbLemma);
            
            //This loop iterates through all the possible answers for the question word word1.
            for(int i=0;i<allNounAnswers.size();i++){
                
                //The product of the logarithm of the frequency of the words in the question is calculated for all the 4 possible POS.
                double frequencyLogProductNoun=computeFrequencyLogProduct(nounLemma, allNounAnswers.get(i).get(0), nounFrequencies);
                double frequencyLogProductVerb=computeFrequencyLogProduct(verbLemma, allVerbAnswers.get(i).get(0), verbFrequencies);
                double frequencyLogProductAdjective=computeFrequencyLogProduct(adjectiveLemma, allAdjectiveAnswers.get(i).get(0), adjectiveFrequencies);
                double frequencyLogProductAdverb=computeFrequencyLogProduct(adverbLemma, allAdverbAnswers.get(i).get(0), adverbFrequencies);
                                
                if(createOutputFile) out.println(frequencyLogProductNoun + " " + frequencyLogProductVerb + " " + frequencyLogProductAdjective + " " + frequencyLogProductAdverb);
                
                double ownScore=0d;
                double goldStandardScore=0d;
                String word2=null;
                String pos=null;
                String lemma1=null;
                            
                /*
                * Based on the product of the logarithm of the frequency of the words in the question, the most suitable POS for the question is selected.
                * After this, the score returned by my method and the original score for the word pair is saved in a map.
                */
                if(frequencyLogProductNoun==Math.max(Math.max(frequencyLogProductNoun, frequencyLogProductVerb), Math.max(frequencyLogProductAdjective, frequencyLogProductAdverb))){
                    word2=allNounAnswers.get(i).get(0);
                    ownScore=semanticCategoriesNouns.get(nounLemma).get(word2);
                    goldStandardScore=allNounQuestionsWithCorrectScores.get(new ComparablePair<String, String>(nounLemma, word2));
                    pos="(noun)";
                    lemma1=nounLemma;
                }else if(frequencyLogProductVerb==Math.max(Math.max(frequencyLogProductNoun, frequencyLogProductVerb), Math.max(frequencyLogProductAdjective, frequencyLogProductAdverb))){
                    word2=allVerbAnswers.get(i).get(0);
                    ownScore=semanticCategoriesVerbs.get(verbLemma).get(word2);
                    goldStandardScore=allVerbQuestionsWithCorrectScores.get(new ComparablePair<String, String>(verbLemma, word2));
                    pos="(verb)";
                    lemma1=verbLemma;
                }else if(frequencyLogProductAdjective==Math.max(Math.max(frequencyLogProductNoun, frequencyLogProductVerb), Math.max(frequencyLogProductAdjective, frequencyLogProductAdverb))){
                    word2=allAdjectiveAnswers.get(i).get(0);
                    ownScore=semanticCategoriesAdjectives.get(adjectiveLemma).get(word2);
                    goldStandardScore=allAdjectiveQuestionsWithCorrectScores.get(new ComparablePair<String, String>(adjectiveLemma, word2));
                    pos="(adjective)";
                    lemma1=adjectiveLemma;
                }else if(frequencyLogProductAdverb==Math.max(Math.max(frequencyLogProductNoun, frequencyLogProductVerb), Math.max(frequencyLogProductAdjective, frequencyLogProductAdverb))){
                    word2=allAdverbAnswers.get(i).get(0);
                    ownScore=semanticCategoriesAdverbs.get(adverbLemma).get(word2);
                    goldStandardScore=allAdverbQuestionsWithCorrectScores.get(new ComparablePair<String, String>(adverbLemma, word2));
                    pos="(adverb)";
                    lemma1=adverbLemma;
                }
                if(createOutputFile) out.println(lemma1 + "\t" + word2 + "\t" + pos + "\t" + goldStandardScore + "\t" + ownScore);
                ownScores.put(new ComparablePair<String, String>(lemma1, word2), ownScore);
                goldStandardScores.put(new ComparablePair<String, String>(lemma1, word2), goldStandardScore);
                
            }

        }
        
        //The Pearson corerlation of my scores and the original scores are calculated.
        double pearson = pearsonCorrelationForEvaluation(ownScores, goldStandardScores);
        
        //My scores and the original scores are converted into ranks, and their Spearman correlation is calculated.
        double spearman = pearsonCorrelationForEvaluation(rankMapForSpearmanCorrelationFromHashMap(ownScores), rankMapForSpearmanCorrelationFromHashMap(goldStandardScores));
        
        //The harmonic mean of the Pearson and Spearman correlation scores is calculated
        double hmps = 2*pearson*spearman/(Math.abs(pearson)+Math.abs(spearman));
        
        //The final accuracy of my method on the input word pair similarity dataset is printed out.
        if(createOutputFile){
            out.println("\nPearson correlation coefficient: " + pearson);
            out.println("Spearman correlation coefficient: " + spearman);
            out.println("Modified harmonic mean of Pearson and Spearman correlation: " + hmps);
            out.close();
        }
        System.out.println(pearson + "\t" + spearman + "\t" + hmps);

    }
    
    
    /**
     * This function calculates the product of the logarithm of the frequency of the words in the question for the selected POS, in case of synonym question dataset.
     * @param word1 the question word
     * @param allAnswers a list, which contains all the possible answers for the question
     * @param frequencyMap a map, which contains the frequencies of words of the selected POS
     * @return the product of the logarithm of the frequency of the words in the question for the selected POS
     */
    public static double computeFrequencyLogProduct(String word1, ArrayList<String> allAnswers, HashMap<String, Double> frequencyMap){
        
        /*
         * A smoothing parameter of 1.0001 is used instead of 1, so that a word with frequency 0 does not make the product 0. This is useful if one of the words does not occur in
         * the corpus at all, since then the product for every POS would be 0, and no other evidence in favor of any POS would count. 
         */
        double smoothingParameter=1.0001d;
        double frequencyLogProduct=1d;
        
        if(frequencyMap.containsKey(word1)){
            frequencyLogProduct*=Math.log(smoothingParameter+frequencyMap.get(word1));
        }else{
            frequencyLogProduct*=Math.log(smoothingParameter);
        }
        
        for(int i=0; i<4; i++){
        
            if(frequencyMap.containsKey(allAnswers.get(i))){
                frequencyLogProduct*=Math.log(smoothingParameter+frequencyMap.get(allAnswers.get(i)));
            }else{
                frequencyLogProduct*=Math.log(smoothingParameter);
            }
            
        }
        
        return frequencyLogProduct;
        
    }
    
    /**
     * This function calculates the product of the logarithm of the frequency of the words in the question for the selected POS, for input word pairs in case of the input word pair similarity datasets.
     * @param word1 the question word
     * @param word2 the second word in the pair
     * @param frequencyMap a map, which contains the frequencies of words of the selected POS
     * @return the product of the logarithm of the frequency of the words in the question for the selected POS
     */
    public static double computeFrequencyLogProduct(String word1, String word2, HashMap<String, Double> frequencyMap){
        
        /*
         * A smoothing parameter of 1.0001 is used instead of 1, so that a word with frequency 0 does not make the product 0. This is useful if one of the words does not occur in
         * the corpus at all, since then the product for every POS would be 0, and no other evidence in favor of any POS would count. 
         */
        double smoothingParameter=1.0001d;
        double frequencyLogProduct=1d;
        
        if(frequencyMap.containsKey(word1)){
            frequencyLogProduct*=Math.log(smoothingParameter+frequencyMap.get(word1));
        }else{
            frequencyLogProduct*=Math.log(smoothingParameter);
        }
        
        if(frequencyMap.containsKey(word2)){
            frequencyLogProduct*=Math.log(smoothingParameter+frequencyMap.get(word2));
        }else{
            frequencyLogProduct*=Math.log(smoothingParameter);
        }
        
        return frequencyLogProduct;
        
    }
    
    
    /**
     * This function converts the scores stored in @param scoreMap into ranks.
     * @param scoreMap a map, which contains instances of V with scores
     * @return a map, which contains the instances of V with ranks
     */
    public static <V> HashMap<V, Double> rankMapForSpearmanCorrelationFromHashMap(HashMap<V, Double> scoreMap){
        
        //A list is created from the instances of V with scores.
        ArrayList<ComparablePair<V, Double>> scoreList = new ArrayList<ComparablePair<V, Double>>();
        for(Map.Entry<V, Double> scoreEntry: scoreMap.entrySet()){
            scoreList.add(new ComparablePair<V, Double>(scoreEntry.getKey(), scoreEntry.getValue()));
        }
        
        //The scores are sorted in descending order.
        Collections.sort(scoreList);
        Collections.reverse(scoreList);
        
        ArrayList<ComparablePair<V, Double>> rankArray = new ArrayList<ComparablePair<V, Double>>();
        
        //The scores are converted into ranks. Equal values have ascending ranks here.
        for(int i=0;i<scoreList.size();i++){
            rankArray.add(new ComparablePair<V, Double>(scoreList.get(i).first, i+1.0));
        }
        
        //Equal values are assigned equal ranks. The average of the original ranks is assigned to the equal values.
        for(int i=0;i<scoreList.size();i++){
            
            int j=i+1;
            double sum=rankArray.get(i).second;
            int count=1;
            
            while(j<scoreList.size() && scoreList.get(i).second.equals(scoreList.get(j).second)){
                sum+=rankArray.get(j).second;
                count++;
                j++;
            }
            
            if(count>1){
                for(int k=i;k<j;k++){
                    rankArray.set(k, new ComparablePair<V, Double>(scoreList.get(k).first, sum/count));
                }
                i=j-1;
            }
            
        }
        
        //A map is created from the ranked instances of V.
        HashMap<V, Double> rankMap = new HashMap<V, Double>();
        for(int i=0;i<scoreList.size();i++){
            rankMap.put(rankArray.get(i).first, rankArray.get(i).second);
        }
        
        return rankMap;
        
    }
    
    
    /**
     * This function computes the Pearson correlation of two maps containing instances of V with scores. First the expected values are calculated, then the standard deviations and the covariance,
     * finally the Pearson correlation is calculated from the other values.
     * @param scoreMap1 the first map containing instances of V with scores
     * @param scoreMap2 the second map containing instances of V with scores
     * @return the Pearson correlation of the two maps
     */
    public static <V> double pearsonCorrelationForEvaluation(HashMap<V, Double> scoreMap1, HashMap<V, Double> scoreMap2){
        
        double expGoldStandard=0d;
        double expOwn=0d;
        double covariance=0d;
        double stdevGoldStandard=0d;
        double stdevOwn=0d;
        
        for(Map.Entry<V, Double> score1Entry: scoreMap1.entrySet()){
            expGoldStandard+=scoreMap2.get(score1Entry.getKey());
            expOwn+=score1Entry.getValue();
        }
        
        int wordPairCount=scoreMap1.size();
        expGoldStandard/=wordPairCount;
        expOwn/=wordPairCount;
        
        
        for(Map.Entry<V, Double> score1Entry: scoreMap1.entrySet()){
            V score1Key = score1Entry.getKey();
            Double score1Value = score1Entry.getValue();
            covariance+=(scoreMap2.get(score1Key)-expGoldStandard)*(score1Value-expOwn);
            stdevGoldStandard+=Math.pow(scoreMap2.get(score1Key)-expGoldStandard, 2d);
            stdevOwn+=Math.pow(score1Value-expOwn, 2d);
        }
        

        return covariance/(Math.sqrt(stdevGoldStandard)*Math.sqrt(stdevOwn));
        
    }
    
    
}
