package dsm.parameters;

import cern.colt.matrix.tdouble.DoubleMatrix2D;
import cern.colt.matrix.tdouble.algo.DenseDoubleAlgebra;
import cern.colt.matrix.tdouble.algo.decomposition.DenseDoubleSingularValueDecomposition;
import cern.colt.matrix.tdouble.impl.DenseDoubleMatrix2D;
import cern.colt.matrix.tdouble.impl.SparseDoubleMatrix2D;
import static dsm.util.MiscUtil.*;
import static dsm.parameters.MiscParam.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import util.ComparablePair;

/**
 * The class representing the dimensionality reduction (DimRed) parameter of the DSM.
 * 
 * <br><br>
 * 
 * Usage of this parameter:
 * 
 * <br><br>
 * 
 * String DimRed: the feature transformation used
 * <ul>
 * <li> "NoDimRed": no dimensionality reduction
 * <li> DimensionalityReductionType dimensionalityReductionParameter: the type of dimensionality reduction, with a Double parameter
 * </ul>
 * 
 * <br>
 * 
 * String DimensionalityReductionType: the type of dimensionality reduction
 * <ul>
 * <li> "IslamInkpen": the dimensionality reduction technique introduced by Islam and Inkpen (2008)
 * <li> "TopNFeat": in each vector retaining only the features with the n highest weight
 * <li> "SVD": singular value decomposition
 * </ul>
 * 
 * @author Dobó
 */
public class DimRed {
    
    public static enum DimensionalityReductionType {NoDimRed, TopNFeat, IslamInkpen, SVD};
    public static DimensionalityReductionType dimensionalityReductionType = DimensionalityReductionType.NoDimRed;
    public static Double dimensionalityReductionParameter = null;
    
    public static DenseDoubleAlgebra da = new DenseDoubleAlgebra();
    
    
    /**
     * This function performs the dimensionality reduction based on the top N features (only the features with the N highest weight are kept, 
     * for all the other features, the weight is set to 0).
     * @param type the type of dimensionality reduction to be used
     * @param n the dimensionality reduction parameter (the number of features to be kept or a paramter for its calculation)
     * @param topFeaturesWithHighestWeights it indicates whether the top features are the ones with the highest weight (normally), 
     * or with the lowes weight (e.g. in case of the RANK feature transformation)
     */
    public static void dimRedTopFeatures(DimensionalityReductionType type, Double n, boolean topFeaturesWithHighestWeights){
        
        dimRedTopFeatures(type, n, topFeaturesWithHighestWeights, allNouns, objVerbTuples, subjVerbTuples, nounNcmodTuples, 
                new HashMap<String, HashMap<String, Double>>(), nounFrequencies, allNounTypeCount);
        dimRedTopFeatures(type, n, topFeaturesWithHighestWeights, allVerbs, verbDobjTuples, verbNcsubjTuples, verbPrepTuples, 
                verbObj2Tuples, verbFrequencies, allVerbTypeCount);
        dimRedTopFeatures(type, n, topFeaturesWithHighestWeights, allAdjectives, adjNounTuples, new HashMap<String, HashMap<String, Double>>(), 
                new HashMap<String, HashMap<String, Double>>(), new HashMap<String, HashMap<String, Double>>(), adjectiveFrequencies, allAdjectiveTypeCount);
        dimRedTopFeatures(type, n, topFeaturesWithHighestWeights, allAdverbs, advWordTuples, new HashMap<String, HashMap<String, Double>>(), 
                new HashMap<String, HashMap<String, Double>>(), new HashMap<String, HashMap<String, Double>>(), adverbFrequencies, allAdverbTypeCount);
        
    }
    
    
    
    
    
