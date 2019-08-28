package extinfparsed;


import static extinfparsed.ExtInfParsedParam.*;
import static extinfparsed.ExtInfParsedInputWordsReader.*;
import static extinfparsed.ExtInfParsedInputCorpusReaderAndProcessor.*;
import static extinfparsed.ExtInfParsedPrinter.*;
import static extinfparsed.ExtInfParsedInitializer.initialize;



/**
 * This is a class for extracting information from a parsed English corpus.
 * <br><br><br>
 * Usage:
 * <br><br>
 * java -cp DSM.jar extinfparsed.ExtractInformationFromParsedText SourceType WordType extractInformaionJustForInputWords outputFileName location
 * 
 * @author Andras
 */
public class ExtractInformationFromParsedText {

    /**
     * This function extracts information from a parsed English corpus. First it initializes the parameters from the command-line arguments, and also initializes some other
     * parts. Then it reads through the input words, because only the necessary information is stored in the information file. After this, it reads through the corpus and extracts the 
     * necessary information. And finally, it creates the output file from the extrected information.
     * @param args the command-line arguments for the input parameters
     */
    public static void main(String[] args) {

        initialize(args);
        readInputWords();
        if(sourceType==SourceType.EnParserV2 || sourceType==SourceType.EnParserV2WithSentenceCounts){
            readFilesNewParser();
        }else if(sourceType==SourceType.EnParserV1){
            readFilesOldParser();
        }
        printOutExtractedInformation();

    }

    


}



