package extinfparsed;

import static extinfparsed.ExtInfParsedParam.*;
import static extinfparsed.ExtInfParsedUtil.out;
import java.util.HashMap;
import java.util.HashSet;
import util.Paraphrase;

/**
 * The class printing out the extracted information produced by the dependency-relation-based method to a file.
 * 
 * @author Dobó
 */
public class ExtInfParsedPrinter {
    
    
    /**
     * This method prints out all the information to the information file.
     */
    public static void printOutExtractedInformation(){
        
        printOutStringLong("nounSimpleFrequencies", nounFrequencies);
        
        printOutParaphraseLong("verbWithObjectSimpleFrequencies", verbWithObjectFrequencies);
        printOutParaphraseLong("verbWithSubjectSimpleFrequencies", verbWithSubjectFrequencies);
        printOutStringLong("ncmodWithNounSimpleFrequencies", ncmodWithNounFrequencies);
        
        printOutStringLong("nounFrequencies", nounFrequencies);
        
        printOutParaphraseLong("verbWithObjectFrequencies", verbWithObjectFrequencies);
        printOutParaphraseLong("verbWithSubjectFrequencies", verbWithSubjectFrequencies);
        printOutStringLong("ncmodWithNounFrequencies", ncmodWithNounFrequencies);
        
        printOutStringLong("objectFrequencies", objectFrequencies);
        printOutStringLong("subjectFrequencies", subjectFrequencies);
        printOutStringLong("nounWithNcmodFrequencies", nounWithNcmodFrequencies);
        
        printOutStringParaphraseLong("objVerbSimpleTuples", objVerbTuples);
        printOutStringParaphraseLong("subjVerbSimpleTuples", subjVerbTuples);     
        printOutStringStringLong("nounNcmodSimpleTuples", nounNcmodTuples);
        
        printOutStringParaphraseLong("objVerbTuples", objVerbTuples);
        printOutStringParaphraseLong("subjVerbTuples", subjVerbTuples);     
        printOutStringStringLong("nounNcmodTuples", nounNcmodTuples);
        
        printOutParaphraseSize("verbObjectTuples", verbObjectTuples);
        printOutParaphraseSize("verbSubjectTuples", verbSubjectTuples);
        printOutStringSize("ncmodNounTuples", ncmodNounTuples);
                
        HashMap<String, Long[]> KNSNCountsMap = new HashMap<String, Long[]>();
        
        printOutOrSaveKNSNCounts(null, objVerbTuples, KNSNCountsMap, null, true, false, false);
        printOutOrSaveKNSNCounts(null, subjVerbTuples, KNSNCountsMap, null, true, false, false);
        printOutOrSaveKNSNCounts("nounKNSNCounts", nounNcmodTuples, KNSNCountsMap, null, true, false, true);
        
        long[] allKNSNCounts = new long[4];
        
        printOutOrSaveKNSNCounts("verbObjectTuplesKNSNCounts", verbObjectTuples, null, allKNSNCounts, false, true, true);
        printOutOrSaveKNSNCounts("verbSubjectTuplesKNSNCounts", verbSubjectTuples, null, allKNSNCounts, false, true, true);
        printOutOrSaveKNSNCounts("ncmodNounTuplesKNSNCounts", ncmodNounTuples, null, allKNSNCounts, false, false, true);
        
        printOutAllKNSNCounts("allNounKNSNCounts", allKNSNCounts);
        

        
        printOutStringLong("verbSimpleFrequencies", verbFrequencies);
        
        printOutStringLong("dobjWithVerbSimpleFrequencies", dobjWithVerbFrequencies);
        printOutStringLong("ncsubjWithVerbSimpleFrequencies", ncsubjWithVerbFrequencies);
        printOutStringLong("prepWithVerbSimpleFrequencies", prepWithVerbFrequencies);
        printOutStringLong("obj2WithVerbSimpleFrequencies", obj2WithVerbFrequencies);
        
        printOutStringLong("verbFrequencies", verbFrequencies);
        
        printOutStringLong("dobjWithVerbFrequencies", dobjWithVerbFrequencies);
        printOutStringLong("ncsubjWithVerbFrequencies", ncsubjWithVerbFrequencies);
        printOutStringLong("prepWithVerbFrequencies", prepWithVerbFrequencies);
        printOutStringLong("obj2WithVerbFrequencies", obj2WithVerbFrequencies);
        
        printOutStringLong("verbWithDobjFrequencies", verbWithDobjFrequencies);
        printOutStringLong("verbWithNcsubjFrequencies", verbWithNcsubjFrequencies);
        printOutStringLong("verbWithPrepFrequencies", verbWithPrepFrequencies);
        printOutStringLong("verbWithObj2Frequencies", verbWithObj2Frequencies);
        
        printOutStringStringLong("verbDobjSimpleTuples", verbDobjTuples);
        printOutStringStringLong("verbNcsubjSimpleTuples", verbNcsubjTuples);
        printOutStringStringLong("verbPrepSimpleTuples", verbPrepTuples);
        printOutStringStringLong("verbObj2SimpleTuples", verbObj2Tuples);
        
        printOutStringStringLong("verbDobjTuples", verbDobjTuples);
        printOutStringStringLong("verbNcsubjTuples", verbNcsubjTuples);
        printOutStringStringLong("verbPrepTuples", verbPrepTuples);
        printOutStringStringLong("verbObj2Tuples", verbObj2Tuples);
        
        printOutStringSize("dobjVerbTuples", dobjVerbTuples);
        printOutStringSize("ncsubjVerbTuples", ncsubjVerbTuples);
        printOutStringSize("prepVerbTuples", prepVerbTuples);
        printOutStringSize("obj2VerbTuples", obj2VerbTuples);
        
        KNSNCountsMap = new HashMap<String, Long[]>();
        
        printOutOrSaveKNSNCounts(null, verbDobjTuples, KNSNCountsMap, null, true, false, false);
        printOutOrSaveKNSNCounts(null, verbNcsubjTuples, KNSNCountsMap, null, true, false, false);
        printOutOrSaveKNSNCounts(null, verbPrepTuples, KNSNCountsMap, null, true, false, false);
        printOutOrSaveKNSNCounts("verbKNSNCounts", verbObj2Tuples, KNSNCountsMap, null, true, false, true);
        
        allKNSNCounts = new long[4];
        
        printOutOrSaveKNSNCounts("dobjVerbTuplesKNSNCounts", dobjVerbTuples, null, allKNSNCounts, false, false, true);
        printOutOrSaveKNSNCounts("ncsubjVerbTuplesKNSNCounts", ncsubjVerbTuples, null, allKNSNCounts, false, false, true);
        printOutOrSaveKNSNCounts("prepVerbTuplesKNSNCounts", prepVerbTuples, null, allKNSNCounts, false, false, true);
        printOutOrSaveKNSNCounts("obj2VerbTuplesKNSNCounts", obj2VerbTuples, null, allKNSNCounts, false, false, true);
        
        printOutAllKNSNCounts("allVerbKNSNCounts", allKNSNCounts);

    
        
        printOutStringLong("adjectiveSimpleFrequencies", adjectiveFrequencies);
        
        printOutStringLong("nounWithAdjSimpleFrequencies", nounWithAdjFrequencies);
        
        printOutStringLong("adjectiveFrequencies", adjectiveFrequencies);
        
        printOutStringLong("nounWithAdjFrequencies", nounWithAdjFrequencies);
        
        printOutStringLong("adjWithNounFrequencies", adjWithNounFrequencies);
        
        printOutStringStringLong("adjNounSimpleTuples", adjNounTuples);
        
        printOutStringStringLong("adjNounTuples", adjNounTuples);
        
        printOutStringSize("nounAdjTuples", nounAdjTuples);
        
        KNSNCountsMap = new HashMap<String, Long[]>();
        
        printOutOrSaveKNSNCounts("adjectiveKNSNCounts", adjNounTuples, KNSNCountsMap, null, true, false, true);
        
        allKNSNCounts = new long[4];
        
        printOutOrSaveKNSNCounts("nounAdjTuplesKNSNCounts", nounAdjTuples, null, allKNSNCounts, false, false, true);
        
        printOutAllKNSNCounts("allAdjectiveKNSNCounts", allKNSNCounts);
        
        
        
        printOutStringLong("adverbSimpleFrequencies", adverbFrequencies);
        
        printOutStringLong("wordWithAdvSimpleFrequencies", wordWithAdvFrequencies);
        
        printOutStringLong("adverbFrequencies", adverbFrequencies);
        
        printOutStringLong("wordWithAdvFrequencies", wordWithAdvFrequencies);
        
        printOutStringLong("advWithWordFrequencies", advWithWordFrequencies);
        
        printOutStringStringLong("advWordSimpleTuples", advWordTuples);
        
        printOutStringStringLong("advWordTuples", advWordTuples);
        
        printOutStringSize("wordAdvTuples", wordAdvTuples);

        KNSNCountsMap = new HashMap<String, Long[]>();
        
        printOutOrSaveKNSNCounts("adverbKNSNCounts", advWordTuples, KNSNCountsMap, null, true, false, true);
        
        allKNSNCounts = new long[4];
        
        printOutOrSaveKNSNCounts("wordAdvTuplesKNSNCounts", wordAdvTuples, null, allKNSNCounts, false, false, true);
        
        printOutAllKNSNCounts("allAdverbKNSNCounts", allKNSNCounts);
        
        
        
        printOutAllCounts();
        
        
        
        out.close();

    }
    