    /**
     * This function performs the dimensionality reduction based on the top N features (only the features with the N highest weight are kept, 
     * for all the other features, the weight is set to 0), for the given type of words.
     * @param <V> the type of keys in @param tuplesMap1
     * @param <X> the type of keys in @param tuplesMap2
     * @param <Y> the type of keys in @param tuplesMap3
     * @param <Z> the type of keys in @param tuplesMap4
     * @param type the type of dimensionality reduction to be used
     * @param n the dimensionality reduction parameter (the number of features to be kept or a paramter for its calculation)
     * @param topFeaturesWithHighestWeights it indicates whether the top features are the ones with the highest weight (normally), 
     * or with the lowes weight (e.g. in case of the RANK feature transformation)
     * @param allWordSet the set containing all the input words of the given POS
     * @param tuplesMap1 a map, in which (word, feature) pairs of a given type are stored
     * @param tuplesMap2 a map, in which (word, feature) pairs of a given type are stored
     * @param tuplesMap3 a map, in which (word, feature) pairs of a given type are stored
     * @param tuplesMap4 a map, in which (word, feature) pairs of a given type are stored
     * @param wordFrequencies the map containing the frequency of words of a given POS
     * @param allWordTypeCount the number of all words with the given POS, for which a word vector in the extracted information exists
     */
    public static <V, X, Y, Z> void dimRedTopFeatures(DimensionalityReductionType type, Double n, boolean topFeaturesWithHighestWeights, HashSet<String> allWordSet, 
            HashMap<String, HashMap<V, Double>> tuplesMap1, HashMap<String, HashMap<X, Double>> tuplesMap2, 
            HashMap<String, HashMap<Y, Double>> tuplesMap3, HashMap<String, HashMap<Z, Double>> tuplesMap4, 
            HashMap<String, Double> wordFrequencies, long allWordTypeCount){
        
        for(String word: allWordSet){
            
            HashMap<V, Double> wordMap1 = tuplesMap1.get(word);
            HashMap<X, Double> wordMap2 = tuplesMap2.get(word);
            HashMap<Y, Double> wordMap3 = tuplesMap3.get(word);
            HashMap<Z, Double> wordMap4 = tuplesMap4.get(word);
            
            ArrayList<ComparablePair<String, Double>> wordConcatenatedSortedList = new ArrayList<ComparablePair<String, Double>>();

            addFeatureValuePairsToArrayList(wordConcatenatedSortedList, wordMap1, "1_");
            addFeatureValuePairsToArrayList(wordConcatenatedSortedList, wordMap2, "2_");
            addFeatureValuePairsToArrayList(wordConcatenatedSortedList, wordMap3, "3_");
            addFeatureValuePairsToArrayList(wordConcatenatedSortedList, wordMap4, "4_");

            Collections.sort(wordConcatenatedSortedList);
            
            if(topFeaturesWithHighestWeights){
                Collections.reverse(wordConcatenatedSortedList);
            }
            
            int numberOfElementsToBeKept;
            
            if(type==DimensionalityReductionType.IslamInkpen){
                
                Double wf1 = wordFrequencies.get(word);
                Double log1;
                if(wf1==null || wf1==0l){
                    log1 = 0d;
                }else{
                    log1 = Math.log10(wf1);
                }
                
                numberOfElementsToBeKept = (int) Math.round(Math.pow(log1, 2d)*lb(allWordTypeCount)/n);
                
            }else{
                
                numberOfElementsToBeKept = n.intValue();
                
            }
            
            
            HashMap<String, Double> wordConcatenatedMapOfFirstNElements = convertFirstNElementsOfListToMap(wordConcatenatedSortedList, numberOfElementsToBeKept);
            
            
            removeNonTopFeatures(wordMap1, wordConcatenatedMapOfFirstNElements, "1_");
            removeNonTopFeatures(wordMap2, wordConcatenatedMapOfFirstNElements, "2_");
            removeNonTopFeatures(wordMap3, wordConcatenatedMapOfFirstNElements, "3_");
            removeNonTopFeatures(wordMap4, wordConcatenatedMapOfFirstNElements, "4_");
            
        }
        
    }
    
    
    
       
    /**
     * This function removes (sets their weight to 0) those (feature, value) pairs from the @param wordMap, that are not among the top N features 
     * (which are stored in @param wordConcatenatedMapOfFirstNElements.
     * @param <V> the type of keys in @param concatenatedList
     * @param wordmap ther map, from which the non-top features will be removed
     * @param wordConcatenatedMapOfFirstNElements the concatenated list containing the top N features for a word
     * @param prefix a prefix for the feature keys used in @param concatenatedList
     */
    public static <V> void removeNonTopFeatures(HashMap<V, Double> wordmap, HashMap<String, Double> wordConcatenatedMapOfFirstNElements, String prefix){
        
        if(wordmap!=null){
        
            for(Map.Entry<V, Double> entry: wordmap.entrySet()){
                if(!wordConcatenatedMapOfFirstNElements.containsKey(prefix + entry.getKey().toString())){
                    entry.setValue(0d);
                }
            }
            
        }
        
    }
    
    
    
    
    
