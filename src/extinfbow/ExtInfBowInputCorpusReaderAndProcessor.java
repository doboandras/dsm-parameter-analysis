package extinfbow;

import static extinfbow.ExtInfBowParam.*;
import static extinfbow.ExtInfBowUtil.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import net.didion.jwnl.JWNLException;
import net.didion.jwnl.data.IndexWord;
import net.didion.jwnl.data.POS;


/**
 *
 * @author Dobó
 */
public class ExtInfBowInputCorpusReaderAndProcessor {
    
    
    /**
     * This function reads in the files of an English corpus to extract information from it, when the corpus is parsed with the newer version of the C&amp;C CCG parser. In this case each file 
     * is in a main directory.
     */
    public static void readFilesEnNewParser(){
        try{
            File dir = new File(corpusLocation);
            int i=0;
            for(String filename : dir.list()){
                readFileEnParser(dir.getAbsolutePath()+"/"+filename);
            }
        }catch(Exception e){
            System.out.println("Error during reading the patents!");
            e.printStackTrace();
            System.exit(1);
        }
    }
    
    /**
     * This function reads in the files of an English corpus to extract information from it, when the corpus is parsed with the older version of the C&amp;C CCG parser. In this case each file 
     * is in a subdirectory of the main directory.
     */
    public static void readFilesEnOldParser(){
        try{
            File maindir = new File(corpusLocation);
            int i=0;
            for(String dirname : maindir.list()){
                File dir = new File(maindir.getAbsolutePath()+"/"+dirname);
                for(String filename : dir.list()){
                    readFileEnParser(dir.getAbsolutePath()+"/"+filename);
                }
            }
        }catch(Exception e){
            System.out.println("Error during reading the BNC!");
            e.printStackTrace();
            System.exit(1);
        }
    }
    
    
    /**
     * This function reads a file of the parsed English corpus to extract information from it. As this is a bag-of-words approach, it does not use the grammatical relation (the lines starting with a 
     * bracket), it only uses the sentences (the lines starting with &gt;). It makes every line lowercase, and replaces some special characters (those special
     * characters are used in the information files). In case of there are sentence frequencies at the end of each sentence (e.g. Web1T5GramCorpus), then it uses those sentence frequencies.
     * @param filePath the path of the file
     * @throws Exception exception
     */
    public static void readFileEnParser(String filePath) throws Exception{
        
        BufferedReader in = new BufferedReader(new FileReader(filePath));
        String line;
        while((line=in.readLine())!=null){
            
            if(line.startsWith("<")){
                
                line=line.toLowerCase();
                if(line.contains("\u001c") || line.contains("\u001d") || line.contains("\u001e")){
                    line=line.replace("\u001c", ";");
                    line=line.replace("\u001d", ";");
                    line=line.replace("\u001e", ";");
                }
                
                long numberOfSentences;
                if(sourceType!=SourceType.EnParserV2WithSentenceCounts){
                    numberOfSentences=1l;
                }else{
                    String[] lineParts = line.split("\t");
                    line=lineParts[0];
                    numberOfSentences = Long.parseLong(lineParts[1]);
                }
                
                //The position of the POS tag is different in case of the older and the newer version of the parser.
                if(sourceType==SourceType.EnParserV1){
                    processLineEnParsed(line, numberOfSentences, 2);
                }else{
                    processLineEnParsed(line, numberOfSentences, 1);
                }

            }
        }
        in.close();
        
    }
    
    
    /**
     * This function is responsible for processing the lines of a parsed English corpus and for extracting the necessary information. It first separates the words form their POS tag.
     * Then, for each word it calls the apropriate function, based on the word's POS, which extracts the relevant infromation from the neighborhood of the word.
     * @param line the line
     * @param numberOfSentences the frequency of the sentence (the number of times it occured in the corpus)
     * @param positionOfPOSTag the position of the POS tag
     */
    public static void processLineEnParsed(String line, long numberOfSentences, int positionOfPOSTag){
        
        try{
        
            String[] words = line.split(" ");
            String[] POSTags = new String[words.length];
            allWordCount+=words.length-1;
            for(int i=1;i<words.length;i++){
                String[] parts = words[i].replace("|", " ").split(" ");
                words[i] = parts[0];
                POSTags[i] = parts[positionOfPOSTag];
            }
            for(int i=1;i<words.length;i++){
                
                if((POSTags[i].equals("nn") || POSTags[i].equals("nns"))){
                    long count=processLineEnParsed(words, i, POS.NOUN, nounNcmodTuples, nounNcmodSimpleTuples, ncmodNounTuples, 
                            ncmodWithNounFrequencies, nounWithNcmodFrequencies, 
                            nounFrequencies, nounSimpleFrequencies, numberOfSentences);
                    allNounNcmodCount+=count;
                }else if(POSTags[i].startsWith("v")){
                    long count=processLineEnParsed(words, i, POS.VERB, verbDobjTuples, verbDobjSimpleTuples, dobjVerbTuples, 
                            dobjWithVerbFrequencies, verbWithDobjFrequencies, 
                            verbFrequencies, verbSimpleFrequencies, numberOfSentences);
                    allVerbDobjCount+=count;
                }else if(POSTags[i].startsWith("jj")){
                    long count=processLineEnParsed(words, i, POS.ADJECTIVE, adjNounTuples, adjNounSimpleTuples, nounAdjTuples, 
                            nounWithAdjFrequencies, adjWithNounFrequencies, 
                            adjectiveFrequencies, adjectiveSimpleFrequencies, numberOfSentences);
                    allAdjNounCount+=count;
                }else if(POSTags[i].startsWith("rb")){
                    long count=processLineEnParsed(words, i, POS.ADVERB, advWordTuples, advWordSimpleTuples, wordAdvTuples, 
                            wordWithAdvFrequencies, advWithWordFrequencies, 
                            adverbFrequencies, adverbSimpleFrequencies, numberOfSentences);
                    allAdvWordCount+=count;
                }
                
                saveStringLong(words[i], numberOfSentences, ncmodWithNounSimpleFrequencies);
                saveStringLong(words[i], numberOfSentences, dobjWithVerbSimpleFrequencies);
                saveStringLong(words[i], numberOfSentences, nounWithAdjSimpleFrequencies);
                saveStringLong(words[i], numberOfSentences, wordWithAdvSimpleFrequencies);
                
            }
        
        } catch (JWNLException ex) {
            System.out.println("Error during using the JWNL!");
            ex.printStackTrace();
            System.exit(1);
        }
    }
    
