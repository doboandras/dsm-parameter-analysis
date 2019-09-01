package extinfparsed;

import static extinfparsed.ExtInfParsedParam.*;
import static extinfparsed.ExtInfParsedUtil.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import net.didion.jwnl.JWNL;
import net.didion.jwnl.dictionary.Dictionary;

/**
 *
 * @author Dobó
 */
public class ExtInfParsedInitializer {
    
    /**
     * This is the method that initializes the parameters from the command-line arguments, and also initializes some other parts. If there are no command line arguments, then 
     * the default value of the parameters is used.
     * @param args the command-line arguments for the input parameters
     */
    public static void initialize(String[] args){
        try {
            if(args.length>0){

                int index=0;

                sourceType=SourceType.valueOf(args[index++]);
                
                extractInformaionJustForInputWords = Boolean.parseBoolean(args[index++]);

                corpusLocation=args[index++];
                
                outputFileName=args[index++];

            }

            out = new PrintWriter(new FileWriter(outputFileName));
            
            BufferedReader in = new BufferedReader(new FileReader("input/prepositions.txt"));
            String line;
            while((line=in.readLine())!=null){
                line=line.toLowerCase();
                prepositions.add(line.split(" ")[0]);
            }
            in.close();
            
            in = new BufferedReader(new FileReader("input/patientiveAmbitransitives.txt"));
            while((line=in.readLine())!=null){
                line=line.toLowerCase();
                patientiveAmbitransitives.add(line);
            }
            in.close();
            
            JWNL.initialize(new FileInputStream(new File("input/map_properties.xml")));
            dict=Dictionary.getInstance();
        } catch (Exception ex) {
            System.out.println("Error during the initialization!");
            ex.printStackTrace();
            System.exit(1);
        }
    }
    
}
