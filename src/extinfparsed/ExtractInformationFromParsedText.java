package extinfparsed;


import static extinfparsed.ExtInfParsedParam.*;
import static extinfparsed.ExtInfParsedInputWordsReader.*;
import static extinfparsed.ExtInfParsedInputCorpusReaderAndProcessor.*;
import static extinfparsed.ExtInfParsedPrinter.*;
import static extinfparsed.ExtInfParsedInitializer.initialize;



/**
 * This is a class for extracting information from a parsed English corpus using a dependency-relation-based method.
 * 
 * <br><br><br>
 * 
 * Usage:
 * 
 * <br><br>
 * 
 * java -cp DSM.jar extinfparsed.ExtractInformationFromParsedText SourceType extractInformaionJustForInputWords corpusLocation outputFileName
 * 
 * <br><br>
 * 
 * String SourceType: the type of corpus used as input ({@link extinfparsed.ExtInfParsedParam})
 * <ul>
 * <li>EnParserV1: English text parsed with the C&amp;C CCG parser (https://github.com/chrzyki/candc), the POS tags of words are in 3rd position, 
 * text files are in the subdirectories of the main directory of the curpus
 * <li>EnParserV2: English text parsed with the C&amp;C CCG parser (https://github.com/chrzyki/candc), the POS tags of words are in 2nd position, 
 * text files are in the main directory of the curpus
 * <li>EnParserV2WithSentenceCounts: English text parsed with the C&amp;C CCG parser (https://github.com/chrzyki/candc), the POS tags of words are in 2nd position, 
 * there are also frequency counts included for each sentence, text files are in the main directory of the curpus
 * </ul>
 * 
 * <br>
 * 
 * Boolean extractInformaionJustForInputWords: for most configurations of {@link dsm.DSM}, it is enought to only extract information for the
 * words in the evaluation datasets. With this parameter the user can set, whether information for all words or just for the words in the 
 * evaluation datasets are extracted (thus producing a significantly smaller extracted information file, which is perfectly enough for almost
 * all configurations of {@link dsm.DSM}) ({@link extinfparsed.ExtInfParsedParam}).
 * 
 * <br><br>
 * 
 * String corpusLocation: the location, at where the main directory of the corpus is located ({@link extinfparsed.ExtInfParsedParam})
 * 
 * <br><br>
 * 
 * Sting outputFileName: the name of the output extrected information file to be created ({@link extinfparsed.ExtInfParsedParam})
 * 
 * @author Dobó
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