    /**
     * This method prints out the information stored in a HashMap&lt;String, HashMap&lt;Paraphrase, Long&gt;&gt;.
     * @param name type of information
     * @param tuplesMap the map in which the information is stored
     */
    public static void printOutStringParaphraseLong(String name, HashMap<String, HashMap<Paraphrase, Long>> tuplesMap){
        
        out.println("!"+name);
        
        StringBuilder b;

        for(String word1: tuplesMap.keySet()){
            HashMap<Paraphrase, Long> table = tuplesMap.get(word1);
            b=new StringBuilder(word1);
            for(Paraphrase p: table.keySet()){
                b.append("\u001c").append(p.passive ? "t\u001e" : "f\u001e").append(p.verb).append("\u001e").append(p.prep).append("\u001d").append(table.get(p));
            }
            out.println(b);
        }
        
    }
    
    
    /**
     * This method prints out the information stored in a HashMap&lt;String, HashMap&lt;String, Long&gt;&gt;.
     * @param name type of information
     * @param tuplesMap the map in which the information is stored
     */
    public static void printOutStringStringLong(String name, HashMap<String, HashMap<String, Long>> tuplesMap){
        
        out.println("!"+name);
        
        StringBuilder b;

        for(String word1: tuplesMap.keySet()){
            HashMap<String, Long> table = tuplesMap.get(word1);
            b=new StringBuilder(word1);
            for(String word2: table.keySet()){
                b.append("\u001c").append(word2).append("\u001d").append(table.get(word2));
            }
            out.println(b);
        }
        
    }
    
    
    /**
     * This method prints out the size of HashMap&lt;String, Long&gt; stored in a HashMap&lt;Paraphrase, HashMap&lt;String, Long&gt;&gt;.
     * @param name type of information
     * @param tuplesMap the map in which the information is stored
     */
    public static void printOutParaphraseSize(String name, HashMap<Paraphrase, HashMap<String, Long>> tuplesMap){
        
        out.println("!"+name);
        
        StringBuilder b;

        for(Paraphrase paraphrase: tuplesMap.keySet()){
            b=new StringBuilder(paraphrase.passive ? "t\u001e" : "f\u001e").append(paraphrase.verb).append("\u001e").append(paraphrase.prep).append("\u001d").append(tuplesMap.get(paraphrase).size());
            out.println(b);
        }
        
    }
    
