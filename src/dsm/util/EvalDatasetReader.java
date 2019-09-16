package dsm.util;

import static dsm.parameters.MiscParam.*;
import static dsm.util.MiscUtil.*;
import dsm.util.ExtractedInfoReader.Corpus;
import dsm.util.ExtractedInfoReader.InputDataType;
import static dsm.util.ExtractedInfoReader.corpus;
import static dsm.util.ExtractedInfoReader.inputDataType;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import net.didion.jwnl.data.IndexWord;
import net.didion.jwnl.data.POS;
import szte.nlp.pos.HunLemMor;
import szte.nlp.pos.MorAna;
import util.ComparablePair;

/**
 * The class for reading in the evaluation dataset.
 * 
 * @author Dobó
 */
public class EvalDatasetReader {
    
    public static enum EvaluationDataset {MillerCharles28, MillerCharles30, Toefl, ToeflPart1, ToeflPart2, RubensteinGoodenough65, Rg65WithoutMc28, WordSim353, 
        ReadersDigestClean, Esl, MenDev, MenDevPart1, MenDevPart2, MenTest, MenFull, SimLex999, MTurk771Halawi, MTurk287Radinsky, SemEval2017Task2, Moldovan};
    public static EvaluationDataset evaluationDataset=EvaluationDataset.MillerCharles28;
    
    
    
    
    
    
    public static void readEvalDataset(){
        
        if(evaluationDataset==EvaluationDataset.Toefl){
            if(inputDataType==InputDataType.Corpus && corpus==Corpus.HuWiki){
                readSynonymQuestions("input/TOEFLDatasetHu.txt");
            }else if(inputDataType==InputDataType.Corpus && corpus==Corpus.EsWki){
                readSynonymQuestions("input/TOEFLDatasetEs.txt");
            }else{
                readSynonymQuestions("input/TOEFLDataset.txt");
            }
        }else if(evaluationDataset==EvaluationDataset.ToeflPart1){
            if(inputDataType==InputDataType.Corpus && corpus==Corpus.HuWiki){
                readSynonymQuestions("input/TOEFLDatasetPart1Hu.txt");
            }else if(inputDataType==InputDataType.Corpus && corpus==Corpus.EsWki){
                readSynonymQuestions("input/TOEFLDatasetPart1Es.txt");
            }else{
                readSynonymQuestions("input/TOEFLDatasetPart1.txt");
            }
        }else if(evaluationDataset==EvaluationDataset.ToeflPart2){
            if(inputDataType==InputDataType.Corpus && corpus==Corpus.HuWiki){
                readSynonymQuestions("input/TOEFLDatasetPart2Hu.txt");
            }else if(inputDataType==InputDataType.Corpus && corpus==Corpus.EsWki){
                readSynonymQuestions("input/TOEFLDatasetPart2Es.txt");
            }else{
                readSynonymQuestions("input/TOEFLDatasetPart2.txt");
            }
        }else if(evaluationDataset==EvaluationDataset.MillerCharles28){
            if(inputDataType==InputDataType.Corpus && corpus==Corpus.HuWiki){
                readWordPairSimilarities("input/millerCharles28DatasetHu.txt");
            }else if(inputDataType==InputDataType.Corpus && corpus==Corpus.EsWki){
                readWordPairSimilarities("input/millerCharles28DatasetEs.txt");
            }else{
                readWordPairSimilarities("input/millerCharles28Dataset.txt");
            }
        }else if(evaluationDataset==EvaluationDataset.MillerCharles30){
            if(inputDataType==InputDataType.Corpus && corpus==Corpus.HuWiki){
                readWordPairSimilarities("input/millerCharles30DatasetHu.txt");
            }else if(inputDataType==InputDataType.Corpus && corpus==Corpus.EsWki){
                readWordPairSimilarities("input/millerCharles30DatasetEs.txt");
            }else{
                readWordPairSimilarities("input/millerCharles30Dataset.txt");
            }
        }else if(evaluationDataset==EvaluationDataset.RubensteinGoodenough65){
            if(inputDataType==InputDataType.Corpus && corpus==Corpus.HuWiki){
                readWordPairSimilarities("input/rubensteinGoodenoughDatasetHu.txt");
            }else if(inputDataType==InputDataType.Corpus && corpus==Corpus.EsWki){
                readWordPairSimilarities("input/rubensteinGoodenoughDatasetEs.txt");
            }else{
                readWordPairSimilarities("input/rubensteinGoodenoughDataset.txt");
            }
        }else if(evaluationDataset==EvaluationDataset.Rg65WithoutMc28){
            if(inputDataType==InputDataType.Corpus && corpus==Corpus.HuWiki){
                readWordPairSimilarities("input/rubensteinGoodenoughMinusMillerCharles28DatasetHu.txt");
            }else if(inputDataType==InputDataType.Corpus && corpus==Corpus.EsWki){
                readWordPairSimilarities("input/rubensteinGoodenoughMinusMillerCharles28DatasetEs.txt");
            }else{
                readWordPairSimilarities("input/rubensteinGoodenoughMinusMillerCharles28Dataset.txt");
            }
        }else if(evaluationDataset==EvaluationDataset.WordSim353){
            if(inputDataType==InputDataType.Corpus && corpus==Corpus.HuWiki){
                readWordPairSimilarities("input/wordSim353DatasetHu.txt");
            }else if(inputDataType==InputDataType.Corpus && corpus==Corpus.EsWki){
                readWordPairSimilarities("input/wordSim353DatasetEs.txt");
            }else{
                readWordPairSimilarities("input/wordSim353Dataset.txt");
            }
        }else if(evaluationDataset==EvaluationDataset.ReadersDigestClean){
            if(inputDataType==InputDataType.Corpus && corpus==Corpus.HuWiki){
                readSynonymQuestions("input/readersDigestCleanedDatasetHu.txt");
            }else if(inputDataType==InputDataType.Corpus && corpus==Corpus.EsWki){
                readSynonymQuestions("input/readersDigestCleanedDatasetEs.txt");
            }else{
                readSynonymQuestions("input/readersDigestCleanedDataset.txt");
            }
        }else if(evaluationDataset==EvaluationDataset.Esl){
            if(inputDataType==InputDataType.Corpus && corpus==Corpus.HuWiki){
                readSynonymQuestions("input/ESLDatasetHu.txt");
            }else if(inputDataType==InputDataType.Corpus && corpus==Corpus.EsWki){
                readSynonymQuestions("input/ESLDatasetEs.txt");
            }else{
                readSynonymQuestions("input/ESLDataset.txt");
            }
        }else if(evaluationDataset==EvaluationDataset.MenDev){
            if(inputDataType==InputDataType.Corpus && corpus==Corpus.HuWiki){
                readWordPairSimilarities("input/MENDatasetDevHu.txt");
            }else if(inputDataType==InputDataType.Corpus && corpus==Corpus.EsWki){
                readWordPairSimilarities("input/MENDatasetDevEs.txt");
            }else{
                readWordPairSimilarities("input/MENDatasetDev.txt");
            }
        }else if(evaluationDataset==EvaluationDataset.MenDevPart1){
            if(inputDataType==InputDataType.Corpus && corpus==Corpus.HuWiki){
                readWordPairSimilarities("input/MENDatasetDevPart1Hu.txt");
            }else if(inputDataType==InputDataType.Corpus && corpus==Corpus.EsWki){
                readWordPairSimilarities("input/MENDatasetDevPart1Es.txt");
            }else{
                readWordPairSimilarities("input/MENDatasetDevPart1.txt");
            }
        }else if(evaluationDataset==EvaluationDataset.MenDevPart2){
            if(inputDataType==InputDataType.Corpus && corpus==Corpus.HuWiki){
                readWordPairSimilarities("input/MENDatasetDevPart2Hu.txt");
            }else if(inputDataType==InputDataType.Corpus && corpus==Corpus.EsWki){
                readWordPairSimilarities("input/MENDatasetDevPart2Es.txt");
            }else{
                readWordPairSimilarities("input/MENDatasetDevPart2.txt");
            }
        }else if(evaluationDataset==EvaluationDataset.MenTest){
            if(inputDataType==InputDataType.Corpus && corpus==Corpus.HuWiki){
                readWordPairSimilarities("input/MENDatasetTestHu.txt");
            }else if(inputDataType==InputDataType.Corpus && corpus==Corpus.EsWki){
                readWordPairSimilarities("input/MENDatasetTestEs.txt");
            }else{
                readWordPairSimilarities("input/MENDatasetTest.txt");
            }
        }else if(evaluationDataset==EvaluationDataset.MenFull){
            if(inputDataType==InputDataType.Corpus && corpus==Corpus.HuWiki){
                readWordPairSimilarities("input/MENDatasetFullHu.txt");
            }else if(inputDataType==InputDataType.Corpus && corpus==Corpus.EsWki){
                readWordPairSimilarities("input/MENDatasetFullEs.txt");
            }else{
                readWordPairSimilarities("input/MENDatasetFull.txt");
            }
        }else if(evaluationDataset==EvaluationDataset.SimLex999){
            if(inputDataType==InputDataType.Corpus && corpus==Corpus.HuWiki){
                readWordPairSimilarities("input/simLex999DatasetHu.txt");
            }else if(inputDataType==InputDataType.Corpus && corpus==Corpus.EsWki){
                readWordPairSimilarities("input/simLex999DatasetEs.txt");
            }else{
                readWordPairSimilarities("input/simLex999Dataset.txt");
            }
        }else if(evaluationDataset==EvaluationDataset.MTurk771Halawi){
            if(inputDataType==InputDataType.Corpus && corpus==Corpus.HuWiki){
                readWordPairSimilarities("input/MTurk771HalawiDatasetHu.txt");
            }else if(inputDataType==InputDataType.Corpus && corpus==Corpus.EsWki){
                readWordPairSimilarities("input/MTurk771HalawiDatasetEs.txt");
            }else{
                readWordPairSimilarities("input/MTurk771HalawiDataset.txt");
            }
        }else if(evaluationDataset==EvaluationDataset.MTurk287Radinsky){
            if(inputDataType==InputDataType.Corpus && corpus==Corpus.HuWiki){
                readWordPairSimilarities("input/MTurk287RadinskyDatasetHu.txt");
            }else if(inputDataType==InputDataType.Corpus && corpus==Corpus.EsWki){
                readWordPairSimilarities("input/MTurk287RadinskyDatasetEs.txt");
            }else{
                readWordPairSimilarities("input/MTurk287RadinskyDataset.txt");
            }
        }else if(evaluationDataset==EvaluationDataset.SemEval2017Task2){
            if(inputDataType==InputDataType.Corpus && corpus==Corpus.HuWiki){
                readWordPairSimilarities("input/semEval2017Task2CleanedDatasetHu.txt");
            }else if(inputDataType==InputDataType.Corpus && corpus==Corpus.EsWki){
                readWordPairSimilarities("input/semEval2017Task2CleanedDatasetEs.txt");
            }else{
                readWordPairSimilarities("input/semEval2017Task2CleanedDataset.txt");
            }
        }else if(evaluationDataset==EvaluationDataset.Moldovan){
            if(inputDataType==InputDataType.Corpus && corpus==Corpus.HuWiki){
                readWordPairSimilarities("input/moldovanDatasetHu.txt");
            }else if(inputDataType==InputDataType.Corpus && corpus==Corpus.EsWki){
                readWordPairSimilarities("input/moldovanDatasetEs.txt");
            }else{
                readWordPairSimilarities("input/moldovanDataset.txt");
            }
        }
        
    }
    
    
    
    


