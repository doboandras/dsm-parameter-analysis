package dsm.util;

import com.shef.ac.uk.util.Util;
import static dsm.util.ExtractedInfoReader.wordVectorsDimension;
import dsm.parameters.DimRed.DimensionalityReductionType;
import static dsm.parameters.DimRed.dimensionalityReductionParameter;
import static dsm.parameters.DimRed.dimensionalityReductionType;
import dsm.parameters.FeatTransf.FeatureTransformationFunction;
import dsm.parameters.FeatTransf.FeatureTransformationType;
import static dsm.parameters.FeatTransf.featureTransformationFunction;
import static dsm.parameters.FeatTransf.featureTransformationType;
import static dsm.parameters.MiscParam.*;
import static dsm.parameters.Smooth.KNSParameters;
import static dsm.parameters.Smooth.MKNSAlsoJustForInputWords;
import dsm.parameters.Smooth.SmoothingType;
import static dsm.parameters.Smooth.smoothingType;
import dsm.parameters.VecNorm.VectorNormalization;
import static dsm.parameters.VecNorm.vectorNormalization;
import dsm.parameters.VecSim.SimilarityMeasure;
import static dsm.parameters.VecSim.similarityMeasure;
import static dsm.parameters.VecSim.similarityMeasureString;
import dsm.parameters.Weight.WeightingScheme;
import static dsm.parameters.Weight.weightingScheme;
import dsm.util.EvalDatasetReader.EvaluationDataset;
import static dsm.util.EvalDatasetReader.evaluationDataset;
import dsm.util.ExtractedInfoReader.Corpus;
import dsm.util.ExtractedInfoReader.InputDataType;
import dsm.util.ExtractedInfoReader.WordVectors;
import static dsm.util.ExtractedInfoReader.bagOfWords;
import static dsm.util.ExtractedInfoReader.corpus;
import static dsm.util.ExtractedInfoReader.inputDataType;
import static dsm.util.ExtractedInfoReader.wordVectors;
import static dsm.util.ExtractedInfoReader.wordVectorsFileName;
import static dsm.util.MiscUtil.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import net.didion.jwnl.JWNL;
import net.didion.jwnl.dictionary.Dictionary;

/**
 * The class responsible for the initialization of the DSM based on the command line arguments.
 * 
 * @author Dobó
 */