    /**
     * This function performs the dimensionality reduction based on (truncated) Singular Value Decomposition (SVD).
     * @param n the number of dimensions to which the vectors should be truncated
     */
    public static void dimRedSVD(Double n){
        
        /*nounNcmodTuples and ncmodNounTuples are set to tuplesMap1 and featureMap1 instead of objVerbTuples and verbObjectTuples by purpose, 
        as after the svd, features will be stored in tuplesMap1, and this way the features in tuplesMap1 are String in case of words of all POS*/
        dimRedSVD(n, allNouns, nounNcmodTuples, objVerbTuples, subjVerbTuples, new HashMap<String, HashMap<String, Double>>(), 
                ncmodNounTuples, verbObjectTuples, verbSubjectTuples, new HashMap<String, Long>());
        dimRedSVD(n, allVerbs, verbDobjTuples, verbNcsubjTuples, verbPrepTuples, verbObj2Tuples, 
                dobjVerbTuples, ncsubjVerbTuples, prepVerbTuples, obj2VerbTuples);
        dimRedSVD(n, allAdjectives, adjNounTuples, new HashMap<String, HashMap<String, Double>>(), 
                new HashMap<String, HashMap<String, Double>>(), new HashMap<String, HashMap<String, Double>>(),
                nounAdjTuples, new HashMap<String, Long>(), new HashMap<String, Long>(), new HashMap<String, Long>());
        dimRedSVD(n, allAdverbs, advWordTuples, new HashMap<String, HashMap<String, Double>>(), 
                new HashMap<String, HashMap<String, Double>>(), new HashMap<String, HashMap<String, Double>>(),
                wordAdvTuples, new HashMap<String, Long>(), new HashMap<String, Long>(), new HashMap<String, Long>());
        
    }
    
    
    
    
    /**
     * This function performs the dimensionality reduction based on the top N features (only the features with the N highest weight are kept, 
     * for all the other features, the weight is set to 0), for the given type of words.
     * @param <V> the type of keys in @param tuplesMap1
     * @param <X> the type of keys in @param tuplesMap2
     * @param <Y> the type of keys in @param tuplesMap3
     * @param <Z> the type of keys in @param tuplesMap4
     * @param n the number of dimensions to which the vectors should be truncated
     * @param allWordSet the set containing all the input words of the given POS
     * @param tuplesMap1 a map, in which (word, feature) pairs of a given type are stored
     * @param tuplesMap2 a map, in which (word, feature) pairs of a given type are stored
     * @param tuplesMap3 a map, in which (word, feature) pairs of a given type are stored
     * @param tuplesMap4 a map, in which (word, feature) pairs of a given type are stored
     * @param featureMap1 a map, in which the features of a given type are stored together with the number of word types with which they occur
     * @param featureMap2 a map, in which the features of a given type are stored together with the number of word types with which they occur
     * @param featureMap3 a map, in which the features of a given type are stored together with the number of word types with which they occur
     * @param featureMap4 a map, in which the features of a given type are stored together with the number of word types with which they occur
     */
    public static <V, X, Y, Z> void dimRedSVD(Double n, HashSet<String> allWordSet, 
            HashMap<String, HashMap<V, Double>> tuplesMap1, HashMap<String, HashMap<X, Double>> tuplesMap2, 
            HashMap<String, HashMap<Y, Double>> tuplesMap3, HashMap<String, HashMap<Z, Double>> tuplesMap4, 
            HashMap<V, Long> featureMap1, HashMap<X, Long> featureMap2, HashMap<Y, Long> featureMap3, HashMap<Z, Long> featureMap4){
        
        int featureNumber = 0;
        HashMap<String, Integer> featureToFeatureNumberMap = new HashMap<String, Integer>();
        
        featureNumber += buildFeatureToFeatureNumberMap(featureToFeatureNumberMap, featureMap1, "1_", featureNumber);
        featureNumber += buildFeatureToFeatureNumberMap(featureToFeatureNumberMap, featureMap2, "2_", featureNumber);
        featureNumber += buildFeatureToFeatureNumberMap(featureToFeatureNumberMap, featureMap3, "3_", featureNumber);
        featureNumber += buildFeatureToFeatureNumberMap(featureToFeatureNumberMap, featureMap4, "4_", featureNumber);
        
        if(featureNumber>0 && !allWordSet.isEmpty()){

            DenseDoubleMatrix2D wordFeatureMatrix = new DenseDoubleMatrix2D(allWordSet.size(), featureNumber);

            int wordNumber=0;
            HashMap<Integer, String> wordNumberToWordMap = new HashMap<Integer, String>();
            
            for(String word: allWordSet){

                wordNumberToWordMap.put(wordNumber, word);

                HashMap<V, Double> wordMap1 = tuplesMap1.get(word);
                HashMap<X, Double> wordMap2 = tuplesMap2.get(word);
                HashMap<Y, Double> wordMap3 = tuplesMap3.get(word);
                HashMap<Z, Double> wordMap4 = tuplesMap4.get(word);

                addFeatureValuePairsToWordFeatureMatrix(wordFeatureMatrix, wordMap1, featureToFeatureNumberMap, "1_", wordNumber);
                addFeatureValuePairsToWordFeatureMatrix(wordFeatureMatrix, wordMap2, featureToFeatureNumberMap, "2_", wordNumber);
                addFeatureValuePairsToWordFeatureMatrix(wordFeatureMatrix, wordMap3, featureToFeatureNumberMap, "3_", wordNumber);
                addFeatureValuePairsToWordFeatureMatrix(wordFeatureMatrix, wordMap4, featureToFeatureNumberMap, "4_", wordNumber);

                wordNumber++;

            }

            tuplesMap1.clear();
            tuplesMap2.clear();
            tuplesMap3.clear();
            tuplesMap4.clear();
            
            featureMap1.clear();
            featureMap2.clear();
            featureMap3.clear();
            featureMap4.clear();
            
            for(int j=0;j<n.intValue();j++){
                featureMap1.put((V) String.valueOf(j), null);
            }

            DenseDoubleSingularValueDecomposition wordFeatureMatrixSVD = new DenseDoubleSingularValueDecomposition(wordFeatureMatrix, true, false);
            
            SparseDoubleMatrix2D truncatedS = new SparseDoubleMatrix2D(wordFeatureMatrixSVD.getS().rows(), n.intValue());
        
            for(int i=0;i<Math.min(wordFeatureMatrixSVD.getS().rows(), n.intValue());i++){
                truncatedS.set(i, i, wordFeatureMatrixSVD.getS().get(i, i));
            }

            DoubleMatrix2D truncatedWordFeatureMatrix = da.mult(wordFeatureMatrixSVD.getU(), truncatedS);

            for(int i=0;i<wordNumber;i++){

                String word = wordNumberToWordMap.get(i);
                HashMap<V, Double> wordMap = new HashMap<V, Double>();

                for(int j=0;j<n;j++){

                    wordMap.put((V) String.valueOf(j), truncatedWordFeatureMatrix.get(i, j));

                }

                tuplesMap1.put(word, wordMap);

            }
            
        }
        
        
    }
    
    
    