    /**
     * This method prints out the size of HashMap&lt;String, Long&gt; stored in HashMap&lt;String, HashMap&lt;String, Long&gt;&gt;.
     * @param name type of information
     * @param tuplesMap the map in which the information is stored
     */
    public static void printOutStringSize(String name, HashMap<String, HashMap<String, Long>> tuplesMap){
        
        out.println("!"+name);
        
        StringBuilder b;

        for(String word1: tuplesMap.keySet()){
            b=new StringBuilder(word1).append("\u001d").append(tuplesMap.get(word1).size());
            out.println(b);
        }
        
    }
    
    
    /**
     * This method prints out the information stored in a HashMap&lt;Paraphrase, Long&gt;.
     * @param name type of information
     * @param tuplesMap the map in which the information is stored
     */
    public static void printOutParaphraseLong(String name, HashMap<Paraphrase, Long> tuplesMap){
        
        out.println("!"+name);
        
        StringBuilder b;

        for(Paraphrase paraphrase: tuplesMap.keySet()){
            b=new StringBuilder(paraphrase.passive ? "t\u001e" : "f\u001e").append(paraphrase.verb).append("\u001e").append(paraphrase.prep).append("\u001d").append(tuplesMap.get(paraphrase));
            out.println(b);
        }
        
    }
    