    /**
     * This function reads in the file containing the input synonym question dataset. It reads every line 4 times, every time as if the line containded words of one of the possible part-of-speeches.
     * This is needed, since some words can take more than one POS, and it will be decided later which POS is assigned to which question.
     * @param inputFile the input file containing the evaluation dataset
     */
    public static void readSynonymQuestions(String inputFile){
        try{
            
            BufferedReader in = new BufferedReader(new FileReader(inputFile));
            String line;
            while((line=in.readLine())!=null){

                readSynonymQuestions(line, POS.NOUN, allNouns, nounLemmas, allNounQuestionsWithAllAnswers);
                readSynonymQuestions(line, POS.VERB, allVerbs, verbLemmas, allVerbQuestionsWithAllAnswers);
                readSynonymQuestions(line, POS.ADJECTIVE, allAdjectives, adjectiveLemmas, allAdjectiveQuestionsWithAllAnswers);
                readSynonymQuestions(line, POS.ADVERB, allAdverbs, adverbLemmas, allAdverbQuestionsWithAllAnswers);

            }
            in.close();
            
            

            
        }catch(Exception e){
            System.out.println("Error in reading the noun compounds!");
            e.printStackTrace();
            System.exit(1);
        }
    }
    
    
    /**
     * This function processes a line of the input synonym question dataset. The lemma of all the input words is determined. Then the information contained in the line is saved in the sets/maps 
     * given as parameters to this function.
     * @param line the line
     * @param pos the assumed pos for the line
     * @param allWordSet the set, to which the all the words with part-of-speech==@param pos are saved
     * @param lemmaMap a map, that maps the original question words to their lemmas
     * @param allQuestionsWithAllAnswersMap a map, in which the question words are stored with all the possible answers
     * @throws Exception possible exception
     */
    public static void readSynonymQuestions(String line, POS pos, HashSet<String> allWordSet, HashMap<String, String> lemmaMap, HashMap<String, ArrayList<ArrayList<String>>> allQuestionsWithAllAnswersMap) throws Exception{
        
        String[] parts = line.replace("|", ";").split(" ; ");

        ArrayList<String> answersList = new ArrayList<String>();
        
        String originalWord=parts[0];

        for(int i=0; i<parts.length; i++){
            
            parts[i]=parts[i].toLowerCase();

            boolean wordIsInWordNet=false;
            if(inputDataType==InputDataType.Corpus && corpus==Corpus.HuWiki){
                Set<MorAna> morAnaSet = HunLemMor.getMorphologicalAnalyses(parts[i]);
                for(MorAna morAna: morAnaSet){
                    String msd = morAna.getMsd();
                    if((pos==POS.NOUN && msd.startsWith("Nc")) || (pos==POS.VERB && msd.startsWith("V")) || (pos==POS.ADJECTIVE && msd.startsWith("A")) || (pos==POS.ADVERB && msd.startsWith("R"))){
                        parts[i] = morAna.getLemma();
                        wordIsInWordNet=true;
                        break;
                    }
                }
            }else if(inputDataType==InputDataType.Corpus && corpus==Corpus.EsWki){
                String lemma;
                if(pos==POS.NOUN){
                    if((lemma=nounDic.get(parts[i]))!=null){
                        parts[i] = lemma;
                        wordIsInWordNet=true;
                    }
                }else if(pos==POS.VERB){
                    if((lemma=verbDic.get(parts[i]))!=null){
                        parts[i] = lemma;
                        wordIsInWordNet=true;
                    }
                }else if(pos==POS.ADJECTIVE){
                    if((lemma=adjDic.get(parts[i]))!=null){
                        parts[i] = lemma;
                        wordIsInWordNet=true;
                    }
                }else if(pos==POS.ADVERB){
                    if((lemma=advDic.get(parts[i]))!=null){
                        parts[i] = lemma;
                        wordIsInWordNet=true;
                    }
                }
            }else{
                IndexWord iw = dict.lookupIndexWord(pos, parts[i]);
                if (iw != null) {
                    parts[i] = iw.getLemma();
                    wordIsInWordNet=true;                      
                }
            }
            
            allWordSet.add(parts[i]);
            if(i>0){
                answersList.add(parts[i]);
            }

        }
        
        ArrayList<ArrayList<String>> list = allQuestionsWithAllAnswersMap.get(parts[0]);
        if(list==null){
            list=new ArrayList<ArrayList<String>>();
        }
        list.add(answersList);
        allQuestionsWithAllAnswersMap.put(parts[0], list);

        lemmaMap.put(originalWord, parts[0]);
        
    }
    
    
    
