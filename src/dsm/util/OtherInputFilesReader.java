package dsm.util;

import static dsm.parameters.MiscParam.*;
import dsm.util.ExtractedInfoReader.Corpus;
import dsm.util.ExtractedInfoReader.InputDataType;
import static dsm.util.ExtractedInfoReader.corpus;
import static dsm.util.ExtractedInfoReader.inputDataType;
import static dsm.util.MiscUtil.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Set;
import net.didion.jwnl.data.IndexWord;
import net.didion.jwnl.data.POS;
import szte.nlp.pos.HunLemMor;
import szte.nlp.pos.MorAna;

/**
 *
 * @author Dobó
 */
public class OtherInputFilesReader {
    
    

    /**
     * This function is used to read in the stop words from a file, when they should be fileterd from the features.
     */
    public static void readStopWordsList(){
        
        try{
            
            stopWordsSet = new HashSet<String>();
            
            BufferedReader in;
            
            if(inputDataType==InputDataType.Corpus && corpus==Corpus.HuWiki){
                in = new BufferedReader(new FileReader("input/stopwords-hu.txt"));
            }else if(inputDataType==InputDataType.Corpus && corpus==Corpus.EsWki){
                in = new BufferedReader(new FileReader("input/stopwords-es.txt"));
            }else{
                in = new BufferedReader(new FileReader("input/stopwords-en.txt"));
            }
            
            String line;
            
            while((line=in.readLine())!=null){
                
                line=line.toLowerCase();
                
                stopWordsSet.add(line);
                
            }
            
        }catch(Exception e){
            System.out.println("Error during reading the stop words!");
            e.printStackTrace();
            System.exit(1);
        }
    }
    
    
    
    
    /**
     * This function is used to read the input words from all the used test data sets. 
     * This is needed for the SVD, as it will use the word vectors of these words.
     */
    public static void readAllInputWords() {

        try {

            BufferedReader in;
            
            if(inputDataType==InputDataType.Corpus && corpus==Corpus.HuWiki){
                in = new BufferedReader(new FileReader("input/inputWordsHu.txt"));
            }else if(inputDataType==InputDataType.Corpus && corpus==Corpus.EsWki){
                in = new BufferedReader(new FileReader("input/inputWordsEs.txt"));
            }else{
                in = new BufferedReader(new FileReader("input/inputWords.txt"));
            }
            
            HashSet<String> allInputWords = new HashSet<String>();
            String line;
            while ((line = in.readLine()) != null) {
                
                line=line.toLowerCase();
                
                allInputWords.add(line);
                
                if(inputDataType==InputDataType.Corpus && corpus==Corpus.HuWiki){
                    
                    Set<MorAna> morAnaSet = HunLemMor.getMorphologicalAnalyses(line);
                    for(MorAna morAna: morAnaSet){
                        allInputWords.add(morAna.getLemma());
                    }
                    
                }else if(inputDataType==InputDataType.Corpus && corpus==Corpus.EsWki){
                    
                    String lemma = nounDic.get(line);
                    if (lemma != null && !lemma.equals("")) {
                        allInputWords.add(lemma);
                    }
                    lemma = verbDic.get(line);
                    if (lemma != null && !lemma.equals("")) {
                        allInputWords.add(lemma);
                    }
                    lemma = adjDic.get(line);
                    if (lemma != null && !lemma.equals("")) {
                        allInputWords.add(lemma);
                    }
                    lemma = advDic.get(line);
                    if (lemma != null && !lemma.equals("")) {
                        allInputWords.add(lemma);
                    }
                    
                }else{
                
                    IndexWord iw = dict.lookupIndexWord(POS.NOUN, line);
                    if (iw != null) {
                        allInputWords.add(iw.getLemma());
                    }
                    iw = dict.lookupIndexWord(POS.VERB, line);
                    if (iw != null) {
                        allInputWords.add(iw.getLemma());
                    }
                    iw = dict.lookupIndexWord(POS.ADJECTIVE, line);
                    if (iw != null) {
                        allInputWords.add(iw.getLemma());
                    }
                    iw = dict.lookupIndexWord(POS.ADVERB, line);
                    if (iw != null) {
                        allInputWords.add(iw.getLemma());
                    }
                    
                }

            }
            
            allNouns=allInputWords;
            allVerbs=allInputWords;
            allAdjectives=allInputWords;
            allAdverbs=allInputWords;
            
        } catch (Exception e) {
            System.out.println("Error during reading the inputWords!");
            e.printStackTrace();
            System.exit(1);
        }
    }
    
    
}
