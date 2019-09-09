package extinfparsed;

import static extinfparsed.ExtInfParsedParam.allInputWords;
import static extinfparsed.ExtInfParsedUtil.dict;
import java.io.BufferedReader;
import java.io.FileReader;
import net.didion.jwnl.data.IndexWord;
import net.didion.jwnl.data.POS;

/**
 * The class responsible for reading the input words used during the dependency-relation-based information extraction.
 * 
 * @author Dobó
 */
public class ExtInfParsedInputWordsReader {
    
    
    /**
     * This function is used to read in the English input words. This is needed, because only the necessary information is stored in the 
     * information file. For each input word, this function stores the word's original form, while also storing its noun, verb, adjective and adverb lemma, if it has a lemma for that
     * part-of-speech. This is necessary, because the part-of-speech of the input words is not known in advance, and so that they can be compared to words of any part-of-speech.
     */
    public static void readInputWords(){
        
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
    
    
}