    /**
     * This function reads in the file containing the input word pair similarity dataset. It reads every line 4 times, every time as if the line contained words of one of the possible part-of-speeches.
     * This is needed, since some words can take more than one POS, and it will be decided later which POS is assigned to which word pair.
     * @param inputFile the input file containing the evaluation dataset
     */
    public static void readWordPairSimilarities(String inputFile){
        try{
            
            BufferedReader in = new BufferedReader(new FileReader(inputFile));
            String line;
            while((line=in.readLine())!=null){
                
                readWordPairSimilarities(line, POS.NOUN, allNouns, nounLemmas, allNounQuestionsWithAllAnswers, allNounQuestionsWithCorrectScores);
                readWordPairSimilarities(line, POS.VERB, allVerbs, verbLemmas, allVerbQuestionsWithAllAnswers, allVerbQuestionsWithCorrectScores);
                readWordPairSimilarities(line, POS.ADJECTIVE, allAdjectives, adjectiveLemmas, allAdjectiveQuestionsWithAllAnswers, allAdjectiveQuestionsWithCorrectScores);
                readWordPairSimilarities(line, POS.ADVERB, allAdverbs, adverbLemmas, allAdverbQuestionsWithAllAnswers, allAdverbQuestionsWithCorrectScores);
       
                
            }
            in.close();

            

        }catch(Exception e){
            System.out.println("Error in reading the noun compounds!");
            e.printStackTrace();
            System.exit(1);
        }
    }
    
    
    
