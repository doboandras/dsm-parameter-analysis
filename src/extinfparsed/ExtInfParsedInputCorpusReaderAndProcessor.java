package extinfparsed;

import static extinfparsed.ExtInfParsedParam.*;
import static extinfparsed.ExtInfParsedUtil.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import net.didion.jwnl.JWNLException;
import net.didion.jwnl.data.IndexWord;
import net.didion.jwnl.data.POS;
import util.ComparablePair;
import util.Paraphrase;

/**
 *
 * @author Dobó
 */
public class ExtInfParsedInputCorpusReaderAndProcessor {
    
    
    /**
     * This function reads in the files of a parsed English corpus to extract information from it, when the corpus is parsed with the newer version of the C&amp;C CCG parser. In this case each file 
     * is in a main directory.
     */
    public static void readFilesNewParser(){
        try{
            File dir = new File(location);
            int i=0;
            for(String filename : dir.list()){
                readFile(dir.getAbsolutePath()+"/"+filename);
            }
        }catch(Exception e){
            System.out.println("Error during reading the patents!");
            e.printStackTrace();
            System.exit(1);
        }
    }
    
    
    /**
     * This function reads in the files of a parsed English corpus to extract information from it, when the corpus is parsed with the older version of the C&amp;C CCG parser. In this case each file 
     * is in a subdirectory of the main directory.
     */
    public static void readFilesOldParser(){
        try{
            File maindir = new File(location);
            int i=0;
            for(String dirname : maindir.list()){
                File dir = new File(maindir.getAbsolutePath()+"/"+dirname);
                for(String filename : dir.list()){
                    readFile(dir.getAbsolutePath()+"/"+filename);
                }
            }
        }catch(Exception e){
            System.out.println("Error during reading the BNC!");
            e.printStackTrace();
            System.exit(1);
        }
    }
    
    
    /**
     * This function reads a file of the parsed English corpus to extract information from it.
     * @param filePath the path of the file
     * @throws Exception 
     */
    public static void readFile(String filePath) throws Exception{
        
        LinkedList<String> grs = new LinkedList<String>();
        BufferedReader in = new BufferedReader(new FileReader(filePath));
        String line;
        
        while((line=in.readLine())!=null){
            
            //It makes every line lowercase, and replaces some special characters (those special characters are used in the information files).
            line=line.toLowerCase();
            if(line.contains("\u001c") || line.contains("\u001d") || line.contains("\u001e")){
                line=line.replace("\u001c", ";");
                line=line.replace("\u001d", ";");
                line=line.replace("\u001e", ";");
            }
            
            //It saves the lines containing the grammatical relations (the lines starting with a bracket).
            if(line.startsWith("(")){
                
                grs.add(line);
            
            //It starts processing a sentence, when it reaches the line with the complete sentence (the line starting with >).
            }else if(line.startsWith("<")){
                
                //In case of there are sentence frequencies at the end of each sentence (e.g. Web1T5GramCorpus), then it uses those sentence frequencies.
                long numberOfSentences;
                if(sourceType!=SourceType.EnParserV2WithSentenceCounts){
                    numberOfSentences=1l;
                }else{
                    String[] lineParts = line.split("\t");
                    line=lineParts[0];
                    numberOfSentences = Long.parseLong(lineParts[1]);
                }
                
                //It exctracts the frequencies of the relevant words of the sentence. The position of the POS tag is different in case of the older and the newer version of the parser.
                if(sourceType==SourceType.EnParserV1){
                    extractFrequencies(line, numberOfSentences, 2);
                }else{
                    extractFrequencies(line, numberOfSentences, 1);
                }
                
                //The saved grammatical relations are processed, one after another, and the simpler part of the information is extracted.
                for(String gr: grs){
                    readPrepAuxSubjObjFromLine(gr.split(" "), numberOfSentences);
                }
                
                /*
                 * Some of the more complex relations regarding nouns and verbs can only be extracted after all grammatical relations have been processed, since these relations
                 * are made up of more than one grammatical relation. These relations are the relations between: objects and paraphrases, subjects and paraphrases, verbs and objects,
                 * verbs and subjects. The extraction of these relation is done here.
                 */
                computeCountsFromSentenceWithPairs(true, numberOfSentences);
                computeCountsFromSentenceWithPairs(false, numberOfSentences);
                computeNcsubjDobjCountsFromSentence(true, numberOfSentences);
                computeNcsubjDobjCountsFromSentence(false, numberOfSentences);
                
                //All the global variables used during the processing of a sentence are cleared at the end of the loop.
                grs=new LinkedList<String>();
                commonNounsInTheSentence.clear();
                verbsInTheSentence.clear();
                adjectivesInTheSentence.clear();
                adverbsInTheSentence.clear();
                justPrepSubjectsInTheSentenceList.clear();
                subjectsInTheSentence.clear();
                subjectsInTheSentenceList.clear();
                objectsInTheSentence.clear();
                prepostitionsWithDependentsInTheSentence.clear();
                prepostitionsWithDobjInTheSentence.clear();
                verbsWithDobjInTheSentence.clear();
                verbsByPrep.clear();
                nounsByPrep.clear();
                prepsByVerb.clear();
                auxByVerb.clear();
                verbsInNcsubjWithObjParameter.clear();
                
            }
        }
        in.close();
        
    }
    
