package extinfbow;

import java.util.HashMap;
import java.util.HashSet;

/**
 * The class containing the parameters for the bag-of-words extracted information.
 * For info about the usage of these parameters see {@link extinfbow.ExtractInformationBagOfWords}.
 * 
 * @author Dobó
 */
public class ExtInfBowParam {
    
    //These are the parameters of this class, with default values.
    static enum SourceType {EnParserV1, EnParserV2, EnParserV2WithSentenceCounts, HuParser, EsTagger};
    static SourceType sourceType = SourceType.HuParser;
    static int windowSize=3;
    static enum WeightingScheme {Uniform, Linear, Quadratic};
    static WeightingScheme weightingScheme = WeightingScheme.Uniform;
    static boolean extractInformaionJustForInputWords = true;
    static String corpusLocation="Corpus";
    static String outputFileName="extractedInformationFromBNCBagOfWordsWindow3QuadraticWeight.txt";

    /*
     * These variables are used to store the information extracted from the corpus. Although their names suggest differently, they all simply store bag-of-words type information. The names are
     * retained from the ExtractInformationFromParsedText class, to make things easier and because in the information file they will be stored under these names.
     */
    static HashMap<String, HashMap<String,Long>> nounNcmodTuples = new HashMap<String, HashMap<String,Long>>();
    static HashMap<String, HashMap<String,Long>> nounNcmodSimpleTuples = new HashMap<String, HashMap<String,Long>>();
    static HashMap<String, HashMap<String,Long>> ncmodNounTuples = new HashMap<String, HashMap<String,Long>>();
    static HashMap<String, Long> ncmodWithNounFrequencies  = new HashMap<String, Long>();
    static HashMap<String, Long> ncmodWithNounSimpleFrequencies  = new HashMap<String, Long>();
    static HashMap<String, Long> nounWithNcmodFrequencies  = new HashMap<String, Long>();
    static HashMap<String, Long> nounFrequencies  = new HashMap<String, Long>();
    static HashMap<String, Long> nounSimpleFrequencies  = new HashMap<String, Long>();
    static HashMap<String, HashMap<String,Long>> verbDobjTuples = new HashMap<String, HashMap<String,Long>>();
    static HashMap<String, HashMap<String,Long>> verbDobjSimpleTuples = new HashMap<String, HashMap<String,Long>>();
    static HashMap<String, HashMap<String,Long>> dobjVerbTuples = new HashMap<String, HashMap<String,Long>>();
    static HashMap<String, Long> dobjWithVerbFrequencies  = new HashMap<String, Long>();
    static HashMap<String, Long> dobjWithVerbSimpleFrequencies  = new HashMap<String, Long>();
    static HashMap<String, Long> verbWithDobjFrequencies  = new HashMap<String, Long>();
    static HashMap<String, Long> verbFrequencies  = new HashMap<String, Long>();
    static HashMap<String, Long> verbSimpleFrequencies  = new HashMap<String, Long>();
    static HashMap<String, HashMap<String,Long>> adjNounTuples = new HashMap<String, HashMap<String,Long>>();
    static HashMap<String, HashMap<String,Long>> adjNounSimpleTuples = new HashMap<String, HashMap<String,Long>>();
    static HashMap<String, HashMap<String,Long>> nounAdjTuples = new HashMap<String, HashMap<String,Long>>();
    static HashMap<String, Long> nounWithAdjFrequencies  = new HashMap<String, Long>();
    static HashMap<String, Long> nounWithAdjSimpleFrequencies  = new HashMap<String, Long>();
    static HashMap<String, Long> adjWithNounFrequencies  = new HashMap<String, Long>();
    static HashMap<String, Long> adjectiveFrequencies  = new HashMap<String, Long>();
    static HashMap<String, Long> adjectiveSimpleFrequencies  = new HashMap<String, Long>();
    static HashMap<String, HashMap<String,Long>> advWordTuples = new HashMap<String, HashMap<String,Long>>();
    static HashMap<String, HashMap<String,Long>> advWordSimpleTuples = new HashMap<String, HashMap<String,Long>>();
    static HashMap<String, HashMap<String,Long>> wordAdvTuples = new HashMap<String, HashMap<String,Long>>();
    static HashMap<String, Long> wordWithAdvFrequencies  = new HashMap<String, Long>();
    static HashMap<String, Long> wordWithAdvSimpleFrequencies  = new HashMap<String, Long>();
    static HashMap<String, Long> advWithWordFrequencies  = new HashMap<String, Long>();
    static HashMap<String, Long> adverbFrequencies  = new HashMap<String, Long>();
    static HashMap<String, Long> adverbSimpleFrequencies  = new HashMap<String, Long>();
    static long allNounNcmodCount=0;
    static long allVerbDobjCount=0;
    static long allAdjNounCount=0;
    static long allAdvWordCount=0;
    static long allWordCount=0;
    static HashSet<String> allInputWords = new HashSet<String>();
    
}