    /**
     * This method prints out the information stored in a HashMap&lt;String, Long&gt;.
     * @param name type of information
     * @param tuplesMap the map in which the information is stored
     */
    public static void printOutStringLong(String name, HashMap<String, Long> tuplesMap){
        
        out.println("!"+name);
        
        StringBuilder b;

        for(String word: tuplesMap.keySet()){
            b=new StringBuilder(word).append("\u001d").append(tuplesMap.get(word));
            out.println(b);
        }
        
    }
    
    
    
    
    /**
     * This function is used for printing out the KNS N counts for words and features. In case of words 
     * it is used in a way that it first accumulates all the information from all feature types, and only then printes them out.
     * In case of features, there is no need for information accumulation, so everything is printed out staight away.
     * @param <T> the type of keys in @param tuplesMap
     * @param <V> the type of keys in the HashMaps inside @param tuplesMap
     * @param name the name of the information to be printed out
     * @param tuplesMap the map containing the (word, feature) pairs
     * @param KNSNCountsMap the map in which the KNSNCounts for the words are accumulated
     * @param allKNSNCounts the array in which the allKNSNCounts for the given POS are accumulated
     * @param word2IsTheFeature a boolean denoting whether the keys in the HashMaps inside @param tuplesMap are features (or words)
     * @param word1TypeIsParaphrase a boolean denoting whether the keys in @param tuplesMap are of the type Paraphrase (or String)
     * @param printOutKNSNCounts a boolean denoting whether the accumulated information should be printed out
     */
    public static <T, V> void printOutOrSaveKNSNCounts(String name, HashMap<T, HashMap<V, Long>> tuplesMap, HashMap<T, Long[]> KNSNCountsMap, long[] allKNSNCounts, 
            boolean word2IsTheFeature, boolean word1TypeIsParaphrase, boolean printOutKNSNCounts){
        
        if(printOutKNSNCounts){
            out.println("!"+name);
        }
        
        long freq;
        
        StringBuilder b;

        for(T word1: tuplesMap.keySet()){
            Long[] word1KNSNCounts = new Long[]{0l, 0l, 0l, 0l};
            HashMap<V, Long> word1Table = tuplesMap.get(word1);
            for (V word2: word1Table.keySet()){
                freq=word1Table.get(word2);
                if(freq>0){
                    word1KNSNCounts[0]++;
                    if(!word2IsTheFeature){
                        allKNSNCounts[0]++;
                    }
                    if(freq>=3){
                        word1KNSNCounts[3]++;
                        if(!word2IsTheFeature){
                            allKNSNCounts[3]++;
                        }
                    }else{
                        word1KNSNCounts[(int)freq]++;
                        if(!word2IsTheFeature){
                            allKNSNCounts[(int)freq]++;
                        }
                    }
                }
            }
            
            if(word2IsTheFeature){
                Long[] storedWord1KNSNCounts = KNSNCountsMap.get(word1);
                if(storedWord1KNSNCounts!=null){
                    for(int i=0;i<4;i++){
                        storedWord1KNSNCounts[i]+=word1KNSNCounts[i];
                    }
                    word1KNSNCounts=storedWord1KNSNCounts;
                }else{
                    KNSNCountsMap.put(word1, word1KNSNCounts);
                }
            }
            
            if(printOutKNSNCounts){
                if(word1TypeIsParaphrase){
                    Paraphrase paraphrase = (Paraphrase) word1;
                    b=new StringBuilder(paraphrase.passive ? "t\u001e" : "f\u001e").append(paraphrase.verb).append("\u001e").append(paraphrase.prep);
                }else{
                    b=new StringBuilder((String) word1);
                }
                for(int i=0;i<4;i++){
                    b.append("\u001d").append(word1KNSNCounts[i]);
                }
                out.println(b);
            }
            
        }
        
    }
    