    /**
     * This function exctracts the frequencies of the relevant words of a sentence, by calling the appropriate function based on the POS of the word.
     * @param line the line
     * @param numberOfSentences the frequency of the sentence (the number of times it occured in the corpus)
     * @param positionOfPOSTag the position of the POS tag
     */
    public static void extractFrequencies(String line, long numberOfSentences, int positionOfPOSTag){
        
        try{
        
            String[] words = line.split(" ");
            allWordCount+=words.length-1;
            for(int i=1;i<words.length;i++){
                String[] wordParts = words[i].replace("|", " ").split(" ");
                if((wordParts[positionOfPOSTag].equals("nn") || wordParts[positionOfPOSTag].equals("nns"))){
                    extractFrequencies(wordParts[0], POS.NOUN, commonNounsInTheSentence, nounFrequencies, i, numberOfSentences);
                }else if(wordParts[positionOfPOSTag].startsWith("v")){
                    extractFrequencies(wordParts[0], POS.VERB, verbsInTheSentence, verbFrequencies, i, numberOfSentences);
                }else if(wordParts[positionOfPOSTag].startsWith("jj")){
                    extractFrequencies(wordParts[0], POS.ADJECTIVE, adjectivesInTheSentence, adjectiveFrequencies, i, numberOfSentences);
                }else if(wordParts[positionOfPOSTag].startsWith("rb")){
                    extractFrequencies(wordParts[0], POS.ADVERB, adverbsInTheSentence, adverbFrequencies, i, numberOfSentences);
                }
            }
        
        } catch (JWNLException ex) {
            System.out.println("Error during using the JWNL!");
            ex.printStackTrace();
            System.exit(1);
        }
    }
    
    /**
     * This function saves the frequency of a word to the relevant map/set, after it determines the lemma of the word. (The wordType==WordType.WordsInWn version of this method stores the 
 information for only those words that are included in wordnet). At the beginning it saves the number (i-1) to (@param set), meaning that at the (i-1)th position of the sentence a 
 word with part-of-speech==(@param pos) stands. This information is used in the readPrepAuxSubjObjFromLine function to ensure that the relations hold between words of the appropriate pos.
     * @param word the word
     * @param pos the pos of the word
     * @param set a set, in which those position numbers are saved, in which positions of the sentence a word with part-of-speech==(@param pos) stands
     * @param frequencyMap one of the maps/sets, in which the extracted information is stored
     * @param i the position of the word in the sentence +1 (since the first token in each line is always &lt;c&gt;)
     * @param numberOfSentences the frequency of the sentence
     * @throws JWNLException 
     */
    public static void extractFrequencies(String word, POS pos, HashSet<Integer> set, HashMap<String, Long> frequencyMap, int i, long numberOfSentences) throws JWNLException{
        
        set.add(i-1);
        
        /*
         * When determining the lemma for a word, first it is checked whether the word is composed of only English letters and hypens and whether it has at most two hypens in it.
         * This is needed, because if a word with more hypens is passed to the jwnl library to look up a word in WordNet, it freezes for an unknown reason.
         */
        boolean wordIsInWordNet=false;
        if(word.split("-").length<4 && word.matches("[a-zA-Z-]*")){
            IndexWord iw = dict.lookupIndexWord(pos, word);
            if (iw != null) {
                word = iw.getLemma();
                wordIsInWordNet=true;
            }
        }
        
        if(wordIsInWordNet || wordType==WordType.AllWords){
            
            saveStringLong(word, numberOfSentences, frequencyMap);

        }

        
    }