public class Initializer {

    
    /**
     * This is the method that initializes the parameters from the command-line arguments, and also initializes some other parts. If there are no command line arguments, then 
     * the default value of the parameters is used, and the strings representing the value of the parameters (used in the name of the output file) are created from the default values.
     * @param args the command-line arguments for the input parameters
     */
    public static void initialize(String args[]){
        
        try {
            
            String evaluationDatasetString=null;
            String inputDataTypeString=null;
            String wordVectorsString=null;
            String corpusString=null;
            String normalizationString=null;
            String weightingSchemeString=null;
            String minimumWordSimpleFrequencyString=null;
            String minimumFeatureSimpleFrequencyString=null;
            String minimumWordFeaturePairSimpleFrequencyString=null;
            String filterStopWordsString=null;
            String minimumWeightTypeString=null;
            String minimumWeightParameterString=null;
            String smoothingTypeString=null;
            String KNSParametersString=null;
            String featureTransformationTypeString=null;
            String featureTransformationFunctionString=null;
            String dimensionalityReductionTypeString=null;
            String dimensionalityReductionParameterString=null;
            String corpusStringSuffix=null;

            if(args.length>0){

                int index=0;                
                               
                evaluationDatasetString=args[index++];
                
                evaluationDataset=EvaluationDataset.valueOf(evaluationDatasetString);
                
                inputDataTypeString = args[index++];
                
                inputDataType=InputDataType.valueOf(inputDataTypeString);
                
                if(inputDataType == InputDataType.Corpus){
                    
                    corpusString = args[index++];
                    
                    corpus=Corpus.valueOf(corpusString);

                    bagOfWords=Boolean.parseBoolean(args[index++]);

                    corpusStringSuffix = args[index++];
                    
                }else if(inputDataType == InputDataType.Vectors){
                    
                    wordVectorsString = args[index++];
                    
                    wordVectors=WordVectors.valueOf(wordVectorsString);
                    
                    wordVectorsFileName = args[index++];
                    
                    wordVectorsDimension = Integer.parseInt(args[index++]);
                    
                }else{
                    System.out.println("Wrong inputDataTypeString: " + inputDataTypeString);
                    System.exit(1);
                }
                
                    
                normalizationString=args[index++];

                vectorNormalization=VectorNormalization.valueOf(normalizationString);

                similarityMeasureString=args[index++];

                similarityMeasure=SimilarityMeasure.valueOf(similarityMeasureString);

                weightingSchemeString=args[index++];

                weightingScheme=WeightingScheme.valueOf(weightingSchemeString);

                minimumWordSimpleFrequencyString=args[index++];

                if(!minimumWordSimpleFrequencyString.equals("null")){
                    minimumWordSimpleFrequency=Integer.parseInt(minimumWordSimpleFrequencyString);
                }else{
                    minimumWordSimpleFrequency=null;
                }

                minimumFeatureSimpleFrequencyString=args[index++];

                if(!minimumFeatureSimpleFrequencyString.equals("null")){
                    minimumFeatureSimpleFrequency=Integer.parseInt(minimumFeatureSimpleFrequencyString);
                }else{
                    minimumFeatureSimpleFrequency=null;
                }

                minimumWordFeaturePairSimpleFrequencyString=args[index++];

                if(!minimumWordFeaturePairSimpleFrequencyString.equals("null")){
                    minimumWordFeatureTupleSimpleFrequency=Integer.parseInt(minimumWordFeaturePairSimpleFrequencyString);
                }else{
                    minimumWordFeatureTupleSimpleFrequency=null;
                }

                filterStopWordsString=args[index++];

                filterStopWords=Boolean.parseBoolean(filterStopWordsString);

                minimumWeightTypeString=args[index++];

                if(!minimumWeightTypeString.equals("null")){

                    if(minimumWeightTypeString.equals("Limit")){
                        minimumWordFeatureTupleWeightType=MinimumWordFeatureTupleWeightType.Limit;
                    }else if(minimumWeightTypeString.equals("Zero")){
                        minimumWordFeatureTupleWeightType=MinimumWordFeatureTupleWeightType.Zero;
                    }else{
                        System.out.println("Wrong minimumWeightTypeString: " + minimumWeightTypeString);
                        System.exit(1);
                    }

                    minimumWeightParameterString=args[index++];
                    minimumWordFeatureTupleWeightParameter=Double.parseDouble(minimumWeightParameterString);

                }else{
                    minimumWordFeatureTupleWeightType=MinimumWordFeatureTupleWeightType.NoLimit;
                    minimumWordFeatureTupleWeightParameter=null;
                }

                smoothingTypeString=args[index++];

                smoothingType=SmoothingType.valueOf(smoothingTypeString);

                if(smoothingType==SmoothingType.FreqKNS || smoothingType==SmoothingType.WeightKNS){

                    KNSParametersString=args[index++];

                    KNSParameters[0]=Double.parseDouble(KNSParametersString);

                }else if(smoothingType==SmoothingType.FreqMKNS || smoothingType==SmoothingType.FreqMDKNSPOMD){

                    for(int i=1;i<4;i++){

                        if(i==1){
                            KNSParametersString=args[index];
                        }else{
                            KNSParametersString+=" " + args[index];
                        }

                        KNSParameters[i]=Double.parseDouble(args[index++]);

                    }

                }

                featureTransformationTypeString=args[index++];

                featureTransformationType=FeatureTransformationType.valueOf(featureTransformationTypeString);

                if(featureTransformationType!=FeatureTransformationType.NoTransf){

                    featureTransformationFunctionString=args[index++];

                    featureTransformationFunction=FeatureTransformationFunction.valueOf(featureTransformationFunctionString);

                }

                dimensionalityReductionTypeString=args[index++];

                dimensionalityReductionType=DimensionalityReductionType.valueOf(dimensionalityReductionTypeString);

                if(dimensionalityReductionType!=DimensionalityReductionType.NoDimRed){

                    dimensionalityReductionParameterString=args[index++];

                    dimensionalityReductionParameter=Double.parseDouble(dimensionalityReductionParameterString);

                }
                

            }else{
                
                
                evaluationDatasetString=evaluationDataset.toString();
                
                inputDataTypeString=inputDataType.toString();
                       
                if(inputDataType==InputDataType.Corpus){
                    
                    corpusString=corpus.toString();
                    
                }else{
                    
                    wordVectorsString=wordVectors.toString();
                    
                }
                
                
                normalizationString=vectorNormalization.toString();

                similarityMeasureString=similarityMeasure.toString();

                weightingSchemeString=weightingScheme.toString();

                minimumWordSimpleFrequencyString=String.valueOf(minimumWordSimpleFrequency);

                minimumFeatureSimpleFrequencyString=String.valueOf(minimumFeatureSimpleFrequency);

                minimumWordFeaturePairSimpleFrequencyString=String.valueOf(minimumWordFeatureTupleSimpleFrequency);

                filterStopWordsString=String.valueOf(filterStopWords);

                minimumWeightTypeString = minimumWordFeatureTupleWeightType==MinimumWordFeatureTupleWeightType.NoLimit ? "null" : minimumWordFeatureTupleWeightType.toString();

                minimumWeightParameterString=String.valueOf(minimumWordFeatureTupleWeightParameter);

                smoothingTypeString=String.valueOf(smoothingType);

                if(smoothingType==SmoothingType.FreqKNS || smoothingType==SmoothingType.WeightKNS){

                    KNSParametersString=KNSParameters[0].toString();

                }else if(smoothingType==SmoothingType.FreqMKNS || smoothingType==SmoothingType.FreqMDKNSPOMD){

                    KNSParametersString=KNSParameters[1].toString() + " " + KNSParameters[2].toString() + " " + KNSParameters[3].toString();

                }else{
                    KNSParametersString=null;
                }

                featureTransformationTypeString=String.valueOf(featureTransformationType);

                if(featureTransformationType!=FeatureTransformationType.NoTransf){
                    featureTransformationFunctionString=String.valueOf(featureTransformationFunction);
                }else{
                    featureTransformationFunctionString=null;
                }

                dimensionalityReductionTypeString=String.valueOf(dimensionalityReductionType);

                if(dimensionalityReductionType!=DimensionalityReductionType.NoDimRed){
                    dimensionalityReductionParameterString=String.valueOf(dimensionalityReductionParameter);
                }else{
                    dimensionalityReductionParameterString=null;
                }
                
            }
            
            
            readExtractedInformationJustForInputWords = !(weightingScheme==WeightingScheme.Gref_2 || weightingScheme.toString().matches("(PmiX)?Rapp_.") || 
                    weightingScheme.toString().matches("Okapi_.") || weightingScheme==WeightingScheme.Ltu || 
                    weightingScheme==WeightingScheme.PmiXOkapi_1 || weightingScheme==WeightingScheme.PmiXLtu || weightingScheme==WeightingScheme.Atc || 
                    (smoothingType==SmoothingType.FreqMKNS && !MKNSAlsoJustForInputWords) || 
                    featureTransformationType==FeatureTransformationType.Freq || featureTransformationType==FeatureTransformationType.FreqAftSmooth);
                
            
            String bagOfWordsString=null;
            

            if(inputDataType==InputDataType.Corpus){
                
                if(bagOfWords){
                    bagOfWordsString="BagOfWords";
                }else{
                    bagOfWordsString="";
                }

                if(corpusStringSuffix==null || corpusStringSuffix.equals("null")){
                    corpusStringSuffix="";
                }
                
                if(corpusStringSuffix.contains("Levy2015Counts") || corpusStringSuffix.contains("Salle2016Counts")){
                    
                    inputLocation="ExtractedInformation/extractedInformationFrom"+corpusString+corpusStringSuffix+".txt";
                    
                }else{
                
                    inputLocation="ExtractedInformation/extractedInformationFrom"+corpusString+bagOfWordsString+corpusStringSuffix+
                            (readExtractedInformationJustForInputWords ? "" : "ForAllWords")
                            +".txt";
                
                }
                
            }else if(inputDataType==InputDataType.Vectors){
                
                inputLocation="WordVectors/" + wordVectorsFileName;
                
            }else{
                
                System.out.println("Wrong inputDataType: " + inputDataType);
                System.exit(1);
                
            }
            
            String completeInputDataString;
            if(inputDataType==InputDataType.Corpus){
                if(corpusStringSuffix.contains("Levy2015Counts") || corpusStringSuffix.contains("Salle2016Counts")){
                    completeInputDataString = corpusString + corpusStringSuffix;
                }else{
                    completeInputDataString = corpusString + bagOfWordsString + corpusStringSuffix;
                }
            }else if(wordVectors==WordVectors.GoogleNews){
                completeInputDataString = wordVectorsString;
            }else{
                completeInputDataString = wordVectorsString+wordVectorsFileName;
            }
            
            String completeSmoothingString = (smoothingType==SmoothingType.NoSmooth ? 
                    smoothingTypeString : smoothingTypeString + " " + KNSParametersString);
            
            String completeFeatureTransformationString = (featureTransformationType==FeatureTransformationType.NoTransf ? 
                    featureTransformationTypeString : featureTransformationTypeString + " " + featureTransformationFunctionString);
            
            String completeDimensionalityReductionString = (dimensionalityReductionType==DimensionalityReductionType.NoDimRed ? 
                    dimensionalityReductionTypeString : dimensionalityReductionTypeString + " " + dimensionalityReductionParameterString);
            
            String completeMinimumWeightString = (minimumWordFeatureTupleWeightType==MinimumWordFeatureTupleWeightType.NoLimit ? 
                    "null" : minimumWeightTypeString + " " + minimumWeightParameterString);
                        
            outputFileName="DSM " + evaluationDatasetString + " " + completeInputDataString + " " + 
                    normalizationString + " " + similarityMeasureString + " " + weightingSchemeString + " " + 
                    minimumWordSimpleFrequencyString + " " + minimumFeatureSimpleFrequencyString + " " + minimumWordFeaturePairSimpleFrequencyString + " " + 
                    filterStopWordsString + " " + completeMinimumWeightString + " " + completeSmoothingString + " " + 
                    completeFeatureTransformationString + " " + completeDimensionalityReductionString + ".txt";
            
            if(createOutputFile) out = new PrintWriter(new FileWriter(outputFileName));
            
            if(inputDataType==InputDataType.Corpus && corpus==Corpus.EsWiki){
            
                nounDic = Util.loadDictionary("AhmetAkerDictEs/nounDic.txt");
                adjDic = Util.loadDictionary("AhmetAkerDictEs/adjDic.txt");
                advDic = Util.loadDictionary("AhmetAkerDictEs/advDic.txt");
                verbDic = Util.loadDictionary("AhmetAkerDictEs/verbDic.txt");
                
            }else if(!(inputDataType==InputDataType.Corpus && corpus==Corpus.HuWiki)){
                
                JWNL.initialize(new FileInputStream(new File("input/file_properties.xml")));
                dict=Dictionary.getInstance();
                
            }
            
            System.out.print(evaluationDatasetString + " " + completeInputDataString + " " + normalizationString + " " + similarityMeasureString + " " + 
                    weightingSchemeString + " " + minimumWordSimpleFrequencyString + " " + minimumFeatureSimpleFrequencyString + " " + 
                    minimumWordFeaturePairSimpleFrequencyString + " " + filterStopWordsString + " " + completeMinimumWeightString + " " + 
                    completeSmoothingString + " " + completeFeatureTransformationString + " " + completeDimensionalityReductionString + "\t");

            
        } catch (Exception ex) {
            System.out.println("Error during the initialization!");
            ex.printStackTrace();
            System.exit(1);
        }
        
    }
    
}