    /**
     * This method prints out the all KNS N counts for a given POS.
     * @param name the name of the information to be printed out
     * @param allKNSNCounts the array in which the allKNSNCounts for the given POS are stored
     */
    public static void printOutAllKNSNCounts(String name, long[] allKNSNCounts){
        
        out.println("!"+name);
        
        for(int i=0;i<4;i++){
            out.println(allKNSNCounts[i]);
        }
        
    }
    
    
    
    
    /**
     * This method prints out the information stored in the allXXXCount variables.
     */
    public static void printOutAllCounts(){
        
        out.println("!allCounts");
        
        out.println(allNounNcmodCount);
        out.println(allObjectCount);
        out.println(allSubjectCount);
        out.println(allVerbDobjCount);
        out.println(allVerbNcsubjCount);
        out.println(allVerbPrepCount);
        out.println(allVerbObj2Count);
        out.println(allAdjNounCount);
        out.println(allAdvWordCount);
        
        out.println(allWordCount);
        
        

        HashSet<String> allSet = new HashSet<String>();

        for(Paraphrase paraphrase: verbObjectTuples.keySet()){
            allSet.addAll(verbObjectTuples.get(paraphrase).keySet());
        }

        for(Paraphrase paraphrase: verbSubjectTuples.keySet()){
            allSet.addAll(verbSubjectTuples.get(paraphrase).keySet());
        }

        for(String str: ncmodNounTuples.keySet()){
            allSet.addAll(ncmodNounTuples.get(str).keySet());
        }
        
        out.println(allSet.size());
        

        allSet = new HashSet<String>();

        for(String str: dobjVerbTuples.keySet()){
            allSet.addAll(dobjVerbTuples.get(str).keySet());
        }

        for(String str: ncsubjVerbTuples.keySet()){
            allSet.addAll(ncsubjVerbTuples.get(str).keySet());
        }

        for(String str: prepVerbTuples.keySet()){
            allSet.addAll(prepVerbTuples.get(str).keySet());
        }

        for(String str: obj2VerbTuples.keySet()){
            allSet.addAll(obj2VerbTuples.get(str).keySet());
        }
        
        out.println(allSet.size());
        
        
        allSet = new HashSet<String>();

        for(String str: nounAdjTuples.keySet()){
            allSet.addAll(nounAdjTuples.get(str).keySet());
        }
        
        out.println(allSet.size());
        
        allSet = new HashSet<String>();

        for(String str: wordAdvTuples.keySet()){
            allSet.addAll(wordAdvTuples.get(str).keySet());
        }
        
        out.println(allSet.size());
        
        
        out.println(verbObjectTuples.size()+verbSubjectTuples.size()+ncmodNounTuples.size());
        out.println(dobjVerbTuples.size()+ncsubjVerbTuples.size()+prepVerbTuples.size()+obj2VerbTuples.size());
        out.println(nounAdjTuples.size());
        out.println(wordAdvTuples.size());
        
        
    }
    
    
}