    /**
     * This function processes a grammatical relation. It first determines the type of the grammatical relation based on its first word, then processes it accordingly.
     * @param words the words of the grammatical relation
     * @param numberOfSentences the frequency of the sentence
     * @throws JWNLException 
     */
    public static void readPrepAuxSubjObjFromLine(String[] words, long numberOfSentences) throws JWNLException{
        
        //Processing the indirect object relation.
        if(words[0].equals("(iobj")){
            
            words[2]=words[2].substring(0,words[2].length()-1);
            int position = Integer.parseInt(words[1].substring(words[1].lastIndexOf("_")+1, words[1].length()));
            
            if(prepositions.contains(words[2].substring(0, words[2].lastIndexOf("_")))){

                //It is an iobj relation between a verb and a preposition.
                if(verbsInTheSentence.contains(position)){
                    
                    //Information saved for the computeCountsFromSentenceWithPairs method: verbs with a preposition and prepositions with a verb.
                    saveStringString(words[2], words[1], verbsByPrep);
                    saveStringString(words[1], words[2], prepsByVerb);
                    
                    words[2]=words[2].substring(0, words[2].lastIndexOf("_"));

                    String verb = words[1].substring(0, words[1].lastIndexOf("_"));

                    boolean wordIsInWordNet=false;
                    if(verb.split("-").length<4 && verb.matches("[a-zA-Z-]*")){
                        IndexWord iw = dict.lookupIndexWord(POS.VERB, verb);
                        if(iw!=null){
                            verb=iw.getLemma();
                            wordIsInWordNet=true;
                        }
                    }
                    
                    if((wordIsInWordNet || wordType==WordType.AllWords) && (allInputWords.contains(verb) || !extractInformaionJustForInputWords)){
                        
                        saveStringLong(verb, numberOfSentences, verbWithPrepFrequencies);
                        saveStringStringLong(verb, words[2], numberOfSentences, verbPrepTuples);
                        
                    }

                        
                    allVerbPrepCount+=numberOfSentences;
                    saveStringLong(words[2], numberOfSentences, prepWithVerbFrequencies);
                    saveStringStringLong(words[2], verb, numberOfSentences, prepVerbTuples);


                }


            }
            
            //Information saved for the computeCountsFromSentenceWithPairs method: prepositions that have dependents.
            if(prepositions.contains(words[1].substring(0, words[1].lastIndexOf("_")))){
                prepostitionsWithDependentsInTheSentence.add(words[1]);
            }
            
        //Processing the non-clausal modifier relation.
        }else if(words[0].equals("(ncmod")){
            
            words[3]=words[3].substring(0,words[3].length()-1);
            String ncmod=words[3].substring(0, words[3].lastIndexOf("_"));

            //Information saved for the computeCountsFromSentenceWithPairs method: prepositions that have dependents.
            if(prepositions.contains(words[2].substring(0, words[2].lastIndexOf("_")))){
                prepostitionsWithDependentsInTheSentence.add(words[2]);
            }

            int position = Integer.parseInt(words[2].substring(words[2].lastIndexOf("_")+1, words[2].length()));
            int position2 = Integer.parseInt(words[3].substring(words[3].lastIndexOf("_")+1, words[3].length()));
            
            if(prepositions.contains(words[3].substring(0, words[3].lastIndexOf("_")))){

                //It is an ncmod relation between a verb and a preposition.
                if(verbsInTheSentence.contains(position)){
   
                    //Information saved for the computeCountsFromSentenceWithPairs method: verbs with a preposition and prepositions with a verb.
                    saveStringString(words[3], words[2], verbsByPrep);
                    saveStringString(words[2], words[3], prepsByVerb);

                    String verb = words[2].substring(0, words[2].lastIndexOf("_"));

                    boolean wordIsInWordNet=false;
                    if(verb.split("-").length<4 && verb.matches("[a-zA-Z-]*")){
                        IndexWord iw = dict.lookupIndexWord(POS.VERB, verb);
                        if(iw!=null){
                            verb=iw.getLemma();
                            wordIsInWordNet=true;
                        }
                    }
                    if((wordIsInWordNet || wordType==WordType.AllWords) && (allInputWords.contains(verb) || !extractInformaionJustForInputWords)){
                        
                        saveStringLong(verb, numberOfSentences, verbWithPrepFrequencies);
                        saveStringStringLong(verb, ncmod, numberOfSentences, verbPrepTuples);
                        
                    }

                    allVerbPrepCount+=numberOfSentences;
                    saveStringLong(ncmod, numberOfSentences, prepWithVerbFrequencies);
                    saveStringStringLong(ncmod, verb, numberOfSentences, prepVerbTuples);

                }

                //It is an ncmod relation between a common noun and a preposition.
                if(commonNounsInTheSentence.contains(position)){

                    //Information saved for the computeCountsFromSentenceWithPairs method: HashMap of nouns with a preposition, where the preposition is the modifier of the noun.
                    HashSet<String> set = nounsByPrep.get(words[3]);
                    if(set==null){
                        set = new HashSet<String>();
                    }
                    set.add(words[2]);
                    nounsByPrep.put(words[3], set);

                    //Information saved for the computeCountsFromSentenceWithPairs method: list of noun with a preposition, where the preposition is the modifier of the noun.
                    justPrepSubjectsInTheSentenceList.add(new ComparablePair<String, String>(words[2], words[3]));

                }
            }

            //It is an ncmod relation between a common noun and a word.
            if(commonNounsInTheSentence.contains(position)){
                String noun=words[2].substring(0, words[2].lastIndexOf("_"));

                boolean wordIsInWordNet=false;
                if(noun.split("-").length<4 && noun.matches("[a-zA-Z-]*")){
                    IndexWord iw = dict.lookupIndexWord(POS.NOUN, noun);
                    if(iw!=null){
                        noun=iw.getLemma();
                        wordIsInWordNet=true;
                    }
                }
                if((wordIsInWordNet || wordType==WordType.AllWords) && (allInputWords.contains(noun) || !extractInformaionJustForInputWords)){
                    
                    saveStringLong(noun, numberOfSentences, nounWithNcmodFrequencies);
                    saveStringStringLong(noun, ncmod, numberOfSentences, nounNcmodTuples);
                    
                }

                allNounNcmodCount+=numberOfSentences;
                saveStringLong(ncmod, numberOfSentences, ncmodWithNounFrequencies);
                saveStringStringLong(ncmod, noun, numberOfSentences, ncmodNounTuples);
     
                
                //It is an ncmod relation between a common noun and an adjective.
                if(adjectivesInTheSentence.contains(position2)){
                    
                    wordIsInWordNet=false;
                    if(ncmod.split("-").length<4 && ncmod.matches("[a-zA-Z-]*")){
                        IndexWord iw = dict.lookupIndexWord(POS.ADJECTIVE, ncmod);
                        if(iw!=null){
                            ncmod=iw.getLemma();
                            wordIsInWordNet=true;
                        }
                    }
                    if((wordIsInWordNet || wordType==WordType.AllWords) && (allInputWords.contains(ncmod) || !extractInformaionJustForInputWords)){
                        
                        saveStringLong(ncmod, numberOfSentences, adjWithNounFrequencies);
                        saveStringStringLong(ncmod, noun, numberOfSentences, adjNounTuples);
                        
                    }

                    allAdjNounCount+=numberOfSentences;
                    saveStringLong(noun, numberOfSentences, nounWithAdjFrequencies);
                    saveStringStringLong(noun, ncmod, numberOfSentences, nounAdjTuples);
                    
                }
                

            }
            
            //It is an ncmod relation between a word and an adverb.
            if(adverbsInTheSentence.contains(position2)){
                
                String word=words[2].substring(0, words[2].lastIndexOf("_"));
                
                if(commonNounsInTheSentence.contains(position) && word.split("-").length<4 && word.matches("[a-zA-Z-]*")){
                    IndexWord iw = dict.lookupIndexWord(POS.NOUN, word);
                    if(iw!=null){
                        word=iw.getLemma();
                    }
                }else if(verbsInTheSentence.contains(position) && word.split("-").length<4 && word.matches("[a-zA-Z-]*")){
                    IndexWord iw = dict.lookupIndexWord(POS.VERB, word);
                    if(iw!=null){
                        word=iw.getLemma();
                    }
                }
                
                boolean wordIsInWordNet=false;
                if(ncmod.split("-").length<4 && ncmod.matches("[a-zA-Z-]*")){
                    IndexWord iw = dict.lookupIndexWord(POS.ADVERB, ncmod);
                    if(iw!=null){
                        ncmod=iw.getLemma();
                        wordIsInWordNet=true;
                    }
                }
                if((wordIsInWordNet || wordType==WordType.AllWords) && (allInputWords.contains(ncmod) || !extractInformaionJustForInputWords)){
                                        
                    saveStringLong(ncmod, numberOfSentences, advWithWordFrequencies);
                    saveStringStringLong(ncmod, word, numberOfSentences, advWordTuples);
                    
                }
                
                allAdvWordCount+=numberOfSentences;
                saveStringLong(word, numberOfSentences, wordWithAdvFrequencies);
                saveStringStringLong(word, ncmod, numberOfSentences, wordAdvTuples);
                
            }
            
        //Processing the auxiliary relation.
        }else if(words[0].equals("(aux")){
            
            //It is an aux relation between a verb and an auxiliary.
            if(verbsInTheSentence.contains(Integer.parseInt(words[1].substring(words[1].lastIndexOf("_")+1, words[1].length())))){
                
                //Information saved for the isPassive method: verbs with an auxiliary.
                words[2]=words[2].substring(0, words[2].lastIndexOf("_"));
                HashSet<String> set = auxByVerb.get(words[1]);
                if(set==null){
                    set = new HashSet<String>();
                }
                set.add(words[2]);
                auxByVerb.put(words[1], set);
                
            }
            
        //Processing the non-clausal subject relation.
        }else if(words[0].equals("(ncsubj")){

            //It is an ncsubj relation between a verb and a common noun.
            if(verbsInTheSentence.contains(Integer.parseInt(words[1].substring(words[1].lastIndexOf("_")+1, words[1].length()))) && commonNounsInTheSentence.contains(Integer.parseInt(words[2].substring(words[2].lastIndexOf("_")+1, words[2].length())))){
                
                words[2]=words[2].substring(0, words[2].lastIndexOf("_"));
                
                //Information saved for the computeCountsFromSentenceWithPairs method: HashMap of verbs with a subject.
                saveStringStringLong(words[1], words[2], numberOfSentences, subjectsInTheSentence);
                
                //Information saved for the computeCountsFromSentenceWithPairs and computeNcsubjDobjCountsFromSentence methods: list of subjects with a verb.
                subjectsInTheSentenceList.add(new ComparablePair<String, String>(words[2], words[1]));
                
                //Information saved for the isPassive method: verbs, whose subject is actually the object of the action.
                if(words[3].equals("obj)")){
                    verbsInNcsubjWithObjParameter.add(words[1]);
                }

            }
            
        //Processing the direct object relation.
        }else if(words[0].equals("(dobj")){
            
            //It is a dobj relation between a (verb or preposition) and a common noun.
            if((verbsInTheSentence.contains(Integer.parseInt(words[1].substring(words[1].lastIndexOf("_")+1, words[1].length()))) || prepositions.contains(words[1].substring(0, words[1].lastIndexOf("_")))) && commonNounsInTheSentence.contains(Integer.parseInt(words[2].substring(words[2].lastIndexOf("_")+1, words[2].length()-1)))){
                
                words[2]=words[2].substring(0, words[2].lastIndexOf("_"));
                
                //Information saved for the computeCountsFromSentenceWithPairs and computeNcsubjDobjCountsFromSentence methods: objects with a verb and verbs that have an object.
                objectsInTheSentence.add(new ComparablePair<String, String>(words[2], words[1]));
                verbsWithDobjInTheSentence.add(words[1]);

            }
            
            //Information saved for the computeCountsFromSentenceWithPairs method: prepositions that have an object.
            if(prepositions.contains(words[1].substring(0, words[1].lastIndexOf("_")))){
                prepostitionsWithDobjInTheSentence.add(words[1]);
            }
            
        //Processing the unsaturated VP complement or saturated clausal complement relation.
        }else if(words[0].equals("(ccomp") || words[0].equals("(xcomp")){
            
            //It is a ccomp or xcomp relation between a preposition and a word.
            if(prepositions.contains(words[2].substring(0, words[2].lastIndexOf("_")))){
                //Information saved for the computeCountsFromSentenceWithPairs method: prepositions that have dependents.
                prepostitionsWithDependentsInTheSentence.add(words[2]);
            }
            
        //Processing the second object relation.
        }else if(words[0].equals("(obj2")){

            //It is an obj2 relation between a verb and a common noun.
            if(verbsInTheSentence.contains(Integer.parseInt(words[1].substring(words[1].lastIndexOf("_")+1, words[1].length()))) && commonNounsInTheSentence.contains(Integer.parseInt(words[2].substring(words[2].lastIndexOf("_")+1, words[2].length()-1)))){

                String noun=words[2].substring(0, words[2].lastIndexOf("_"));

                if(noun.split("-").length<4 && noun.matches("[a-zA-Z-]*")){
                    IndexWord iw = dict.lookupIndexWord(POS.NOUN, noun);
                    if(iw!=null){
                        noun=iw.getLemma();
                    }
                }

                String verb = words[1].substring(0, words[1].lastIndexOf("_"));

                boolean wordIsInWordNet=false;
                if(verb.split("-").length<4 && verb.matches("[a-zA-Z-]*")){
                    IndexWord iw = dict.lookupIndexWord(POS.VERB, verb);
                    if(iw!=null){
                        verb=iw.getLemma();
                        wordIsInWordNet=true;
                    }
                }
                
                if((wordIsInWordNet || wordType==WordType.AllWords) && (allInputWords.contains(verb) || !extractInformaionJustForInputWords)){
                   
                    saveStringLong(verb, numberOfSentences, verbWithObj2Frequencies);
                    saveStringStringLong(verb, noun, numberOfSentences, verbObj2Tuples);
                }

                allVerbObj2Count+=numberOfSentences;
                saveStringLong(noun, numberOfSentences, obj2WithVerbFrequencies);
                saveStringStringLong(noun, verb, numberOfSentences, obj2VerbTuples);

            }

        }
        
    }


