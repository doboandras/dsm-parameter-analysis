package dsm.parameters;

import static dsm.parameters.MiscParam.*;
import dsm.util.Aggregation.AggregationMethod;
import static dsm.util.Aggregation.aggregateVectorElements;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Dobó
 */
public class VecNorm {
    
    public static enum VectorNormalization {NN, L1, L2};
    public static VectorNormalization vectorNormalization=VectorNormalization.NN;
    

    /**
     * This function does the necessary normalization on the words vectors.
     */
    public static void normalizeVectors() {
        if (vectorNormalization == VectorNormalization.L1) {
            normalizeVectors(false);
        } else if (vectorNormalization == VectorNormalization.L2) {
            normalizeVectors(true);
        }
    }
    

    /**
     * This function normalizes the word vectors by the square of the length of feature vectors of words or the vector element sum for all types of POS.
     * @param normalizeWithVectorLength determines whether the length of feature vectors of words or the vector element sum is used for normalization
     */
    public static void normalizeVectors(boolean normalizeWithVectorLength) {
        aggregateVectorElements(normalizeWithVectorLength ? AggregationMethod.LengthSquare : AggregationMethod.AbsValueSum);
        double normalizationValue;
        
        for (String noun : allNouns) {
            if (normalizeWithVectorLength) {
                normalizationValue = Math.sqrt(nounVectorLengthSquares.get(noun));
            } else {
                normalizationValue = nounVectorElementAbsValueSums.get(noun);
            }
            if (Double.isFinite(normalizationValue) && normalizationValue != 0.0) {
                normalizeVector(objVerbTuples, noun, normalizationValue);
                normalizeVector(subjVerbTuples, noun, normalizationValue);
                normalizeVector(nounNcmodTuples, noun, normalizationValue);
            }
        }
        
        for (String verb : allVerbs) {
            if (normalizeWithVectorLength) {
                normalizationValue = Math.sqrt(verbVectorLengthSquares.get(verb));
            } else {
                normalizationValue = verbVectorElementAbsValueSums.get(verb);
            }
            if (Double.isFinite(normalizationValue) && normalizationValue != 0.0) {
                normalizeVector(verbDobjTuples, verb, normalizationValue);
                normalizeVector(verbNcsubjTuples, verb, normalizationValue);
                normalizeVector(verbPrepTuples, verb, normalizationValue);
                normalizeVector(verbObj2Tuples, verb, normalizationValue);
            }
        }
        
        for (String adjective : allAdjectives) {
            if (normalizeWithVectorLength) {
                normalizationValue = Math.sqrt(adjectiveVectorLengthSquares.get(adjective));
            } else {
                normalizationValue = adjectiveVectorElementAbsValueSums.get(adjective);
            }
            if (Double.isFinite(normalizationValue) && normalizationValue != 0.0) {
                normalizeVector(adjNounTuples, adjective, normalizationValue);
            }
        }
        
        for (String adverb : allAdverbs) {
            if (normalizeWithVectorLength) {
                normalizationValue = Math.sqrt(adverbVectorLengthSquares.get(adverb));
            } else {
                normalizationValue = adverbVectorElementAbsValueSums.get(adverb);
            }
            if (Double.isFinite(normalizationValue) && normalizationValue != 0.0) {
                normalizeVector(advWordTuples, adverb, normalizationValue);
            }
        }
    }
    
    

    /**
     * This function normalizes the word vector of @param word by the square of the length of feature vector or the vector element sum.
     * @param <T> the type of keys in @param tuplesMap
     * @param <V> the type of values in @param tuplesMap
     * @param tuplesMap the map containing the (word, feature) pairs
     * @param word the word
     * @param normalizationValue the value that is used for normalization
     * with respect to one type of features, which are contained in @param tuplesMap.
     */
    public static <T, V> void normalizeVector(HashMap<T, HashMap<V, Double>> tuplesMap, T word, double normalizationValue) {
        HashMap<V, Double> wordTable;
        if ((wordTable = tuplesMap.get(word)) != null) {
            for (Map.Entry<V, Double> wordEntry : wordTable.entrySet()) {
                wordEntry.setValue(wordEntry.getValue() / normalizationValue);
            }
        }
    }
    
    
    
}
