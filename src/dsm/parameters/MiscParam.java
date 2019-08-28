package dsm.parameters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import util.ComparablePair;
import util.Paraphrase;

/**
 *
 * @author Dobó
 */
public class MiscParam {
    
    
    //These are the parameters of this class, with default values.
    public static boolean filterStopWords=false;
    public static HashSet<String> stopWordsSet = null;
    public static Integer minimumWordFeatureTupleSimpleFrequency=null;
    public static enum MinimumWordFeatureTupleWeightType {NoLimit, Limit, Zero};
    public static MinimumWordFeatureTupleWeightType minimumWordFeatureTupleWeightType=MinimumWordFeatureTupleWeightType.NoLimit;
    public static Double minimumWordFeatureTupleWeightParameter=null;
    public static Integer minimumFeatureSimpleFrequency=null;
    
    public static Integer minimumWordSimpleFrequency=null;
    public static enum Method {Lin, Num};
    public static Method method=Method.Num;
    public static enum WordType {WordsInWN, AllWords};
    public static WordType wordType=WordType.AllWords;
    public static boolean readExtractedInformationJustForInputWords = true;
    
    //These variables are used to store the information extracted from the corpus.
    public static HashMap<String, HashMap<String, Double>> semanticCategoriesNouns = new HashMap<String, HashMap<String, Double>>();
    public static HashMap<String, HashMap<String, Double>> semanticCategoriesVerbs = new HashMap<String, HashMap<String, Double>>();
    public static HashMap<String, HashMap<String, Double>> semanticCategoriesAdjectives = new HashMap<String, HashMap<String, Double>>();
    public static HashMap<String, HashMap<String, Double>> semanticCategoriesAdverbs = new HashMap<String, HashMap<String, Double>>();
    
