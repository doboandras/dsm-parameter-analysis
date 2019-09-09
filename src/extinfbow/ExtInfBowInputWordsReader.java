package extinfbow;

import static extinfbow.ExtInfBowParam.allInputWords;
import static extinfbow.ExtInfBowUtil.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Set;
import net.didion.jwnl.data.IndexWord;
import net.didion.jwnl.data.POS;
import szte.nlp.pos.HunLemMor;
import szte.nlp.pos.MorAna;

/**
 * The class responsible for reading the input words used during the bag-of-words information extraction.
 * 
 * @author Dobó
 */
public class ExtInfBowInputWordsReader {
    
    /**
     * This function is used to read in the English input words, when a corpus of parsed English text is used. This is needed, because only the necessary information is stored in the 
     * information file. For each input word, this function stores the word's original form, while also storing its noun, verb, adjective and adverb lemma, if it has a lemma for that
     * part-of-speech. This is necessary, because the part-of-speech of the input words is not known in advance, and so that they can be compared to words of any part-of-speech.
     */
    public static void readInputWordsEnParser(){
        
        try{
            
            BufferedReader in = new BufferedReader(new FileReader("input/inputWords.txt"));
            String line;
            while((line=in.readLine())!=null){
                
                line=line.toLowerCase();
                
                allInputWords.add(line);
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
        }catch(Exception e){
            System.out.println("Error during reading the inputWords!");
            e.printStackTrace();
            System.exit(1);
        }
    }
    
    /**
     * This function is used to read in the Hungarian input words, when a corpus of parsed Hungarian text is used. This is needed, because only the necessary information is stored in the 
     * information file.
     */
    public static void readInputWordsHuParser(){
        
        try{
            
            BufferedReader in = new BufferedReader(new FileReader("input/inputWordsHu.txt"));
            String line;
            while((line=in.readLine())!=null){
                
                line=line.toLowerCase();
                
                Set<MorAna> morAnaSet = HunLemMor.getMorphologicalAnalyses(line);
                for(MorAna morAna: morAnaSet){
                    allInputWords.add(morAna.getLemma());
                }
                allInputWords.add(line);
                
            }
        }catch(Exception e){
            System.out.println("Error during reading the inputWords!");
            e.printStackTrace();
            System.exit(1);
        }
    }
    
    
    
    /**
     * This function is used to read in the Spanish input words, when a corpus of tagged Spanish text is used. This is needed, because only the necessary information is stored in the 
     * information file.
     */
    public static void readInputWordsEsTagger(){
        
        try{
            
            BufferedReader in = new BufferedReader(new FileReader("input/inputWordsEs.txt"));
            String line;
            while((line=in.readLine())!=null){
                
                line=line.toLowerCase();
                
                allInputWords.add(line);
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
                
            }
            
        }catch(Exception e){
            System.out.println("Error during reading the inputWords!");
            e.printStackTrace();
            System.exit(1);
        }
    }
    
}
