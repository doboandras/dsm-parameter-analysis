package dsm.util;

import dsm.parameters.DimRed.DimensionalityReductionType;
import static dsm.parameters.DimRed.dimensionalityReductionType;
import dsm.parameters.FeatTransf.FeatureTransformationType;
import static dsm.parameters.FeatTransf.featureTransformationType;
import static dsm.parameters.MiscParam.*;
import dsm.parameters.Smooth.SmoothingType;
import static dsm.parameters.Smooth.smoothingType;
import dsm.parameters.VecSim.SimilarityMeasure;
import static dsm.parameters.VecSim.similarityMeasure;
import static dsm.parameters.VecSim.similarityMeasureString;
import dsm.parameters.Weight.WeightingScheme;
import static dsm.parameters.Weight.weightingScheme;
import static dsm.util.MiscUtil.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import util.Paraphrase;
import static dsm.parameters.VecSim.pearsModPattern;
import static dsm.parameters.VecSim.pearsCombPattern;
import static dsm.parameters.VecSim.pearsMbAdjCosModPattern;
import static dsm.parameters.VecSim.pearsMbAdjCosPfModPattern;
import static dsm.parameters.VecSim.pearsMbModPattern;

/**
 * The class for reading in the extracted information or word vectors used as input for the DSM.
 * 
 * <br><br>
 * 
 * Usage of this parameter:
 * 
 * <br><br>
 * 
 * String InputData: the type of extracted information or word vectors used as input for the DSM
 * <ul>
 * <li>"Corpus" Corpus bagOfWords corpusStringSuffix: extracted information from a corpus used as input
 * <li>"Vectors" WordVectors wordVectorsDimension: word vectors used as input
 * </ul>
 * 
 * <br>
 * 
 * String Corpus: the corpus used in the informaiton extraction phase. It is only used in the determination of the input extracted information file name, 
 * and for determining the language of the test dataset used and of the other input files.
 * <ul>
 * <li>BNC: the British National Corpus
 * <li>EnWiki: the text of the 26.05.2011 dump of the English Wikipedia
 * <li>UkWaC: the ukWaC corpus
 * <li>HuWiki: the text of the 23.01.2012 dump of the Hungarian Wikipedia
 * <li>EsWki: the Spanish Wikicorpus
 * </ul>
 * 
 * <br>
 * 
 * Boolean bagOfWords: it determines whether the input extracted information was created using a bag-of-words method, 
 * or from a parsed text using a dependency-relation-based method. It is only used in the determination of the input extracted information file name.
 * 
 * <br><br>
 * 
 * String corpusStringSuffix: a suffix for the extracted information file name (e.g. the Weighting scheme used in case of 
 * bag-of-words information extraction methods). It is only used in the determination of the input extracted information file name.
 * 
 * <br><br>
 * 
 * String WordVectors: the word vectors used as input for the DSM. It is only used for determining the structure of the input file.
 * 
 * <br><br>
 * 
 * Integer wordVectorsDimension: the number of dimensions of the input word vectors.
 * 
 * @author Dobó
 */
public class ExtractedInfoReader {
    
    public static enum InputDataType {Corpus, Vectors};
    public static InputDataType inputDataType=InputDataType.Corpus;
    public static enum WordVectors {GoogleNews, Baroni2014, Levy2015, Pennington2014, Salle2016, Speer2017, Yin2016};
    public static WordVectors wordVectors=WordVectors.GoogleNews;
    public static String wordVectorsFileName = null;
    public static Integer wordVectorsDimension = null;
    public static enum Corpus {PA61K, P9000, BNC, TaggedWeb1T5Gram, ParsedWeb1T5Gram, P170K, PubMedAbstractsAndTitles, EnWiki, EnWiki20151201, UkWaC, HuWiki, EsWki};
    public static Corpus corpus=Corpus.PA61K;
    public static boolean bagOfWords=false;
    
    
    





