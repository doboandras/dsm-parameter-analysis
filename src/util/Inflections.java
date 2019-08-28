package util;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;


/**
 * This class handles the generation and storage of all the inflections for the nouns and verbs.
 * @author Andras
 */
public class Inflections {

    public static enum InflectionType {PLURAL, THIRDPERSONSINGULAR, PROGRESSIVE, PAST, PASTPARTICIPLE};
    
    //The inflections are stored in the following five HashMaps.
    static HashMap<String, String> pluralForms = new HashMap<String, String>();
    static HashMap<String, String> thirdPersonForms = new HashMap<String, String>();
    static HashMap<String, String> progressiveForms = new HashMap<String, String>();
    static HashMap<String, String> pastForms = new HashMap<String, String>();
    static HashMap<String, String> pastParticiples = new HashMap<String, String>();

    /**
     * This method generates the requested type of inflection for a set of verbs or nouns. This generation is done using the Morphg morphological generator tool, developed by John A. Carroll.
     * @param inflectionType the type of inflections
     * @param baseFormSet the set of verbs or nouns
     */
    public static void determineInflections(InflectionType inflectionType, HashSet<String> baseFormSet){
        try {
            
            ArrayList<String> list = new ArrayList<String>(baseFormSet);
            String POS;
            if(inflectionType==InflectionType.PLURAL){
                POS="+s_NN2";
            }else if(inflectionType==InflectionType.THIRDPERSONSINGULAR){
                POS="+s_VVZ";
            }else if(inflectionType==InflectionType.PROGRESSIVE){
                POS="+ing_VVG";
            }else if(inflectionType==InflectionType.PAST){
                POS="+ed_VVD";
            }else{
                POS="+en_VVN";
            }

            Process proc = Runtime.getRuntime().exec("./morphg.ix86_linux");
            PrintWriter procInput = new PrintWriter(proc.getOutputStream());
            for (String str : list) {
                procInput.println(str + POS);
            }
            procInput.close();
            
            BufferedReader procOutput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            if(inflectionType==InflectionType.PLURAL){
                for (String noun : list) {
                    pluralForms.put(noun, procOutput.readLine());
                }
            }else if(inflectionType==InflectionType.THIRDPERSONSINGULAR){
                for (String verb : list) {
                    thirdPersonForms.put(verb, procOutput.readLine());
                }
            }else if(inflectionType==InflectionType.PROGRESSIVE){
                for (String verb : list) {
                    progressiveForms.put(verb, procOutput.readLine());
                }
            }else if(inflectionType==InflectionType.PAST){
                for (String verb : list) {
                    pastForms.put(verb, procOutput.readLine());
                }
            }else{
                for (String verb : list) {
                    pastParticiples.put(verb, procOutput.readLine());
                }
            }
            procOutput.close();

        } catch (IOException ex) {
            System.out.println("Error during running morphg!");
            ex.printStackTrace();
            System.exit(1);
        }
    }

}
