package extinfbow;


import static extinfbow.ExtInfBowParam.*;
import static extinfbow.ExtInfBowInputWordsReader.*;
import static extinfbow.ExtInfBowInputCorpusReaderAndProcessor.*;
import static extinfbow.ExtInfBowPrinter.*;
import static extinfbow.ExtInfBowInitializer.initialize;





/**
 * This is a class for extracting information from a corpus, using a bag-of-words approach.
 * 
 * <br><br><br>
 * 
 * Usage:
 * 
 * <br><br>
 * 
 * java -cp DSM.jar extinfbow.ExtractInformationBagOfWords SourceType windowSize WeightingScheme extractInformaionJustForInputWords corpusLocation outputFileName
 * 
 * <br><br>
 * 
 * String SourceType: the type of corpus used as input ({@link extinfbow.ExtInfBowParam})
 * <ul>
 * <li>EnParserV1: English text parsed with the C&amp;C CCG parser (Stephen Clark and James R. Curran (2007): Wide-Coverage Efficient Statistical 
 * Parsing with CCG and Log-Linear Models. Computational Linguistics, 33(4), 2007, https://github.com/chrzyki/candc), the POS tags of words are in 3rd position, 
 * text files are in the subdirectories of the main directory of the curpus
 * <li>EnParserV2: English text parsed with the C&amp;C CCG parser (Stephen Clark and James R. Curran (2007): Wide-Coverage Efficient Statistical 
 * Parsing with CCG and Log-Linear Models. Computational Linguistics, 33(4), 2007, https://github.com/chrzyki/candc), the POS tags of words are in 2nd position, 
 * text files are in the main directory of the curpus
 * <li>EnParserV2WithSentenceCounts: English text parsed with the C&amp;C CCG parser (Stephen Clark and James R. Curran (2007): Wide-Coverage Efficient Statistical 
 * Parsing with CCG and Log-Linear Models. Computational Linguistics, 33(4), 2007, https://github.com/chrzyki/candc), the POS tags of words are in 2nd position, 
 * there are also frequency counts included for each sentence, text files are in the main directory of the curpus
 * <li>HuParser: Hungarian text parsed with the Magyar lánc (HunPOSChain) parser, text files are in the main directory of the curpus
 * <li>EsTagger: Spanish text POS-tagged with the Ahmet Aker Spanish POS Tagger and Lemmatizer, text files are in the main directory of the curpus
 * </ul>
 * 
 * <br>
 * 
 * Ingeger windowSize: the size of the window (number of words in each direction) used in the bag-of-words information extraction ({@link extinfbow.ExtInfBowParam})
 * 
 * <br><br>
 * 
 * String WeightingScheme: the weighting scheme used in the bag-of-words information extraction ({@link extinfbow.ExtInfBowParam})
 * <ul>
 * <li>Uniform: uniform weighting for all words (distance of x -&gt; weight of 1)
 * <li>Linear: weighting increasing linearly by the distance from the examined word (distance of x -&gt; weight of (windowSize-x)+1)
 * <li>Quadratic: weighting increasing quandratically by the distance from the examined word (distance of x -&gt; weight of ((windowSize-x)+1)^2)
 * </ul>
 * 
 * <br>
 * 
 * Boolean extractInformaionJustForInputWords: for most configurations of {@link dsm.DSM}, it is enought to only extract information for the
 * words in the evaluation datasets. With this parameter the user can set, whether information for all words or just for the words in the 
 * evaluation datasets are extracted (thus producing a significantly smaller extracted information file, which is perfectly enough for almost
 * all configurations of {@link dsm.DSM}) ({@link extinfbow.ExtInfBowParam}).
 * 
 * <br><br>
 * 
 * String corpusLocation: the location, at where the main directory of the corpus is located ({@link extinfbow.ExtInfBowParam})
 * 
 * <br><br>
 * 
 * Sting outputFileName: the name of the output extrected information file to be created ({@link extinfbow.ExtInfBowParam})
 * 
 * @author Dobó
 */
public class ExtractInformationBagOfWords {

    
    /**
     * This function extracts information from a corpus, using the bag-of-words approach. First it initializes the parameters from the command-line arguments, and also initializes some other
     * parts. Then it reads through the input words, because only the necessary information is stored in the information file. After this, it reads through the corpus and extracts the 
     * necessary information. And finally, it creates the output file from the extrected information.
     * @param args the command-line arguments for the input parameters
     */
    public static void main(String[] args) {

        initialize(args);
        
        if(sourceType==SourceType.EnParserV2 || sourceType==SourceType.EnParserV2WithSentenceCounts){
            readInputWordsEnParser();
            readFilesEnNewParser();
        }else if(sourceType==SourceType.EnParserV1){
            readInputWordsEnParser();
            readFilesEnOldParser();
        }else if(sourceType==SourceType.HuParser){
            readInputWordsHuParser();
            readFilesHuParser();
        }else if(sourceType==SourceType.EsTagger){
            readInputWordsEsTagger();
            readFilesEsTagger();
        }else{
            System.out.println("Wrong sourceType: " + sourceType);
            System.exit(1);
        }
        printOutExtractedInformation();

    }




}