    /**
     * This function determines for an extracted verb, whether it is passive or not. It is passive, if it was included in a ncsubj relation, where there was an obj parameter 
     * (meaning that the subject is actually the object of the action). It is also passive if it does no end with "ing" and has an auxiliary verb whose lemma is be. Otherwise it is active.
     * @param verb the verb
     * @return the boolean that represents whether the verb is passive or not
     */
    public static boolean isPassive(String verb){
        if(verbsInNcsubjWithObjParameter.contains(verb)){
            return true;
        }else{
            HashSet<String> auxiliaries = auxByVerb.get(verb);
            if(auxiliaries!=null && !verb.split("_")[0].endsWith("ing")){
                for(String aux : auxiliaries){
                    try {
                        if(aux.split("-").length<4 && aux.matches("[a-zA-Z-]*")){
                            IndexWord iw = dict.lookupIndexWord(POS.VERB, aux);
                            if(iw!=null && iw.getLemma().equals("be")){
                                return true;
                            }
                        }
                    } catch (JWNLException ex) {
                        System.out.println("Error during using the JWNL!");
                        ex.printStackTrace();
                        System.exit(1);
                    }
                }
            }
            return false;
        }
    }



    /**
     * This function is responsible for extracting the relations between subjects and paraphrases and between objects and paraphrases. This is done here and not in the 
     * readPrepAuxSubjObjFromLine  function, because these relations are made up of more than one grammatical relation, so these relations can only be extracted after all 
     * grammatical relations have been processed.
     * @param isSubject indicates whether the relations between subjects and paraphrases or between objects and paraphrases should be processed
     * @param numberOfSentences the frequency of the sentence
     */
    public static void computeCountsFromSentenceWithPairs(boolean isSubject, long numberOfSentences){

        try {
            /*
             * This loop iterates through the subjects/objects found in the given sentence. The pair p contains these nouns with those words, to which they were directly connected to.
             * In case of subjects, it can only be a verb, in case of objects, it can be a verb or a preposition.
             */
            for(ComparablePair<String, String> p : (isSubject ? subjectsInTheSentenceList : objectsInTheSentence)){
                
                String prep=null;
                /*
                 * If the noun p.first is a subject, then the noun is the subject of the verb p.second.
                 * If the noun p.first is an object and its pair is a preposition, then the noun is the object of all verbs to which the preposition connects.
                 * If the noun p.first is an object and its pair is not a preposition, then the noun is the object of the verb p.second.
                 * (If the noun p.first is a subject or an object of a verb directly, then all the subject object relation will be stored separately in 
                 * subjectsInTheSentenceList and in objectsInTheSentence, so there will always be only one verb for a given pair p, which is p.second. But if 
                 * p.second was a preposition, then there could be more than one verbs, and those verbs are all those to which the preposition p.second connects)
                 */
                HashSet<String> verbSet = new HashSet<String>();
                if(isSubject){
                    verbSet.add(p.second);
                }else{
                    if(verbsByPrep.containsKey(p.second)){
                        verbSet.addAll(verbsByPrep.get(p.second));
                    }else{
                        verbSet.add(p.second);
                    }
                }

                
                /*
                 * This part iterates through all the verbs found for the subject/object p.first. It only considers those verbs that occur in an ncsubj relation (they are included in 
                 * subjectsInTheSentence). In case the noun p.first is a subject, then it is always the case, but in case the noun p.first is an object, then it might not be. This way only
                 * those verb-object relations are used, where the verb has a subject (since each English verb has to have a subject; if it does not have, there is a parsing error).
                 */
                for(String verb: verbSet){
                    if(subjectsInTheSentence.containsKey(verb)){
                        
                        //If there is a preposition connected to the verb, then that preposition should also be used in the paraphrase.
                        if(prepsByVerb.containsKey(verb)){
                            
                            ArrayList<ComparablePair<String, Integer>> list = new ArrayList<ComparablePair<String, Integer>>();
                            /*
                             * All the prepositions that have no dependents nor direct objects belong together with the verb. These are verb-particles and are saved in a list with their
                             * position in the sentence. Only the prepositions without dependents and direct objects are used, since the other prepositions are rather related to their 
                             * dependents/objects than the verb.
                             */
                            for(String preposition: prepsByVerb.get(verb)){
                                if(!prepostitionsWithDependentsInTheSentence.contains(preposition) && !prepostitionsWithDobjInTheSentence.contains(preposition)){
                                    list.add(new ComparablePair<String, Integer>(preposition.substring(0, preposition.lastIndexOf("_")), Integer.parseInt(preposition.substring(preposition.lastIndexOf("_")+1, preposition.length()))));
                                }
                            }
                            /*
                             * In case the noun p.first is an object and its pair is a preposition, then that preposition belongs to the verb-object relation, therefore it is saved in the list. 
                             * Its position is set to Integer.MAX_VALUE, because it is related to the object rather than the verb, so it should be behind all the verb-particles that belong 
                             * to the verb. In the previous loop this preposition is not included, since this preposition has a direct object. This preposition is only used in the object 
                             * relation, because it is only related to the object, and is not related to the subject.
                             */
                            if(!isSubject && verbsByPrep.containsKey(p.second)){
                                list.add(new ComparablePair<String, Integer>(p.second.substring(0, p.second.lastIndexOf("_")), Integer.MAX_VALUE));
                            }
                            //The prepositions saved in the list are sorted according their position in the sentence, then a single string is created out of their concatenation.
                            if(list.size()>0){
                                Collections.sort(list);
                                prep=list.get(0).first;
                                for(int i=1;i<list.size();i++){
                                    prep+=" " + list.get(i).first;
                                }
                            }
                            
                        }
                        
                        //It is checked whether the verb is passive. Then the position of the verb, included at the end of its string is trimed, after which the lemma of the verb is determined.
                        boolean passive = isPassive(verb);
                        String verbWithPosition = verb;
                        verb=verb.substring(0, verb.lastIndexOf("_"));
                        if(verb.split("-").length<4 && verb.matches("[a-zA-Z-]*")){
                            IndexWord iw = dict.lookupIndexWord(POS.VERB, verb);
                            if (iw != null) {
                                verb = iw.getLemma();
                            }
                        }

                        /*
                         * If the verb is active, it has no direct objects and is a patientive ambitransitive verb, then it behaves as a passive verb (it surface subject is actually the
                         * object of the action). Therefore the variable passive is set true. In case it is passive and has a direct object, then there must be a parsing error, since
                         * passive verbs cannot have direct object (any noun connected to it is connected with a preposition), so this verb is skipped.
                         */
                        if(!passive && !verbsWithDobjInTheSentence.contains(verbWithPosition) && patientiveAmbitransitives.contains(verb)){
                            passive=true;
                        }else if(passive && verbsWithDobjInTheSentence.contains(verbWithPosition)){
                            continue;
                        }

                        //In case the noun p.first is a subject.
                        if(isSubject){

                            /*
                             * If the verb is passive, then its subject is actually the object of the action, and it means the same as if the verb was active and its object was the 
                             * original subject. These two different represantation of the same thing should be handled together. Therefore, if a passive verb with a subject is encountered,
                             * then it is saved as an active verb with an object instead. Active verbs with a subject are saved without modification.
                             */
                            if(passive){
                                saveCountsUsingPairs(!isSubject, p.first, new Paraphrase(false, verb, prep), numberOfSentences);
                            }else{
                                saveCountsUsingPairs(isSubject, p.first, new Paraphrase(passive, verb, prep), numberOfSentences);
                            }

                            /*
                             * Beside using the verb in its base form, together with its possible verb-particles, one verb for each other prepositions connected to its object are also used, 
                             * including that preposition. It is done so, since from the sentence “The professor teaches anatomy at a university” it is reasonable to not only extract the pair
                             * (professor, teaches), but also (professor, teaches at). For every such preposition a separate verb is extracted. The only time when it is not done
                             * is when the verb is passive and the last preposition is by, because the by preposition actually shows the subject of the action, and not an 
                             * object connected to the verb. It is not reasonable to extract the pair (course, is taught by) from the sentence "The course is taugth by a famous 
                             * professor"; it is only reasonable to extract the pair (course, is taught).
                             */
                            if(prepsByVerb.containsKey(verbWithPosition)){
                                for(String prep2: prepsByVerb.get(verbWithPosition)){
                                    if(prepostitionsWithDobjInTheSentence.contains(prep2)){
                                        
                                        String newPrep;
                                        if(prep!=null){
                                            newPrep=prep+" "+prep2.substring(0, prep2.lastIndexOf("_"));
                                        }else{
                                            newPrep=prep2.substring(0, prep2.lastIndexOf("_"));
                                        }

                                        if(!(passive && newPrep!=null && newPrep.endsWith("by"))){
                                            saveCountsUsingPairs(isSubject, p.first, new Paraphrase(passive, verb, newPrep), numberOfSentences);
                                        }

                                    }
                                }
                            }

                        //In case the noun p.first is an object.
                        }else{

                            /*
                             * If the verb is passive and its object is connected to it using the preposition "by", then that object is actually the subject of the action, and it means the 
                             * same as if the verb was active and its subject was the original object. These two different represantation of the same thing should be handled together. 
                             * Therefore, if such passive verb is encountered, whose object is is connected to it using the preposition "by", then it is saved as an active verb with a 
                             * subject instead. Otherwise, the verb with the object is saved without modification. (At this point there cannot be such passive verbs that have a direct 
                             * object. By the English grammar passive verbs cannot have direct objects. Still, if due to a parsing error a passive verb is encountered, then that verb is
                             * skipped with "continue" before the "if".)
                             */
                            if(passive && prep!=null && prep.endsWith("by")){
                                saveCountsUsingPairs(!isSubject, p.first, new Paraphrase(false, verb, prep.length()==2 ? null : prep.substring(0, prep.length()-3)), numberOfSentences);
                            }else{
                                saveCountsUsingPairs(isSubject, p.first, new Paraphrase(passive, verb, prep), numberOfSentences);
                            }

                        }


                    }
                }

                /*
                 * A single preposition without a verb can also function as a paraphrase.
                 * If we consider objects that are directly connected to a preposition, then that preposition can not only be connected to a verb, but also to another noun. 
                 * Such grammatical structures function the same way, as if (instead of the preposition alone) the verb "be" with the same preposition was used. These two 
                 * different represantation of the same thing should be handled together. So if such a structure is found, it is saved as if the verb "be" was there.
                 * The following part saves the object relations found in such structures.
                 */
                if(!isSubject){
                    
                    if(nounsByPrep.containsKey(p.second)){
                        saveCountsUsingPairs(false, p.first, new Paraphrase(false, "be", p.second.substring(0, p.second.lastIndexOf("_"))), numberOfSentences);
                    }
                    
                }

            }

            /*
             * As noted before, a single preposition without a verb can also function as a paraphrase.
             * If we consider objects that are directly connected to a preposition, then that preposition can not only be connected to a verb, but also to another noun. 
             * Such grammatical structures function the same way, as if (instead of the preposition alone) the verb "be" with the same preposition was used. These two 
             * different represantation of the same thing should be handled together. So if such a structure is found, it is saved as if the verb "be" was there.
             * The following part saves the subject relations found in such structures.
             */
            if(isSubject){
                
                for(ComparablePair<String, String> p : justPrepSubjectsInTheSentenceList){
                    String subj=p.first.substring(0, p.first.lastIndexOf("_"));
                    saveCountsUsingPairs(true, subj, new Paraphrase(false, "be", p.second.substring(0, p.second.lastIndexOf("_"))), numberOfSentences);
                }
                
            }


        } catch (JWNLException ex) {
            System.out.println("Error during using the JWNL!");
            ex.printStackTrace();
            System.exit(1);
        }
    }


