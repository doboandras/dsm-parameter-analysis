package dsm.util;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import net.didion.jwnl.dictionary.Dictionary;
import util.ComparablePair;

/**
 * A class containing miscellaneous utility functions.
 * 
 * @author Dobó
 */
public class MiscUtil {
    
    public static boolean createOutputFile = false;
    
    public static String inputLocation;
    public static String outputFileName;
    public static PrintWriter out;
    public static Dictionary dict;
    
    public static Map<String, String> nounDic = null;
    public static Map<String, String> adjDic = null;
    public static Map<String, String> advDic = null;
    public static Map<String, String> verbDic = null;
    
    public static final double LN2 = Math.log(2d);
    
    
    
    /**
     * A function calculating the binary logarithm of a double.
     * @param d the double whose binary logarithms should be calculated
     * @return the binary logarithm of @param d
     */
    public static double lb(double d){
        
        return Math.log(d)/LN2;
        
    }
    
    
    
    
    /**
     * This function adds the (feature, value) pairs from the @param mapToBeAdded to the @param concatenatedList, with the @param prefix added to the keys as prefix.
     * @param <V> the type of keys in @param concatenatedList
     * @param concatenatedList the concatenated list, to which the (feature, value) pairs should be added
     * @param mapToBeAdded a map, which contains the (feature, value) pairs to be added to @param concatenatedList
     * @param prefix a prefix for the feature keys to be added to @param concatenatedList
     */
    public static <V> void addFeatureValuePairsToArrayList(ArrayList<ComparablePair<String, Double>> concatenatedList, HashMap<V, Double> mapToBeAdded, String prefix){
        
        if(mapToBeAdded!=null){
        
            for(Map.Entry<V, Double> entryToBeAdded: mapToBeAdded.entrySet()){
                concatenatedList.add(new ComparablePair<String, Double>(prefix + entryToBeAdded.getKey().toString(), entryToBeAdded.getValue()));
            }
            
        }
        
    }
    
    
    
    
    /**
     * This function converts the first N elements of a list with (feature, value) pairs to a HashMap.
     * @param <V> the type of keys in @param concatenatedMap
     * @param list the list to be converted
     * @param n the number of elements to be used from the beginning of a list
     */
    public static <V> HashMap<String, Double> convertFirstNElementsOfListToMap(ArrayList<ComparablePair<String, Double>> list, Integer n){
        
        HashMap<String, Double> map = new HashMap<String, Double>();
        
        int limit;
        if(n==null || n>list.size()){
            limit=list.size();
        }else{
            limit=n;
        }
        
        for(int i=0;i<limit;i++){
            ComparablePair<String, Double> pair = list.get(i);
            map.put(pair.first, pair.second);
        }
        
        return map;
        
    }
    
    
}