    public static HashMap<String, HashMap<Paraphrase,Double>> objVerbTuples = new HashMap<String, HashMap<Paraphrase,Double>>();
    public static HashMap<String, HashMap<Paraphrase,Double>> objVerbSimpleTuples = new HashMap<String, HashMap<Paraphrase,Double>>();
    public static HashMap<String, HashMap<Paraphrase,Double>> subjVerbTuples = new HashMap<String, HashMap<Paraphrase,Double>>();
    public static HashMap<String, HashMap<Paraphrase,Double>> subjVerbSimpleTuples = new HashMap<String, HashMap<Paraphrase,Double>>();
    public static HashMap<String, HashMap<String,Double>> nounNcmodTuples = new HashMap<String, HashMap<String,Double>>();
    public static HashMap<String, HashMap<String,Double>> nounNcmodSimpleTuples = new HashMap<String, HashMap<String,Double>>();
    public static HashMap<Paraphrase, Long> verbObjectTuples = new HashMap<Paraphrase, Long>();
    public static HashMap<Paraphrase, Long> verbSubjectTuples = new HashMap<Paraphrase, Long>();
    public static HashMap<String, Long> ncmodNounTuples = new HashMap<String, Long>();
    public static HashMap<Paraphrase, Double> verbObjectEntropy = new HashMap<Paraphrase, Double>();
    public static HashMap<Paraphrase, Double> verbSubjectEntropy = new HashMap<Paraphrase, Double>();
    public static HashMap<String, Double> ncmodNounEntropy = new HashMap<String, Double>();
    public static HashMap<Paraphrase, Double> verbWithObjectFrequencies = new HashMap<Paraphrase, Double>();
    public static HashMap<Paraphrase, Double> verbWithObjectSimpleFrequencies = new HashMap<Paraphrase, Double>();
    public static HashMap<Paraphrase, Double> verbWithSubjectFrequencies = new HashMap<Paraphrase, Double>();
    public static HashMap<Paraphrase, Double> verbWithSubjectSimpleFrequencies = new HashMap<Paraphrase, Double>();
    public static HashMap<String, Double> ncmodWithNounFrequencies  = new HashMap<String, Double>();
    public static HashMap<String, Double> ncmodWithNounSimpleFrequencies  = new HashMap<String, Double>();
    public static HashMap<String, Double> objectFrequencies  = new HashMap<String, Double>();
    public static HashMap<String, Double> subjectFrequencies  = new HashMap<String, Double>();
    public static HashMap<String, Double> nounWithNcmodFrequencies  = new HashMap<String, Double>();
    public static HashMap<Paraphrase, Double> verbObjectInformation = new HashMap<Paraphrase, Double>();
    public static HashMap<Paraphrase, Double> verbSubjectInformation = new HashMap<Paraphrase, Double>();
    public static HashMap<String, Double> ncmodNounInformation = new HashMap<String, Double>();
    public static HashMap<Paraphrase, Double> verbObjectGrefFeatureWeight = new HashMap<Paraphrase, Double>();
    public static HashMap<Paraphrase, Double> verbSubjectGrefFeatureWeight = new HashMap<Paraphrase, Double>();
    public static HashMap<String, Double> ncmodNounGrefFeatureWeight = new HashMap<String, Double>();
    public static HashMap<Paraphrase, Double> verbObjectATCFeatureWeight = new HashMap<Paraphrase, Double>();
    public static HashMap<Paraphrase, Double> verbSubjectATCFeatureWeight = new HashMap<Paraphrase, Double>();
    public static HashMap<String, Double> ncmodNounATCFeatureWeight = new HashMap<String, Double>();
    public static Double objVerbMaxFrequency = null;
    public static Double subjVerbMaxFrequency = null;
    public static Double nounNcmodMaxFrequency = null;
    public static HashMap<String, Double> nounFrequencies  = new HashMap<String, Double>();
    public static HashMap<String, Double> nounSimpleFrequencies  = new HashMap<String, Double>();
    public static HashMap<String, HashMap<Double, Double>> nounValueToRankMaps = new HashMap<String, HashMap<Double, Double>>();
    public static HashMap<String, Long[]> nounKNSNCounts  = new HashMap<String, Long[]>();
    public static HashMap<Paraphrase, Long[]> verbObjectTuplesKNSNCounts  = new HashMap<Paraphrase, Long[]>();
    public static HashMap<Paraphrase, Long[]> verbSubjectTuplesKNSNCounts  = new HashMap<Paraphrase, Long[]>();
    public static HashMap<String, Long[]> ncmodNounTuplesKNSNCounts  = new HashMap<String, Long[]>();
    public static Long[] allNounKNSNCounts = new Long[4];
    