    /**
     * This function reads in the information from the extracted information file. 
     * Only that information is read in, which is needed for the selected version of the algorithm. 
     * The toLowerCase() function is not called on the input lines, as the extracted information 
     * files already only contain lower case words.
     */
    public static void readExtractedInformationFile(){
        
        int i=0;

        try {

            BufferedReader in = new BufferedReader(new FileReader(inputLocation));

            in.readLine();
            
            if(minimumWordSimpleFrequency!=null){
            
                readStringDouble(in, "verbWithObjectSimpleFrequencies", nounSimpleFrequencies, null, null, allNouns, false, i++);
                
            }else{
                
                readUntil(in, "verbWithObjectSimpleFrequencies");

            }
            
            if(minimumFeatureSimpleFrequency!=null){
            
                readParaphraseDouble(in, "verbWithSubjectSimpleFrequencies", verbWithObjectSimpleFrequencies, null, null, filterStopWords, i++);
                readParaphraseDouble(in, "ncmodWithNounSimpleFrequencies", verbWithSubjectSimpleFrequencies, null, null, filterStopWords, i++);
                readStringDouble(in, "nounFrequencies", ncmodWithNounSimpleFrequencies, null, null, null, filterStopWords, i++);
                
            }else{
                
                readUntil(in, "nounFrequencies");

            }
            
            readStringDouble(in, "verbWithObjectFrequencies", nounFrequencies, nounSimpleFrequencies, minimumWordSimpleFrequency, allNouns, false, i++);
            
            if(smoothingType!=SmoothingType.NoSmooth || featureTransformationType!=FeatureTransformationType.NoTransf || 
                    weightingScheme.toString().matches(".*Pmi.*") || weightingScheme.toString().matches("(Sq)?LogLhr") || weightingScheme.toString().matches("Rapp_.") ||
                    weightingScheme.toString().matches("((Hyp)|(Bin)|(Poisson(Stirling)?))Lh") || weightingScheme.toString().matches("(T|Z)Test.*") || 
                    weightingScheme.toString().matches("ChiSquare.*") || weightingScheme.toString().matches("CondProb.*") || 
                    weightingScheme==WeightingScheme.JointProb || weightingScheme.toString().matches("(TfIdf_3)|(TfIcf_(1|2))") || weightingScheme==WeightingScheme.Dice || 
                    weightingScheme==WeightingScheme.Gref_2 || weightingScheme.toString().matches("RelRisk_.") || 
                    weightingScheme==WeightingScheme.Liddell || weightingScheme==WeightingScheme.Jaccard || weightingScheme==WeightingScheme.GMean || weightingScheme==WeightingScheme.MinSens || 
                    weightingScheme.toString().matches("OddsRatio_.") || weightingScheme.toString().matches("Lin_3") || 
                    weightingScheme.toString().matches("SimpleLl") || weightingScheme.toString().matches("Allr") || weightingScheme.toString().matches("KrippendorffAlpha") || 
                    weightingScheme.toString().matches("ScottPi") || weightingScheme.toString().matches("(Cohen)?Kappa") || weightingScheme.toString().matches("Ochiai") || 
                    weightingScheme.toString().matches("Cca") || weightingScheme.toString().matches("(A)?N(g|c)d(Mod)?") || weightingScheme.toString().matches("Simpson_.(Mod)?") || 
                    weightingScheme.toString().matches("(Lfb)?M(d|e).*") || weightingScheme.toString().matches("SalienceMod_.") || weightingScheme.toString().matches("PoissonSig") || 
                    weightingScheme.toString().matches("SokalMichiner") || weightingScheme.toString().matches("RogersTanimoto") || weightingScheme.toString().matches("Hamann") || 
                    weightingScheme.toString().matches("SokalSneath_.") || weightingScheme.toString().matches("Kulczynski_.") || weightingScheme.toString().matches("Yulle.") || 
                    weightingScheme.toString().matches("Pears") || weightingScheme.toString().matches("Fager") || weightingScheme.toString().matches("Unis.*") || 
                    weightingScheme.toString().matches("TCombCost") || weightingScheme.toString().matches("Phi") || weightingScheme.toString().matches("J") || 
                    weightingScheme.toString().matches("Gini") || weightingScheme.toString().matches("Confidence") || weightingScheme.toString().matches("DriverKroeber") || 
                    weightingScheme.toString().matches("Laplace") || weightingScheme.toString().matches("PiaterskyShapiro") || weightingScheme.toString().matches("Certainty") || 
                    weightingScheme.toString().matches("AddedValue")){
                
                readParaphraseDouble(in, "verbWithSubjectFrequencies", verbWithObjectFrequencies, verbWithObjectSimpleFrequencies, 
                        minimumFeatureSimpleFrequency, filterStopWords, i++);
                readParaphraseDouble(in, "ncmodWithNounFrequencies", verbWithSubjectFrequencies, verbWithSubjectSimpleFrequencies, 
                        minimumFeatureSimpleFrequency, filterStopWords, i++);
                readStringDouble(in, "objectFrequencies", ncmodWithNounFrequencies, ncmodWithNounSimpleFrequencies, 
                        minimumFeatureSimpleFrequency, null, filterStopWords, i++);
                
                readStringDouble(in, "subjectFrequencies", objectFrequencies, nounSimpleFrequencies, 
                        minimumWordSimpleFrequency, allNouns, false, i++);
                readStringDouble(in, "nounWithNcmodFrequencies", subjectFrequencies, nounSimpleFrequencies, 
                        minimumWordSimpleFrequency, allNouns, false, i++);
                readStringDouble(in, "objVerbSimpleTuples", nounWithNcmodFrequencies, nounSimpleFrequencies, 
                        minimumWordSimpleFrequency, allNouns, false, i++);

            }else{
                
                readUntil(in, "objVerbSimpleTuples");

            }
            
            if(minimumWordFeatureTupleSimpleFrequency!=null){
                
                readStringParaphraseDouble(in, "subjVerbSimpleTuples", objVerbSimpleTuples, null, null, null, allNouns, filterStopWords, i++);
                readStringParaphraseDouble(in, "nounNcmodSimpleTuples", subjVerbSimpleTuples, null, null, null, allNouns, filterStopWords, i++);
                readStringStringDouble(in, "objVerbTuples", nounNcmodSimpleTuples, null, null, null, allNouns, filterStopWords, i++);
                
            }else{
                
                readUntil(in, "objVerbTuples");

            }
            
            readStringParaphraseDouble(in, "subjVerbTuples", objVerbTuples, nounSimpleFrequencies, 
                    verbWithObjectSimpleFrequencies, objVerbSimpleTuples, allNouns, filterStopWords, i++);
            readStringParaphraseDouble(in, "nounNcmodTuples", subjVerbTuples, nounSimpleFrequencies, 
                    verbWithSubjectSimpleFrequencies, subjVerbSimpleTuples, allNouns, filterStopWords, i++);
            readStringStringDouble(in, "verbObjectTuples", nounNcmodTuples, nounSimpleFrequencies, 
                    ncmodWithNounSimpleFrequencies, nounNcmodSimpleTuples, allNouns, filterStopWords, i++);
            
            if(weightingScheme==WeightingScheme.Plffi || weightingScheme==WeightingScheme.Qlflnw || 
                    weightingScheme.toString().matches("(TfIdf_(1|2|4|5|7|8|9))|(TfIcf_3)") || weightingScheme==WeightingScheme.Gref_1 || weightingScheme.toString().matches("(PmiX)?Lin_1..") || 
                    weightingScheme==WeightingScheme.Lin_2 || weightingScheme.toString().matches("Okapi_.") || weightingScheme==WeightingScheme.Ltu || 
                    weightingScheme==WeightingScheme.Atc || weightingScheme==WeightingScheme.PmiXTfIdf_1 || 
                    weightingScheme==WeightingScheme.PmiXOkapi_1 || weightingScheme==WeightingScheme.PmiXLtu || 
                    similarityMeasure==SimilarityMeasure.Pears || similarityMeasure==SimilarityMeasure.Spearm || 
                    similarityMeasure==SimilarityMeasure.CorrKiela || similarityMeasure==SimilarityMeasure.Covariance || 
                    similarityMeasure==SimilarityMeasure.Lin || similarityMeasure==SimilarityMeasure.Dfvmb || 
                    pearsModPattern.matcher(similarityMeasureString).matches() || pearsCombPattern.matcher(similarityMeasureString).matches() || 
                    pearsMbAdjCosModPattern.matcher(similarityMeasureString).matches() || pearsMbAdjCosPfModPattern.matcher(similarityMeasureString).matches() || 
                    pearsMbModPattern.matcher(similarityMeasureString).matches() || similarityMeasureString.matches("PenroseShape(Mod_.*)?") || 
                    dimensionalityReductionType==DimensionalityReductionType.SVD){

                readParaphraseLong(in, "verbSubjectTuples", verbObjectTuples, verbWithObjectSimpleFrequencies, filterStopWords, i++);
                readParaphraseLong(in, "ncmodNounTuples", verbSubjectTuples, verbWithSubjectSimpleFrequencies, filterStopWords, i++);
                readStringLong(in, "nounKNSNCounts", ncmodNounTuples, ncmodWithNounSimpleFrequencies, filterStopWords, i++);
                
            }else{
                
                readUntil(in, "nounKNSNCounts");
                
            }
            
            
            if(smoothingType!=SmoothingType.NoSmooth){
            
                readKNSNCountsForStrings(in, "verbObjectTuplesKNSNCounts", nounKNSNCounts, nounSimpleFrequencies, 
                        minimumWordSimpleFrequency, allNouns, false, i++);
                
                readKNSNCountsForParaphrases(in, "verbSubjectTuplesKNSNCounts", verbObjectTuplesKNSNCounts, 
                        verbWithObjectSimpleFrequencies, minimumFeatureSimpleFrequency, filterStopWords, i++);
                readKNSNCountsForParaphrases(in, "ncmodNounTuplesKNSNCounts", verbSubjectTuplesKNSNCounts, 
                        verbWithSubjectSimpleFrequencies, minimumFeatureSimpleFrequency, filterStopWords, i++);
                readKNSNCountsForStrings(in, "allNounKNSNCounts", ncmodNounTuplesKNSNCounts, 
                        ncmodWithNounSimpleFrequencies, minimumFeatureSimpleFrequency, null, filterStopWords, i++);
                
                readAllKNSNCounts(in, "verbSimpleFrequencies", allNounKNSNCounts, i++);
                
            }else{
                
                readUntil(in, "verbSimpleFrequencies");

            }

            
            
            
            
            if(minimumWordSimpleFrequency!=null){
            
                readStringDouble(in, "dobjWithVerbSimpleFrequencies", verbSimpleFrequencies, null, null, allVerbs, false, i++);
                
            }else{
                
                readUntil(in, "dobjWithVerbSimpleFrequencies");

            }
            
            if(minimumFeatureSimpleFrequency!=null){
            
                readStringDouble(in, "ncsubjWithVerbSimpleFrequencies", dobjWithVerbSimpleFrequencies, null, null, null, filterStopWords, i++);
                readStringDouble(in, "prepWithVerbSimpleFrequencies", ncsubjWithVerbSimpleFrequencies, null, null, null, filterStopWords, i++);
                readStringDouble(in, "obj2WithVerbSimpleFrequencies", prepWithVerbSimpleFrequencies, null, null, null, filterStopWords, i++);
                readStringDouble(in, "verbFrequencies", obj2WithVerbSimpleFrequencies, null, null, null, filterStopWords, i++);
                
            }else{
                
                readUntil(in, "verbFrequencies");

            }
            
            readStringDouble(in, "dobjWithVerbFrequencies", verbFrequencies, verbSimpleFrequencies, minimumWordSimpleFrequency, allVerbs, false, i++);
            
            if(smoothingType!=SmoothingType.NoSmooth || featureTransformationType!=FeatureTransformationType.NoTransf || 
                    weightingScheme.toString().matches(".*Pmi.*") || weightingScheme.toString().matches("(Sq)?LogLhr") || weightingScheme.toString().matches("Rapp_.") || 
                    weightingScheme.toString().matches("((Hyp)|(Bin)|(Poisson(Stirling)?))Lh") || weightingScheme.toString().matches("(T|Z)Test.*") || 
                    weightingScheme.toString().matches("ChiSquare.*") || weightingScheme.toString().matches("CondProb.*") || 
                    weightingScheme==WeightingScheme.JointProb || weightingScheme.toString().matches("(TfIdf_3)|(TfIcf_(1|2))") || weightingScheme==WeightingScheme.Dice || 
                    weightingScheme==WeightingScheme.Gref_2 || weightingScheme.toString().matches("RelRisk_.") || 
                    weightingScheme==WeightingScheme.Liddell || weightingScheme==WeightingScheme.Jaccard || weightingScheme==WeightingScheme.GMean || weightingScheme==WeightingScheme.MinSens || 
                    weightingScheme.toString().matches("OddsRatio_.") || weightingScheme.toString().matches("Lin_3") || 
                    weightingScheme.toString().matches("SimpleLl") || weightingScheme.toString().matches("Allr") || weightingScheme.toString().matches("KrippendorffAlpha") || 
                    weightingScheme.toString().matches("ScottPi") || weightingScheme.toString().matches("(Cohen)?Kappa") || weightingScheme.toString().matches("Ochiai") || 
                    weightingScheme.toString().matches("Cca") || weightingScheme.toString().matches("(A)?N(g|c)d(Mod)?") || weightingScheme.toString().matches("Simpson_.(Mod)?") || 
                    weightingScheme.toString().matches("(Lfb)?M(d|e).*") || weightingScheme.toString().matches("SalienceMod_.") || weightingScheme.toString().matches("PoissonSig") || 
                    weightingScheme.toString().matches("SokalMichiner") || weightingScheme.toString().matches("RogersTanimoto") || weightingScheme.toString().matches("Hamann") || 
                    weightingScheme.toString().matches("SokalSneath_.") || weightingScheme.toString().matches("Kulczynski_.") || weightingScheme.toString().matches("Yulle.") || 
                    weightingScheme.toString().matches("Pears") || weightingScheme.toString().matches("Fager") || weightingScheme.toString().matches("Unis.*") || 
                    weightingScheme.toString().matches("TCombCost") || weightingScheme.toString().matches("Phi") || weightingScheme.toString().matches("J") || 
                    weightingScheme.toString().matches("Gini") || weightingScheme.toString().matches("Confidence") || weightingScheme.toString().matches("DriverKroeber") || 
                    weightingScheme.toString().matches("Laplace") || weightingScheme.toString().matches("PiaterskyShapiro") || weightingScheme.toString().matches("Certainty") || 
                    weightingScheme.toString().matches("AddedValue")){

                readStringDouble(in, "ncsubjWithVerbFrequencies", dobjWithVerbFrequencies, dobjWithVerbSimpleFrequencies, 
                        minimumFeatureSimpleFrequency, null, filterStopWords, i++);
                readStringDouble(in, "prepWithVerbFrequencies", ncsubjWithVerbFrequencies, ncsubjWithVerbSimpleFrequencies, 
                        minimumFeatureSimpleFrequency, null, filterStopWords, i++);
                readStringDouble(in, "obj2WithVerbFrequencies", prepWithVerbFrequencies, prepWithVerbSimpleFrequencies, 
                        minimumFeatureSimpleFrequency, null, filterStopWords, i++);
                readStringDouble(in, "verbWithDobjFrequencies", obj2WithVerbFrequencies, obj2WithVerbSimpleFrequencies, 
                        minimumFeatureSimpleFrequency, null, filterStopWords, i++);
                
                readStringDouble(in, "verbWithNcsubjFrequencies", verbWithDobjFrequencies, verbSimpleFrequencies, 
                        minimumWordSimpleFrequency, allVerbs, false, i++);
                readStringDouble(in, "verbWithPrepFrequencies", verbWithNcsubjFrequencies, verbSimpleFrequencies, 
                        minimumWordSimpleFrequency, allVerbs, false, i++);
                readStringDouble(in, "verbWithObj2Frequencies", verbWithPrepFrequencies, verbSimpleFrequencies, 
                        minimumWordSimpleFrequency, allVerbs, false, i++);
                readStringDouble(in, "verbDobjSimpleTuples", verbWithObj2Frequencies, verbSimpleFrequencies, 
                        minimumWordSimpleFrequency, allVerbs, false, i++);

            }else{

                readUntil(in, "verbDobjSimpleTuples");

            }
            
            
            if(minimumWordFeatureTupleSimpleFrequency!=null){
            
                readStringStringDouble(in, "verbNcsubjSimpleTuples", verbDobjSimpleTuples, null, null, null, allVerbs, filterStopWords, i++);
                readStringStringDouble(in, "verbPrepSimpleTuples", verbNcsubjSimpleTuples, null, null, null, allVerbs, filterStopWords, i++);
                readStringStringDouble(in, "verbObj2SimpleTuples", verbPrepSimpleTuples, null, null, null, allVerbs, filterStopWords, i++);
                readStringStringDouble(in, "verbDobjTuples", verbObj2SimpleTuples, null, null, null, allVerbs, filterStopWords, i++);
            
            }else{
                
                readUntil(in, "verbDobjTuples");

            }
            
            readStringStringDouble(in, "verbNcsubjTuples", verbDobjTuples, verbSimpleFrequencies, 
                    dobjWithVerbSimpleFrequencies, verbDobjSimpleTuples, allVerbs, filterStopWords, i++);
            readStringStringDouble(in, "verbPrepTuples", verbNcsubjTuples, verbSimpleFrequencies, 
                    ncsubjWithVerbSimpleFrequencies, verbNcsubjSimpleTuples, allVerbs, filterStopWords, i++);
            readStringStringDouble(in, "verbObj2Tuples", verbPrepTuples, verbSimpleFrequencies, 
                    prepWithVerbSimpleFrequencies, verbPrepSimpleTuples, allVerbs, filterStopWords, i++);
            readStringStringDouble(in, "dobjVerbTuples", verbObj2Tuples, verbSimpleFrequencies, 
                    obj2WithVerbSimpleFrequencies, verbObj2SimpleTuples, allVerbs, filterStopWords, i++);
            
            if(weightingScheme==WeightingScheme.Plffi || weightingScheme==WeightingScheme.Qlflnw || 
                    weightingScheme.toString().matches("(TfIdf_(1|2|4|5|7|8|9))|(TfIcf_3)") || weightingScheme==WeightingScheme.Gref_1 || weightingScheme.toString().matches("(PmiX)?Lin_1..") || 
                    weightingScheme==WeightingScheme.Lin_2 || weightingScheme.toString().matches("Okapi_.") || weightingScheme==WeightingScheme.Ltu || 
                    weightingScheme==WeightingScheme.Atc || weightingScheme==WeightingScheme.PmiXTfIdf_1 || 
                    weightingScheme==WeightingScheme.PmiXOkapi_1 || weightingScheme==WeightingScheme.PmiXLtu || 
                    similarityMeasure==SimilarityMeasure.Pears || similarityMeasure==SimilarityMeasure.Spearm || 
                    similarityMeasure==SimilarityMeasure.CorrKiela || similarityMeasure==SimilarityMeasure.Covariance || 
                    similarityMeasure==SimilarityMeasure.Lin || similarityMeasure==SimilarityMeasure.Dfvmb || 
                    pearsModPattern.matcher(similarityMeasureString).matches() || pearsCombPattern.matcher(similarityMeasureString).matches() || 
                    pearsMbAdjCosModPattern.matcher(similarityMeasureString).matches() || pearsMbAdjCosPfModPattern.matcher(similarityMeasureString).matches() || 
                    pearsMbModPattern.matcher(similarityMeasureString).matches() || similarityMeasureString.matches("PenroseShape(Mod_.*)?") || 
                    dimensionalityReductionType==DimensionalityReductionType.SVD){

                readStringLong(in, "ncsubjVerbTuples", dobjVerbTuples, dobjWithVerbSimpleFrequencies, filterStopWords, i++);
                readStringLong(in, "prepVerbTuples", ncsubjVerbTuples, ncsubjWithVerbSimpleFrequencies, filterStopWords, i++);
                readStringLong(in, "obj2VerbTuples", prepVerbTuples, prepWithVerbSimpleFrequencies, filterStopWords, i++);
                readStringLong(in, "verbKNSNCounts", obj2VerbTuples, obj2WithVerbSimpleFrequencies, filterStopWords, i++);
                
            }else{
                
                readUntil(in, "verbKNSNCounts");
                
            }
            
            
            if(smoothingType!=SmoothingType.NoSmooth){
            
                readKNSNCountsForStrings(in, "dobjVerbTuplesKNSNCounts", verbKNSNCounts, 
                        verbSimpleFrequencies, minimumWordSimpleFrequency, allVerbs, false, i++);
                
                readKNSNCountsForStrings(in, "ncsubjVerbTuplesKNSNCounts", dobjVerbTuplesKNSNCounts, 
                        dobjWithVerbSimpleFrequencies, minimumFeatureSimpleFrequency, null, filterStopWords, i++);
                readKNSNCountsForStrings(in, "prepVerbTuplesKNSNCounts", ncsubjVerbTuplesKNSNCounts, 
                        ncsubjWithVerbSimpleFrequencies, minimumFeatureSimpleFrequency, null, filterStopWords, i++);
                readKNSNCountsForStrings(in, "obj2VerbTuplesKNSNCounts", prepVerbTuplesKNSNCounts, 
                        prepWithVerbSimpleFrequencies, minimumFeatureSimpleFrequency, null, filterStopWords, i++);
                readKNSNCountsForStrings(in, "allVerbKNSNCounts", obj2VerbTuplesKNSNCounts, 
                        obj2WithVerbSimpleFrequencies, minimumFeatureSimpleFrequency, null, filterStopWords, i++);
                
                readAllKNSNCounts(in, "adjectiveSimpleFrequencies", allVerbKNSNCounts, i++);
                
            }else{
                
                readUntil(in, "adjectiveSimpleFrequencies");

            }

            
            
            
            
            if(minimumWordSimpleFrequency!=null){
            
                readStringDouble(in, "nounWithAdjSimpleFrequencies", adjectiveSimpleFrequencies, null, null, allAdjectives, false, i++);
                
            }else{
                
                readUntil(in, "nounWithAdjSimpleFrequencies");

            }
            
            if(minimumFeatureSimpleFrequency!=null){
                
                readStringDouble(in, "adjectiveFrequencies", nounWithAdjSimpleFrequencies, null, null, null, filterStopWords, i++);
            
            }else{
                
                readUntil(in, "adjectiveFrequencies");

            }
            
            readStringDouble(in, "nounWithAdjFrequencies", adjectiveFrequencies, adjectiveSimpleFrequencies, minimumWordSimpleFrequency, allAdjectives, false, i++);
            
            
            if(smoothingType!=SmoothingType.NoSmooth || featureTransformationType!=FeatureTransformationType.NoTransf || 
                    weightingScheme.toString().matches(".*Pmi.*") || weightingScheme.toString().matches("(Sq)?LogLhr") || weightingScheme.toString().matches("Rapp_.") || 
                    weightingScheme.toString().matches("((Hyp)|(Bin)|(Poisson(Stirling)?))Lh") || weightingScheme.toString().matches("(T|Z)Test.*") || 
                    weightingScheme.toString().matches("ChiSquare.*") || weightingScheme.toString().matches("CondProb.*") || 
                    weightingScheme==WeightingScheme.JointProb || weightingScheme.toString().matches("(TfIdf_3)|(TfIcf_(1|2))") || weightingScheme==WeightingScheme.Dice || 
                    weightingScheme==WeightingScheme.Gref_2 || weightingScheme.toString().matches("RelRisk_.") || 
                    weightingScheme==WeightingScheme.Liddell || weightingScheme==WeightingScheme.Jaccard || weightingScheme==WeightingScheme.GMean || weightingScheme==WeightingScheme.MinSens || 
                    weightingScheme.toString().matches("OddsRatio_.") || weightingScheme.toString().matches("Lin_3") || 
                    weightingScheme.toString().matches("SimpleLl") || weightingScheme.toString().matches("Allr") || weightingScheme.toString().matches("KrippendorffAlpha") || 
                    weightingScheme.toString().matches("ScottPi") || weightingScheme.toString().matches("(Cohen)?Kappa") || weightingScheme.toString().matches("Ochiai") || 
                    weightingScheme.toString().matches("Cca") || weightingScheme.toString().matches("(A)?N(g|c)d(Mod)?") || weightingScheme.toString().matches("Simpson_.(Mod)?") || 
                    weightingScheme.toString().matches("(Lfb)?M(d|e).*") || weightingScheme.toString().matches("SalienceMod_.") || weightingScheme.toString().matches("PoissonSig") || 
                    weightingScheme.toString().matches("SokalMichiner") || weightingScheme.toString().matches("RogersTanimoto") || weightingScheme.toString().matches("Hamann") || 
                    weightingScheme.toString().matches("SokalSneath_.") || weightingScheme.toString().matches("Kulczynski_.") || weightingScheme.toString().matches("Yulle.") || 
                    weightingScheme.toString().matches("Pears") || weightingScheme.toString().matches("Fager") || weightingScheme.toString().matches("Unis.*") || 
                    weightingScheme.toString().matches("TCombCost") || weightingScheme.toString().matches("Phi") || weightingScheme.toString().matches("J") || 
                    weightingScheme.toString().matches("Gini") || weightingScheme.toString().matches("Confidence") || weightingScheme.toString().matches("DriverKroeber") || 
                    weightingScheme.toString().matches("Laplace") || weightingScheme.toString().matches("PiaterskyShapiro") || weightingScheme.toString().matches("Certainty") || 
                    weightingScheme.toString().matches("AddedValue")){
                readStringDouble(in, "adjWithNounFrequencies", nounWithAdjFrequencies, nounWithAdjSimpleFrequencies, 
                        minimumFeatureSimpleFrequency, null, filterStopWords, i++);
                
                readStringDouble(in, "adjNounSimpleTuples", adjWithNounFrequencies, adjectiveSimpleFrequencies, 
                        minimumWordSimpleFrequency, allAdjectives, false, i++);
            }else{
                readUntil(in, "adjNounSimpleTuples");
            }

            
            if(minimumWordFeatureTupleSimpleFrequency!=null){
            
                readStringStringDouble(in, "adjNounTuples", adjNounSimpleTuples, null, null, null, allAdjectives, filterStopWords, i++);
            
            }else{
                
                readUntil(in, "adjNounTuples");

            }
            
            readStringStringDouble(in, "nounAdjTuples", adjNounTuples, adjectiveSimpleFrequencies, 
                    nounWithAdjSimpleFrequencies, adjNounSimpleTuples, allAdjectives, filterStopWords, i++);
            
            if(weightingScheme==WeightingScheme.Plffi || weightingScheme==WeightingScheme.Qlflnw || 
                    weightingScheme.toString().matches("(TfIdf_(1|2|4|5|7|8|9))|(TfIcf_3)") || weightingScheme==WeightingScheme.Gref_1 || weightingScheme.toString().matches("(PmiX)?Lin_1..") || 
                    weightingScheme==WeightingScheme.Lin_2 || weightingScheme.toString().matches("Okapi_.") || weightingScheme==WeightingScheme.Ltu || 
                    weightingScheme==WeightingScheme.Atc || weightingScheme==WeightingScheme.PmiXTfIdf_1 || 
                    weightingScheme==WeightingScheme.PmiXOkapi_1 || weightingScheme==WeightingScheme.PmiXLtu || 
                    similarityMeasure==SimilarityMeasure.Pears || similarityMeasure==SimilarityMeasure.Spearm || 
                    similarityMeasure==SimilarityMeasure.CorrKiela || similarityMeasure==SimilarityMeasure.Covariance || 
                    similarityMeasure==SimilarityMeasure.Lin || similarityMeasure==SimilarityMeasure.Dfvmb || 
                    pearsModPattern.matcher(similarityMeasureString).matches() || pearsCombPattern.matcher(similarityMeasureString).matches() || 
                    pearsMbAdjCosModPattern.matcher(similarityMeasureString).matches() || pearsMbAdjCosPfModPattern.matcher(similarityMeasureString).matches() || 
                    pearsMbModPattern.matcher(similarityMeasureString).matches() || similarityMeasureString.matches("PenroseShape(Mod_.*)?") || 
                    dimensionalityReductionType==DimensionalityReductionType.SVD){
                readStringLong(in, "adjectiveKNSNCounts", nounAdjTuples, nounWithAdjSimpleFrequencies, filterStopWords, i++);
            }else{
                readUntil(in, "adjectiveKNSNCounts"); 
            }
            

            if(smoothingType!=SmoothingType.NoSmooth){
            
                readKNSNCountsForStrings(in, "nounAdjTuplesKNSNCounts", adjectiveKNSNCounts, 
                        adjectiveSimpleFrequencies, minimumWordSimpleFrequency, allAdjectives, false, i++);
                
                readKNSNCountsForStrings(in, "allAdjectiveKNSNCounts", nounAdjTuplesKNSNCounts, 
                        nounWithAdjSimpleFrequencies, minimumFeatureSimpleFrequency, null, filterStopWords, i++);
                
                readAllKNSNCounts(in, "adverbSimpleFrequencies", allAdjectiveKNSNCounts, i++);
                
            }else{
                
                readUntil(in, "adverbSimpleFrequencies");

            }



            
            if(minimumWordSimpleFrequency!=null){
            
                readStringDouble(in, "wordWithAdvSimpleFrequencies", adverbSimpleFrequencies, null, null, allAdverbs, false, i++);
                
            }else{
                
                readUntil(in, "wordWithAdvSimpleFrequencies");

            }
            
            if(minimumFeatureSimpleFrequency!=null){
                
                readStringDouble(in, "adverbFrequencies", wordWithAdvSimpleFrequencies, null, null, null, filterStopWords, i++);
                
            }else{
                
                readUntil(in, "adverbFrequencies");

            }
            
            readStringDouble(in, "wordWithAdvFrequencies", adverbFrequencies, adverbSimpleFrequencies, minimumWordSimpleFrequency, allAdverbs, false, i++);
            
            
            if(smoothingType!=SmoothingType.NoSmooth || featureTransformationType!=FeatureTransformationType.NoTransf || 
                    weightingScheme.toString().matches(".*Pmi.*") || weightingScheme.toString().matches("(Sq)?LogLhr") || weightingScheme.toString().matches("Rapp_.") || 
                    weightingScheme.toString().matches("((Hyp)|(Bin)|(Poisson(Stirling)?))Lh") || weightingScheme.toString().matches("(T|Z)Test.*") || 
                    weightingScheme.toString().matches("ChiSquare.*") || weightingScheme.toString().matches("CondProb.*") || 
                    weightingScheme==WeightingScheme.JointProb || weightingScheme.toString().matches("(TfIdf_3)|(TfIcf_(1|2))") || weightingScheme==WeightingScheme.Dice || 
                    weightingScheme==WeightingScheme.Gref_2 || weightingScheme.toString().matches("RelRisk_.") || 
                    weightingScheme==WeightingScheme.Liddell || weightingScheme==WeightingScheme.Jaccard || weightingScheme==WeightingScheme.GMean || weightingScheme==WeightingScheme.MinSens || 
                    weightingScheme.toString().matches("OddsRatio_.") || weightingScheme.toString().matches("Lin_3") || 
                    weightingScheme.toString().matches("SimpleLl") || weightingScheme.toString().matches("Allr") || weightingScheme.toString().matches("KrippendorffAlpha") || 
                    weightingScheme.toString().matches("ScottPi") || weightingScheme.toString().matches("(Cohen)?Kappa") || weightingScheme.toString().matches("Ochiai") || 
                    weightingScheme.toString().matches("Cca") || weightingScheme.toString().matches("(A)?N(g|c)d(Mod)?") || weightingScheme.toString().matches("Simpson_.(Mod)?") || 
                    weightingScheme.toString().matches("(Lfb)?M(d|e).*") || weightingScheme.toString().matches("SalienceMod_.") || weightingScheme.toString().matches("PoissonSig") || 
                    weightingScheme.toString().matches("SokalMichiner") || weightingScheme.toString().matches("RogersTanimoto") || weightingScheme.toString().matches("Hamann") || 
                    weightingScheme.toString().matches("SokalSneath_.") || weightingScheme.toString().matches("Kulczynski_.") || weightingScheme.toString().matches("Yulle.") || 
                    weightingScheme.toString().matches("Pears") || weightingScheme.toString().matches("Fager") || weightingScheme.toString().matches("Unis.*") || 
                    weightingScheme.toString().matches("TCombCost") || weightingScheme.toString().matches("Phi") || weightingScheme.toString().matches("J") || 
                    weightingScheme.toString().matches("Gini") || weightingScheme.toString().matches("Confidence") || weightingScheme.toString().matches("DriverKroeber") || 
                    weightingScheme.toString().matches("Laplace") || weightingScheme.toString().matches("PiaterskyShapiro") || weightingScheme.toString().matches("Certainty") || 
                    weightingScheme.toString().matches("AddedValue")){
                readStringDouble(in, "advWithWordFrequencies", wordWithAdvFrequencies, wordWithAdvSimpleFrequencies, 
                        minimumFeatureSimpleFrequency, null, filterStopWords, i++);
                
                readStringDouble(in, "advWordSimpleTuples", advWithWordFrequencies, adverbSimpleFrequencies, 
                        minimumWordSimpleFrequency, allAdverbs, false, i++);
            }else{
                readUntil(in, "advWordSimpleTuples");
            }

            
            if(minimumWordFeatureTupleSimpleFrequency!=null){

                readStringStringDouble(in, "advWordTuples", advWordSimpleTuples, null, null, null, allAdverbs, filterStopWords, i++);
            
            }else{
                
                readUntil(in, "advWordTuples");

            }
                
            readStringStringDouble(in, "wordAdvTuples", advWordTuples, adverbSimpleFrequencies, 
                    wordWithAdvSimpleFrequencies, advWordSimpleTuples, allAdverbs, filterStopWords, i++);
            
            if(weightingScheme==WeightingScheme.Plffi || weightingScheme==WeightingScheme.Qlflnw || 
                    weightingScheme.toString().matches("(TfIdf_(1|2|4|5|7|8|9))|(TfIcf_3)") || weightingScheme==WeightingScheme.Gref_1 || weightingScheme.toString().matches("(PmiX)?Lin_1..") || 
                    weightingScheme==WeightingScheme.Lin_2 || weightingScheme.toString().matches("Okapi_.") || weightingScheme==WeightingScheme.Ltu || 
                    weightingScheme==WeightingScheme.Atc || weightingScheme==WeightingScheme.PmiXTfIdf_1 || 
                    weightingScheme==WeightingScheme.PmiXOkapi_1 || weightingScheme==WeightingScheme.PmiXLtu || 
                    similarityMeasure==SimilarityMeasure.Pears || similarityMeasure==SimilarityMeasure.Spearm || 
                    similarityMeasure==SimilarityMeasure.CorrKiela || similarityMeasure==SimilarityMeasure.Covariance || 
                    similarityMeasure==SimilarityMeasure.Lin || similarityMeasure==SimilarityMeasure.Dfvmb || 
                    pearsModPattern.matcher(similarityMeasureString).matches() || pearsCombPattern.matcher(similarityMeasureString).matches() || 
                    pearsMbAdjCosModPattern.matcher(similarityMeasureString).matches() || pearsMbAdjCosPfModPattern.matcher(similarityMeasureString).matches() || 
                    pearsMbModPattern.matcher(similarityMeasureString).matches() || similarityMeasureString.matches("PenroseShape(Mod_.*)?") || 
                    dimensionalityReductionType==DimensionalityReductionType.SVD){
                readStringLong(in, "adverbKNSNCounts", wordAdvTuples, wordWithAdvSimpleFrequencies, filterStopWords, i++);
            }else{
                readUntil(in, "adverbKNSNCounts");
            }            
            
            
            if(smoothingType!=SmoothingType.NoSmooth){
            
                readKNSNCountsForStrings(in, "wordAdvTuplesKNSNCounts", adverbKNSNCounts, 
                        adverbSimpleFrequencies, minimumWordSimpleFrequency, allAdverbs, false, i++);
                
                readKNSNCountsForStrings(in, "allAdverbKNSNCounts", wordAdvTuplesKNSNCounts, 
                        wordWithAdvSimpleFrequencies, minimumFeatureSimpleFrequency, null, filterStopWords, i++);
                
                readAllKNSNCounts(in, "allCounts", allAdverbKNSNCounts, i++);
                
            }else{
                
                readUntil(in, "allCounts");

            }
                    

            readAllCounts(in, i++);
            

            in.close();



        } catch (Exception ex) {
            System.out.println("Error during reading the extractedInformationFile!");
            ex.printStackTrace();
            System.exit(1);
        }

    }
    
    
    
    
    /**
     * This method reads in the information to be stored in a HashMap&lt;String, HashMap&lt;Paraphrase, Double&gt;&gt;.
     * @param in the BufferedReader from which this function reads
     * @param readUntilThis the first line in the input that does not belong to the information to be stored in the current map
     * @param tuplesMap the map in which the read in information will be stored
     * @param frequency1SimpleMap the map in which the simpleFrequencies for the words are stored for mininum simple frequency check
     * @param frequency2SimpleMap the map in which the simpleFrequencies for the features are stored for mininum simple frequency check
     * @param tuplesSimpleMap the map in which the tuplesSimpleCounts are stored for mininum simple frequency check
     * @param allWordSet the set containing all the input words of the given POS
     * @param useStopWordFiltering determines whether to use stop word filtering
     * @param i the number of the previously called readXXX functions
     * @throws IOException 
     */
    public static void readStringParaphraseDouble(BufferedReader in, String readUntilThis, HashMap<String, HashMap<Paraphrase, Double>> tuplesMap, 
            HashMap<String, Double> frequency1SimpleMap, HashMap<Paraphrase, Double> frequency2SimpleMap, 
            HashMap<String, HashMap<Paraphrase, Double>> tuplesSimpleMap, HashSet<String> allWordSet, boolean useStopWordFiltering, int i) throws IOException{
        
        String line;
        
        int score;
        
        HashMap<Paraphrase, Double> table;
        
        readUntilThis="!"+readUntilThis;
        
        while(!(line=in.readLine()).equals(readUntilThis)){
            
            String[] parts = line.split("\u001c");
            
            if((allWordSet.contains(parts[0]) || (!readExtractedInformationJustForInputWords)) && 
                    (minimumWordSimpleFrequency==null || frequency1SimpleMap==null || frequency1SimpleMap.get(parts[0])>minimumWordSimpleFrequency)){
                
                table = new HashMap<Paraphrase, Double>();
            
                for(int j=1;j<parts.length;j++){

                    String[] paraphraseAndScore = parts[j].split("\u001d");
                    String[] paraphraseParts = paraphraseAndScore[0].split("\u001e");

                    score = Integer.parseInt(paraphraseAndScore[1]);
                    
                    Paraphrase p = new Paraphrase(paraphraseParts[0].equals("t"), paraphraseParts[1], paraphraseParts[2].equals("null")? null : paraphraseParts[2]);

                    if((!useStopWordFiltering || !stopWordsSet.contains(paraphraseParts[1])) && 
                            (minimumWordFeatureTupleSimpleFrequency==null || tuplesSimpleMap==null || 
                            tuplesSimpleMap.get(parts[0]).get(p)>minimumWordFeatureTupleSimpleFrequency) && 
                            (minimumFeatureSimpleFrequency==null || frequency2SimpleMap==null || frequency2SimpleMap.get(p)>minimumFeatureSimpleFrequency)){

                        table.put(p, (double) score);

                    }

                }

                tuplesMap.put(parts[0], table);
            
            }

        }
        
    }
    
    
    /**
     * This method reads in the information to be stored in a HashMap&lt;String, HashMap&lt;String, Double&gt;&gt;.
     * @param in the BufferedReader from which this function reads
     * @param readUntilThis the first line in the input that does not belong to the information to be stored in the current map
     * @param tuplesMap the map in which the read in information will be stored
     * @param frequency1SimpleMap the map in which the simpleFrequencies for the words are stored for mininum simple frequency check
     * @param frequency2SimpleMap the map in which the simpleFrequencies for the features are stored for mininum simple frequency check
     * @param tuplesSimpleMap the map in which the tuplesSimpleCounts are stored for mininum simple frequency check
     * @param allWordSet the set containing all the input words of the given POS
     * @param useStopWordFiltering determines whether to use stop word filtering
     * @param i the number of the previously called readXXX functions
     * @throws IOException 
     */
    public static void readStringStringDouble(BufferedReader in, String readUntilThis, HashMap<String, HashMap<String, Double>> tuplesMap, 
            HashMap<String, Double> frequency1SimpleMap, HashMap<String, Double> frequency2SimpleMap, 
            HashMap<String, HashMap<String, Double>> tuplesSimpleMap, HashSet<String> allWordSet, boolean useStopWordFiltering, int i) throws IOException{
        
        String line;
        
        int score;
        
        HashMap<String, Double> table;
        
        readUntilThis="!"+readUntilThis;
        
        while(!(line=in.readLine()).equals(readUntilThis)){
            
            String[] parts = line.split("\u001c");
            
            if((allWordSet.contains(parts[0]) || (!readExtractedInformationJustForInputWords)) && 
                    (minimumWordSimpleFrequency==null || frequency1SimpleMap==null || frequency1SimpleMap.get(parts[0])>minimumWordSimpleFrequency)){
                
                table = new HashMap<String, Double>();
            
                for(int j=1;j<parts.length;j++){

                    String[] featureAndScore = parts[j].split("\u001d");

                    score = Integer.parseInt(featureAndScore[1]);

                    if((!useStopWordFiltering || !stopWordsSet.contains(featureAndScore[0])) && 
                            (minimumWordFeatureTupleSimpleFrequency==null || tuplesSimpleMap==null || 
                            tuplesSimpleMap.get(parts[0]).get(featureAndScore[0])>minimumWordFeatureTupleSimpleFrequency) && 
                            (minimumFeatureSimpleFrequency==null || frequency2SimpleMap==null || frequency2SimpleMap.get(featureAndScore[0])>minimumFeatureSimpleFrequency)){

                        table.put(featureAndScore[0], (double) score);

                    }

                }

                tuplesMap.put(parts[0], table);
            
            }

        }
        
    }
    
    
    /**
     * This method reads in the information to be stored in a HashMap&lt;Paraphrase, Long&gt;.
     * @param in the BufferedReader from which this function reads
     * @param readUntilThis the first line in the input that does not belong to the information to be stored in the current map
     * @param tuplesMap the map in which the read in information will be stored
     * @param tuplesSimpleMap the map in which the tuplesSimpleCounts are stored for mininum simple frequency check
     * @param useStopWordFiltering determines whether to use stop word filtering
     * @param i the number of the previously called readXXX functions
     * @throws IOException 
     */
    public static void readParaphraseLong(BufferedReader in, String readUntilThis, HashMap<Paraphrase, Long> tuplesMap, 
            HashMap<Paraphrase, Double> tuplesSimpleMap, boolean useStopWordFiltering, int i) throws IOException{
        
        String line;
        
        readUntilThis="!"+readUntilThis;
        
        while(!(line=in.readLine()).equals(readUntilThis)){

            String[] parts = line.split("\u001d");
            String[] paraphraseParts = parts[0].split("\u001e");
            
            Paraphrase p = new Paraphrase(paraphraseParts[0].equals("t"), paraphraseParts[1], paraphraseParts[2].equals("null")? null : paraphraseParts[2]);
            
            if((!useStopWordFiltering || !stopWordsSet.contains(paraphraseParts[1])) && 
                    (minimumFeatureSimpleFrequency==null || tuplesSimpleMap==null || tuplesSimpleMap.get(p)>minimumFeatureSimpleFrequency)){

                tuplesMap.put(p, Long.parseLong(parts[1]));
                
            }

        }
        
    }
    
    
    