    /**
     * This function extracts the relevant infromation from the neighborhood of the word. It first determines which words are inside the used window. Then, it determines the lemma of the
 current word. Finally, it extracts the words in the window and saves them to the relevant map/set with a frequency that decreases quadratically with the distance. A part of the 
 information is only stored for those words that are in the allInputWords set (further, the wordType==WordType.WordsInWn version of this method stores the information for only 
 those words that are included in wordnet).
     * @param words the words in the sentence
     * @param i the position of the current word
     * @param pos the pos of the current word
     * @param tuplesMap1 the map in which the (word, feature) pairs with their frequency are stored
     * @param simpleTuplesMap1 the map in which the (word, feature) pairs with their frequency are stored, with everything counted only once
     * @param tuplesMap2 the map in which the (feature, word) pairs with their frequency are stored
     * @param frequencyMap1 the map in which the frequencies of the features with the given type of word are stored
     * @param frequencyMap2 the map in which the frequencies of the words with the given type of features are stored
     * @param frequencyMap3 the map in which the frequencies of the words of a given POS are stored
     * @param simpleFrequencyMap3 the map in which the frequencies of the words of a given POS are stored, with everything counted only once
     * @param numberOfSentences the sentence frequency
     * @return the number, by which the relevant allXXXCount variable should be increased
     * @throws JWNLException exception
     */
    public static long processLineEnParsed(String[] words, int i, POS pos, HashMap<String, HashMap<String, Long>> tuplesMap1, 
            HashMap<String, HashMap<String, Long>> simpleTuplesMap1, HashMap<String, HashMap<String, Long>> tuplesMap2, 
            HashMap<String, Long> frequencyMap1, HashMap<String, Long> frequencyMap2, HashMap<String, Long> frequencyMap3, 
            HashMap<String, Long> simpleFrequencyMap3, long numberOfSentences) throws JWNLException{
        
        String word = words[i];
        int startPosition = (i-windowSize>0) ? (i-windowSize) : 1;
        int endPosition = (i+windowSize+1<=words.length) ? (i+windowSize+1) : words.length;
        
        if(word.split("-").length<4 && word.matches("[a-zA-Z-]*")){
            IndexWord iw = dict.lookupIndexWord(pos, word);
            if (iw != null) {
                word = iw.getLemma();
            }
        }
        
        saveStringLong(word, numberOfSentences, simpleFrequencyMap3);

        if(allInputWords.contains(word) || !extractInformaionJustForInputWords){
            
            for(int j=startPosition;j<i;j++){
                long weight;
                if(weightingScheme==WeightingScheme.Uniform){
                    weight = numberOfSentences;
                }else if(weightingScheme==WeightingScheme.Linear){
                    weight = numberOfSentences*Math.round(windowSize-Math.abs(i-j)+1);
                }else{
                    weight = numberOfSentences*Math.round(Math.pow(windowSize-Math.abs(i-j)+1, 2));
                }
                saveStringLong(word, weight, frequencyMap3);
                saveStringLong(word, weight, frequencyMap2);
                saveStringStringLong(word, words[j], weight, tuplesMap1);
                saveStringStringLong(word, words[j], numberOfSentences, simpleTuplesMap1);
            }
            
            for(int j=i+1;j<endPosition;j++){
                long weight;
                if(weightingScheme==WeightingScheme.Uniform){
                    weight = numberOfSentences;
                }else if(weightingScheme==WeightingScheme.Linear){
                    weight = numberOfSentences*Math.round(windowSize-Math.abs(i-j)+1);
                }else{
                    weight = numberOfSentences*Math.round(Math.pow(windowSize-Math.abs(i-j)+1, 2));
                }
                saveStringLong(word, weight, frequencyMap3);
                saveStringLong(word, weight, frequencyMap2);
                saveStringStringLong(word, words[j], weight, tuplesMap1);
                saveStringStringLong(word, words[j], numberOfSentences, simpleTuplesMap1);
            }

        }
        
        long allCount=0;
        
        for(int j=startPosition;j<i;j++){
            long weight;
            if(weightingScheme==WeightingScheme.Uniform){
                weight = numberOfSentences;
            }else if(weightingScheme==WeightingScheme.Linear){
                weight = numberOfSentences*Math.round(windowSize-Math.abs(i-j)+1);
            }else{
                weight = numberOfSentences*Math.round(Math.pow(windowSize-Math.abs(i-j)+1, 2));
            }
            allCount+=weight;
            saveStringLong(words[j], weight, frequencyMap1);
            saveStringStringLong(words[j], word, weight, tuplesMap2);
        }

        for(int j=i+1;j<endPosition;j++){
            long weight;
            if(weightingScheme==WeightingScheme.Uniform){
                weight = numberOfSentences;
            }else if(weightingScheme==WeightingScheme.Linear){
                weight = numberOfSentences*Math.round(windowSize-Math.abs(i-j)+1);
            }else{
                weight = numberOfSentences*Math.round(Math.pow(windowSize-Math.abs(i-j)+1, 2));
            }
            allCount+=weight;
            saveStringLong(words[j], weight, frequencyMap1);
            saveStringStringLong(words[j], word, weight, tuplesMap2);
        }

        return allCount;
        
    }
    
    
    