    public static HashMap<String, HashMap<String,Double>> verbDobjTuples = new HashMap<String, HashMap<String,Double>>();
    public static HashMap<String, HashMap<String,Double>> verbDobjSimpleTuples = new HashMap<String, HashMap<String,Double>>();
    public static HashMap<String, HashMap<String,Double>> verbNcsubjTuples = new HashMap<String, HashMap<String,Double>>();
    public static HashMap<String, HashMap<String,Double>> verbNcsubjSimpleTuples = new HashMap<String, HashMap<String,Double>>();
    public static HashMap<String, HashMap<String,Double>> verbPrepTuples = new HashMap<String, HashMap<String,Double>>();
    public static HashMap<String, HashMap<String,Double>> verbPrepSimpleTuples = new HashMap<String, HashMap<String,Double>>();
    public static HashMap<String, HashMap<String,Double>> verbObj2Tuples = new HashMap<String, HashMap<String,Double>>();
    public static HashMap<String, HashMap<String,Double>> verbObj2SimpleTuples = new HashMap<String, HashMap<String,Double>>();
    public static HashMap<String, Long> dobjVerbTuples = new HashMap<String, Long>();
    public static HashMap<String, Long> ncsubjVerbTuples = new HashMap<String, Long>();
    public static HashMap<String, Long> prepVerbTuples = new HashMap<String, Long>();
    public static HashMap<String, Long> obj2VerbTuples = new HashMap<String, Long>();
    public static HashMap<String, Double> dobjVerbEntropy = new HashMap<String, Double>();
    public static HashMap<String, Double> ncsubjVerbEntropy = new HashMap<String, Double>();
    public static HashMap<String, Double> prepVerbEntropy = new HashMap<String, Double>();
    public static HashMap<String, Double> obj2VerbEntropy = new HashMap<String, Double>();
    public static HashMap<String, Double> dobjWithVerbFrequencies  = new HashMap<String, Double>();
    public static HashMap<String, Double> dobjWithVerbSimpleFrequencies  = new HashMap<String, Double>();
    public static HashMap<String, Double> ncsubjWithVerbFrequencies  = new HashMap<String, Double>();
    public static HashMap<String, Double> ncsubjWithVerbSimpleFrequencies  = new HashMap<String, Double>();
    public static HashMap<String, Double> prepWithVerbFrequencies  = new HashMap<String, Double>();
    public static HashMap<String, Double> prepWithVerbSimpleFrequencies  = new HashMap<String, Double>();
    public static HashMap<String, Double> obj2WithVerbFrequencies  = new HashMap<String, Double>();
    public static HashMap<String, Double> obj2WithVerbSimpleFrequencies  = new HashMap<String, Double>();
    public static HashMap<String, Double> verbWithDobjFrequencies  = new HashMap<String, Double>();
    public static HashMap<String, Double> verbWithNcsubjFrequencies  = new HashMap<String, Double>();
    public static HashMap<String, Double> verbWithPrepFrequencies  = new HashMap<String, Double>();
    public static HashMap<String, Double> verbWithObj2Frequencies  = new HashMap<String, Double>();
    public static HashMap<String, Double> dobjVerbInformation = new HashMap<String, Double>();
    public static HashMap<String, Double> ncsubjVerbInformation = new HashMap<String, Double>();
    public static HashMap<String, Double> prepVerbInformation = new HashMap<String, Double>();
    public static HashMap<String, Double> obj2VerbInformation = new HashMap<String, Double>();
    public static HashMap<String, Double> dobjVerbGrefFeatureWeight = new HashMap<String, Double>();
    public static HashMap<String, Double> ncsubjVerbGrefFeatureWeight = new HashMap<String, Double>();
    public static HashMap<String, Double> prepVerbGrefFeatureWeight = new HashMap<String, Double>();
    public static HashMap<String, Double> obj2VerbGrefFeatureWeight = new HashMap<String, Double>();
    public static HashMap<String, Double> dobjVerbATCFeatureWeight = new HashMap<String, Double>();
    public static HashMap<String, Double> ncsubjVerbATCFeatureWeight = new HashMap<String, Double>();
    public static HashMap<String, Double> prepVerbATCFeatureWeight = new HashMap<String, Double>();
    public static HashMap<String, Double> obj2VerbATCFeatureWeight = new HashMap<String, Double>();
    public static Double verbDobjMaxFrequency = null;
    public static Double verbNcsubjMaxFrequency = null;
    public static Double verbPrepMaxFrequency = null;
    public static Double verbObj2MaxFrequency = null;
    public static HashMap<String, Double> verbFrequencies  = new HashMap<String, Double>();
    public static HashMap<String, Double> verbSimpleFrequencies  = new HashMap<String, Double>();
    public static HashMap<String, HashMap<Double, Double>> verbValueToRankMaps = new HashMap<String, HashMap<Double, Double>>();
    public static HashMap<String, Long[]> verbKNSNCounts  = new HashMap<String, Long[]>();
    public static HashMap<String, Long[]> dobjVerbTuplesKNSNCounts  = new HashMap<String, Long[]>();
    public static HashMap<String, Long[]> ncsubjVerbTuplesKNSNCounts  = new HashMap<String, Long[]>();
    public static HashMap<String, Long[]> prepVerbTuplesKNSNCounts  = new HashMap<String, Long[]>();
    public static HashMap<String, Long[]> obj2VerbTuplesKNSNCounts  = new HashMap<String, Long[]>();
    public static Long[] allVerbKNSNCounts = new Long[4];
    
