package dsm;

import static dsm.parameters.DimRed.*;
import static dsm.parameters.FeatTransf.*;
import static dsm.parameters.MiscParam.*;
import static dsm.parameters.Smooth.*;
import static dsm.parameters.VecNorm.normalizeVectors;
import static dsm.parameters.VecSim.*;
import static dsm.parameters.Weight.*;
import static dsm.util.Aggregation.*;
import static dsm.util.EvalDatasetReader.*;
import static dsm.util.Evaluation.*;
import static dsm.util.ExtractedInfoReader.*;
import static dsm.util.Initializer.initialize;
import static dsm.util.OtherInputFilesReader.*;


/**
 * This class implements numerous distributional semantic models based on information extracted from corpora, and evaluates them on standard datasets.
 * <br><br><br>
 * Usage:
 * <br><br>
 * java -cp DSM.jar dsm.DSM EvaluationDataset InputDataType INPUTDATA METHOD
 * <br><br>
 * INPUTDATA = ("Corpus" bagOfWords WordType corpusStringSuffix) | ("Vectors" WordVectors wordVectorsDimension)
 * <br><br>
 * METHOD = "Lin" | ("Num" VectorNormalization SimilarityMeasure WeightingScheme minimumWordSimpleFrequency minimumFeatureSimpleFrequency minimumWordFeatureTupleSimpleFrequency filterStopWords MINIMUMWEIGHT SMOOTH FEATTRANSF DIMRED
 * <br><br>
 * MINIMUMWEIGHT = "null" | (Limit minimumWordFeatureTupleWeightParameter) | (Zero minimumWordFeatureTupleWeightParameter)
 * <br><br>
 * SMOOTH = "NoSmooth" | (("FreqKNS" | "WeightKNS") KNSParameter) | (("FreqMKNS" | "FreqMDKNSPOMD") KNSParameter1 KNSParameter2 KNSParameter3)
 * <br><br>
 * FEATTRANSF = "NoTransf" | (FeatureTransformationType FeatureTransformationFunction)
 * <br><br>
 * DIMRED = "NoDimRed" | (DimensionalityReductionType dimensionalityReductionParameter)
 * 
 * @author Andras
 */