    /**
     * This method reads in the information to be stored in a HashMap&lt;String, Long&gt;.
     * @param in the BufferedReader from which this function reads
     * @param readUntilThis the first line in the input that does not belong to the information to be stored in the current map
     * @param tuplesMap the map in which the read in information will be stored
     * @param tuplesSimpleMap the map in which the tuplesSimpleCounts are stored for mininum simple frequency check
     * @param useStopWordFiltering determines whether to use stop word filtering
     * @param i the number of the previously called readXXX functions
     * @throws IOException 
     */
    public static void readStringLong(BufferedReader in, String readUntilThis, HashMap<String, Long> tuplesMap, 
            HashMap<String, Double> tuplesSimpleMap, boolean useStopWordFiltering, int i) throws IOException{
        
        String line;
        
        readUntilThis="!"+readUntilThis;
        
        while(!(line=in.readLine()).equals(readUntilThis)){

            String[] parts = line.split("\u001d");
            
            if((!useStopWordFiltering || !stopWordsSet.contains(parts[0])) && 
                    (minimumFeatureSimpleFrequency==null || tuplesSimpleMap==null || tuplesSimpleMap.get(parts[0])>minimumFeatureSimpleFrequency)){

                tuplesMap.put(parts[0], Long.parseLong(parts[1]));
                
            }

        }
        
    }
    
    
    /**
     * This method reads in the information to be stored in a HashMap&lt;Paraphrase, Double&gt;.
     * @param in the BufferedReader from which this function reads
     * @param readUntilThis the first line in the input that does not belong to the information to be stored in the current map
     * @param tuplesMap the map in which the read in information will be stored
     * @param tuplesSimpleMap the map in which the tuplesSimpleCounts are stored for mininum simple frequency check
     * @param minSimpleFreq the mininum simple frequency a word/feature should have to be considered
     * @param useStopWordFiltering determines whether to use stop word filtering
     * @param i the number of the previously called readXXX functions
     * @throws IOException 
     */
    public static void readParaphraseDouble(BufferedReader in, String readUntilThis, HashMap<Paraphrase, Double> tuplesMap, 
            HashMap<Paraphrase, Double> tuplesSimpleMap, Integer minSimpleFreq, boolean useStopWordFiltering, int i) throws IOException{
        
        String line;
        
        readUntilThis="!"+readUntilThis;
        
        while(!(line=in.readLine()).equals(readUntilThis)){

            String[] parts = line.split("\u001d");
            String[] paraphraseParts = parts[0].split("\u001e");
            
            Paraphrase p = new Paraphrase(paraphraseParts[0].equals("t"), paraphraseParts[1], paraphraseParts[2].equals("null")? null : paraphraseParts[2]);
            
            if((!useStopWordFiltering || !stopWordsSet.contains(paraphraseParts[1])) && 
                    (minSimpleFreq==null || tuplesSimpleMap==null || tuplesSimpleMap.get(p)>minSimpleFreq)){

                tuplesMap.put(p, Double.parseDouble(parts[1]));
                
            }

        }
        
    }
    
    
    /**
     * This method reads in the information to be stored in a HashMap&lt;String, Double&gt;.
     * @param in the BufferedReader from which this function reads
     * @param readUntilThis the first line in the input that does not belong to the information to be stored in the current map
     * @param tuplesMap the map in which the read in information will be stored
     * @param tuplesSimpleMap the map in which the tuplesSimpleCounts are stored for mininum simple frequency check
     * @param minSimpleFreq the mininum simple frequency a word/feature should have to be considered
     * @param allWordSet the set containing all the input words of the given POS
     * @param useStopWordFiltering determines whether to use stop word filtering
     * @param i the number of the previously called readXXX functions
     * @throws IOException 
     */
    public static void readStringDouble(BufferedReader in, String readUntilThis, HashMap<String, Double> tuplesMap, 
            HashMap<String, Double> tuplesSimpleMap, Integer minSimpleFreq, HashSet<String> allWordSet, boolean useStopWordFiltering, int i) throws IOException{
        
        String line;
        
        readUntilThis="!"+readUntilThis;
        
        while(!(line=in.readLine()).equals(readUntilThis)){

            String[] parts = line.split("\u001d");
            
            if(((allWordSet!=null && allWordSet.contains(parts[0])) || ((allWordSet==null || !readExtractedInformationJustForInputWords) && 
                    (!useStopWordFiltering || !stopWordsSet.contains(parts[0])))) && 
                    (minSimpleFreq==null || tuplesSimpleMap==null || tuplesSimpleMap.get(parts[0])>minSimpleFreq)){

                tuplesMap.put(parts[0], Double.parseDouble(parts[1]));
                
            }

        }
        
    }
    
    
    /**
     * This method reads the input until the line containing the @param readUntilThis string is reached.
     * @param in the BufferedReader from which this function reads
     * @param readUntilThis the first line in the input that does not belong to the information to be stored in the current map
     * @throws IOException 
     */
    public static void readUntil(BufferedReader in, String readUntilThis) throws IOException{
        
        readUntilThis="!"+readUntilThis;
        
        while(!in.readLine().equals(readUntilThis)){
            
        }
        
    }
    
    
    