    public static HashMap<String, HashMap<String,Double>> adjNounTuples = new HashMap<String, HashMap<String,Double>>();
    public static HashMap<String, HashMap<String,Double>> adjNounSimpleTuples = new HashMap<String, HashMap<String,Double>>();
    public static HashMap<String, Long> nounAdjTuples = new HashMap<String, Long>();
    public static HashMap<String, Double> nounAdjEntropy = new HashMap<String, Double>();
    public static HashMap<String, Double> nounWithAdjFrequencies  = new HashMap<String, Double>();
    public static HashMap<String, Double> nounWithAdjSimpleFrequencies  = new HashMap<String, Double>();
    public static HashMap<String, Double> adjWithNounFrequencies  = new HashMap<String, Double>();
    public static HashMap<String, Double> nounAdjInformation = new HashMap<String, Double>();
    public static HashMap<String, Double> nounAdjGrefFeatureWeight = new HashMap<String, Double>();
    public static HashMap<String, Double> nounAdjATCFeatureWeight = new HashMap<String, Double>();
    public static Double adjNounMaxFrequency = null;
    public static HashMap<String, Double> adjectiveFrequencies  = new HashMap<String, Double>();
    public static HashMap<String, Double> adjectiveSimpleFrequencies  = new HashMap<String, Double>();
    public static HashMap<String, HashMap<Double, Double>> adjectiveValueToRankMaps = new HashMap<String, HashMap<Double, Double>>();
    public static HashMap<String, Long[]> adjectiveKNSNCounts  = new HashMap<String, Long[]>();
    public static HashMap<String, Long[]> nounAdjTuplesKNSNCounts  = new HashMap<String, Long[]>();
    public static Long[] allAdjectiveKNSNCounts = new Long[4];
    
    public static HashMap<String, HashMap<String,Double>> advWordTuples = new HashMap<String, HashMap<String,Double>>();
    public static HashMap<String, HashMap<String,Double>> advWordSimpleTuples = new HashMap<String, HashMap<String,Double>>();
    public static HashMap<String, Long> wordAdvTuples = new HashMap<String, Long>();
    public static HashMap<String, Double> wordAdvEntropy = new HashMap<String, Double>();
    public static HashMap<String, Double> wordWithAdvFrequencies  = new HashMap<String, Double>();
    public static HashMap<String, Double> wordWithAdvSimpleFrequencies  = new HashMap<String, Double>();
    public static HashMap<String, Double> advWithWordFrequencies  = new HashMap<String, Double>();
    public static HashMap<String, Double> wordAdvInformation = new HashMap<String, Double>();
    public static HashMap<String, Double> wordAdvGrefFeatureWeight = new HashMap<String, Double>();
    public static HashMap<String, Double> wordAdvATCFeatureWeight = new HashMap<String, Double>();
    public static Double advWordMaxFrequency = null;
    public static HashMap<String, Double> adverbFrequencies  = new HashMap<String, Double>();
    public static HashMap<String, Double> adverbSimpleFrequencies  = new HashMap<String, Double>();
    public static HashMap<String, HashMap<Double, Double>> adverbValueToRankMaps = new HashMap<String, HashMap<Double, Double>>();
    public static HashMap<String, Long[]> adverbKNSNCounts  = new HashMap<String, Long[]>();
    public static HashMap<String, Long[]> wordAdvTuplesKNSNCounts  = new HashMap<String, Long[]>();
    public static Long[] allAdverbKNSNCounts = new Long[4];
    
    public static HashMap<String, Double> nounVectorLengthSquares = new HashMap<String, Double>();
    public static HashMap<String, Double> nounVectorElementSums = new HashMap<String, Double>();
    public static HashMap<String, Double> nounVectorElementAbsValueSums = new HashMap<String, Double>();
    public static HashMap<String, Double> verbVectorLengthSquares = new HashMap<String, Double>();
    public static HashMap<String, Double> verbVectorElementSums = new HashMap<String, Double>();
    public static HashMap<String, Double> verbVectorElementAbsValueSums = new HashMap<String, Double>();
    public static HashMap<String, Double> adjectiveVectorLengthSquares = new HashMap<String, Double>();
    public static HashMap<String, Double> adjectiveVectorElementSums = new HashMap<String, Double>();
    public static HashMap<String, Double> adjectiveVectorElementAbsValueSums = new HashMap<String, Double>();
    public static HashMap<String, Double> adverbVectorLengthSquares = new HashMap<String, Double>();
    public static HashMap<String, Double> adverbVectorElementSums = new HashMap<String, Double>();
    public static HashMap<String, Double> adverbVectorElementAbsValueSums = new HashMap<String, Double>();
    
