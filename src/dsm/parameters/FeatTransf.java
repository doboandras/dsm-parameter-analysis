package dsm.parameters;

import static dsm.util.MiscUtil.lb;
import static dsm.parameters.MiscParam.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import util.ComparablePair;

/**
 * The class representing the feature transformation (FeatTransf) parameter of the DSM.
 * 
 * <br><br>
 * 
 * Usage of this parameter:
 * 
 * <br><br>
 * 
 * String FeatTransf: the feature transformation used
 * <ul>
 * <li> "NoTransf": no feature transformation
 * <li> FeatureTransformationType FeatureTransformationFunction: the type of feature transformation, with a feature transformation function
 * </ul>
 * 
 * <br>
 * 
 * String FeatureTransformationType: the type of feature transformation
 * <ul>
 * <li> "Freq": transformation of feature counts
 * <li> "WeightBefNorm": transformation of feature weights before possible smoothing and normalization
 * <li> "WeightAftNorm": transformation of feature weights after possible smoothing and normalization
 * </ul>
 * 
 * <br>
 * 
 * String FeatureTransformationFunction: the type of feature transformation
 * <ul>
 * <li> "Lb": modified logarithm function
 * <li> "Sqrt": modified square root function
 * <li> "Sq": modified square function
 * <li> "Cu": modified cubic function
 * <li> "Sigm": sigmoid function
 * <li> "P1D2": plus 1 divided by 2 function
 * <li> "Rank": rank function
 * </ul>
 * 
 * <br>
 * 
 * For more information about the transformation functions, see Section 4.2.3 of the dissertation.
 * 
 * @author Dobó
 */
public class FeatTransf {
    
