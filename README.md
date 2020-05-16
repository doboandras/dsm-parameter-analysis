# dsm-parameter-analysis
This is the GitHub project connected to the PhD dissertation of András Dobó:

Dobó, A.: <a href="http://doktori.bibl.u-szeged.hu/10120/1/AndrasDoboThesis2019.pdf" target="_blank">A comprehensive analysis of the parameters in the creation and comparison of feature vectors in distributional semantic models for multiple languages</a>. University of Szeged (2019)

If you would like to reference or cite this project, please cite the above dissertation.


<br><br>


General usage:

First, information should be extracted from a corpus, if a corpus is used as input data, using either <a href="javadoc/extinfbow/ExtractInformationBagOfWords.html" target="_blank">ExtractInformationBagOfWords</a> (bag-of-words based) or <a href="javadoc/extinfparsed/ExtractInformationFromParsedText.html" target="_blank">ExtractInformationFromParsedText</a> (dependency-relation-based).<br>
Then, this extracted information or word vectors obtained from somewhere should be used as input for <a href="javadoc/dsm/DSM.html" target="_blank">DSM</a> to test a given configuration (combination of parameter settings) on a standard test dataset.


<br><br>


Main classes:
<ul>
<li><a href="javadoc/extinfbow/ExtractInformationBagOfWords.html" target="_blank">ExtractInformationBagOfWords</a> - it extracts information from an English, Spanish or Hungarian corpus using a bag-of-words method</li>
<li><a href="javadoc/extinfparsed/ExtractInformationFromParsedText.html" target="_blank">ExtractInformationFromParsedText</a> - it extracts information from a parsed English corpus using a dependency-relation-based method</li>
<li><a href="javadoc/dsm/DSM.html" target="_blank">DSM</a> - it implements numerous distributional semantic models based on information extracted from corpora, and evaluates them on standard datasets</li>
</ul>


<br><br>


Used libraries and resources:

<ul>

<li><a href="https://wordnet.princeton.edu/" target="_blank">WordNet dictionary files (version 3.0)</a><br>
George A. Miller. WordNet: A Lexical Database for English. Communications of the ACM Vol. 38, No. 11: 39-41, 1995.<br>
Christiane Fellbaum. WordNet: An Electronic Lexical Database. Cambridge, MA: MIT Press, 1998.
</li>

<li><a href="https://sourceforge.net/projects/jwordnet/" target="_blank">Java WordNet Library (JWNL) (version 1.4-rc2)</a></li>

<li><a href="https://sourceforge.net/projects/parallelcolt/" target="_blank">ParallelColt (version 0.9.4)</a></li>

<li><a href="http://staffwww.dcs.shef.ac.uk/people/A.Aker/activityNLPProjects.html" target="_blank">Ahmet Aker Spanish POS Tagger and Lemmatizer and corresponding dictionary files</a></li>

<li><a href="http://www.inf.u-szeged.hu/rgai/magyarlanc" target="_blank">Magyar lánc (HunPOSChain) (version 2.0)</a><br>
Zsibrita, János; Vincze, Veronika; Farkas, Richárd. magyarlanc: A Toolkit for Morphological and Dependency Parsing of Hungarian. In: Proceedings of RANLP 2013, pp. 763-771, 2013.</li>

<li><a href="https://github.com/stopwords-iso/" target="_blank">Stopwords ISO collection</a></li>

</ul>


<br><br>


Used test datasets:

<ul>

<li><a href="https://staff.fnwi.uva.nl/e.bruni/MEN" target="_blank">MEN dataset</a><br>
Elia Bruni, Nam Khan Tran, and Marco Baroni. Multimodal distributional semantics. Journal of Articifial Intelligence Research, 48:1–47, 2013.
</li>

<li><a href="https://dl.acm.org/citation.cfm?id=365657" target="_blank">RubensteinGoodenough-65 dataset</a><br>
Herbert Rubenstein and John B. Goodenough. Contextual correlates of synonymy. Communications of the ACM, 8(10):627–633, 1965.
</li>

<li><a href="https://www.ijcai.org/Proceedings/95-1/Papers/059.pdf" target="_blank">Miller-Charles-28 dataset</a><br>
Philip Resnik. Using Information Content to Evaluate Semantic Similarity in a Taxonomy. In In: 14th International Joint Conference on Artificial Intelligence, pages 448–453. Morgan Kaufmann Publishers Inc., San Francisco, 1995.
</li>