    /**
     * This function stores the extracted information for a relation between a subject and a paraphrase or between an object and a paraphrase.
     * @param isSubject indicates whether this is a relation between a subject and a paraphrase or between an object and a paraphrase
     * @param noun the subject/object
     * @param paraphrase the paraphrase
     * @param numberOfSentences the frequency of the sentence
     * @throws JWNLException 
     */
    public static void saveCountsUsingPairs(boolean isSubject, String noun, Paraphrase paraphrase, long numberOfSentences) throws JWNLException{

        boolean wordIsInWordNet=false;
        if(noun.split("-").length<4 && noun.matches("[a-zA-Z-]*")){
            IndexWord iw = dict.lookupIndexWord(POS.NOUN, noun);
            if(iw!=null){
                noun=iw.getLemma();
                wordIsInWordNet=true;
            }
        }
        
        if((wordIsInWordNet || wordType==WordType.AllWords) && (allInputWords.contains(noun) || !extractInformaionJustForInputWords)){
            
            if(isSubject){
                saveStringLong(noun, numberOfSentences, subjectFrequencies);
                saveStringParaphraseLong(noun, paraphrase, numberOfSentences, subjVerbTuples);
            }else{
                saveStringLong(noun, numberOfSentences, objectFrequencies);
                saveStringParaphraseLong(noun, paraphrase, numberOfSentences, objVerbTuples);
            }
             
        }

        if(isSubject){
            allSubjectCount+=numberOfSentences;
            saveParaphraseLong(paraphrase, numberOfSentences, verbWithSubjectFrequencies);
            saveParaphraseStringLong(paraphrase, noun, numberOfSentences, verbSubjectTuples);
        }else{
            allObjectCount+=numberOfSentences;
            saveParaphraseLong(paraphrase, numberOfSentences, verbWithObjectFrequencies);
            saveParaphraseStringLong(paraphrase, noun, numberOfSentences, verbObjectTuples);
        }

    }