    /**
     * This function processes a line of the input word pair similarity dataset. The lemma of all the input words is determined. Then the information contained in the line is saved in the sets/maps 
     * given as parameters to this function.
     * @param line the line to be processed
     * @param pos the assumed pos for the line
     * @param allWordSet the set, to which the all the words with part-of-speech==@param pos are saved
     * @param lemmaMap a map, that maps the original words to their lemmas
     * @param allQuestionsWithAllAnswersMap a map, in which the question words are stored with all the possible answers
     * @param allQuestionsWithCorrectScores a map, in which the word pairs are stored with their correct score
     * @throws Exception possible exception
     */
    public static void readWordPairSimilarities(String line, POS pos, HashSet<String> allWordSet, HashMap<String, String> lemmaMap, HashMap<String, ArrayList<ArrayList<String>>> allQuestionsWithAllAnswersMap, HashMap<ComparablePair<String, String>, Double> allQuestionsWithCorrectScores) throws Exception{
                
        String[] parts= line.split("\t");
        
        String originalWord=parts[0];
        
        for(int i=0; i<2; i++){
            
            parts[i]=parts[i].toLowerCase();
            
            boolean wordIsInWordNet=false;
            if(inputDataType==InputDataType.Corpus && corpus==Corpus.HuWiki){
                Set<MorAna> morAnaSet = HunLemMor.getMorphologicalAnalyses(parts[i]);
                for(MorAna morAna: morAnaSet){
                    String msd = morAna.getMsd();
                    if((pos==POS.NOUN && msd.startsWith("Nc")) || (pos==POS.VERB && msd.startsWith("V")) || (pos==POS.ADJECTIVE && msd.startsWith("A")) || (pos==POS.ADVERB && msd.startsWith("R"))){
                        parts[i] = morAna.getLemma();
                        wordIsInWordNet=true;
                        break;
                    }
                }
            }else if(inputDataType==InputDataType.Corpus && corpus==Corpus.EsWki){
                String lemma;
                if(pos==POS.NOUN){
                    if((lemma=nounDic.get(parts[i]))!=null){
                        parts[i] = lemma;
                        wordIsInWordNet=true;
                    }
                }else if(pos==POS.VERB){
                    if((lemma=verbDic.get(parts[i]))!=null){
                        parts[i] = lemma;
                        wordIsInWordNet=true;
                    }
                }else if(pos==POS.ADJECTIVE){
                    if((lemma=adjDic.get(parts[i]))!=null){
                        parts[i] = lemma;
                        wordIsInWordNet=true;
                    }
                }else if(pos==POS.ADVERB){
                    if((lemma=advDic.get(parts[i]))!=null){
                        parts[i] = lemma;
                        wordIsInWordNet=true;
                    }
                }
            }else{
                IndexWord iw = dict.lookupIndexWord(pos, parts[i]);
                if (iw != null) {
                    parts[i] = iw.getLemma();
                    wordIsInWordNet=true;                      
                }
            }
            
            allWordSet.add(parts[i]);
            
        }

        ArrayList<ArrayList<String>> list1 = allQuestionsWithAllAnswersMap.get(parts[0]);
        if(list1==null){
            list1=new ArrayList<ArrayList<String>>();
        }
        ArrayList<String> list2 = new ArrayList<String>();
        list2.add(parts[1]);
        list1.add(list2);
        allQuestionsWithAllAnswersMap.put(parts[0], list1);
        
        allQuestionsWithCorrectScores.put(new ComparablePair<String, String>(parts[0], parts[1]), Double.parseDouble(parts[2]));
        
        lemmaMap.put(originalWord, parts[0]);
        
    }
    
    
}