    /**
     * This method reads in the the KNS N counts for words and features of type Paraphrase.
     * @param in the BufferedReader from which this function reads
     * @param readUntilThis the first line in the input that does not belong to the information to be stored in the current map
     * @param tuplesMap the map in which the read in information will be stored
     * @param tuplesSimpleMap the map in which the tuplesSimpleCounts are stored for mininum simple frequency check
     * @param minSimpleFreq the mininum simple frequency a word/feature should have to be considered
     * @param useStopWordFiltering determines whether to use stop word filtering
     * @param i the number of the previously called readXXX functions
     * @throws IOException 
     */
    public static void readKNSNCountsForParaphrases(BufferedReader in, String readUntilThis, HashMap<Paraphrase, Long[]> tuplesMap, 
            HashMap<Paraphrase, Double> tuplesSimpleMap, Integer minSimpleFreq, boolean useStopWordFiltering, int i) throws IOException{
        
        String line;
        
        Long[] KNSNCounts;
        
        readUntilThis="!"+readUntilThis;
        
        while(!(line=in.readLine()).equals(readUntilThis)){

            String[] parts = line.split("\u001d");
            String[] paraphraseParts = parts[0].split("\u001e");
            
            KNSNCounts = new Long[4];
            
            for(int j=0;j<4;j++){
                KNSNCounts[j] = Long.parseLong(parts[j+1]);
            }
            
            Paraphrase p = new Paraphrase(paraphraseParts[0].equals("t"), paraphraseParts[1], paraphraseParts[2].equals("null")? null : paraphraseParts[2]);
            
            if((!useStopWordFiltering || !stopWordsSet.contains(paraphraseParts[1])) && 
                    (minSimpleFreq==null || tuplesSimpleMap==null || tuplesSimpleMap.get(p)>minSimpleFreq)){

                tuplesMap.put(p, KNSNCounts);
                
            }

        }
        
    }
    
    
    