public class DSM {

    

    
    /**
     * This function tests my methods on the input word pair similarity or synonym question datasets. First it initializes the parameters from the command-line arguments, and also initializes some other
     * parts. Then it reads in the test dataset and the information extracted from the used corpus. After this, computes the necessary weights for the (word, feature) pairs. Then it 
     * computes the semantic similarity of all the word pairs in the selected test dataset. Finally, it evaluates the performance of the method and prints out the results.
     * @param args the command-line arguments for the input parameters
     */
    public static void main(String[] args) {
                
        initialize(args);
        
        if((featureTransformationType==FeatureTransformationType.FreqAftSmooth && 
                smoothingType!=SmoothingType.FreqKNS && smoothingType!=SmoothingType.FreqMKNS && smoothingType!=SmoothingType.FreqMDKNSPOMD) ||
                (featureTransformationType==FeatureTransformationType.WeightAftSmooth && 
                smoothingType!=SmoothingType.WeightKNS)){
            System.out.println("No such smoothing (freq/weight) after which the feature transformation should take place");
            System.exit(1);
        }

        readEvalDataset();
        
        if(dimensionalityReductionType==DimensionalityReductionType.SVD){
            
            readAllInputWords();
            
        }
        
        if(filterStopWords){
            
            readStopWordsList();
            
        }

        if(inputDataType==InputDataType.Corpus){

            if(method==Method.Num && similarityMeasure==SimilarityMeasure.Lin && dimensionalityReductionType==DimensionalityReductionType.SVD){
                System.out.println("In case of the LIN similarity measure the SVD dimensionlality reduction cannot be used, because the feature information values cannot be calculated");
                System.exit(1);
            }
            
                readExtractedInformationFile();
                
            }else{
                
            if(method!=Method.Num || similarityMeasure==SimilarityMeasure.Lin || weightingScheme!=WeightingScheme.Freq){
                System.out.println("Wrong method, similarityMeasure or weightingScheme (in case of WORDVECTORS only NUM FREQ weightingScheme is available, "
                        + "and the LIN1 similarity measure cannot be used): " + method + " " + similarityMeasure + " " + weightingScheme);
                System.exit(1); 
            }else if(smoothingType==SmoothingType.FreqKNS || smoothingType==SmoothingType.FreqMKNS || smoothingType==SmoothingType.FreqMDKNSPOMD || 
                    featureTransformationType==FeatureTransformationType.FreqAftSmooth || featureTransformationType==FeatureTransformationType.Freq || 
                    minimumWordFeatureTupleSimpleFrequency!=null){
                System.out.println("Wrong smoothingType, featureTransformationType or minimumWordFeaturePairSimpleFrequency (in case of WORDVECTORS, the frequencies cannot be "
                        + "smoothed or transformed, and the minimumWordFeaturePairSimpleFrequency option cannot be used (therefore it should be set to 0)): " + 
                        smoothingType + " " + featureTransformationType + " " + minimumWordFeatureTupleSimpleFrequency);
                System.exit(1);
            }

            readWordVectorsFile();

        }

        if(method==Method.Num){

            if(featureTransformationType==FeatureTransformationType.Freq){
                applyFeatureTransformation(featureTransformationType, featureTransformationFunction);
            }

            if(smoothingType==SmoothingType.FreqKNS || smoothingType==SmoothingType.FreqMKNS || smoothingType==SmoothingType.FreqMDKNSPOMD){
                applySmoothing();
            }

            if(featureTransformationType==FeatureTransformationType.FreqAftSmooth){
                applyFeatureTransformation(featureTransformationType, featureTransformationFunction);
            }

        }


        if(method==Method.Lin || (method==Method.Num && (similarityMeasure==SimilarityMeasure.Lin || weightingScheme==WeightingScheme.Plffi))){
            computeInformationForFeatures();
        }
        
        
        if(method==Method.Num){

            if(weightingScheme.toString().matches("(PmiX)?Rapp_.")){
                computeEntropyForFeatures();
            }

            if(weightingScheme==WeightingScheme.Gref_2){
                computeGrefFeatureWeightForFeatures();
            }

            if(weightingScheme.toString().matches("Okapi_.") || weightingScheme==WeightingScheme.Ltu || 
                    weightingScheme==WeightingScheme.PmiXOkapi_1 || weightingScheme==WeightingScheme.PmiXLtu){
                aggregateVectorElements(AggregationMethod.Sum);
                computeAvgWordVectorElementSums();
            }

            if(weightingScheme==WeightingScheme.Atc){
                computeMaxWordFeaturePairFrequencies();
                computeAtcFeatureWeightForFeatures();
            }

            if(weightingScheme.toString().matches(".*((PmiAl)|(_.*Tc1)).*") || weightingScheme==WeightingScheme.Cca){
                computeAllRelationCountOfGivenTypeAlphas();
            }

            if(weightingScheme==WeightingScheme.TfIdf_9){
                aggregateVectorElements(AggregationMethod.Sum);
                computeAllFeatureDocFreqCounts();
            }


            applyWeightingScheme();

            if(featureTransformationType==FeatureTransformationType.WeightBefNorm){
                applyFeatureTransformation(featureTransformationType, featureTransformationFunction);
            }

            if(smoothingType==SmoothingType.WeightKNS){
                applySmoothing();
            }

            if(featureTransformationType==FeatureTransformationType.WeightAftSmooth){
                applyFeatureTransformation(featureTransformationType, featureTransformationFunction);
            }

            if(dimensionalityReductionType==DimensionalityReductionType.TopNFeat || 
                    dimensionalityReductionType==DimensionalityReductionType.IslamInkpen){

                dimRedTopFeatures(dimensionalityReductionType, dimensionalityReductionParameter, true);

            }else if(dimensionalityReductionType==DimensionalityReductionType.SVD){
                
                normalizeVectors(true);
                
                dimRedSVD(dimensionalityReductionParameter);

            }

            normalizeVectors();


            if(featureTransformationType==FeatureTransformationType.WeightAftNorm){

                applyFeatureTransformation(featureTransformationType, featureTransformationFunction);

            }
            
            if(similarityMeasure==SimilarityMeasure.ApSyn){

                dimRedTopFeatures(DimensionalityReductionType.TopNFeat, apSynN, false);

            }else if(similarityMeasureString.matches("(NormMod)?SocPmiMod")){
                
                double dimRedParam = similarityMeasure==SimilarityMeasure.SocPmiMod ? SocPmiMu : normModSocPmiDelta;
                
                dimRedTopFeatures(DimensionalityReductionType.IslamInkpen, dimRedParam, true);
                
            }

            if(similarityMeasure==SimilarityMeasure.ApSyn || similarityMeasure==SimilarityMeasure.ApSynP || 
                    similarityMeasure==SimilarityMeasure.Wo || similarityMeasure==SimilarityMeasure.Rbo){

                applyFeatureTransformation(null, FeatureTransformationFunction.Rank);

            }
            
        }
        

        aggregateVectors();
        
        evaluate();
        
        if(evaluationDataset==EvaluationDataset.Toefl || evaluationDataset==EvaluationDataset.ToeflPart1 || evaluationDataset==EvaluationDataset.ToeflPart2 || 
                evaluationDataset==EvaluationDataset.ReadersDigestClean || evaluationDataset==EvaluationDataset.Esl){
            printOutResultsSynonymQuestions();
        }else{
            printOutResultsWordPairSimilarities();
        }

    }
    
    


}