    public static String matrixToString(DoubleMatrix2D m){
        
        StringBuilder sb = new StringBuilder();
        
        for(int i=0;i<m.rows();i++){
            for(int j=0;j<m.columns();j++){
                sb.append("\t").append(m.getQuick(i, j));
            }
            sb.append("\n");
        }
        
        return sb.toString();
        
    }
    
    
    
    
    /**
     * This function builds the feature to feature number map using the features valus in @param featureMap, with the @param prefix added to the keys as prefix.
     * @param <V> the type of keys in @param wordMap
     * @param featureToFeatureNumberMap feature to feature number map, to which the new features should be added
     * @param featureMap a map, which contains the features to be added to @param concatenatedList
     * @param prefix a prefix for the feature keys to be added to @param featureToFeatureNumberMap
     * @param featureNumber the starting feature number
     * @return the ending feature number
     */
    public static <V> int buildFeatureToFeatureNumberMap(HashMap<String, Integer> featureToFeatureNumberMap, HashMap<V, Long> featureMap, String prefix, int featureNumber){
        
        int featureCount = 0;
        
        if(featureMap!=null){
        
            for(Map.Entry<V, Long> featureEntry: featureMap.entrySet()){
                featureToFeatureNumberMap.put(prefix + featureEntry.getKey().toString(), featureNumber+featureCount);
                featureCount++;
            }
            
        }
        
        return featureCount;
        
    }
    
    

    /**
     * This function adds the (feature, value) pairs from the @param mapToBeAdded to the (word,feature) matrix @param wordFeatureMatrix, with the @param prefix added to the keys as prefix.
     * @param <V> the type of keys in @param mapToBeAdded
     * @param wordFeatureMatrix the (word,feature) matrix, to which the (feature, value) pairs should be added
     * @param mapToBeAdded a map, which contains the (feature, value) pairs to be added to @param featureToFeatureNumberMap
     * @param featureToFeatureNumberMap a map containing the feature to feature number mappings
     * @param prefix a prefix for the feature keys to be added to @param concatenatedList
     * @param wordNumber the number of the current word
     */
    public static <V> void addFeatureValuePairsToWordFeatureMatrix(DenseDoubleMatrix2D wordFeatureMatrix, 
            HashMap<V, Double> mapToBeAdded, HashMap<String, Integer> featureToFeatureNumberMap, String prefix, int wordNumber){
        
        if(mapToBeAdded!=null){
        
            for(Map.Entry<V, Double> entryToBeAdded: mapToBeAdded.entrySet()){
                Integer featureNumber = featureToFeatureNumberMap.get(prefix + entryToBeAdded.getKey().toString());
                if(featureNumber!=null){
                    wordFeatureMatrix.set(wordNumber, featureNumber, entryToBeAdded.getValue());
                }
            }
            
        }
        
    }
    
    
}
