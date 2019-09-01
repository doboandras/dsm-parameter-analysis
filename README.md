# dsm-parameter-analysis
This is the GitHub project connected to the PhD dissertation of András Dobó:

Dobó, A.: <a href="http://doktori.bibl.u-szeged.hu/10120/1/AndrasDoboThesis2019.pdf" target="_blank">A comprehensive analysis of the parameters in the creation and comparison of feature vectors in distributional semantic models for multiple languages</a>. University of Szeged (2019)

If you would like to reference or cite this project, please cite the above dissertation.


<br><br><br><br>


There are 3 main classes in the project, as follows:

<br><br><br>

DSM - it implements numerous distributional semantic models based on information extracted from corpora, and evaluates them on standard datasets
<br><br>

Usage:

java -cp DSM.jar dsm.DSM EvaluationDataset INPUTDATA VectorNormalization SimilarityMeasure WeightingScheme minimumWordSimpleFrequency minimumFeatureSimpleFrequency minimumWordFeatureTupleSimpleFrequency filterStopWords MINIMUMWEIGHT SMOOTH FEATTRANSF DIMRED

INPUTDATA = ("Corpus" Corpus bagOfWords corpusStringSuffix) | ("Vectors" WordVectors wordVectorsDimension)

MINIMUMWEIGHT = "null" | (Limit minimumWordFeatureTupleWeightParameter) | (Zero minimumWordFeatureTupleWeightParameter)

SMOOTH = "NoSmooth" | (("FreqKNS" | "WeightKNS") KNSParameter) | (("FreqMKNS" | "FreqMDKNSPOMD") KNSParameter1 KNSParameter2 KNSParameter3)

FEATTRANSF = "NoTransf" | (FeatureTransformationType FeatureTransformationFunction)

DIMRED = "NoDimRed" | (DimensionalityReductionType dimensionalityReductionParameter)


<br><br><br>


ExtractInformationBagOfWords - it extracts information from an English, Spanish or Hungarian corpus using a bag-of-words method


Usage:

java -cp DSM.jar extinfbow.ExtractInformationBagOfWords SourceType windowSize WeightingScheme extractInformaionJustForInputWords corpusLocation outputFileName


<br><br><br>


ExtractInformationFromParsedText - it extracts information from a parsed English corpus


Usage:

java -cp DSM.jar extinfparsed.ExtractInformationFromParsedText SourceType extractInformaionJustForInputWords corpusLocation outputFileName


<br><br><br><br>


General usage:

First, information should be extracted from a corpus, if a corpus is used as input data, using either ExtractInformationBagOfWords or ExtractInformationFromParsedText. Then, this extracted information or word vectors should be used as input for DSM to test a given configuration (combination of parameter settings) on a standard test dataset.


<br><br><br><br>


Required libraries:
<ul>
<li>Java WordNet Library (JWNL) (version 1.4-rc2)</li>
<li>ParallelColt (version 0.9.4)</li>
<li>Ahmet Aker Spanish Lemmatizer</li>
<li>Magyar lánc (HunPOSChain) (version 2.0)</li>
</ul>


<br><br><br><br>


For more information please see the below publications:

Dobó, A.: <a href="http://doktori.bibl.u-szeged.hu/10120/1/AndrasDoboThesis2019.pdf" target="_blank">A comprehensive analysis of the parameters in the creation and comparison of feature vectors in distributional semantic models for multiple languages</a>. University of Szeged (2019)

Dobó A., Csirik J.: <a href="Publications/Comparison of the best parameter settings of DSMs across languages.pdf" target="_blank">Comparison of the Best Parameter Settings in the Creation and Comparison of Feature Vectors in Distributional Semantic Models Across Multiple Languages</a>. In: MacIntyre J., Maglogiannis I., Iliadis L., Pimenidis E. (eds) Artificial Intelligence Applications and Innovations. AIAI 2019. IFIP Advances in Information and Communication Technology, vol 559. 487-499. Springer, Cham. (2019)

Dobó A., Csirik J.: <a href="https://doi.org/10.1080/09296174.2019.1570897" target="_blank">A Comprehensive Study of the Parameters in the Creation and Comparison of Feature Vectors in Distributional Semantic Models</a>. Journal of Quantitative Linguistics (2019)