    /**
     * This function reads in the files of a Hungarian corpus to extract information from it, when the corpus is parsed with the Magyar lanc (Hunposchain) parser. Each file is in a main dir.
     */
    public static void readFilesHuParser(){
        try{
            File dir = new File(corpusLocation);
            int i=0;
            for(String filename : dir.list()){
                readFileHuParser(dir.getAbsolutePath()+"/"+filename);
            }
        }catch(Exception e){
            System.out.println("Error during reading the patents!");
            e.printStackTrace();
            System.exit(1);
        }
    }
    
    /**
     * This function reads a file of the parsed Hungarian corpus to extract information from it. It replaces some special characters (those special
     * characters are used in the information files). It processes the parsed Hungarian corpus by sentences.
     * @param filePath the path of the file
     * @throws Exception exception
     */
    public static void readFileHuParser(String filePath) throws Exception{
        
        boolean newSentence=true;
        ArrayList<String> words = new ArrayList<String>();
        ArrayList<String> POSTags = new ArrayList<String>();
        BufferedReader in = new BufferedReader(new FileReader(filePath));
        String line;
        while((line=in.readLine())!=null){
            
            line=line.toLowerCase();
            
            if(line.contains("\u001c") || line.contains("\u001d") || line.contains("\u001e")){
                line=line.replace("\u001c", ";");
                line=line.replace("\u001d", ";");
                line=line.replace("\u001e", ";");
            }
            if(line.length()==0){
                newSentence=true;
                processLineHuParsed(words, POSTags, 1);
                words = new ArrayList<String>();
                POSTags = new ArrayList<String>();
            }else if(!newSentence || (newSentence && line.startsWith("1\t"))){
                newSentence=false;
                String[] parts = line.split("\t");
                words.add(parts[2]);
                POSTags.add(parts[3]);
            }
            
        }
        
        processLineHuParsed(words, POSTags, 1);
        
        in.close();
        
    }
    
    
    /**
     * This function is responsible for processing the sentences of a parsed Hungarian corpus and for extracting the necessary information. 
     * For each word in the sentence it calls the apropriate function, based on the word's POS, which extracts the relevant infromation from the neighborhood of the word.
     * @param words the ArrayList containing the words of the sentence
     * @param POSTags the ArrayList containing the POS tags of the words of the sentence
     * @param numberOfSentences the sentence frequency
     */
    public static void processLineHuParsed(ArrayList<String> words, ArrayList<String> POSTags, long numberOfSentences){
        
        allWordCount+=words.size();
        for(int i=0;i<words.size();i++){

            if((POSTags.get(i).startsWith("nc"))){
                long count=processLineHuParsedOrEsTagged(words, i, nounNcmodTuples, nounNcmodSimpleTuples, ncmodNounTuples, 
                        ncmodWithNounFrequencies, nounWithNcmodFrequencies, 
                        nounFrequencies, nounSimpleFrequencies, numberOfSentences);
                allNounNcmodCount+=count;
            }else if(POSTags.get(i).startsWith("v")){
                long count=processLineHuParsedOrEsTagged(words, i, verbDobjTuples, verbDobjSimpleTuples, dobjVerbTuples, 
                        dobjWithVerbFrequencies, verbWithDobjFrequencies, 
                        verbFrequencies, verbSimpleFrequencies, numberOfSentences);
                allVerbDobjCount+=count;
            }else if(POSTags.get(i).startsWith("a")){
                long count=processLineHuParsedOrEsTagged(words, i, adjNounTuples, adjNounSimpleTuples, nounAdjTuples, 
                        nounWithAdjFrequencies, adjWithNounFrequencies, 
                        adjectiveFrequencies, adjectiveSimpleFrequencies, numberOfSentences);
                allAdjNounCount+=count;
            }else if(POSTags.get(i).startsWith("r")){
                long count=processLineHuParsedOrEsTagged(words, i, advWordTuples, advWordSimpleTuples, wordAdvTuples, 
                        wordWithAdvFrequencies, advWithWordFrequencies, 
                        adverbFrequencies, adverbSimpleFrequencies, numberOfSentences);
                allAdvWordCount+=count;
            }
            
            saveStringLong(words.get(i), numberOfSentences, ncmodWithNounSimpleFrequencies);
            saveStringLong(words.get(i), numberOfSentences, dobjWithVerbSimpleFrequencies);
            saveStringLong(words.get(i), numberOfSentences, nounWithAdjSimpleFrequencies);
            saveStringLong(words.get(i), numberOfSentences, wordWithAdvSimpleFrequencies);
            
        }
        
    }
    
