package dsm.parameters;

import static dsm.parameters.MiscParam.*;
import java.util.HashMap;
import java.util.HashSet;

/**
 *
 * @author Dobó
 */
public class Smooth {
    
    public static enum SmoothingType {NoSmooth, FreqKNS, FreqMKNS, FreqMDKNSPOMD, WeightKNS};
    public static SmoothingType smoothingType = SmoothingType.NoSmooth;
    public static boolean MKNSAlsoJustForInputWords = true;
    public static Double KNSParameters[] = new Double[]{null, null, null, null}; //D, D1, D2, D3
    
    
    
    
    
    /**
     * This function is used for smoothing the (word, feature) pair values. It can either be used for smoothing the raw frequency counts or the weights, 
     * depending on when it is called. It can also be employed on all features or only on non-null features.
     */
    public static void applySmoothing(){
        
        HashSet<String> allWordSet;
            
        if(smoothingType==SmoothingType.FreqMKNS && !MKNSAlsoJustForInputWords){
            allWordSet = new HashSet<String>();
            allWordSet.addAll(objVerbTuples.keySet());
            allWordSet.addAll(subjVerbTuples.keySet());
            allWordSet.addAll(nounNcmodTuples.keySet());
            allWordSet.addAll(allNouns);
        }else{
            allWordSet = allNouns;
        }
        
        applySmoothing(objVerbTuples, allWordSet, verbWithObjectFrequencies, nounKNSNCounts, verbObjectTuplesKNSNCounts, allNounKNSNCounts);
        applySmoothing(subjVerbTuples, allWordSet, verbWithSubjectFrequencies, nounKNSNCounts, verbSubjectTuplesKNSNCounts, allNounKNSNCounts);
        applySmoothing(nounNcmodTuples, allWordSet, ncmodWithNounFrequencies, nounKNSNCounts, ncmodNounTuplesKNSNCounts, allNounKNSNCounts);
        
        
        if(smoothingType==SmoothingType.FreqMKNS && !MKNSAlsoJustForInputWords){
            allWordSet = new HashSet<String>();
            allWordSet.addAll(verbDobjTuples.keySet());
            allWordSet.addAll(verbNcsubjTuples.keySet());
            allWordSet.addAll(verbPrepTuples.keySet());
            allWordSet.addAll(verbObj2Tuples.keySet());
            allWordSet.addAll(allVerbs);
        }else{
            allWordSet = allVerbs;
        }
        
        applySmoothing(verbDobjTuples, allWordSet, dobjWithVerbFrequencies, verbKNSNCounts, dobjVerbTuplesKNSNCounts, allVerbKNSNCounts);
        applySmoothing(verbNcsubjTuples, allWordSet, ncsubjWithVerbFrequencies, verbKNSNCounts, ncsubjVerbTuplesKNSNCounts, allVerbKNSNCounts);
        applySmoothing(verbPrepTuples, allWordSet, prepWithVerbFrequencies, verbKNSNCounts, prepVerbTuplesKNSNCounts, allVerbKNSNCounts);
        applySmoothing(verbObj2Tuples, allWordSet, obj2WithVerbFrequencies, verbKNSNCounts, obj2VerbTuplesKNSNCounts, allVerbKNSNCounts);
        
        
        if(smoothingType==SmoothingType.FreqMKNS && !MKNSAlsoJustForInputWords){
            allWordSet = new HashSet<String>();
            allWordSet.addAll(adjNounTuples.keySet());
            allWordSet.addAll(allAdjectives);
        }else{
            allWordSet = allAdjectives;
        }
        
        applySmoothing(adjNounTuples, allWordSet, nounWithAdjFrequencies, adjectiveKNSNCounts, nounAdjTuplesKNSNCounts, allAdjectiveKNSNCounts);
        
        
        if(smoothingType==SmoothingType.FreqMKNS && !MKNSAlsoJustForInputWords){
            allWordSet = new HashSet<String>();
            allWordSet.addAll(advWordTuples.keySet());
            allWordSet.addAll(allAdverbs);
        }else{
            allWordSet = allAdverbs;
        }
        
        applySmoothing(advWordTuples, allWordSet, wordWithAdvFrequencies, adverbKNSNCounts, wordAdvTuplesKNSNCounts, allAdverbKNSNCounts);
        
    }
    
    
    
    
    /**
     * This function is used for smoothing the (word, feature) pair values in @param tuplesMap.
     * @param <T> the type of keys in @param tuplesMap
     * @param <V> the type of values in @param tuplesMap
     * @param tuplesMap the map containing the (word, feature) pairs
     * @param allWordSet the set containing all the input words of the given POS
     * @param frequency2Map the map containing the frequency of features
     * @param word1KNSNCountsMap the map containing the KNSN counts for the words
     * @param word2KNSNCountsMap the map containing the KNSN counts for the features
     * @param allKNSNCounts the array containing the allKNSN counts for the given feature type
     */
    public static <T, V> void applySmoothing(HashMap<T, HashMap<V, Double>> tuplesMap, HashSet<T> allWordSet, HashMap<V, Double> frequency2Map, 
            HashMap<T, Long[]> word1KNSNCountsMap, HashMap<V, Long[]> word2KNSNCountsMap, Long[] allKNSNCounts){
        
        for(T word1: allWordSet){
            
            HashMap<V, Double> word1Table = tuplesMap.get(word1);
            if(word1Table==null){
                word1Table = new HashMap<V, Double>();
            }
            
            Long[] word1KNSNCounts = word1KNSNCountsMap.get(word1);
            if(word1KNSNCounts==null){
                word1KNSNCounts = new Long[]{0l, 0l, 0l, 0l};
            }
            
            for(V word2: frequency2Map.keySet()){
                
                Double originalValue = word1Table.get(word2);
                if(originalValue==null){
                    originalValue = 0d;
                }
                
                Long[] word2KNSNCounts = word2KNSNCountsMap.get(word2);
                
                double discountedValue = 0d;
                double interpolationTerm = 0d;
                
                if((smoothingType==SmoothingType.FreqKNS && originalValue > 0d) || smoothingType==SmoothingType.WeightKNS){
                   discountedValue = originalValue-KNSParameters[0];
                }else if(smoothingType==SmoothingType.FreqMKNS || smoothingType==SmoothingType.FreqMDKNSPOMD){
                    if(originalValue==1d){
                        discountedValue = originalValue-KNSParameters[1];
                    }else if(originalValue==2d){
                        discountedValue = originalValue-KNSParameters[2];
                    }else if(originalValue>=3d){
                        discountedValue = originalValue-KNSParameters[3];
                    }else if(originalValue!=0d){
                        System.out.println("There is such a frequency that is neither ==0, ==1, ==2 or >=3: " + originalValue);
                        System.exit(1);
                    }
                }
                
                if(smoothingType!=SmoothingType.WeightKNS && discountedValue < 0d){
                    System.out.println("Discounted value < 0: " + discountedValue);
                    System.exit(1);
                }
                
                if(smoothingType==SmoothingType.FreqKNS || smoothingType==SmoothingType.WeightKNS){
                   interpolationTerm = KNSParameters[0]*word1KNSNCounts[0]*word2KNSNCounts[0]/allKNSNCounts[0];
                }else if(smoothingType==SmoothingType.FreqMKNS){
                    interpolationTerm = (KNSParameters[1]*word1KNSNCounts[1]+KNSParameters[2]*word1KNSNCounts[2]+KNSParameters[3]*word1KNSNCounts[3])*word2KNSNCounts[0]/allKNSNCounts[0];
                }else if(smoothingType==SmoothingType.FreqMDKNSPOMD){
                    interpolationTerm = (KNSParameters[1]*word1KNSNCounts[1]+KNSParameters[2]*word1KNSNCounts[2]+KNSParameters[3]*word1KNSNCounts[3])*
                            (KNSParameters[1]*word2KNSNCounts[1]+KNSParameters[2]*word2KNSNCounts[2]+KNSParameters[3]*word2KNSNCounts[3])/
                            (KNSParameters[1]*allKNSNCounts[1]+KNSParameters[2]*allKNSNCounts[2]+KNSParameters[3]*allKNSNCounts[3]);
                }else{
                    System.out.println("Wrong smoothingType: " + smoothingType);
                    System.exit(1);
                }
                
                double smoothedValue = discountedValue+interpolationTerm;
                
                if(smoothedValue!=0d){
                    word1Table.put(word2, smoothedValue);
                    if(smoothingType==SmoothingType.FreqMKNS){
                        frequency2Map.put(word2, frequency2Map.get(word2)+smoothedValue-originalValue);
                    }
                }else if(originalValue!=0d){
                    System.out.println("Smoothed value is 0 when original value is not 0: " + originalValue + " " + smoothedValue);
                    System.exit(1);
                }
                
            }
            
            if(!word1Table.isEmpty()){
                tuplesMap.put(word1, word1Table);
            }
            
        }
        
    }
    
    
}
