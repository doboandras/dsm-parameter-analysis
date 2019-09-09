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
 * 
 * <br><br><br>
 * 
 * Usage:
 * 
 * <br><br>
 * 
 * java -cp DSM.jar dsm.DSM EvaluationDataset InputData VectorNormalization SimilarityMeasure WeightingScheme 
 * minimumWordSimpleFrequency minimumFeatureSimpleFrequency minimumWordFeatureTupleSimpleFrequency 
 * filterStopWords MinimumWeight Smooth FeatTransf DimRed
 * 
 * <br><br>
 * 
 * String EvaluationDataset: the test dataset, on which the DSM is evaluated ({@link dsm.util.EvalDatasetReader}). Some datasets are available for multiple languages.
 * The language of the test dataset used depends on the language of the InputData used.
 * <ul>
 * <li>MenDev: the development part of the MEN dataset
 * <li>MenDevPart1: the first half of the development part of the MEN dataset
 * <li>MenDevPart2: the second half of the development part of the MEN dataset
 * <li>MenTest: the test part of the MEN dataset
 * <li>MenFull: the full MEN dataset
 * <li>RubensteinGoodenough65: the Rubenstein-Goodenough-65 dataset
 * <li>MillerCharles28: the Miller-Charles-28 dataset
 * <li>WordSim353: the WordSim-353 dataset
 * <li>SimLex999: the SimLex-999 dataset
 * <li>Toefl: the TOEFL dataset
 * <li>ToeflPart1: the first part of the TOEFL dataset
 * <li>ToeflPart2: the second part of the TOEFL dataset
 * <li>Moldovan: the Moldovan dataset
 * </ul>
 * 
 * <br>
 * 
 * String InputData: the type of extracted information or word vectors used as input for the DSM. For more info see ({@link dsm.util.ExtractedInfoReader}).
 * 
 * <br><br>
 * 
 * String VectorNormalization: the type of vector normalization used before the comparison of word vectors ({@link dsm.parameters.VecNorm}).
 * <ul>
 * <li>NN: no normalization
 * <li>L1: L1 norm
 * <li>L2: L2 norm
 * </ul>
 * 
 * <br>
 * 
 * String SimilarityMeasure: the type of vector similarity measure used in the DSM. For more info see ({@link dsm.parameters.VecSim}).
 * 
 * <br><br>
 * 
 * String WeightingScheme: the type of weighting scheme used in the DSM. For more info see ({@link dsm.parameters.Weight}).
 * 
 * <br><br>
 * 
 * Integer minimumWordSimpleFrequency: minimum limit on word frequencies ({@link dsm.parameters.MiscParam})
 * 
 * <br><br>
 * 
 * Integer minimumFeatureSimpleFrequency: minimum limit on feature frequencies ({@link dsm.parameters.MiscParam})
 * 
 * <br><br>
 * 
 * Integer minimumWordFeatureTupleSimpleFrequency: minimum limit on word-feature tuple frequencies ({@link dsm.parameters.MiscParam})
 * 
 * <br><br>
 * 
 * Boolean filterStopWords: the option to filter stop-words or not ({@link dsm.parameters.MiscParam})
 * 
 * <br><br>
 * 
 * String MinimumWeight: minimum limit on word-feature tuple weights ({@link dsm.parameters.MiscParam}). For more info see the Section 4.2.9 of the dissertation.
 * <ul>
 * <li>"null": no limit
 * <li>"Limit" minimumWordFeatureTupleWeightParameter: Limit type of limit, with a Double as the value of the limit
 * <li>"Zero" minimumWordFeatureTupleWeightParameter: Zero type of limit, with a Double as the value of the limit
 * </ul>
 * 
 * <br>
 * 
 * String Smooth: the type of smoothing used on the word vectors ({@link dsm.parameters.Smooth}).
 * <ul>
 * <li>"NoSmooth": no smoothing
 * <li>"WeightKNS" KNSParameter: Kneser-Ney smoothing on weights, with a Double parameter
 * <li>"FreqKNS" KNSParameter: Kneser-Ney smoothing on raw counts, with a Double parameter
 * <li>"FreqMKNS" KNSParameter1 KNSParameter2 KNSParameter3: Modified Kneser-Ney smoothing on raw counts, with 3 Double parameters
 * <li>"FreqMDKNSPOMD" KNSParameter1 KNSParameter2 KNSParameter3: Multi-D Kneser-Ney Smoothing Preserving the Original Marginal Distributions 
 * on raw counts, with 3 Double parameters
 * </ul>
 * 
 * <br>
 * 
 * String FeatTransf: the type of feature transformation used. For more info see ({@link dsm.parameters.FeatTransf}).
 * 
 * <br><br>
 * 
 * String DimRed: the type of dimensionality reduction used on the word vectors. For more info see ({@link dsm.parameters.DimRed}).
 * 
 * @author Dobó
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

            if(similarityMeasure==SimilarityMeasure.Lin && dimensionalityReductionType==DimensionalityReductionType.SVD){
                System.out.println("In case of the LIN similarity measure the SVD dimensionlality reduction cannot be used, because the feature information values cannot be calculated");
                System.exit(1);
            }
            
                readExtractedInformationFile();
                
            }else{
                
            if(similarityMeasure==SimilarityMeasure.Lin || weightingScheme!=WeightingScheme.Freq){
                System.out.println("Wrong method, similarityMeasure or weightingScheme (in case of WORDVECTORS only NUM FREQ weightingScheme is available, "
                        + "and the LIN1 similarity measure cannot be used): " + similarityMeasure + " " + weightingScheme);
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

        if(featureTransformationType==FeatureTransformationType.Freq){
            applyFeatureTransformation(featureTransformationType, featureTransformationFunction);
        }

        if(smoothingType==SmoothingType.FreqKNS || smoothingType==SmoothingType.FreqMKNS || smoothingType==SmoothingType.FreqMDKNSPOMD){
            applySmoothing();
        }

        if(featureTransformationType==FeatureTransformationType.FreqAftSmooth){
            applyFeatureTransformation(featureTransformationType, featureTransformationFunction);
        }


        if(similarityMeasure==SimilarityMeasure.Lin || weightingScheme==WeightingScheme.Plffi){
            computeInformationForFeatures();
        }
        

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
