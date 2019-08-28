/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsm.parameters;

import static dsm.parameters.MiscParam.*;
import dsm.parameters.VecSim.SimilarityMeasure;
import static dsm.parameters.VecSim.distanceToSimilarity;
import static dsm.parameters.VecSim.similarityMeasure;
import static dsm.util.MiscUtil.lb;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Dobó
 */
public class Weight {
    
    public static enum WeightingScheme {Freq, LogFreq, Pmi, LogLhr, Qlflnw, Plffi, Identity, CondProb_1_1, CondProb_1_2, CondProb_1_3, CondProb_1_4, CondProb_1_5, 
        CondProb_1_6, CondProb_1_7, CondProb_1_8, CondProb_2_1, CondProb_2_2, CondProb_2_3, CondProb_2_4, CondProb_2_5, CondProb_2_6, CondProb_2_7, CondProb_2_8, JointProb, 
        PmiWdf, PmiWls, WPmi_1, WPmi_2, WPmi_3, WPmi_4, WPmi_5, WPmi_6, WPmi_7, WPmi_8, WPmi_9, WPmi_10, WPmi_11, WPmi_12, WPmi_13, 
        WPmi_14, WPmi_15, ModPmiLikeWithoutLog_1, ModPmiLikeWithoutLog_2, ModPmiLikeWithoutLog_3, PmiCurran, LPmi_1, LPmi_2, PmiAl, PmiAlWithoutLog, SPmi, NPmi, PmiDisc, 
        Ochiai, Cca, APmiMod, Simpson_1Mod, Simpson_2, Md, LfbMd, LfbMdMod_1, LfbMdMod_2, Me, 
        SalienceMod_1, SalienceMod_2, SimplPmi, BinLh, PoissonLh, PoissonStirlingLh, PoissonSig, SqLogLhr, SokalMichiner, RogersTanimoto, Hamann, 
        SokalSneath_1, SokalSneath_2, SokalSneath_3, Kulczynski_1, Kulczynski_2, YulleW, YulleQ, DriverKroeber, Pears, Fager, Unis, TCombCost, Phi, Kappa, J, Gini, 
        Confidence, Laplace, PiaterskyShapiro, Certainty, AddedValue, 
        TTest_1, TTest_2, TTest_3, ZTest_1, ZTest_2, ZTest_3, SimpleLl, Allr, ChiSquare, ChiSquareWYCC, TfIdf_1, TfIdf_2, TfIdf_3, 
        TfIdf_4, TfIdf_5, TfIdf_6, TfIdf_7, TfIdf_8, TfIdf_9, TfIcf_1, TfIcf_2, TfIcf_3, Gref_1, Lin_1_1, Lin_1_2, Lin_1_3, Lin_1_4, Lin_1_5, Lin_1_6, Lin_1_7, Lin_1_8, 
        Lin_2, Lin_3, Dice, Okapi_1, Okapi_2, Okapi_3, Ltu, RelRisk_1, RelRisk_2, Liddell, Jaccard, GMean, 
        MinSens, OddsRatio_1, OddsRatio_2, OddsRatio_3, PmiPow_2, PmiPow_3, MinPmiTTest_1, MinPmiTTest_2, KrippendorffAlpha, ScottPi, CohenKappa, Ncd, NgdMod, ANgdMod, 
        PmiXTTest_1, PmiXTTest_2, PmiXChiSquare, PmiXTfIdf_1, PmiXRelRisk_1, PmiXRelRisk_2, PmiXLiddell, 
        PmiXOddsRatio_3, PmiXOkapi_1, PmiXLtu, PmiXCondProb_2_1, PmiXCondProb_2_2, PmiXCondProb_2_4, PmiXCondProb_2_6, 
        PmiXLin_1_1, PmiXLin_1_2, PmiXLin_1_3, PmiXLin_1_4, PmiXLin_1_5, PmiXLin_1_6, 
        NPmiAl, SPmiAl, PmiAlWdf, NSPmiAl, NPmiAlWdf, SPmiAlWdf, NSPmiAlWdf, NSPmi, NPmiWdf, NSPmiWdf, SPmiWdf, 
        PmiAlUnis, NPmiUnis, SPmiUnis, PmiWdfUnis, 
        NPmiAlUnis, SPmiAlUnis, PmiAlWdfUnis, NSPmiAlUnis, NPmiAlWdfUnis, 
        SPmiAlWdfUnis, NSPmiAlWdfUnis, NSPmiUnis, NPmiWdfUnis, NSPmiWdfUnis, SPmiWdfUnis, 
        PmiAlUnisAm, PmiAlUnisGm, PmiAlUnisHm, PmiAlUnisProd, PmiAlUnisLogProd, 
        NPmiAlAm, NPmiAlGm, NPmiAlHm, NPmiAlProd, NPmiAlLogProd, 
        Pmi_Tc0Tw0S0P0, Pmi_Tc0Tw0S0P1, Pmi_Tc0Tw0S0P2, Pmi_Tc0Tw0S0P3, Pmi_Tc0Tw0S0P4, Pmi_Tc0Tw0S0P5, Pmi_Tc0Tw0S1P0, Pmi_Tc0Tw0S1P1, Pmi_Tc0Tw0S1P2, Pmi_Tc0Tw0S1P3, Pmi_Tc0Tw0S1P4, Pmi_Tc0Tw0S1P5, Pmi_Tc0Tw0S2P0, Pmi_Tc0Tw0S2P1, Pmi_Tc0Tw0S2P2, Pmi_Tc0Tw0S2P3, Pmi_Tc0Tw0S2P4, Pmi_Tc0Tw0S2P5, 
        Pmi_Tc0Tw1S0P0, Pmi_Tc0Tw1S0P1, Pmi_Tc0Tw1S0P2, Pmi_Tc0Tw1S0P3, Pmi_Tc0Tw1S0P4, Pmi_Tc0Tw1S0P5, Pmi_Tc0Tw1S1P0, Pmi_Tc0Tw1S1P1, Pmi_Tc0Tw1S1P2, Pmi_Tc0Tw1S1P3, Pmi_Tc0Tw1S1P4, Pmi_Tc0Tw1S1P5, Pmi_Tc0Tw1S2P0, Pmi_Tc0Tw1S2P1, Pmi_Tc0Tw1S2P2, Pmi_Tc0Tw1S2P3, Pmi_Tc0Tw1S2P4, Pmi_Tc0Tw1S2P5, 
        Pmi_Tc0Tw2S0P0, Pmi_Tc0Tw2S0P1, Pmi_Tc0Tw2S0P2, Pmi_Tc0Tw2S0P3, Pmi_Tc0Tw2S0P4, Pmi_Tc0Tw2S0P5, Pmi_Tc0Tw2S1P0, Pmi_Tc0Tw2S1P1, Pmi_Tc0Tw2S1P2, Pmi_Tc0Tw2S1P3, Pmi_Tc0Tw2S1P4, Pmi_Tc0Tw2S1P5, Pmi_Tc0Tw2S2P0, Pmi_Tc0Tw2S2P1, Pmi_Tc0Tw2S2P2, Pmi_Tc0Tw2S2P3, Pmi_Tc0Tw2S2P4, Pmi_Tc0Tw2S2P5, 
        Pmi_Tc0Tw3S0P0, Pmi_Tc0Tw3S0P1, Pmi_Tc0Tw3S0P2, Pmi_Tc0Tw3S0P3, Pmi_Tc0Tw3S0P4, Pmi_Tc0Tw3S0P5, Pmi_Tc0Tw3S1P0, Pmi_Tc0Tw3S1P1, Pmi_Tc0Tw3S1P2, Pmi_Tc0Tw3S1P3, Pmi_Tc0Tw3S1P4, Pmi_Tc0Tw3S1P5, Pmi_Tc0Tw3S2P0, Pmi_Tc0Tw3S2P1, Pmi_Tc0Tw3S2P2, Pmi_Tc0Tw3S2P3, Pmi_Tc0Tw3S2P4, Pmi_Tc0Tw3S2P5, 
        Pmi_Tc0Tw4S0P0, Pmi_Tc0Tw4S0P1, Pmi_Tc0Tw4S0P2, Pmi_Tc0Tw4S0P3, Pmi_Tc0Tw4S0P4, Pmi_Tc0Tw4S0P5, Pmi_Tc0Tw4S1P0, Pmi_Tc0Tw4S1P1, Pmi_Tc0Tw4S1P2, Pmi_Tc0Tw4S1P3, Pmi_Tc0Tw4S1P4, Pmi_Tc0Tw4S1P5, Pmi_Tc0Tw4S2P0, Pmi_Tc0Tw4S2P1, Pmi_Tc0Tw4S2P2, Pmi_Tc0Tw4S2P3, Pmi_Tc0Tw4S2P4, Pmi_Tc0Tw4S2P5, 
        Pmi_Tc1Tw0S0P0, Pmi_Tc1Tw0S0P1, Pmi_Tc1Tw0S0P2, Pmi_Tc1Tw0S0P3, Pmi_Tc1Tw0S0P4, Pmi_Tc1Tw0S0P5, Pmi_Tc1Tw0S1P0, Pmi_Tc1Tw0S1P1, Pmi_Tc1Tw0S1P2, Pmi_Tc1Tw0S1P3, Pmi_Tc1Tw0S1P4, Pmi_Tc1Tw0S1P5, Pmi_Tc1Tw0S2P0, Pmi_Tc1Tw0S2P1, Pmi_Tc1Tw0S2P2, Pmi_Tc1Tw0S2P3, Pmi_Tc1Tw0S2P4, Pmi_Tc1Tw0S2P5, 
        Pmi_Tc1Tw1S0P0, Pmi_Tc1Tw1S0P1, Pmi_Tc1Tw1S0P2, Pmi_Tc1Tw1S0P3, Pmi_Tc1Tw1S0P4, Pmi_Tc1Tw1S0P5, Pmi_Tc1Tw1S1P0, Pmi_Tc1Tw1S1P1, Pmi_Tc1Tw1S1P2, Pmi_Tc1Tw1S1P3, Pmi_Tc1Tw1S1P4, Pmi_Tc1Tw1S1P5, Pmi_Tc1Tw1S2P0, Pmi_Tc1Tw1S2P1, Pmi_Tc1Tw1S2P2, Pmi_Tc1Tw1S2P3, Pmi_Tc1Tw1S2P4, Pmi_Tc1Tw1S2P5, 
        Pmi_Tc1Tw2S0P0, Pmi_Tc1Tw2S0P1, Pmi_Tc1Tw2S0P2, Pmi_Tc1Tw2S0P3, Pmi_Tc1Tw2S0P4, Pmi_Tc1Tw2S0P5, Pmi_Tc1Tw2S1P0, Pmi_Tc1Tw2S1P1, Pmi_Tc1Tw2S1P2, Pmi_Tc1Tw2S1P3, Pmi_Tc1Tw2S1P4, Pmi_Tc1Tw2S1P5, Pmi_Tc1Tw2S2P0, Pmi_Tc1Tw2S2P1, Pmi_Tc1Tw2S2P2, Pmi_Tc1Tw2S2P3, Pmi_Tc1Tw2S2P4, Pmi_Tc1Tw2S2P5, 
        Pmi_Tc1Tw3S0P0, Pmi_Tc1Tw3S0P1, Pmi_Tc1Tw3S0P2, Pmi_Tc1Tw3S0P3, Pmi_Tc1Tw3S0P4, Pmi_Tc1Tw3S0P5, Pmi_Tc1Tw3S1P0, Pmi_Tc1Tw3S1P1, Pmi_Tc1Tw3S1P2, Pmi_Tc1Tw3S1P3, Pmi_Tc1Tw3S1P4, Pmi_Tc1Tw3S1P5, Pmi_Tc1Tw3S2P0, Pmi_Tc1Tw3S2P1, Pmi_Tc1Tw3S2P2, Pmi_Tc1Tw3S2P3, Pmi_Tc1Tw3S2P4, Pmi_Tc1Tw3S2P5, 
        Pmi_Tc1Tw4S0P0, Pmi_Tc1Tw4S0P1, Pmi_Tc1Tw4S0P2, Pmi_Tc1Tw4S0P3, Pmi_Tc1Tw4S0P4, Pmi_Tc1Tw4S0P5, Pmi_Tc1Tw4S1P0, Pmi_Tc1Tw4S1P1, Pmi_Tc1Tw4S1P2, Pmi_Tc1Tw4S1P3, Pmi_Tc1Tw4S1P4, Pmi_Tc1Tw4S1P5, Pmi_Tc1Tw4S2P0, Pmi_Tc1Tw4S2P1, Pmi_Tc1Tw4S2P2, Pmi_Tc1Tw4S2P3, Pmi_Tc1Tw4S2P4, Pmi_Tc1Tw4S2P5, 
        Pmi_Tc2Tw0S0P0, Pmi_Tc2Tw0S0P1, Pmi_Tc2Tw0S0P2, Pmi_Tc2Tw0S0P3, Pmi_Tc2Tw0S0P4, Pmi_Tc2Tw0S0P5, Pmi_Tc2Tw0S1P0, Pmi_Tc2Tw0S1P1, Pmi_Tc2Tw0S1P2, Pmi_Tc2Tw0S1P3, Pmi_Tc2Tw0S1P4, Pmi_Tc2Tw0S1P5, Pmi_Tc2Tw0S2P0, Pmi_Tc2Tw0S2P1, Pmi_Tc2Tw0S2P2, Pmi_Tc2Tw0S2P3, Pmi_Tc2Tw0S2P4, Pmi_Tc2Tw0S2P5, 
        Pmi_Tc2Tw1S0P0, Pmi_Tc2Tw1S0P1, Pmi_Tc2Tw1S0P2, Pmi_Tc2Tw1S0P3, Pmi_Tc2Tw1S0P4, Pmi_Tc2Tw1S0P5, Pmi_Tc2Tw1S1P0, Pmi_Tc2Tw1S1P1, Pmi_Tc2Tw1S1P2, Pmi_Tc2Tw1S1P3, Pmi_Tc2Tw1S1P4, Pmi_Tc2Tw1S1P5, Pmi_Tc2Tw1S2P0, Pmi_Tc2Tw1S2P1, Pmi_Tc2Tw1S2P2, Pmi_Tc2Tw1S2P3, Pmi_Tc2Tw1S2P4, Pmi_Tc2Tw1S2P5, 
        Pmi_Tc2Tw2S0P0, Pmi_Tc2Tw2S0P1, Pmi_Tc2Tw2S0P2, Pmi_Tc2Tw2S0P3, Pmi_Tc2Tw2S0P4, Pmi_Tc2Tw2S0P5, Pmi_Tc2Tw2S1P0, Pmi_Tc2Tw2S1P1, Pmi_Tc2Tw2S1P2, Pmi_Tc2Tw2S1P3, Pmi_Tc2Tw2S1P4, Pmi_Tc2Tw2S1P5, Pmi_Tc2Tw2S2P0, Pmi_Tc2Tw2S2P1, Pmi_Tc2Tw2S2P2, Pmi_Tc2Tw2S2P3, Pmi_Tc2Tw2S2P4, Pmi_Tc2Tw2S2P5, 
        Pmi_Tc2Tw3S0P0, Pmi_Tc2Tw3S0P1, Pmi_Tc2Tw3S0P2, Pmi_Tc2Tw3S0P3, Pmi_Tc2Tw3S0P4, Pmi_Tc2Tw3S0P5, Pmi_Tc2Tw3S1P0, Pmi_Tc2Tw3S1P1, Pmi_Tc2Tw3S1P2, Pmi_Tc2Tw3S1P3, Pmi_Tc2Tw3S1P4, Pmi_Tc2Tw3S1P5, Pmi_Tc2Tw3S2P0, Pmi_Tc2Tw3S2P1, Pmi_Tc2Tw3S2P2, Pmi_Tc2Tw3S2P3, Pmi_Tc2Tw3S2P4, Pmi_Tc2Tw3S2P5, 
        Pmi_Tc2Tw4S0P0, Pmi_Tc2Tw4S0P1, Pmi_Tc2Tw4S0P2, Pmi_Tc2Tw4S0P3, Pmi_Tc2Tw4S0P4, Pmi_Tc2Tw4S0P5, Pmi_Tc2Tw4S1P0, Pmi_Tc2Tw4S1P1, Pmi_Tc2Tw4S1P2, Pmi_Tc2Tw4S1P3, Pmi_Tc2Tw4S1P4, Pmi_Tc2Tw4S1P5, Pmi_Tc2Tw4S2P0, Pmi_Tc2Tw4S2P1, Pmi_Tc2Tw4S2P2, Pmi_Tc2Tw4S2P3, Pmi_Tc2Tw4S2P4, Pmi_Tc2Tw4S2P5, 
        Pmi_Tc3Tw0S0P0, Pmi_Tc3Tw0S0P1, Pmi_Tc3Tw0S0P2, Pmi_Tc3Tw0S0P3, Pmi_Tc3Tw0S0P4, Pmi_Tc3Tw0S0P5, Pmi_Tc3Tw0S1P0, Pmi_Tc3Tw0S1P1, Pmi_Tc3Tw0S1P2, Pmi_Tc3Tw0S1P3, Pmi_Tc3Tw0S1P4, Pmi_Tc3Tw0S1P5, Pmi_Tc3Tw0S2P0, Pmi_Tc3Tw0S2P1, Pmi_Tc3Tw0S2P2, Pmi_Tc3Tw0S2P3, Pmi_Tc3Tw0S2P4, Pmi_Tc3Tw0S2P5, 
        Pmi_Tc3Tw1S0P0, Pmi_Tc3Tw1S0P1, Pmi_Tc3Tw1S0P2, Pmi_Tc3Tw1S0P3, Pmi_Tc3Tw1S0P4, Pmi_Tc3Tw1S0P5, Pmi_Tc3Tw1S1P0, Pmi_Tc3Tw1S1P1, Pmi_Tc3Tw1S1P2, Pmi_Tc3Tw1S1P3, Pmi_Tc3Tw1S1P4, Pmi_Tc3Tw1S1P5, Pmi_Tc3Tw1S2P0, Pmi_Tc3Tw1S2P1, Pmi_Tc3Tw1S2P2, Pmi_Tc3Tw1S2P3, Pmi_Tc3Tw1S2P4, Pmi_Tc3Tw1S2P5, 
        Pmi_Tc3Tw2S0P0, Pmi_Tc3Tw2S0P1, Pmi_Tc3Tw2S0P2, Pmi_Tc3Tw2S0P3, Pmi_Tc3Tw2S0P4, Pmi_Tc3Tw2S0P5, Pmi_Tc3Tw2S1P0, Pmi_Tc3Tw2S1P1, Pmi_Tc3Tw2S1P2, Pmi_Tc3Tw2S1P3, Pmi_Tc3Tw2S1P4, Pmi_Tc3Tw2S1P5, Pmi_Tc3Tw2S2P0, Pmi_Tc3Tw2S2P1, Pmi_Tc3Tw2S2P2, Pmi_Tc3Tw2S2P3, Pmi_Tc3Tw2S2P4, Pmi_Tc3Tw2S2P5, 
        Pmi_Tc3Tw3S0P0, Pmi_Tc3Tw3S0P1, Pmi_Tc3Tw3S0P2, Pmi_Tc3Tw3S0P3, Pmi_Tc3Tw3S0P4, Pmi_Tc3Tw3S0P5, Pmi_Tc3Tw3S1P0, Pmi_Tc3Tw3S1P1, Pmi_Tc3Tw3S1P2, Pmi_Tc3Tw3S1P3, Pmi_Tc3Tw3S1P4, Pmi_Tc3Tw3S1P5, Pmi_Tc3Tw3S2P0, Pmi_Tc3Tw3S2P1, Pmi_Tc3Tw3S2P2, Pmi_Tc3Tw3S2P3, Pmi_Tc3Tw3S2P4, Pmi_Tc3Tw3S2P5, 
        Pmi_Tc3Tw4S0P0, Pmi_Tc3Tw4S0P1, Pmi_Tc3Tw4S0P2, Pmi_Tc3Tw4S0P3, Pmi_Tc3Tw4S0P4, Pmi_Tc3Tw4S0P5, Pmi_Tc3Tw4S1P0, Pmi_Tc3Tw4S1P1, Pmi_Tc3Tw4S1P2, Pmi_Tc3Tw4S1P3, Pmi_Tc3Tw4S1P4, Pmi_Tc3Tw4S1P5, Pmi_Tc3Tw4S2P0, Pmi_Tc3Tw4S2P1, Pmi_Tc3Tw4S2P2, Pmi_Tc3Tw4S2P3, Pmi_Tc3Tw4S2P4, Pmi_Tc3Tw4S2P5, 
        Pmi_Tc4Tw0S0P0, Pmi_Tc4Tw0S0P1, Pmi_Tc4Tw0S0P2, Pmi_Tc4Tw0S0P3, Pmi_Tc4Tw0S0P4, Pmi_Tc4Tw0S0P5, Pmi_Tc4Tw0S1P0, Pmi_Tc4Tw0S1P1, Pmi_Tc4Tw0S1P2, Pmi_Tc4Tw0S1P3, Pmi_Tc4Tw0S1P4, Pmi_Tc4Tw0S1P5, Pmi_Tc4Tw0S2P0, Pmi_Tc4Tw0S2P1, Pmi_Tc4Tw0S2P2, Pmi_Tc4Tw0S2P3, Pmi_Tc4Tw0S2P4, Pmi_Tc4Tw0S2P5, 
        Pmi_Tc4Tw1S0P0, Pmi_Tc4Tw1S0P1, Pmi_Tc4Tw1S0P2, Pmi_Tc4Tw1S0P3, Pmi_Tc4Tw1S0P4, Pmi_Tc4Tw1S0P5, Pmi_Tc4Tw1S1P0, Pmi_Tc4Tw1S1P1, Pmi_Tc4Tw1S1P2, Pmi_Tc4Tw1S1P3, Pmi_Tc4Tw1S1P4, Pmi_Tc4Tw1S1P5, Pmi_Tc4Tw1S2P0, Pmi_Tc4Tw1S2P1, Pmi_Tc4Tw1S2P2, Pmi_Tc4Tw1S2P3, Pmi_Tc4Tw1S2P4, Pmi_Tc4Tw1S2P5, 
        Pmi_Tc4Tw2S0P0, Pmi_Tc4Tw2S0P1, Pmi_Tc4Tw2S0P2, Pmi_Tc4Tw2S0P3, Pmi_Tc4Tw2S0P4, Pmi_Tc4Tw2S0P5, Pmi_Tc4Tw2S1P0, Pmi_Tc4Tw2S1P1, Pmi_Tc4Tw2S1P2, Pmi_Tc4Tw2S1P3, Pmi_Tc4Tw2S1P4, Pmi_Tc4Tw2S1P5, Pmi_Tc4Tw2S2P0, Pmi_Tc4Tw2S2P1, Pmi_Tc4Tw2S2P2, Pmi_Tc4Tw2S2P3, Pmi_Tc4Tw2S2P4, Pmi_Tc4Tw2S2P5, 
        Pmi_Tc4Tw3S0P0, Pmi_Tc4Tw3S0P1, Pmi_Tc4Tw3S0P2, Pmi_Tc4Tw3S0P3, Pmi_Tc4Tw3S0P4, Pmi_Tc4Tw3S0P5, Pmi_Tc4Tw3S1P0, Pmi_Tc4Tw3S1P1, Pmi_Tc4Tw3S1P2, Pmi_Tc4Tw3S1P3, Pmi_Tc4Tw3S1P4, Pmi_Tc4Tw3S1P5, Pmi_Tc4Tw3S2P0, Pmi_Tc4Tw3S2P1, Pmi_Tc4Tw3S2P2, Pmi_Tc4Tw3S2P3, Pmi_Tc4Tw3S2P4, Pmi_Tc4Tw3S2P5, 
        Pmi_Tc4Tw4S0P0, Pmi_Tc4Tw4S0P1, Pmi_Tc4Tw4S0P2, Pmi_Tc4Tw4S0P3, Pmi_Tc4Tw4S0P4, Pmi_Tc4Tw4S0P5, Pmi_Tc4Tw4S1P0, Pmi_Tc4Tw4S1P1, Pmi_Tc4Tw4S1P2, Pmi_Tc4Tw4S1P3, Pmi_Tc4Tw4S1P4, Pmi_Tc4Tw4S1P5, Pmi_Tc4Tw4S2P0, Pmi_Tc4Tw4S2P1, Pmi_Tc4Tw4S2P2, Pmi_Tc4Tw4S2P3, Pmi_Tc4Tw4S2P4, Pmi_Tc4Tw4S2P5, 
        PmiAl_Tc0Tw0S0P0, PmiAl_Tc0Tw0S0P1, PmiAl_Tc0Tw0S0P2, PmiAl_Tc0Tw0S0P3, PmiAl_Tc0Tw0S0P4, PmiAl_Tc0Tw0S0P5, PmiAl_Tc0Tw0S1P0, PmiAl_Tc0Tw0S1P1, PmiAl_Tc0Tw0S1P2, PmiAl_Tc0Tw0S1P3, PmiAl_Tc0Tw0S1P4, PmiAl_Tc0Tw0S1P5, PmiAl_Tc0Tw0S2P0, PmiAl_Tc0Tw0S2P1, PmiAl_Tc0Tw0S2P2, PmiAl_Tc0Tw0S2P3, PmiAl_Tc0Tw0S2P4, PmiAl_Tc0Tw0S2P5, 
        PmiAl_Tc0Tw1S0P0, PmiAl_Tc0Tw1S0P1, PmiAl_Tc0Tw1S0P2, PmiAl_Tc0Tw1S0P3, PmiAl_Tc0Tw1S0P4, PmiAl_Tc0Tw1S0P5, PmiAl_Tc0Tw1S1P0, PmiAl_Tc0Tw1S1P1, PmiAl_Tc0Tw1S1P2, PmiAl_Tc0Tw1S1P3, PmiAl_Tc0Tw1S1P4, PmiAl_Tc0Tw1S1P5, PmiAl_Tc0Tw1S2P0, PmiAl_Tc0Tw1S2P1, PmiAl_Tc0Tw1S2P2, PmiAl_Tc0Tw1S2P3, PmiAl_Tc0Tw1S2P4, PmiAl_Tc0Tw1S2P5, 
        PmiAl_Tc0Tw2S0P0, PmiAl_Tc0Tw2S0P1, PmiAl_Tc0Tw2S0P2, PmiAl_Tc0Tw2S0P3, PmiAl_Tc0Tw2S0P4, PmiAl_Tc0Tw2S0P5, PmiAl_Tc0Tw2S1P0, PmiAl_Tc0Tw2S1P1, PmiAl_Tc0Tw2S1P2, PmiAl_Tc0Tw2S1P3, PmiAl_Tc0Tw2S1P4, PmiAl_Tc0Tw2S1P5, PmiAl_Tc0Tw2S2P0, PmiAl_Tc0Tw2S2P1, PmiAl_Tc0Tw2S2P2, PmiAl_Tc0Tw2S2P3, PmiAl_Tc0Tw2S2P4, PmiAl_Tc0Tw2S2P5, 
        PmiAl_Tc0Tw3S0P0, PmiAl_Tc0Tw3S0P1, PmiAl_Tc0Tw3S0P2, PmiAl_Tc0Tw3S0P3, PmiAl_Tc0Tw3S0P4, PmiAl_Tc0Tw3S0P5, PmiAl_Tc0Tw3S1P0, PmiAl_Tc0Tw3S1P1, PmiAl_Tc0Tw3S1P2, PmiAl_Tc0Tw3S1P3, PmiAl_Tc0Tw3S1P4, PmiAl_Tc0Tw3S1P5, PmiAl_Tc0Tw3S2P0, PmiAl_Tc0Tw3S2P1, PmiAl_Tc0Tw3S2P2, PmiAl_Tc0Tw3S2P3, PmiAl_Tc0Tw3S2P4, PmiAl_Tc0Tw3S2P5, 
        PmiAl_Tc0Tw4S0P0, PmiAl_Tc0Tw4S0P1, PmiAl_Tc0Tw4S0P2, PmiAl_Tc0Tw4S0P3, PmiAl_Tc0Tw4S0P4, PmiAl_Tc0Tw4S0P5, PmiAl_Tc0Tw4S1P0, PmiAl_Tc0Tw4S1P1, PmiAl_Tc0Tw4S1P2, PmiAl_Tc0Tw4S1P3, PmiAl_Tc0Tw4S1P4, PmiAl_Tc0Tw4S1P5, PmiAl_Tc0Tw4S2P0, PmiAl_Tc0Tw4S2P1, PmiAl_Tc0Tw4S2P2, PmiAl_Tc0Tw4S2P3, PmiAl_Tc0Tw4S2P4, PmiAl_Tc0Tw4S2P5, 
        PmiAl_Tc1Tw0S0P0, PmiAl_Tc1Tw0S0P1, PmiAl_Tc1Tw0S0P2, PmiAl_Tc1Tw0S0P3, PmiAl_Tc1Tw0S0P4, PmiAl_Tc1Tw0S0P5, PmiAl_Tc1Tw0S1P0, PmiAl_Tc1Tw0S1P1, PmiAl_Tc1Tw0S1P2, PmiAl_Tc1Tw0S1P3, PmiAl_Tc1Tw0S1P4, PmiAl_Tc1Tw0S1P5, PmiAl_Tc1Tw0S2P0, PmiAl_Tc1Tw0S2P1, PmiAl_Tc1Tw0S2P2, PmiAl_Tc1Tw0S2P3, PmiAl_Tc1Tw0S2P4, PmiAl_Tc1Tw0S2P5, 
        PmiAl_Tc1Tw1S0P0, PmiAl_Tc1Tw1S0P1, PmiAl_Tc1Tw1S0P2, PmiAl_Tc1Tw1S0P3, PmiAl_Tc1Tw1S0P4, PmiAl_Tc1Tw1S0P5, PmiAl_Tc1Tw1S1P0, PmiAl_Tc1Tw1S1P1, PmiAl_Tc1Tw1S1P2, PmiAl_Tc1Tw1S1P3, PmiAl_Tc1Tw1S1P4, PmiAl_Tc1Tw1S1P5, PmiAl_Tc1Tw1S2P0, PmiAl_Tc1Tw1S2P1, PmiAl_Tc1Tw1S2P2, PmiAl_Tc1Tw1S2P3, PmiAl_Tc1Tw1S2P4, PmiAl_Tc1Tw1S2P5, 
        PmiAl_Tc1Tw2S0P0, PmiAl_Tc1Tw2S0P1, PmiAl_Tc1Tw2S0P2, PmiAl_Tc1Tw2S0P3, PmiAl_Tc1Tw2S0P4, PmiAl_Tc1Tw2S0P5, PmiAl_Tc1Tw2S1P0, PmiAl_Tc1Tw2S1P1, PmiAl_Tc1Tw2S1P2, PmiAl_Tc1Tw2S1P3, PmiAl_Tc1Tw2S1P4, PmiAl_Tc1Tw2S1P5, PmiAl_Tc1Tw2S2P0, PmiAl_Tc1Tw2S2P1, PmiAl_Tc1Tw2S2P2, PmiAl_Tc1Tw2S2P3, PmiAl_Tc1Tw2S2P4, PmiAl_Tc1Tw2S2P5, 
        PmiAl_Tc1Tw3S0P0, PmiAl_Tc1Tw3S0P1, PmiAl_Tc1Tw3S0P2, PmiAl_Tc1Tw3S0P3, PmiAl_Tc1Tw3S0P4, PmiAl_Tc1Tw3S0P5, PmiAl_Tc1Tw3S1P0, PmiAl_Tc1Tw3S1P1, PmiAl_Tc1Tw3S1P2, PmiAl_Tc1Tw3S1P3, PmiAl_Tc1Tw3S1P4, PmiAl_Tc1Tw3S1P5, PmiAl_Tc1Tw3S2P0, PmiAl_Tc1Tw3S2P1, PmiAl_Tc1Tw3S2P2, PmiAl_Tc1Tw3S2P3, PmiAl_Tc1Tw3S2P4, PmiAl_Tc1Tw3S2P5, 
        PmiAl_Tc1Tw4S0P0, PmiAl_Tc1Tw4S0P1, PmiAl_Tc1Tw4S0P2, PmiAl_Tc1Tw4S0P3, PmiAl_Tc1Tw4S0P4, PmiAl_Tc1Tw4S0P5, PmiAl_Tc1Tw4S1P0, PmiAl_Tc1Tw4S1P1, PmiAl_Tc1Tw4S1P2, PmiAl_Tc1Tw4S1P3, PmiAl_Tc1Tw4S1P4, PmiAl_Tc1Tw4S1P5, PmiAl_Tc1Tw4S2P0, PmiAl_Tc1Tw4S2P1, PmiAl_Tc1Tw4S2P2, PmiAl_Tc1Tw4S2P3, PmiAl_Tc1Tw4S2P4, PmiAl_Tc1Tw4S2P5, 
        PmiAl_Tc2Tw0S0P0, PmiAl_Tc2Tw0S0P1, PmiAl_Tc2Tw0S0P2, PmiAl_Tc2Tw0S0P3, PmiAl_Tc2Tw0S0P4, PmiAl_Tc2Tw0S0P5, PmiAl_Tc2Tw0S1P0, PmiAl_Tc2Tw0S1P1, PmiAl_Tc2Tw0S1P2, PmiAl_Tc2Tw0S1P3, PmiAl_Tc2Tw0S1P4, PmiAl_Tc2Tw0S1P5, PmiAl_Tc2Tw0S2P0, PmiAl_Tc2Tw0S2P1, PmiAl_Tc2Tw0S2P2, PmiAl_Tc2Tw0S2P3, PmiAl_Tc2Tw0S2P4, PmiAl_Tc2Tw0S2P5, 
        PmiAl_Tc2Tw1S0P0, PmiAl_Tc2Tw1S0P1, PmiAl_Tc2Tw1S0P2, PmiAl_Tc2Tw1S0P3, PmiAl_Tc2Tw1S0P4, PmiAl_Tc2Tw1S0P5, PmiAl_Tc2Tw1S1P0, PmiAl_Tc2Tw1S1P1, PmiAl_Tc2Tw1S1P2, PmiAl_Tc2Tw1S1P3, PmiAl_Tc2Tw1S1P4, PmiAl_Tc2Tw1S1P5, PmiAl_Tc2Tw1S2P0, PmiAl_Tc2Tw1S2P1, PmiAl_Tc2Tw1S2P2, PmiAl_Tc2Tw1S2P3, PmiAl_Tc2Tw1S2P4, PmiAl_Tc2Tw1S2P5, 
        PmiAl_Tc2Tw2S0P0, PmiAl_Tc2Tw2S0P1, PmiAl_Tc2Tw2S0P2, PmiAl_Tc2Tw2S0P3, PmiAl_Tc2Tw2S0P4, PmiAl_Tc2Tw2S0P5, PmiAl_Tc2Tw2S1P0, PmiAl_Tc2Tw2S1P1, PmiAl_Tc2Tw2S1P2, PmiAl_Tc2Tw2S1P3, PmiAl_Tc2Tw2S1P4, PmiAl_Tc2Tw2S1P5, PmiAl_Tc2Tw2S2P0, PmiAl_Tc2Tw2S2P1, PmiAl_Tc2Tw2S2P2, PmiAl_Tc2Tw2S2P3, PmiAl_Tc2Tw2S2P4, PmiAl_Tc2Tw2S2P5, 
        PmiAl_Tc2Tw3S0P0, PmiAl_Tc2Tw3S0P1, PmiAl_Tc2Tw3S0P2, PmiAl_Tc2Tw3S0P3, PmiAl_Tc2Tw3S0P4, PmiAl_Tc2Tw3S0P5, PmiAl_Tc2Tw3S1P0, PmiAl_Tc2Tw3S1P1, PmiAl_Tc2Tw3S1P2, PmiAl_Tc2Tw3S1P3, PmiAl_Tc2Tw3S1P4, PmiAl_Tc2Tw3S1P5, PmiAl_Tc2Tw3S2P0, PmiAl_Tc2Tw3S2P1, PmiAl_Tc2Tw3S2P2, PmiAl_Tc2Tw3S2P3, PmiAl_Tc2Tw3S2P4, PmiAl_Tc2Tw3S2P5, 
        PmiAl_Tc2Tw4S0P0, PmiAl_Tc2Tw4S0P1, PmiAl_Tc2Tw4S0P2, PmiAl_Tc2Tw4S0P3, PmiAl_Tc2Tw4S0P4, PmiAl_Tc2Tw4S0P5, PmiAl_Tc2Tw4S1P0, PmiAl_Tc2Tw4S1P1, PmiAl_Tc2Tw4S1P2, PmiAl_Tc2Tw4S1P3, PmiAl_Tc2Tw4S1P4, PmiAl_Tc2Tw4S1P5, PmiAl_Tc2Tw4S2P0, PmiAl_Tc2Tw4S2P1, PmiAl_Tc2Tw4S2P2, PmiAl_Tc2Tw4S2P3, PmiAl_Tc2Tw4S2P4, PmiAl_Tc2Tw4S2P5, 
        PmiAl_Tc3Tw0S0P0, PmiAl_Tc3Tw0S0P1, PmiAl_Tc3Tw0S0P2, PmiAl_Tc3Tw0S0P3, PmiAl_Tc3Tw0S0P4, PmiAl_Tc3Tw0S0P5, PmiAl_Tc3Tw0S1P0, PmiAl_Tc3Tw0S1P1, PmiAl_Tc3Tw0S1P2, PmiAl_Tc3Tw0S1P3, PmiAl_Tc3Tw0S1P4, PmiAl_Tc3Tw0S1P5, PmiAl_Tc3Tw0S2P0, PmiAl_Tc3Tw0S2P1, PmiAl_Tc3Tw0S2P2, PmiAl_Tc3Tw0S2P3, PmiAl_Tc3Tw0S2P4, PmiAl_Tc3Tw0S2P5, 
        PmiAl_Tc3Tw1S0P0, PmiAl_Tc3Tw1S0P1, PmiAl_Tc3Tw1S0P2, PmiAl_Tc3Tw1S0P3, PmiAl_Tc3Tw1S0P4, PmiAl_Tc3Tw1S0P5, PmiAl_Tc3Tw1S1P0, PmiAl_Tc3Tw1S1P1, PmiAl_Tc3Tw1S1P2, PmiAl_Tc3Tw1S1P3, PmiAl_Tc3Tw1S1P4, PmiAl_Tc3Tw1S1P5, PmiAl_Tc3Tw1S2P0, PmiAl_Tc3Tw1S2P1, PmiAl_Tc3Tw1S2P2, PmiAl_Tc3Tw1S2P3, PmiAl_Tc3Tw1S2P4, PmiAl_Tc3Tw1S2P5, 
        PmiAl_Tc3Tw2S0P0, PmiAl_Tc3Tw2S0P1, PmiAl_Tc3Tw2S0P2, PmiAl_Tc3Tw2S0P3, PmiAl_Tc3Tw2S0P4, PmiAl_Tc3Tw2S0P5, PmiAl_Tc3Tw2S1P0, PmiAl_Tc3Tw2S1P1, PmiAl_Tc3Tw2S1P2, PmiAl_Tc3Tw2S1P3, PmiAl_Tc3Tw2S1P4, PmiAl_Tc3Tw2S1P5, PmiAl_Tc3Tw2S2P0, PmiAl_Tc3Tw2S2P1, PmiAl_Tc3Tw2S2P2, PmiAl_Tc3Tw2S2P3, PmiAl_Tc3Tw2S2P4, PmiAl_Tc3Tw2S2P5, 
        PmiAl_Tc3Tw3S0P0, PmiAl_Tc3Tw3S0P1, PmiAl_Tc3Tw3S0P2, PmiAl_Tc3Tw3S0P3, PmiAl_Tc3Tw3S0P4, PmiAl_Tc3Tw3S0P5, PmiAl_Tc3Tw3S1P0, PmiAl_Tc3Tw3S1P1, PmiAl_Tc3Tw3S1P2, PmiAl_Tc3Tw3S1P3, PmiAl_Tc3Tw3S1P4, PmiAl_Tc3Tw3S1P5, PmiAl_Tc3Tw3S2P0, PmiAl_Tc3Tw3S2P1, PmiAl_Tc3Tw3S2P2, PmiAl_Tc3Tw3S2P3, PmiAl_Tc3Tw3S2P4, PmiAl_Tc3Tw3S2P5, 
        PmiAl_Tc3Tw4S0P0, PmiAl_Tc3Tw4S0P1, PmiAl_Tc3Tw4S0P2, PmiAl_Tc3Tw4S0P3, PmiAl_Tc3Tw4S0P4, PmiAl_Tc3Tw4S0P5, PmiAl_Tc3Tw4S1P0, PmiAl_Tc3Tw4S1P1, PmiAl_Tc3Tw4S1P2, PmiAl_Tc3Tw4S1P3, PmiAl_Tc3Tw4S1P4, PmiAl_Tc3Tw4S1P5, PmiAl_Tc3Tw4S2P0, PmiAl_Tc3Tw4S2P1, PmiAl_Tc3Tw4S2P2, PmiAl_Tc3Tw4S2P3, PmiAl_Tc3Tw4S2P4, PmiAl_Tc3Tw4S2P5, 
        PmiAl_Tc4Tw0S0P0, PmiAl_Tc4Tw0S0P1, PmiAl_Tc4Tw0S0P2, PmiAl_Tc4Tw0S0P3, PmiAl_Tc4Tw0S0P4, PmiAl_Tc4Tw0S0P5, PmiAl_Tc4Tw0S1P0, PmiAl_Tc4Tw0S1P1, PmiAl_Tc4Tw0S1P2, PmiAl_Tc4Tw0S1P3, PmiAl_Tc4Tw0S1P4, PmiAl_Tc4Tw0S1P5, PmiAl_Tc4Tw0S2P0, PmiAl_Tc4Tw0S2P1, PmiAl_Tc4Tw0S2P2, PmiAl_Tc4Tw0S2P3, PmiAl_Tc4Tw0S2P4, PmiAl_Tc4Tw0S2P5, 
        PmiAl_Tc4Tw1S0P0, PmiAl_Tc4Tw1S0P1, PmiAl_Tc4Tw1S0P2, PmiAl_Tc4Tw1S0P3, PmiAl_Tc4Tw1S0P4, PmiAl_Tc4Tw1S0P5, PmiAl_Tc4Tw1S1P0, PmiAl_Tc4Tw1S1P1, PmiAl_Tc4Tw1S1P2, PmiAl_Tc4Tw1S1P3, PmiAl_Tc4Tw1S1P4, PmiAl_Tc4Tw1S1P5, PmiAl_Tc4Tw1S2P0, PmiAl_Tc4Tw1S2P1, PmiAl_Tc4Tw1S2P2, PmiAl_Tc4Tw1S2P3, PmiAl_Tc4Tw1S2P4, PmiAl_Tc4Tw1S2P5, 
        PmiAl_Tc4Tw2S0P0, PmiAl_Tc4Tw2S0P1, PmiAl_Tc4Tw2S0P2, PmiAl_Tc4Tw2S0P3, PmiAl_Tc4Tw2S0P4, PmiAl_Tc4Tw2S0P5, PmiAl_Tc4Tw2S1P0, PmiAl_Tc4Tw2S1P1, PmiAl_Tc4Tw2S1P2, PmiAl_Tc4Tw2S1P3, PmiAl_Tc4Tw2S1P4, PmiAl_Tc4Tw2S1P5, PmiAl_Tc4Tw2S2P0, PmiAl_Tc4Tw2S2P1, PmiAl_Tc4Tw2S2P2, PmiAl_Tc4Tw2S2P3, PmiAl_Tc4Tw2S2P4, PmiAl_Tc4Tw2S2P5, 
        PmiAl_Tc4Tw3S0P0, PmiAl_Tc4Tw3S0P1, PmiAl_Tc4Tw3S0P2, PmiAl_Tc4Tw3S0P3, PmiAl_Tc4Tw3S0P4, PmiAl_Tc4Tw3S0P5, PmiAl_Tc4Tw3S1P0, PmiAl_Tc4Tw3S1P1, PmiAl_Tc4Tw3S1P2, PmiAl_Tc4Tw3S1P3, PmiAl_Tc4Tw3S1P4, PmiAl_Tc4Tw3S1P5, PmiAl_Tc4Tw3S2P0, PmiAl_Tc4Tw3S2P1, PmiAl_Tc4Tw3S2P2, PmiAl_Tc4Tw3S2P3, PmiAl_Tc4Tw3S2P4, PmiAl_Tc4Tw3S2P5, 
        PmiAl_Tc4Tw4S0P0, PmiAl_Tc4Tw4S0P1, PmiAl_Tc4Tw4S0P2, PmiAl_Tc4Tw4S0P3, PmiAl_Tc4Tw4S0P4, PmiAl_Tc4Tw4S0P5, PmiAl_Tc4Tw4S1P0, PmiAl_Tc4Tw4S1P1, PmiAl_Tc4Tw4S1P2, PmiAl_Tc4Tw4S1P3, PmiAl_Tc4Tw4S1P4, PmiAl_Tc4Tw4S1P5, PmiAl_Tc4Tw4S2P0, PmiAl_Tc4Tw4S2P1, PmiAl_Tc4Tw4S2P2, PmiAl_Tc4Tw4S2P3, PmiAl_Tc4Tw4S2P4, PmiAl_Tc4Tw4S2P5, 
        Unis_Tc0Tw0S0P0, Unis_Tc0Tw0S0P1, Unis_Tc0Tw0S0P2, Unis_Tc0Tw0S0P3, Unis_Tc0Tw0S0P4, Unis_Tc0Tw0S0P5, Unis_Tc0Tw0S1P0, Unis_Tc0Tw0S1P1, Unis_Tc0Tw0S1P2, Unis_Tc0Tw0S1P3, Unis_Tc0Tw0S1P4, Unis_Tc0Tw0S1P5, Unis_Tc0Tw0S2P0, Unis_Tc0Tw0S2P1, Unis_Tc0Tw0S2P2, Unis_Tc0Tw0S2P3, Unis_Tc0Tw0S2P4, Unis_Tc0Tw0S2P5, 
        Unis_Tc0Tw1S0P0, Unis_Tc0Tw1S0P1, Unis_Tc0Tw1S0P2, Unis_Tc0Tw1S0P3, Unis_Tc0Tw1S0P4, Unis_Tc0Tw1S0P5, Unis_Tc0Tw1S1P0, Unis_Tc0Tw1S1P1, Unis_Tc0Tw1S1P2, Unis_Tc0Tw1S1P3, Unis_Tc0Tw1S1P4, Unis_Tc0Tw1S1P5, Unis_Tc0Tw1S2P0, Unis_Tc0Tw1S2P1, Unis_Tc0Tw1S2P2, Unis_Tc0Tw1S2P3, Unis_Tc0Tw1S2P4, Unis_Tc0Tw1S2P5, 
        Unis_Tc0Tw2S0P0, Unis_Tc0Tw2S0P1, Unis_Tc0Tw2S0P2, Unis_Tc0Tw2S0P3, Unis_Tc0Tw2S0P4, Unis_Tc0Tw2S0P5, Unis_Tc0Tw2S1P0, Unis_Tc0Tw2S1P1, Unis_Tc0Tw2S1P2, Unis_Tc0Tw2S1P3, Unis_Tc0Tw2S1P4, Unis_Tc0Tw2S1P5, Unis_Tc0Tw2S2P0, Unis_Tc0Tw2S2P1, Unis_Tc0Tw2S2P2, Unis_Tc0Tw2S2P3, Unis_Tc0Tw2S2P4, Unis_Tc0Tw2S2P5, 
        Unis_Tc0Tw3S0P0, Unis_Tc0Tw3S0P1, Unis_Tc0Tw3S0P2, Unis_Tc0Tw3S0P3, Unis_Tc0Tw3S0P4, Unis_Tc0Tw3S0P5, Unis_Tc0Tw3S1P0, Unis_Tc0Tw3S1P1, Unis_Tc0Tw3S1P2, Unis_Tc0Tw3S1P3, Unis_Tc0Tw3S1P4, Unis_Tc0Tw3S1P5, Unis_Tc0Tw3S2P0, Unis_Tc0Tw3S2P1, Unis_Tc0Tw3S2P2, Unis_Tc0Tw3S2P3, Unis_Tc0Tw3S2P4, Unis_Tc0Tw3S2P5, 
        Unis_Tc0Tw4S0P0, Unis_Tc0Tw4S0P1, Unis_Tc0Tw4S0P2, Unis_Tc0Tw4S0P3, Unis_Tc0Tw4S0P4, Unis_Tc0Tw4S0P5, Unis_Tc0Tw4S1P0, Unis_Tc0Tw4S1P1, Unis_Tc0Tw4S1P2, Unis_Tc0Tw4S1P3, Unis_Tc0Tw4S1P4, Unis_Tc0Tw4S1P5, Unis_Tc0Tw4S2P0, Unis_Tc0Tw4S2P1, Unis_Tc0Tw4S2P2, Unis_Tc0Tw4S2P3, Unis_Tc0Tw4S2P4, Unis_Tc0Tw4S2P5, 
        Unis_Tc1Tw0S0P0, Unis_Tc1Tw0S0P1, Unis_Tc1Tw0S0P2, Unis_Tc1Tw0S0P3, Unis_Tc1Tw0S0P4, Unis_Tc1Tw0S0P5, Unis_Tc1Tw0S1P0, Unis_Tc1Tw0S1P1, Unis_Tc1Tw0S1P2, Unis_Tc1Tw0S1P3, Unis_Tc1Tw0S1P4, Unis_Tc1Tw0S1P5, Unis_Tc1Tw0S2P0, Unis_Tc1Tw0S2P1, Unis_Tc1Tw0S2P2, Unis_Tc1Tw0S2P3, Unis_Tc1Tw0S2P4, Unis_Tc1Tw0S2P5, 
        Unis_Tc1Tw1S0P0, Unis_Tc1Tw1S0P1, Unis_Tc1Tw1S0P2, Unis_Tc1Tw1S0P3, Unis_Tc1Tw1S0P4, Unis_Tc1Tw1S0P5, Unis_Tc1Tw1S1P0, Unis_Tc1Tw1S1P1, Unis_Tc1Tw1S1P2, Unis_Tc1Tw1S1P3, Unis_Tc1Tw1S1P4, Unis_Tc1Tw1S1P5, Unis_Tc1Tw1S2P0, Unis_Tc1Tw1S2P1, Unis_Tc1Tw1S2P2, Unis_Tc1Tw1S2P3, Unis_Tc1Tw1S2P4, Unis_Tc1Tw1S2P5, 
        Unis_Tc1Tw2S0P0, Unis_Tc1Tw2S0P1, Unis_Tc1Tw2S0P2, Unis_Tc1Tw2S0P3, Unis_Tc1Tw2S0P4, Unis_Tc1Tw2S0P5, Unis_Tc1Tw2S1P0, Unis_Tc1Tw2S1P1, Unis_Tc1Tw2S1P2, Unis_Tc1Tw2S1P3, Unis_Tc1Tw2S1P4, Unis_Tc1Tw2S1P5, Unis_Tc1Tw2S2P0, Unis_Tc1Tw2S2P1, Unis_Tc1Tw2S2P2, Unis_Tc1Tw2S2P3, Unis_Tc1Tw2S2P4, Unis_Tc1Tw2S2P5, 
        Unis_Tc1Tw3S0P0, Unis_Tc1Tw3S0P1, Unis_Tc1Tw3S0P2, Unis_Tc1Tw3S0P3, Unis_Tc1Tw3S0P4, Unis_Tc1Tw3S0P5, Unis_Tc1Tw3S1P0, Unis_Tc1Tw3S1P1, Unis_Tc1Tw3S1P2, Unis_Tc1Tw3S1P3, Unis_Tc1Tw3S1P4, Unis_Tc1Tw3S1P5, Unis_Tc1Tw3S2P0, Unis_Tc1Tw3S2P1, Unis_Tc1Tw3S2P2, Unis_Tc1Tw3S2P3, Unis_Tc1Tw3S2P4, Unis_Tc1Tw3S2P5, 
        Unis_Tc1Tw4S0P0, Unis_Tc1Tw4S0P1, Unis_Tc1Tw4S0P2, Unis_Tc1Tw4S0P3, Unis_Tc1Tw4S0P4, Unis_Tc1Tw4S0P5, Unis_Tc1Tw4S1P0, Unis_Tc1Tw4S1P1, Unis_Tc1Tw4S1P2, Unis_Tc1Tw4S1P3, Unis_Tc1Tw4S1P4, Unis_Tc1Tw4S1P5, Unis_Tc1Tw4S2P0, Unis_Tc1Tw4S2P1, Unis_Tc1Tw4S2P2, Unis_Tc1Tw4S2P3, Unis_Tc1Tw4S2P4, Unis_Tc1Tw4S2P5, 
        Unis_Tc2Tw0S0P0, Unis_Tc2Tw0S0P1, Unis_Tc2Tw0S0P2, Unis_Tc2Tw0S0P3, Unis_Tc2Tw0S0P4, Unis_Tc2Tw0S0P5, Unis_Tc2Tw0S1P0, Unis_Tc2Tw0S1P1, Unis_Tc2Tw0S1P2, Unis_Tc2Tw0S1P3, Unis_Tc2Tw0S1P4, Unis_Tc2Tw0S1P5, Unis_Tc2Tw0S2P0, Unis_Tc2Tw0S2P1, Unis_Tc2Tw0S2P2, Unis_Tc2Tw0S2P3, Unis_Tc2Tw0S2P4, Unis_Tc2Tw0S2P5, 
        Unis_Tc2Tw1S0P0, Unis_Tc2Tw1S0P1, Unis_Tc2Tw1S0P2, Unis_Tc2Tw1S0P3, Unis_Tc2Tw1S0P4, Unis_Tc2Tw1S0P5, Unis_Tc2Tw1S1P0, Unis_Tc2Tw1S1P1, Unis_Tc2Tw1S1P2, Unis_Tc2Tw1S1P3, Unis_Tc2Tw1S1P4, Unis_Tc2Tw1S1P5, Unis_Tc2Tw1S2P0, Unis_Tc2Tw1S2P1, Unis_Tc2Tw1S2P2, Unis_Tc2Tw1S2P3, Unis_Tc2Tw1S2P4, Unis_Tc2Tw1S2P5, 
        Unis_Tc2Tw2S0P0, Unis_Tc2Tw2S0P1, Unis_Tc2Tw2S0P2, Unis_Tc2Tw2S0P3, Unis_Tc2Tw2S0P4, Unis_Tc2Tw2S0P5, Unis_Tc2Tw2S1P0, Unis_Tc2Tw2S1P1, Unis_Tc2Tw2S1P2, Unis_Tc2Tw2S1P3, Unis_Tc2Tw2S1P4, Unis_Tc2Tw2S1P5, Unis_Tc2Tw2S2P0, Unis_Tc2Tw2S2P1, Unis_Tc2Tw2S2P2, Unis_Tc2Tw2S2P3, Unis_Tc2Tw2S2P4, Unis_Tc2Tw2S2P5, 
        Unis_Tc2Tw3S0P0, Unis_Tc2Tw3S0P1, Unis_Tc2Tw3S0P2, Unis_Tc2Tw3S0P3, Unis_Tc2Tw3S0P4, Unis_Tc2Tw3S0P5, Unis_Tc2Tw3S1P0, Unis_Tc2Tw3S1P1, Unis_Tc2Tw3S1P2, Unis_Tc2Tw3S1P3, Unis_Tc2Tw3S1P4, Unis_Tc2Tw3S1P5, Unis_Tc2Tw3S2P0, Unis_Tc2Tw3S2P1, Unis_Tc2Tw3S2P2, Unis_Tc2Tw3S2P3, Unis_Tc2Tw3S2P4, Unis_Tc2Tw3S2P5, 
        Unis_Tc2Tw4S0P0, Unis_Tc2Tw4S0P1, Unis_Tc2Tw4S0P2, Unis_Tc2Tw4S0P3, Unis_Tc2Tw4S0P4, Unis_Tc2Tw4S0P5, Unis_Tc2Tw4S1P0, Unis_Tc2Tw4S1P1, Unis_Tc2Tw4S1P2, Unis_Tc2Tw4S1P3, Unis_Tc2Tw4S1P4, Unis_Tc2Tw4S1P5, Unis_Tc2Tw4S2P0, Unis_Tc2Tw4S2P1, Unis_Tc2Tw4S2P2, Unis_Tc2Tw4S2P3, Unis_Tc2Tw4S2P4, Unis_Tc2Tw4S2P5, 
        Unis_Tc3Tw0S0P0, Unis_Tc3Tw0S0P1, Unis_Tc3Tw0S0P2, Unis_Tc3Tw0S0P3, Unis_Tc3Tw0S0P4, Unis_Tc3Tw0S0P5, Unis_Tc3Tw0S1P0, Unis_Tc3Tw0S1P1, Unis_Tc3Tw0S1P2, Unis_Tc3Tw0S1P3, Unis_Tc3Tw0S1P4, Unis_Tc3Tw0S1P5, Unis_Tc3Tw0S2P0, Unis_Tc3Tw0S2P1, Unis_Tc3Tw0S2P2, Unis_Tc3Tw0S2P3, Unis_Tc3Tw0S2P4, Unis_Tc3Tw0S2P5, 
        Unis_Tc3Tw1S0P0, Unis_Tc3Tw1S0P1, Unis_Tc3Tw1S0P2, Unis_Tc3Tw1S0P3, Unis_Tc3Tw1S0P4, Unis_Tc3Tw1S0P5, Unis_Tc3Tw1S1P0, Unis_Tc3Tw1S1P1, Unis_Tc3Tw1S1P2, Unis_Tc3Tw1S1P3, Unis_Tc3Tw1S1P4, Unis_Tc3Tw1S1P5, Unis_Tc3Tw1S2P0, Unis_Tc3Tw1S2P1, Unis_Tc3Tw1S2P2, Unis_Tc3Tw1S2P3, Unis_Tc3Tw1S2P4, Unis_Tc3Tw1S2P5, 
        Unis_Tc3Tw2S0P0, Unis_Tc3Tw2S0P1, Unis_Tc3Tw2S0P2, Unis_Tc3Tw2S0P3, Unis_Tc3Tw2S0P4, Unis_Tc3Tw2S0P5, Unis_Tc3Tw2S1P0, Unis_Tc3Tw2S1P1, Unis_Tc3Tw2S1P2, Unis_Tc3Tw2S1P3, Unis_Tc3Tw2S1P4, Unis_Tc3Tw2S1P5, Unis_Tc3Tw2S2P0, Unis_Tc3Tw2S2P1, Unis_Tc3Tw2S2P2, Unis_Tc3Tw2S2P3, Unis_Tc3Tw2S2P4, Unis_Tc3Tw2S2P5, 
        Unis_Tc3Tw3S0P0, Unis_Tc3Tw3S0P1, Unis_Tc3Tw3S0P2, Unis_Tc3Tw3S0P3, Unis_Tc3Tw3S0P4, Unis_Tc3Tw3S0P5, Unis_Tc3Tw3S1P0, Unis_Tc3Tw3S1P1, Unis_Tc3Tw3S1P2, Unis_Tc3Tw3S1P3, Unis_Tc3Tw3S1P4, Unis_Tc3Tw3S1P5, Unis_Tc3Tw3S2P0, Unis_Tc3Tw3S2P1, Unis_Tc3Tw3S2P2, Unis_Tc3Tw3S2P3, Unis_Tc3Tw3S2P4, Unis_Tc3Tw3S2P5, 
        Unis_Tc3Tw4S0P0, Unis_Tc3Tw4S0P1, Unis_Tc3Tw4S0P2, Unis_Tc3Tw4S0P3, Unis_Tc3Tw4S0P4, Unis_Tc3Tw4S0P5, Unis_Tc3Tw4S1P0, Unis_Tc3Tw4S1P1, Unis_Tc3Tw4S1P2, Unis_Tc3Tw4S1P3, Unis_Tc3Tw4S1P4, Unis_Tc3Tw4S1P5, Unis_Tc3Tw4S2P0, Unis_Tc3Tw4S2P1, Unis_Tc3Tw4S2P2, Unis_Tc3Tw4S2P3, Unis_Tc3Tw4S2P4, Unis_Tc3Tw4S2P5, 
        Unis_Tc4Tw0S0P0, Unis_Tc4Tw0S0P1, Unis_Tc4Tw0S0P2, Unis_Tc4Tw0S0P3, Unis_Tc4Tw0S0P4, Unis_Tc4Tw0S0P5, Unis_Tc4Tw0S1P0, Unis_Tc4Tw0S1P1, Unis_Tc4Tw0S1P2, Unis_Tc4Tw0S1P3, Unis_Tc4Tw0S1P4, Unis_Tc4Tw0S1P5, Unis_Tc4Tw0S2P0, Unis_Tc4Tw0S2P1, Unis_Tc4Tw0S2P2, Unis_Tc4Tw0S2P3, Unis_Tc4Tw0S2P4, Unis_Tc4Tw0S2P5, 
        Unis_Tc4Tw1S0P0, Unis_Tc4Tw1S0P1, Unis_Tc4Tw1S0P2, Unis_Tc4Tw1S0P3, Unis_Tc4Tw1S0P4, Unis_Tc4Tw1S0P5, Unis_Tc4Tw1S1P0, Unis_Tc4Tw1S1P1, Unis_Tc4Tw1S1P2, Unis_Tc4Tw1S1P3, Unis_Tc4Tw1S1P4, Unis_Tc4Tw1S1P5, Unis_Tc4Tw1S2P0, Unis_Tc4Tw1S2P1, Unis_Tc4Tw1S2P2, Unis_Tc4Tw1S2P3, Unis_Tc4Tw1S2P4, Unis_Tc4Tw1S2P5, 
        Unis_Tc4Tw2S0P0, Unis_Tc4Tw2S0P1, Unis_Tc4Tw2S0P2, Unis_Tc4Tw2S0P3, Unis_Tc4Tw2S0P4, Unis_Tc4Tw2S0P5, Unis_Tc4Tw2S1P0, Unis_Tc4Tw2S1P1, Unis_Tc4Tw2S1P2, Unis_Tc4Tw2S1P3, Unis_Tc4Tw2S1P4, Unis_Tc4Tw2S1P5, Unis_Tc4Tw2S2P0, Unis_Tc4Tw2S2P1, Unis_Tc4Tw2S2P2, Unis_Tc4Tw2S2P3, Unis_Tc4Tw2S2P4, Unis_Tc4Tw2S2P5, 
        Unis_Tc4Tw3S0P0, Unis_Tc4Tw3S0P1, Unis_Tc4Tw3S0P2, Unis_Tc4Tw3S0P3, Unis_Tc4Tw3S0P4, Unis_Tc4Tw3S0P5, Unis_Tc4Tw3S1P0, Unis_Tc4Tw3S1P1, Unis_Tc4Tw3S1P2, Unis_Tc4Tw3S1P3, Unis_Tc4Tw3S1P4, Unis_Tc4Tw3S1P5, Unis_Tc4Tw3S2P0, Unis_Tc4Tw3S2P1, Unis_Tc4Tw3S2P2, Unis_Tc4Tw3S2P3, Unis_Tc4Tw3S2P4, Unis_Tc4Tw3S2P5, 
        Unis_Tc4Tw4S0P0, Unis_Tc4Tw4S0P1, Unis_Tc4Tw4S0P2, Unis_Tc4Tw4S0P3, Unis_Tc4Tw4S0P4, Unis_Tc4Tw4S0P5, Unis_Tc4Tw4S1P0, Unis_Tc4Tw4S1P1, Unis_Tc4Tw4S1P2, Unis_Tc4Tw4S1P3, Unis_Tc4Tw4S1P4, Unis_Tc4Tw4S1P5, Unis_Tc4Tw4S2P0, Unis_Tc4Tw4S2P1, Unis_Tc4Tw4S2P2, Unis_Tc4Tw4S2P3, Unis_Tc4Tw4S2P4, Unis_Tc4Tw4S2P5, 
        PmiAlUnis_Tc0Tw0S0P0, PmiAlUnis_Tc0Tw0S0P1, PmiAlUnis_Tc0Tw0S0P2, PmiAlUnis_Tc0Tw0S0P3, PmiAlUnis_Tc0Tw0S0P4, PmiAlUnis_Tc0Tw0S0P5, PmiAlUnis_Tc0Tw0S1P0, PmiAlUnis_Tc0Tw0S1P1, PmiAlUnis_Tc0Tw0S1P2, PmiAlUnis_Tc0Tw0S1P3, PmiAlUnis_Tc0Tw0S1P4, PmiAlUnis_Tc0Tw0S1P5, PmiAlUnis_Tc0Tw0S2P0, PmiAlUnis_Tc0Tw0S2P1, PmiAlUnis_Tc0Tw0S2P2, PmiAlUnis_Tc0Tw0S2P3, PmiAlUnis_Tc0Tw0S2P4, PmiAlUnis_Tc0Tw0S2P5, 
        PmiAlUnis_Tc0Tw1S0P0, PmiAlUnis_Tc0Tw1S0P1, PmiAlUnis_Tc0Tw1S0P2, PmiAlUnis_Tc0Tw1S0P3, PmiAlUnis_Tc0Tw1S0P4, PmiAlUnis_Tc0Tw1S0P5, PmiAlUnis_Tc0Tw1S1P0, PmiAlUnis_Tc0Tw1S1P1, PmiAlUnis_Tc0Tw1S1P2, PmiAlUnis_Tc0Tw1S1P3, PmiAlUnis_Tc0Tw1S1P4, PmiAlUnis_Tc0Tw1S1P5, PmiAlUnis_Tc0Tw1S2P0, PmiAlUnis_Tc0Tw1S2P1, PmiAlUnis_Tc0Tw1S2P2, PmiAlUnis_Tc0Tw1S2P3, PmiAlUnis_Tc0Tw1S2P4, PmiAlUnis_Tc0Tw1S2P5, 
        PmiAlUnis_Tc0Tw2S0P0, PmiAlUnis_Tc0Tw2S0P1, PmiAlUnis_Tc0Tw2S0P2, PmiAlUnis_Tc0Tw2S0P3, PmiAlUnis_Tc0Tw2S0P4, PmiAlUnis_Tc0Tw2S0P5, PmiAlUnis_Tc0Tw2S1P0, PmiAlUnis_Tc0Tw2S1P1, PmiAlUnis_Tc0Tw2S1P2, PmiAlUnis_Tc0Tw2S1P3, PmiAlUnis_Tc0Tw2S1P4, PmiAlUnis_Tc0Tw2S1P5, PmiAlUnis_Tc0Tw2S2P0, PmiAlUnis_Tc0Tw2S2P1, PmiAlUnis_Tc0Tw2S2P2, PmiAlUnis_Tc0Tw2S2P3, PmiAlUnis_Tc0Tw2S2P4, PmiAlUnis_Tc0Tw2S2P5, 
        PmiAlUnis_Tc0Tw3S0P0, PmiAlUnis_Tc0Tw3S0P1, PmiAlUnis_Tc0Tw3S0P2, PmiAlUnis_Tc0Tw3S0P3, PmiAlUnis_Tc0Tw3S0P4, PmiAlUnis_Tc0Tw3S0P5, PmiAlUnis_Tc0Tw3S1P0, PmiAlUnis_Tc0Tw3S1P1, PmiAlUnis_Tc0Tw3S1P2, PmiAlUnis_Tc0Tw3S1P3, PmiAlUnis_Tc0Tw3S1P4, PmiAlUnis_Tc0Tw3S1P5, PmiAlUnis_Tc0Tw3S2P0, PmiAlUnis_Tc0Tw3S2P1, PmiAlUnis_Tc0Tw3S2P2, PmiAlUnis_Tc0Tw3S2P3, PmiAlUnis_Tc0Tw3S2P4, PmiAlUnis_Tc0Tw3S2P5, 
        PmiAlUnis_Tc0Tw4S0P0, PmiAlUnis_Tc0Tw4S0P1, PmiAlUnis_Tc0Tw4S0P2, PmiAlUnis_Tc0Tw4S0P3, PmiAlUnis_Tc0Tw4S0P4, PmiAlUnis_Tc0Tw4S0P5, PmiAlUnis_Tc0Tw4S1P0, PmiAlUnis_Tc0Tw4S1P1, PmiAlUnis_Tc0Tw4S1P2, PmiAlUnis_Tc0Tw4S1P3, PmiAlUnis_Tc0Tw4S1P4, PmiAlUnis_Tc0Tw4S1P5, PmiAlUnis_Tc0Tw4S2P0, PmiAlUnis_Tc0Tw4S2P1, PmiAlUnis_Tc0Tw4S2P2, PmiAlUnis_Tc0Tw4S2P3, PmiAlUnis_Tc0Tw4S2P4, PmiAlUnis_Tc0Tw4S2P5, 
        PmiAlUnis_Tc1Tw0S0P0, PmiAlUnis_Tc1Tw0S0P1, PmiAlUnis_Tc1Tw0S0P2, PmiAlUnis_Tc1Tw0S0P3, PmiAlUnis_Tc1Tw0S0P4, PmiAlUnis_Tc1Tw0S0P5, PmiAlUnis_Tc1Tw0S1P0, PmiAlUnis_Tc1Tw0S1P1, PmiAlUnis_Tc1Tw0S1P2, PmiAlUnis_Tc1Tw0S1P3, PmiAlUnis_Tc1Tw0S1P4, PmiAlUnis_Tc1Tw0S1P5, PmiAlUnis_Tc1Tw0S2P0, PmiAlUnis_Tc1Tw0S2P1, PmiAlUnis_Tc1Tw0S2P2, PmiAlUnis_Tc1Tw0S2P3, PmiAlUnis_Tc1Tw0S2P4, PmiAlUnis_Tc1Tw0S2P5, 
        PmiAlUnis_Tc1Tw1S0P0, PmiAlUnis_Tc1Tw1S0P1, PmiAlUnis_Tc1Tw1S0P2, PmiAlUnis_Tc1Tw1S0P3, PmiAlUnis_Tc1Tw1S0P4, PmiAlUnis_Tc1Tw1S0P5, PmiAlUnis_Tc1Tw1S1P0, PmiAlUnis_Tc1Tw1S1P1, PmiAlUnis_Tc1Tw1S1P2, PmiAlUnis_Tc1Tw1S1P3, PmiAlUnis_Tc1Tw1S1P4, PmiAlUnis_Tc1Tw1S1P5, PmiAlUnis_Tc1Tw1S2P0, PmiAlUnis_Tc1Tw1S2P1, PmiAlUnis_Tc1Tw1S2P2, PmiAlUnis_Tc1Tw1S2P3, PmiAlUnis_Tc1Tw1S2P4, PmiAlUnis_Tc1Tw1S2P5, 
        PmiAlUnis_Tc1Tw2S0P0, PmiAlUnis_Tc1Tw2S0P1, PmiAlUnis_Tc1Tw2S0P2, PmiAlUnis_Tc1Tw2S0P3, PmiAlUnis_Tc1Tw2S0P4, PmiAlUnis_Tc1Tw2S0P5, PmiAlUnis_Tc1Tw2S1P0, PmiAlUnis_Tc1Tw2S1P1, PmiAlUnis_Tc1Tw2S1P2, PmiAlUnis_Tc1Tw2S1P3, PmiAlUnis_Tc1Tw2S1P4, PmiAlUnis_Tc1Tw2S1P5, PmiAlUnis_Tc1Tw2S2P0, PmiAlUnis_Tc1Tw2S2P1, PmiAlUnis_Tc1Tw2S2P2, PmiAlUnis_Tc1Tw2S2P3, PmiAlUnis_Tc1Tw2S2P4, PmiAlUnis_Tc1Tw2S2P5, 
        PmiAlUnis_Tc1Tw3S0P0, PmiAlUnis_Tc1Tw3S0P1, PmiAlUnis_Tc1Tw3S0P2, PmiAlUnis_Tc1Tw3S0P3, PmiAlUnis_Tc1Tw3S0P4, PmiAlUnis_Tc1Tw3S0P5, PmiAlUnis_Tc1Tw3S1P0, PmiAlUnis_Tc1Tw3S1P1, PmiAlUnis_Tc1Tw3S1P2, PmiAlUnis_Tc1Tw3S1P3, PmiAlUnis_Tc1Tw3S1P4, PmiAlUnis_Tc1Tw3S1P5, PmiAlUnis_Tc1Tw3S2P0, PmiAlUnis_Tc1Tw3S2P1, PmiAlUnis_Tc1Tw3S2P2, PmiAlUnis_Tc1Tw3S2P3, PmiAlUnis_Tc1Tw3S2P4, PmiAlUnis_Tc1Tw3S2P5, 
        PmiAlUnis_Tc1Tw4S0P0, PmiAlUnis_Tc1Tw4S0P1, PmiAlUnis_Tc1Tw4S0P2, PmiAlUnis_Tc1Tw4S0P3, PmiAlUnis_Tc1Tw4S0P4, PmiAlUnis_Tc1Tw4S0P5, PmiAlUnis_Tc1Tw4S1P0, PmiAlUnis_Tc1Tw4S1P1, PmiAlUnis_Tc1Tw4S1P2, PmiAlUnis_Tc1Tw4S1P3, PmiAlUnis_Tc1Tw4S1P4, PmiAlUnis_Tc1Tw4S1P5, PmiAlUnis_Tc1Tw4S2P0, PmiAlUnis_Tc1Tw4S2P1, PmiAlUnis_Tc1Tw4S2P2, PmiAlUnis_Tc1Tw4S2P3, PmiAlUnis_Tc1Tw4S2P4, PmiAlUnis_Tc1Tw4S2P5, 
        PmiAlUnis_Tc2Tw0S0P0, PmiAlUnis_Tc2Tw0S0P1, PmiAlUnis_Tc2Tw0S0P2, PmiAlUnis_Tc2Tw0S0P3, PmiAlUnis_Tc2Tw0S0P4, PmiAlUnis_Tc2Tw0S0P5, PmiAlUnis_Tc2Tw0S1P0, PmiAlUnis_Tc2Tw0S1P1, PmiAlUnis_Tc2Tw0S1P2, PmiAlUnis_Tc2Tw0S1P3, PmiAlUnis_Tc2Tw0S1P4, PmiAlUnis_Tc2Tw0S1P5, PmiAlUnis_Tc2Tw0S2P0, PmiAlUnis_Tc2Tw0S2P1, PmiAlUnis_Tc2Tw0S2P2, PmiAlUnis_Tc2Tw0S2P3, PmiAlUnis_Tc2Tw0S2P4, PmiAlUnis_Tc2Tw0S2P5, 
        PmiAlUnis_Tc2Tw1S0P0, PmiAlUnis_Tc2Tw1S0P1, PmiAlUnis_Tc2Tw1S0P2, PmiAlUnis_Tc2Tw1S0P3, PmiAlUnis_Tc2Tw1S0P4, PmiAlUnis_Tc2Tw1S0P5, PmiAlUnis_Tc2Tw1S1P0, PmiAlUnis_Tc2Tw1S1P1, PmiAlUnis_Tc2Tw1S1P2, PmiAlUnis_Tc2Tw1S1P3, PmiAlUnis_Tc2Tw1S1P4, PmiAlUnis_Tc2Tw1S1P5, PmiAlUnis_Tc2Tw1S2P0, PmiAlUnis_Tc2Tw1S2P1, PmiAlUnis_Tc2Tw1S2P2, PmiAlUnis_Tc2Tw1S2P3, PmiAlUnis_Tc2Tw1S2P4, PmiAlUnis_Tc2Tw1S2P5, 
        PmiAlUnis_Tc2Tw2S0P0, PmiAlUnis_Tc2Tw2S0P1, PmiAlUnis_Tc2Tw2S0P2, PmiAlUnis_Tc2Tw2S0P3, PmiAlUnis_Tc2Tw2S0P4, PmiAlUnis_Tc2Tw2S0P5, PmiAlUnis_Tc2Tw2S1P0, PmiAlUnis_Tc2Tw2S1P1, PmiAlUnis_Tc2Tw2S1P2, PmiAlUnis_Tc2Tw2S1P3, PmiAlUnis_Tc2Tw2S1P4, PmiAlUnis_Tc2Tw2S1P5, PmiAlUnis_Tc2Tw2S2P0, PmiAlUnis_Tc2Tw2S2P1, PmiAlUnis_Tc2Tw2S2P2, PmiAlUnis_Tc2Tw2S2P3, PmiAlUnis_Tc2Tw2S2P4, PmiAlUnis_Tc2Tw2S2P5, 
        PmiAlUnis_Tc2Tw3S0P0, PmiAlUnis_Tc2Tw3S0P1, PmiAlUnis_Tc2Tw3S0P2, PmiAlUnis_Tc2Tw3S0P3, PmiAlUnis_Tc2Tw3S0P4, PmiAlUnis_Tc2Tw3S0P5, PmiAlUnis_Tc2Tw3S1P0, PmiAlUnis_Tc2Tw3S1P1, PmiAlUnis_Tc2Tw3S1P2, PmiAlUnis_Tc2Tw3S1P3, PmiAlUnis_Tc2Tw3S1P4, PmiAlUnis_Tc2Tw3S1P5, PmiAlUnis_Tc2Tw3S2P0, PmiAlUnis_Tc2Tw3S2P1, PmiAlUnis_Tc2Tw3S2P2, PmiAlUnis_Tc2Tw3S2P3, PmiAlUnis_Tc2Tw3S2P4, PmiAlUnis_Tc2Tw3S2P5, 
        PmiAlUnis_Tc2Tw4S0P0, PmiAlUnis_Tc2Tw4S0P1, PmiAlUnis_Tc2Tw4S0P2, PmiAlUnis_Tc2Tw4S0P3, PmiAlUnis_Tc2Tw4S0P4, PmiAlUnis_Tc2Tw4S0P5, PmiAlUnis_Tc2Tw4S1P0, PmiAlUnis_Tc2Tw4S1P1, PmiAlUnis_Tc2Tw4S1P2, PmiAlUnis_Tc2Tw4S1P3, PmiAlUnis_Tc2Tw4S1P4, PmiAlUnis_Tc2Tw4S1P5, PmiAlUnis_Tc2Tw4S2P0, PmiAlUnis_Tc2Tw4S2P1, PmiAlUnis_Tc2Tw4S2P2, PmiAlUnis_Tc2Tw4S2P3, PmiAlUnis_Tc2Tw4S2P4, PmiAlUnis_Tc2Tw4S2P5, 
        PmiAlUnis_Tc3Tw0S0P0, PmiAlUnis_Tc3Tw0S0P1, PmiAlUnis_Tc3Tw0S0P2, PmiAlUnis_Tc3Tw0S0P3, PmiAlUnis_Tc3Tw0S0P4, PmiAlUnis_Tc3Tw0S0P5, PmiAlUnis_Tc3Tw0S1P0, PmiAlUnis_Tc3Tw0S1P1, PmiAlUnis_Tc3Tw0S1P2, PmiAlUnis_Tc3Tw0S1P3, PmiAlUnis_Tc3Tw0S1P4, PmiAlUnis_Tc3Tw0S1P5, PmiAlUnis_Tc3Tw0S2P0, PmiAlUnis_Tc3Tw0S2P1, PmiAlUnis_Tc3Tw0S2P2, PmiAlUnis_Tc3Tw0S2P3, PmiAlUnis_Tc3Tw0S2P4, PmiAlUnis_Tc3Tw0S2P5, 
        PmiAlUnis_Tc3Tw1S0P0, PmiAlUnis_Tc3Tw1S0P1, PmiAlUnis_Tc3Tw1S0P2, PmiAlUnis_Tc3Tw1S0P3, PmiAlUnis_Tc3Tw1S0P4, PmiAlUnis_Tc3Tw1S0P5, PmiAlUnis_Tc3Tw1S1P0, PmiAlUnis_Tc3Tw1S1P1, PmiAlUnis_Tc3Tw1S1P2, PmiAlUnis_Tc3Tw1S1P3, PmiAlUnis_Tc3Tw1S1P4, PmiAlUnis_Tc3Tw1S1P5, PmiAlUnis_Tc3Tw1S2P0, PmiAlUnis_Tc3Tw1S2P1, PmiAlUnis_Tc3Tw1S2P2, PmiAlUnis_Tc3Tw1S2P3, PmiAlUnis_Tc3Tw1S2P4, PmiAlUnis_Tc3Tw1S2P5, 
        PmiAlUnis_Tc3Tw2S0P0, PmiAlUnis_Tc3Tw2S0P1, PmiAlUnis_Tc3Tw2S0P2, PmiAlUnis_Tc3Tw2S0P3, PmiAlUnis_Tc3Tw2S0P4, PmiAlUnis_Tc3Tw2S0P5, PmiAlUnis_Tc3Tw2S1P0, PmiAlUnis_Tc3Tw2S1P1, PmiAlUnis_Tc3Tw2S1P2, PmiAlUnis_Tc3Tw2S1P3, PmiAlUnis_Tc3Tw2S1P4, PmiAlUnis_Tc3Tw2S1P5, PmiAlUnis_Tc3Tw2S2P0, PmiAlUnis_Tc3Tw2S2P1, PmiAlUnis_Tc3Tw2S2P2, PmiAlUnis_Tc3Tw2S2P3, PmiAlUnis_Tc3Tw2S2P4, PmiAlUnis_Tc3Tw2S2P5, 
        PmiAlUnis_Tc3Tw3S0P0, PmiAlUnis_Tc3Tw3S0P1, PmiAlUnis_Tc3Tw3S0P2, PmiAlUnis_Tc3Tw3S0P3, PmiAlUnis_Tc3Tw3S0P4, PmiAlUnis_Tc3Tw3S0P5, PmiAlUnis_Tc3Tw3S1P0, PmiAlUnis_Tc3Tw3S1P1, PmiAlUnis_Tc3Tw3S1P2, PmiAlUnis_Tc3Tw3S1P3, PmiAlUnis_Tc3Tw3S1P4, PmiAlUnis_Tc3Tw3S1P5, PmiAlUnis_Tc3Tw3S2P0, PmiAlUnis_Tc3Tw3S2P1, PmiAlUnis_Tc3Tw3S2P2, PmiAlUnis_Tc3Tw3S2P3, PmiAlUnis_Tc3Tw3S2P4, PmiAlUnis_Tc3Tw3S2P5, 
        PmiAlUnis_Tc3Tw4S0P0, PmiAlUnis_Tc3Tw4S0P1, PmiAlUnis_Tc3Tw4S0P2, PmiAlUnis_Tc3Tw4S0P3, PmiAlUnis_Tc3Tw4S0P4, PmiAlUnis_Tc3Tw4S0P5, PmiAlUnis_Tc3Tw4S1P0, PmiAlUnis_Tc3Tw4S1P1, PmiAlUnis_Tc3Tw4S1P2, PmiAlUnis_Tc3Tw4S1P3, PmiAlUnis_Tc3Tw4S1P4, PmiAlUnis_Tc3Tw4S1P5, PmiAlUnis_Tc3Tw4S2P0, PmiAlUnis_Tc3Tw4S2P1, PmiAlUnis_Tc3Tw4S2P2, PmiAlUnis_Tc3Tw4S2P3, PmiAlUnis_Tc3Tw4S2P4, PmiAlUnis_Tc3Tw4S2P5, 
        PmiAlUnis_Tc4Tw0S0P0, PmiAlUnis_Tc4Tw0S0P1, PmiAlUnis_Tc4Tw0S0P2, PmiAlUnis_Tc4Tw0S0P3, PmiAlUnis_Tc4Tw0S0P4, PmiAlUnis_Tc4Tw0S0P5, PmiAlUnis_Tc4Tw0S1P0, PmiAlUnis_Tc4Tw0S1P1, PmiAlUnis_Tc4Tw0S1P2, PmiAlUnis_Tc4Tw0S1P3, PmiAlUnis_Tc4Tw0S1P4, PmiAlUnis_Tc4Tw0S1P5, PmiAlUnis_Tc4Tw0S2P0, PmiAlUnis_Tc4Tw0S2P1, PmiAlUnis_Tc4Tw0S2P2, PmiAlUnis_Tc4Tw0S2P3, PmiAlUnis_Tc4Tw0S2P4, PmiAlUnis_Tc4Tw0S2P5, 
        PmiAlUnis_Tc4Tw1S0P0, PmiAlUnis_Tc4Tw1S0P1, PmiAlUnis_Tc4Tw1S0P2, PmiAlUnis_Tc4Tw1S0P3, PmiAlUnis_Tc4Tw1S0P4, PmiAlUnis_Tc4Tw1S0P5, PmiAlUnis_Tc4Tw1S1P0, PmiAlUnis_Tc4Tw1S1P1, PmiAlUnis_Tc4Tw1S1P2, PmiAlUnis_Tc4Tw1S1P3, PmiAlUnis_Tc4Tw1S1P4, PmiAlUnis_Tc4Tw1S1P5, PmiAlUnis_Tc4Tw1S2P0, PmiAlUnis_Tc4Tw1S2P1, PmiAlUnis_Tc4Tw1S2P2, PmiAlUnis_Tc4Tw1S2P3, PmiAlUnis_Tc4Tw1S2P4, PmiAlUnis_Tc4Tw1S2P5, 
        PmiAlUnis_Tc4Tw2S0P0, PmiAlUnis_Tc4Tw2S0P1, PmiAlUnis_Tc4Tw2S0P2, PmiAlUnis_Tc4Tw2S0P3, PmiAlUnis_Tc4Tw2S0P4, PmiAlUnis_Tc4Tw2S0P5, PmiAlUnis_Tc4Tw2S1P0, PmiAlUnis_Tc4Tw2S1P1, PmiAlUnis_Tc4Tw2S1P2, PmiAlUnis_Tc4Tw2S1P3, PmiAlUnis_Tc4Tw2S1P4, PmiAlUnis_Tc4Tw2S1P5, PmiAlUnis_Tc4Tw2S2P0, PmiAlUnis_Tc4Tw2S2P1, PmiAlUnis_Tc4Tw2S2P2, PmiAlUnis_Tc4Tw2S2P3, PmiAlUnis_Tc4Tw2S2P4, PmiAlUnis_Tc4Tw2S2P5, 
        PmiAlUnis_Tc4Tw3S0P0, PmiAlUnis_Tc4Tw3S0P1, PmiAlUnis_Tc4Tw3S0P2, PmiAlUnis_Tc4Tw3S0P3, PmiAlUnis_Tc4Tw3S0P4, PmiAlUnis_Tc4Tw3S0P5, PmiAlUnis_Tc4Tw3S1P0, PmiAlUnis_Tc4Tw3S1P1, PmiAlUnis_Tc4Tw3S1P2, PmiAlUnis_Tc4Tw3S1P3, PmiAlUnis_Tc4Tw3S1P4, PmiAlUnis_Tc4Tw3S1P5, PmiAlUnis_Tc4Tw3S2P0, PmiAlUnis_Tc4Tw3S2P1, PmiAlUnis_Tc4Tw3S2P2, PmiAlUnis_Tc4Tw3S2P3, PmiAlUnis_Tc4Tw3S2P4, PmiAlUnis_Tc4Tw3S2P5, 
        PmiAlUnis_Tc4Tw4S0P0, PmiAlUnis_Tc4Tw4S0P1, PmiAlUnis_Tc4Tw4S0P2, PmiAlUnis_Tc4Tw4S0P3, PmiAlUnis_Tc4Tw4S0P4, PmiAlUnis_Tc4Tw4S0P5, PmiAlUnis_Tc4Tw4S1P0, PmiAlUnis_Tc4Tw4S1P1, PmiAlUnis_Tc4Tw4S1P2, PmiAlUnis_Tc4Tw4S1P3, PmiAlUnis_Tc4Tw4S1P4, PmiAlUnis_Tc4Tw4S1P5, PmiAlUnis_Tc4Tw4S2P0, PmiAlUnis_Tc4Tw4S2P1, PmiAlUnis_Tc4Tw4S2P2, PmiAlUnis_Tc4Tw4S2P3, PmiAlUnis_Tc4Tw4S2P4, PmiAlUnis_Tc4Tw4S2P5, 
        NPmiAl_Tc0Tw0S0P0, NPmiAl_Tc0Tw0S0P1, NPmiAl_Tc0Tw0S0P2, NPmiAl_Tc0Tw0S0P3, NPmiAl_Tc0Tw0S0P4, NPmiAl_Tc0Tw0S0P5, NPmiAl_Tc0Tw0S1P0, NPmiAl_Tc0Tw0S1P1, NPmiAl_Tc0Tw0S1P2, NPmiAl_Tc0Tw0S1P3, NPmiAl_Tc0Tw0S1P4, NPmiAl_Tc0Tw0S1P5, NPmiAl_Tc0Tw0S2P0, NPmiAl_Tc0Tw0S2P1, NPmiAl_Tc0Tw0S2P2, NPmiAl_Tc0Tw0S2P3, NPmiAl_Tc0Tw0S2P4, NPmiAl_Tc0Tw0S2P5, 
        NPmiAl_Tc0Tw1S0P0, NPmiAl_Tc0Tw1S0P1, NPmiAl_Tc0Tw1S0P2, NPmiAl_Tc0Tw1S0P3, NPmiAl_Tc0Tw1S0P4, NPmiAl_Tc0Tw1S0P5, NPmiAl_Tc0Tw1S1P0, NPmiAl_Tc0Tw1S1P1, NPmiAl_Tc0Tw1S1P2, NPmiAl_Tc0Tw1S1P3, NPmiAl_Tc0Tw1S1P4, NPmiAl_Tc0Tw1S1P5, NPmiAl_Tc0Tw1S2P0, NPmiAl_Tc0Tw1S2P1, NPmiAl_Tc0Tw1S2P2, NPmiAl_Tc0Tw1S2P3, NPmiAl_Tc0Tw1S2P4, NPmiAl_Tc0Tw1S2P5, 
        NPmiAl_Tc0Tw2S0P0, NPmiAl_Tc0Tw2S0P1, NPmiAl_Tc0Tw2S0P2, NPmiAl_Tc0Tw2S0P3, NPmiAl_Tc0Tw2S0P4, NPmiAl_Tc0Tw2S0P5, NPmiAl_Tc0Tw2S1P0, NPmiAl_Tc0Tw2S1P1, NPmiAl_Tc0Tw2S1P2, NPmiAl_Tc0Tw2S1P3, NPmiAl_Tc0Tw2S1P4, NPmiAl_Tc0Tw2S1P5, NPmiAl_Tc0Tw2S2P0, NPmiAl_Tc0Tw2S2P1, NPmiAl_Tc0Tw2S2P2, NPmiAl_Tc0Tw2S2P3, NPmiAl_Tc0Tw2S2P4, NPmiAl_Tc0Tw2S2P5, 
        NPmiAl_Tc0Tw3S0P0, NPmiAl_Tc0Tw3S0P1, NPmiAl_Tc0Tw3S0P2, NPmiAl_Tc0Tw3S0P3, NPmiAl_Tc0Tw3S0P4, NPmiAl_Tc0Tw3S0P5, NPmiAl_Tc0Tw3S1P0, NPmiAl_Tc0Tw3S1P1, NPmiAl_Tc0Tw3S1P2, NPmiAl_Tc0Tw3S1P3, NPmiAl_Tc0Tw3S1P4, NPmiAl_Tc0Tw3S1P5, NPmiAl_Tc0Tw3S2P0, NPmiAl_Tc0Tw3S2P1, NPmiAl_Tc0Tw3S2P2, NPmiAl_Tc0Tw3S2P3, NPmiAl_Tc0Tw3S2P4, NPmiAl_Tc0Tw3S2P5, 
        NPmiAl_Tc0Tw4S0P0, NPmiAl_Tc0Tw4S0P1, NPmiAl_Tc0Tw4S0P2, NPmiAl_Tc0Tw4S0P3, NPmiAl_Tc0Tw4S0P4, NPmiAl_Tc0Tw4S0P5, NPmiAl_Tc0Tw4S1P0, NPmiAl_Tc0Tw4S1P1, NPmiAl_Tc0Tw4S1P2, NPmiAl_Tc0Tw4S1P3, NPmiAl_Tc0Tw4S1P4, NPmiAl_Tc0Tw4S1P5, NPmiAl_Tc0Tw4S2P0, NPmiAl_Tc0Tw4S2P1, NPmiAl_Tc0Tw4S2P2, NPmiAl_Tc0Tw4S2P3, NPmiAl_Tc0Tw4S2P4, NPmiAl_Tc0Tw4S2P5, 
        NPmiAl_Tc1Tw0S0P0, NPmiAl_Tc1Tw0S0P1, NPmiAl_Tc1Tw0S0P2, NPmiAl_Tc1Tw0S0P3, NPmiAl_Tc1Tw0S0P4, NPmiAl_Tc1Tw0S0P5, NPmiAl_Tc1Tw0S1P0, NPmiAl_Tc1Tw0S1P1, NPmiAl_Tc1Tw0S1P2, NPmiAl_Tc1Tw0S1P3, NPmiAl_Tc1Tw0S1P4, NPmiAl_Tc1Tw0S1P5, NPmiAl_Tc1Tw0S2P0, NPmiAl_Tc1Tw0S2P1, NPmiAl_Tc1Tw0S2P2, NPmiAl_Tc1Tw0S2P3, NPmiAl_Tc1Tw0S2P4, NPmiAl_Tc1Tw0S2P5, 
        NPmiAl_Tc1Tw1S0P0, NPmiAl_Tc1Tw1S0P1, NPmiAl_Tc1Tw1S0P2, NPmiAl_Tc1Tw1S0P3, NPmiAl_Tc1Tw1S0P4, NPmiAl_Tc1Tw1S0P5, NPmiAl_Tc1Tw1S1P0, NPmiAl_Tc1Tw1S1P1, NPmiAl_Tc1Tw1S1P2, NPmiAl_Tc1Tw1S1P3, NPmiAl_Tc1Tw1S1P4, NPmiAl_Tc1Tw1S1P5, NPmiAl_Tc1Tw1S2P0, NPmiAl_Tc1Tw1S2P1, NPmiAl_Tc1Tw1S2P2, NPmiAl_Tc1Tw1S2P3, NPmiAl_Tc1Tw1S2P4, NPmiAl_Tc1Tw1S2P5, 
        NPmiAl_Tc1Tw2S0P0, NPmiAl_Tc1Tw2S0P1, NPmiAl_Tc1Tw2S0P2, NPmiAl_Tc1Tw2S0P3, NPmiAl_Tc1Tw2S0P4, NPmiAl_Tc1Tw2S0P5, NPmiAl_Tc1Tw2S1P0, NPmiAl_Tc1Tw2S1P1, NPmiAl_Tc1Tw2S1P2, NPmiAl_Tc1Tw2S1P3, NPmiAl_Tc1Tw2S1P4, NPmiAl_Tc1Tw2S1P5, NPmiAl_Tc1Tw2S2P0, NPmiAl_Tc1Tw2S2P1, NPmiAl_Tc1Tw2S2P2, NPmiAl_Tc1Tw2S2P3, NPmiAl_Tc1Tw2S2P4, NPmiAl_Tc1Tw2S2P5, 
        NPmiAl_Tc1Tw3S0P0, NPmiAl_Tc1Tw3S0P1, NPmiAl_Tc1Tw3S0P2, NPmiAl_Tc1Tw3S0P3, NPmiAl_Tc1Tw3S0P4, NPmiAl_Tc1Tw3S0P5, NPmiAl_Tc1Tw3S1P0, NPmiAl_Tc1Tw3S1P1, NPmiAl_Tc1Tw3S1P2, NPmiAl_Tc1Tw3S1P3, NPmiAl_Tc1Tw3S1P4, NPmiAl_Tc1Tw3S1P5, NPmiAl_Tc1Tw3S2P0, NPmiAl_Tc1Tw3S2P1, NPmiAl_Tc1Tw3S2P2, NPmiAl_Tc1Tw3S2P3, NPmiAl_Tc1Tw3S2P4, NPmiAl_Tc1Tw3S2P5, 
        NPmiAl_Tc1Tw4S0P0, NPmiAl_Tc1Tw4S0P1, NPmiAl_Tc1Tw4S0P2, NPmiAl_Tc1Tw4S0P3, NPmiAl_Tc1Tw4S0P4, NPmiAl_Tc1Tw4S0P5, NPmiAl_Tc1Tw4S1P0, NPmiAl_Tc1Tw4S1P1, NPmiAl_Tc1Tw4S1P2, NPmiAl_Tc1Tw4S1P3, NPmiAl_Tc1Tw4S1P4, NPmiAl_Tc1Tw4S1P5, NPmiAl_Tc1Tw4S2P0, NPmiAl_Tc1Tw4S2P1, NPmiAl_Tc1Tw4S2P2, NPmiAl_Tc1Tw4S2P3, NPmiAl_Tc1Tw4S2P4, NPmiAl_Tc1Tw4S2P5, 
        NPmiAl_Tc2Tw0S0P0, NPmiAl_Tc2Tw0S0P1, NPmiAl_Tc2Tw0S0P2, NPmiAl_Tc2Tw0S0P3, NPmiAl_Tc2Tw0S0P4, NPmiAl_Tc2Tw0S0P5, NPmiAl_Tc2Tw0S1P0, NPmiAl_Tc2Tw0S1P1, NPmiAl_Tc2Tw0S1P2, NPmiAl_Tc2Tw0S1P3, NPmiAl_Tc2Tw0S1P4, NPmiAl_Tc2Tw0S1P5, NPmiAl_Tc2Tw0S2P0, NPmiAl_Tc2Tw0S2P1, NPmiAl_Tc2Tw0S2P2, NPmiAl_Tc2Tw0S2P3, NPmiAl_Tc2Tw0S2P4, NPmiAl_Tc2Tw0S2P5, 
        NPmiAl_Tc2Tw1S0P0, NPmiAl_Tc2Tw1S0P1, NPmiAl_Tc2Tw1S0P2, NPmiAl_Tc2Tw1S0P3, NPmiAl_Tc2Tw1S0P4, NPmiAl_Tc2Tw1S0P5, NPmiAl_Tc2Tw1S1P0, NPmiAl_Tc2Tw1S1P1, NPmiAl_Tc2Tw1S1P2, NPmiAl_Tc2Tw1S1P3, NPmiAl_Tc2Tw1S1P4, NPmiAl_Tc2Tw1S1P5, NPmiAl_Tc2Tw1S2P0, NPmiAl_Tc2Tw1S2P1, NPmiAl_Tc2Tw1S2P2, NPmiAl_Tc2Tw1S2P3, NPmiAl_Tc2Tw1S2P4, NPmiAl_Tc2Tw1S2P5, 
        NPmiAl_Tc2Tw2S0P0, NPmiAl_Tc2Tw2S0P1, NPmiAl_Tc2Tw2S0P2, NPmiAl_Tc2Tw2S0P3, NPmiAl_Tc2Tw2S0P4, NPmiAl_Tc2Tw2S0P5, NPmiAl_Tc2Tw2S1P0, NPmiAl_Tc2Tw2S1P1, NPmiAl_Tc2Tw2S1P2, NPmiAl_Tc2Tw2S1P3, NPmiAl_Tc2Tw2S1P4, NPmiAl_Tc2Tw2S1P5, NPmiAl_Tc2Tw2S2P0, NPmiAl_Tc2Tw2S2P1, NPmiAl_Tc2Tw2S2P2, NPmiAl_Tc2Tw2S2P3, NPmiAl_Tc2Tw2S2P4, NPmiAl_Tc2Tw2S2P5, 
        NPmiAl_Tc2Tw3S0P0, NPmiAl_Tc2Tw3S0P1, NPmiAl_Tc2Tw3S0P2, NPmiAl_Tc2Tw3S0P3, NPmiAl_Tc2Tw3S0P4, NPmiAl_Tc2Tw3S0P5, NPmiAl_Tc2Tw3S1P0, NPmiAl_Tc2Tw3S1P1, NPmiAl_Tc2Tw3S1P2, NPmiAl_Tc2Tw3S1P3, NPmiAl_Tc2Tw3S1P4, NPmiAl_Tc2Tw3S1P5, NPmiAl_Tc2Tw3S2P0, NPmiAl_Tc2Tw3S2P1, NPmiAl_Tc2Tw3S2P2, NPmiAl_Tc2Tw3S2P3, NPmiAl_Tc2Tw3S2P4, NPmiAl_Tc2Tw3S2P5, 
        NPmiAl_Tc2Tw4S0P0, NPmiAl_Tc2Tw4S0P1, NPmiAl_Tc2Tw4S0P2, NPmiAl_Tc2Tw4S0P3, NPmiAl_Tc2Tw4S0P4, NPmiAl_Tc2Tw4S0P5, NPmiAl_Tc2Tw4S1P0, NPmiAl_Tc2Tw4S1P1, NPmiAl_Tc2Tw4S1P2, NPmiAl_Tc2Tw4S1P3, NPmiAl_Tc2Tw4S1P4, NPmiAl_Tc2Tw4S1P5, NPmiAl_Tc2Tw4S2P0, NPmiAl_Tc2Tw4S2P1, NPmiAl_Tc2Tw4S2P2, NPmiAl_Tc2Tw4S2P3, NPmiAl_Tc2Tw4S2P4, NPmiAl_Tc2Tw4S2P5, 
        NPmiAl_Tc3Tw0S0P0, NPmiAl_Tc3Tw0S0P1, NPmiAl_Tc3Tw0S0P2, NPmiAl_Tc3Tw0S0P3, NPmiAl_Tc3Tw0S0P4, NPmiAl_Tc3Tw0S0P5, NPmiAl_Tc3Tw0S1P0, NPmiAl_Tc3Tw0S1P1, NPmiAl_Tc3Tw0S1P2, NPmiAl_Tc3Tw0S1P3, NPmiAl_Tc3Tw0S1P4, NPmiAl_Tc3Tw0S1P5, NPmiAl_Tc3Tw0S2P0, NPmiAl_Tc3Tw0S2P1, NPmiAl_Tc3Tw0S2P2, NPmiAl_Tc3Tw0S2P3, NPmiAl_Tc3Tw0S2P4, NPmiAl_Tc3Tw0S2P5, 
        NPmiAl_Tc3Tw1S0P0, NPmiAl_Tc3Tw1S0P1, NPmiAl_Tc3Tw1S0P2, NPmiAl_Tc3Tw1S0P3, NPmiAl_Tc3Tw1S0P4, NPmiAl_Tc3Tw1S0P5, NPmiAl_Tc3Tw1S1P0, NPmiAl_Tc3Tw1S1P1, NPmiAl_Tc3Tw1S1P2, NPmiAl_Tc3Tw1S1P3, NPmiAl_Tc3Tw1S1P4, NPmiAl_Tc3Tw1S1P5, NPmiAl_Tc3Tw1S2P0, NPmiAl_Tc3Tw1S2P1, NPmiAl_Tc3Tw1S2P2, NPmiAl_Tc3Tw1S2P3, NPmiAl_Tc3Tw1S2P4, NPmiAl_Tc3Tw1S2P5, 
        NPmiAl_Tc3Tw2S0P0, NPmiAl_Tc3Tw2S0P1, NPmiAl_Tc3Tw2S0P2, NPmiAl_Tc3Tw2S0P3, NPmiAl_Tc3Tw2S0P4, NPmiAl_Tc3Tw2S0P5, NPmiAl_Tc3Tw2S1P0, NPmiAl_Tc3Tw2S1P1, NPmiAl_Tc3Tw2S1P2, NPmiAl_Tc3Tw2S1P3, NPmiAl_Tc3Tw2S1P4, NPmiAl_Tc3Tw2S1P5, NPmiAl_Tc3Tw2S2P0, NPmiAl_Tc3Tw2S2P1, NPmiAl_Tc3Tw2S2P2, NPmiAl_Tc3Tw2S2P3, NPmiAl_Tc3Tw2S2P4, NPmiAl_Tc3Tw2S2P5, 
        NPmiAl_Tc3Tw3S0P0, NPmiAl_Tc3Tw3S0P1, NPmiAl_Tc3Tw3S0P2, NPmiAl_Tc3Tw3S0P3, NPmiAl_Tc3Tw3S0P4, NPmiAl_Tc3Tw3S0P5, NPmiAl_Tc3Tw3S1P0, NPmiAl_Tc3Tw3S1P1, NPmiAl_Tc3Tw3S1P2, NPmiAl_Tc3Tw3S1P3, NPmiAl_Tc3Tw3S1P4, NPmiAl_Tc3Tw3S1P5, NPmiAl_Tc3Tw3S2P0, NPmiAl_Tc3Tw3S2P1, NPmiAl_Tc3Tw3S2P2, NPmiAl_Tc3Tw3S2P3, NPmiAl_Tc3Tw3S2P4, NPmiAl_Tc3Tw3S2P5, 
        NPmiAl_Tc3Tw4S0P0, NPmiAl_Tc3Tw4S0P1, NPmiAl_Tc3Tw4S0P2, NPmiAl_Tc3Tw4S0P3, NPmiAl_Tc3Tw4S0P4, NPmiAl_Tc3Tw4S0P5, NPmiAl_Tc3Tw4S1P0, NPmiAl_Tc3Tw4S1P1, NPmiAl_Tc3Tw4S1P2, NPmiAl_Tc3Tw4S1P3, NPmiAl_Tc3Tw4S1P4, NPmiAl_Tc3Tw4S1P5, NPmiAl_Tc3Tw4S2P0, NPmiAl_Tc3Tw4S2P1, NPmiAl_Tc3Tw4S2P2, NPmiAl_Tc3Tw4S2P3, NPmiAl_Tc3Tw4S2P4, NPmiAl_Tc3Tw4S2P5, 
        NPmiAl_Tc4Tw0S0P0, NPmiAl_Tc4Tw0S0P1, NPmiAl_Tc4Tw0S0P2, NPmiAl_Tc4Tw0S0P3, NPmiAl_Tc4Tw0S0P4, NPmiAl_Tc4Tw0S0P5, NPmiAl_Tc4Tw0S1P0, NPmiAl_Tc4Tw0S1P1, NPmiAl_Tc4Tw0S1P2, NPmiAl_Tc4Tw0S1P3, NPmiAl_Tc4Tw0S1P4, NPmiAl_Tc4Tw0S1P5, NPmiAl_Tc4Tw0S2P0, NPmiAl_Tc4Tw0S2P1, NPmiAl_Tc4Tw0S2P2, NPmiAl_Tc4Tw0S2P3, NPmiAl_Tc4Tw0S2P4, NPmiAl_Tc4Tw0S2P5, 
        NPmiAl_Tc4Tw1S0P0, NPmiAl_Tc4Tw1S0P1, NPmiAl_Tc4Tw1S0P2, NPmiAl_Tc4Tw1S0P3, NPmiAl_Tc4Tw1S0P4, NPmiAl_Tc4Tw1S0P5, NPmiAl_Tc4Tw1S1P0, NPmiAl_Tc4Tw1S1P1, NPmiAl_Tc4Tw1S1P2, NPmiAl_Tc4Tw1S1P3, NPmiAl_Tc4Tw1S1P4, NPmiAl_Tc4Tw1S1P5, NPmiAl_Tc4Tw1S2P0, NPmiAl_Tc4Tw1S2P1, NPmiAl_Tc4Tw1S2P2, NPmiAl_Tc4Tw1S2P3, NPmiAl_Tc4Tw1S2P4, NPmiAl_Tc4Tw1S2P5, 
        NPmiAl_Tc4Tw2S0P0, NPmiAl_Tc4Tw2S0P1, NPmiAl_Tc4Tw2S0P2, NPmiAl_Tc4Tw2S0P3, NPmiAl_Tc4Tw2S0P4, NPmiAl_Tc4Tw2S0P5, NPmiAl_Tc4Tw2S1P0, NPmiAl_Tc4Tw2S1P1, NPmiAl_Tc4Tw2S1P2, NPmiAl_Tc4Tw2S1P3, NPmiAl_Tc4Tw2S1P4, NPmiAl_Tc4Tw2S1P5, NPmiAl_Tc4Tw2S2P0, NPmiAl_Tc4Tw2S2P1, NPmiAl_Tc4Tw2S2P2, NPmiAl_Tc4Tw2S2P3, NPmiAl_Tc4Tw2S2P4, NPmiAl_Tc4Tw2S2P5, 
        NPmiAl_Tc4Tw3S0P0, NPmiAl_Tc4Tw3S0P1, NPmiAl_Tc4Tw3S0P2, NPmiAl_Tc4Tw3S0P3, NPmiAl_Tc4Tw3S0P4, NPmiAl_Tc4Tw3S0P5, NPmiAl_Tc4Tw3S1P0, NPmiAl_Tc4Tw3S1P1, NPmiAl_Tc4Tw3S1P2, NPmiAl_Tc4Tw3S1P3, NPmiAl_Tc4Tw3S1P4, NPmiAl_Tc4Tw3S1P5, NPmiAl_Tc4Tw3S2P0, NPmiAl_Tc4Tw3S2P1, NPmiAl_Tc4Tw3S2P2, NPmiAl_Tc4Tw3S2P3, NPmiAl_Tc4Tw3S2P4, NPmiAl_Tc4Tw3S2P5, 
        NPmiAl_Tc4Tw4S0P0, NPmiAl_Tc4Tw4S0P1, NPmiAl_Tc4Tw4S0P2, NPmiAl_Tc4Tw4S0P3, NPmiAl_Tc4Tw4S0P4, NPmiAl_Tc4Tw4S0P5, NPmiAl_Tc4Tw4S1P0, NPmiAl_Tc4Tw4S1P1, NPmiAl_Tc4Tw4S1P2, NPmiAl_Tc4Tw4S1P3, NPmiAl_Tc4Tw4S1P4, NPmiAl_Tc4Tw4S1P5, NPmiAl_Tc4Tw4S2P0, NPmiAl_Tc4Tw4S2P1, NPmiAl_Tc4Tw4S2P2, NPmiAl_Tc4Tw4S2P3, NPmiAl_Tc4Tw4S2P4, NPmiAl_Tc4Tw4S2P5, 
        PmiAlUnisAm_Tc0Tw3S2P1, PmiAlUnisAm_Tc0Tw2S2P2, PmiAlUnisAm_Tc4Tw3S2P0, PmiAlUnisAm_Tc3Tw0S2P3, PmiAlUnisAm_Tc4Tw0S0P5, PmiAlUnisAm_Tc3Tw0S0P5, PmiAlUnisAm_Tc4Tw3S2P5, PmiAlUnisAm_Tc4Tw0S0P1, PmiAlUnisAm_Tc0Tw2S0P1, 
        //here follows those schemes whose computation takes overly much time
        Rapp_1, Rapp_2, Rapp_3, Rapp_4, Rapp_5, Rapp_6, PmiXRapp_1, PmiXRapp_4, PmiXRapp_6, Atc, Gref_2
        //there are so many weighting schemes in this enum, that the code does not compile, as it writes "error: code too large", so the rest should either be tested separately or omitted altogether
        /*
        PmiAlUnisAm_Tc0Tw0S0P0, PmiAlUnisAm_Tc0Tw0S0P1, PmiAlUnisAm_Tc0Tw0S0P2, PmiAlUnisAm_Tc0Tw0S0P3, PmiAlUnisAm_Tc0Tw0S0P4, PmiAlUnisAm_Tc0Tw0S0P5, PmiAlUnisAm_Tc0Tw0S1P0, PmiAlUnisAm_Tc0Tw0S1P1, PmiAlUnisAm_Tc0Tw0S1P2, PmiAlUnisAm_Tc0Tw0S1P3, PmiAlUnisAm_Tc0Tw0S1P4, PmiAlUnisAm_Tc0Tw0S1P5, PmiAlUnisAm_Tc0Tw0S2P0, PmiAlUnisAm_Tc0Tw0S2P1, PmiAlUnisAm_Tc0Tw0S2P2, PmiAlUnisAm_Tc0Tw0S2P3, PmiAlUnisAm_Tc0Tw0S2P4, PmiAlUnisAm_Tc0Tw0S2P5, 
        PmiAlUnisAm_Tc0Tw1S0P0, PmiAlUnisAm_Tc0Tw1S0P1, PmiAlUnisAm_Tc0Tw1S0P2, PmiAlUnisAm_Tc0Tw1S0P3, PmiAlUnisAm_Tc0Tw1S0P4, PmiAlUnisAm_Tc0Tw1S0P5, PmiAlUnisAm_Tc0Tw1S1P0, PmiAlUnisAm_Tc0Tw1S1P1, PmiAlUnisAm_Tc0Tw1S1P2, PmiAlUnisAm_Tc0Tw1S1P3, PmiAlUnisAm_Tc0Tw1S1P4, PmiAlUnisAm_Tc0Tw1S1P5, PmiAlUnisAm_Tc0Tw1S2P0, PmiAlUnisAm_Tc0Tw1S2P1, PmiAlUnisAm_Tc0Tw1S2P2, PmiAlUnisAm_Tc0Tw1S2P3, PmiAlUnisAm_Tc0Tw1S2P4, PmiAlUnisAm_Tc0Tw1S2P5, 
        PmiAlUnisAm_Tc0Tw2S0P0, PmiAlUnisAm_Tc0Tw2S0P1, PmiAlUnisAm_Tc0Tw2S0P2, PmiAlUnisAm_Tc0Tw2S0P3, PmiAlUnisAm_Tc0Tw2S0P4, PmiAlUnisAm_Tc0Tw2S0P5, PmiAlUnisAm_Tc0Tw2S1P0, PmiAlUnisAm_Tc0Tw2S1P1, PmiAlUnisAm_Tc0Tw2S1P2, PmiAlUnisAm_Tc0Tw2S1P3, PmiAlUnisAm_Tc0Tw2S1P4, PmiAlUnisAm_Tc0Tw2S1P5, PmiAlUnisAm_Tc0Tw2S2P0, PmiAlUnisAm_Tc0Tw2S2P1, PmiAlUnisAm_Tc0Tw2S2P2, PmiAlUnisAm_Tc0Tw2S2P3, PmiAlUnisAm_Tc0Tw2S2P4, PmiAlUnisAm_Tc0Tw2S2P5, 
        PmiAlUnisAm_Tc0Tw3S0P0, PmiAlUnisAm_Tc0Tw3S0P1, PmiAlUnisAm_Tc0Tw3S0P2, PmiAlUnisAm_Tc0Tw3S0P3, PmiAlUnisAm_Tc0Tw3S0P4, PmiAlUnisAm_Tc0Tw3S0P5, PmiAlUnisAm_Tc0Tw3S1P0, PmiAlUnisAm_Tc0Tw3S1P1, PmiAlUnisAm_Tc0Tw3S1P2, PmiAlUnisAm_Tc0Tw3S1P3, PmiAlUnisAm_Tc0Tw3S1P4, PmiAlUnisAm_Tc0Tw3S1P5, PmiAlUnisAm_Tc0Tw3S2P0, PmiAlUnisAm_Tc0Tw3S2P1, PmiAlUnisAm_Tc0Tw3S2P2, PmiAlUnisAm_Tc0Tw3S2P3, PmiAlUnisAm_Tc0Tw3S2P4, PmiAlUnisAm_Tc0Tw3S2P5, 
        PmiAlUnisAm_Tc0Tw4S0P0, PmiAlUnisAm_Tc0Tw4S0P1, PmiAlUnisAm_Tc0Tw4S0P2, PmiAlUnisAm_Tc0Tw4S0P3, PmiAlUnisAm_Tc0Tw4S0P4, PmiAlUnisAm_Tc0Tw4S0P5, PmiAlUnisAm_Tc0Tw4S1P0, PmiAlUnisAm_Tc0Tw4S1P1, PmiAlUnisAm_Tc0Tw4S1P2, PmiAlUnisAm_Tc0Tw4S1P3, PmiAlUnisAm_Tc0Tw4S1P4, PmiAlUnisAm_Tc0Tw4S1P5, PmiAlUnisAm_Tc0Tw4S2P0, PmiAlUnisAm_Tc0Tw4S2P1, PmiAlUnisAm_Tc0Tw4S2P2, PmiAlUnisAm_Tc0Tw4S2P3, PmiAlUnisAm_Tc0Tw4S2P4, PmiAlUnisAm_Tc0Tw4S2P5, 
        PmiAlUnisAm_Tc1Tw0S0P0, PmiAlUnisAm_Tc1Tw0S0P1, PmiAlUnisAm_Tc1Tw0S0P2, PmiAlUnisAm_Tc1Tw0S0P3, PmiAlUnisAm_Tc1Tw0S0P4, PmiAlUnisAm_Tc1Tw0S0P5, PmiAlUnisAm_Tc1Tw0S1P0, PmiAlUnisAm_Tc1Tw0S1P1, PmiAlUnisAm_Tc1Tw0S1P2, PmiAlUnisAm_Tc1Tw0S1P3, PmiAlUnisAm_Tc1Tw0S1P4, PmiAlUnisAm_Tc1Tw0S1P5, PmiAlUnisAm_Tc1Tw0S2P0, PmiAlUnisAm_Tc1Tw0S2P1, PmiAlUnisAm_Tc1Tw0S2P2, PmiAlUnisAm_Tc1Tw0S2P3, PmiAlUnisAm_Tc1Tw0S2P4, PmiAlUnisAm_Tc1Tw0S2P5, 
        PmiAlUnisAm_Tc1Tw1S0P0, PmiAlUnisAm_Tc1Tw1S0P1, PmiAlUnisAm_Tc1Tw1S0P2, PmiAlUnisAm_Tc1Tw1S0P3, PmiAlUnisAm_Tc1Tw1S0P4, PmiAlUnisAm_Tc1Tw1S0P5, PmiAlUnisAm_Tc1Tw1S1P0, PmiAlUnisAm_Tc1Tw1S1P1, PmiAlUnisAm_Tc1Tw1S1P2, PmiAlUnisAm_Tc1Tw1S1P3, PmiAlUnisAm_Tc1Tw1S1P4, PmiAlUnisAm_Tc1Tw1S1P5, PmiAlUnisAm_Tc1Tw1S2P0, PmiAlUnisAm_Tc1Tw1S2P1, PmiAlUnisAm_Tc1Tw1S2P2, PmiAlUnisAm_Tc1Tw1S2P3, PmiAlUnisAm_Tc1Tw1S2P4, PmiAlUnisAm_Tc1Tw1S2P5, 
        PmiAlUnisAm_Tc1Tw2S0P0, PmiAlUnisAm_Tc1Tw2S0P1, PmiAlUnisAm_Tc1Tw2S0P2, PmiAlUnisAm_Tc1Tw2S0P3, PmiAlUnisAm_Tc1Tw2S0P4, PmiAlUnisAm_Tc1Tw2S0P5, PmiAlUnisAm_Tc1Tw2S1P0, PmiAlUnisAm_Tc1Tw2S1P1, PmiAlUnisAm_Tc1Tw2S1P2, PmiAlUnisAm_Tc1Tw2S1P3, PmiAlUnisAm_Tc1Tw2S1P4, PmiAlUnisAm_Tc1Tw2S1P5, PmiAlUnisAm_Tc1Tw2S2P0, PmiAlUnisAm_Tc1Tw2S2P1, PmiAlUnisAm_Tc1Tw2S2P2, PmiAlUnisAm_Tc1Tw2S2P3, PmiAlUnisAm_Tc1Tw2S2P4, PmiAlUnisAm_Tc1Tw2S2P5, 
        PmiAlUnisAm_Tc1Tw3S0P0, PmiAlUnisAm_Tc1Tw3S0P1, PmiAlUnisAm_Tc1Tw3S0P2, PmiAlUnisAm_Tc1Tw3S0P3, PmiAlUnisAm_Tc1Tw3S0P4, PmiAlUnisAm_Tc1Tw3S0P5, PmiAlUnisAm_Tc1Tw3S1P0, PmiAlUnisAm_Tc1Tw3S1P1, PmiAlUnisAm_Tc1Tw3S1P2, PmiAlUnisAm_Tc1Tw3S1P3, PmiAlUnisAm_Tc1Tw3S1P4, PmiAlUnisAm_Tc1Tw3S1P5, PmiAlUnisAm_Tc1Tw3S2P0, PmiAlUnisAm_Tc1Tw3S2P1, PmiAlUnisAm_Tc1Tw3S2P2, PmiAlUnisAm_Tc1Tw3S2P3, PmiAlUnisAm_Tc1Tw3S2P4, PmiAlUnisAm_Tc1Tw3S2P5, 
        PmiAlUnisAm_Tc1Tw4S0P0, PmiAlUnisAm_Tc1Tw4S0P1, PmiAlUnisAm_Tc1Tw4S0P2, PmiAlUnisAm_Tc1Tw4S0P3, PmiAlUnisAm_Tc1Tw4S0P4, PmiAlUnisAm_Tc1Tw4S0P5, PmiAlUnisAm_Tc1Tw4S1P0, PmiAlUnisAm_Tc1Tw4S1P1, PmiAlUnisAm_Tc1Tw4S1P2, PmiAlUnisAm_Tc1Tw4S1P3, PmiAlUnisAm_Tc1Tw4S1P4, PmiAlUnisAm_Tc1Tw4S1P5, PmiAlUnisAm_Tc1Tw4S2P0, PmiAlUnisAm_Tc1Tw4S2P1, PmiAlUnisAm_Tc1Tw4S2P2, PmiAlUnisAm_Tc1Tw4S2P3, PmiAlUnisAm_Tc1Tw4S2P4, PmiAlUnisAm_Tc1Tw4S2P5, 
        PmiAlUnisAm_Tc2Tw0S0P0, PmiAlUnisAm_Tc2Tw0S0P1, PmiAlUnisAm_Tc2Tw0S0P2, PmiAlUnisAm_Tc2Tw0S0P3, PmiAlUnisAm_Tc2Tw0S0P4, PmiAlUnisAm_Tc2Tw0S0P5, PmiAlUnisAm_Tc2Tw0S1P0, PmiAlUnisAm_Tc2Tw0S1P1, PmiAlUnisAm_Tc2Tw0S1P2, PmiAlUnisAm_Tc2Tw0S1P3, PmiAlUnisAm_Tc2Tw0S1P4, PmiAlUnisAm_Tc2Tw0S1P5, PmiAlUnisAm_Tc2Tw0S2P0, PmiAlUnisAm_Tc2Tw0S2P1, PmiAlUnisAm_Tc2Tw0S2P2, PmiAlUnisAm_Tc2Tw0S2P3, PmiAlUnisAm_Tc2Tw0S2P4, PmiAlUnisAm_Tc2Tw0S2P5, 
        PmiAlUnisAm_Tc2Tw1S0P0, PmiAlUnisAm_Tc2Tw1S0P1, PmiAlUnisAm_Tc2Tw1S0P2, PmiAlUnisAm_Tc2Tw1S0P3, PmiAlUnisAm_Tc2Tw1S0P4, PmiAlUnisAm_Tc2Tw1S0P5, PmiAlUnisAm_Tc2Tw1S1P0, PmiAlUnisAm_Tc2Tw1S1P1, PmiAlUnisAm_Tc2Tw1S1P2, PmiAlUnisAm_Tc2Tw1S1P3, PmiAlUnisAm_Tc2Tw1S1P4, PmiAlUnisAm_Tc2Tw1S1P5, PmiAlUnisAm_Tc2Tw1S2P0, PmiAlUnisAm_Tc2Tw1S2P1, PmiAlUnisAm_Tc2Tw1S2P2, PmiAlUnisAm_Tc2Tw1S2P3, PmiAlUnisAm_Tc2Tw1S2P4, PmiAlUnisAm_Tc2Tw1S2P5, 
        PmiAlUnisAm_Tc2Tw2S0P0, PmiAlUnisAm_Tc2Tw2S0P1, PmiAlUnisAm_Tc2Tw2S0P2, PmiAlUnisAm_Tc2Tw2S0P3, PmiAlUnisAm_Tc2Tw2S0P4, PmiAlUnisAm_Tc2Tw2S0P5, PmiAlUnisAm_Tc2Tw2S1P0, PmiAlUnisAm_Tc2Tw2S1P1, PmiAlUnisAm_Tc2Tw2S1P2, PmiAlUnisAm_Tc2Tw2S1P3, PmiAlUnisAm_Tc2Tw2S1P4, PmiAlUnisAm_Tc2Tw2S1P5, PmiAlUnisAm_Tc2Tw2S2P0, PmiAlUnisAm_Tc2Tw2S2P1, PmiAlUnisAm_Tc2Tw2S2P2, PmiAlUnisAm_Tc2Tw2S2P3, PmiAlUnisAm_Tc2Tw2S2P4, PmiAlUnisAm_Tc2Tw2S2P5, 
        PmiAlUnisAm_Tc2Tw3S0P0, PmiAlUnisAm_Tc2Tw3S0P1, PmiAlUnisAm_Tc2Tw3S0P2, PmiAlUnisAm_Tc2Tw3S0P3, PmiAlUnisAm_Tc2Tw3S0P4, PmiAlUnisAm_Tc2Tw3S0P5, PmiAlUnisAm_Tc2Tw3S1P0, PmiAlUnisAm_Tc2Tw3S1P1, PmiAlUnisAm_Tc2Tw3S1P2, PmiAlUnisAm_Tc2Tw3S1P3, PmiAlUnisAm_Tc2Tw3S1P4, PmiAlUnisAm_Tc2Tw3S1P5, PmiAlUnisAm_Tc2Tw3S2P0, PmiAlUnisAm_Tc2Tw3S2P1, PmiAlUnisAm_Tc2Tw3S2P2, PmiAlUnisAm_Tc2Tw3S2P3, PmiAlUnisAm_Tc2Tw3S2P4, PmiAlUnisAm_Tc2Tw3S2P5, 
        PmiAlUnisAm_Tc2Tw4S0P0, PmiAlUnisAm_Tc2Tw4S0P1, PmiAlUnisAm_Tc2Tw4S0P2, PmiAlUnisAm_Tc2Tw4S0P3, PmiAlUnisAm_Tc2Tw4S0P4, PmiAlUnisAm_Tc2Tw4S0P5, PmiAlUnisAm_Tc2Tw4S1P0, PmiAlUnisAm_Tc2Tw4S1P1, PmiAlUnisAm_Tc2Tw4S1P2, PmiAlUnisAm_Tc2Tw4S1P3, PmiAlUnisAm_Tc2Tw4S1P4, PmiAlUnisAm_Tc2Tw4S1P5, PmiAlUnisAm_Tc2Tw4S2P0, PmiAlUnisAm_Tc2Tw4S2P1, PmiAlUnisAm_Tc2Tw4S2P2, PmiAlUnisAm_Tc2Tw4S2P3, PmiAlUnisAm_Tc2Tw4S2P4, PmiAlUnisAm_Tc2Tw4S2P5, 
        PmiAlUnisAm_Tc3Tw0S0P0, PmiAlUnisAm_Tc3Tw0S0P1, PmiAlUnisAm_Tc3Tw0S0P2, PmiAlUnisAm_Tc3Tw0S0P3, PmiAlUnisAm_Tc3Tw0S0P4, PmiAlUnisAm_Tc3Tw0S0P5, PmiAlUnisAm_Tc3Tw0S1P0, PmiAlUnisAm_Tc3Tw0S1P1, PmiAlUnisAm_Tc3Tw0S1P2, PmiAlUnisAm_Tc3Tw0S1P3, PmiAlUnisAm_Tc3Tw0S1P4, PmiAlUnisAm_Tc3Tw0S1P5, PmiAlUnisAm_Tc3Tw0S2P0, PmiAlUnisAm_Tc3Tw0S2P1, PmiAlUnisAm_Tc3Tw0S2P2, PmiAlUnisAm_Tc3Tw0S2P3, PmiAlUnisAm_Tc3Tw0S2P4, PmiAlUnisAm_Tc3Tw0S2P5, 
        PmiAlUnisAm_Tc3Tw1S0P0, PmiAlUnisAm_Tc3Tw1S0P1, PmiAlUnisAm_Tc3Tw1S0P2, PmiAlUnisAm_Tc3Tw1S0P3, PmiAlUnisAm_Tc3Tw1S0P4, PmiAlUnisAm_Tc3Tw1S0P5, PmiAlUnisAm_Tc3Tw1S1P0, PmiAlUnisAm_Tc3Tw1S1P1, PmiAlUnisAm_Tc3Tw1S1P2, PmiAlUnisAm_Tc3Tw1S1P3, PmiAlUnisAm_Tc3Tw1S1P4, PmiAlUnisAm_Tc3Tw1S1P5, PmiAlUnisAm_Tc3Tw1S2P0, PmiAlUnisAm_Tc3Tw1S2P1, PmiAlUnisAm_Tc3Tw1S2P2, PmiAlUnisAm_Tc3Tw1S2P3, PmiAlUnisAm_Tc3Tw1S2P4, PmiAlUnisAm_Tc3Tw1S2P5, 
        PmiAlUnisAm_Tc3Tw2S0P0, PmiAlUnisAm_Tc3Tw2S0P1, PmiAlUnisAm_Tc3Tw2S0P2, PmiAlUnisAm_Tc3Tw2S0P3, PmiAlUnisAm_Tc3Tw2S0P4, PmiAlUnisAm_Tc3Tw2S0P5, PmiAlUnisAm_Tc3Tw2S1P0, PmiAlUnisAm_Tc3Tw2S1P1, PmiAlUnisAm_Tc3Tw2S1P2, PmiAlUnisAm_Tc3Tw2S1P3, PmiAlUnisAm_Tc3Tw2S1P4, PmiAlUnisAm_Tc3Tw2S1P5, PmiAlUnisAm_Tc3Tw2S2P0, PmiAlUnisAm_Tc3Tw2S2P1, PmiAlUnisAm_Tc3Tw2S2P2, PmiAlUnisAm_Tc3Tw2S2P3, PmiAlUnisAm_Tc3Tw2S2P4, PmiAlUnisAm_Tc3Tw2S2P5, 
        PmiAlUnisAm_Tc3Tw3S0P0, PmiAlUnisAm_Tc3Tw3S0P1, PmiAlUnisAm_Tc3Tw3S0P2, PmiAlUnisAm_Tc3Tw3S0P3, PmiAlUnisAm_Tc3Tw3S0P4, PmiAlUnisAm_Tc3Tw3S0P5, PmiAlUnisAm_Tc3Tw3S1P0, PmiAlUnisAm_Tc3Tw3S1P1, PmiAlUnisAm_Tc3Tw3S1P2, PmiAlUnisAm_Tc3Tw3S1P3, PmiAlUnisAm_Tc3Tw3S1P4, PmiAlUnisAm_Tc3Tw3S1P5, PmiAlUnisAm_Tc3Tw3S2P0, PmiAlUnisAm_Tc3Tw3S2P1, PmiAlUnisAm_Tc3Tw3S2P2, PmiAlUnisAm_Tc3Tw3S2P3, PmiAlUnisAm_Tc3Tw3S2P4, PmiAlUnisAm_Tc3Tw3S2P5, 
        PmiAlUnisAm_Tc3Tw4S0P0, PmiAlUnisAm_Tc3Tw4S0P1, PmiAlUnisAm_Tc3Tw4S0P2, PmiAlUnisAm_Tc3Tw4S0P3, PmiAlUnisAm_Tc3Tw4S0P4, PmiAlUnisAm_Tc3Tw4S0P5, PmiAlUnisAm_Tc3Tw4S1P0, PmiAlUnisAm_Tc3Tw4S1P1, PmiAlUnisAm_Tc3Tw4S1P2, PmiAlUnisAm_Tc3Tw4S1P3, PmiAlUnisAm_Tc3Tw4S1P4, PmiAlUnisAm_Tc3Tw4S1P5, PmiAlUnisAm_Tc3Tw4S2P0, PmiAlUnisAm_Tc3Tw4S2P1, PmiAlUnisAm_Tc3Tw4S2P2, PmiAlUnisAm_Tc3Tw4S2P3, PmiAlUnisAm_Tc3Tw4S2P4, PmiAlUnisAm_Tc3Tw4S2P5, 
        PmiAlUnisAm_Tc4Tw0S0P0, PmiAlUnisAm_Tc4Tw0S0P1, PmiAlUnisAm_Tc4Tw0S0P2, PmiAlUnisAm_Tc4Tw0S0P3, PmiAlUnisAm_Tc4Tw0S0P4, PmiAlUnisAm_Tc4Tw0S0P5, PmiAlUnisAm_Tc4Tw0S1P0, PmiAlUnisAm_Tc4Tw0S1P1, PmiAlUnisAm_Tc4Tw0S1P2, PmiAlUnisAm_Tc4Tw0S1P3, PmiAlUnisAm_Tc4Tw0S1P4, PmiAlUnisAm_Tc4Tw0S1P5, PmiAlUnisAm_Tc4Tw0S2P0, PmiAlUnisAm_Tc4Tw0S2P1, PmiAlUnisAm_Tc4Tw0S2P2, PmiAlUnisAm_Tc4Tw0S2P3, PmiAlUnisAm_Tc4Tw0S2P4, PmiAlUnisAm_Tc4Tw0S2P5, 
        PmiAlUnisAm_Tc4Tw1S0P0, PmiAlUnisAm_Tc4Tw1S0P1, PmiAlUnisAm_Tc4Tw1S0P2, PmiAlUnisAm_Tc4Tw1S0P3, PmiAlUnisAm_Tc4Tw1S0P4, PmiAlUnisAm_Tc4Tw1S0P5, PmiAlUnisAm_Tc4Tw1S1P0, PmiAlUnisAm_Tc4Tw1S1P1, PmiAlUnisAm_Tc4Tw1S1P2, PmiAlUnisAm_Tc4Tw1S1P3, PmiAlUnisAm_Tc4Tw1S1P4, PmiAlUnisAm_Tc4Tw1S1P5, PmiAlUnisAm_Tc4Tw1S2P0, PmiAlUnisAm_Tc4Tw1S2P1, PmiAlUnisAm_Tc4Tw1S2P2, PmiAlUnisAm_Tc4Tw1S2P3, PmiAlUnisAm_Tc4Tw1S2P4, PmiAlUnisAm_Tc4Tw1S2P5, 
        PmiAlUnisAm_Tc4Tw2S0P0, PmiAlUnisAm_Tc4Tw2S0P1, PmiAlUnisAm_Tc4Tw2S0P2, PmiAlUnisAm_Tc4Tw2S0P3, PmiAlUnisAm_Tc4Tw2S0P4, PmiAlUnisAm_Tc4Tw2S0P5, PmiAlUnisAm_Tc4Tw2S1P0, PmiAlUnisAm_Tc4Tw2S1P1, PmiAlUnisAm_Tc4Tw2S1P2, PmiAlUnisAm_Tc4Tw2S1P3, PmiAlUnisAm_Tc4Tw2S1P4, PmiAlUnisAm_Tc4Tw2S1P5, PmiAlUnisAm_Tc4Tw2S2P0, PmiAlUnisAm_Tc4Tw2S2P1, PmiAlUnisAm_Tc4Tw2S2P2, PmiAlUnisAm_Tc4Tw2S2P3, PmiAlUnisAm_Tc4Tw2S2P4, PmiAlUnisAm_Tc4Tw2S2P5, 
        PmiAlUnisAm_Tc4Tw3S0P0, PmiAlUnisAm_Tc4Tw3S0P1, PmiAlUnisAm_Tc4Tw3S0P2, PmiAlUnisAm_Tc4Tw3S0P3, PmiAlUnisAm_Tc4Tw3S0P4, PmiAlUnisAm_Tc4Tw3S0P5, PmiAlUnisAm_Tc4Tw3S1P0, PmiAlUnisAm_Tc4Tw3S1P1, PmiAlUnisAm_Tc4Tw3S1P2, PmiAlUnisAm_Tc4Tw3S1P3, PmiAlUnisAm_Tc4Tw3S1P4, PmiAlUnisAm_Tc4Tw3S1P5, PmiAlUnisAm_Tc4Tw3S2P0, PmiAlUnisAm_Tc4Tw3S2P1, PmiAlUnisAm_Tc4Tw3S2P2, PmiAlUnisAm_Tc4Tw3S2P3, PmiAlUnisAm_Tc4Tw3S2P4, PmiAlUnisAm_Tc4Tw3S2P5, 
        PmiAlUnisAm_Tc4Tw4S0P0, PmiAlUnisAm_Tc4Tw4S0P1, PmiAlUnisAm_Tc4Tw4S0P2, PmiAlUnisAm_Tc4Tw4S0P3, PmiAlUnisAm_Tc4Tw4S0P4, PmiAlUnisAm_Tc4Tw4S0P5, PmiAlUnisAm_Tc4Tw4S1P0, PmiAlUnisAm_Tc4Tw4S1P1, PmiAlUnisAm_Tc4Tw4S1P2, PmiAlUnisAm_Tc4Tw4S1P3, PmiAlUnisAm_Tc4Tw4S1P4, PmiAlUnisAm_Tc4Tw4S1P5, PmiAlUnisAm_Tc4Tw4S2P0, PmiAlUnisAm_Tc4Tw4S2P1, PmiAlUnisAm_Tc4Tw4S2P2, PmiAlUnisAm_Tc4Tw4S2P3, PmiAlUnisAm_Tc4Tw4S2P4, PmiAlUnisAm_Tc4Tw4S2P5, 
        NPmi_Tc0Tw0S0P0, NPmi_Tc0Tw0S0P1, NPmi_Tc0Tw0S0P2, NPmi_Tc0Tw0S0P3, NPmi_Tc0Tw0S0P4, NPmi_Tc0Tw0S0P5, NPmi_Tc0Tw0S1P0, NPmi_Tc0Tw0S1P1, NPmi_Tc0Tw0S1P2, NPmi_Tc0Tw0S1P3, NPmi_Tc0Tw0S1P4, NPmi_Tc0Tw0S1P5, NPmi_Tc0Tw0S2P0, NPmi_Tc0Tw0S2P1, NPmi_Tc0Tw0S2P2, NPmi_Tc0Tw0S2P3, NPmi_Tc0Tw0S2P4, NPmi_Tc0Tw0S2P5, 
        NPmi_Tc0Tw1S0P0, NPmi_Tc0Tw1S0P1, NPmi_Tc0Tw1S0P2, NPmi_Tc0Tw1S0P3, NPmi_Tc0Tw1S0P4, NPmi_Tc0Tw1S0P5, NPmi_Tc0Tw1S1P0, NPmi_Tc0Tw1S1P1, NPmi_Tc0Tw1S1P2, NPmi_Tc0Tw1S1P3, NPmi_Tc0Tw1S1P4, NPmi_Tc0Tw1S1P5, NPmi_Tc0Tw1S2P0, NPmi_Tc0Tw1S2P1, NPmi_Tc0Tw1S2P2, NPmi_Tc0Tw1S2P3, NPmi_Tc0Tw1S2P4, NPmi_Tc0Tw1S2P5, 
        NPmi_Tc0Tw2S0P0, NPmi_Tc0Tw2S0P1, NPmi_Tc0Tw2S0P2, NPmi_Tc0Tw2S0P3, NPmi_Tc0Tw2S0P4, NPmi_Tc0Tw2S0P5, NPmi_Tc0Tw2S1P0, NPmi_Tc0Tw2S1P1, NPmi_Tc0Tw2S1P2, NPmi_Tc0Tw2S1P3, NPmi_Tc0Tw2S1P4, NPmi_Tc0Tw2S1P5, NPmi_Tc0Tw2S2P0, NPmi_Tc0Tw2S2P1, NPmi_Tc0Tw2S2P2, NPmi_Tc0Tw2S2P3, NPmi_Tc0Tw2S2P4, NPmi_Tc0Tw2S2P5, 
        NPmi_Tc0Tw3S0P0, NPmi_Tc0Tw3S0P1, NPmi_Tc0Tw3S0P2, NPmi_Tc0Tw3S0P3, NPmi_Tc0Tw3S0P4, NPmi_Tc0Tw3S0P5, NPmi_Tc0Tw3S1P0, NPmi_Tc0Tw3S1P1, NPmi_Tc0Tw3S1P2, NPmi_Tc0Tw3S1P3, NPmi_Tc0Tw3S1P4, NPmi_Tc0Tw3S1P5, NPmi_Tc0Tw3S2P0, NPmi_Tc0Tw3S2P1, NPmi_Tc0Tw3S2P2, NPmi_Tc0Tw3S2P3, NPmi_Tc0Tw3S2P4, NPmi_Tc0Tw3S2P5, 
        NPmi_Tc0Tw4S0P0, NPmi_Tc0Tw4S0P1, NPmi_Tc0Tw4S0P2, NPmi_Tc0Tw4S0P3, NPmi_Tc0Tw4S0P4, NPmi_Tc0Tw4S0P5, NPmi_Tc0Tw4S1P0, NPmi_Tc0Tw4S1P1, NPmi_Tc0Tw4S1P2, NPmi_Tc0Tw4S1P3, NPmi_Tc0Tw4S1P4, NPmi_Tc0Tw4S1P5, NPmi_Tc0Tw4S2P0, NPmi_Tc0Tw4S2P1, NPmi_Tc0Tw4S2P2, NPmi_Tc0Tw4S2P3, NPmi_Tc0Tw4S2P4, NPmi_Tc0Tw4S2P5, 
        NPmi_Tc1Tw0S0P0, NPmi_Tc1Tw0S0P1, NPmi_Tc1Tw0S0P2, NPmi_Tc1Tw0S0P3, NPmi_Tc1Tw0S0P4, NPmi_Tc1Tw0S0P5, NPmi_Tc1Tw0S1P0, NPmi_Tc1Tw0S1P1, NPmi_Tc1Tw0S1P2, NPmi_Tc1Tw0S1P3, NPmi_Tc1Tw0S1P4, NPmi_Tc1Tw0S1P5, NPmi_Tc1Tw0S2P0, NPmi_Tc1Tw0S2P1, NPmi_Tc1Tw0S2P2, NPmi_Tc1Tw0S2P3, NPmi_Tc1Tw0S2P4, NPmi_Tc1Tw0S2P5, 
        NPmi_Tc1Tw1S0P0, NPmi_Tc1Tw1S0P1, NPmi_Tc1Tw1S0P2, NPmi_Tc1Tw1S0P3, NPmi_Tc1Tw1S0P4, NPmi_Tc1Tw1S0P5, NPmi_Tc1Tw1S1P0, NPmi_Tc1Tw1S1P1, NPmi_Tc1Tw1S1P2, NPmi_Tc1Tw1S1P3, NPmi_Tc1Tw1S1P4, NPmi_Tc1Tw1S1P5, NPmi_Tc1Tw1S2P0, NPmi_Tc1Tw1S2P1, NPmi_Tc1Tw1S2P2, NPmi_Tc1Tw1S2P3, NPmi_Tc1Tw1S2P4, NPmi_Tc1Tw1S2P5, 
        NPmi_Tc1Tw2S0P0, NPmi_Tc1Tw2S0P1, NPmi_Tc1Tw2S0P2, NPmi_Tc1Tw2S0P3, NPmi_Tc1Tw2S0P4, NPmi_Tc1Tw2S0P5, NPmi_Tc1Tw2S1P0, NPmi_Tc1Tw2S1P1, NPmi_Tc1Tw2S1P2, NPmi_Tc1Tw2S1P3, NPmi_Tc1Tw2S1P4, NPmi_Tc1Tw2S1P5, NPmi_Tc1Tw2S2P0, NPmi_Tc1Tw2S2P1, NPmi_Tc1Tw2S2P2, NPmi_Tc1Tw2S2P3, NPmi_Tc1Tw2S2P4, NPmi_Tc1Tw2S2P5, 
        NPmi_Tc1Tw3S0P0, NPmi_Tc1Tw3S0P1, NPmi_Tc1Tw3S0P2, NPmi_Tc1Tw3S0P3, NPmi_Tc1Tw3S0P4, NPmi_Tc1Tw3S0P5, NPmi_Tc1Tw3S1P0, NPmi_Tc1Tw3S1P1, NPmi_Tc1Tw3S1P2, NPmi_Tc1Tw3S1P3, NPmi_Tc1Tw3S1P4, NPmi_Tc1Tw3S1P5, NPmi_Tc1Tw3S2P0, NPmi_Tc1Tw3S2P1, NPmi_Tc1Tw3S2P2, NPmi_Tc1Tw3S2P3, NPmi_Tc1Tw3S2P4, NPmi_Tc1Tw3S2P5, 
        NPmi_Tc1Tw4S0P0, NPmi_Tc1Tw4S0P1, NPmi_Tc1Tw4S0P2, NPmi_Tc1Tw4S0P3, NPmi_Tc1Tw4S0P4, NPmi_Tc1Tw4S0P5, NPmi_Tc1Tw4S1P0, NPmi_Tc1Tw4S1P1, NPmi_Tc1Tw4S1P2, NPmi_Tc1Tw4S1P3, NPmi_Tc1Tw4S1P4, NPmi_Tc1Tw4S1P5, NPmi_Tc1Tw4S2P0, NPmi_Tc1Tw4S2P1, NPmi_Tc1Tw4S2P2, NPmi_Tc1Tw4S2P3, NPmi_Tc1Tw4S2P4, NPmi_Tc1Tw4S2P5, 
        NPmi_Tc2Tw0S0P0, NPmi_Tc2Tw0S0P1, NPmi_Tc2Tw0S0P2, NPmi_Tc2Tw0S0P3, NPmi_Tc2Tw0S0P4, NPmi_Tc2Tw0S0P5, NPmi_Tc2Tw0S1P0, NPmi_Tc2Tw0S1P1, NPmi_Tc2Tw0S1P2, NPmi_Tc2Tw0S1P3, NPmi_Tc2Tw0S1P4, NPmi_Tc2Tw0S1P5, NPmi_Tc2Tw0S2P0, NPmi_Tc2Tw0S2P1, NPmi_Tc2Tw0S2P2, NPmi_Tc2Tw0S2P3, NPmi_Tc2Tw0S2P4, NPmi_Tc2Tw0S2P5, 
        NPmi_Tc2Tw1S0P0, NPmi_Tc2Tw1S0P1, NPmi_Tc2Tw1S0P2, NPmi_Tc2Tw1S0P3, NPmi_Tc2Tw1S0P4, NPmi_Tc2Tw1S0P5, NPmi_Tc2Tw1S1P0, NPmi_Tc2Tw1S1P1, NPmi_Tc2Tw1S1P2, NPmi_Tc2Tw1S1P3, NPmi_Tc2Tw1S1P4, NPmi_Tc2Tw1S1P5, NPmi_Tc2Tw1S2P0, NPmi_Tc2Tw1S2P1, NPmi_Tc2Tw1S2P2, NPmi_Tc2Tw1S2P3, NPmi_Tc2Tw1S2P4, NPmi_Tc2Tw1S2P5, 
        NPmi_Tc2Tw2S0P0, NPmi_Tc2Tw2S0P1, NPmi_Tc2Tw2S0P2, NPmi_Tc2Tw2S0P3, NPmi_Tc2Tw2S0P4, NPmi_Tc2Tw2S0P5, NPmi_Tc2Tw2S1P0, NPmi_Tc2Tw2S1P1, NPmi_Tc2Tw2S1P2, NPmi_Tc2Tw2S1P3, NPmi_Tc2Tw2S1P4, NPmi_Tc2Tw2S1P5, NPmi_Tc2Tw2S2P0, NPmi_Tc2Tw2S2P1, NPmi_Tc2Tw2S2P2, NPmi_Tc2Tw2S2P3, NPmi_Tc2Tw2S2P4, NPmi_Tc2Tw2S2P5, 
        NPmi_Tc2Tw3S0P0, NPmi_Tc2Tw3S0P1, NPmi_Tc2Tw3S0P2, NPmi_Tc2Tw3S0P3, NPmi_Tc2Tw3S0P4, NPmi_Tc2Tw3S0P5, NPmi_Tc2Tw3S1P0, NPmi_Tc2Tw3S1P1, NPmi_Tc2Tw3S1P2, NPmi_Tc2Tw3S1P3, NPmi_Tc2Tw3S1P4, NPmi_Tc2Tw3S1P5, NPmi_Tc2Tw3S2P0, NPmi_Tc2Tw3S2P1, NPmi_Tc2Tw3S2P2, NPmi_Tc2Tw3S2P3, NPmi_Tc2Tw3S2P4, NPmi_Tc2Tw3S2P5, 
        NPmi_Tc2Tw4S0P0, NPmi_Tc2Tw4S0P1, NPmi_Tc2Tw4S0P2, NPmi_Tc2Tw4S0P3, NPmi_Tc2Tw4S0P4, NPmi_Tc2Tw4S0P5, NPmi_Tc2Tw4S1P0, NPmi_Tc2Tw4S1P1, NPmi_Tc2Tw4S1P2, NPmi_Tc2Tw4S1P3, NPmi_Tc2Tw4S1P4, NPmi_Tc2Tw4S1P5, NPmi_Tc2Tw4S2P0, NPmi_Tc2Tw4S2P1, NPmi_Tc2Tw4S2P2, NPmi_Tc2Tw4S2P3, NPmi_Tc2Tw4S2P4, NPmi_Tc2Tw4S2P5, 
        NPmi_Tc3Tw0S0P0, NPmi_Tc3Tw0S0P1, NPmi_Tc3Tw0S0P2, NPmi_Tc3Tw0S0P3, NPmi_Tc3Tw0S0P4, NPmi_Tc3Tw0S0P5, NPmi_Tc3Tw0S1P0, NPmi_Tc3Tw0S1P1, NPmi_Tc3Tw0S1P2, NPmi_Tc3Tw0S1P3, NPmi_Tc3Tw0S1P4, NPmi_Tc3Tw0S1P5, NPmi_Tc3Tw0S2P0, NPmi_Tc3Tw0S2P1, NPmi_Tc3Tw0S2P2, NPmi_Tc3Tw0S2P3, NPmi_Tc3Tw0S2P4, NPmi_Tc3Tw0S2P5, 
        NPmi_Tc3Tw1S0P0, NPmi_Tc3Tw1S0P1, NPmi_Tc3Tw1S0P2, NPmi_Tc3Tw1S0P3, NPmi_Tc3Tw1S0P4, NPmi_Tc3Tw1S0P5, NPmi_Tc3Tw1S1P0, NPmi_Tc3Tw1S1P1, NPmi_Tc3Tw1S1P2, NPmi_Tc3Tw1S1P3, NPmi_Tc3Tw1S1P4, NPmi_Tc3Tw1S1P5, NPmi_Tc3Tw1S2P0, NPmi_Tc3Tw1S2P1, NPmi_Tc3Tw1S2P2, NPmi_Tc3Tw1S2P3, NPmi_Tc3Tw1S2P4, NPmi_Tc3Tw1S2P5, 
        NPmi_Tc3Tw2S0P0, NPmi_Tc3Tw2S0P1, NPmi_Tc3Tw2S0P2, NPmi_Tc3Tw2S0P3, NPmi_Tc3Tw2S0P4, NPmi_Tc3Tw2S0P5, NPmi_Tc3Tw2S1P0, NPmi_Tc3Tw2S1P1, NPmi_Tc3Tw2S1P2, NPmi_Tc3Tw2S1P3, NPmi_Tc3Tw2S1P4, NPmi_Tc3Tw2S1P5, NPmi_Tc3Tw2S2P0, NPmi_Tc3Tw2S2P1, NPmi_Tc3Tw2S2P2, NPmi_Tc3Tw2S2P3, NPmi_Tc3Tw2S2P4, NPmi_Tc3Tw2S2P5, 
        NPmi_Tc3Tw3S0P0, NPmi_Tc3Tw3S0P1, NPmi_Tc3Tw3S0P2, NPmi_Tc3Tw3S0P3, NPmi_Tc3Tw3S0P4, NPmi_Tc3Tw3S0P5, NPmi_Tc3Tw3S1P0, NPmi_Tc3Tw3S1P1, NPmi_Tc3Tw3S1P2, NPmi_Tc3Tw3S1P3, NPmi_Tc3Tw3S1P4, NPmi_Tc3Tw3S1P5, NPmi_Tc3Tw3S2P0, NPmi_Tc3Tw3S2P1, NPmi_Tc3Tw3S2P2, NPmi_Tc3Tw3S2P3, NPmi_Tc3Tw3S2P4, NPmi_Tc3Tw3S2P5, 
        NPmi_Tc3Tw4S0P0, NPmi_Tc3Tw4S0P1, NPmi_Tc3Tw4S0P2, NPmi_Tc3Tw4S0P3, NPmi_Tc3Tw4S0P4, NPmi_Tc3Tw4S0P5, NPmi_Tc3Tw4S1P0, NPmi_Tc3Tw4S1P1, NPmi_Tc3Tw4S1P2, NPmi_Tc3Tw4S1P3, NPmi_Tc3Tw4S1P4, NPmi_Tc3Tw4S1P5, NPmi_Tc3Tw4S2P0, NPmi_Tc3Tw4S2P1, NPmi_Tc3Tw4S2P2, NPmi_Tc3Tw4S2P3, NPmi_Tc3Tw4S2P4, NPmi_Tc3Tw4S2P5, 
        NPmi_Tc4Tw0S0P0, NPmi_Tc4Tw0S0P1, NPmi_Tc4Tw0S0P2, NPmi_Tc4Tw0S0P3, NPmi_Tc4Tw0S0P4, NPmi_Tc4Tw0S0P5, NPmi_Tc4Tw0S1P0, NPmi_Tc4Tw0S1P1, NPmi_Tc4Tw0S1P2, NPmi_Tc4Tw0S1P3, NPmi_Tc4Tw0S1P4, NPmi_Tc4Tw0S1P5, NPmi_Tc4Tw0S2P0, NPmi_Tc4Tw0S2P1, NPmi_Tc4Tw0S2P2, NPmi_Tc4Tw0S2P3, NPmi_Tc4Tw0S2P4, NPmi_Tc4Tw0S2P5, 
        NPmi_Tc4Tw1S0P0, NPmi_Tc4Tw1S0P1, NPmi_Tc4Tw1S0P2, NPmi_Tc4Tw1S0P3, NPmi_Tc4Tw1S0P4, NPmi_Tc4Tw1S0P5, NPmi_Tc4Tw1S1P0, NPmi_Tc4Tw1S1P1, NPmi_Tc4Tw1S1P2, NPmi_Tc4Tw1S1P3, NPmi_Tc4Tw1S1P4, NPmi_Tc4Tw1S1P5, NPmi_Tc4Tw1S2P0, NPmi_Tc4Tw1S2P1, NPmi_Tc4Tw1S2P2, NPmi_Tc4Tw1S2P3, NPmi_Tc4Tw1S2P4, NPmi_Tc4Tw1S2P5, 
        NPmi_Tc4Tw2S0P0, NPmi_Tc4Tw2S0P1, NPmi_Tc4Tw2S0P2, NPmi_Tc4Tw2S0P3, NPmi_Tc4Tw2S0P4, NPmi_Tc4Tw2S0P5, NPmi_Tc4Tw2S1P0, NPmi_Tc4Tw2S1P1, NPmi_Tc4Tw2S1P2, NPmi_Tc4Tw2S1P3, NPmi_Tc4Tw2S1P4, NPmi_Tc4Tw2S1P5, NPmi_Tc4Tw2S2P0, NPmi_Tc4Tw2S2P1, NPmi_Tc4Tw2S2P2, NPmi_Tc4Tw2S2P3, NPmi_Tc4Tw2S2P4, NPmi_Tc4Tw2S2P5, 
        NPmi_Tc4Tw3S0P0, NPmi_Tc4Tw3S0P1, NPmi_Tc4Tw3S0P2, NPmi_Tc4Tw3S0P3, NPmi_Tc4Tw3S0P4, NPmi_Tc4Tw3S0P5, NPmi_Tc4Tw3S1P0, NPmi_Tc4Tw3S1P1, NPmi_Tc4Tw3S1P2, NPmi_Tc4Tw3S1P3, NPmi_Tc4Tw3S1P4, NPmi_Tc4Tw3S1P5, NPmi_Tc4Tw3S2P0, NPmi_Tc4Tw3S2P1, NPmi_Tc4Tw3S2P2, NPmi_Tc4Tw3S2P3, NPmi_Tc4Tw3S2P4, NPmi_Tc4Tw3S2P5, 
        NPmi_Tc4Tw4S0P0, NPmi_Tc4Tw4S0P1, NPmi_Tc4Tw4S0P2, NPmi_Tc4Tw4S0P3, NPmi_Tc4Tw4S0P4, NPmi_Tc4Tw4S0P5, NPmi_Tc4Tw4S1P0, NPmi_Tc4Tw4S1P1, NPmi_Tc4Tw4S1P2, NPmi_Tc4Tw4S1P3, NPmi_Tc4Tw4S1P4, NPmi_Tc4Tw4S1P5, NPmi_Tc4Tw4S2P0, NPmi_Tc4Tw4S2P1, NPmi_Tc4Tw4S2P2, NPmi_Tc4Tw4S2P3, NPmi_Tc4Tw4S2P4, NPmi_Tc4Tw4S2P5, 
        PmiWdf_Tc0Tw0S0P0, PmiWdf_Tc0Tw0S0P1, PmiWdf_Tc0Tw0S0P2, PmiWdf_Tc0Tw0S0P3, PmiWdf_Tc0Tw0S0P4, PmiWdf_Tc0Tw0S0P5, PmiWdf_Tc0Tw0S1P0, PmiWdf_Tc0Tw0S1P1, PmiWdf_Tc0Tw0S1P2, PmiWdf_Tc0Tw0S1P3, PmiWdf_Tc0Tw0S1P4, PmiWdf_Tc0Tw0S1P5, PmiWdf_Tc0Tw0S2P0, PmiWdf_Tc0Tw0S2P1, PmiWdf_Tc0Tw0S2P2, PmiWdf_Tc0Tw0S2P3, PmiWdf_Tc0Tw0S2P4, PmiWdf_Tc0Tw0S2P5, 
        PmiWdf_Tc0Tw1S0P0, PmiWdf_Tc0Tw1S0P1, PmiWdf_Tc0Tw1S0P2, PmiWdf_Tc0Tw1S0P3, PmiWdf_Tc0Tw1S0P4, PmiWdf_Tc0Tw1S0P5, PmiWdf_Tc0Tw1S1P0, PmiWdf_Tc0Tw1S1P1, PmiWdf_Tc0Tw1S1P2, PmiWdf_Tc0Tw1S1P3, PmiWdf_Tc0Tw1S1P4, PmiWdf_Tc0Tw1S1P5, PmiWdf_Tc0Tw1S2P0, PmiWdf_Tc0Tw1S2P1, PmiWdf_Tc0Tw1S2P2, PmiWdf_Tc0Tw1S2P3, PmiWdf_Tc0Tw1S2P4, PmiWdf_Tc0Tw1S2P5, 
        PmiWdf_Tc0Tw2S0P0, PmiWdf_Tc0Tw2S0P1, PmiWdf_Tc0Tw2S0P2, PmiWdf_Tc0Tw2S0P3, PmiWdf_Tc0Tw2S0P4, PmiWdf_Tc0Tw2S0P5, PmiWdf_Tc0Tw2S1P0, PmiWdf_Tc0Tw2S1P1, PmiWdf_Tc0Tw2S1P2, PmiWdf_Tc0Tw2S1P3, PmiWdf_Tc0Tw2S1P4, PmiWdf_Tc0Tw2S1P5, PmiWdf_Tc0Tw2S2P0, PmiWdf_Tc0Tw2S2P1, PmiWdf_Tc0Tw2S2P2, PmiWdf_Tc0Tw2S2P3, PmiWdf_Tc0Tw2S2P4, PmiWdf_Tc0Tw2S2P5, 
        PmiWdf_Tc0Tw3S0P0, PmiWdf_Tc0Tw3S0P1, PmiWdf_Tc0Tw3S0P2, PmiWdf_Tc0Tw3S0P3, PmiWdf_Tc0Tw3S0P4, PmiWdf_Tc0Tw3S0P5, PmiWdf_Tc0Tw3S1P0, PmiWdf_Tc0Tw3S1P1, PmiWdf_Tc0Tw3S1P2, PmiWdf_Tc0Tw3S1P3, PmiWdf_Tc0Tw3S1P4, PmiWdf_Tc0Tw3S1P5, PmiWdf_Tc0Tw3S2P0, PmiWdf_Tc0Tw3S2P1, PmiWdf_Tc0Tw3S2P2, PmiWdf_Tc0Tw3S2P3, PmiWdf_Tc0Tw3S2P4, PmiWdf_Tc0Tw3S2P5, 
        PmiWdf_Tc0Tw4S0P0, PmiWdf_Tc0Tw4S0P1, PmiWdf_Tc0Tw4S0P2, PmiWdf_Tc0Tw4S0P3, PmiWdf_Tc0Tw4S0P4, PmiWdf_Tc0Tw4S0P5, PmiWdf_Tc0Tw4S1P0, PmiWdf_Tc0Tw4S1P1, PmiWdf_Tc0Tw4S1P2, PmiWdf_Tc0Tw4S1P3, PmiWdf_Tc0Tw4S1P4, PmiWdf_Tc0Tw4S1P5, PmiWdf_Tc0Tw4S2P0, PmiWdf_Tc0Tw4S2P1, PmiWdf_Tc0Tw4S2P2, PmiWdf_Tc0Tw4S2P3, PmiWdf_Tc0Tw4S2P4, PmiWdf_Tc0Tw4S2P5, 
        PmiWdf_Tc1Tw0S0P0, PmiWdf_Tc1Tw0S0P1, PmiWdf_Tc1Tw0S0P2, PmiWdf_Tc1Tw0S0P3, PmiWdf_Tc1Tw0S0P4, PmiWdf_Tc1Tw0S0P5, PmiWdf_Tc1Tw0S1P0, PmiWdf_Tc1Tw0S1P1, PmiWdf_Tc1Tw0S1P2, PmiWdf_Tc1Tw0S1P3, PmiWdf_Tc1Tw0S1P4, PmiWdf_Tc1Tw0S1P5, PmiWdf_Tc1Tw0S2P0, PmiWdf_Tc1Tw0S2P1, PmiWdf_Tc1Tw0S2P2, PmiWdf_Tc1Tw0S2P3, PmiWdf_Tc1Tw0S2P4, PmiWdf_Tc1Tw0S2P5, 
        PmiWdf_Tc1Tw1S0P0, PmiWdf_Tc1Tw1S0P1, PmiWdf_Tc1Tw1S0P2, PmiWdf_Tc1Tw1S0P3, PmiWdf_Tc1Tw1S0P4, PmiWdf_Tc1Tw1S0P5, PmiWdf_Tc1Tw1S1P0, PmiWdf_Tc1Tw1S1P1, PmiWdf_Tc1Tw1S1P2, PmiWdf_Tc1Tw1S1P3, PmiWdf_Tc1Tw1S1P4, PmiWdf_Tc1Tw1S1P5, PmiWdf_Tc1Tw1S2P0, PmiWdf_Tc1Tw1S2P1, PmiWdf_Tc1Tw1S2P2, PmiWdf_Tc1Tw1S2P3, PmiWdf_Tc1Tw1S2P4, PmiWdf_Tc1Tw1S2P5, 
        PmiWdf_Tc1Tw2S0P0, PmiWdf_Tc1Tw2S0P1, PmiWdf_Tc1Tw2S0P2, PmiWdf_Tc1Tw2S0P3, PmiWdf_Tc1Tw2S0P4, PmiWdf_Tc1Tw2S0P5, PmiWdf_Tc1Tw2S1P0, PmiWdf_Tc1Tw2S1P1, PmiWdf_Tc1Tw2S1P2, PmiWdf_Tc1Tw2S1P3, PmiWdf_Tc1Tw2S1P4, PmiWdf_Tc1Tw2S1P5, PmiWdf_Tc1Tw2S2P0, PmiWdf_Tc1Tw2S2P1, PmiWdf_Tc1Tw2S2P2, PmiWdf_Tc1Tw2S2P3, PmiWdf_Tc1Tw2S2P4, PmiWdf_Tc1Tw2S2P5, 
        PmiWdf_Tc1Tw3S0P0, PmiWdf_Tc1Tw3S0P1, PmiWdf_Tc1Tw3S0P2, PmiWdf_Tc1Tw3S0P3, PmiWdf_Tc1Tw3S0P4, PmiWdf_Tc1Tw3S0P5, PmiWdf_Tc1Tw3S1P0, PmiWdf_Tc1Tw3S1P1, PmiWdf_Tc1Tw3S1P2, PmiWdf_Tc1Tw3S1P3, PmiWdf_Tc1Tw3S1P4, PmiWdf_Tc1Tw3S1P5, PmiWdf_Tc1Tw3S2P0, PmiWdf_Tc1Tw3S2P1, PmiWdf_Tc1Tw3S2P2, PmiWdf_Tc1Tw3S2P3, PmiWdf_Tc1Tw3S2P4, PmiWdf_Tc1Tw3S2P5, 
        PmiWdf_Tc1Tw4S0P0, PmiWdf_Tc1Tw4S0P1, PmiWdf_Tc1Tw4S0P2, PmiWdf_Tc1Tw4S0P3, PmiWdf_Tc1Tw4S0P4, PmiWdf_Tc1Tw4S0P5, PmiWdf_Tc1Tw4S1P0, PmiWdf_Tc1Tw4S1P1, PmiWdf_Tc1Tw4S1P2, PmiWdf_Tc1Tw4S1P3, PmiWdf_Tc1Tw4S1P4, PmiWdf_Tc1Tw4S1P5, PmiWdf_Tc1Tw4S2P0, PmiWdf_Tc1Tw4S2P1, PmiWdf_Tc1Tw4S2P2, PmiWdf_Tc1Tw4S2P3, PmiWdf_Tc1Tw4S2P4, PmiWdf_Tc1Tw4S2P5, 
        PmiWdf_Tc2Tw0S0P0, PmiWdf_Tc2Tw0S0P1, PmiWdf_Tc2Tw0S0P2, PmiWdf_Tc2Tw0S0P3, PmiWdf_Tc2Tw0S0P4, PmiWdf_Tc2Tw0S0P5, PmiWdf_Tc2Tw0S1P0, PmiWdf_Tc2Tw0S1P1, PmiWdf_Tc2Tw0S1P2, PmiWdf_Tc2Tw0S1P3, PmiWdf_Tc2Tw0S1P4, PmiWdf_Tc2Tw0S1P5, PmiWdf_Tc2Tw0S2P0, PmiWdf_Tc2Tw0S2P1, PmiWdf_Tc2Tw0S2P2, PmiWdf_Tc2Tw0S2P3, PmiWdf_Tc2Tw0S2P4, PmiWdf_Tc2Tw0S2P5, 
        PmiWdf_Tc2Tw1S0P0, PmiWdf_Tc2Tw1S0P1, PmiWdf_Tc2Tw1S0P2, PmiWdf_Tc2Tw1S0P3, PmiWdf_Tc2Tw1S0P4, PmiWdf_Tc2Tw1S0P5, PmiWdf_Tc2Tw1S1P0, PmiWdf_Tc2Tw1S1P1, PmiWdf_Tc2Tw1S1P2, PmiWdf_Tc2Tw1S1P3, PmiWdf_Tc2Tw1S1P4, PmiWdf_Tc2Tw1S1P5, PmiWdf_Tc2Tw1S2P0, PmiWdf_Tc2Tw1S2P1, PmiWdf_Tc2Tw1S2P2, PmiWdf_Tc2Tw1S2P3, PmiWdf_Tc2Tw1S2P4, PmiWdf_Tc2Tw1S2P5, 
        PmiWdf_Tc2Tw2S0P0, PmiWdf_Tc2Tw2S0P1, PmiWdf_Tc2Tw2S0P2, PmiWdf_Tc2Tw2S0P3, PmiWdf_Tc2Tw2S0P4, PmiWdf_Tc2Tw2S0P5, PmiWdf_Tc2Tw2S1P0, PmiWdf_Tc2Tw2S1P1, PmiWdf_Tc2Tw2S1P2, PmiWdf_Tc2Tw2S1P3, PmiWdf_Tc2Tw2S1P4, PmiWdf_Tc2Tw2S1P5, PmiWdf_Tc2Tw2S2P0, PmiWdf_Tc2Tw2S2P1, PmiWdf_Tc2Tw2S2P2, PmiWdf_Tc2Tw2S2P3, PmiWdf_Tc2Tw2S2P4, PmiWdf_Tc2Tw2S2P5, 
        PmiWdf_Tc2Tw3S0P0, PmiWdf_Tc2Tw3S0P1, PmiWdf_Tc2Tw3S0P2, PmiWdf_Tc2Tw3S0P3, PmiWdf_Tc2Tw3S0P4, PmiWdf_Tc2Tw3S0P5, PmiWdf_Tc2Tw3S1P0, PmiWdf_Tc2Tw3S1P1, PmiWdf_Tc2Tw3S1P2, PmiWdf_Tc2Tw3S1P3, PmiWdf_Tc2Tw3S1P4, PmiWdf_Tc2Tw3S1P5, PmiWdf_Tc2Tw3S2P0, PmiWdf_Tc2Tw3S2P1, PmiWdf_Tc2Tw3S2P2, PmiWdf_Tc2Tw3S2P3, PmiWdf_Tc2Tw3S2P4, PmiWdf_Tc2Tw3S2P5, 
        PmiWdf_Tc2Tw4S0P0, PmiWdf_Tc2Tw4S0P1, PmiWdf_Tc2Tw4S0P2, PmiWdf_Tc2Tw4S0P3, PmiWdf_Tc2Tw4S0P4, PmiWdf_Tc2Tw4S0P5, PmiWdf_Tc2Tw4S1P0, PmiWdf_Tc2Tw4S1P1, PmiWdf_Tc2Tw4S1P2, PmiWdf_Tc2Tw4S1P3, PmiWdf_Tc2Tw4S1P4, PmiWdf_Tc2Tw4S1P5, PmiWdf_Tc2Tw4S2P0, PmiWdf_Tc2Tw4S2P1, PmiWdf_Tc2Tw4S2P2, PmiWdf_Tc2Tw4S2P3, PmiWdf_Tc2Tw4S2P4, PmiWdf_Tc2Tw4S2P5, 
        PmiWdf_Tc3Tw0S0P0, PmiWdf_Tc3Tw0S0P1, PmiWdf_Tc3Tw0S0P2, PmiWdf_Tc3Tw0S0P3, PmiWdf_Tc3Tw0S0P4, PmiWdf_Tc3Tw0S0P5, PmiWdf_Tc3Tw0S1P0, PmiWdf_Tc3Tw0S1P1, PmiWdf_Tc3Tw0S1P2, PmiWdf_Tc3Tw0S1P3, PmiWdf_Tc3Tw0S1P4, PmiWdf_Tc3Tw0S1P5, PmiWdf_Tc3Tw0S2P0, PmiWdf_Tc3Tw0S2P1, PmiWdf_Tc3Tw0S2P2, PmiWdf_Tc3Tw0S2P3, PmiWdf_Tc3Tw0S2P4, PmiWdf_Tc3Tw0S2P5, 
        PmiWdf_Tc3Tw1S0P0, PmiWdf_Tc3Tw1S0P1, PmiWdf_Tc3Tw1S0P2, PmiWdf_Tc3Tw1S0P3, PmiWdf_Tc3Tw1S0P4, PmiWdf_Tc3Tw1S0P5, PmiWdf_Tc3Tw1S1P0, PmiWdf_Tc3Tw1S1P1, PmiWdf_Tc3Tw1S1P2, PmiWdf_Tc3Tw1S1P3, PmiWdf_Tc3Tw1S1P4, PmiWdf_Tc3Tw1S1P5, PmiWdf_Tc3Tw1S2P0, PmiWdf_Tc3Tw1S2P1, PmiWdf_Tc3Tw1S2P2, PmiWdf_Tc3Tw1S2P3, PmiWdf_Tc3Tw1S2P4, PmiWdf_Tc3Tw1S2P5, 
        PmiWdf_Tc3Tw2S0P0, PmiWdf_Tc3Tw2S0P1, PmiWdf_Tc3Tw2S0P2, PmiWdf_Tc3Tw2S0P3, PmiWdf_Tc3Tw2S0P4, PmiWdf_Tc3Tw2S0P5, PmiWdf_Tc3Tw2S1P0, PmiWdf_Tc3Tw2S1P1, PmiWdf_Tc3Tw2S1P2, PmiWdf_Tc3Tw2S1P3, PmiWdf_Tc3Tw2S1P4, PmiWdf_Tc3Tw2S1P5, PmiWdf_Tc3Tw2S2P0, PmiWdf_Tc3Tw2S2P1, PmiWdf_Tc3Tw2S2P2, PmiWdf_Tc3Tw2S2P3, PmiWdf_Tc3Tw2S2P4, PmiWdf_Tc3Tw2S2P5, 
        PmiWdf_Tc3Tw3S0P0, PmiWdf_Tc3Tw3S0P1, PmiWdf_Tc3Tw3S0P2, PmiWdf_Tc3Tw3S0P3, PmiWdf_Tc3Tw3S0P4, PmiWdf_Tc3Tw3S0P5, PmiWdf_Tc3Tw3S1P0, PmiWdf_Tc3Tw3S1P1, PmiWdf_Tc3Tw3S1P2, PmiWdf_Tc3Tw3S1P3, PmiWdf_Tc3Tw3S1P4, PmiWdf_Tc3Tw3S1P5, PmiWdf_Tc3Tw3S2P0, PmiWdf_Tc3Tw3S2P1, PmiWdf_Tc3Tw3S2P2, PmiWdf_Tc3Tw3S2P3, PmiWdf_Tc3Tw3S2P4, PmiWdf_Tc3Tw3S2P5, 
        PmiWdf_Tc3Tw4S0P0, PmiWdf_Tc3Tw4S0P1, PmiWdf_Tc3Tw4S0P2, PmiWdf_Tc3Tw4S0P3, PmiWdf_Tc3Tw4S0P4, PmiWdf_Tc3Tw4S0P5, PmiWdf_Tc3Tw4S1P0, PmiWdf_Tc3Tw4S1P1, PmiWdf_Tc3Tw4S1P2, PmiWdf_Tc3Tw4S1P3, PmiWdf_Tc3Tw4S1P4, PmiWdf_Tc3Tw4S1P5, PmiWdf_Tc3Tw4S2P0, PmiWdf_Tc3Tw4S2P1, PmiWdf_Tc3Tw4S2P2, PmiWdf_Tc3Tw4S2P3, PmiWdf_Tc3Tw4S2P4, PmiWdf_Tc3Tw4S2P5, 
        PmiWdf_Tc4Tw0S0P0, PmiWdf_Tc4Tw0S0P1, PmiWdf_Tc4Tw0S0P2, PmiWdf_Tc4Tw0S0P3, PmiWdf_Tc4Tw0S0P4, PmiWdf_Tc4Tw0S0P5, PmiWdf_Tc4Tw0S1P0, PmiWdf_Tc4Tw0S1P1, PmiWdf_Tc4Tw0S1P2, PmiWdf_Tc4Tw0S1P3, PmiWdf_Tc4Tw0S1P4, PmiWdf_Tc4Tw0S1P5, PmiWdf_Tc4Tw0S2P0, PmiWdf_Tc4Tw0S2P1, PmiWdf_Tc4Tw0S2P2, PmiWdf_Tc4Tw0S2P3, PmiWdf_Tc4Tw0S2P4, PmiWdf_Tc4Tw0S2P5, 
        PmiWdf_Tc4Tw1S0P0, PmiWdf_Tc4Tw1S0P1, PmiWdf_Tc4Tw1S0P2, PmiWdf_Tc4Tw1S0P3, PmiWdf_Tc4Tw1S0P4, PmiWdf_Tc4Tw1S0P5, PmiWdf_Tc4Tw1S1P0, PmiWdf_Tc4Tw1S1P1, PmiWdf_Tc4Tw1S1P2, PmiWdf_Tc4Tw1S1P3, PmiWdf_Tc4Tw1S1P4, PmiWdf_Tc4Tw1S1P5, PmiWdf_Tc4Tw1S2P0, PmiWdf_Tc4Tw1S2P1, PmiWdf_Tc4Tw1S2P2, PmiWdf_Tc4Tw1S2P3, PmiWdf_Tc4Tw1S2P4, PmiWdf_Tc4Tw1S2P5, 
        PmiWdf_Tc4Tw2S0P0, PmiWdf_Tc4Tw2S0P1, PmiWdf_Tc4Tw2S0P2, PmiWdf_Tc4Tw2S0P3, PmiWdf_Tc4Tw2S0P4, PmiWdf_Tc4Tw2S0P5, PmiWdf_Tc4Tw2S1P0, PmiWdf_Tc4Tw2S1P1, PmiWdf_Tc4Tw2S1P2, PmiWdf_Tc4Tw2S1P3, PmiWdf_Tc4Tw2S1P4, PmiWdf_Tc4Tw2S1P5, PmiWdf_Tc4Tw2S2P0, PmiWdf_Tc4Tw2S2P1, PmiWdf_Tc4Tw2S2P2, PmiWdf_Tc4Tw2S2P3, PmiWdf_Tc4Tw2S2P4, PmiWdf_Tc4Tw2S2P5, 
        PmiWdf_Tc4Tw3S0P0, PmiWdf_Tc4Tw3S0P1, PmiWdf_Tc4Tw3S0P2, PmiWdf_Tc4Tw3S0P3, PmiWdf_Tc4Tw3S0P4, PmiWdf_Tc4Tw3S0P5, PmiWdf_Tc4Tw3S1P0, PmiWdf_Tc4Tw3S1P1, PmiWdf_Tc4Tw3S1P2, PmiWdf_Tc4Tw3S1P3, PmiWdf_Tc4Tw3S1P4, PmiWdf_Tc4Tw3S1P5, PmiWdf_Tc4Tw3S2P0, PmiWdf_Tc4Tw3S2P1, PmiWdf_Tc4Tw3S2P2, PmiWdf_Tc4Tw3S2P3, PmiWdf_Tc4Tw3S2P4, PmiWdf_Tc4Tw3S2P5, 
        PmiWdf_Tc4Tw4S0P0, PmiWdf_Tc4Tw4S0P1, PmiWdf_Tc4Tw4S0P2, PmiWdf_Tc4Tw4S0P3, PmiWdf_Tc4Tw4S0P4, PmiWdf_Tc4Tw4S0P5, PmiWdf_Tc4Tw4S1P0, PmiWdf_Tc4Tw4S1P1, PmiWdf_Tc4Tw4S1P2, PmiWdf_Tc4Tw4S1P3, PmiWdf_Tc4Tw4S1P4, PmiWdf_Tc4Tw4S1P5, PmiWdf_Tc4Tw4S2P0, PmiWdf_Tc4Tw4S2P1, PmiWdf_Tc4Tw4S2P2, PmiWdf_Tc4Tw4S2P3, PmiWdf_Tc4Tw4S2P4, PmiWdf_Tc4Tw4S2P5, 
        SPmi_Tc0Tw0S0P0, SPmi_Tc0Tw0S0P1, SPmi_Tc0Tw0S0P2, SPmi_Tc0Tw0S0P3, SPmi_Tc0Tw0S0P4, SPmi_Tc0Tw0S0P5, SPmi_Tc0Tw0S1P0, SPmi_Tc0Tw0S1P1, SPmi_Tc0Tw0S1P2, SPmi_Tc0Tw0S1P3, SPmi_Tc0Tw0S1P4, SPmi_Tc0Tw0S1P5, SPmi_Tc0Tw0S2P0, SPmi_Tc0Tw0S2P1, SPmi_Tc0Tw0S2P2, SPmi_Tc0Tw0S2P3, SPmi_Tc0Tw0S2P4, SPmi_Tc0Tw0S2P5, 
        SPmi_Tc0Tw1S0P0, SPmi_Tc0Tw1S0P1, SPmi_Tc0Tw1S0P2, SPmi_Tc0Tw1S0P3, SPmi_Tc0Tw1S0P4, SPmi_Tc0Tw1S0P5, SPmi_Tc0Tw1S1P0, SPmi_Tc0Tw1S1P1, SPmi_Tc0Tw1S1P2, SPmi_Tc0Tw1S1P3, SPmi_Tc0Tw1S1P4, SPmi_Tc0Tw1S1P5, SPmi_Tc0Tw1S2P0, SPmi_Tc0Tw1S2P1, SPmi_Tc0Tw1S2P2, SPmi_Tc0Tw1S2P3, SPmi_Tc0Tw1S2P4, SPmi_Tc0Tw1S2P5, 
        SPmi_Tc0Tw2S0P0, SPmi_Tc0Tw2S0P1, SPmi_Tc0Tw2S0P2, SPmi_Tc0Tw2S0P3, SPmi_Tc0Tw2S0P4, SPmi_Tc0Tw2S0P5, SPmi_Tc0Tw2S1P0, SPmi_Tc0Tw2S1P1, SPmi_Tc0Tw2S1P2, SPmi_Tc0Tw2S1P3, SPmi_Tc0Tw2S1P4, SPmi_Tc0Tw2S1P5, SPmi_Tc0Tw2S2P0, SPmi_Tc0Tw2S2P1, SPmi_Tc0Tw2S2P2, SPmi_Tc0Tw2S2P3, SPmi_Tc0Tw2S2P4, SPmi_Tc0Tw2S2P5, 
        SPmi_Tc0Tw3S0P0, SPmi_Tc0Tw3S0P1, SPmi_Tc0Tw3S0P2, SPmi_Tc0Tw3S0P3, SPmi_Tc0Tw3S0P4, SPmi_Tc0Tw3S0P5, SPmi_Tc0Tw3S1P0, SPmi_Tc0Tw3S1P1, SPmi_Tc0Tw3S1P2, SPmi_Tc0Tw3S1P3, SPmi_Tc0Tw3S1P4, SPmi_Tc0Tw3S1P5, SPmi_Tc0Tw3S2P0, SPmi_Tc0Tw3S2P1, SPmi_Tc0Tw3S2P2, SPmi_Tc0Tw3S2P3, SPmi_Tc0Tw3S2P4, SPmi_Tc0Tw3S2P5, 
        SPmi_Tc0Tw4S0P0, SPmi_Tc0Tw4S0P1, SPmi_Tc0Tw4S0P2, SPmi_Tc0Tw4S0P3, SPmi_Tc0Tw4S0P4, SPmi_Tc0Tw4S0P5, SPmi_Tc0Tw4S1P0, SPmi_Tc0Tw4S1P1, SPmi_Tc0Tw4S1P2, SPmi_Tc0Tw4S1P3, SPmi_Tc0Tw4S1P4, SPmi_Tc0Tw4S1P5, SPmi_Tc0Tw4S2P0, SPmi_Tc0Tw4S2P1, SPmi_Tc0Tw4S2P2, SPmi_Tc0Tw4S2P3, SPmi_Tc0Tw4S2P4, SPmi_Tc0Tw4S2P5, 
        SPmi_Tc1Tw0S0P0, SPmi_Tc1Tw0S0P1, SPmi_Tc1Tw0S0P2, SPmi_Tc1Tw0S0P3, SPmi_Tc1Tw0S0P4, SPmi_Tc1Tw0S0P5, SPmi_Tc1Tw0S1P0, SPmi_Tc1Tw0S1P1, SPmi_Tc1Tw0S1P2, SPmi_Tc1Tw0S1P3, SPmi_Tc1Tw0S1P4, SPmi_Tc1Tw0S1P5, SPmi_Tc1Tw0S2P0, SPmi_Tc1Tw0S2P1, SPmi_Tc1Tw0S2P2, SPmi_Tc1Tw0S2P3, SPmi_Tc1Tw0S2P4, SPmi_Tc1Tw0S2P5, 
        SPmi_Tc1Tw1S0P0, SPmi_Tc1Tw1S0P1, SPmi_Tc1Tw1S0P2, SPmi_Tc1Tw1S0P3, SPmi_Tc1Tw1S0P4, SPmi_Tc1Tw1S0P5, SPmi_Tc1Tw1S1P0, SPmi_Tc1Tw1S1P1, SPmi_Tc1Tw1S1P2, SPmi_Tc1Tw1S1P3, SPmi_Tc1Tw1S1P4, SPmi_Tc1Tw1S1P5, SPmi_Tc1Tw1S2P0, SPmi_Tc1Tw1S2P1, SPmi_Tc1Tw1S2P2, SPmi_Tc1Tw1S2P3, SPmi_Tc1Tw1S2P4, SPmi_Tc1Tw1S2P5, 
        SPmi_Tc1Tw2S0P0, SPmi_Tc1Tw2S0P1, SPmi_Tc1Tw2S0P2, SPmi_Tc1Tw2S0P3, SPmi_Tc1Tw2S0P4, SPmi_Tc1Tw2S0P5, SPmi_Tc1Tw2S1P0, SPmi_Tc1Tw2S1P1, SPmi_Tc1Tw2S1P2, SPmi_Tc1Tw2S1P3, SPmi_Tc1Tw2S1P4, SPmi_Tc1Tw2S1P5, SPmi_Tc1Tw2S2P0, SPmi_Tc1Tw2S2P1, SPmi_Tc1Tw2S2P2, SPmi_Tc1Tw2S2P3, SPmi_Tc1Tw2S2P4, SPmi_Tc1Tw2S2P5, 
        SPmi_Tc1Tw3S0P0, SPmi_Tc1Tw3S0P1, SPmi_Tc1Tw3S0P2, SPmi_Tc1Tw3S0P3, SPmi_Tc1Tw3S0P4, SPmi_Tc1Tw3S0P5, SPmi_Tc1Tw3S1P0, SPmi_Tc1Tw3S1P1, SPmi_Tc1Tw3S1P2, SPmi_Tc1Tw3S1P3, SPmi_Tc1Tw3S1P4, SPmi_Tc1Tw3S1P5, SPmi_Tc1Tw3S2P0, SPmi_Tc1Tw3S2P1, SPmi_Tc1Tw3S2P2, SPmi_Tc1Tw3S2P3, SPmi_Tc1Tw3S2P4, SPmi_Tc1Tw3S2P5, 
        SPmi_Tc1Tw4S0P0, SPmi_Tc1Tw4S0P1, SPmi_Tc1Tw4S0P2, SPmi_Tc1Tw4S0P3, SPmi_Tc1Tw4S0P4, SPmi_Tc1Tw4S0P5, SPmi_Tc1Tw4S1P0, SPmi_Tc1Tw4S1P1, SPmi_Tc1Tw4S1P2, SPmi_Tc1Tw4S1P3, SPmi_Tc1Tw4S1P4, SPmi_Tc1Tw4S1P5, SPmi_Tc1Tw4S2P0, SPmi_Tc1Tw4S2P1, SPmi_Tc1Tw4S2P2, SPmi_Tc1Tw4S2P3, SPmi_Tc1Tw4S2P4, SPmi_Tc1Tw4S2P5, 
        SPmi_Tc2Tw0S0P0, SPmi_Tc2Tw0S0P1, SPmi_Tc2Tw0S0P2, SPmi_Tc2Tw0S0P3, SPmi_Tc2Tw0S0P4, SPmi_Tc2Tw0S0P5, SPmi_Tc2Tw0S1P0, SPmi_Tc2Tw0S1P1, SPmi_Tc2Tw0S1P2, SPmi_Tc2Tw0S1P3, SPmi_Tc2Tw0S1P4, SPmi_Tc2Tw0S1P5, SPmi_Tc2Tw0S2P0, SPmi_Tc2Tw0S2P1, SPmi_Tc2Tw0S2P2, SPmi_Tc2Tw0S2P3, SPmi_Tc2Tw0S2P4, SPmi_Tc2Tw0S2P5, 
        SPmi_Tc2Tw1S0P0, SPmi_Tc2Tw1S0P1, SPmi_Tc2Tw1S0P2, SPmi_Tc2Tw1S0P3, SPmi_Tc2Tw1S0P4, SPmi_Tc2Tw1S0P5, SPmi_Tc2Tw1S1P0, SPmi_Tc2Tw1S1P1, SPmi_Tc2Tw1S1P2, SPmi_Tc2Tw1S1P3, SPmi_Tc2Tw1S1P4, SPmi_Tc2Tw1S1P5, SPmi_Tc2Tw1S2P0, SPmi_Tc2Tw1S2P1, SPmi_Tc2Tw1S2P2, SPmi_Tc2Tw1S2P3, SPmi_Tc2Tw1S2P4, SPmi_Tc2Tw1S2P5, 
        SPmi_Tc2Tw2S0P0, SPmi_Tc2Tw2S0P1, SPmi_Tc2Tw2S0P2, SPmi_Tc2Tw2S0P3, SPmi_Tc2Tw2S0P4, SPmi_Tc2Tw2S0P5, SPmi_Tc2Tw2S1P0, SPmi_Tc2Tw2S1P1, SPmi_Tc2Tw2S1P2, SPmi_Tc2Tw2S1P3, SPmi_Tc2Tw2S1P4, SPmi_Tc2Tw2S1P5, SPmi_Tc2Tw2S2P0, SPmi_Tc2Tw2S2P1, SPmi_Tc2Tw2S2P2, SPmi_Tc2Tw2S2P3, SPmi_Tc2Tw2S2P4, SPmi_Tc2Tw2S2P5, 
        SPmi_Tc2Tw3S0P0, SPmi_Tc2Tw3S0P1, SPmi_Tc2Tw3S0P2, SPmi_Tc2Tw3S0P3, SPmi_Tc2Tw3S0P4, SPmi_Tc2Tw3S0P5, SPmi_Tc2Tw3S1P0, SPmi_Tc2Tw3S1P1, SPmi_Tc2Tw3S1P2, SPmi_Tc2Tw3S1P3, SPmi_Tc2Tw3S1P4, SPmi_Tc2Tw3S1P5, SPmi_Tc2Tw3S2P0, SPmi_Tc2Tw3S2P1, SPmi_Tc2Tw3S2P2, SPmi_Tc2Tw3S2P3, SPmi_Tc2Tw3S2P4, SPmi_Tc2Tw3S2P5, 
        SPmi_Tc2Tw4S0P0, SPmi_Tc2Tw4S0P1, SPmi_Tc2Tw4S0P2, SPmi_Tc2Tw4S0P3, SPmi_Tc2Tw4S0P4, SPmi_Tc2Tw4S0P5, SPmi_Tc2Tw4S1P0, SPmi_Tc2Tw4S1P1, SPmi_Tc2Tw4S1P2, SPmi_Tc2Tw4S1P3, SPmi_Tc2Tw4S1P4, SPmi_Tc2Tw4S1P5, SPmi_Tc2Tw4S2P0, SPmi_Tc2Tw4S2P1, SPmi_Tc2Tw4S2P2, SPmi_Tc2Tw4S2P3, SPmi_Tc2Tw4S2P4, SPmi_Tc2Tw4S2P5, 
        SPmi_Tc3Tw0S0P0, SPmi_Tc3Tw0S0P1, SPmi_Tc3Tw0S0P2, SPmi_Tc3Tw0S0P3, SPmi_Tc3Tw0S0P4, SPmi_Tc3Tw0S0P5, SPmi_Tc3Tw0S1P0, SPmi_Tc3Tw0S1P1, SPmi_Tc3Tw0S1P2, SPmi_Tc3Tw0S1P3, SPmi_Tc3Tw0S1P4, SPmi_Tc3Tw0S1P5, SPmi_Tc3Tw0S2P0, SPmi_Tc3Tw0S2P1, SPmi_Tc3Tw0S2P2, SPmi_Tc3Tw0S2P3, SPmi_Tc3Tw0S2P4, SPmi_Tc3Tw0S2P5, 
        SPmi_Tc3Tw1S0P0, SPmi_Tc3Tw1S0P1, SPmi_Tc3Tw1S0P2, SPmi_Tc3Tw1S0P3, SPmi_Tc3Tw1S0P4, SPmi_Tc3Tw1S0P5, SPmi_Tc3Tw1S1P0, SPmi_Tc3Tw1S1P1, SPmi_Tc3Tw1S1P2, SPmi_Tc3Tw1S1P3, SPmi_Tc3Tw1S1P4, SPmi_Tc3Tw1S1P5, SPmi_Tc3Tw1S2P0, SPmi_Tc3Tw1S2P1, SPmi_Tc3Tw1S2P2, SPmi_Tc3Tw1S2P3, SPmi_Tc3Tw1S2P4, SPmi_Tc3Tw1S2P5, 
        SPmi_Tc3Tw2S0P0, SPmi_Tc3Tw2S0P1, SPmi_Tc3Tw2S0P2, SPmi_Tc3Tw2S0P3, SPmi_Tc3Tw2S0P4, SPmi_Tc3Tw2S0P5, SPmi_Tc3Tw2S1P0, SPmi_Tc3Tw2S1P1, SPmi_Tc3Tw2S1P2, SPmi_Tc3Tw2S1P3, SPmi_Tc3Tw2S1P4, SPmi_Tc3Tw2S1P5, SPmi_Tc3Tw2S2P0, SPmi_Tc3Tw2S2P1, SPmi_Tc3Tw2S2P2, SPmi_Tc3Tw2S2P3, SPmi_Tc3Tw2S2P4, SPmi_Tc3Tw2S2P5, 
        SPmi_Tc3Tw3S0P0, SPmi_Tc3Tw3S0P1, SPmi_Tc3Tw3S0P2, SPmi_Tc3Tw3S0P3, SPmi_Tc3Tw3S0P4, SPmi_Tc3Tw3S0P5, SPmi_Tc3Tw3S1P0, SPmi_Tc3Tw3S1P1, SPmi_Tc3Tw3S1P2, SPmi_Tc3Tw3S1P3, SPmi_Tc3Tw3S1P4, SPmi_Tc3Tw3S1P5, SPmi_Tc3Tw3S2P0, SPmi_Tc3Tw3S2P1, SPmi_Tc3Tw3S2P2, SPmi_Tc3Tw3S2P3, SPmi_Tc3Tw3S2P4, SPmi_Tc3Tw3S2P5, 
        SPmi_Tc3Tw4S0P0, SPmi_Tc3Tw4S0P1, SPmi_Tc3Tw4S0P2, SPmi_Tc3Tw4S0P3, SPmi_Tc3Tw4S0P4, SPmi_Tc3Tw4S0P5, SPmi_Tc3Tw4S1P0, SPmi_Tc3Tw4S1P1, SPmi_Tc3Tw4S1P2, SPmi_Tc3Tw4S1P3, SPmi_Tc3Tw4S1P4, SPmi_Tc3Tw4S1P5, SPmi_Tc3Tw4S2P0, SPmi_Tc3Tw4S2P1, SPmi_Tc3Tw4S2P2, SPmi_Tc3Tw4S2P3, SPmi_Tc3Tw4S2P4, SPmi_Tc3Tw4S2P5, 
        SPmi_Tc4Tw0S0P0, SPmi_Tc4Tw0S0P1, SPmi_Tc4Tw0S0P2, SPmi_Tc4Tw0S0P3, SPmi_Tc4Tw0S0P4, SPmi_Tc4Tw0S0P5, SPmi_Tc4Tw0S1P0, SPmi_Tc4Tw0S1P1, SPmi_Tc4Tw0S1P2, SPmi_Tc4Tw0S1P3, SPmi_Tc4Tw0S1P4, SPmi_Tc4Tw0S1P5, SPmi_Tc4Tw0S2P0, SPmi_Tc4Tw0S2P1, SPmi_Tc4Tw0S2P2, SPmi_Tc4Tw0S2P3, SPmi_Tc4Tw0S2P4, SPmi_Tc4Tw0S2P5, 
        SPmi_Tc4Tw1S0P0, SPmi_Tc4Tw1S0P1, SPmi_Tc4Tw1S0P2, SPmi_Tc4Tw1S0P3, SPmi_Tc4Tw1S0P4, SPmi_Tc4Tw1S0P5, SPmi_Tc4Tw1S1P0, SPmi_Tc4Tw1S1P1, SPmi_Tc4Tw1S1P2, SPmi_Tc4Tw1S1P3, SPmi_Tc4Tw1S1P4, SPmi_Tc4Tw1S1P5, SPmi_Tc4Tw1S2P0, SPmi_Tc4Tw1S2P1, SPmi_Tc4Tw1S2P2, SPmi_Tc4Tw1S2P3, SPmi_Tc4Tw1S2P4, SPmi_Tc4Tw1S2P5, 
        SPmi_Tc4Tw2S0P0, SPmi_Tc4Tw2S0P1, SPmi_Tc4Tw2S0P2, SPmi_Tc4Tw2S0P3, SPmi_Tc4Tw2S0P4, SPmi_Tc4Tw2S0P5, SPmi_Tc4Tw2S1P0, SPmi_Tc4Tw2S1P1, SPmi_Tc4Tw2S1P2, SPmi_Tc4Tw2S1P3, SPmi_Tc4Tw2S1P4, SPmi_Tc4Tw2S1P5, SPmi_Tc4Tw2S2P0, SPmi_Tc4Tw2S2P1, SPmi_Tc4Tw2S2P2, SPmi_Tc4Tw2S2P3, SPmi_Tc4Tw2S2P4, SPmi_Tc4Tw2S2P5, 
        SPmi_Tc4Tw3S0P0, SPmi_Tc4Tw3S0P1, SPmi_Tc4Tw3S0P2, SPmi_Tc4Tw3S0P3, SPmi_Tc4Tw3S0P4, SPmi_Tc4Tw3S0P5, SPmi_Tc4Tw3S1P0, SPmi_Tc4Tw3S1P1, SPmi_Tc4Tw3S1P2, SPmi_Tc4Tw3S1P3, SPmi_Tc4Tw3S1P4, SPmi_Tc4Tw3S1P5, SPmi_Tc4Tw3S2P0, SPmi_Tc4Tw3S2P1, SPmi_Tc4Tw3S2P2, SPmi_Tc4Tw3S2P3, SPmi_Tc4Tw3S2P4, SPmi_Tc4Tw3S2P5, 
        SPmi_Tc4Tw4S0P0, SPmi_Tc4Tw4S0P1, SPmi_Tc4Tw4S0P2, SPmi_Tc4Tw4S0P3, SPmi_Tc4Tw4S0P4, SPmi_Tc4Tw4S0P5, SPmi_Tc4Tw4S1P0, SPmi_Tc4Tw4S1P1, SPmi_Tc4Tw4S1P2, SPmi_Tc4Tw4S1P3, SPmi_Tc4Tw4S1P4, SPmi_Tc4Tw4S1P5, SPmi_Tc4Tw4S2P0, SPmi_Tc4Tw4S2P1, SPmi_Tc4Tw4S2P2, SPmi_Tc4Tw4S2P3, SPmi_Tc4Tw4S2P4, SPmi_Tc4Tw4S2P5*/};
    public static WeightingScheme weightingScheme=WeightingScheme.Pmi;
    
    
    public static double pmiAlphaAlpha=0.75d;
    public static double spmiK=5d;
    public static double pmiDiscDiscount=0.95d;
    
    
    
    /**
     * This function calculates all the necessary weights for the word vectors.
     */
    public static void applyWeightingScheme(){
        
        applyWeightingScheme(objVerbTuples, verbObjectTuples, objectFrequencies, verbWithObjectFrequencies, nounFrequencies, verbObjectInformation, verbObjectEntropy, verbObjectGrefFeatureWeight, 
                verbObjectATCFeatureWeight, allObjectCount, allObjectCountAlpha, allObjectCount+allSubjectCount+allNounNcmodCount, nounVectorElementSums, avgNounVectorElementSum, objVerbMaxFrequency, 
                allNounTypeCount, allNounFeatureDocFreqCount);
        applyWeightingScheme(subjVerbTuples, verbSubjectTuples, subjectFrequencies, verbWithSubjectFrequencies, nounFrequencies, verbSubjectInformation, verbSubjectEntropy, verbSubjectGrefFeatureWeight, 
                verbSubjectATCFeatureWeight, allSubjectCount, allSubjectCountAlpha, allObjectCount+allSubjectCount+allNounNcmodCount, nounVectorElementSums, avgNounVectorElementSum, subjVerbMaxFrequency, 
                allNounTypeCount, allNounFeatureDocFreqCount);
        applyWeightingScheme(nounNcmodTuples, ncmodNounTuples, nounWithNcmodFrequencies, ncmodWithNounFrequencies, nounFrequencies, ncmodNounInformation, ncmodNounEntropy, ncmodNounGrefFeatureWeight, 
                ncmodNounATCFeatureWeight, allNounNcmodCount, allNounNcmodCountAlpha, allObjectCount+allSubjectCount+allNounNcmodCount, nounVectorElementSums, avgNounVectorElementSum, nounNcmodMaxFrequency, 
                allNounTypeCount, allNounFeatureDocFreqCount);
        applyWeightingScheme(verbDobjTuples, dobjVerbTuples, verbWithDobjFrequencies, dobjWithVerbFrequencies, verbFrequencies, dobjVerbInformation, dobjVerbEntropy, dobjVerbGrefFeatureWeight, 
                dobjVerbATCFeatureWeight, allVerbDobjCount, allVerbDobjCountAlpha, allVerbDobjCount+allVerbNcsubjCount+allVerbPrepCount+allVerbObj2Count, verbVectorElementSums, avgVerbVectorElementSum, 
                verbDobjMaxFrequency, allVerbTypeCount, allVerbFeatureDocFreqCount);
        applyWeightingScheme(verbNcsubjTuples, ncsubjVerbTuples, verbWithNcsubjFrequencies, ncsubjWithVerbFrequencies, verbFrequencies, ncsubjVerbInformation, ncsubjVerbEntropy, ncsubjVerbGrefFeatureWeight, 
                ncsubjVerbATCFeatureWeight, allVerbNcsubjCount, allVerbNcsubjCountAlpha, allVerbDobjCount+allVerbNcsubjCount+allVerbPrepCount+allVerbObj2Count, verbVectorElementSums, avgVerbVectorElementSum, 
                verbNcsubjMaxFrequency, allVerbTypeCount, allVerbFeatureDocFreqCount);
        applyWeightingScheme(verbPrepTuples, prepVerbTuples, verbWithPrepFrequencies, prepWithVerbFrequencies, verbFrequencies, prepVerbInformation, prepVerbEntropy, prepVerbGrefFeatureWeight, 
                prepVerbATCFeatureWeight, allVerbPrepCount, allVerbPrepCountAlpha, allVerbDobjCount+allVerbNcsubjCount+allVerbPrepCount+allVerbObj2Count, verbVectorElementSums, avgVerbVectorElementSum, 
                verbPrepMaxFrequency, allVerbTypeCount, allVerbFeatureDocFreqCount);
        applyWeightingScheme(verbObj2Tuples, obj2VerbTuples, verbWithObj2Frequencies, obj2WithVerbFrequencies, verbFrequencies, obj2VerbInformation, obj2VerbEntropy, obj2VerbGrefFeatureWeight, 
                obj2VerbATCFeatureWeight, allVerbObj2Count, allVerbObj2CountAlpha, allVerbDobjCount+allVerbNcsubjCount+allVerbPrepCount+allVerbObj2Count, verbVectorElementSums, avgVerbVectorElementSum, 
                verbObj2MaxFrequency, allVerbTypeCount, allVerbFeatureDocFreqCount);
        applyWeightingScheme(adjNounTuples, nounAdjTuples, adjWithNounFrequencies, nounWithAdjFrequencies, adjectiveFrequencies, nounAdjInformation, nounAdjEntropy, nounAdjGrefFeatureWeight, 
                nounAdjATCFeatureWeight, allAdjNounCount, allAdjNounCountAlpha, allAdjNounCount, adjectiveVectorElementSums, avgAdjectiveVectorElementSum, adjNounMaxFrequency, 
                allAdjectiveTypeCount, allAdjFeatureDocFreqCount);
        applyWeightingScheme(advWordTuples, wordAdvTuples, advWithWordFrequencies, wordWithAdvFrequencies, adverbFrequencies, wordAdvInformation, wordAdvEntropy, wordAdvGrefFeatureWeight, 
                wordAdvATCFeatureWeight, allAdvWordCount, allAdvWordCountAlpha, allAdvWordCount, adverbVectorElementSums, avgAdverbVectorElementSum, advWordMaxFrequency, 
                allAdverbTypeCount, allAdvFeatureDocFreqCount);
        
    }
    

    /**
     * This function calculates the weight for (word, feature) pairs stored in @param tuplesMap.
     * @param <T> the type of keys in @param tuplesMap
     * @param <V> the type of values in @param tuplesMap
     * @param tuplesMap the map containing the (word, feature) pairs
     * @param tuplesMap2 the map containing the number of words with which the features occur
     * @param frequency1Map the map containing the frequency of words in the given type of relation
     * @param frequency2Map the map containing the frequency of features in the given type of relation
     * @param frequency1MapOfGivenPOS the map containing the frequency of words of a given POS
     * @param informationMap the map containing the information content of the features
     * @param entropyMap the map containing the entropy of features
     * @param grefFeatureWeightMap the map containing Grefenstette's feature weight of the features
     * @param atcFeatureWeightMap the map containing ATC feature weight of the features
     * @param allRelationCountOfGivenType the count of all the relations of the given type
     * @param allRelationCountOfGivenTypeAlpha the sum of the feature frequencies rasied to the power pmiAlphaAlpha of the given type
     * @param allRelationCount the count of all the relations of all types for the given POS
     * @param vectorElementSumMap a map containing the vector element sums of words for a given POS
     * @param avgWordVectorElementSum the average word vector element sum of a given POS
     * @param maxWordFeaturePairFrequency the maximum word-feature pair frequency
     * @param allWordTypeCount the count of all the word types of the given POS
     * @param allFeatureDocFreqCount the sum of the feature document frequencies of a given type
     */
    public static <T, V> void applyWeightingScheme(HashMap<T, HashMap<V, Double>> tuplesMap, HashMap<V, Long> tuplesMap2, HashMap<T, Double> frequency1Map, HashMap<V, Double> frequency2Map, 
            HashMap<T, Double> frequency1MapOfGivenPOS, HashMap<V, Double> informationMap, HashMap<V, Double> entropyMap, HashMap<V, Double> grefFeatureWeightMap, HashMap<V, Double> atcFeatureWeightMap, 
            double allRelationCountOfGivenType, double allRelationCountOfGivenTypeAlpha, 
            double allRelationCount, HashMap<T, Double> vectorElementSumMap, Double avgWordVectorElementSum, Double maxWordFeaturePairFrequency, long allWordTypeCount, long allFeatureDocFreqCount){
        
        for(Map.Entry<T, HashMap<V, Double>> word1Entry: tuplesMap.entrySet()){
            T word1 = word1Entry.getKey();
            HashMap<V, Double> word1Table = word1Entry.getValue();
            for (Map.Entry<V, Double> word2Entry: word1Table.entrySet()){
                V word2 = word2Entry.getKey();
                double weight = computeWeight(word1, word2, word1Table, tuplesMap2, frequency1Map, frequency2Map, frequency1MapOfGivenPOS, informationMap, entropyMap, grefFeatureWeightMap, atcFeatureWeightMap, 
                        allRelationCountOfGivenType, allRelationCountOfGivenTypeAlpha, allRelationCount, vectorElementSumMap, avgWordVectorElementSum, maxWordFeaturePairFrequency, allWordTypeCount, 
                        allFeatureDocFreqCount);
                if(minimumWordFeatureTupleWeightType==MinimumWordFeatureTupleWeightType.Limit){
                    weight = Math.max(minimumWordFeatureTupleWeightParameter, weight);
                }else if(minimumWordFeatureTupleWeightType==MinimumWordFeatureTupleWeightType.Zero){
                    weight = weight<minimumWordFeatureTupleWeightParameter ? 0d : weight;
                }
                word2Entry.setValue(weight);
            }
        }
        
    }
    
    
    
    /**
     * This function calculates the weight for a (word, feature) pair.
     * @param <T> the type of keys in @param tuplesMap
     * @param <V> the type of values in @param tuplesMap
     * @param word1 the word
     * @param word2 the feature
     * @param word1Table the map containing the features with frequencies for word
     * @param tuplesMap2 the map containing the number of words with which the features occur
     * @param frequency1Map the map containing the frequency of words in the given type of relation
     * @param frequency2Map the map containing the frequency of features in the given type of relation
     * @param frequency1MapOfGivenPOS the map containing the frequency of words of a given POS
     * @param informationMap the map containing the information content of the features
     * @param entropyMap the map containing the entropy of features
     * @param grefFeatureWeightMap that map that will contain Grefenstette's feature weight of the features
     * @param allRelationCountOfGivenType the count of all the relations of the given type
     * @param allRelationCountOfGivenTypeAlpha the sum of the feature frequencies rasied to the power pmiAlphaAlpha of the given type
     * @param allRelationCount the count of all the relations of all types for the given POS
     * @param atcFeatureWeightMap the map containing ATC feature weight of the features
     * @param vectorElementSumMap a map containing the vector element sumps of words for a given POS
     * @param avgWordVectorElementSum the average word vector element sum of a given POS
     * @param maxWordFeaturePairFrequency the maximum word-feature pair frequency
     * @param allWordTypeCount the count of all the word types of the given POS
     * @param allFeatureDocFreqCount the sum of the feature document frequencies of a given type
     * @return the weight of the (word, feature) pair
     */
    public static <T, V> double computeWeight(T word1, V word2, HashMap<V, Double> word1Table, HashMap<V, Long> tuplesMap2, HashMap<T, Double> frequency1Map, HashMap<V, Double> frequency2Map, 
            HashMap<T, Double> frequency1MapOfGivenPOS, HashMap<V, Double> informationMap, HashMap<V, Double> entropyMap, HashMap<V, Double> grefFeatureWeightMap, HashMap<V, Double> atcFeatureWeightMap, 
            double allRelationCountOfGivenType, double allRelationCountOfGivenTypeAlpha, 
            double allRelationCount, HashMap<T, Double> vectorElementSumMap, Double avgWordVectorElementSum, Double maxWordFeaturePairFrequency, long allWordTypeCount, long allFeatureDocFreqCount){
        
        double weight = Double.NaN;
        
        double fxy = word1Table.get(word2);
        
        if(similarityMeasure==SimilarityMeasure.Lin){
            
            if(fxy!=0d){
                weight = informationMap.get(word2);
            }else{
                return 0d;
            }
            
        }else{

            Double fx = frequency1Map.get(word1);
            Double fy = frequency2Map.get(word2);
            Double n = allRelationCountOfGivenType;
            Double nAlpha = allRelationCountOfGivenTypeAlpha;
            
            String weightingSchemeString = weightingScheme.toString();
            String[] weightingSchemeStringParts = weightingSchemeString.split("_");
            
            if(weightingSchemeStringParts.length==2 && weightingSchemeStringParts[1].startsWith("Tc")){
                
                int tcType = Integer.parseInt(weightingSchemeStringParts[1].substring(2, 3));
                    
                if(tcType == 1){
                    fy = Math.pow(fy, pmiAlphaAlpha);
                    n = allRelationCountOfGivenTypeAlpha;
                }else if(tcType == 2){
                    fxy = fxy-pmiDiscDiscount;
                }else if(tcType == 3){
                    fxy++;
                    fx++;
                    fy++;
                    n++;
                    nAlpha++;
                }else if(tcType == 4){
                    nAlpha = nAlpha-fx-fy+fxy;
                    n = n-fx-fy+fxy;
                    fy = fy-fxy;
                    fx = fx-fxy;
                    if(fx<=0d || fy<=0d || (nAlpha<=0 && weightingSchemeStringParts[0].contains("PmiAl"))){
                        return 0d;
                    }
                }else if(tcType != 0){
                    System.out.println("Wrong TC parameter in the weighting scheme: " + tcType);
                    System.exit(1);
                }
                
                
                int twType = Integer.parseInt(weightingSchemeStringParts[1].substring(5, 6));

                if(weightingSchemeStringParts[0].equals("Pmi")){

                    if(twType == 0 || twType == 4){
                        weight = mutualInformation(fxy, fx, fy, n);
                    }else if(twType == 1){
                        weight = lb(1d+(n*fxy/(fx*fy)));
                    }else if(twType == 2){
                        weight = Math.pow(lb(1d+(n*fxy/(fx*fy))),2d);
                    }else if(twType == 3){
                        weight = lb(n*fxy/(fx*fy))*lb(1d+(n*fxy/(fx*fy)));
                    }else{
                        System.out.println("Wrong TW parameter in the weighting scheme: " + twType);
                        System.exit(1);
                    }

                }else if(weightingSchemeStringParts[0].equals("SPmi")){

                    if(twType == 0 || twType == 4){
                        weight = mutualInformation(fxy, fx, fy, n)-lb(spmiK);
                    }else if(twType == 1){
                        weight = lb(1d+(n*fxy/(fx*fy)))-lb(spmiK);
                    }else if(twType == 2){
                        weight = Math.pow(lb(1d+(n*fxy/(fx*fy))),2d)-lb(spmiK);
                    }else if(twType == 3){
                        weight = lb(n*fxy/(fx*fy))*lb(1d+(n*fxy/(fx*fy)))-lb(spmiK);
                    }else{
                        System.out.println("Wrong TW parameter in the weighting scheme: " + twType);
                        System.exit(1);
                    }

                }else if(weightingSchemeStringParts[0].equals("PmiAl")){

                    if(twType == 0 || twType == 4){
                        weight = mutualInformation(fxy, fx, Math.pow(fy, pmiAlphaAlpha), nAlpha);
                    }else if(twType == 1){
                        weight = lb(1d+(nAlpha*fxy/(fx*Math.pow(fy, pmiAlphaAlpha))));
                    }else if(twType == 2){
                        weight = Math.pow(lb(1d+(nAlpha*fxy/(fx*Math.pow(fy, pmiAlphaAlpha)))),2d);
                    }else if(twType == 3){
                        weight = lb(nAlpha*fxy/(fx*Math.pow(fy, pmiAlphaAlpha)))*lb(1d+(nAlpha*fxy/(fx*Math.pow(fy, pmiAlphaAlpha))));
                    }else{
                        System.out.println("Wrong TW parameter in the weighting scheme: " + twType);
                        System.exit(1);
                    }

                }else if(weightingSchemeStringParts[0].equals("NPmi")){

                    if(twType == 0 || twType == 4){
                        weight = mutualInformation(fxy, fx, fy, n)/(-1d*lb(fxy/n));
                    }else if(twType == 1){
                        weight = lb(1d+(n*fxy/(fx*fy)))/(-1d*lb(fxy/n));
                    }else if(twType == 2){
                        weight = Math.pow(lb(1d+(n*fxy/(fx*fy))),2d)/(-1d*lb(fxy/n));
                    }else if(twType == 3){
                        weight = lb(n*fxy/(fx*fy))*lb(1d+(n*fxy/(fx*fy)))/(-1d*lb(fxy/n));
                    }else{
                        System.out.println("Wrong TW parameter in the weighting scheme: " + twType);
                        System.exit(1);
                    }

                }else if(weightingSchemeStringParts[0].equals("PmiWdf")){

                    if(twType == 0 || twType == 4){
                        weight = mutualInformation(fxy, fx, fy, n)*fxy/(fxy+1d)*Math.min(fx,fy)/(Math.min(fx,fy)+1d);
                    }else if(twType == 1){
                        weight = lb(1d+(n*fxy/(fx*fy)))*fxy/(fxy+1d)*Math.min(fx,fy)/(Math.min(fx,fy)+1d);
                    }else if(twType == 2){
                        weight = Math.pow(lb(1d+(n*fxy/(fx*fy))),2d)*fxy/(fxy+1d)*Math.min(fx,fy)/(Math.min(fx,fy)+1d);
                    }else if(twType == 3){
                        weight = lb(n*fxy/(fx*fy))*lb(1d+(n*fxy/(fx*fy)))*fxy/(fxy+1d)*Math.min(fx,fy)/(Math.min(fx,fy)+1d);
                    }else{
                        System.out.println("Wrong TW parameter in the weighting scheme: " + twType);
                        System.exit(1);
                    }

                }else if(weightingSchemeStringParts[0].equals("Unis")){

                    double a = fxy;
                    double b = fx-a;
                    double c = fy-a;
                    double d = n-fx-fy+fxy;

                    if(b>0d && c>0d && d>0d){
                        if(twType == 0 || twType == 4){
                            weight = lb(a*d/(b*c))-3.29d*Math.sqrt(1/a+1/b+1/c+1/d);
                        }else if(twType == 1){
                            weight = lb(1d+(a*d/(b*c)))-3.29d*Math.sqrt(1/a+1/b+1/c+1/d);
                        }else if(twType == 2){
                            weight = Math.pow(lb(1d+(a*d/(b*c))), 2d)-3.29d*Math.sqrt(1/a+1/b+1/c+1/d);
                        }else if(twType == 3){
                            weight = lb(a*d/(b*c))*lb(1d+(a*d/(b*c)))-3.29d*Math.sqrt(1/a+1/b+1/c+1/d);
                        }else{
                            System.out.println("Wrong TW parameter in the weighting scheme: " + twType);
                            System.exit(1);
                        }
                    }else{
                        return 0d;
                    }

                }else if(weightingSchemeStringParts[0].equals("PmiAlUnis")){

                    double a = fxy;
                    double b = fx-a;
                    double c = Math.pow(fy, pmiAlphaAlpha)-a;
                    double d = nAlpha-fx-Math.pow(fy, pmiAlphaAlpha)+fxy;

                    if(b>0d && c>0d && d>0d){
                        if(twType == 0 || twType == 4){
                            weight = lb(a*d/(b*c))-3.29d*Math.sqrt(1/a+1/b+1/c+1/d);
                        }else if(twType == 1){
                            weight = lb(1d+(a*d/(b*c)))-3.29d*Math.sqrt(1/a+1/b+1/c+1/d);
                        }else if(twType == 2){
                            weight = Math.pow(lb(1d+(a*d/(b*c))), 2d)-3.29d*Math.sqrt(1/a+1/b+1/c+1/d);
                        }else if(twType == 3){
                            weight = lb(a*d/(b*c))*lb(1d+(a*d/(b*c)))-3.29d*Math.sqrt(1/a+1/b+1/c+1/d);
                        }else{
                            System.out.println("Wrong TW parameter in the weighting scheme: " + twType);
                            System.exit(1);
                        }
                    }else{
                        return 0d;
                    }

                }else if(weightingSchemeStringParts[0].equals("NPmiAl")){

                    if(twType == 0 || twType == 4){
                        weight = mutualInformation(fxy, fx, Math.pow(fy, pmiAlphaAlpha), nAlpha)/(-1d*lb(fxy/n));
                    }else if(twType == 1){
                        weight = lb(1d+(nAlpha*fxy/(fx*Math.pow(fy, pmiAlphaAlpha))))/(-1d*lb(fxy/n));
                    }else if(twType == 2){
                        weight = Math.pow(lb(1d+(nAlpha*fxy/(fx*Math.pow(fy, pmiAlphaAlpha)))),2d)/(-1d*lb(fxy/n));
                    }else if(twType == 3){
                        weight = lb(nAlpha*fxy/(fx*Math.pow(fy, pmiAlphaAlpha)))*lb(1d+(nAlpha*fxy/(fx*Math.pow(fy, pmiAlphaAlpha))))/(-1d*lb(fxy/n));
                    }else{
                        System.out.println("Wrong TW parameter in the weighting scheme: " + twType);
                        System.exit(1);
                    }

                }else if(weightingSchemeStringParts[0].equals("PmiAlUnisAm")){

                    double pmiAlpha = Double.NaN;
                    double uniSubt = Double.NaN;

                    double a = fxy;
                    double b = fx-a;
                    double c = fy-a;
                    double d = n-fx-fy+fxy;

                    if(b>0d && c>0d && d>0d){
                        
                        if(twType == 0 || twType == 4){
                            pmiAlpha = mutualInformation(fxy, fx, Math.pow(fy, pmiAlphaAlpha), nAlpha);
                            uniSubt = lb(a*d/(b*c))-3.29d*Math.sqrt(1/a+1/b+1/c+1/d);
                        }else if(twType == 1){
                            pmiAlpha = lb(1d+(nAlpha*fxy/(fx*Math.pow(fy, pmiAlphaAlpha))));
                            uniSubt = lb(1d+(a*d/(b*c)))-3.29d*Math.sqrt(1/a+1/b+1/c+1/d);
                        }else if(twType == 2){
                            pmiAlpha = Math.pow(lb(1d+(nAlpha*fxy/(fx*Math.pow(fy, pmiAlphaAlpha)))),2d);
                            uniSubt = Math.pow(lb(1d+(a*d/(b*c))), 2d)-3.29d*Math.sqrt(1/a+1/b+1/c+1/d);
                        }else if(twType == 3){
                            pmiAlpha = lb(nAlpha*fxy/(fx*Math.pow(fy, pmiAlphaAlpha)))*lb(1d+(nAlpha*fxy/(fx*Math.pow(fy, pmiAlphaAlpha))));
                            uniSubt = lb(a*d/(b*c))*lb(1d+(a*d/(b*c)))-3.29d*Math.sqrt(1/a+1/b+1/c+1/d);
                        }else{
                            System.out.println("Wrong TW parameter in the weighting scheme: " + twType);
                            System.exit(1);
                        }
                        
                        weight = (pmiAlpha+uniSubt)/2;
                        
                    }else{
                        return 0d;
                    }

                }else{
                    System.out.println("No such WeightingScheme: " + weightingScheme);
                    throw new RuntimeException();
                }

                if(twType == 4){
                    weight = (weight == 0d) ? 0d : (weight * lb(1d+Math.abs(weight)));
                }
                
                
                int sType = Integer.parseInt(weightingSchemeStringParts[1].substring(7, 8));
                    
                if(sType == 1){
                    weight -= lb(spmiK);
                }else if(sType == 2){
                    if(tcType == 4){
                        if(fx>0d && fy>0d && n>0d){
                            weight -= 3.29d*Math.sqrt(1/fxy+1/fx+1/fy+1/n);
                        }else{
                            System.out.println("The code should not be able to get here, as this test is also done previously in case of tcType 4");
                            throw new RuntimeException();
                        }
                    }else{
                        double a = fxy;
                        double b = fx-a;
                        double c = fy-a;
                        double d = n-fx-fy+fxy;
                        if(b>0d && c>0d && d>0d){
                            weight -= 3.29d*Math.sqrt(1/a+1/b+1/c+1/d);
                        }else{
                            return 0d;
                        }
                    }
                }else if(sType != 0){
                    System.out.println("Wrong S parameter in the weighting scheme: " + sType);
                    System.exit(1);
                }

                
                int pType = Integer.parseInt(weightingSchemeStringParts[1].substring(9, 10));
                    
                if(pType == 1){
                    weight *= lb(1d+fxy);
                }else if(pType == 2){
                    weight *= 1d+lb(fxy);
                }else if(pType == 3){
                    if(fx>0d && fy>0d && n>0d){
                        weight *= lb(1d+(n*fxy/(fx*fy)));
                    }else{
                        return 0d;
                    }
                }else if(pType == 4){
                    double div = (Math.min(fx,fy)+1d);
                    if(div != 0d){
                        weight *= fxy/(fxy+1d)*Math.min(fx,fy)/div;
                    }else{
                        return 0d;
                    }
                }else if(pType == 5){
                    weight /= (-1d*lb(fxy/n));
                }else if(pType != 0){
                    System.out.println("Wrong P parameter in the weighting scheme: " + pType);
                    System.exit(1);
                }
                
                
            }else if(weightingScheme==WeightingScheme.Freq){
                weight = fxy;
            }else if(weightingScheme==WeightingScheme.LogFreq){ //already implemented through feature transformation
                weight = lb(1d+fxy);
            }else if(weightingScheme==WeightingScheme.Pmi){
                weight = mutualInformation(fxy, fx, fy, n);
            }else if(weightingScheme==WeightingScheme.LogLhr){
                weight = logLikelihood(fx, fy , fxy, n);
            }else if(weightingScheme==WeightingScheme.Qlflnw){
                weight = lb(1d+fxy)/lb(1+tuplesMap2.get(word2));
            }else if(weightingScheme==WeightingScheme.Plffi){
                weight = lb(1d+fxy)*informationMap.get(word2);
            }else if(weightingScheme==WeightingScheme.Rapp_1){
                weight = entropyMap.get(word2);
            }else if(weightingScheme==WeightingScheme.Rapp_2){ //original Rapp (2003)
                weight = lb(1d+fxy)*entropyMap.get(word2);
            }else if(weightingScheme==WeightingScheme.Rapp_3){
                weight = fxy/fx*entropyMap.get(word2);
            }else if(weightingScheme==WeightingScheme.Rapp_4){
                weight = fxy/fy*entropyMap.get(word2);
            }else if(weightingScheme==WeightingScheme.Rapp_5){
                double p = fxy/(1d+fx);
                weight = -p/lb(p)*entropyMap.get(word2);
            }else if(weightingScheme==WeightingScheme.Rapp_6){
                double p = fxy/(1d+fy);
                weight = -p/lb(p)*entropyMap.get(word2);
            }else if(weightingScheme==WeightingScheme.Identity){
                weight = fxy > 0d ? 1d : 0d;
            }else if(weightingScheme==WeightingScheme.CondProb_1_1){ //original conditional probability (Jurafsky and Martin 2008) pp661, relFreq (Curran 2004) 
                weight = fxy/fx;
            }else if(weightingScheme==WeightingScheme.CondProb_1_2){
                double p = fxy/(1d+fx);
                weight = -p/lb(p);
            }else if(weightingScheme==WeightingScheme.CondProb_1_3){
                weight = 1d/lb(1d+fx/fxy);
            }else if(weightingScheme==WeightingScheme.CondProb_1_4){
                double p = fxy/(1d+fx);
                weight = lb(1d+fxy)*(-p)/lb(p);
            }else if(weightingScheme==WeightingScheme.CondProb_1_5){
                weight = lb(1d+fxy)/lb(1d+fx/fxy);
            }else if(weightingScheme==WeightingScheme.CondProb_1_6){
                weight = lb(1d+fxy)*fxy/fx;
            }else if(weightingScheme==WeightingScheme.CondProb_1_7){
                double p = fx/fxy;
                weight = -p*lb(p);
            }else if(weightingScheme==WeightingScheme.CondProb_1_8){
                double p = fxy/fx;
                weight = -p*lb(p);
            }else if(weightingScheme==WeightingScheme.CondProb_2_1){
                weight = fxy/fy;
            }else if(weightingScheme==WeightingScheme.CondProb_2_2){
                double p = fxy/(1d+fy);
                weight = -p/lb(p);
            }else if(weightingScheme==WeightingScheme.CondProb_2_3){
                weight = 1d/lb(1d+fy/fxy);
            }else if(weightingScheme==WeightingScheme.CondProb_2_4){
                double p = fxy/(1d+fy);
                weight = lb(1d+fxy)*(-p)/lb(p);
            }else if(weightingScheme==WeightingScheme.CondProb_2_5){
                weight = lb(1d+fxy)/lb(1d+fy/fxy);
            }else if(weightingScheme==WeightingScheme.CondProb_2_6){
                weight = lb(1d+fxy)*fxy/fy;
            }else if(weightingScheme==WeightingScheme.CondProb_2_7){
                double p = fy/fxy;
                weight = -p*lb(p);
            }else if(weightingScheme==WeightingScheme.CondProb_2_8){
                double p = fxy/fy;
                weight = -p*lb(p);
            }else if(weightingScheme==WeightingScheme.JointProb){
                weight = fxy/n;
            }else if(weightingScheme==WeightingScheme.PmiWls){
                weight = mutualInformation(fxy+1d, fx+1d, fy+1d, n+1d);
            }else if(weightingScheme==WeightingScheme.WPmi_1){ //original (Fung and McKeown, 1997)
                weight = fxy/n*mutualInformation(fxy, fx, fy, n);
            }else if(weightingScheme==WeightingScheme.WPmi_2){
                weight = fxy/fy*mutualInformation(fxy, fx, fy, n);
            }else if(weightingScheme==WeightingScheme.WPmi_3){
                weight = n*fxy/(fx*fy)*mutualInformation(fxy, fx, fy, n);
            }else if(weightingScheme==WeightingScheme.WPmi_4){
                weight = lb(1d+fxy)*mutualInformation(fxy, fx, fy, n);
            }else if(weightingScheme==WeightingScheme.WPmi_5){
                double p = fxy/n;
                weight = -p/lb(p)*mutualInformation(fxy, fx, fy, n);
            }else if(weightingScheme==WeightingScheme.WPmi_6){
                double p = fxy/(1d+fy);
                weight = -p/lb(p)*mutualInformation(fxy, fx, fy, n);
            }else if(weightingScheme==WeightingScheme.WPmi_7){
                weight = lb(1d+n*fxy/(fx*fy))*mutualInformation(fxy, fx, fy, n);
            }else if(weightingScheme==WeightingScheme.WPmi_8){
                weight = Math.pow(mutualInformation(fxy, fx, fy, n),2d);
            }else if(weightingScheme==WeightingScheme.WPmi_9){
                weight = lb(1d+n*fxy/(fx*fy));
            }else if(weightingScheme==WeightingScheme.WPmi_10){
                weight = Math.pow(lb(1d+n*fxy/(fx*fy)),2d);
            }else if(weightingScheme==WeightingScheme.WPmi_11){
                weight = Math.pow(lb(1d+n*fxy/(fx*fy)),3d);
            }else if(weightingScheme==WeightingScheme.WPmi_12){
                weight = Math.pow(lb(1d+n*fxy/(fx*fy)),4d);
            }else if(weightingScheme==WeightingScheme.WPmi_13){
                weight = Math.pow(lb(1d+n*fxy/(fx*fy)),5d);
            }else if(weightingScheme==WeightingScheme.WPmi_14){
                weight = Math.pow(lb(0.1d+n*fxy/(fx*fy)),2d);
            }else if(weightingScheme==WeightingScheme.WPmi_15){
                weight = Math.pow(lb(2d+n*fxy/(fx*fy)),2d);
            }else if(weightingScheme==WeightingScheme.ModPmiLikeWithoutLog_1){
                double numerator = n*fxy;
                double denominator = fx*fy;
                if(numerator>=denominator){
                    weight = numerator/denominator-1d;
                }else{
                    weight = -denominator/numerator+1d;
                }
            }else if(weightingScheme==WeightingScheme.ModPmiLikeWithoutLog_2){
                double x = n*fxy/(fx*fy);
                weight = (x-1d)/x;
            }else if(weightingScheme==WeightingScheme.ModPmiLikeWithoutLog_3){
                weight = (n*fxy/(fx*fy)) - 1d;
            }else if(weightingScheme==WeightingScheme.PmiCurran){
                weight = mutualInformation(fxy, frequency1MapOfGivenPOS.get(word1), fy, allRelationCount);
            }else if(weightingScheme==WeightingScheme.LPmi_1){
                weight = fxy*mutualInformation(fxy, fx, fy, n);
            }else if(weightingScheme==WeightingScheme.LPmi_2){
                weight = fxy/n*mutualInformation(fxy, fx, fy, n);
            }else if(weightingScheme==WeightingScheme.PmiWdf){
                weight = mutualInformation(fxy, fx, fy, n)*pmiWdfDisc(fxy, fx, fy);
            }else if(weightingScheme==WeightingScheme.PmiAl){
                weight = mutualInformation(fxy, fx, Math.pow(fy, pmiAlphaAlpha), allRelationCountOfGivenTypeAlpha);
            }else if(weightingScheme==WeightingScheme.PmiAlWithoutLog){
                weight = fxy*allRelationCountOfGivenTypeAlpha/(fx*Math.pow(fy, pmiAlphaAlpha));
            }else if(weightingScheme==WeightingScheme.SPmi){
                weight = mutualInformation(fxy, fx, fy, n)-lb(spmiK);
            }else if(weightingScheme==WeightingScheme.NPmi){
                weight = mutualInformation(fxy, fx, fy, n)/(-1d*lb(fxy/n));
            }else if(weightingScheme==WeightingScheme.PmiDisc){
                weight = mutualInformation(fxy-pmiDiscDiscount, fx, fy, n);
            }else if(weightingScheme==WeightingScheme.PmiAlUnis){
                weight = uniSubtuples(fxy, fx, Math.pow(fy, pmiAlphaAlpha), allRelationCountOfGivenTypeAlpha);
            }else if(weightingScheme==WeightingScheme.NPmiUnis){
                weight = uniSubtuples(fxy, fx, fy, n)/(-1d*lb(fxy/n));
            }else if(weightingScheme==WeightingScheme.SPmiUnis){
                weight = uniSubtuples(fxy, fx, fy, n)-lb(spmiK);
            }else if(weightingScheme==WeightingScheme.PmiWdfUnis){
                weight = uniSubtuples(fxy, fx, fy, n)*pmiWdfDisc(fxy, fx, fy);
            }else if(weightingScheme==WeightingScheme.NPmiAl){
                weight = mutualInformation(fxy, fx, Math.pow(fy, pmiAlphaAlpha), allRelationCountOfGivenTypeAlpha)/(-1d*lb(fxy/n));
            }else if(weightingScheme==WeightingScheme.SPmiAl){
                weight = mutualInformation(fxy, fx, Math.pow(fy, pmiAlphaAlpha), allRelationCountOfGivenTypeAlpha)-lb(spmiK);
            }else if(weightingScheme==WeightingScheme.PmiAlWdf){
                weight = mutualInformation(fxy, fx, Math.pow(fy, pmiAlphaAlpha), allRelationCountOfGivenTypeAlpha)*pmiWdfDisc(fxy, fx, fy);
            }else if(weightingScheme==WeightingScheme.NSPmiAl){
                weight = (mutualInformation(fxy, fx, Math.pow(fy, pmiAlphaAlpha), allRelationCountOfGivenTypeAlpha)-lb(spmiK))/(-1d*lb(fxy/n));
            }else if(weightingScheme==WeightingScheme.NPmiAlWdf){
                weight = mutualInformation(fxy, fx, Math.pow(fy, pmiAlphaAlpha), allRelationCountOfGivenTypeAlpha)/(-1d*lb(fxy/n))*pmiWdfDisc(fxy, fx, fy);
            }else if(weightingScheme==WeightingScheme.SPmiAlWdf){
                weight = (mutualInformation(fxy, fx, Math.pow(fy, pmiAlphaAlpha), allRelationCountOfGivenTypeAlpha)-lb(spmiK))*pmiWdfDisc(fxy, fx, fy);
            }else if(weightingScheme==WeightingScheme.NSPmiAlWdf){
                weight = (mutualInformation(fxy, fx, Math.pow(fy, pmiAlphaAlpha), allRelationCountOfGivenTypeAlpha)-lb(spmiK))/(-1d*lb(fxy/n))*pmiWdfDisc(fxy, fx, fy);
            }else if(weightingScheme==WeightingScheme.NSPmi){
                weight = (mutualInformation(fxy, fx, fy, n)-lb(spmiK))/(-1d*lb(fxy/n));
            }else if(weightingScheme==WeightingScheme.NPmiWdf){
                weight = mutualInformation(fxy, fx, fy, n)/(-1d*lb(fxy/n))*pmiWdfDisc(fxy, fx, fy);
            }else if(weightingScheme==WeightingScheme.NSPmiWdf){
                weight = (mutualInformation(fxy, fx, fy, n)-lb(spmiK))/(-1d*lb(fxy/n))*pmiWdfDisc(fxy, fx, fy);
            }else if(weightingScheme==WeightingScheme.SPmiWdf){
                weight = (mutualInformation(fxy, fx, fy, n)-lb(spmiK))*pmiWdfDisc(fxy, fx, fy);
            }else if(weightingScheme==WeightingScheme.NPmiAlUnis){
                weight = uniSubtuples(fxy, fx, Math.pow(fy, pmiAlphaAlpha), allRelationCountOfGivenTypeAlpha)/(-1d*lb(fxy/n));
            }else if(weightingScheme==WeightingScheme.SPmiAlUnis){
                weight = uniSubtuples(fxy, fx, Math.pow(fy, pmiAlphaAlpha), allRelationCountOfGivenTypeAlpha)-lb(spmiK);
            }else if(weightingScheme==WeightingScheme.PmiAlWdfUnis){
                weight = uniSubtuples(fxy, fx, Math.pow(fy, pmiAlphaAlpha), allRelationCountOfGivenTypeAlpha)*pmiWdfDisc(fxy, fx, fy);
            }else if(weightingScheme==WeightingScheme.NSPmiAlUnis){
                weight = (uniSubtuples(fxy, fx, Math.pow(fy, pmiAlphaAlpha), allRelationCountOfGivenTypeAlpha)-lb(spmiK))/(-1d*lb(fxy/n));
            }else if(weightingScheme==WeightingScheme.NPmiAlWdfUnis){
                weight = uniSubtuples(fxy, fx, Math.pow(fy, pmiAlphaAlpha), allRelationCountOfGivenTypeAlpha)/(-1d*lb(fxy/n))*pmiWdfDisc(fxy, fx, fy);
            }else if(weightingScheme==WeightingScheme.SPmiAlWdfUnis){
                weight = (uniSubtuples(fxy, fx, Math.pow(fy, pmiAlphaAlpha), allRelationCountOfGivenTypeAlpha)-lb(spmiK))*pmiWdfDisc(fxy, fx, fy);
            }else if(weightingScheme==WeightingScheme.NSPmiAlWdfUnis){
                weight = (uniSubtuples(fxy, fx, Math.pow(fy, pmiAlphaAlpha), allRelationCountOfGivenTypeAlpha)-lb(spmiK))/(-1d*lb(fxy/n))*pmiWdfDisc(fxy, fx, fy);
            }else if(weightingScheme==WeightingScheme.NSPmiUnis){
                weight = (uniSubtuples(fxy, fx, fy, n)-lb(spmiK))/(-1d*lb(fxy/n));
            }else if(weightingScheme==WeightingScheme.NPmiWdfUnis){
                weight = uniSubtuples(fxy, fx, fy, n)/(-1d*lb(fxy/n))*pmiWdfDisc(fxy, fx, fy);
            }else if(weightingScheme==WeightingScheme.NSPmiWdfUnis){
                weight = (uniSubtuples(fxy, fx, fy, n)-lb(spmiK))/(-1d*lb(fxy/n))*pmiWdfDisc(fxy, fx, fy);
            }else if(weightingScheme==WeightingScheme.SPmiWdfUnis){
                weight = (uniSubtuples(fxy, fx, fy, n)-lb(spmiK))*pmiWdfDisc(fxy, fx, fy);
            }else if(weightingScheme==WeightingScheme.PmiAlUnisAm){
                double pmiAlpha = mutualInformation(fxy, fx, Math.pow(fy, pmiAlphaAlpha), allRelationCountOfGivenTypeAlpha);
                double uniSubt = uniSubtuples(fxy, fx, fy, n);
                weight = (pmiAlpha+uniSubt)/2;
            }else if(weightingScheme==WeightingScheme.PmiAlUnisGm){
                double pmiAlpha = mutualInformation(fxy, fx, Math.pow(fy, pmiAlphaAlpha), allRelationCountOfGivenTypeAlpha);
                double uniSubt = uniSubtuples(fxy, fx, fy, n);
                weight = Math.signum(pmiAlpha*uniSubt)*Math.sqrt(Math.abs(pmiAlpha*uniSubt));
            }else if(weightingScheme==WeightingScheme.PmiAlUnisHm){
                double pmiAlpha = mutualInformation(fxy, fx, Math.pow(fy, pmiAlphaAlpha), allRelationCountOfGivenTypeAlpha);
                double uniSubt = uniSubtuples(fxy, fx, fy, n);
                double den = Math.abs(pmiAlpha)+Math.abs(uniSubt);
                if(den==0d){
                    weight = 0d;
                }else{
                    weight = 2*pmiAlpha*uniSubt/den;
                }
            }else if(weightingScheme==WeightingScheme.PmiAlUnisProd){
                double pmiAlpha = mutualInformation(fxy, fx, Math.pow(fy, pmiAlphaAlpha), allRelationCountOfGivenTypeAlpha);
                double uniSubt = uniSubtuples(fxy, fx, fy, n);
                weight = pmiAlpha*uniSubt;
            }else if(weightingScheme==WeightingScheme.PmiAlUnisLogProd){
                double pmiAlpha = mutualInformation(fxy, fx, Math.pow(fy, pmiAlphaAlpha), allRelationCountOfGivenTypeAlpha);
                double uniSubt = uniSubtuples(fxy, fx, fy, n);
                weight = Math.signum(pmiAlpha)*lb(1d+Math.abs(pmiAlpha))*Math.signum(uniSubt)*lb(1d+Math.abs(uniSubt));
            }else if(weightingScheme==WeightingScheme.NPmiAlAm){
                double pmiAlpha = mutualInformation(fxy, fx, Math.pow(fy, pmiAlphaAlpha), allRelationCountOfGivenTypeAlpha);
                double npmi = mutualInformation(fxy, fx, fy, n)/(-1d*lb(fxy/n));
                weight = (pmiAlpha+npmi)/2;
            }else if(weightingScheme==WeightingScheme.NPmiAlGm){
                double pmiAlpha = mutualInformation(fxy, fx, Math.pow(fy, pmiAlphaAlpha), allRelationCountOfGivenTypeAlpha);
                double npmi = mutualInformation(fxy, fx, fy, n)/(-1d*lb(fxy/n));
                weight = Math.signum(pmiAlpha*npmi)*Math.sqrt(Math.abs(pmiAlpha*npmi));
            }else if(weightingScheme==WeightingScheme.NPmiAlHm){
                double pmiAlpha = mutualInformation(fxy, fx, Math.pow(fy, pmiAlphaAlpha), allRelationCountOfGivenTypeAlpha);
                double npmi = mutualInformation(fxy, fx, fy, n)/(-1d*lb(fxy/n));
                double den = Math.abs(pmiAlpha)+Math.abs(npmi);
                if(den==0d){
                    weight = 0d;
                }else{
                    weight = 2*pmiAlpha*npmi/den;
                }
            }else if(weightingScheme==WeightingScheme.NPmiAlProd){
                double pmiAlpha = mutualInformation(fxy, fx, Math.pow(fy, pmiAlphaAlpha), allRelationCountOfGivenTypeAlpha);
                double npmi = mutualInformation(fxy, fx, fy, n)/(-1d*lb(fxy/n));
                weight = pmiAlpha*npmi;
            }else if(weightingScheme==WeightingScheme.NPmiAlLogProd){
                double pmiAlpha = mutualInformation(fxy, fx, Math.pow(fy, pmiAlphaAlpha), allRelationCountOfGivenTypeAlpha);
                double npmi = mutualInformation(fxy, fx, fy, n)/(-1d*lb(fxy/n));
                weight = Math.signum(pmiAlpha)*lb(1d+Math.abs(pmiAlpha))*Math.signum(npmi)*lb(1d+Math.abs(npmi));
            }else if(weightingScheme==WeightingScheme.Ochiai){
                weight = fxy/Math.sqrt(fx*fy);
            }else if(weightingScheme==WeightingScheme.Cca){
                weight = fxy/Math.sqrt(fx*Math.pow(fy, pmiAlphaAlpha))*Math.sqrt(allRelationCountOfGivenTypeAlpha/n);
            }else if(weightingScheme==WeightingScheme.APmiMod){
                weight = Math.sqrt(lb(1d+fxy)/(lb(1d+fx)*lb(1d+fy)));
            }else if(weightingScheme==WeightingScheme.Simpson_1Mod){
                weight = lb(1d+fxy)/Math.min(lb(1d+fx),lb(1d+fy));
            }else if(weightingScheme==WeightingScheme.Simpson_2){
                weight = fxy/Math.min(fx,fy);
            }else if(weightingScheme==WeightingScheme.Md){
                weight = mutualInformation(Math.pow(fxy, 2d), fx, fy, 1);
            }else if(weightingScheme==WeightingScheme.LfbMd){
                weight = mutualInformation(Math.pow(fxy, 2d), fx, fy, 1) + lb(fxy/n);
            }else if(weightingScheme==WeightingScheme.LfbMdMod_1){
                weight = mutualInformation(Math.pow(fxy, 2d), fx, fy, n) + lb(fxy/n);
            }else if(weightingScheme==WeightingScheme.LfbMdMod_2){
                weight = mutualInformation(Math.pow(fxy, 2d), fx, fy, n) + lb(1d+fxy);
            }else if(weightingScheme==WeightingScheme.Me){
                weight = 2*fxy/(fx+fy)*fxy/n;
            }else if(weightingScheme==WeightingScheme.SalienceMod_1){
                weight = mutualInformation(Math.pow(fxy, 2d), fx, fy, 1) * lb(1d+fxy);
            }else if(weightingScheme==WeightingScheme.SalienceMod_2){
                weight = mutualInformation(Math.pow(fxy, 2d), fx, fy, n) * lb(1d+fxy);
            }else if(weightingScheme==WeightingScheme.SimplPmi){
                weight = Math.pow(fxy, 2d)/(fx*fy);
            }else if(weightingScheme==WeightingScheme.BinLh){
                weight = binLH(fxy, fx, fy, n);
            }else if(weightingScheme==WeightingScheme.PoissonLh){
                weight = poissonLH(fxy, fx, fy, n);
            }else if(weightingScheme==WeightingScheme.PoissonStirlingLh){
                weight = poissonStirlingLH(fxy, fx, fy, n);
            }else if(weightingScheme==WeightingScheme.PoissonSig){
                weight = poissonSig(fxy, fx, fy, n);
            }else if(weightingScheme==WeightingScheme.SqLogLhr){
                weight = sqLogLikelihood(fxy, fx, fy, n);
            }else if(weightingScheme==WeightingScheme.SokalMichiner){
                weight = sokalMichiner(fxy, fx, fy, n);
            }else if(weightingScheme==WeightingScheme.RogersTanimoto){
                weight = rogersTanimoto(fxy, fx, fy, n);
            }else if(weightingScheme==WeightingScheme.Hamann){
                weight = hamann(fxy, fx, fy, n);
            }else if(weightingScheme==WeightingScheme.SokalSneath_1){
                weight = sokalSneath1(fxy, fx, fy, n);
            }else if(weightingScheme==WeightingScheme.SokalSneath_2){
                weight = sokalSneath2(fxy, fx, fy, n);
            }else if(weightingScheme==WeightingScheme.SokalSneath_3){
                weight = sokalSneath3(fxy, fx, fy, n);
            }else if(weightingScheme==WeightingScheme.Kulczynski_1){
                weight = kulczynski1(fxy, fx, fy, n);
            }else if(weightingScheme==WeightingScheme.Kulczynski_2){
                weight = kulczynski2(fxy, fx, fy, n);
            }else if(weightingScheme==WeightingScheme.YulleW){
                weight = yulleW(fxy, fx, fy, n);
            }else if(weightingScheme==WeightingScheme.YulleQ){
                weight = yulleQ(fxy, fx, fy, n);
            }else if(weightingScheme==WeightingScheme.DriverKroeber){
                weight = driverKroeber(fxy, fx, fy, n);
            }else if(weightingScheme==WeightingScheme.Pears){
                weight = pearson(fxy, fx, fy, n);
            }else if(weightingScheme==WeightingScheme.Fager){
                weight = fager(fxy, fx, fy, n);
            }else if(weightingScheme==WeightingScheme.Unis){
                weight = uniSubtuples(fxy, fx, fy, n);
            }else if(weightingScheme==WeightingScheme.TCombCost){
                weight = tCombCost(fxy, fx, fy, n);
            }else if(weightingScheme==WeightingScheme.Phi){
                weight = phi(fxy, fx, fy, n);
            }else if(weightingScheme==WeightingScheme.Kappa){
                weight = kappa(fxy, fx, fy, n);
            }else if(weightingScheme==WeightingScheme.J){
                weight = jMeasure(fxy, fx, fy, n);
            }else if(weightingScheme==WeightingScheme.Gini){
                weight = gini(fxy, fx, fy, n);
            }else if(weightingScheme==WeightingScheme.Confidence){
                weight = confidence(fxy, fx, fy, n);
            }else if(weightingScheme==WeightingScheme.Laplace){
                weight = laplace(fxy, fx, fy, n);
            }else if(weightingScheme==WeightingScheme.PiaterskyShapiro){
                weight = piaterskyShapiro(fxy, fx, fy, n);
            }else if(weightingScheme==WeightingScheme.Certainty){
                weight = certainty(fxy, fx, fy, n);
            }else if(weightingScheme==WeightingScheme.AddedValue){
                weight = addedValue(fxy, fx, fy, n);
            }else if(weightingScheme==WeightingScheme.TTest_1){
                weight = tTest1(fxy, fx, fy, n);
            }else if(weightingScheme==WeightingScheme.TTest_2){
                weight = tTest2(fxy, fx, fy, n);
            }else if(weightingScheme==WeightingScheme.TTest_3){
                weight = tTest3(fxy, fx, fy, n);
            }else if(weightingScheme==WeightingScheme.ZTest_1){
                weight = zTest1(fxy, fx, fy, n);
            }else if(weightingScheme==WeightingScheme.ZTest_2){
                weight = zTest2(fxy, fx, fy, n);
            }else if(weightingScheme==WeightingScheme.ZTest_3){
                weight = zTest3(fxy, fx, fy, n);
            }else if(weightingScheme==WeightingScheme.SimpleLl){
                weight = simpleLL(fxy, fx, fy, n);
            }else if(weightingScheme==WeightingScheme.Allr){
                weight = allr(fxy, fx, fy, n);
            }else if(weightingScheme==WeightingScheme.ChiSquare){
                weight = chiSquare(fxy, fx, fy, n);
            }else if(weightingScheme==WeightingScheme.ChiSquareWYCC){
                weight = chiSquareWYCC(fxy, fx, fy, n);
            }else if(weightingScheme==WeightingScheme.TfIdf_1){
                weight = fxy/tuplesMap2.get(word2);
            }else if(weightingScheme==WeightingScheme.TfIdf_2){
                weight = lb(1d+fxy)/lb(1d+(double) tuplesMap2.size()/tuplesMap2.get(word2));
            }else if(weightingScheme==WeightingScheme.TfIdf_3){
                weight = fxy*lb((double) n/fy);
            }else if(weightingScheme==WeightingScheme.TfIdf_4){
                weight = lb(fxy)*lb((double) n/tuplesMap2.get(word2));
            }else if(weightingScheme==WeightingScheme.TfIdf_5){
                weight = lb(1d+fxy)*lb((double) n/tuplesMap2.get(word2));
            }else if(weightingScheme==WeightingScheme.TfIdf_6){
                weight = 1d+lb(fxy);
            }else if(weightingScheme==WeightingScheme.TfIdf_7){
                weight = (1d+lb(fxy))*lb((double) n/tuplesMap2.get(word2));
            }else if(weightingScheme==WeightingScheme.TfIdf_8){
                weight = (1d+lb(fxy))*Math.max(0d, lb((double) (n-tuplesMap2.get(word2))/tuplesMap2.get(word2)));
            }else if(weightingScheme==WeightingScheme.TfIdf_9){
                weight = lb(1d+(fxy/(tuplesMap2.get(word2)*vectorElementSumMap.get(word1)))*(0.15d*allFeatureDocFreqCount/0.85d));
            }else if(weightingScheme==WeightingScheme.Atc){
                weight = (0.5d+0.5d*fxy/maxWordFeaturePairFrequency)*lb(n/tuplesMap2.get(word2))/atcFeatureWeightMap.get(word2);
            }else if(weightingScheme==WeightingScheme.Ltu){
                weight = ltu(fxy, vectorElementSumMap.get(word1), avgWordVectorElementSum, tuplesMap2.get(word2), n);
            }else if(weightingScheme==WeightingScheme.Okapi_1){
                weight = okapi1(fxy, vectorElementSumMap.get(word1), avgWordVectorElementSum, tuplesMap2.get(word2), n);
            }else if(weightingScheme==WeightingScheme.Okapi_2){
                weight = okapi2(fxy, vectorElementSumMap.get(word1), avgWordVectorElementSum, tuplesMap2.get(word2), n);
            }else if(weightingScheme==WeightingScheme.Okapi_3){
                weight = okapi3(fxy, vectorElementSumMap.get(word1), avgWordVectorElementSum, tuplesMap2.get(word2), n);
            }else if(weightingScheme==WeightingScheme.TfIcf_1){
                weight = lb(fxy)*lb((double) n/fy);
            }else if(weightingScheme==WeightingScheme.TfIcf_2){
                weight = lb(1d+fxy)*lb((double) n/fy);
            }else if(weightingScheme==WeightingScheme.TfIcf_3){
                weight = lb(1d+fxy)*lb((double) (1+n)/(1+tuplesMap2.get(word2)));
            }else if(weightingScheme==WeightingScheme.Gref_1){
                weight = lb(1d+fxy)/lb(1+tuplesMap2.get(word2));
            }else if(weightingScheme==WeightingScheme.Gref_2){
                double grefFW = grefFeatureWeightMap.get(word2);
                if(grefFW==0){
                    weight=0d;
                }else{
                    weight = lb(1d+fxy)/grefFW;
                }
            }else if(weightingScheme==WeightingScheme.Lin_1_1){
                weight = (double) allWordTypeCount/tuplesMap2.get(word2);
            }else if(weightingScheme==WeightingScheme.Lin_1_2){
                weight = Math.log(1d+(double) allWordTypeCount/tuplesMap2.get(word2));
            }else if(weightingScheme==WeightingScheme.Lin_1_3){ //original lin98B (Curran 2004)
                weight = -Math.log((double) tuplesMap2.get(word2)/allWordTypeCount);
            }else if(weightingScheme==WeightingScheme.Lin_1_4){
                weight = Math.log(1d+fxy)*Math.log(1d+(double) allWordTypeCount/tuplesMap2.get(word2));
            }else if(weightingScheme==WeightingScheme.Lin_1_5){
                weight = -Math.log(1d+fxy)*Math.log((double) tuplesMap2.get(word2)/allWordTypeCount);
            }else if(weightingScheme==WeightingScheme.Lin_1_6){
                double p = (double) allWordTypeCount/tuplesMap2.get(word2);
                weight = p*Math.log(1d+p);
            }else if(weightingScheme==WeightingScheme.Lin_1_7){
                double p = (double) tuplesMap2.get(word2)/allWordTypeCount;
                weight = -p*Math.log(p);
            }else if(weightingScheme==WeightingScheme.Lin_1_8){
                double p = (double) allWordTypeCount/tuplesMap2.get(word2);
                weight = -p*Math.log(p);
            }else if(weightingScheme==WeightingScheme.Lin_2){ //original lin98B (Kiela and Clark, 2014)
                weight = -Math.log((double) tuplesMap2.get(word2)/n);
            }else if(weightingScheme==WeightingScheme.Lin_3){ //original lin98A (Kiela and Clark, 2014)
                weight = n*fxy/(fx*fy);
            }else if(weightingScheme==WeightingScheme.Dice){
                weight = 2d*fxy/(fx+fy);
            }else if(weightingScheme==WeightingScheme.RelRisk_1){
                weight = relRisk(fxy, fx, fy, n);
            }else if(weightingScheme==WeightingScheme.RelRisk_2){
                weight = relRisk(fxy, fx, fy, n);
                if(weight!=0d){
                    weight = lb(weight);
                }
            }else if(weightingScheme==WeightingScheme.Liddell){
                weight = liddell(fxy, fx, fy, n);
            }else if(weightingScheme==WeightingScheme.Jaccard){
                weight = jaccard(fxy, fx, fy, n);
            }else if(weightingScheme==WeightingScheme.GMean){
                weight = gMean(fxy, fx, fy, n);
            }else if(weightingScheme==WeightingScheme.MinSens){
                weight = minSens(fxy, fx, fy, n);
            }else if(weightingScheme==WeightingScheme.OddsRatio_1){
                weight = oddsRatio1(fxy, fx, fy, n);
            }else if(weightingScheme==WeightingScheme.OddsRatio_2){
                weight = oddsRatio1(fxy, fx, fy, n);
                if(weight!=0d){
                    weight = Math.log(weight);
                }
            }else if(weightingScheme==WeightingScheme.OddsRatio_3){
                weight = Math.log(oddsRatio2(fxy, fx, fy, n));
            }else if(weightingScheme==WeightingScheme.PmiPow_2){
                weight = mutualInformation(Math.pow(fxy, 2d), fx, fy, n);
            }else if(weightingScheme==WeightingScheme.PmiPow_3){
                weight = mutualInformation(Math.pow(fxy, 3d), fx, fy, n);
            }else if(weightingScheme==WeightingScheme.MinPmiTTest_1){
                weight = minPmiTTest1(fxy, fx, fy, n);
            }else if(weightingScheme==WeightingScheme.MinPmiTTest_2){
                weight = minPmiTTest2(fxy, fx, fy, n);
            }else if(weightingScheme==WeightingScheme.KrippendorffAlpha){
                weight = krippendorffAlpha(fxy, fx, fy, n);
            }else if(weightingScheme==WeightingScheme.ScottPi){
                weight = scottPi(fxy, fx, fy, n);
            }else if(weightingScheme==WeightingScheme.CohenKappa){
                weight = cohenKappa(fxy, fx, fy, n);
            }else if(weightingScheme==WeightingScheme.Ncd){
                weight = distanceToSimilarity((fxy-Math.min(fx, fy))/Math.max(fx, fy));
            }else if(weightingScheme==WeightingScheme.NgdMod){
                weight = distanceToSimilarity(ngd(fxy, fx, fy, n));
            }else if(weightingScheme==WeightingScheme.ANgdMod){
                weight = Math.exp(-2*ngd(fxy, fx, fy, n));
            }else if(weightingScheme==WeightingScheme.PmiXTTest_1){
                weight = mutualInformation(fxy, fx, fy, n)*tTest1(fxy, fx, fy, n);
            }else if(weightingScheme==WeightingScheme.PmiXTTest_2){
                weight = mutualInformation(fxy, fx, fy, n)*tTest2(fxy, fx, fy, n);
            }else if(weightingScheme==WeightingScheme.PmiXChiSquare){
                weight = mutualInformation(fxy, fx, fy, n)*chiSquare(fxy, fx, fy, n);
            }else if(weightingScheme==WeightingScheme.PmiXTfIdf_1){
                weight = mutualInformation(fxy, fx, fy, n)*fxy/tuplesMap2.get(word2);
            }else if(weightingScheme==WeightingScheme.PmiXRelRisk_1){
                weight = mutualInformation(fxy, fx, fy, n)*relRisk(fxy, fx, fy, n);
            }else if(weightingScheme==WeightingScheme.PmiXRelRisk_2){
                weight = relRisk(fxy, fx, fy, n);
                if(weight!=0d){
                    weight = mutualInformation(fxy, fx, fy, n)*lb(weight);
                }
            }else if(weightingScheme==WeightingScheme.PmiXLiddell){
                weight = mutualInformation(fxy, fx, fy, n)*liddell(fxy, fx, fy, n);
            }else if(weightingScheme==WeightingScheme.PmiXOddsRatio_3){
                weight = mutualInformation(fxy, fx, fy, n)*Math.log(oddsRatio2(fxy, fx, fy, n));
            }else if(weightingScheme==WeightingScheme.PmiXOkapi_1){
                weight = mutualInformation(fxy, fx, fy, n)*okapi1(fxy, vectorElementSumMap.get(word1), avgWordVectorElementSum, tuplesMap2.get(word2), n);
            }else if(weightingScheme==WeightingScheme.PmiXLtu){
                weight = mutualInformation(fxy, fx, fy, n)*ltu(fxy, vectorElementSumMap.get(word1), avgWordVectorElementSum, tuplesMap2.get(word2), n);
            }else if(weightingScheme==WeightingScheme.PmiXRapp_1){
                weight = mutualInformation(fxy, fx, fy, n)*fxy/fy*entropyMap.get(word2);
            }else if(weightingScheme==WeightingScheme.PmiXRapp_4){
                weight = mutualInformation(fxy, fx, fy, n)*fxy/fy*entropyMap.get(word2);
            }else if(weightingScheme==WeightingScheme.PmiXRapp_6){
                double p = fxy/(1d+fy);
                weight = mutualInformation(fxy, fx, fy, n)*(-p)/lb(p)*entropyMap.get(word2);
            }else if(weightingScheme==WeightingScheme.PmiXCondProb_2_1){
                weight = mutualInformation(fxy, fx, fy, n)*fxy/fy;
            }else if(weightingScheme==WeightingScheme.PmiXCondProb_2_2){
                double p = fxy/(1d+fy);
                weight = mutualInformation(fxy, fx, fy, n)*(-p)/lb(p);
            }else if(weightingScheme==WeightingScheme.PmiXCondProb_2_4){
                double p = fxy/(1d+fy);
                weight = mutualInformation(fxy, fx, fy, n)*lb(1d+fxy)*(-p)/lb(p);
            }else if(weightingScheme==WeightingScheme.PmiXCondProb_2_6){
                weight = mutualInformation(fxy, fx, fy, n)*lb(1d+fxy)*fxy/fy;
            }else if(weightingScheme==WeightingScheme.PmiXLin_1_1){
                weight = mutualInformation(fxy, fx, fy, n)*allWordTypeCount/tuplesMap2.get(word2);
            }else if(weightingScheme==WeightingScheme.PmiXLin_1_2){
                weight = mutualInformation(fxy, fx, fy, n)*Math.log(1d+(double) allWordTypeCount/tuplesMap2.get(word2));
            }else if(weightingScheme==WeightingScheme.PmiXLin_1_3){
                weight = mutualInformation(fxy, fx, fy, n)*-Math.log((double) tuplesMap2.get(word2)/allWordTypeCount);
            }else if(weightingScheme==WeightingScheme.PmiXLin_1_4){
                weight = mutualInformation(fxy, fx, fy, n)*Math.log(1d+fxy)*Math.log(1d+(double) allWordTypeCount/tuplesMap2.get(word2));
            }else if(weightingScheme==WeightingScheme.PmiXLin_1_5){
                weight = mutualInformation(fxy, fx, fy, n)*-Math.log(1d+fxy)*Math.log((double) tuplesMap2.get(word2)/allWordTypeCount);
            }else if(weightingScheme==WeightingScheme.PmiXLin_1_6){
                double p = (double) allWordTypeCount/tuplesMap2.get(word2);
                weight = mutualInformation(fxy, fx, fy, n)*p*Math.log(1d+p);
            }else{
                System.out.println("No such WeightingScheme: " + weightingScheme);
                throw new RuntimeException();
            }
            
        }
        
        
        if(Double.isFinite(weight)){
            return weight;
        }else{
            System.out.println("NaN or Infinity weight: " + weightingScheme + " " + word1 + " " + word2 + " " + weight + 
                    "\n\t fxy=" + fxy + " fx=" + frequency1Map.get(word1) + " fy=" + frequency2Map.get(word2) + " n=" + allRelationCountOfGivenType + 
                    "\n\t tuplesMap2=" + tuplesMap2.get(word2) + " frequency1MapOfGivenPOS=" + frequency1MapOfGivenPOS.get(word1) + 
                    "\n\t informationMap=" + informationMap.get(word2) + " entropyMap=" + entropyMap.get(word2) + 
                    "\n\t grefFeatureWeightMap=" + grefFeatureWeightMap.get(word2) + " allRelationCountOfGivenTypeAlpha=" + allRelationCountOfGivenTypeAlpha + 
                    "\n\t allRelationCount=" + allRelationCount + " atcFeatureWeightMap=" + atcFeatureWeightMap.get(word2) + 
                    "\n\t vectorElementSumMap=" + vectorElementSumMap.get(word1) + " avgWordVectorElementSum=" + avgWordVectorElementSum + 
                    "\n\t maxWordFeaturePairFrequency=" + maxWordFeaturePairFrequency + " allWordTypeCount=" + allWordTypeCount + 
                    "\n\t allFeatureDocFreqCount=" + allFeatureDocFreqCount);
            throw new RuntimeException();
        }
        
        
    }
    
    
    /**
     * This method computes the mutual information of a (word, feature) pair based on the frequencies given as input parameters.
     * @param fxy the frequency with which word x and feature y co-occur
     * @param fx the frequency of word x
     * @param fy the frequency of feature y
     * @param N  the count of all the relations of the given type
     * @return the mutual information
     */
    public static double mutualInformation(double fxy, double fx, double fy, double N){
        
        return lb((fxy*N)/(fy*fx));
        
    }
    
    
    
    
    
    
    /**
     * This method computes the PmiWdf discounting factor of a (word, feature) pair based on the frequencies given as input parameters.
     * @param fxy the frequency with which word x and feature y co-occur
     * @param fx the frequency of word x
     * @param fy the frequency of feature y
     * @return the mutual information
     */
    public static double pmiWdfDisc(double fxy, double fx, double fy){
        
        return fxy/(fxy+1d)*Math.min(fx,fy)/(Math.min(fx,fy)+1d);
        
    }
    
    
    
    
    /**
     * This function computes the log likelihood ratio of a (word, feature) pair based on the frequencies given as input parameters.
     * @param c1 the frequency of word x
     * @param c2 the frequency of feature y
     * @param c12 the frequency with which word x and feature y co-occur
     * @param N the count of all the relations of the given type
     * @return the logLikelihood
     */
    public static double logLikelihood(double c1, double c2, double c12, double N){
        
        double p=c2/N;
        double p1=c12/c1;
        double p2=(c2-c12)/(N-c1);
        
        if(N==c1 || N==c2 || N==0 || c1==0d || c2==0d || c12==0d){
            System.out.println(c1 + " " + c2 + " " + c12 + " " + N);
            System.exit(1);
        }
        
        double d = -2d*(logLFunctionInsideLogLikelihood(c12, c1, p)+logLFunctionInsideLogLikelihood(c2-c12,N-c1,p)-logLFunctionInsideLogLikelihood(c12,c1,p1)-logLFunctionInsideLogLikelihood(c2-c12,N-c1,p2));
        
        if(Double.isInfinite(d) || Double.isNaN(d)){
            System.out.println(c1 + " " + c2 + " " + c12 + " " + N + " " + d);
            System.exit(1);
        }
        
        return d;
        
    }
    
    
    /**
     * This is the logL function used inside the logLikelihood function as described in "Manning and Schütze, 2000, Foundations of Statistical Natural Language Processing, p. 173"
     * @param k
     * @param n
     * @param x
     * @return the logL value
     */
    public static double logLFunctionInsideLogLikelihood(double k, double n, double x){
        
        if(x==0d){
            if(k==0d){
                return (n-k)*Math.log(1d-x);
            }else{
                System.out.println(k + " " + n + " " + x);
                System.exit(1);
                throw new IllegalArgumentException();
            }
        }else if(x==1d){
            if((n-k)==0d){
                return k*Math.log(x);
            }else{
                System.out.println(k + " " + n + " " + x);
                System.exit(1);
                throw new IllegalArgumentException();
            }
        }else{
            return k*Math.log(x)+(n-k)*Math.log(1d-x);
        }
        
    }
    
        
    
    /**
     * This method computes the binomial coefficient of two numbers. Acquired from:
     * https://stackoverflow.com/questions/36925730/java-calculating-binomial-coefficient
     * https://rosettacode.org/wiki/Evaluate_binomial_coefficients#Java
     * @param n the upper number
     * @param k the lower number
     * @return the binomial coefficient
     */
    private static long binomial(long n, long k)
    {
        if (k>n-k)
            k=n-k;

        long b=1;
        for (long i=1, m=n; i<=k; i++, m--)
            b=b*m/i;
        return b;
    }
    
    
    
    
    /**
     * This method computes the logarighm of the factorial of a number.
     * @param n the number
     * @return the factorial @param n!
     */
    private static double logFactorial(long n){
        double d=0d;
        for (long i=1; i<=n; i++)
            d+=Math.log(i);
        return d;
    }
    
    
    
    
    
    
    /**
     * This method computes the hypergeometric-likelihood weight of a (word, feature) pair based on the frequencies given as input parameters.
     * @param fxy the frequency with which word x and feature y co-occur
     * @param fx the frequency of word x
     * @param fy the frequency of feature y
     * @param N  the count of all the relations of the given type
     * @return the mutual information
     */
    public static double hypLH(double fxy, double fx, double fy, double N){
        
        long O11 = Math.round(fxy);
        long O12 = Math.round(fx-O11);
        long O22 = Math.round(N-fx-fy+fxy);
        long C1 = Math.round(fy);
        long C2 = Math.round(O12+O22);
        long R1 = Math.round(fx);
        
        return ((double) binomial(C1, O11)/binomial(Math.round(N), R1))*binomial(C2, R1-O11);
        
    }
    
    
    
    
    
    
    /**
     * This method computes the binomial-likelihood weight of a (word, feature) pair based on the frequencies given as input parameters.
     * @param fxy the frequency with which word x and feature y co-occur
     * @param fx the frequency of word x
     * @param fy the frequency of feature y
     * @param N  the count of all the relations of the given type
     * @return the mutual information
     */
    public static double binLH(double fxy, double fx, double fy, double N){
        
        double O11 = fxy;
        double E11 = fx*fy/N;
        
        return binomial(Math.round(N), Math.round(O11))*Math.pow(E11/N, O11)*Math.pow(1-(E11/N), N-O11);
        
    }
    
    
    
    
    
    
    /**
     * This method computes the poisson-likelihood weight of a (word, feature) pair based on the frequencies given as input parameters.
     * @param fxy the frequency with which word x and feature y co-occur
     * @param fx the frequency of word x
     * @param fy the frequency of feature y
     * @param N  the count of all the relations of the given type
     * @return the mutual information
     */
    public static double poissonLH(double fxy, double fx, double fy, double N){
        
        double O11 = fxy;
        double E11 = fx*fy/N;
        
        return Math.exp(-E11+(O11*Math.log(E11))-logFactorial(Math.round(O11)));
        
    }
    
    
    
    
    
    
    /**
     * This method computes the poisson-stirling-likelihood weight of a (word, feature) pair based on the frequencies given as input parameters.
     * @param fxy the frequency with which word x and feature y co-occur
     * @param fx the frequency of word x
     * @param fy the frequency of feature y
     * @param N  the count of all the relations of the given type
     * @return the mutual information
     */
    public static double poissonStirlingLH(double fxy, double fx, double fy, double N){
        
        double O11 = fxy;
        double E11 = fx*fy/N;
        
        return O11*(Math.log(O11)-Math.log(E11)-1);
        
    }
    
    
    
    
    /**
     * This method computes the poisson significance weight of a (word, feature) pair based on the frequencies given as input parameters.
     * @param fxy the frequency with which word x and feature y co-occur
     * @param fx the frequency of word x
     * @param fy the frequency of feature y
     * @param N  the count of all the relations of the given type
     * @return the mutual information
     */
    public static double poissonSig(double fxy, double fx, double fy, double N){
        
        double O11 = fxy;
        double E11 = fx*fy/N;
        
        return (E11-O11*Math.log(E11)+logFactorial(Math.round(O11)))/Math.log(N);
        
    }
    
    
    
    
    /**
     * This method computes the squared log likelihood ratio weight of a (word, feature) pair based on the frequencies given as input parameters.
     * @param fxy the frequency with which word x and feature y co-occur
     * @param fx the frequency of word x
     * @param fy the frequency of feature y
     * @param N  the count of all the relations of the given type
     * @return the chi-square weight
     */
    public static double sqLogLikelihood(double fxy, double fx, double fy, double N){
        
        double O11 = fxy;
        double O12 = fx-O11;
        double O21 = fy-O11;
        double O22 = N-fx-fy+fxy;
        
        if(O12==0d || O21==0d){
            
            return 0d;
            
        }else{
        
            double E11 = fx*fy/N;
            double E12 = fx*(O12+O22)/N;
            double E21 = (O21+O22)*fy/N;
            double E22 = (O21+O22)*(O12+O22)/N;

            return 2*(Math.log(Math.pow(O11, 2d))/E11+Math.log(Math.pow(O12, 2d))/E12+Math.log(Math.pow(O21, 2d))/E21+Math.log(Math.pow(O22, 2d))/E22);
            
        }
        
    }
    
    
    
    
    
    
    /**
     * This method computes the Sokal-Michiner weight of a (word, feature) pair based on the frequencies given as input parameters.
     * @param fxy the frequency with which word x and feature y co-occur
     * @param fx the frequency of word x
     * @param fy the frequency of feature y
     * @param N  the count of all the relations of the given type
     * @return the Sokal-Michiner weight
     */
    public static double sokalMichiner(double fxy, double fx, double fy, double N){
        
        double a = fxy;
        double d = N-fx-fy+fxy;
        
        return (a+d)/N;
        
    }
    
    
    
    
    
    
    /**
     * This method computes the Rogers-Tanimoto weight of a (word, feature) pair based on the frequencies given as input parameters.
     * @param fxy the frequency with which word x and feature y co-occur
     * @param fx the frequency of word x
     * @param fy the frequency of feature y
     * @param N  the count of all the relations of the given type
     * @return the Rogers-Tanimoto weight
     */
    public static double rogersTanimoto(double fxy, double fx, double fy, double N){
        
        double a = fxy;
        double b = fx-a;
        double c = fy-a;
        double d = N-fx-fy+fxy;
        
        return (a+d)/(a+2d*b+2d*c+d);
        
    }
    
    
    
    
    
    
    /**
     * This method computes the Hamann weight of a (word, feature) pair based on the frequencies given as input parameters.
     * @param fxy the frequency with which word x and feature y co-occur
     * @param fx the frequency of word x
     * @param fy the frequency of feature y
     * @param N  the count of all the relations of the given type
     * @return the Hamann weight
     */
    public static double hamann(double fxy, double fx, double fy, double N){
        
        double a = fxy;
        double b = fx-a;
        double c = fy-a;
        double d = N-fx-fy+fxy;
        
        return ((a+d)-(b+c))/N;
        
    }
    
    
    
    
    
    
    /**
     * This method computes the Sokal-Sneath weight of a (word, feature) pair based on the frequencies given as input parameters.
     * @param fxy the frequency with which word x and feature y co-occur
     * @param fx the frequency of word x
     * @param fy the frequency of feature y
     * @param N  the count of all the relations of the given type
     * @return the Sokal-Sneath weight
     */
    public static double sokalSneath1(double fxy, double fx, double fy, double N){
        
        double a = fxy;
        double b = fx-a;
        double c = fy-a;
        double d = N-fx-fy+fxy;
        
        return (a+d)/(b+c);
        
    }
    
    
    
    
    
    
    /**
     * This method computes the Sokal-Sneath weight of a (word, feature) pair based on the frequencies given as input parameters.
     * @param fxy the frequency with which word x and feature y co-occur
     * @param fx the frequency of word x
     * @param fy the frequency of feature y
     * @param N  the count of all the relations of the given type
     * @return the Sokal-Sneath weight
     */
    public static double sokalSneath2(double fxy, double fx, double fy, double N){
        
        double a = fxy;
        double b = fx-a;
        double c = fy-a;
        
        return a/(a+2d*(b+c));
        
    }
    
    
    
    
    
    
    /**
     * This method computes the Sokal-Sneath weight of a (word, feature) pair based on the frequencies given as input parameters.
     * @param fxy the frequency with which word x and feature y co-occur
     * @param fx the frequency of word x
     * @param fy the frequency of feature y
     * @param N  the count of all the relations of the given type
     * @return the Sokal-Sneath weight
     */
    public static double sokalSneath3(double fxy, double fx, double fy, double N){
        
        double a = fxy;
        double b = fx-a;
        double c = fy-a;
        double d = N-fx-fy+fxy;
        
        return ((a/(a+b))+(a/(a+c))+(d/(d+b))+(d/(d+c)))/4;
        
    }
    
    
    
    
    
    
    /**
     * This method computes the Kulczynski weight of a (word, feature) pair based on the frequencies given as input parameters.
     * @param fxy the frequency with which word x and feature y co-occur
     * @param fx the frequency of word x
     * @param fy the frequency of feature y
     * @param N  the count of all the relations of the given type
     * @return the Kulczynski weight
     */
    public static double kulczynski1(double fxy, double fx, double fy, double N){
        
        double a = fxy;
        double b = fx-a;
        double c = fy-a;
        
        return a/(b+c);
        
    }
    
    
    
    
    
    
    /**
     * This method computes the Kulczynski weight of a (word, feature) pair based on the frequencies given as input parameters.
     * @param fxy the frequency with which word x and feature y co-occur
     * @param fx the frequency of word x
     * @param fy the frequency of feature y
     * @param N  the count of all the relations of the given type
     * @return the Kulczynski weight
     */
    public static double kulczynski2(double fxy, double fx, double fy, double N){
        
        double a = fxy;
        double b = fx-a;
        double c = fy-a;
        
        return ((a/(a+b))+(a/(a+c)))/2d;
        
    }
    
    
    
    
    /**
     * This method computes the Yulle's W weight of a (word, feature) pair based on the frequencies given as input parameters.
     * @param fxy the frequency with which word x and feature y co-occur
     * @param fx the frequency of word x
     * @param fy the frequency of feature y
     * @param N  the count of all the relations of the given type
     * @return the Yulle's W weight
     */
    public static double yulleW(double fxy, double fx, double fy, double N){
        
        double a = fxy;
        double b = fx-a;
        double c = fy-a;
        double d = N-fx-fy+fxy;
        
        double d1 = Math.sqrt(a*d);
        double d2 = Math.sqrt(b*c);
        
        return (d1-d2)/(d1+d2);
        
    }
    
    
    
    
    /**
     * This method computes the Yulle's Q weight of a (word, feature) pair based on the frequencies given as input parameters.
     * @param fxy the frequency with which word x and feature y co-occur
     * @param fx the frequency of word x
     * @param fy the frequency of feature y
     * @param N  the count of all the relations of the given type
     * @return the Yulle's Q weight
     */
    public static double yulleQ(double fxy, double fx, double fy, double N){
        
        double a = fxy;
        double b = fx-a;
        double c = fy-a;
        double d = N-fx-fy+fxy;
        
        double d1 = a*d;
        double d2 = b*c;
        
        return (d1-d2)/(d1+d2);
        
    }
    
    
    
    
    /**
     * This method computes the Driver-Kroeber weight of a (word, feature) pair based on the frequencies given as input parameters.
     * @param fxy the frequency with which word x and feature y co-occur
     * @param fx the frequency of word x
     * @param fy the frequency of feature y
     * @param N  the count of all the relations of the given type
     * @return the Driver-Kroeber weight
     */
    public static double driverKroeber(double fxy, double fx, double fy, double N){
        
        double a = fxy;
        double b = fx-a;
        double c = fy-a;
        
        return a/Math.sqrt((a+b)*(a+c));
        
    }
    
    
    
    
    /**
     * This method computes the Pearson weight of a (word, feature) pair based on the frequencies given as input parameters.
     * @param fxy the frequency with which word x and feature y co-occur
     * @param fx the frequency of word x
     * @param fy the frequency of feature y
     * @param N  the count of all the relations of the given type
     * @return the Pearson weight
     */
    public static double pearson(double fxy, double fx, double fy, double N){
        
        double a = fxy;
        double b = fx-a;
        double c = fy-a;
        double d = N-fx-fy+fxy;
        
        return (a*d-b*c)/Math.sqrt((a+b)*(a+c)*(d+b)*(d+c));
        
    }
    
    
    
    
    /**
     * This method computes the Fager weight of a (word, feature) pair based on the frequencies given as input parameters.
     * @param fxy the frequency with which word x and feature y co-occur
     * @param fx the frequency of word x
     * @param fy the frequency of feature y
     * @param N  the count of all the relations of the given type
     * @return the Fager weight
     */
    public static double fager(double fxy, double fx, double fy, double N){
        
        double a = fxy;
        double b = fx-a;
        double c = fy-a;
        
        return a/Math.sqrt((a+b)*(a+c))-Math.max(b,c)/2d;
        
    }
    
    
    
    
    /**
     * This method computes the Unigram subtuples weight of a (word, feature) pair based on the frequencies given as input parameters.
     * @param fxy the frequency with which word x and feature y co-occur
     * @param fx the frequency of word x
     * @param fy the frequency of feature y
     * @param N  the count of all the relations of the given type
     * @return the Unigram subtuples weight
     */
    public static double uniSubtuples(double fxy, double fx, double fy, double N){
        
        double a = fxy;
        double b = fx-a;
        double c = fy-a;
        double d = N-fx-fy+fxy;
        
        //Due to the combination of Unis with PmiAlpha the usual if(b==0d || c==0d) test is not enough, as values can also become negative in the combined verions
        if(b>0d && c>0d && d>0d){
            
            return lb(a*d/(b*c))-3.29d*Math.sqrt(1/a+1/b+1/c+1/d);
            
        }else{
        
            return 0d;
            
        }
        
    }
    
    
    
    
    /**
     * This method computes the T combined cost weight of a (word, feature) pair based on the frequencies given as input parameters.
     * @param fxy the frequency with which word x and feature y co-occur
     * @param fx the frequency of word x
     * @param fy the frequency of feature y
     * @param N  the count of all the relations of the given type
     * @return the T combined cost weight
     */
    public static double tCombCost(double fxy, double fx, double fy, double N){
        
        double a = fxy;
        double b = fx-a;
        double c = fy-a;
        
        if(b==0d || c==0d){
            
            return 0d;
            
        }else{
        
            double U = lb(1d+(Math.min(b,c)+a)/(Math.max(b,c)+a));
            double S = Math.pow(lb(1d+(Math.min(b,c))/(a+1)), -0.5d);
            double R = lb(1d+a/(a+b))*lb(1+a/(a+c));

            return Math.sqrt(U*S*R);
            
        }
        
    }
    
    
    
    
    /**
     * This method computes the phi weight of a (word, feature) pair based on the frequencies given as input parameters.
     * @param fxy the frequency with which word x and feature y co-occur
     * @param fx the frequency of word x
     * @param fy the frequency of feature y
     * @param N  the count of all the relations of the given type
     * @return the phi weight
     */
    public static double phi(double fxy, double fx, double fy, double N){
        
        double pxy = fxy/N;
        double px = fx/N;
        double py = fy/N;
        
        return (pxy-px*py)/Math.sqrt(px*py*(1-px)*(1-py));
        
    }
    
    
    
    
    /**
     * This method computes the Kappa weight of a (word, feature) pair based on the frequencies given as input parameters.
     * @param fxy the frequency with which word x and feature y co-occur
     * @param fx the frequency of word x
     * @param fy the frequency of feature y
     * @param N  the count of all the relations of the given type
     * @return the Kappa weight
     */
    public static double kappa(double fxy, double fx, double fy, double N){
        
        double pxy = fxy/N;
        double p_x_y = (N-fx-fy+fxy)/N;
        double px = fx/N;
        double p_x = (N-fx)/N;
        double py = fy/N;
        double p_y = (N-fy)/N;
        
        return (pxy+p_x_y-px*py-p_x*p_y)/(1-px*py-p_x*p_y);
        
    }
    
    
    
    
    /**
     * This method computes the J measure weight of a (word, feature) pair based on the frequencies given as input parameters.
     * @param fxy the frequency with which word x and feature y co-occur
     * @param fx the frequency of word x
     * @param fy the frequency of feature y
     * @param N  the count of all the relations of the given type
     * @return the J measure weight
     */
    public static double jMeasure(double fxy, double fx, double fy, double N){
        
        double pxy = fxy/N;
        double px_y = (fx-fxy)/N;
        double p_xy = (fy-fxy)/N;
        double px = fx/N;
        double p_x = (N-fx)/N;
        double py = fy/N;
        double p_y = (N-fy)/N;
        double pxCondy = fxy/fy;
        double pyCondx = fxy/fx;
        double p_yCondx = px_y/px;
        double p_xCondy = p_xy/py;
        
        if(px_y==0d || p_xy==0d){
            
            return 0d;
            
        }else{
        
            return Math.max(pxy*lb(pyCondx/py)+px_y*lb(p_yCondx/p_y), pxy*lb(pxCondy/px)+p_xy*lb(p_xCondy/p_x));
            
        }
        
    }
    
    
    
    
    /**
     * This method computes the Gini index weight of a (word, feature) pair based on the frequencies given as input parameters.
     * @param fxy the frequency with which word x and feature y co-occur
     * @param fx the frequency of word x
     * @param fy the frequency of feature y
     * @param N  the count of all the relations of the given type
     * @return the Gini index weight
     */
    public static double gini(double fxy, double fx, double fy, double N){
        
        double px_y = (fx-fxy)/N;
        double p_xy = (fy-fxy)/N;
        double p_x_y = (N-fx-fy+fxy)/N;
        double px = fx/N;
        double p_x = (N-fx)/N;
        double py = fy/N;
        double p_y = (N-fy)/N;
        double pxCondy = fxy/fy;
        double pxCond_y = px_y/p_y;
        double p_xCondy = p_xy/py;
        double p_xCond_y = p_x_y/p_y;
        double pyCondx = fxy/fx;
        double pyCond_x = p_xy/p_x;
        double p_yCondx = px_y/px;
        double p_yCond_x = p_x_y/p_x;
        
        return Math.max(px*(Math.pow(pyCondx,2d)+Math.pow(p_yCondx,2d))-Math.pow(py,2d) + p_x*(Math.pow(pyCond_x,2d)+Math.pow(p_yCond_x,2d))-Math.pow(p_y,2d),
                py*(Math.pow(pxCondy,2d)+Math.pow(p_xCondy,2d))-Math.pow(px,2d) + p_y*(Math.pow(pxCond_y,2d)+Math.pow(p_xCond_y,2d))-Math.pow(p_x,2d));
        
    }
    
    
    
    
    /**
     * This method computes the confidence weight of a (word, feature) pair based on the frequencies given as input parameters.
     * @param fxy the frequency with which word x and feature y co-occur
     * @param fx the frequency of word x
     * @param fy the frequency of feature y
     * @param N  the count of all the relations of the given type
     * @return the confidence weight
     */
    public static double confidence(double fxy, double fx, double fy, double N){
        
        double pxCondy = fxy/fy;
        double pyCondx = fxy/fx;
        
        return Math.max(pxCondy, pyCondx);
        
    }
    
    
    
    
    /**
     * This method computes the Laplace weight of a (word, feature) pair based on the frequencies given as input parameters.
     * @param fxy the frequency with which word x and feature y co-occur
     * @param fx the frequency of word x
     * @param fy the frequency of feature y
     * @param N  the count of all the relations of the given type
     * @return the Laplace weight
     */
    public static double laplace(double fxy, double fx, double fy, double N){
        
        double pxy = fxy/N;
        double px = fx/N;
        double py = fy/N;
        
        return Math.max((N*pxy+1)/(N*px+2d), (N*pxy+1)/(N*py+2d));
        
    }
    
    
    
    
    /**
     * This method computes the Piatersky-Shapiro weight of a (word, feature) pair based on the frequencies given as input parameters.
     * @param fxy the frequency with which word x and feature y co-occur
     * @param fx the frequency of word x
     * @param fy the frequency of feature y
     * @param N  the count of all the relations of the given type
     * @return the Piatersky-Shapiro weight
     */
    public static double piaterskyShapiro(double fxy, double fx, double fy, double N){
        
        double pxy = fxy/N;
        double px = fx/N;
        double py = fy/N;
        
        return pxy-px*py;
        
    }
    
    
    
    
    /**
     * This method computes the Certainty factor weight of a (word, feature) pair based on the frequencies given as input parameters.
     * @param fxy the frequency with which word x and feature y co-occur
     * @param fx the frequency of word x
     * @param fy the frequency of feature y
     * @param N  the count of all the relations of the given type
     * @return the Certainty factor weight
     */
    public static double certainty(double fxy, double fx, double fy, double N){
        
        double px = fx/N;
        double py = fy/N;
        double pxCondy = fxy/fy;
        double pyCondx = fxy/fx;
        
        return Math.max((pyCondx-py)/(1-py), (pxCondy-px)/(1-px));
        
    }
    
    
    
    
    /**
     * This method computes the Added value weight of a (word, feature) pair based on the frequencies given as input parameters.
     * @param fxy the frequency with which word x and feature y co-occur
     * @param fx the frequency of word x
     * @param fy the frequency of feature y
     * @param N  the count of all the relations of the given type
     * @return the Added value weight
     */
    public static double addedValue(double fxy, double fx, double fy, double N){
        
        double px = fx/N;
        double py = fy/N;
        double pxCondy = fxy/fy;
        double pyCondx = fxy/fx;
        
        return Math.max(pyCondx-py, pxCondy-px);
        
    }
    
    
    
    
    
    
    /**
     * This method computes the t-test weight of a (word, feature) pair based on the frequencies given as input parameters.
     * @param fxy the frequency with which word x and feature y co-occur
     * @param fx the frequency of word x
     * @param fy the frequency of feature y
     * @param N  the count of all the relations of the given type
     * @return the mutual information
     */
    public static double tTest1(double fxy, double fx, double fy, double N){
        
        double pxy = fxy/N;
        
        return (pxy-((fx/N)*(fy/N)))/Math.sqrt(pxy/N);
        
    }
    
        
    
    /**
     * This method computes the t-test weight of a (word, feature) pair based on the frequencies given as input parameters.
     * @param fxy the frequency with which word x and feature y co-occur
     * @param fx the frequency of word x
     * @param fy the frequency of feature y
     * @param N  the count of all the relations of the given type
     * @return the mutual information
     */
    public static double tTest2(double fxy, double fx, double fy, double N){
        
        double px = fx/N;
        double py = fy/N;
        
        return (fxy/N-(px*py))/Math.sqrt(px*py);
        
    }
    
    
    
    /**
     * This method computes the t-test weight of a (word, feature) pair based on the frequencies given as input parameters.
     * @param fxy the frequency with which word x and feature y co-occur
     * @param fx the frequency of word x
     * @param fy the frequency of feature y
     * @param N  the count of all the relations of the given type
     * @return the mutual information
     */
    public static double tTest3(double fxy, double fx, double fy, double N){
        
        return (fxy-(fx*fy/N))/Math.sqrt(fxy*(1-(fxy/N)));
        
    }
    
    
    
    /**
     * This method computes the z-test weight of a (word, feature) pair based on the frequencies given as input parameters.
     * @param fxy the frequency with which word x and feature y co-occur
     * @param fx the frequency of word x
     * @param fy the frequency of feature y
     * @param N  the count of all the relations of the given type
     * @return the mutual information
     */
    public static double zTest1(double fxy, double fx, double fy, double N){
        
        double px = fx/N;
        double py = fy/N;
        
        return (fxy/N-(px*py))/Math.sqrt(px*py/N);
        
    }
    
    
    
    /**
     * This method computes the z-test weight of a (word, feature) pair based on the frequencies given as input parameters.
     * @param fxy the frequency with which word x and feature y co-occur
     * @param fx the frequency of word x
     * @param fy the frequency of feature y
     * @param N  the count of all the relations of the given type
     * @return the mutual information
     */
    public static double zTest2(double fxy, double fx, double fy, double N){
        
        double E11 = fx*fy/N;
        
        return (fxy-E11)/Math.sqrt(E11);
        
    }
    
    
    
    /**
     * This method computes the z-test weight of a (word, feature) pair based on the frequencies given as input parameters.
     * @param fxy the frequency with which word x and feature y co-occur
     * @param fx the frequency of word x
     * @param fy the frequency of feature y
     * @param N  the count of all the relations of the given type
     * @return the mutual information
     */
    public static double zTest3(double fxy, double fx, double fy, double N){
        
        double E11 = fx*fy/N;
        
        return (fxy-E11)/Math.sqrt(E11*(1-(E11/N)));
        
    }
    
    
    
    /**
     * This method computes the simple log likelihood weight of a (word, feature) pair based on the frequencies given as input parameters.
     * @param fxy the frequency with which word x and feature y co-occur
     * @param fx the frequency of word x
     * @param fy the frequency of feature y
     * @param N  the count of all the relations of the given type
     * @return the mutual information
     */
    public static double simpleLL(double fxy, double fx, double fy, double N){
        
        double E11 = fx*fy/N;
        
        return 2*(fxy*Math.log(fxy/E11)-(fxy-E11));
        
    }
    
    
    
    
    /**
     * This method computes the Additive log-likelihood ratio based (allr) weight of a (word, feature) pair based on the frequencies given as input parameters.
     * @param fxy the frequency with which word x and feature y co-occur
     * @param fx the frequency of word x
     * @param fy the frequency of feature y
     * @param N  the count of all the relations of the given type
     * @return the mutual information
     */
    public static double allr(double fxy, double fx, double fy, double N){
        
        return -2d*(logLFunctionInsideLogLikelihood(fxy,fx,fy/N)-logLFunctionInsideLogLikelihood(fxy,fx,fxy/fx));
        
    }
    
    
    
        
    
    /**
     * This method computes the chi-square weight of a (word, feature) pair based on the frequencies given as input parameters.
     * @param fxy the frequency with which word x and feature y co-occur
     * @param fx the frequency of word x
     * @param fy the frequency of feature y
     * @param N  the count of all the relations of the given type
     * @return the chi-square weight
     */
    public static double chiSquare(double fxy, double fx, double fy, double N){
        
        double O11 = fxy;
        double O12 = fx-O11;
        double O21 = fy-O11;
        double O22 = N-fx-fy+fxy;
        
        return N*Math.pow(O11*O22-O12*O21, 2d)/((O11+O12)*(O11+O21)*(O12+O22)*(O21+O22));
        
    }
    
    
    
    
    /**
     * This method computes the chi-square with Yates' continuity correction weight of a (word, feature) pair based on the frequencies given as input parameters.
     * @param fxy the frequency with which word x and feature y co-occur
     * @param fx the frequency of word x
     * @param fy the frequency of feature y
     * @param N  the count of all the relations of the given type
     * @return the chi-square with Yates' continuity correction weight
     */
    public static double chiSquareWYCC(double fxy, double fx, double fy, double N){
        
        double O11 = fxy;
        double O12 = fx-O11;
        double O21 = fy-O11;
        double O22 = N-fx-fy+fxy;
        
        return N*Math.pow(Math.abs(O11*O22-O12*O21)-(N/2d), 2d)/((O11+O12)*(O11+O21)*(O12+O22)*(O21+O22));
        
    }
    
    
    
    
    /**
     * This method computes the Okapi BM25 weight of a (word, feature) pair.
     * @param fij the frequency with which word i and feature j co-occur
     * @param vectorElementSum the element sum of the current word vector
     * @param avgWordVectorElementSum the average word vector element sum of a given POS
     * @param nj the type frequency of feature j
     * @param N  the count of all the relations of the given type
     * @return the Okapi BM25 weight
     */
    public static double okapi1(double fij, double vectorElementSum, double avgWordVectorElementSum, double nj, double N){
        
        return fij/(0.5d+1.5d*vectorElementSum/avgWordVectorElementSum+fij)*lb((N-nj+0.5d)/(fij+0.5d));
        
    }
    
    
    
    
    /**
     * This method computes the Okapi BM25 weight of a (word, feature) pair.
     * @param fij the frequency with which word i and feature j co-occur
     * @param vectorElementSum the element sum of the current word vector
     * @param avgWordVectorElementSum the average word vector element sum of a given POS
     * @param nj the type frequency of feature j
     * @param N  the count of all the relations of the given type
     * @return the Okapi BM25 weight
     */
    public static double okapi2(double fij, double vectorElementSum, double avgWordVectorElementSum, double nj, double N){
        
        return 3d*fij/(0.5d+1.5d*vectorElementSum/avgWordVectorElementSum+fij)*lb((N-nj+0.5d)/(nj+0.5d));
        
    }
    
    
    
    
    /**
     * This method computes the Okapi BM25 weight of a (word, feature) pair.
     * @param fij the frequency with which word i and feature j co-occur
     * @param vectorElementSum the element sum of the current word vector
     * @param avgWordVectorElementSum the average word vector element sum of a given POS
     * @param nj the type frequency of feature j
     * @param N  the count of all the relations of the given type
     * @return the Okapi BM25 weight
     */
    public static double okapi3(double fij, double vectorElementSum, double avgWordVectorElementSum, double nj, double N){
        
        return 2.2d*fij/(1.2d*((1d-0.95d)+0.95d*vectorElementSum/avgWordVectorElementSum)+fij)*lb((N-nj+0.5d)/(nj+0.5d));
        
    }
    
    
    
    /**
     * This method computes the LTU weight of a (word, feature) pair.
     * @param fij the frequency with which word i and feature j co-occur
     * @param vectorElementSum the element sum of the current word vector
     * @param avgWordVectorElementSum the average word vector element sum of a given POS
     * @param nj the type frequency of feature j
     * @param N  the count of all the relations of the given type
     * @return the LTU weight
     */
    public static double ltu(double fij, double vectorElementSum, double avgWordVectorElementSum, double nj, double N){
        
        return ((lb(fij)+1d)*lb(N/nj))/(0.8d+0.2d*vectorElementSum/avgWordVectorElementSum);
        
    }
    
    
    
    /**
     * This method computes the relative risk weight of a (word, feature) pair based on the frequencies given as input parameters.
     * @param fxy the frequency with which word x and feature y co-occur
     * @param fx the frequency of word x
     * @param fy the frequency of feature y
     * @param N  the count of all the relations of the given type
     * @return the relative risk
     */
    public static double relRisk(double fxy, double fx, double fy, double N){
        
        if(fx==fxy){
            
            return 0d;
            
        }else{
        
            return fxy*(N-fy)/((fx-fxy)*fy);
            
        }
        
    }
    
    
    
    
    /**
     * This method computes the Liddell weight of a (word, feature) pair based on the frequencies given as input parameters.
     * @param fxy the frequency with which word x and feature y co-occur
     * @param fx the frequency of word x
     * @param fy the frequency of feature y
     * @param N  the count of all the relations of the given type
     * @return the Liddell weight
     */
    public static double liddell(double fxy, double fx, double fy, double N){

        return (fxy*(N-fx-fy+fxy)-(fx-fxy)*(fy-fxy))/(fy*(N-fy));
        
    }
    

    
    
    /**
     * This method computes the Jaccard weight of a (word, feature) pair based on the frequencies given as input parameters.
     * @param fxy the frequency with which word x and feature y co-occur
     * @param fx the frequency of word x
     * @param fy the frequency of feature y
     * @param N  the count of all the relations of the given type
     * @return the Jaccard weight
     */
    public static double jaccard(double fxy, double fx, double fy, double N){

        return fxy/(fx+fy-fxy);
        
    }
    
    
    
    
    /**
     * This method computes the geometric mean weight of a (word, feature) pair based on the frequencies given as input parameters.
     * @param fxy the frequency with which word x and feature y co-occur
     * @param fx the frequency of word x
     * @param fy the frequency of feature y
     * @param N  the count of all the relations of the given type
     * @return the geometric mean weight
     */
    public static double gMean(double fxy, double fx, double fy, double N){

        return fxy/Math.sqrt(fx*fy);
        
    }
    
    
    
    
    /**
     * This method computes the minimum sensitivity weight of a (word, feature) pair based on the frequencies given as input parameters.
     * @param fxy the frequency with which word x and feature y co-occur
     * @param fx the frequency of word x
     * @param fy the frequency of feature y
     * @param N  the count of all the relations of the given type
     * @return the minimum sensitivity weight
     */
    public static double minSens(double fxy, double fx, double fy, double N){

        return Math.min(fxy/fx,fxy/fy);
        
    }
    
    
    
    
    /**
     * This method computes the odds-ratio1 weight of a (word, feature) pair based on the frequencies given as input parameters.
     * @param fxy the frequency with which word x and feature y co-occur
     * @param fx the frequency of word x
     * @param fy the frequency of feature y
     * @param N  the count of all the relations of the given type
     * @return the odds-ratio1 weight
     */
    public static double oddsRatio1(double fxy, double fx, double fy, double N){
        
        double d = (fx-fxy)*(fy-fxy);

        if(d==0d){
            
            return 0d;
            
        }else{
        
            return fxy*(N-fx-fy+fxy)/d;
            
        }
        
    }
    
    
    
    
    /**
     * This method computes the odds-ratio2 weight of a (word, feature) pair based on the frequencies given as input parameters.
     * @param fxy the frequency with which word x and feature y co-occur
     * @param fx the frequency of word x
     * @param fy the frequency of feature y
     * @param N  the count of all the relations of the given type
     * @return the odds-ratio weight
     */
    public static double oddsRatio2(double fxy, double fx, double fy, double N){

        return (fxy+0.5d)*(N-fx-fy+fxy+0.5d)/((fx-fxy+0.5d)*(fy-fxy+0.5d));
        
    }
    
    
    
    
    
    
    /**
     * This method computes the min(Pmi,T-test1) weight of a (word, feature) pair based on the frequencies given as input parameters.
     * @param fxy the frequency with which word x and feature y co-occur
     * @param fx the frequency of word x
     * @param fy the frequency of feature y
     * @param N  the count of all the relations of the given type
     * @return the min(Pmi,T-test1) weight
     */
    public static double minPmiTTest1(double fxy, double fx, double fy, double N){
        
        return Math.min(mutualInformation(fxy, fx, fy, N), tTest1(fxy, fx, fy, N));
        
    }
    
    
    
    
    /**
     * This method computes the min(Pmi,T-test2) weight of a (word, feature) pair based on the frequencies given as input parameters.
     * @param fxy the frequency with which word x and feature y co-occur
     * @param fx the frequency of word x
     * @param fy the frequency of feature y
     * @param N  the count of all the relations of the given type
     * @return the min(Pmi,T-test2) weight
     */
    public static double minPmiTTest2(double fxy, double fx, double fy, double N){
        
        return Math.min(mutualInformation(fxy, fx, fy, N), tTest2(fxy, fx, fy, N));
        
    }
    
    
    
        
    
    /**
     * This method computes the Krippendorff's alpha weight of a (word, feature) pair based on the frequencies given as input parameters.
     * @param fxy the frequency with which word x and feature y co-occur
     * @param fx the frequency of word x
     * @param fy the frequency of feature y
     * @param N  the count of all the relations of the given type
     * @return the Krippendorff's alpha weight
     */
    public static double krippendorffAlpha(double fxy, double fx, double fy, double N){
        
        double O11 = fxy;
        double O12 = fx-O11;
        double O21 = fy-O11;
        double O22 = N-fx-fy+fxy;
        
        double O11mod = 2*O11;
        double O12mod = O12+O21;
        double O21mod = O12+O21;
        double O22mod = 2*O22;
        double Nmod = 2*N;
        
        return 1-((Nmod-1)*O12mod/((O11mod+O12mod)*(O21mod+O22mod)));
        
    }
    
    
    
        
    
    /**
     * This method computes the Scott's Pi weight of a (word, feature) pair based on the frequencies given as input parameters.
     * @param fxy the frequency with which word x and feature y co-occur
     * @param fx the frequency of word x
     * @param fy the frequency of feature y
     * @param N  the count of all the relations of the given type
     * @return the Scott's Pi weight
     */
    public static double scottPi(double fxy, double fx, double fy, double N){
        
        double O11 = fxy;
        double O12 = fx-O11;
        double O21 = fy-O11;
        double O22 = N-fx-fy+fxy;
        
        double Po = (O11+O22)/N;
        double Pe = Math.pow((O11+O12+O11+O21)/(2*N), 2d)+Math.pow((O21+O22+O12+O22)/(2*N), 2d);
        
        return (Po-Pe)/(1-Pe);
        
    }
    
    
    
        
    
    /**
     * This method computes the Cohen's Kappa weight of a (word, feature) pair based on the frequencies given as input parameters.
     * @param fxy the frequency with which word x and feature y co-occur
     * @param fx the frequency of word x
     * @param fy the frequency of feature y
     * @param N  the count of all the relations of the given type
     * @return the Cohen's kappa weight
     */
    public static double cohenKappa(double fxy, double fx, double fy, double N){
        
        double O11 = fxy;
        double O12 = fx-O11;
        double O21 = fy-O11;
        double O22 = N-fx-fy+fxy;
        
        double Po = (O11+O22)/N;
        double Pe = (((O11+O12)*(O11+O21))+((O21+O22)*(O12+O22)))/Math.pow(N, 2d);
        
        return (Po-Pe)/(1-Pe);
        
    }
    
    
    
    /**
     * This method computes the NgdMOD weight of a (word, feature) pair based on the frequencies given as input parameters.
     * @param fxy the frequency with which word x and feature y co-occur
     * @param fx the frequency of word x
     * @param fy the frequency of feature y
     * @param N  the count of all the relations of the given type
     * @return the NgdMOD weight
     */
    public static double ngd(double fxy, double fx, double fy, double N){
        
        return (Math.max(lb(1d+fx),lb(1d+fy))-lb(1d+fxy))/(lb(1d+N)-Math.min(lb(1d+fx),lb(1d+fy)));
        
    }
    
    
    
    
    
    
    /**
     * This method computes the information content of features as defined in "Lin, 1998, An information-theoretic definition of similarity" for all types of features.
     * This is used for the method based on Lin and the numerical method with PRODOFLOGCOUNTANDLOGFEATINF weighting.
     */
    public static void computeInformationForFeatures(){

        computeInformationForFeatures(verbObjectTuples, verbObjectInformation, allNounTypeCount);
        computeInformationForFeatures(verbSubjectTuples, verbSubjectInformation, allNounTypeCount);
        computeInformationForFeatures(ncmodNounTuples, ncmodNounInformation, allNounTypeCount);
        computeInformationForFeatures(dobjVerbTuples, dobjVerbInformation, allVerbTypeCount);
        computeInformationForFeatures(ncsubjVerbTuples, ncsubjVerbInformation, allVerbTypeCount);
        computeInformationForFeatures(prepVerbTuples, prepVerbInformation, allVerbTypeCount);
        computeInformationForFeatures(obj2VerbTuples, obj2VerbInformation, allVerbTypeCount);
        computeInformationForFeatures(nounAdjTuples, nounAdjInformation, allAdjectiveTypeCount);
        computeInformationForFeatures(wordAdvTuples, wordAdvInformation, allAdverbTypeCount);

    }
    
    
    /**
     * This function calculates the information content of features stored in @param tuplesMap.
     * @param <T> the type of keys in @param tuplesMap and @param informationMap
     * @param tuplesMap the map containing the features
     * @param informationMap that map that will contain the information content of the features
     * @param allCount the count of all the word types of the given POS
     */
    public static <T> void computeInformationForFeatures(HashMap<T, Long> tuplesMap, HashMap<T, Double> informationMap, long allCount){
        
        for(Map.Entry<T, Long> wordEntry: tuplesMap.entrySet()){
            double d = -Math.log((double)wordEntry.getValue()/allCount);
            if(d<0d){
                System.out.println(wordEntry.getKey() + " " + d + " " + wordEntry.getValue() + " " + allCount);
                System.exit(1);
            }
            informationMap.put(wordEntry.getKey(), d);
        }
        
    }
    
    
    
    
    /**
     * This method computes the entropy of features as defined in "Rapp, 2003, Word Sense Discovery Based on Sense Descriptor Dissimilarity" for all types of features.
     */
    public static void computeEntropyForFeatures(){

        computeEntropyForFeatures(objVerbTuples, verbWithObjectFrequencies, verbObjectEntropy);
        computeEntropyForFeatures(subjVerbTuples, verbWithSubjectFrequencies, verbSubjectEntropy);
        computeEntropyForFeatures(nounNcmodTuples, ncmodWithNounFrequencies, ncmodNounEntropy);
        computeEntropyForFeatures(verbDobjTuples, dobjWithVerbFrequencies, dobjVerbEntropy);
        computeEntropyForFeatures(verbNcsubjTuples, ncsubjWithVerbFrequencies, ncsubjVerbEntropy);
        computeEntropyForFeatures(verbPrepTuples, prepWithVerbFrequencies, prepVerbEntropy);
        computeEntropyForFeatures(verbObj2Tuples, obj2WithVerbFrequencies, obj2VerbEntropy);
        computeEntropyForFeatures(adjNounTuples, nounWithAdjFrequencies, nounAdjEntropy);
        computeEntropyForFeatures(advWordTuples, wordWithAdvFrequencies, wordAdvEntropy);
        
    }
    
    
    /**
     * This method computes the entropy of features as defined in "Rapp, 2003, Word Sense Discovery Based on Sense Descriptor Dissimilarity" for a given type of features.
     * @param <T> the type of keys in @param tuplesMap
     * @param <V> the type of keys in @param frequency2Map and @param EntropyMap
     * @param tuplesMap the map containing the (word, feature) pairs
     * @param frequency2Map the map containing the frequency of features
     * @param entropyMap that map that will contain entropy of the features
     */
    public static <T, V> void computeEntropyForFeatures(HashMap<T, HashMap<V, Double>> tuplesMap, HashMap<V, Double> frequency2Map, HashMap<V, Double> entropyMap){
        
        Double numerator;
        double p;
        
        for(Map.Entry<V, Double> word2Entry: frequency2Map.entrySet()){
            double sum = 0d;
            V entry2Key = word2Entry.getKey();
            double denominator = word2Entry.getValue();
            for (Map.Entry<T, HashMap<V, Double>> word1Entry: tuplesMap.entrySet()){
                numerator = word1Entry.getValue().get(entry2Key);
                if(numerator!=null){
                    p=numerator/denominator;
                    sum-=p*lb(p);
                }
            }
            entropyMap.put(entry2Key, sum);
        }
        
    }
    
    
    
    
    
    /**
     * This method computes Grefenstette's feature weight of features as defined in "Curran, 2004, From distributional to semantic similarity" for all types of features.
     */
    public static void computeGrefFeatureWeightForFeatures(){

        computeGrefFeatureWeightForFeatures(objVerbTuples, verbWithObjectFrequencies, verbObjectGrefFeatureWeight);
        computeGrefFeatureWeightForFeatures(subjVerbTuples, verbWithSubjectFrequencies, verbSubjectGrefFeatureWeight);
        computeGrefFeatureWeightForFeatures(nounNcmodTuples, ncmodWithNounFrequencies, ncmodNounGrefFeatureWeight);
        computeGrefFeatureWeightForFeatures(verbDobjTuples, dobjWithVerbFrequencies, dobjVerbGrefFeatureWeight);
        computeGrefFeatureWeightForFeatures(verbNcsubjTuples, ncsubjWithVerbFrequencies, ncsubjVerbGrefFeatureWeight);
        computeGrefFeatureWeightForFeatures(verbPrepTuples, prepWithVerbFrequencies, prepVerbGrefFeatureWeight);
        computeGrefFeatureWeightForFeatures(verbObj2Tuples, obj2WithVerbFrequencies, obj2VerbGrefFeatureWeight);
        computeGrefFeatureWeightForFeatures(adjNounTuples, nounWithAdjFrequencies, nounAdjGrefFeatureWeight);
        computeGrefFeatureWeightForFeatures(advWordTuples, wordWithAdvFrequencies, wordAdvGrefFeatureWeight);
        
    }
    
    
    /**
     * This function calculates Grefenstette's feature weight of features stored in @param frequency2Map.
     * @param <T> the type of keys in @param tuplesMap
     * @param <V> the type of keys in @param frequency2Map and @param GrefFeatureWeightMap
     * @param tuplesMap the map containing the (word, feature) pairs
     * @param frequency2Map the map containing the frequency of features
     * @param grefFeatureWeightMap that map that will contain Grefenstette's feature weight of the features
     */
    public static <T, V> void computeGrefFeatureWeightForFeatures(HashMap<T, HashMap<V, Double>> tuplesMap, HashMap<V, Double> frequency2Map, HashMap<V, Double> grefFeatureWeightMap){
        
        Double numerator;
        double p;
        
        for(Map.Entry<V, Double> word2Entry: frequency2Map.entrySet()){
            double sum = 0d;
            V entry2Key = word2Entry.getKey();
            double denominator = word2Entry.getValue();
            for (Map.Entry<T, HashMap<V, Double>> word1Entry: tuplesMap.entrySet()){
                numerator = word1Entry.getValue().get(entry2Key);
                if(numerator!=null){
                    p=numerator/denominator;
                    sum+=p*lb(p);
                }
            }
            grefFeatureWeightMap.put(entry2Key, sum+1d);
        }
        
    }
    
    
    
    
    /**
     * This method computes the ATC feature weight of features for all types of features.
     */
    public static void computeAtcFeatureWeightForFeatures(){

        computeAtcFeatureWeightForFeatures(objVerbTuples, verbObjectTuples, allObjectCount, objVerbMaxFrequency, verbObjectATCFeatureWeight);
        computeAtcFeatureWeightForFeatures(subjVerbTuples, verbSubjectTuples, allSubjectCount, subjVerbMaxFrequency, verbSubjectATCFeatureWeight);
        computeAtcFeatureWeightForFeatures(nounNcmodTuples, ncmodNounTuples, allNounNcmodCount, nounNcmodMaxFrequency, ncmodNounATCFeatureWeight);
        computeAtcFeatureWeightForFeatures(verbDobjTuples, dobjVerbTuples, allVerbDobjCount, verbDobjMaxFrequency, dobjVerbATCFeatureWeight);
        computeAtcFeatureWeightForFeatures(verbNcsubjTuples, ncsubjVerbTuples, allVerbNcsubjCount, verbNcsubjMaxFrequency, ncsubjVerbATCFeatureWeight);
        computeAtcFeatureWeightForFeatures(verbPrepTuples, prepVerbTuples, allVerbPrepCount, verbPrepMaxFrequency, prepVerbATCFeatureWeight);
        computeAtcFeatureWeightForFeatures(verbObj2Tuples, obj2VerbTuples, allVerbObj2Count, verbObj2MaxFrequency, obj2VerbATCFeatureWeight);
        computeAtcFeatureWeightForFeatures(adjNounTuples, nounAdjTuples, allAdjNounCount, adjNounMaxFrequency, nounAdjATCFeatureWeight);
        computeAtcFeatureWeightForFeatures(advWordTuples, wordAdvTuples, allAdvWordCount, advWordMaxFrequency, wordAdvATCFeatureWeight);
        
    }
    
    
    /**
     * This function calculates ATC feature weight of features stored in @param frequency2Map.
     * @param <T> the type of keys in @param tuplesMap
     * @param <V> the type of keys in @param tuplesMap2 and @param ATCFeatureWeightMap
     * @param tuplesMap the map containing the (word, feature) pairs
     * @param tuplesMap2 the map containing the number of words with which the features occur
     * @param allCount the count of all the relations of the given type
     * @param maxWordFeaturePairFrequency the maximum word-feature pair frequency
     * @param atcFeatureWeightMap that map that will contain ATC feature weight of the features
     */
    public static <T, V> void computeAtcFeatureWeightForFeatures(HashMap<T, HashMap<V, Double>> tuplesMap, HashMap<V, Long> tuplesMap2, double allCount, Double maxWordFeaturePairFrequency, HashMap<V, Double> atcFeatureWeightMap){
        
        Double fij;
        
        for(Map.Entry<V, Long> word2Entry: tuplesMap2.entrySet()){
            double sum = 0d;
            V entry2Key = word2Entry.getKey();
            Long entry2Value = word2Entry.getValue();
            for (Map.Entry<T, HashMap<V, Double>> word1Entry: tuplesMap.entrySet()){
                fij = word1Entry.getValue().get(entry2Key);
                if(fij==null){
                    fij=0d;
                }
                sum+=Math.pow((0.5d+0.5d*fij/maxWordFeaturePairFrequency)*lb(allCount/entry2Value),2d);
            }
            atcFeatureWeightMap.put(entry2Key, Math.sqrt(sum));
        }
        
    }
    
    
    
    
    /**
     * This method computes the average word vector length.
     */
    public static void computeAvgWordVectorElementSums(){
        
        double count = 0d;

        for(Map.Entry<String, Double> wordEntry: nounVectorElementSums.entrySet()){
            count+=wordEntry.getValue();
        }
        
        avgNounVectorElementSum = count/nounVectorElementSums.size();
        
        count = 0d;
        
        for(Map.Entry<String, Double> wordEntry: verbVectorElementSums.entrySet()){
            count+=wordEntry.getValue();
        }
        
        avgVerbVectorElementSum = count/verbVectorElementSums.size();
        
        count = 0d;
        
        for(Map.Entry<String, Double> wordEntry: adjectiveVectorElementSums.entrySet()){
            count+=wordEntry.getValue();
        }
        
        avgAdjectiveVectorElementSum = count/adjectiveVectorElementSums.size();
        
        count = 0d;
        
        for(Map.Entry<String, Double> wordEntry: adverbVectorElementSums.entrySet()){
            count+=wordEntry.getValue();
        }
        
        avgAdverbVectorElementSum = count/adverbVectorElementSums.size();
        
    }
    
    
    
    
    
    
    /**
     * This method computes the maximum word-feature pair frequencies for all types of features.
     */
    public static void computeMaxWordFeaturePairFrequencies(){

        objVerbMaxFrequency = computeMaxWordFeaturePairFrequencies(objVerbTuples);
        subjVerbMaxFrequency = computeMaxWordFeaturePairFrequencies(subjVerbTuples);
        nounNcmodMaxFrequency = computeMaxWordFeaturePairFrequencies(nounNcmodTuples);
        verbDobjMaxFrequency = computeMaxWordFeaturePairFrequencies(verbDobjTuples);
        verbNcsubjMaxFrequency = computeMaxWordFeaturePairFrequencies(verbNcsubjTuples);
        verbPrepMaxFrequency = computeMaxWordFeaturePairFrequencies(verbPrepTuples);
        verbObj2MaxFrequency = computeMaxWordFeaturePairFrequencies(verbObj2Tuples);
        adjNounMaxFrequency = computeMaxWordFeaturePairFrequencies(adjNounTuples);
        advWordMaxFrequency = computeMaxWordFeaturePairFrequencies(advWordTuples);
                
    }
    
    
    /**
     * This function calculates the maximum word-feature pair frequency in @param tuplesMap.
     * @param <T> the type of keys in @param tuplesMap
     * @param <V> the type of values in @param tuplesMap
     * @param tuplesMap the map containing the (word, feature) pairs
     * @return the maximum word-feature pair frequency
     */
    public static <T, V> Double computeMaxWordFeaturePairFrequencies(HashMap<T, HashMap<V, Double>> tuplesMap){
        
        double max = 0d;
        double f;
        
        for(Map.Entry<T, HashMap<V, Double>> word1Entry: tuplesMap.entrySet()){
            HashMap<V, Double> word1Table = word1Entry.getValue();
            for (Map.Entry<V, Double> word2Entry: word1Table.entrySet()){
                f = word2Entry.getValue();
                if(f>max){
                    max=f;
                }
            }
        }
        
        return max;
        
    }


    
    
    
    
    /**
     * This method calculates the sum of the feature frequencies rasied to the power pmiAlphaAlpha for all types of features.
     */
    public static void computeAllRelationCountOfGivenTypeAlphas(){

        allObjectCountAlpha = computeAllRelationCountOfGivenTypeAlphas(verbWithObjectFrequencies);
        allSubjectCountAlpha = computeAllRelationCountOfGivenTypeAlphas(verbWithSubjectFrequencies);
        allNounNcmodCountAlpha = computeAllRelationCountOfGivenTypeAlphas(ncmodWithNounFrequencies);
        allVerbDobjCountAlpha = computeAllRelationCountOfGivenTypeAlphas(dobjWithVerbFrequencies);
        allVerbNcsubjCountAlpha = computeAllRelationCountOfGivenTypeAlphas(ncsubjWithVerbFrequencies);
        allVerbPrepCountAlpha = computeAllRelationCountOfGivenTypeAlphas(prepWithVerbFrequencies);
        allVerbObj2CountAlpha = computeAllRelationCountOfGivenTypeAlphas(obj2WithVerbFrequencies);
        allAdjNounCountAlpha = computeAllRelationCountOfGivenTypeAlphas(nounWithAdjFrequencies);
        allAdvWordCountAlpha = computeAllRelationCountOfGivenTypeAlphas(wordWithAdvFrequencies);
                
    }
    
    
    /**
     * This function calculates the sum of the feature frequencies rasied to the power pmiAlphaAlpha in @param frequencyMap.
     * @param <V> the type of keys in @param frequencyMap
     * @param frequencyMap the map containing the features with their frequency
     * @return the sum of the feature frequencies rasied to the power pmiAlphaAlpha in @param frequencyMap
     */
    public static <V> Double computeAllRelationCountOfGivenTypeAlphas(HashMap<V, Double> frequencyMap){
        
        double sum = 0d;
        
        for (Map.Entry<V, Double> wordEntry: frequencyMap.entrySet()){
            sum += Math.pow(wordEntry.getValue(), pmiAlphaAlpha);
        }
        
        return sum;
        
    }
    
    
    
    
    /**
     * This method computes sum of the feature document frequencies for all types of features.
     */
    public static void computeAllFeatureDocFreqCounts(){

        allNounFeatureDocFreqCount = computeAllFeatureDocFreqCounts(verbObjectTuples)+computeAllFeatureDocFreqCounts(verbSubjectTuples)+
                computeAllFeatureDocFreqCounts(ncmodNounTuples);
        allVerbFeatureDocFreqCount = computeAllFeatureDocFreqCounts(dobjVerbTuples)+computeAllFeatureDocFreqCounts(ncsubjVerbTuples)+
                computeAllFeatureDocFreqCounts(prepVerbTuples)+computeAllFeatureDocFreqCounts(obj2VerbTuples);
        allAdjFeatureDocFreqCount = computeAllFeatureDocFreqCounts(nounAdjTuples);
        allAdvFeatureDocFreqCount = computeAllFeatureDocFreqCounts(wordAdvTuples);
                
    }
    
    
    /**
     * This function calculates the sum of the feature document frequencies in @param frequencyMap.
     * @param <V> the type of keys in @param frequencyMap
     * @param frequencyMap the map containing the features with their document frequency
     * @return the sum of the feature document frequencies in @param frequencyMap
     */
    public static <V> long computeAllFeatureDocFreqCounts(HashMap<V, Long> frequencyMap){
        
        long sum = 0;
        
        for (Map.Entry<V, Long> wordEntry: frequencyMap.entrySet()){
            sum += wordEntry.getValue();
        }
        
        return sum;
        
    }
    
    
    
    
}
