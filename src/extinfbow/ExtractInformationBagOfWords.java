package extinfbow;


import static extinfbow.ExtInfBowParam.*;
import static extinfbow.ExtInfBowInputWordsReader.*;
import static extinfbow.ExtInfBowInputCorpusReaderAndProcessor.*;
import static extinfbow.ExtInfBowPrinter.*;
import static extinfbow.ExtInfBowInitializer.initialize;





/**
 * This is a class for extracting information from a corpus, using the bag-of-words approach.
 * <br><br><br>
 * Usage:
 * <br><br>
 * java -cp DSM.jar extinfbow.ExtractInformationBagOfWords SourceType windowSize WeightingScheme extractInformaionJustForInputWords corpusLocation outputFileName
 * 
 * @author Andras
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



