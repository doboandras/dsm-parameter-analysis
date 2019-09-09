package extinfparsed;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import util.ComparablePair;
import util.Paraphrase;

/**
 * The class containing the parameters for the dependency-relation-based extracted information.
 * For info about the usage of these parameters see {@link extinfparsed.ExtractInformationFromParsedText}.
 * 
 * @author Dobó
 */
public class ExtInfParsedParam {
    
    //These are the parameters of this class, with default values.
    static enum SourceType {EnParserV1, EnParserV2, EnParserV2WithSentenceCounts};
    static SourceType sourceType = SourceType.EnParserV2;
    static boolean extractInformaionJustForInputWords = true;
    static String corpusLocation="Corpus";
    static String outputFileName="extractedInformationFromBNC.txt";

    //These variables are used to store the information extracted from the corpus.
    static HashMap<String, HashMap<Paraphrase,Long>> objVerbTuples = new HashMap<String, HashMap<Paraphrase,Long>>();
    static HashMap<String, HashMap<Paraphrase,Long>> subjVerbTuples = new HashMap<String, HashMap<Paraphrase,Long>>();
    static HashMap<String, HashMap<String,Long>> nounNcmodTuples = new HashMap<String, HashMap<String,Long>>();
    static HashMap<Paraphrase, HashMap<String,Long>> verbObjectTuples = new HashMap<Paraphrase, HashMap<String,Long>>();
    static HashMap<Paraphrase, HashMap<String,Long>> verbSubjectTuples = new HashMap<Paraphrase, HashMap<String,Long>>();
    static HashMap<String, HashMap<String,Long>> ncmodNounTuples = new HashMap<String, HashMap<String,Long>>();
    static HashMap<Paraphrase, Long> verbWithObjectFrequencies = new HashMap<Paraphrase, Long>();
    static HashMap<Paraphrase, Long> verbWithSubjectFrequencies = new HashMap<Paraphrase, Long>();
    static HashMap<String, Long> ncmodWithNounFrequencies  = new HashMap<String, Long>();
    static HashMap<String, Long> objectFrequencies  = new HashMap<String, Long>();
    static HashMap<String, Long> subjectFrequencies  = new HashMap<String, Long>();
    static HashMap<String, Long> nounWithNcmodFrequencies  = new HashMap<String, Long>();
    static HashMap<String, Long> nounFrequencies  = new HashMap<String, Long>();
    static HashMap<String, HashMap<String,Long>> verbDobjTuples = new HashMap<String, HashMap<String,Long>>();
    static HashMap<String, HashMap<String,Long>> verbNcsubjTuples = new HashMap<String, HashMap<String,Long>>();
    static HashMap<String, HashMap<String,Long>> verbPrepTuples = new HashMap<String, HashMap<String,Long>>();
    static HashMap<String, HashMap<String,Long>> verbObj2Tuples = new HashMap<String, HashMap<String,Long>>();
    static HashMap<String, HashMap<String,Long>> dobjVerbTuples = new HashMap<String, HashMap<String,Long>>();
    static HashMap<String, HashMap<String,Long>> ncsubjVerbTuples = new HashMap<String, HashMap<String,Long>>();
    static HashMap<String, HashMap<String,Long>> prepVerbTuples = new HashMap<String, HashMap<String,Long>>();
    static HashMap<String, HashMap<String,Long>> obj2VerbTuples = new HashMap<String, HashMap<String,Long>>();
    static HashMap<String, Long> dobjWithVerbFrequencies  = new HashMap<String, Long>();
    static HashMap<String, Long> ncsubjWithVerbFrequencies  = new HashMap<String, Long>();
    static HashMap<String, Long> prepWithVerbFrequencies  = new HashMap<String, Long>();
    static HashMap<String, Long> obj2WithVerbFrequencies  = new HashMap<String, Long>();
    static HashMap<String, Long> verbWithDobjFrequencies  = new HashMap<String, Long>();
    static HashMap<String, Long> verbWithNcsubjFrequencies  = new HashMap<String, Long>();
    static HashMap<String, Long> verbWithPrepFrequencies  = new HashMap<String, Long>();
    static HashMap<String, Long> verbWithObj2Frequencies  = new HashMap<String, Long>();
    static HashMap<String, Long> verbFrequencies  = new HashMap<String, Long>();
    static HashMap<String, HashMap<String,Long>> adjNounTuples = new HashMap<String, HashMap<String,Long>>();
    static HashMap<String, HashMap<String,Long>> nounAdjTuples = new HashMap<String, HashMap<String,Long>>();
    static HashMap<String, Long> nounWithAdjFrequencies  = new HashMap<String, Long>();
    static HashMap<String, Long> adjWithNounFrequencies  = new HashMap<String, Long>();
    static HashMap<String, Long> adjectiveFrequencies  = new HashMap<String, Long>();
    static HashMap<String, HashMap<String,Long>> advWordTuples = new HashMap<String, HashMap<String,Long>>();
    static HashMap<String, HashMap<String,Long>> wordAdvTuples = new HashMap<String, HashMap<String,Long>>();
    static HashMap<String, Long> wordWithAdvFrequencies  = new HashMap<String, Long>();
    static HashMap<String, Long> advWithWordFrequencies  = new HashMap<String, Long>();
    static HashMap<String, Long> adverbFrequencies  = new HashMap<String, Long>();
    static HashSet<String> prepositions = new HashSet<String>();
    static HashSet<String> patientiveAmbitransitives = new HashSet<String>();
    static HashMap<String, HashMap<String, Long>> subjectsInTheSentence = new HashMap<String, HashMap<String, Long>>();
    static LinkedList<ComparablePair<String, String>> objectsInTheSentence = new LinkedList<ComparablePair<String, String>>();
    static LinkedList<ComparablePair<String, String>> subjectsInTheSentenceList = new LinkedList<ComparablePair<String, String>>();
    static LinkedList<ComparablePair<String, String>> justPrepSubjectsInTheSentenceList = new LinkedList<ComparablePair<String, String>>();
    static HashSet<String> prepostitionsWithDependentsInTheSentence = new HashSet<String>();
    static HashSet<String> prepostitionsWithDobjInTheSentence = new HashSet<String>();
    static HashSet<String> verbsWithDobjInTheSentence = new HashSet<String>();
    static HashSet<Integer> commonNounsInTheSentence = new HashSet<Integer>();
    static HashSet<Integer> verbsInTheSentence = new HashSet<Integer>();
    static HashSet<Integer> adjectivesInTheSentence = new HashSet<Integer>();
    static HashSet<Integer> adverbsInTheSentence = new HashSet<Integer>();
    static HashMap<String, HashSet<String>> prepsByVerb = new HashMap<String, HashSet<String>>();
    static HashMap<String, HashSet<String>> verbsByPrep = new HashMap<String, HashSet<String>>();
    static HashMap<String, HashSet<String>> nounsByPrep = new HashMap<String, HashSet<String>>();
    static HashMap<String, HashSet<String>> auxByVerb = new HashMap<String, HashSet<String>>();
    static HashSet<String> verbsInNcsubjWithObjParameter = new HashSet<String>();
    static long allNounNcmodCount=0;
    static long allObjectCount=0;
    static long allSubjectCount=0;
    static long allVerbDobjCount=0;
    static long allVerbNcsubjCount=0;
    static long allVerbPrepCount=0;
    static long allVerbObj2Count=0;
    static long allAdjNounCount=0;
    static long allAdvWordCount=0;
    static long allWordCount=0;
    static HashSet<String> allInputWords = new HashSet<String>();
    
    
}