<li><a href="http://www.cs.technion.ac.il/~gabr/resources/data/wordsim353/" target="_blank">WordSim-353 dataset</a><br>
Lev Finkelstein, Evgeniy Gabrilovich, Yossi Matias, Ehud Rivlin, Zach Solan, Gadi Wolfman, and Eytan Ruppin. Placing search in context: the concept revisited. ACM Transactions on Information Systems, 20(1):116–131, 2002.
</li>

<li><a href="https://fh295.github.io/simlex.html" target="_blank">SimLex-999 dataset</a><br>
Felix Hill, Roi Reichart, and Anna Korhonen. SimLex-999: Evaluating Semantic Models with (Genuine) Similarity Estimation. Computational Linguistics, 41(4):665–695, 2015.
</li>

<li><a href="https://psycnet.apa.org/doiLanding?doi=10.1037%2F0033-295X.104.2.211" target="_blank">TOEFL dataset</a><br>
T K Landauer and S T Dumais. A solution to Plato’s problem: The latent semantic analysis theory of acquisition, induction and representation of knowledge. Psychological Review, 104(2):211–240, 1997.
</li>

<li><a href="http://web.eecs.umich.edu/~mihalcea/downloads.html" target="_blank">Spanish WordSimilarity-353</a><br>
Samer Hassan and Rada Mihalcea. Cross-lingual semantic relatedness using encyclopedic knowledge. In 2009 CoNLL, pages 1192–1201, 2009.
</li>

<li><a href="https://doi.org/10.3758/s13428-014-0501-z" target="_blank">Moldovan dataset</a><br>
Cornelia D Moldovan, Pilar Ferré, Josep Demestre, and Rosa Sánchez-Casas. Semantic similarity: normative ratings for 185 Spanish noun triplets. Behavior research methods, 47(3):788–799, 2015.
</li>

<li><a href="http://lcl.uniroma1.it/similarity-datasets/" target="_blank">Spanish Rubenstein Goodenough</a><br>
José Camacho-Collados, Mohammad Taher Pilehvar, and Roberto Navigli. A Framework for the Construction of Monolingual and Cross-lingual Word Similarity Datasets. In 53rd ACL, pages 1–7, 2015.
</li>

<li><a href="http://doktori.bibl.u-szeged.hu/10120/1/AndrasDoboThesis2019.pdf" target="_blank">Hungarian TOEFL dataset</a><br>
András Dobó. A comprehensive analysis of the parameters in the creation and comparison of feature vectors in distributional semantic models for multiple languages. University of Szeged, 2019.
</li>

<li><a href="http://doktori.bibl.u-szeged.hu/10120/1/AndrasDoboThesis2019.pdf" target="_blank">Hungarian Rubenstein-Goodenough</a><br>
András Dobó. A comprehensive analysis of the parameters in the creation and comparison of feature vectors in distributional semantic models for multiple languages. University of Szeged, 2019.
</li>

</ul>


<br><br>


For more information please see the below publications:

Dobó, A.: <a href="http://journal.sepln.org/index.php/pln/article/viewFile/6205/3656" target="_blank">A comprehensive analysis of the parameters in the creation and comparison of feature vectors in distributional semantic models for multiple languages</a>. Procesamiento del Lenguaje Natural. 64, 127-130. (2020)

Dobó, A.: <a href="http://doktori.bibl.u-szeged.hu/10120/1/AndrasDoboThesis2019.pdf" target="_blank">A comprehensive analysis of the parameters in the creation and comparison of feature vectors in distributional semantic models for multiple languages</a>. University of Szeged (2019)

Dobó, A., Csirik, J.: <a href="Publications/Comparison of the best parameter settings of DSMs across languages.pdf" target="_blank">Comparison of the Best Parameter Settings in the Creation and Comparison of Feature Vectors in Distributional Semantic Models Across Multiple Languages</a>. In: MacIntyre J., Maglogiannis I., Iliadis L., Pimenidis E. (eds) Artificial Intelligence Applications and Innovations. AIAI 2019. IFIP Advances in Information and Communication Technology, vol 559. 487-499. Springer, Cham. (2019)

Dobó, A., Csirik, J.: <a href="https://doi.org/10.1080/09296174.2019.1570897" target="_blank">A Comprehensive Study of the Parameters in the Creation and Comparison of Feature Vectors in Distributional Semantic Models</a>. Journal of Quantitative Linguistics (2019)


<br><br>


Questions and comments:

If you have questions or comments, please email me at: dobo at inf dot u-szeged dot hu.
