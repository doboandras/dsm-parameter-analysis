package extinfbow;

import static extinfbow.ExtInfBowParam.*;
import static extinfbow.ExtInfBowUtil.out;
import java.util.HashMap;
import java.util.HashSet;
import util.Paraphrase;

/**
 * The class printing out the extracted information produced by the bag-of-words method to a file.
 * 
 * @author Dobó
 */
public class ExtInfBowPrinter {
    
    /**
     * This method prints out all the information to the information file.
     */
    public static void printOutExtractedInformation(){
        
        printOutStringLong("nounSimpleFrequencies", nounSimpleFrequencies);
        
        printOutParaphraseLong("verbWithObjectSimpleFrequencies", new HashMap<Paraphrase, Long>());
        printOutParaphraseLong("verbWithSubjectSimpleFrequencies", new HashMap<Paraphrase, Long>());
        printOutStringLong("ncmodWithNounSimpleFrequencies", ncmodWithNounSimpleFrequencies);
        
        printOutStringLong("nounFrequencies", nounFrequencies);
        
        printOutParaphraseLong("verbWithObjectFrequencies", new HashMap<Paraphrase, Long>());
        printOutParaphraseLong("verbWithSubjectFrequencies", new HashMap<Paraphrase, Long>());
        printOutStringLong("ncmodWithNounFrequencies", ncmodWithNounFrequencies);
        
        printOutStringLong("objectFrequencies", new HashMap<String, Long>());
        printOutStringLong("subjectFrequencies", new HashMap<String, Long>());
        printOutStringLong("nounWithNcmodFrequencies", nounWithNcmodFrequencies);
        
        printOutStringParaphraseLong("objVerbSimpleTuples", new HashMap<String, HashMap<Paraphrase, Long>>());
        printOutStringParaphraseLong("subjVerbSimpleTuples", new HashMap<String, HashMap<Paraphrase, Long>>());     
        printOutStringStringLong("nounNcmodSimpleTuples", nounNcmodSimpleTuples);
        
        printOutStringParaphraseLong("objVerbTuples", new HashMap<String, HashMap<Paraphrase, Long>>());
        printOutStringParaphraseLong("subjVerbTuples", new HashMap<String, HashMap<Paraphrase, Long>>());     
        printOutStringStringLong("nounNcmodTuples", nounNcmodTuples);
        
        printOutParaphraseSize("verbObjectTuples", new HashMap<Paraphrase, HashMap<String, Long>>());
        printOutParaphraseSize("verbSubjectTuples", new HashMap<Paraphrase, HashMap<String, Long>>());
        printOutStringSize("ncmodNounTuples", ncmodNounTuples);
                
        HashMap<String, Long[]> KNSNCountsMap = new HashMap<String, Long[]>();
        
        printOutOrSaveKNSNCounts("nounKNSNCounts", nounNcmodTuples, KNSNCountsMap, null, true, false, true);
        
        long[] allKNSNCounts = new long[4];
        
        printOutOrSaveKNSNCounts("verbObjectTuplesKNSNCounts", new HashMap<Paraphrase, HashMap<String, Long>>(), null, allKNSNCounts, false, true, true);
        printOutOrSaveKNSNCounts("verbSubjectTuplesKNSNCounts", new HashMap<Paraphrase, HashMap<String, Long>>(), null, allKNSNCounts, false, true, true);
        printOutOrSaveKNSNCounts("ncmodNounTuplesKNSNCounts", ncmodNounTuples, null, allKNSNCounts, false, false, true);
        
        printOutAllKNSNCounts("allNounKNSNCounts", allKNSNCounts);
        

        
        printOutStringLong("verbSimpleFrequencies", verbSimpleFrequencies);
        
        printOutStringLong("dobjWithVerbSimpleFrequencies", dobjWithVerbSimpleFrequencies);
        printOutStringLong("ncsubjWithVerbSimpleFrequencies", new HashMap<String, Long>());
        printOutStringLong("prepWithVerbSimpleFrequencies", new HashMap<String, Long>());
        printOutStringLong("obj2WithVerbSimpleFrequencies", new HashMap<String, Long>());
        
        printOutStringLong("verbFrequencies", verbFrequencies);
        
        printOutStringLong("dobjWithVerbFrequencies", dobjWithVerbFrequencies);
        printOutStringLong("ncsubjWithVerbFrequencies", new HashMap<String, Long>());
        printOutStringLong("prepWithVerbFrequencies", new HashMap<String, Long>());
        printOutStringLong("obj2WithVerbFrequencies", new HashMap<String, Long>());
        
        printOutStringLong("verbWithDobjFrequencies", verbWithDobjFrequencies);
        printOutStringLong("verbWithNcsubjFrequencies", new HashMap<String, Long>());
        printOutStringLong("verbWithPrepFrequencies", new HashMap<String, Long>());
        printOutStringLong("verbWithObj2Frequencies", new HashMap<String, Long>());
        
        printOutStringStringLong("verbDobjSimpleTuples", verbDobjSimpleTuples);
        printOutStringStringLong("verbNcsubjSimpleTuples", new HashMap<String, HashMap<String, Long>>());
        printOutStringStringLong("verbPrepSimpleTuples", new HashMap<String, HashMap<String, Long>>());
        printOutStringStringLong("verbObj2SimpleTuples", new HashMap<String, HashMap<String, Long>>());
        
        printOutStringStringLong("verbDobjTuples", verbDobjTuples);
        printOutStringStringLong("verbNcsubjTuples", new HashMap<String, HashMap<String, Long>>());
        printOutStringStringLong("verbPrepTuples", new HashMap<String, HashMap<String, Long>>());
        printOutStringStringLong("verbObj2Tuples", new HashMap<String, HashMap<String, Long>>());
        
        printOutStringSize("dobjVerbTuples", dobjVerbTuples);
        printOutStringSize("ncsubjVerbTuples", new HashMap<String, HashMap<String, Long>>());
        printOutStringSize("prepVerbTuples", new HashMap<String, HashMap<String, Long>>());
        printOutStringSize("obj2VerbTuples", new HashMap<String, HashMap<String, Long>>());
        
        KNSNCountsMap = new HashMap<String, Long[]>();
        
        printOutOrSaveKNSNCounts("verbKNSNCounts", verbDobjTuples, KNSNCountsMap, null, true, false, true);
        
        allKNSNCounts = new long[4];
        
        printOutOrSaveKNSNCounts("dobjVerbTuplesKNSNCounts", dobjVerbTuples, null, allKNSNCounts, false, false, true);
        printOutOrSaveKNSNCounts("ncsubjVerbTuplesKNSNCounts", new HashMap<String, HashMap<String, Long>>(), null, allKNSNCounts, false, false, true);
        printOutOrSaveKNSNCounts("prepVerbTuplesKNSNCounts", new HashMap<String, HashMap<String, Long>>(), null, allKNSNCounts, false, false, true);
        printOutOrSaveKNSNCounts("obj2VerbTuplesKNSNCounts", new HashMap<String, HashMap<String, Long>>(), null, allKNSNCounts, false, false, true);
        
        printOutAllKNSNCounts("allVerbKNSNCounts", allKNSNCounts);

    
        
        printOutStringLong("adjectiveSimpleFrequencies", adjectiveSimpleFrequencies);
        
        printOutStringLong("nounWithAdjSimpleFrequencies", nounWithAdjSimpleFrequencies);
        
        printOutStringLong("adjectiveFrequencies", adjectiveFrequencies);
        
        printOutStringLong("nounWithAdjFrequencies", nounWithAdjFrequencies);
        
        printOutStringLong("adjWithNounFrequencies", adjWithNounFrequencies);
        
        printOutStringStringLong("adjNounSimpleTuples", adjNounSimpleTuples);
        
        printOutStringStringLong("adjNounTuples", adjNounTuples);
        
        printOutStringSize("nounAdjTuples", nounAdjTuples);
        
        KNSNCountsMap = new HashMap<String, Long[]>();
        
        printOutOrSaveKNSNCounts("adjectiveKNSNCounts", adjNounTuples, KNSNCountsMap, null, true, false, true);
        
        allKNSNCounts = new long[4];
        
        printOutOrSaveKNSNCounts("nounAdjTuplesKNSNCounts", nounAdjTuples, null, allKNSNCounts, false, false, true);
        
        printOutAllKNSNCounts("allAdjectiveKNSNCounts", allKNSNCounts);
        
        
        
        printOutStringLong("adverbSimpleFrequencies", adverbSimpleFrequencies);
        
        printOutStringLong("wordWithAdvSimpleFrequencies", wordWithAdvSimpleFrequencies);
        
        printOutStringLong("adverbFrequencies", adverbFrequencies);
        
        printOutStringLong("wordWithAdvFrequencies", wordWithAdvFrequencies);
        
        printOutStringLong("advWithWordFrequencies", advWithWordFrequencies);
        
        printOutStringStringLong("advWordSimpleTuples", advWordSimpleTuples);
        
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
     * @param name
     * @param tuplesMap 
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
     * @param name
     * @param tuplesMap 
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
     * @param name
     * @param tuplesMap 
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
     * @param name
     * @param tuplesMap 
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
     * @param name
     * @param tuplesMap 
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
     * @param name
     * @param tuplesMap 
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
        out.println(0);
        out.println(0);
        out.println(allVerbDobjCount);
        out.println(0);
        out.println(0);
        out.println(0);
        out.println(allAdjNounCount);
        out.println(allAdvWordCount);
        
        out.println(allWordCount);

        

        HashSet<String> allSet = new HashSet<String>();

        for(String str: ncmodNounTuples.keySet()){
            allSet.addAll(ncmodNounTuples.get(str).keySet());
        }
        
        out.println(allSet.size());
        

        allSet = new HashSet<String>();

        for(String str: dobjVerbTuples.keySet()){
            allSet.addAll(dobjVerbTuples.get(str).keySet());
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
        
        
        out.println(ncmodNounTuples.size());
        out.println(dobjVerbTuples.size());
        out.println(nounAdjTuples.size());
        out.println(wordAdvTuples.size());
        

    }
    
    
}
