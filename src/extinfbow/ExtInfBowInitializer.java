package extinfbow;

import static extinfbow.ExtInfBowParam.*;
import static extinfbow.ExtInfBowUtil.*;
import com.shef.ac.uk.util.Util;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import net.didion.jwnl.JWNL;
import net.didion.jwnl.dictionary.Dictionary;

/**
 *
 * @author Dobó
 */
public class ExtInfBowInitializer {
    
    /**
     * This is the method that initializes the parameters from the command-line arguments, and also initializes some other parts. If there are no command line arguments, then 
     * the default value of the parametes is used.
     * @param args the command-line arguments for the input parameters
     */
    public static void initialize(String[] args){
        try {
            

            if(args.length>0){

                int index=0;
                
                sourceType=SourceType.valueOf(args[index++]);

                windowSize=Integer.parseInt(args[index++]);
                
                weightingScheme=WeightingScheme.valueOf(args[index++]);

                extractInformaionJustForInputWords = Boolean.parseBoolean(args[index++]);
                
                corpusLocation=args[index++];

                outputFileName=args[index++];

            }

            out = new PrintWriter(new FileWriter(outputFileName));
            
            if(sourceType.toString().startsWith("En")){
                
                JWNL.initialize(new FileInputStream(new File("input/map_properties.xml")));
                dict=Dictionary.getInstance();
                
            }else if(sourceType==SourceType.EsTagger){
                
                nounDic = Util.loadDictionary("AhmetAkerDictEs/nounDic.txt");
                adjDic = Util.loadDictionary("AhmetAkerDictEs/adjDic.txt");
                advDic = Util.loadDictionary("AhmetAkerDictEs/advDic.txt");
                verbDic = Util.loadDictionary("AhmetAkerDictEs/verbDic.txt");
                
            }
            
        } catch (Exception ex) {
            System.out.println("Error during the initialization!");
            ex.printStackTrace();
            System.exit(1);
        }
    }
    
}