    /**
     * This function extracts the relevant infromation from the neighborhood of the word in case of Hungarian and Spanish sentences.
     * It first determines which words are inside the used window. Then, it extracts the words in the 
     * window and saves them to the relevant map/set with a frequency that decreases quadratically with the distance. 
     * A part of the information is only stored for those words that are 
     * in the allInputWords set.
     * @param words the words in the sentence
     * @param i the position of the current word
     * @param tuplesMap1 the map in which the (word, feature) pairs with their frequency are stored
     * @param simpleTuplesMap1 the map in which the (word, feature) pairs with their frequency are stored, with everything counted only once
     * @param tuplesMap2 the map in which the (feature, word) pairs with their frequency are stored
     * @param frequencyMap1 the map in which the frequencies of the features with the given type of word are stored
     * @param frequencyMap2 the map in which the frequencies of the words with the given type of features are stored
     * @param frequencyMap3 the map in which the frequencies of the words of a given POS are stored
     * @param simpleFrequencyMap3 the map in which the frequencies of the words of a given POS are stored, with everything counted only once
     * @param numberOfSentences the sentence frequency
     * @return the number, by which the relevant allXXXCount variable should be increased
     */
    public static long processLineHuParsedOrEsTagged(ArrayList<String> words, int i, HashMap<String, HashMap<String, Long>> tuplesMap1, 
            HashMap<String, HashMap<String, Long>> simpleTuplesMap1, HashMap<String, HashMap<String, Long>> tuplesMap2, 
            HashMap<String, Long> frequencyMap1, HashMap<String, Long> frequencyMap2, HashMap<String, Long> frequencyMap3, 
            HashMap<String, Long> simpleFrequencyMap3, long numberOfSentences){
        
        String word = words.get(i);
        int startPosition = (i-windowSize>=0) ? (i-windowSize) : 0;
        int endPosition = (i+windowSize+1<=words.size()) ? (i+windowSize+1) : words.size();
        
        
        saveStringLong(word, numberOfSentences, simpleFrequencyMap3);
        
        
        if(allInputWords.contains(word) || !extractInformaionJustForInputWords){
            
            for(int j=startPosition;j<i;j++){
                long weight;
                if(weightingScheme==WeightingScheme.Uniform){
                    weight = numberOfSentences;
                }else if(weightingScheme==WeightingScheme.Linear){
                    weight = numberOfSentences*Math.round(windowSize-Math.abs(i-j)+1);
                }else{
                    weight = numberOfSentences*Math.round(Math.pow(windowSize-Math.abs(i-j)+1, 2));
                }
                saveStringLong(word, weight, frequencyMap3);
                saveStringLong(word, weight, frequencyMap2);
                saveStringStringLong(word, words.get(j), weight, tuplesMap1);
                saveStringStringLong(word, words.get(j), numberOfSentences, simpleTuplesMap1);
            }
            
            for(int j=i+1;j<endPosition;j++){
                long weight;
                if(weightingScheme==WeightingScheme.Uniform){
                    weight = numberOfSentences;
                }else if(weightingScheme==WeightingScheme.Linear){
                    weight = numberOfSentences*Math.round(windowSize-Math.abs(i-j)+1);
                }else{
                    weight = numberOfSentences*Math.round(Math.pow(windowSize-Math.abs(i-j)+1, 2));
                }
                saveStringLong(word, weight, frequencyMap3);
                saveStringLong(word, weight, frequencyMap2);
                saveStringStringLong(word, words.get(j), weight, tuplesMap1);
                saveStringStringLong(word, words.get(j), numberOfSentences, simpleTuplesMap1);
            }

        }
        
        long allCount=0;
        
        for(int j=startPosition;j<i;j++){
            long weight;
            if(weightingScheme==WeightingScheme.Uniform){
                weight = numberOfSentences;
            }else if(weightingScheme==WeightingScheme.Linear){
                weight = numberOfSentences*Math.round(windowSize-Math.abs(i-j)+1);
            }else{
                weight = numberOfSentences*Math.round(Math.pow(windowSize-Math.abs(i-j)+1, 2));
            }
            allCount+=weight;
            saveStringLong(words.get(j), weight, frequencyMap1);
            saveStringStringLong(words.get(j), word, weight, tuplesMap2);
        }

        for(int j=i+1;j<endPosition;j++){
            long weight;
            if(weightingScheme==WeightingScheme.Uniform){
                weight = numberOfSentences;
            }else if(weightingScheme==WeightingScheme.Linear){
                weight = numberOfSentences*Math.round(windowSize-Math.abs(i-j)+1);
            }else{
                weight = numberOfSentences*Math.round(Math.pow(windowSize-Math.abs(i-j)+1, 2));
            }
            allCount+=weight;
            saveStringLong(words.get(j), weight, frequencyMap1);
            saveStringStringLong(words.get(j), word, weight, tuplesMap2);
        }

        return allCount;
        
    }
    
    
    
    
    /**
     * This function reads in the files of a Spanish corpus to extract information from it, when the corpus is tagged with Freelilng. Each file is in a main dir.
     */
    public static void readFilesEsTagger(){
        try{
            File dir = new File(corpusLocation);
            int i=0;
            for(String filename : dir.list()){
                readFileEsTagger(dir.getAbsolutePath()+"/"+filename);
            }
        }catch(Exception e){
            System.out.println("Error during reading the patents!");
            e.printStackTrace();
            System.exit(1);
        }
    }
    
    
    
    
    
