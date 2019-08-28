package dsm.util;

import dsm.parameters.VecSim.SimilarityMeasure;
import static dsm.parameters.MiscParam.*;
import static dsm.parameters.VecSim.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 *
 * @author Dobó
 */
public class Aggregation {
    
    public static enum AggregationMethod {LengthSquare, AbsValueSum, Sum};

    /**
     * This function does the necessary aggregations on the words vectors.
     */
    public static void aggregateVectors() {
        aggregateVectorElements(AggregationMethod.LengthSquare);
        if (similarityMeasure == SimilarityMeasure.Dice_1 || similarityMeasureString.matches("Pears(Mod_.*)?") || similarityMeasure == SimilarityMeasure.Jaccard_1 || 
                similarityMeasure == SimilarityMeasure.PseudoCos || similarityMeasure == SimilarityMeasure.PseudoCosMod_2 || similarityMeasure == SimilarityMeasure.Covariance || 
                similarityMeasure == SimilarityMeasure.Overlap || similarityMeasure == SimilarityMeasure.Lin || pearsCombPattern.matcher(similarityMeasureString).matches() || 
                pearsMbAdjCosModPattern.matcher(similarityMeasureString).matches() || pearsMbAdjCosPfModPattern.matcher(similarityMeasureString).matches() || 
                pearsMbModPattern.matcher(similarityMeasureString).matches()) {
            aggregateVectorElements(AggregationMethod.Sum);
        } else if (similarityMeasure == SimilarityMeasure.RobersMod || similarityMeasure == SimilarityMeasure.Simpson_1 || similarityMeasure == SimilarityMeasure.PseudoCosMod_1 || 
                similarityMeasure == SimilarityMeasure.PseudoCosMod_3 || similarityMeasure == SimilarityMeasure.Dice_1Mod || similarityMeasure == SimilarityMeasure.Jaccard_1Mod) {
            aggregateVectorElements(AggregationMethod.AbsValueSum);
        } else if (similarityMeasure == SimilarityMeasure.CorrKiela || similarityMeasure == SimilarityMeasure.MahalanobisMod || 
                similarityMeasureString.matches("PenroseShape(Mod_.*)?") || similarityMeasure == SimilarityMeasure.Dfvmb) {
            aggregateVectorElements(AggregationMethod.Sum);
        } else if (similarityMeasure == SimilarityMeasure.SorensenMod) {
            aggregateVectorElements(AggregationMethod.AbsValueSum);
        } else if (!(similarityMeasure == SimilarityMeasure.Cos || similarityMeasure == SimilarityMeasure.Dice_2 || similarityMeasure == SimilarityMeasure.L05 || 
                similarityMeasure == SimilarityMeasure.L1 || similarityMeasure == SimilarityMeasure.L2 || similarityMeasure == SimilarityMeasure.L3 || 
                similarityMeasure == SimilarityMeasure.LInf || similarityMeasure == SimilarityMeasure.Canberra || similarityMeasure == SimilarityMeasure.Lorentzian || 
                similarityMeasure == SimilarityMeasure.Jaccard_2 || similarityMeasure == SimilarityMeasure.LinKiela || similarityMeasure == SimilarityMeasure.Tanimoto_1 || 
                similarityMeasure == SimilarityMeasure.Tanimoto_1Mod || similarityMeasure == SimilarityMeasure.Tanimoto_2 || similarityMeasureString.matches("StdLike_.") || 
                similarityMeasure == SimilarityMeasure.ZklMod || similarityMeasure == SimilarityMeasure.ZklModSym || similarityMeasure == SimilarityMeasure.ASkewMod || 
                similarityMeasure == SimilarityMeasure.ASkewModSym || similarityMeasure == SimilarityMeasure.JensenShannonMod || similarityMeasure == SimilarityMeasure.JensenMod || 
                similarityMeasure == SimilarityMeasure.HellingerMod || similarityMeasure == SimilarityMeasure.ChiSquareMod || similarityMeasure == SimilarityMeasure.PsChiSquareMod || 
                similarityMeasure == SimilarityMeasure.ClarkMod || similarityMeasure == SimilarityMeasure.RenyiDivMod2 || similarityMeasure == SimilarityMeasure.RenyiDivModInf || 
                similarityMeasure == SimilarityMeasure.Lsmq || similarityMeasureString.matches("CosMod_(1|4)_.") || lModPattern.matcher(similarityMeasureString).matches() || 
                lWPattern.matcher(similarityMeasureString).matches() || dtvPattern.matcher(similarityMeasureString).matches() || 
                dtvWPattern.matcher(similarityMeasureString).matches() || similarityMeasure == SimilarityMeasure.TanejaMod || 
                similarityMeasure == SimilarityMeasure.KumarJohnsonMod || similarityMeasure == SimilarityMeasure.AvgL1LInf || similarityMeasure == SimilarityMeasure.VicWhMod || 
                similarityMeasure == SimilarityMeasure.VicSymChiSqMod1 || similarityMeasure == SimilarityMeasure.VicSymChiSqMod2 || 
                similarityMeasure == SimilarityMeasure.VicSymChiSqMod3 || similarityMeasure == SimilarityMeasure.MaxSymChiSqMod || 
                similarityMeasureString.matches("Lin(HindleR)?Mod_(6|7)_(1|2).*") || similarityMeasure == SimilarityMeasure.Mb || similarityMeasureString.matches("Multiplicative.*") || 
                similarityMeasure == SimilarityMeasure.SmoothCos || similarityMeasure == SimilarityMeasure.AdjCos || similarityMeasureString.matches("NcdMod_.") || 
                similarityMeasure == SimilarityMeasure.NgdMod || similarityMeasure == SimilarityMeasure.Spearm || similarityMeasure == SimilarityMeasure.Jaccard_3 || 
                similarityMeasure == SimilarityMeasure.HarmMeanMod || similarityMeasure == SimilarityMeasure.InnerProd || similarityMeasure == SimilarityMeasure.FidelityMod || 
                similarityMeasureString.matches("Mb(Adj)?Cos((Am)|(Gm)|(Hm)|(Prod)|(LogProd))") || similarityMeasureString.matches("CosMod_(2|3|5|6)_.") || 
                innerProdWPattern.matcher(similarityMeasureString).matches() || similarityMeasure == SimilarityMeasure.Srsn || similarityMeasure == SimilarityMeasure.Rsssn || 
                similarityMeasure == SimilarityMeasure.Sdsn || similarityMeasure == SimilarityMeasure.Srsmv || similarityMeasure == SimilarityMeasure.Srsm || 
                similarityMeasureString.matches("Lin(HindleR)?Mod_(1|2|3|4|5|8|9)_(1|2).*") || similarityMeasure == SimilarityMeasure.Rms || 
                similarityMeasure == SimilarityMeasure.ContraHMeanMod || similarityMeasure == SimilarityMeasure.Spline || similarityMeasure == SimilarityMeasure.Kulczynski || 
                similarityMeasure == SimilarityMeasure.Simpson_2Mod || similarityMeasure == SimilarityMeasure.NormCosMod || similarityMeasureString.matches("(NormMod)?SocPmiMod") || 
                similarityMeasure == SimilarityMeasure.ChenCorr || similarityMeasure == SimilarityMeasure.ApSyn || similarityMeasure == SimilarityMeasure.ApSynP || 
                similarityMeasure == SimilarityMeasure.Wo || similarityMeasure == SimilarityMeasure.Rbo || similarityMeasure == SimilarityMeasure.MbAdjCos || 
                similarityMeasure == SimilarityMeasure.MbPFMod || similarityMeasure == SimilarityMeasure.MbAdjCosPFMod || similarityMeasure == SimilarityMeasure.AdjCosPFMod || 
                mbModPattern.matcher(similarityMeasureString).matches() || adjCosModPattern.matcher(similarityMeasureString).matches() || 
                pfModPattern.matcher(similarityMeasureString).matches())) {
            System.out.println("It is not defined which version of vector normalization should be used for this version: " + method + " " + similarityMeasure);
            System.exit(1);
        }
    }