    /**
     * This method reads in the the KNS N counts for words and features of type String.
     * @param in the BufferedReader from which this function reads
     * @param readUntilThis the first line in the input that does not belong to the information to be stored in the current map
     * @param tuplesMap the map in which the read in information will be stored
     * @param tuplesSimpleMap the map in which the tuplesSimpleCounts are stored for mininum simple frequency check
     * @param minSimpleFreq the mininum simple frequency a word/feature should have to be considered
     * @param allWordSet the set containing all the input words of the given POS
     * @param useStopWordFiltering determines whether to use stop word filtering
     * @param i the number of the previously called readXXX functions
     * @throws IOException 
     */
    public static void readKNSNCountsForStrings(BufferedReader in, String readUntilThis, HashMap<String, Long[]> tuplesMap, 
            HashMap<String, Double> tuplesSimpleMap, Integer minSimpleFreq, HashSet<String> allWordSet, boolean useStopWordFiltering, int i) throws IOException{
        
        String line;
        
        Long[] KNSNCounts;
        
        readUntilThis="!"+readUntilThis;
        
        while(!(line=in.readLine()).equals(readUntilThis)){

            String[] parts = line.split("\u001d");
            
            KNSNCounts = new Long[4];
            
            for(int j=0;j<4;j++){
                KNSNCounts[j] = Long.parseLong(parts[j+1]);
            }
            
            if(((allWordSet!=null && allWordSet.contains(parts[0])) || ((allWordSet==null || !readExtractedInformationJustForInputWords) && 
                    (!useStopWordFiltering || !stopWordsSet.contains(parts[0])))) && 
                    (minSimpleFreq==null || tuplesSimpleMap==null || tuplesSimpleMap.get(parts[0])>minSimpleFreq)){

                tuplesMap.put(parts[0], KNSNCounts);
                
            }

        }
        
    }
    
    
    
    
    /**
     * This method reads in the the KNS N counts for words and features of type String.
     * @param in the BufferedReader from which this function reads
     * @param readUntilThis the first line in the input that does not belong to the information to be stored in the current map
     * @param allKNSNCounts the array in which the read in information will be stored
     * @param i the number of the previously called readXXX functions
     * @throws IOException 
     */
    public static void readAllKNSNCounts(BufferedReader in, String readUntilThis, Long[] allKNSNCounts, int i) throws IOException{
        
        String line;
        
        readUntilThis="!"+readUntilThis;
        
        int j=0;
        
        while(!(line=in.readLine()).equals(readUntilThis)){

            allKNSNCounts[j++]=Long.parseLong(line);

        }
        
        if(j!=4){
            System.out.println("Wrong number of allKNSNCounts!");
            System.exit(1);
        }
        
    }
    
    
    