    public static HashMap<String, ArrayList<ArrayList<String>>> allNounQuestionsWithAllAnswers = new HashMap<String, ArrayList<ArrayList<String>>>();
    public static HashMap<String, ArrayList<ArrayList<String>>> allVerbQuestionsWithAllAnswers = new HashMap<String, ArrayList<ArrayList<String>>>();
    public static HashMap<String, ArrayList<ArrayList<String>>> allAdjectiveQuestionsWithAllAnswers = new HashMap<String, ArrayList<ArrayList<String>>>();
    public static HashMap<String, ArrayList<ArrayList<String>>> allAdverbQuestionsWithAllAnswers = new HashMap<String, ArrayList<ArrayList<String>>>();
    public static HashMap<ComparablePair<String, String>, Double> allNounQuestionsWithCorrectScores = new HashMap<ComparablePair<String, String>, Double>();
    public static HashMap<ComparablePair<String, String>, Double> allVerbQuestionsWithCorrectScores = new HashMap<ComparablePair<String, String>, Double>();
    public static HashMap<ComparablePair<String, String>, Double> allAdjectiveQuestionsWithCorrectScores = new HashMap<ComparablePair<String, String>, Double>();
    public static HashMap<ComparablePair<String, String>, Double> allAdverbQuestionsWithCorrectScores = new HashMap<ComparablePair<String, String>, Double>();
    public static HashSet<String> allNouns = new HashSet<String>();
    public static HashSet<String> allVerbs = new HashSet<String>();
    public static HashSet<String> allAdjectives = new HashSet<String>();
    public static HashSet<String> allAdverbs = new HashSet<String>();
    public static HashMap<String, String> nounLemmas = new HashMap<String, String>();
    public static HashMap<String, String> verbLemmas = new HashMap<String, String>();
    public static HashMap<String, String> adjectiveLemmas = new HashMap<String, String>();
    public static HashMap<String, String> adverbLemmas = new HashMap<String, String>();
    
    public static double allObjectCount=0;
    public static double allSubjectCount=0;
    public static double allNounNcmodCount=0;
    public static double allVerbDobjCount=0;
    public static double allVerbNcsubjCount=0;
    public static double allVerbPrepCount=0;
    public static double allVerbObj2Count=0;
    public static double allAdjNounCount=0;
    public static double allAdvWordCount=0;
    
    public static double allObjectCountAlpha=0d;
    public static double allSubjectCountAlpha=0d;
    public static double allNounNcmodCountAlpha=0d;
    public static double allVerbDobjCountAlpha=0d;
    public static double allVerbNcsubjCountAlpha=0d;
    public static double allVerbPrepCountAlpha=0d;
    public static double allVerbObj2CountAlpha=0d;
    public static double allAdjNounCountAlpha=0d;
    public static double allAdvWordCountAlpha=0d;
    
    public static long allNounTypeCount=0;
    public static long allVerbTypeCount=0;
    public static long allAdjectiveTypeCount=0;
    public static long allAdverbTypeCount=0;
    
    public static double allWordCount=0;
    
    public static long allNounFeatureCount=0;
    public static long allVerbFeatureCount=0;
    public static long allAdjFeatureCount=0;
    public static long allAdvFeatureCount=0;
    
    public static double avgNounVectorElementSum=0d;
    public static double avgVerbVectorElementSum=0d;
    public static double avgAdjectiveVectorElementSum=0d;
    public static double avgAdverbVectorElementSum=0d;
    
    public static long allNounFeatureDocFreqCount=0;
    public static long allVerbFeatureDocFreqCount=0;
    public static long allAdjFeatureDocFreqCount=0;
    public static long allAdvFeatureDocFreqCount=0;
    
    public static long KNSN1p=0;
    public static long KNSN1=0;
    public static long KNSN2=0;
    public static long KNSN3p=0;
    
    
    
}