    /**
     * This function calculates the square of the length of feature vectors of words or the vector element sum for all types of POS.
     * @param aggregationMethod determines the aggregation method
     */
    public static void aggregateVectorElements(AggregationMethod aggregationMethod) {
        HashMap<String, Double> map;
        HashSet<String> allWordSet;
        if (aggregationMethod == AggregationMethod.LengthSquare) {
            map = nounVectorLengthSquares;
        } else if (aggregationMethod == AggregationMethod.AbsValueSum) {
            map = nounVectorElementAbsValueSums;
        } else {
            map = nounVectorElementSums;
        }
        if (readExtractedInformationJustForInputWords) {
            allWordSet = allNouns;
        } else {
            allWordSet = new HashSet<String>();
            allWordSet.addAll(objVerbTuples.keySet());
            allWordSet.addAll(subjVerbTuples.keySet());
            allWordSet.addAll(nounNcmodTuples.keySet());
            allWordSet.addAll(allNouns);
        }
        for (String noun : allWordSet) {
            map.put(noun, aggregateVectorElements(objVerbTuples, noun, aggregationMethod) + aggregateVectorElements(subjVerbTuples, noun, aggregationMethod) + aggregateVectorElements(nounNcmodTuples, noun, aggregationMethod));
        }
        if (aggregationMethod == AggregationMethod.LengthSquare) {
            map = verbVectorLengthSquares;
        } else if (aggregationMethod == AggregationMethod.AbsValueSum) {
            map = verbVectorElementAbsValueSums;
        } else {
            map = verbVectorElementSums;
        }
        if (readExtractedInformationJustForInputWords) {
            allWordSet = allVerbs;
        } else {
            allWordSet = new HashSet<String>();
            allWordSet.addAll(verbDobjTuples.keySet());
            allWordSet.addAll(verbNcsubjTuples.keySet());
            allWordSet.addAll(verbPrepTuples.keySet());
            allWordSet.addAll(verbObj2Tuples.keySet());
            allWordSet.addAll(allVerbs);
        }
        for (String verb : allWordSet) {
            map.put(verb, aggregateVectorElements(verbDobjTuples, verb, aggregationMethod) + aggregateVectorElements(verbNcsubjTuples, verb, aggregationMethod) + aggregateVectorElements(verbPrepTuples, verb, aggregationMethod) + aggregateVectorElements(verbObj2Tuples, verb, aggregationMethod));
        }
        if (aggregationMethod == AggregationMethod.LengthSquare) {
            map = adjectiveVectorLengthSquares;
        } else if (aggregationMethod == AggregationMethod.AbsValueSum) {
            map = adjectiveVectorElementAbsValueSums;
        } else {
            map = adjectiveVectorElementSums;
        }
        if (readExtractedInformationJustForInputWords) {
            allWordSet = allAdjectives;
        } else {
            allWordSet = new HashSet<String>();
            allWordSet.addAll(adjNounTuples.keySet());
            allWordSet.addAll(allAdjectives);
        }
        for (String adjective : allWordSet) {
            map.put(adjective, aggregateVectorElements(adjNounTuples, adjective, aggregationMethod));
        }
        if (aggregationMethod == AggregationMethod.LengthSquare) {
            map = adverbVectorLengthSquares;
        } else if (aggregationMethod == AggregationMethod.AbsValueSum) {
            map = adverbVectorElementAbsValueSums;
        } else {
            map = adverbVectorElementSums;
        }
        if (readExtractedInformationJustForInputWords) {
            allWordSet = allAdverbs;
        } else {
            allWordSet = new HashSet<String>();
            allWordSet.addAll(advWordTuples.keySet());
            allWordSet.addAll(allAdverbs);
        }
        for (String adverb : allWordSet) {
            map.put(adverb, aggregateVectorElements(advWordTuples, adverb, aggregationMethod));
        }
    }

    /**
     * This function calculates the square of the length of feature vector or the vector element sum of @param word
     * with respect to one type of features, which are contained in @param tuplesMap.
     * @param <T> the type of keys in @param tuplesMap
     * @param <V> the type of values in @param tuplesMap
     * @param tuplesMap the map containing the (word, feature) pairs
     * @param word the word
     * @param aggregationMethod determines the aggregation method
     * @return the square of the vector length or the vector element sum
     */
    public static <T, V> double aggregateVectorElements(HashMap<T, HashMap<V, Double>> tuplesMap, T word, AggregationMethod aggregationMethod) {
        double d;
        double aggregateValue = 0.0;
        HashMap<V, Double> wordTable;
        if ((wordTable = tuplesMap.get(word)) != null) {
            for (Map.Entry<V, Double> wordEntry : wordTable.entrySet()) {
                d = wordEntry.getValue();
                if (aggregationMethod == AggregationMethod.LengthSquare) {
                    aggregateValue += d * d;
                } else if (aggregationMethod == AggregationMethod.AbsValueSum) {
                    aggregateValue += Math.abs(d);
                } else {
                    aggregateValue += d;
                }
            }
        }
        return aggregateValue;
    }
    
    
}