    public static enum FeatureTransformationType {NoTransf, Freq, FreqAftSmooth, 
        WeightBefNorm, WeightAftSmooth, WeightAftNorm};
    public static FeatureTransformationType featureTransformationType = FeatureTransformationType.NoTransf;
    public static enum FeatureTransformationFunction {Lb, Sqrt, Sq, Cu, Sigm, P1D2, Rank};
    public static FeatureTransformationFunction featureTransformationFunction = null;
    
    
    
    
    /**
     * This function is used for transforming the (word, feature) pair weights using a transformation function.
     * It can either be used for transforming the weights before or after smoothing, 
     * depending on when it is called.
     * @param ftType the type of feature transformation (determining when and on which values the transformation happens)
     * @param ftFunction the feature transformation function to be used
     */
    public static void applyFeatureTransformation(FeatureTransformationType ftType, FeatureTransformationFunction ftFunction){
        
        if(ftFunction==FeatureTransformationFunction.Rank){
            
            HashSet<String> allWordSet;
            
            if(ftType==FeatureTransformationType.Freq || ftType==FeatureTransformationType.FreqAftSmooth){
                allWordSet = new HashSet<String>();
                allWordSet.addAll(objVerbTuples.keySet());
                allWordSet.addAll(subjVerbTuples.keySet());
                allWordSet.addAll(nounNcmodTuples.keySet());
                allWordSet.addAll(allNouns);
            }else{
                allWordSet = allNouns;
            }
            
            createRankMapsForFeatureTransformation(allWordSet, objVerbTuples, subjVerbTuples, nounNcmodTuples, new HashMap<String, HashMap<String, Double>>(), nounValueToRankMaps);
            
            
            if(ftType==FeatureTransformationType.Freq || ftType==FeatureTransformationType.FreqAftSmooth){
                allWordSet = new HashSet<String>();
                allWordSet.addAll(verbDobjTuples.keySet());
                allWordSet.addAll(verbNcsubjTuples.keySet());
                allWordSet.addAll(verbPrepTuples.keySet());
                allWordSet.addAll(verbObj2Tuples.keySet());
                allWordSet.addAll(allVerbs);
            }else{
                allWordSet = allVerbs;
            }
            
            createRankMapsForFeatureTransformation(allWordSet, verbDobjTuples, verbNcsubjTuples, verbPrepTuples, verbObj2Tuples, verbValueToRankMaps);
            
            
            if(ftType==FeatureTransformationType.Freq || ftType==FeatureTransformationType.FreqAftSmooth){
                allWordSet = new HashSet<String>();
                allWordSet.addAll(adjNounTuples.keySet());
                allWordSet.addAll(allAdjectives);
            }else{
                allWordSet = allAdjectives;
            }
            
            createRankMapsForFeatureTransformation(allWordSet, adjNounTuples, new HashMap<String, HashMap<String, Double>>(), new HashMap<String, HashMap<String, Double>>(), new HashMap<String, HashMap<String, Double>>(), adjectiveValueToRankMaps);
            
            
            if(ftType==FeatureTransformationType.Freq || ftType==FeatureTransformationType.FreqAftSmooth){
                allWordSet = new HashSet<String>();
                allWordSet.addAll(advWordTuples.keySet());
                allWordSet.addAll(allAdverbs);
            }else{
                allWordSet = allAdverbs;
            }
            createRankMapsForFeatureTransformation(allWordSet, advWordTuples, new HashMap<String, HashMap<String, Double>>(), new HashMap<String, HashMap<String, Double>>(), new HashMap<String, HashMap<String, Double>>(), adverbValueToRankMaps);
            
        }
        
        double diffSum;
        
        diffSum = applyFeatureTransformation(ftType, ftFunction, objVerbTuples, objectFrequencies, verbWithObjectFrequencies, nounFrequencies, nounValueToRankMaps);
        allObjectCount += diffSum;
        allWordCount += diffSum;
        
        diffSum = applyFeatureTransformation(ftType, ftFunction, subjVerbTuples, subjectFrequencies, verbWithSubjectFrequencies, nounFrequencies, nounValueToRankMaps);
        allSubjectCount += diffSum;
        allWordCount += diffSum;
        
        diffSum = applyFeatureTransformation(ftType, ftFunction, nounNcmodTuples, nounWithNcmodFrequencies, ncmodWithNounFrequencies, nounFrequencies, nounValueToRankMaps);
        allNounNcmodCount += diffSum;
        allWordCount += diffSum;
        
        diffSum = applyFeatureTransformation(ftType, ftFunction, verbDobjTuples, verbWithDobjFrequencies, dobjWithVerbFrequencies, verbFrequencies, verbValueToRankMaps);
        allVerbDobjCount += diffSum;
        allWordCount += diffSum;
        
        diffSum = applyFeatureTransformation(ftType, ftFunction, verbNcsubjTuples, verbWithNcsubjFrequencies, ncsubjWithVerbFrequencies, verbFrequencies, verbValueToRankMaps);
        allVerbNcsubjCount += diffSum;
        allWordCount += diffSum;
        
        diffSum = applyFeatureTransformation(ftType, ftFunction, verbPrepTuples, verbWithPrepFrequencies, prepWithVerbFrequencies, verbFrequencies, verbValueToRankMaps);
        allVerbPrepCount += diffSum;
        allWordCount += diffSum;
        
        diffSum = applyFeatureTransformation(ftType, ftFunction, verbObj2Tuples, verbWithObj2Frequencies, obj2WithVerbFrequencies, verbFrequencies, verbValueToRankMaps);
        allVerbObj2Count += diffSum;
        allWordCount += diffSum;
        
        diffSum = applyFeatureTransformation(ftType, ftFunction, adjNounTuples, adjWithNounFrequencies, nounWithAdjFrequencies, adjectiveFrequencies, adjectiveValueToRankMaps);
        allAdjNounCount += diffSum;
        allWordCount += diffSum;
        
        diffSum = applyFeatureTransformation(ftType, ftFunction, advWordTuples, advWithWordFrequencies, wordWithAdvFrequencies, adverbFrequencies, adverbValueToRankMaps);
        allAdvWordCount += diffSum;
        allWordCount += diffSum;
        
        
    }
    
    
    
    
    /**
     * This function is used for transforming the (word, feature) pair feature values in @param tuplesMap using a transformation function.
     * @param <T> the type of keys in @param frequency1Map
     * @param <V> the type of keys in @param frequency2Map
     * @param ftType the type of feature transformation (determining when and on which values the transformation happens)
     * @param ftFunction the feature transformation function to be used
     * @param tuplesMap the map containing the (word, feature) pairs
     * @param frequency1Map the map containing the frequency of words in the given type of relation
     * @param frequency2Map the map containing the frequency of features in the given type of relation
     * @param frequency1MapOfGivenPOS the map containing the frequency of words of a given POS
     * @param valueToRankMaps the map containing the value to rank mappings for each word of a given POS
     * @return the sum of the differences in values due to the feature transformation in the given type of relation, when needed
     */
    public static <T, V> double applyFeatureTransformation(FeatureTransformationType ftType, FeatureTransformationFunction ftFunction, 
            HashMap<T, HashMap<V, Double>> tuplesMap, HashMap<T, Double> frequency1Map, HashMap<V, Double> frequency2Map, 
            HashMap<T, Double> frequency1MapOfGivenPOS, HashMap<T, HashMap<Double, Double>> valueToRankMaps){
        
        double originalValue;
        double transformedValue;
        double diffSum = 0d;
        
        for(Map.Entry<T, HashMap<V, Double>> word1Entry: tuplesMap.entrySet()){
            T word1 = word1Entry.getKey();
            HashMap<V, Double> word1Table = word1Entry.getValue();
            for(Map.Entry<V, Double> word2Entry: word1Table.entrySet()){
                
                originalValue = word2Entry.getValue();
                transformedValue = transformFeature(ftFunction, word1, originalValue, valueToRankMaps);
                word2Entry.setValue(transformedValue);
                
                if(ftType==FeatureTransformationType.Freq || ftType==FeatureTransformationType.FreqAftSmooth){
                    V word2 = word2Entry.getKey();
                    double diff = transformedValue-originalValue;
                    frequency1Map.put(word1, frequency1Map.get(word1)+diff);
                    frequency2Map.put(word2, frequency2Map.get(word2)+diff);
                    frequency1MapOfGivenPOS.put(word1, frequency1MapOfGivenPOS.get(word1)+diff);
                    diffSum+=diff;
                }
                
            }
        }
        
        return diffSum;
        
    }
    
    
    /**
     * This function transforms a feature value for a word using a transformation function.
     * @param <T> the type of keys in @param valueToRankMaps
     * @param ftFunction the feature transformation function to be used
     * @param word1 the word for which the featurevalue is transformed
     * @param value the feature value to be transformed
     * @param valueToRankMaps the map containing the value to rank mappings for each word of a given POS
     * @return the transformed value
     */
    public static <T> Double transformFeature(FeatureTransformationFunction ftFunction, 
            T word1, Double value, HashMap<T, HashMap<Double, Double>> valueToRankMaps){
        
        if(ftFunction==FeatureTransformationFunction.Lb){
            return Math.signum(value)*lb(Math.abs(value)+1d);
        }else if(ftFunction==FeatureTransformationFunction.Sqrt){
            return Math.signum(value)*Math.sqrt(Math.abs(value));
        }else if(ftFunction==FeatureTransformationFunction.Sq){
            return value*Math.abs(value);
        }else if(ftFunction==FeatureTransformationFunction.Cu){
            return Math.pow(value, 3d);
        }else if(ftFunction==FeatureTransformationFunction.Sigm){
            return 1d/(2d+Math.expm1(-value))-0.5d;
        }else if(ftFunction==FeatureTransformationFunction.P1D2){
            return (value+1)/2;
        }else if(ftFunction==FeatureTransformationFunction.Rank){
            return valueToRankMaps.get(word1).get(value);
        }else{
            System.out.println("No such FeatureTransformationFunction: " + ftFunction);
            throw new RuntimeException();
        }
        
    }
    