    /**
     * This function reads a file of the tagged Spanish corpus to extract information from it. It replaces some special characters (those special
     * characters are used in the information files). It processes the tagged Spanish corpus by sentences.
     * @param filePath the path of the file
     * @throws Exception exception
     */
    public static void readFileEsTagger(String filePath) throws Exception{
        
        ArrayList<String> words = new ArrayList<String>();
        ArrayList<String> POSTags = new ArrayList<String>();
        BufferedReader in = new BufferedReader(new FileReader(filePath));
        String line;
        while((line=in.readLine())!=null){
            
            if(line.startsWith("ENDOFARTICLE")){
                
                in.readLine();
                
            }else if(!(line.startsWith("<doc") || line.startsWith("</doc"))){
            
                line=line.toLowerCase();

                if(line.contains("\u001c") || line.contains("\u001d") || line.contains("\u001e")){
                    line=line.replace("\u001c", ";");
                    line=line.replace("\u001d", ";");
                    line=line.replace("\u001e", ";");
                }
                
                if(line.length()==0){
                    processLineEsTagged(words, POSTags, 1);
                    words = new ArrayList<String>();
                    POSTags = new ArrayList<String>();
                }else{
                    String[] parts = line.split(" ");
                    words.add(parts[1]);
                    POSTags.add(parts[2]);
                }
                
            }
            
        }
        
        processLineEsTagged(words, POSTags, 1);
        
        in.close();
        
    }
    
    
    /**
     * This function is responsible for processing the sentences of a tagged Spanish corpus and for extracting the necessary information. 
     * For each word in the sentence it calls the apropriate function, based on the word's POS, which extracts the relevant infromation from the neighborhood of the word.
     * @param words the ArrayList containing the words of the sentence
     * @param POSTags the ArrayList containing the POS tags of the words of the sentence
     * @param numberOfSentences the sentence frequency
     */
    public static void processLineEsTagged(ArrayList<String> words, ArrayList<String> POSTags, long numberOfSentences){
        
        allWordCount+=words.size();
        for(int i=0;i<words.size();i++){

            if((POSTags.get(i).startsWith("nc"))){
                long count=processLineHuParsedOrEsTagged(words, i, nounNcmodTuples, nounNcmodSimpleTuples, ncmodNounTuples, 
                        ncmodWithNounFrequencies, nounWithNcmodFrequencies, 
                        nounFrequencies, nounSimpleFrequencies, numberOfSentences);
                allNounNcmodCount+=count;
            }else if(POSTags.get(i).startsWith("v")){
                long count=processLineHuParsedOrEsTagged(words, i, verbDobjTuples, verbDobjSimpleTuples, dobjVerbTuples, 
                        dobjWithVerbFrequencies, verbWithDobjFrequencies, 
                        verbFrequencies, verbSimpleFrequencies, numberOfSentences);
                allVerbDobjCount+=count;
            }else if(POSTags.get(i).startsWith("a")){
                long count=processLineHuParsedOrEsTagged(words, i, adjNounTuples, adjNounSimpleTuples, nounAdjTuples, 
                        nounWithAdjFrequencies, adjWithNounFrequencies, 
                        adjectiveFrequencies, adjectiveSimpleFrequencies, numberOfSentences);
                allAdjNounCount+=count;
            }else if(POSTags.get(i).startsWith("r")){
                long count=processLineHuParsedOrEsTagged(words, i, advWordTuples, advWordSimpleTuples, wordAdvTuples, 
                        wordWithAdvFrequencies, advWithWordFrequencies, 
                        adverbFrequencies, adverbSimpleFrequencies, numberOfSentences);
                allAdvWordCount+=count;
            }
            
            saveStringLong(words.get(i), numberOfSentences, ncmodWithNounSimpleFrequencies);
            saveStringLong(words.get(i), numberOfSentences, dobjWithVerbSimpleFrequencies);
            saveStringLong(words.get(i), numberOfSentences, nounWithAdjSimpleFrequencies);
            saveStringLong(words.get(i), numberOfSentences, wordWithAdvSimpleFrequencies);
            
        }
        
    }
    
    
    
    
    
    
    
    
    /**
     * This method stores two strings and a long in a HashMap&lt;String, HashMap&lt;String, Long&gt;&gt;.
     * @param word1
     * @param word2
     * @param numberOfSentences
     * @param tuplesMap 
     */
    public static void saveStringStringLong(String word1, String word2, long numberOfSentences, HashMap<String, HashMap<String, Long>> tuplesMap){
                
        HashMap<String, Long> table=tuplesMap.get(word1);
        if(table!=null){
            Long l = table.get(word2);
            if(l!=null){
                table.put(word2, l+numberOfSentences);
            }else{
                table.put(word2, numberOfSentences);
            }
        }else{
            table = new HashMap<String, Long>();
            table.put(word2, numberOfSentences);
            tuplesMap.put(word1, table);
        }
        
    }
    
    
    /**
     * This method stores a strings and a long in a HashMap&lt;String, Long&gt;.
     * @param word1
     * @param numberOfSentences
     * @param tuplesMap 
     */
    public static void saveStringLong(String word1, long numberOfSentences, HashMap<String, Long> tuplesMap){
        
        Long l = tuplesMap.get(word1);
        if(l!=null){
            tuplesMap.put(word1, l+numberOfSentences);
        }else{
            tuplesMap.put(word1, numberOfSentences);
        }
        
    }
    
    
}
