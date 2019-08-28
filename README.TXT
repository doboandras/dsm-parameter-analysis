This is the GitHub project connected to the PhD dissertation of András Dobó:

Dobó, A.: A comprehensive analysis of the parameters in the creation and comparison of feature vectors in distributional semantic models for multiple languages. University of Szeged (2019)

If you would like to reference or cite this project, please cite the above dissertation.





There are 3 main classes in the project, as follows:



DSM - it implements numerous distributional semantic models based on information extracted from corpora, and evaluates them on standard datasets


Usage:

java -cp DSM.jar dsm.DSM EvaluationDataset InputDataType INPUTDATA METHOD

INPUTDATA = ("Corpus" bagOfWords WordType corpusStringSuffix) | ("Vectors" WordVectors wordVectorsDimension)

METHOD = "Lin" | ("Num" VectorNormalization SimilarityMeasure WeightingScheme minimumWordSimpleFrequency minimumFeatureSimpleFrequency minimumWordFeatureTupleSimpleFrequency filterStopWords MINIMUMWEIGHT SMOOTH FEATTRANSF DIMRED

MINIMUMWEIGHT = "null" | (Limit minimumWordFeatureTupleWeightParameter) | (Zero minimumWordFeatureTupleWeightParameter)

SMOOTH = "NoSmooth" | (("FreqKNS" | "WeightKNS") KNSParameter) | (("FreqMKNS" | "FreqMDKNSPOMD") KNSParameter1 KNSParameter2 KNSParameter3)

FEATTRANSF = "NoTransf" | (FeatureTransformationType FeatureTransformationFunction)

DIMRED = "NoDimRed" | (DimensionalityReductionType dimensionalityReductionParameter)





ExtractInformationBagOfWords - it extracts information from an English, Spanish or Hungarian corpus using a bag-of-words method


Usage:

java -cp DSM.jar extinfbow.ExtractInformationBagOfWords SOURCETYPE windowSize WeightingScheme extractInformaionJustForInputWords outputFileName location

SOURCETYPE = "HuParser" | "EsTagger" | (("EnParserV1" | "EnParserV2" | "EnParserV2WithSentenceCounts") WordType)





ExtractInformationFromParsedText - it extracts information from a parsed English corpus


Usage:

java -cp DSM.jar extinfparsed.ExtractInformationFromParsedText SourceType WordType extractInformaionJustForInputWords outputFileName location





For more information please see the below publications:

Dobó, A.: A comprehensive analysis of the parameters in the creation and comparison of feature vectors in distributional semantic models for multiple languages. University of Szeged (2019)

Dobó A., Csirik J.: Comparison of the Best Parameter Settings in the Creation and Comparison of Feature Vectors in Distributional Semantic Models Across Multiple Languages. In: MacIntyre J., Maglogiannis I., Iliadis L., Pimenidis E. (eds) Artificial Intelligence Applications and Innovations. AIAI 2019. IFIP Advances in Information and Communication Technology, vol 559. 487-499. Springer, Cham. (2019)

Dobó A., Csirik J.: A Comprehensive Study of the Parameters in the Creation and Comparison of Feature Vectors in Distributional Semantic Models. Journal of Quantitative Linguistics (2019)