    /**
     * This function is responsible for extracting the relations between verbs and subjects and between verbs and objects. This is done here and not in the readPrepAuxSubjObjFromLine 
     * function, because these relations are made up of more than one grammatical relation, so these relations can only be extracted after all grammatical relations have been processed.
     * @param isSubject indicates whether the relations between subjects and paraphrases or between objects and paraphrases should be processed
     * @param numberOfSentences the frequency of the sentence
     * @throws JWNLException 
     */
    public static void computeNcsubjDobjCountsFromSentence(boolean isSubject, long numberOfSentences) throws JWNLException{

        /*
         * This loop iterates through the subjects/objects found in the given sentence. The pair p contains these nouns with those words, to which they were directly connected to.
         * In case of subjects, it can only be a verb, in case of objects, it can be a verb or a preposition.
         */
        for(ComparablePair<String, String> p : (isSubject ? subjectsInTheSentenceList : objectsInTheSentence)){

            //The position of the verb, included at the end of its string is trimed, after which the lemma of the verb is determined. Then it is checked whether the verb is passive.
            String verb = p.second.substring(0, p.second.lastIndexOf("_"));
            boolean wordIsInWordNet=false;
            if(verb.split("-").length<4 && verb.matches("[a-zA-Z-]*")){
                IndexWord iw = dict.lookupIndexWord(POS.VERB, verb);
                if(iw!=null){
                    verb=iw.getLemma();
                    wordIsInWordNet=true;
                }
            }
            boolean passive = isPassive(p.second);

            /*
             * If the verb is active, it has no direct objects and is a patientive ambitransitive verb, then it behaves as a passive verb (it surface subject is actually the
             * object of the action). Therefore the variable passive is set true. In case it is passive and has a direct object, then there must be a parsing error, since
             * passive verbs cannot have direct object (any noun connected to it is connected with a preposition), so this verb is skipped.
             */
            if(!passive && !verbsWithDobjInTheSentence.contains(p.second) && patientiveAmbitransitives.contains(verb)){
                passive=true;
            }else if(passive && verbsWithDobjInTheSentence.contains(p.second)){
                continue;
            }

            /*
             * If the verb is passive, then its subject is actually the object of the action, and it means the same as if the verb was active and its object was the 
             * original subject. These two different represantation of the same thing should be handled together. Therefore, if a passive verb with a subject is encountered,
             * then it is saved as an active verb with an object instead. Active verbs with a subject and active verbs with an object are saved without modification.
             * (At this point there cannot be such passive verbs that have a direct object. By the English grammar passive verbs cannot have direct objects. Still, if due to a 
             * parsing error a passive verb is encountered, then that verb is skipped with "continue" a couple of lines before.)
             */
            if(passive){
                saveNcsubjDobjCounts(!isSubject, verb, p.first, numberOfSentences, wordIsInWordNet);
            }else{
                saveNcsubjDobjCounts(isSubject, verb, p.first, numberOfSentences, wordIsInWordNet);
            }


        }

    }