    /**
     * This function converts the feature values for each word stored in the input tuplesMaps into ranks, and stores these value to rank mappings for the word in @param valueToRankMaps.
     * @param <V> the type of features in @param tuplesMap1
     * @param <X> the type of features in @param tuplesMap2
     * @param <Y> the type of features in @param tuplesMap3
     * @param <Z> the type of features in @param tuplesMap4
     * @param allWordSet a set containing all the words of a given POS
     * @param tuplesMap1 one of the input tuplesMaps containing the (word, feature) pairs
     * @param tuplesMap2 one of the input tuplesMaps containing the (word, feature) pairs
     * @param tuplesMap3 one of the input tuplesMaps containing the (word, feature) pairs
     * @param tuplesMap4 one of the input tuplesMaps containing the (word, feature) pairs
     * @param valueToRankMaps the map to which the value to rank mappings for each word of a given POS should be stored
     */
    public static <V, X, Y, Z> void createRankMapsForFeatureTransformation(HashSet<String> allWordSet, HashMap<String, HashMap<V, Double>> tuplesMap1, HashMap<String, HashMap<X, Double>> tuplesMap2, 
            HashMap<String, HashMap<Y, Double>> tuplesMap3, HashMap<String, HashMap<Z, Double>> tuplesMap4, HashMap<String, HashMap<Double, Double>> valueToRankMaps){
        
        for(String word: allWordSet){

            //A list is created from the values in the maps for a given word.
            ArrayList<Double> valueList = new ArrayList<Double>();
            
            addValuesFromMapToList(word, tuplesMap1, valueList);
            addValuesFromMapToList(word, tuplesMap2, valueList);
            addValuesFromMapToList(word, tuplesMap3, valueList);
            addValuesFromMapToList(word, tuplesMap4, valueList);

            //The scores are sorted in descending order.
            Collections.sort(valueList);
            Collections.reverse(valueList);

            ArrayList<ComparablePair<Double, Double>> rankArray = new ArrayList<ComparablePair<Double, Double>>();

            //The scores are converted into ranks. Equal values have ascending ranks here.
            for(int i=0;i<valueList.size();i++){
                rankArray.add(new ComparablePair<Double, Double>(valueList.get(i), i+1d));
            }

            //Equal values are assigned equal ranks. The average of the original ranks is assigned to the equal values.
            for(int i=0;i<valueList.size();i++){

                int j=i+1;
                double sum=rankArray.get(i).second;
                int count=1;

                while(j<valueList.size() && valueList.get(i).equals(valueList.get(j))){
                    sum+=rankArray.get(j).second;
                    count++;
                    j++;
                }

                if(count>1){
                    for(int k=i;k<j;k++){
                        rankArray.set(k, new ComparablePair<Double, Double>(valueList.get(k), sum/count));
                    }
                    i=j-1;
                }

            }

            //A map is created from the ranked values.
            HashMap<Double, Double> valueToRankMap = new HashMap<Double, Double>();
            for(int i=0;i<valueList.size();i++){
                ComparablePair<Double, Double> pair = rankArray.get(i);
                valueToRankMap.put(pair.first, pair.second);
            }

            valueToRankMaps.put(word, valueToRankMap);
            
        }
        
    }
    
    /**
     * A function that adds all the feature values of a word from the input tuplesMap to a list
     * @param <V> The type of features in @param tuplesMap
     * @param word the input word
     * @param tuplesMap the input tuplesMap containing the (word, feature) paris
     * @param valueList the list to which the feature values should be stored
     */
    public static <V> void addValuesFromMapToList(String word, HashMap<String, HashMap<V, Double>> tuplesMap, ArrayList<Double> valueList){
        
        HashMap<V, Double> wordMap = tuplesMap.get(word);
        
        if(wordMap!=null){
        
            for(Map.Entry<V, Double> wordEntry: wordMap.entrySet()){
             
                valueList.add(wordEntry.getValue());
                
            }
            
        }
        
    }
    
    
    
}