    /**
     * This method reads in the information to be stored in the allXXXCount variables.
     * @param in the BufferedReader from which this function reads
     * @param i the number of the previously called readXXX functions
     * @throws IOException 
     */
    public static void readAllCounts(BufferedReader in, int i) throws IOException{
        
        allNounNcmodCount= Double.parseDouble(in.readLine());
        allObjectCount= Double.parseDouble(in.readLine());
        allSubjectCount= Double.parseDouble(in.readLine());
        allVerbDobjCount= Double.parseDouble(in.readLine());
        allVerbNcsubjCount= Double.parseDouble(in.readLine());
        allVerbPrepCount= Double.parseDouble(in.readLine());
        allVerbObj2Count= Double.parseDouble(in.readLine());
        allAdjNounCount= Double.parseDouble(in.readLine());
        allAdvWordCount= Double.parseDouble(in.readLine());
        allWordCount= Double.parseDouble(in.readLine());

        allNounTypeCount=Long.parseLong(in.readLine());
        allVerbTypeCount=Long.parseLong(in.readLine());
        allAdjectiveTypeCount=Long.parseLong(in.readLine());
        allAdverbTypeCount=Long.parseLong(in.readLine());
        
        allNounFeatureCount = Long.parseLong(in.readLine());
        allVerbFeatureCount = Long.parseLong(in.readLine());
        allAdjFeatureCount = Long.parseLong(in.readLine());
        allAdvFeatureCount = Long.parseLong(in.readLine());
        
    }
    
    
        
        
    /**
     * This function reads in the information from the Google News word vectors file. The toLowerCase() function is not called on the input lines, 
     * as the Google News dataset contains word vectors for each word written in completely lower case and also with a capital first letter. As all 
     * test words are converted to lower case, only the information from the lower case variants of a word is used this way. If the toLowerCase() funtion 
     * would be called, then randomly either the information from the lower case version or the other variant would be used, which would not make too 
     * much sense.
     */
    public static void readWordVectorsFile(){
        
        try {
            int partsLength;
            int currentVectorLength;
            
            BufferedReader in = new BufferedReader(new FileReader(inputLocation));
            String line;
            
            if(wordVectors==WordVectors.GoogleNews || wordVectors==WordVectors.Salle2016 || 
                    wordVectors==WordVectors.Speer2017 || (wordVectors==WordVectors.Yin2016 && wordVectorsFileName.equals("O2M_overlap.txt"))){
            
                in.readLine();
                
            }
            
            while((line=in.readLine())!=null){

                String[] parts;
                
                if(wordVectors==WordVectors.GoogleNews || wordVectors==WordVectors.Levy2015 || wordVectors==WordVectors.Pennington2014 || 
                        wordVectors==WordVectors.Salle2016 || wordVectors==WordVectors.Speer2017){
                    parts = line.split(" ");
                }else if(wordVectors==WordVectors.Baroni2014){
                    parts = line.split("\t");
                }else{
                    parts = line.replaceFirst("\t", " ").split(" ");
                }
                
                if(wordVectors==WordVectors.GoogleNews){
                    partsLength=wordVectorsDimension+2;
                }else{
                    partsLength=wordVectorsDimension+1;
                }
                
                if(wordVectors==WordVectors.Speer2017){
                    currentVectorLength=Math.min(wordVectorsDimension+1, parts.length);
                }else{
                    currentVectorLength=wordVectorsDimension+1;
                }

                if(parts.length==partsLength || wordVectors==WordVectors.Speer2017){
                    
                    if(parts.length!=partsLength){
                        System.err.println("The format of the word vectors file is incorrect:\t" + parts.length + "\t" + partsLength + "\n" + line);
                    }

                    if(!readExtractedInformationJustForInputWords || allNouns.contains(parts[0])){
                        
                        HashMap<String, Double> table = nounNcmodTuples.get(parts[0]);
                        if(table==null){
                            table = new HashMap<String, Double>();
                        }else{
                            System.out.println("There are two identical input words in the word vectors file!");
                            System.exit(1);
                        }

                        for(int i=1;i<currentVectorLength;i++){

                            table.put(Integer.toString(i), Double.parseDouble(parts[i]));
                            
                        }
                        
                        nounNcmodTuples.put(parts[0], table);

                    }

                }else{
                    System.out.println("The format of the word vectors file is incorrect:\t" + parts.length + "\t" + partsLength + "\n" + line);
                    System.exit(1);
                }
                
            }
            
            
            for(int i=1;i<301;i++){

                ncmodNounTuples.put(Integer.toString(i), null);

            }
            
            allNounFeatureCount = 300;
            
            
            in.close();

        } catch (Exception ex) {
            System.out.println("Error during reading the wordVectorsFile!");
            ex.printStackTrace();
            System.exit(1);
        }

    }
    
    
}