    /**
     * This function stores the extracted information for a relation between a verb and a subject or between a verb and an object.
     * @param isSubject indicates whether this is a relation between a verb and a subject or between a verb and an object
     * @param verb the verb
     * @param noun the noun
     * @param numberOfSentences the frequency of the sentence
     * @param wordIsInWordNet indicates whether the verb is included in the wordnet or not
     * @throws JWNLException 
     */
    public static void saveNcsubjDobjCounts(boolean isSubject, String verb, String noun, long numberOfSentences, boolean wordIsInWordNet) throws JWNLException{


        if(noun.split("-").length<4 && noun.matches("[a-zA-Z-]*")){
            IndexWord iw = dict.lookupIndexWord(POS.NOUN, noun);
            if(iw!=null){
                noun=iw.getLemma();
            }
        }

        if((wordIsInWordNet || wordType==WordType.AllWords) && (allInputWords.contains(verb) || !extractInformaionJustForInputWords)){
                    
            if(isSubject){
                saveStringLong(verb, numberOfSentences, verbWithNcsubjFrequencies);
                saveStringStringLong(verb, noun, numberOfSentences, verbNcsubjTuples);
            }else{
                saveStringLong(verb, numberOfSentences, verbWithDobjFrequencies);
                saveStringStringLong(verb, noun, numberOfSentences, verbDobjTuples);
            }
            
        }
        

        if(isSubject){
            allVerbNcsubjCount+=numberOfSentences;
            saveStringLong(noun, numberOfSentences, ncsubjWithVerbFrequencies);
            saveStringStringLong(noun, verb, numberOfSentences, ncsubjVerbTuples);
        }else{
            allVerbDobjCount+=numberOfSentences;
            saveStringLong(noun, numberOfSentences, dobjWithVerbFrequencies);
            saveStringStringLong(noun, verb, numberOfSentences, dobjVerbTuples);
        }

    }
    
    
    
    
    /**
     * This method stores a strings, a paraphrase and a long in a HashMap&lt;String, HashMap&lt;Paraphrase, Long&gt;&gt;.
     * @param word1
     * @param word2
     * @param numberOfSentences
     * @param tuplesMap 
     */
    public static void saveStringParaphraseLong(String word1, Paraphrase word2, long numberOfSentences, HashMap<String, HashMap<Paraphrase, Long>> tuplesMap){
        
        HashMap<Paraphrase, Long> table=tuplesMap.get(word1);
        if(table!=null){
            Long l = table.get(word2);
            if(l!=null){
                table.put(word2, l+numberOfSentences);
            }else{
                table.put(word2, numberOfSentences);
            }
        }else{
            table = new HashMap<Paraphrase, Long>();
            table.put(word2, numberOfSentences);
            tuplesMap.put(word1, table);
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
     * This method stores a paraphrase, a string and a long in a HashMap&lt;Paraphrase, HashMap&lt;String, Long&gt;&gt;.
     * @param word1
     * @param word2
     * @param numberOfSentences
     * @param tuplesMap 
     */
    public static void saveParaphraseStringLong(Paraphrase word1, String word2, long numberOfSentences, HashMap<Paraphrase, HashMap<String, Long>> tuplesMap){
        
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
     * This method stores a paraphrase and a long in a HashMap&lt;Paraphrase, Long&gt;.
     * @param word1
     * @param numberOfSentences
     * @param tuplesMap 
     */
    public static void saveParaphraseLong(Paraphrase word1, long numberOfSentences, HashMap<Paraphrase, Long> tuplesMap){
        
        Long l = tuplesMap.get(word1);
        if(l!=null){
            tuplesMap.put(word1, l+numberOfSentences);
        }else{
            tuplesMap.put(word1, numberOfSentences);
        }
        
    }
    
    /**
     * This method stores a string and a long in a HashMap&lt;String, Long&gt;.
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
    
    /**
     * This method saves two strings in a HashMap&lt;String, HashSet&lt;String&gt;&gt;
     * @param word1
     * @param word2
     * @param map 
     */
    public static void saveStringString(String word1, String word2, HashMap<String, HashSet<String>> map){
        
        HashSet<String> set=map.get(word1);
        if(set!=null){
            set.add(word2);
        }else{
            set = new HashSet<String>();
            set.add(word2);
            map.put(word1, set);
        }
        
        
    }
    
    
}
