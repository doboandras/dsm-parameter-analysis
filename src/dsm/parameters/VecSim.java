package dsm.parameters;

import static dsm.parameters.MiscParam.*;
import static dsm.util.MiscUtil.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import util.ComparablePair;
import util.SimilarityScoreParts;

/**
 *
 * @author Dobó
 */
public class VecSim {
    
    public static enum SimilarityMeasure {Cos, Dice_1, Dice_1Mod, Dice_2, Pears, Spearm, ZklMod, ZklModSym, L05, L1, L2, L3, LInf, Canberra, Lorentzian, 
        Jaccard_1, Jaccard_1Mod, Jaccard_2, Jaccard_3, ASkewMod, ASkewModSym, JensenShannonMod, JensenMod, LinKiela, CorrKiela, Tanimoto_1, Tanimoto_1Mod, Tanimoto_2, 
        HarmMeanMod, FidelityMod, HellingerMod, ChiSquareMod, PsChiSquareMod, ClarkMod, InnerProd, PseudoCos, PseudoCosMod_1, 
        PseudoCosMod_2, PseudoCosMod_3, Covariance, RenyiDivMod2, RenyiDivModInf, StdLike_1, StdLike_2, StdLike_3, StdLike_4, StdLike_5, Lsmq, 
        Rms, ContraHMeanMod, Spline, RobersMod, Mb, Simpson_1, Simpson_2Mod, Kulczynski, SorensenMod, MahalanobisMod, PenroseShape, 
        Multiplicative, MultiplicativeMod1, MultiplicativeMod2, SmoothCos, AdjCos, NormCosMod, SocPmiMod, NormModSocPmiMod, ChenCorr, NcdMod_1, NcdMod_2, NgdMod, Dfvmb, 
        ApSyn, ApSynP, Wo, Rbo, Srsn, Rsssn, Sdsn, Srsmv, Srsm, Overlap, TanejaMod, KumarJohnsonMod, AvgL1LInf, 
        VicWhMod, VicSymChiSqMod1, VicSymChiSqMod2, VicSymChiSqMod3, MaxSymChiSqMod, Lin, 
        PearsMb, PearsAdjCos, PearsPFMod, PearsMbAdjCos, PearsMbPFMod, PearsAdjCosPFMod, PearsMbAdjCosPFMod, 
        MbAdjCos, MbPFMod, MbAdjCosPFMod, AdjCosPFMod, 
        MbAdjCosAm, MbAdjCosGm, MbAdjCosHm, MbAdjCosProd, MbAdjCosLogProd, 
        MbCosAm, MbCosGm, MbCosHm, MbCosProd, MbCosLogProd, 
        CosMod_1_2, CosMod_2_2, CosMod_3_2, CosMod_4_2, CosMod_5_2, CosMod_6_2, CosMod_1_3, CosMod_2_3, CosMod_3_3, CosMod_4_3, CosMod_5_3, CosMod_6_3, 
        CosMod_1_4, CosMod_2_4, CosMod_3_4, CosMod_4_4, CosMod_5_4, CosMod_6_4, CosMod_1_5, CosMod_2_5, CosMod_3_5, CosMod_4_5, CosMod_5_5, CosMod_6_5, 
        CosMod_1_6, CosMod_2_6, CosMod_3_6, CosMod_4_6, CosMod_5_6, CosMod_6_6, 
        MbMod_1_2, MbMod_2_2, MbMod_3_2, MbMod_4_2, MbMod_5_2, MbMod_6_2, 
        MbMod_1_3, MbMod_2_3, MbMod_3_3, MbMod_4_3, MbMod_5_3, MbMod_6_3, 
        MbMod_1_4, MbMod_2_4, MbMod_3_4, MbMod_4_4, MbMod_5_4, MbMod_6_4, 
        MbMod_1_5, MbMod_2_5, MbMod_3_5, MbMod_4_5, MbMod_5_5, MbMod_6_5, 
        MbMod_1_6, MbMod_2_6, MbMod_3_6, MbMod_4_6, MbMod_5_6, MbMod_6_6, 
        AdjCosMod_1_2, AdjCosMod_2_2, AdjCosMod_3_2, AdjCosMod_4_2, AdjCosMod_5_2, AdjCosMod_6_2, 
        AdjCosMod_1_3, AdjCosMod_2_3, AdjCosMod_3_3, AdjCosMod_4_3, AdjCosMod_5_3, AdjCosMod_6_3, 
        AdjCosMod_1_4, AdjCosMod_2_4, AdjCosMod_3_4, AdjCosMod_4_4, AdjCosMod_5_4, AdjCosMod_6_4, 
        AdjCosMod_1_5, AdjCosMod_2_5, AdjCosMod_3_5, AdjCosMod_4_5, AdjCosMod_5_5, AdjCosMod_6_5, 
        AdjCosMod_1_6, AdjCosMod_2_6, AdjCosMod_3_6, AdjCosMod_4_6, AdjCosMod_5_6, AdjCosMod_6_6, 
        PFMod_1_1, 
        PFMod_1_2, PFMod_2_2, PFMod_3_2, PFMod_4_2, PFMod_5_2, PFMod_6_2, PFMod_1_3, PFMod_2_3, PFMod_3_3, PFMod_4_3, PFMod_5_3, PFMod_6_3, 
        PFMod_1_4, PFMod_2_4, PFMod_3_4, PFMod_4_4, PFMod_5_4, PFMod_6_4, PFMod_1_5, PFMod_2_5, PFMod_3_5, PFMod_4_5, PFMod_5_5, PFMod_6_5, 
        PFMod_1_6, PFMod_2_6, PFMod_3_6, PFMod_4_6, PFMod_5_6, PFMod_6_6, 
        PearsMbAdjCosMod_1_2, PearsMbAdjCosMod_2_2, PearsMbAdjCosMod_3_2, PearsMbAdjCosMod_4_2, PearsMbAdjCosMod_5_2, PearsMbAdjCosMod_6_2, 
        PearsMbAdjCosMod_1_3, PearsMbAdjCosMod_2_3, PearsMbAdjCosMod_3_3, PearsMbAdjCosMod_4_3, PearsMbAdjCosMod_5_3, PearsMbAdjCosMod_6_3, 
        PearsMbAdjCosMod_1_4, PearsMbAdjCosMod_2_4, PearsMbAdjCosMod_3_4, PearsMbAdjCosMod_4_4, PearsMbAdjCosMod_5_4, PearsMbAdjCosMod_6_4, 
        PearsMbAdjCosMod_1_5, PearsMbAdjCosMod_2_5, PearsMbAdjCosMod_3_5, PearsMbAdjCosMod_4_5, PearsMbAdjCosMod_5_5, PearsMbAdjCosMod_6_5, 
        PearsMbAdjCosMod_1_6, PearsMbAdjCosMod_2_6, PearsMbAdjCosMod_3_6, PearsMbAdjCosMod_4_6, PearsMbAdjCosMod_5_6, PearsMbAdjCosMod_6_6, 
        PearsMbAdjCosPFMod_1_2, PearsMbAdjCosPFMod_2_2, PearsMbAdjCosPFMod_3_2, PearsMbAdjCosPFMod_4_2, PearsMbAdjCosPFMod_5_2, PearsMbAdjCosPFMod_6_2, 
        PearsMbAdjCosPFMod_1_3, PearsMbAdjCosPFMod_2_3, PearsMbAdjCosPFMod_3_3, PearsMbAdjCosPFMod_4_3, PearsMbAdjCosPFMod_5_3, PearsMbAdjCosPFMod_6_3, 
        PearsMbAdjCosPFMod_1_4, PearsMbAdjCosPFMod_2_4, PearsMbAdjCosPFMod_3_4, PearsMbAdjCosPFMod_4_4, PearsMbAdjCosPFMod_5_4, PearsMbAdjCosPFMod_6_4, 
        PearsMbAdjCosPFMod_1_5, PearsMbAdjCosPFMod_2_5, PearsMbAdjCosPFMod_3_5, PearsMbAdjCosPFMod_4_5, PearsMbAdjCosPFMod_5_5, PearsMbAdjCosPFMod_6_5, 
        PearsMbAdjCosPFMod_1_6, PearsMbAdjCosPFMod_2_6, PearsMbAdjCosPFMod_3_6, PearsMbAdjCosPFMod_4_6, PearsMbAdjCosPFMod_5_6, PearsMbAdjCosPFMod_6_6, 
        PearsMbMod_1_2, PearsMbMod_2_2, PearsMbMod_3_2, PearsMbMod_4_2, PearsMbMod_5_2, PearsMbMod_6_2, 
        PearsMbMod_1_3, PearsMbMod_2_3, PearsMbMod_3_3, PearsMbMod_4_3, PearsMbMod_5_3, PearsMbMod_6_3, 
        PearsMbMod_1_4, PearsMbMod_2_4, PearsMbMod_3_4, PearsMbMod_4_4, PearsMbMod_5_4, PearsMbMod_6_4, 
        PearsMbMod_1_5, PearsMbMod_2_5, PearsMbMod_3_5, PearsMbMod_4_5, PearsMbMod_5_5, PearsMbMod_6_5, 
        PearsMbMod_1_6, PearsMbMod_2_6, PearsMbMod_3_6, PearsMbMod_4_6, PearsMbMod_5_6, PearsMbMod_6_6, 
        LMod_1_1_1, LMod_1_2_1, LMod_2_1_1, LMod_2_2_1, LMod_3_1_1, LMod_3_2_1, LMod_4_1_1, LMod_4_2_1, LMod_5_1_1, LMod_5_2_1, LMod_6_1_1, LMod_6_2_1, 
        LMod_7_1_1, LMod_7_2_1, LMod_8_1_1, LMod_8_2_1, LMod_9_1_1, LMod_9_2_1, LMod_10_1_1, LMod_10_2_1, LMod_11_1_1, LMod_11_2_1, LMod_12_1_1, LMod_12_2_1, 
        LMod_13_1_1, LMod_13_2_1, LMod_14_1_1, LMod_14_2_1, LMod_15_1_1, LMod_15_2_1, 
        LMod_1_1_2, LMod_1_2_2, LMod_2_1_2, LMod_2_2_2, LMod_3_1_2, LMod_3_2_2, LMod_4_1_2, LMod_4_2_2, LMod_5_1_2, LMod_5_2_2, LMod_6_1_2, LMod_6_2_2, 
        LMod_7_1_2, LMod_7_2_2, LMod_8_1_2, LMod_8_2_2, LMod_9_1_2, LMod_9_2_2, LMod_10_1_2, LMod_10_2_2, LMod_11_1_2, LMod_11_2_2, LMod_12_1_2, LMod_12_2_2, 
        LMod_13_1_2, LMod_13_2_2, LMod_14_1_2, LMod_14_2_2, LMod_15_1_2, LMod_15_2_2, 
        LMod_1_1_3, LMod_1_2_3, LMod_2_1_3, LMod_2_2_3, LMod_3_1_3, LMod_3_2_3, LMod_4_1_3, LMod_4_2_3, LMod_5_1_3, LMod_5_2_3, LMod_6_1_3, LMod_6_2_3, 
        LMod_7_1_3, LMod_7_2_3, LMod_8_1_3, LMod_8_2_3, LMod_9_1_3, LMod_9_2_3, LMod_10_1_3, LMod_10_2_3, LMod_11_1_3, LMod_11_2_3, LMod_12_1_3, LMod_12_2_3, 
        LMod_13_1_3, LMod_13_2_3, LMod_14_1_3, LMod_14_2_3, LMod_15_1_3, LMod_15_2_3, 
        LMod_1_1_4, LMod_1_2_4, LMod_2_1_4, LMod_2_2_4, LMod_3_1_4, LMod_3_2_4, LMod_4_1_4, LMod_4_2_4, LMod_5_1_4, LMod_5_2_4, LMod_6_1_4, LMod_6_2_4, 
        LMod_7_1_4, LMod_7_2_4, LMod_8_1_4, LMod_8_2_4, LMod_9_1_4, LMod_9_2_4, LMod_10_1_4, LMod_10_2_4, LMod_11_1_4, LMod_11_2_4, LMod_12_1_4, LMod_12_2_4, 
        LMod_13_1_4, LMod_13_2_4, LMod_14_1_4, LMod_14_2_4, LMod_15_1_4, LMod_15_2_4, 
        LMod_1_1_5, LMod_1_2_5, LMod_2_1_5, LMod_2_2_5, LMod_3_1_5, LMod_3_2_5, LMod_4_1_5, LMod_4_2_5, LMod_5_1_5, LMod_5_2_5, LMod_6_1_5, LMod_6_2_5, 
        LMod_7_1_5, LMod_7_2_5, LMod_8_1_5, LMod_8_2_5, LMod_9_1_5, LMod_9_2_5, LMod_10_1_5, LMod_10_2_5, LMod_11_1_5, LMod_11_2_5, LMod_12_1_5, LMod_12_2_5, 
        LMod_13_1_5, LMod_13_2_5, LMod_14_1_5, LMod_14_2_5, LMod_15_1_5, LMod_15_2_5, 
        LMod_1_1_6, LMod_1_2_6, LMod_2_1_6, LMod_2_2_6, LMod_3_1_6, LMod_3_2_6, LMod_4_1_6, LMod_4_2_6, LMod_5_1_6, LMod_5_2_6, LMod_6_1_6, LMod_6_2_6, 
        LMod_7_1_6, LMod_7_2_6, LMod_8_1_6, LMod_8_2_6, LMod_9_1_6, LMod_9_2_6, LMod_10_1_6, LMod_10_2_6, LMod_11_1_6, LMod_11_2_6, LMod_12_1_6, LMod_12_2_6, 
        LMod_13_1_6, LMod_13_2_6, LMod_14_1_6, LMod_14_2_6, LMod_15_1_6, LMod_15_2_6, 
        LW_1_1_1, LW_1_2_1, LW_2_1_1, LW_2_2_1, LW_1_1_2, LW_1_2_2, LW_2_1_2, LW_2_2_2, LW_1_1_3, LW_1_2_3, LW_2_1_3, LW_2_2_3, 
        LW_1_1_4, LW_1_2_4, LW_2_1_4, LW_2_2_4, LW_1_1_5, LW_1_2_5, LW_2_1_5, LW_2_2_5, LW_1_1_6, LW_1_2_6, LW_2_1_6, LW_2_2_6, 
        InnerProdW_1_1, 
        InnerProdW_1_2, InnerProdW_2_2, InnerProdW_3_2, InnerProdW_4_2, InnerProdW_1_3, InnerProdW_2_3, InnerProdW_3_3, InnerProdW_4_3, 
        InnerProdW_1_4, InnerProdW_2_4, InnerProdW_3_4, InnerProdW_4_4, InnerProdW_1_5, InnerProdW_2_5, InnerProdW_3_5, InnerProdW_4_5, 
        InnerProdW_1_6, InnerProdW_2_6, InnerProdW_3_6, InnerProdW_4_6, 
        Dtv_1_1_1, Dtv_1_2_1, Dtv_2_1_1, Dtv_2_2_1, Dtv_3_1_1, Dtv_3_2_1, Dtv_4_1_1, Dtv_4_2_1, Dtv_5_1_1, Dtv_5_2_1, Dtv_6_1_1, Dtv_6_2_1, Dtv_7_1_1, Dtv_7_2_1, 
        Dtv_8_1_1, Dtv_9_1_1, Dtv_9_2_1, Dtv_10_1_1, Dtv_10_2_1, Dtv_11_1_1, Dtv_11_2_1, Dtv_12_1_1, Dtv_12_2_1, Dtv_13_1_1, Dtv_14_1_1, 
        Dtv_1_1_2, Dtv_1_2_2, Dtv_2_1_2, Dtv_2_2_2, Dtv_3_1_2, Dtv_3_2_2, Dtv_4_1_2, Dtv_4_2_2, Dtv_5_1_2, Dtv_5_2_2, Dtv_6_1_2, Dtv_6_2_2, Dtv_7_1_2, Dtv_7_2_2, 
        Dtv_8_1_2, Dtv_9_1_2, Dtv_9_2_2, Dtv_10_1_2, Dtv_10_2_2, Dtv_11_1_2, Dtv_11_2_2, Dtv_12_1_2, Dtv_12_2_2, Dtv_13_1_2, Dtv_14_1_2, 
        Dtv_1_1_3, Dtv_1_2_3, Dtv_2_1_3, Dtv_2_2_3, Dtv_3_1_3, Dtv_3_2_3, Dtv_4_1_3, Dtv_4_2_3, Dtv_5_1_3, Dtv_5_2_3, Dtv_6_1_3, Dtv_6_2_3, Dtv_7_1_3, Dtv_7_2_3, 
        Dtv_8_1_3, Dtv_9_1_3, Dtv_9_2_3, Dtv_10_1_3, Dtv_10_2_3, Dtv_11_1_3, Dtv_11_2_3, Dtv_12_1_3, Dtv_12_2_3, Dtv_13_1_3, Dtv_14_1_3, 
        Dtv_1_1_4, Dtv_1_2_4, Dtv_2_1_4, Dtv_2_2_4, Dtv_3_1_4, Dtv_3_2_4, Dtv_4_1_4, Dtv_4_2_4, Dtv_5_1_4, Dtv_5_2_4, Dtv_6_1_4, Dtv_6_2_4, Dtv_7_1_4, Dtv_7_2_4, 
        Dtv_8_1_4, Dtv_9_1_4, Dtv_9_2_4, Dtv_10_1_4, Dtv_10_2_4, Dtv_11_1_4, Dtv_11_2_4, Dtv_12_1_4, Dtv_12_2_4, Dtv_13_1_4, Dtv_14_1_4, 
        Dtv_1_1_5, Dtv_1_2_5, Dtv_2_1_5, Dtv_2_2_5, Dtv_3_1_5, Dtv_3_2_5, Dtv_4_1_5, Dtv_4_2_5, Dtv_5_1_5, Dtv_5_2_5, Dtv_6_1_5, Dtv_6_2_5, Dtv_7_1_5, Dtv_7_2_5, 
        Dtv_8_1_5, Dtv_9_1_5, Dtv_9_2_5, Dtv_10_1_5, Dtv_10_2_5, Dtv_11_1_5, Dtv_11_2_5, Dtv_12_1_5, Dtv_12_2_5, Dtv_13_1_5, Dtv_14_1_5, 
        Dtv_1_1_6, Dtv_1_2_6, Dtv_2_1_6, Dtv_2_2_6, Dtv_3_1_6, Dtv_3_2_6, Dtv_4_1_6, Dtv_4_2_6, Dtv_5_1_6, Dtv_5_2_6, Dtv_6_1_6, Dtv_6_2_6, Dtv_7_1_6, Dtv_7_2_6, 
        Dtv_8_1_6, Dtv_9_1_6, Dtv_9_2_6, Dtv_10_1_6, Dtv_10_2_6, Dtv_11_1_6, Dtv_11_2_6, Dtv_12_1_6, Dtv_12_2_6, Dtv_13_1_6, Dtv_14_1_6, 
        DtvW_1_1_2, DtvW_1_2_2, DtvW_2_1_2, DtvW_2_2_2, DtvW_3_1_2, DtvW_3_2_2, DtvW_4_1_2, DtvW_4_2_2, 
        DtvW_1_1_3, DtvW_1_2_3, DtvW_2_1_3, DtvW_2_2_3, DtvW_3_1_3, DtvW_3_2_3, DtvW_4_1_3, DtvW_4_2_3, 
        DtvW_1_1_4, DtvW_1_2_4, DtvW_2_1_4, DtvW_2_2_4, DtvW_3_1_4, DtvW_3_2_4, DtvW_4_1_4, DtvW_4_2_4, 
        DtvW_1_1_5, DtvW_1_2_5, DtvW_2_1_5, DtvW_2_2_5, DtvW_3_1_5, DtvW_3_2_5, DtvW_4_1_5, DtvW_4_2_5, 
        DtvW_1_1_6, DtvW_1_2_6, DtvW_2_1_6, DtvW_2_2_6, DtvW_3_1_6, DtvW_3_2_6, DtvW_4_1_6, DtvW_4_2_6, 
        LinMod_1_1_1_1, LinMod_1_1_2_1, LinMod_1_2_1_1, LinMod_1_2_2_1, LinMod_2_1_1_1, LinMod_2_1_2_1, LinMod_2_2_1_1, LinMod_2_2_2_1, 
        LinMod_3_1_1_1, LinMod_3_1_2_1, LinMod_3_2_1_1, LinMod_3_2_2_1, LinMod_4_1_1_1, LinMod_4_1_2_1, LinMod_4_2_1_1, LinMod_4_2_2_1, 
        LinMod_5_1_1_1, LinMod_5_1_2_1, LinMod_5_2_1_1, LinMod_5_2_2_1, LinMod_6_1_1_1, LinMod_6_1_2_1, LinMod_6_2_1_1, LinMod_6_2_2_1, 
        LinMod_7_1_1_1, LinMod_7_1_2_1, LinMod_7_2_1_1, LinMod_7_2_2_1, LinMod_8_1_1_1, LinMod_8_1_2_1, LinMod_8_2_1_1, LinMod_8_2_2_1, 
        LinMod_9_1_1_1, LinMod_9_1_2_1, LinMod_9_2_1_1, LinMod_9_2_2_1, 
        LinMod_1_1_1_2, LinMod_1_1_2_2, LinMod_1_2_1_2, LinMod_1_2_2_2, LinMod_2_1_1_2, LinMod_2_1_2_2, LinMod_2_2_1_2, LinMod_2_2_2_2, 
        LinMod_3_1_1_2, LinMod_3_1_2_2, LinMod_3_2_1_2, LinMod_3_2_2_2, LinMod_4_1_1_2, LinMod_4_1_2_2, LinMod_4_2_1_2, LinMod_4_2_2_2, 
        LinMod_5_1_1_2, LinMod_5_1_2_2, LinMod_5_2_1_2, LinMod_5_2_2_2, LinMod_6_1_1_2, LinMod_6_1_2_2, LinMod_6_2_1_2, LinMod_6_2_2_2, 
        LinMod_7_1_1_2, LinMod_7_1_2_2, LinMod_7_2_1_2, LinMod_7_2_2_2, LinMod_8_1_1_2, LinMod_8_1_2_2, LinMod_8_2_1_2, LinMod_8_2_2_2, 
        LinMod_9_1_1_2, LinMod_9_1_2_2, LinMod_9_2_1_2, LinMod_9_2_2_2, 
        LinMod_1_1_1_3, LinMod_1_1_2_3, LinMod_1_2_1_3, LinMod_1_2_2_3, LinMod_2_1_1_3, LinMod_2_1_2_3, LinMod_2_2_1_3, LinMod_2_2_2_3, 
        LinMod_3_1_1_3, LinMod_3_1_2_3, LinMod_3_2_1_3, LinMod_3_2_2_3, LinMod_4_1_1_3, LinMod_4_1_2_3, LinMod_4_2_1_3, LinMod_4_2_2_3, 
        LinMod_5_1_1_3, LinMod_5_1_2_3, LinMod_5_2_1_3, LinMod_5_2_2_3, LinMod_6_1_1_3, LinMod_6_1_2_3, LinMod_6_2_1_3, LinMod_6_2_2_3, 
        LinMod_7_1_1_3, LinMod_7_1_2_3, LinMod_7_2_1_3, LinMod_7_2_2_3, LinMod_8_1_1_3, LinMod_8_1_2_3, LinMod_8_2_1_3, LinMod_8_2_2_3, 
        LinMod_9_1_1_3, LinMod_9_1_2_3, LinMod_9_2_1_3, LinMod_9_2_2_3, 
        LinMod_1_1_1_4, LinMod_1_1_2_4, LinMod_1_2_1_4, LinMod_1_2_2_4, LinMod_2_1_1_4, LinMod_2_1_2_4, LinMod_2_2_1_4, LinMod_2_2_2_4, 
        LinMod_3_1_1_4, LinMod_3_1_2_4, LinMod_3_2_1_4, LinMod_3_2_2_4, LinMod_4_1_1_4, LinMod_4_1_2_4, LinMod_4_2_1_4, LinMod_4_2_2_4, 
        LinMod_5_1_1_4, LinMod_5_1_2_4, LinMod_5_2_1_4, LinMod_5_2_2_4, LinMod_6_1_1_4, LinMod_6_1_2_4, LinMod_6_2_1_4, LinMod_6_2_2_4, 
        LinMod_7_1_1_4, LinMod_7_1_2_4, LinMod_7_2_1_4, LinMod_7_2_2_4, LinMod_8_1_1_4, LinMod_8_1_2_4, LinMod_8_2_1_4, LinMod_8_2_2_4, 
        LinMod_9_1_1_4, LinMod_9_1_2_4, LinMod_9_2_1_4, LinMod_9_2_2_4, 
        LinMod_1_1_1_5, LinMod_1_1_2_5, LinMod_1_2_1_5, LinMod_1_2_2_5, LinMod_2_1_1_5, LinMod_2_1_2_5, LinMod_2_2_1_5, LinMod_2_2_2_5, 
        LinMod_3_1_1_5, LinMod_3_1_2_5, LinMod_3_2_1_5, LinMod_3_2_2_5, LinMod_4_1_1_5, LinMod_4_1_2_5, LinMod_4_2_1_5, LinMod_4_2_2_5, 
        LinMod_5_1_1_5, LinMod_5_1_2_5, LinMod_5_2_1_5, LinMod_5_2_2_5, LinMod_6_1_1_5, LinMod_6_1_2_5, LinMod_6_2_1_5, LinMod_6_2_2_5, 
        LinMod_7_1_1_5, LinMod_7_1_2_5, LinMod_7_2_1_5, LinMod_7_2_2_5, LinMod_8_1_1_5, LinMod_8_1_2_5, LinMod_8_2_1_5, LinMod_8_2_2_5, 
        LinMod_9_1_1_5, LinMod_9_1_2_5, LinMod_9_2_1_5, LinMod_9_2_2_5, 
        LinMod_1_1_1_6, LinMod_1_1_2_6, LinMod_1_2_1_6, LinMod_1_2_2_6, LinMod_2_1_1_6, LinMod_2_1_2_6, LinMod_2_2_1_6, LinMod_2_2_2_6, 
        LinMod_3_1_1_6, LinMod_3_1_2_6, LinMod_3_2_1_6, LinMod_3_2_2_6, LinMod_4_1_1_6, LinMod_4_1_2_6, LinMod_4_2_1_6, LinMod_4_2_2_6, 
        LinMod_5_1_1_6, LinMod_5_1_2_6, LinMod_5_2_1_6, LinMod_5_2_2_6, LinMod_6_1_1_6, LinMod_6_1_2_6, LinMod_6_2_1_6, LinMod_6_2_2_6, 
        LinMod_7_1_1_6, LinMod_7_1_2_6, LinMod_7_2_1_6, LinMod_7_2_2_6, LinMod_8_1_1_6, LinMod_8_1_2_6, LinMod_8_2_1_6, LinMod_8_2_2_6, 
        LinMod_9_1_1_6, LinMod_9_1_2_6, LinMod_9_2_1_6, LinMod_9_2_2_6, 
        LinHindleRMod_1_1_1_1, LinHindleRMod_1_1_2_1, LinHindleRMod_1_2_1_1, LinHindleRMod_1_2_2_1, 
        LinHindleRMod_2_1_1_1, LinHindleRMod_2_1_2_1, LinHindleRMod_2_2_1_1, LinHindleRMod_2_2_2_1, 
        LinHindleRMod_3_1_1_1, LinHindleRMod_3_1_2_1, LinHindleRMod_3_2_1_1, LinHindleRMod_3_2_2_1, 
        LinHindleRMod_4_1_1_1, LinHindleRMod_4_1_2_1, LinHindleRMod_4_2_1_1, LinHindleRMod_4_2_2_1, 
        LinHindleRMod_5_1_1_1, LinHindleRMod_5_1_2_1, LinHindleRMod_5_2_1_1, LinHindleRMod_5_2_2_1, 
        LinHindleRMod_6_1_1_1, LinHindleRMod_6_1_2_1, LinHindleRMod_6_2_1_1, LinHindleRMod_6_2_2_1, 
        LinHindleRMod_7_1_1_1, LinHindleRMod_7_1_2_1, LinHindleRMod_7_2_1_1, LinHindleRMod_7_2_2_1, 
        LinHindleRMod_8_1_1_1, LinHindleRMod_8_1_2_1, LinHindleRMod_8_2_1_1, LinHindleRMod_8_2_2_1, 
        LinHindleRMod_9_1_1_1, LinHindleRMod_9_1_2_1, LinHindleRMod_9_2_1_1, LinHindleRMod_9_2_2_1, 
        LinHindleRMod_1_1_1_2, LinHindleRMod_1_1_2_2, LinHindleRMod_1_2_1_2, LinHindleRMod_1_2_2_2, 
        LinHindleRMod_2_1_1_2, LinHindleRMod_2_1_2_2, LinHindleRMod_2_2_1_2, LinHindleRMod_2_2_2_2, 
        LinHindleRMod_3_1_1_2, LinHindleRMod_3_1_2_2, LinHindleRMod_3_2_1_2, LinHindleRMod_3_2_2_2, 
        LinHindleRMod_4_1_1_2, LinHindleRMod_4_1_2_2, LinHindleRMod_4_2_1_2, LinHindleRMod_4_2_2_2, 
        LinHindleRMod_5_1_1_2, LinHindleRMod_5_1_2_2, LinHindleRMod_5_2_1_2, LinHindleRMod_5_2_2_2, 
        LinHindleRMod_6_1_1_2, LinHindleRMod_6_1_2_2, LinHindleRMod_6_2_1_2, LinHindleRMod_6_2_2_2, 
        LinHindleRMod_7_1_1_2, LinHindleRMod_7_1_2_2, LinHindleRMod_7_2_1_2, LinHindleRMod_7_2_2_2, 
        LinHindleRMod_8_1_1_2, LinHindleRMod_8_1_2_2, LinHindleRMod_8_2_1_2, LinHindleRMod_8_2_2_2, 
        LinHindleRMod_9_1_1_2, LinHindleRMod_9_1_2_2, LinHindleRMod_9_2_1_2, LinHindleRMod_9_2_2_2, 
        LinHindleRMod_1_1_1_3, LinHindleRMod_1_1_2_3, LinHindleRMod_1_2_1_3, LinHindleRMod_1_2_2_3, 
        LinHindleRMod_2_1_1_3, LinHindleRMod_2_1_2_3, LinHindleRMod_2_2_1_3, LinHindleRMod_2_2_2_3, 
        LinHindleRMod_3_1_1_3, LinHindleRMod_3_1_2_3, LinHindleRMod_3_2_1_3, LinHindleRMod_3_2_2_3, 
        LinHindleRMod_4_1_1_3, LinHindleRMod_4_1_2_3, LinHindleRMod_4_2_1_3, LinHindleRMod_4_2_2_3, 
        LinHindleRMod_5_1_1_3, LinHindleRMod_5_1_2_3, LinHindleRMod_5_2_1_3, LinHindleRMod_5_2_2_3, 
        LinHindleRMod_6_1_1_3, LinHindleRMod_6_1_2_3, LinHindleRMod_6_2_1_3, LinHindleRMod_6_2_2_3, 
        LinHindleRMod_7_1_1_3, LinHindleRMod_7_1_2_3, LinHindleRMod_7_2_1_3, LinHindleRMod_7_2_2_3, 
        LinHindleRMod_8_1_1_3, LinHindleRMod_8_1_2_3, LinHindleRMod_8_2_1_3, LinHindleRMod_8_2_2_3, 
        LinHindleRMod_9_1_1_3, LinHindleRMod_9_1_2_3, LinHindleRMod_9_2_1_3, LinHindleRMod_9_2_2_3, 
        LinHindleRMod_1_1_1_4, LinHindleRMod_1_1_2_4, LinHindleRMod_1_2_1_4, LinHindleRMod_1_2_2_4, 
        LinHindleRMod_2_1_1_4, LinHindleRMod_2_1_2_4, LinHindleRMod_2_2_1_4, LinHindleRMod_2_2_2_4, 
        LinHindleRMod_3_1_1_4, LinHindleRMod_3_1_2_4, LinHindleRMod_3_2_1_4, LinHindleRMod_3_2_2_4, 
        LinHindleRMod_4_1_1_4, LinHindleRMod_4_1_2_4, LinHindleRMod_4_2_1_4, LinHindleRMod_4_2_2_4, 
        LinHindleRMod_5_1_1_4, LinHindleRMod_5_1_2_4, LinHindleRMod_5_2_1_4, LinHindleRMod_5_2_2_4, 
        LinHindleRMod_6_1_1_4, LinHindleRMod_6_1_2_4, LinHindleRMod_6_2_1_4, LinHindleRMod_6_2_2_4, 
        LinHindleRMod_7_1_1_4, LinHindleRMod_7_1_2_4, LinHindleRMod_7_2_1_4, LinHindleRMod_7_2_2_4, 
        LinHindleRMod_8_1_1_4, LinHindleRMod_8_1_2_4, LinHindleRMod_8_2_1_4, LinHindleRMod_8_2_2_4, 
        LinHindleRMod_9_1_1_4, LinHindleRMod_9_1_2_4, LinHindleRMod_9_2_1_4, LinHindleRMod_9_2_2_4, 
        LinHindleRMod_1_1_1_5, LinHindleRMod_1_1_2_5, LinHindleRMod_1_2_1_5, LinHindleRMod_1_2_2_5, 
        LinHindleRMod_2_1_1_5, LinHindleRMod_2_1_2_5, LinHindleRMod_2_2_1_5, LinHindleRMod_2_2_2_5, 
        LinHindleRMod_3_1_1_5, LinHindleRMod_3_1_2_5, LinHindleRMod_3_2_1_5, LinHindleRMod_3_2_2_5, 
        LinHindleRMod_4_1_1_5, LinHindleRMod_4_1_2_5, LinHindleRMod_4_2_1_5, LinHindleRMod_4_2_2_5, 
        LinHindleRMod_5_1_1_5, LinHindleRMod_5_1_2_5, LinHindleRMod_5_2_1_5, LinHindleRMod_5_2_2_5, 
        LinHindleRMod_6_1_1_5, LinHindleRMod_6_1_2_5, LinHindleRMod_6_2_1_5, LinHindleRMod_6_2_2_5, 
        LinHindleRMod_7_1_1_5, LinHindleRMod_7_1_2_5, LinHindleRMod_7_2_1_5, LinHindleRMod_7_2_2_5, 
        LinHindleRMod_8_1_1_5, LinHindleRMod_8_1_2_5, LinHindleRMod_8_2_1_5, LinHindleRMod_8_2_2_5, 
        LinHindleRMod_9_1_1_5, LinHindleRMod_9_1_2_5, LinHindleRMod_9_2_1_5, LinHindleRMod_9_2_2_5, 
        LinHindleRMod_1_1_1_6, LinHindleRMod_1_1_2_6, LinHindleRMod_1_2_1_6, LinHindleRMod_1_2_2_6, 
        LinHindleRMod_2_1_1_6, LinHindleRMod_2_1_2_6, LinHindleRMod_2_2_1_6, LinHindleRMod_2_2_2_6, 
        LinHindleRMod_3_1_1_6, LinHindleRMod_3_1_2_6, LinHindleRMod_3_2_1_6, LinHindleRMod_3_2_2_6, 
        LinHindleRMod_4_1_1_6, LinHindleRMod_4_1_2_6, LinHindleRMod_4_2_1_6, LinHindleRMod_4_2_2_6, 
        LinHindleRMod_5_1_1_6, LinHindleRMod_5_1_2_6, LinHindleRMod_5_2_1_6, LinHindleRMod_5_2_2_6, 
        LinHindleRMod_6_1_1_6, LinHindleRMod_6_1_2_6, LinHindleRMod_6_2_1_6, LinHindleRMod_6_2_2_6, 
        LinHindleRMod_7_1_1_6, LinHindleRMod_7_1_2_6, LinHindleRMod_7_2_1_6, LinHindleRMod_7_2_2_6, 
        LinHindleRMod_8_1_1_6, LinHindleRMod_8_1_2_6, LinHindleRMod_8_2_1_6, LinHindleRMod_8_2_2_6, 
        LinHindleRMod_9_1_1_6, LinHindleRMod_9_1_2_6, LinHindleRMod_9_2_1_6, LinHindleRMod_9_2_2_6, 
        PearsMod_1_2, PearsMod_2_2, PearsMod_3_2, PearsMod_4_2, PearsMod_5_2, PearsMod_6_2, PearsMod_1_3, PearsMod_2_3, PearsMod_3_3, PearsMod_4_3, PearsMod_5_3, PearsMod_6_3, 
        PearsMod_1_4, PearsMod_2_4, PearsMod_3_4, PearsMod_4_4, PearsMod_5_4, PearsMod_6_4, PearsMod_1_5, PearsMod_2_5, PearsMod_3_5, PearsMod_4_5, PearsMod_5_5, PearsMod_6_5, 
        PearsMod_1_6, PearsMod_2_6, PearsMod_3_6, PearsMod_4_6, PearsMod_5_6, PearsMod_6_6, 
        PenroseShapeMod_1_1, 
        PenroseShapeMod_1_2, PenroseShapeMod_2_2, PenroseShapeMod_3_2, PenroseShapeMod_4_2, PenroseShapeMod_1_3, PenroseShapeMod_2_3, PenroseShapeMod_3_3, PenroseShapeMod_4_3, 
        PenroseShapeMod_1_4, PenroseShapeMod_2_4, PenroseShapeMod_3_4, PenroseShapeMod_4_4, PenroseShapeMod_1_5, PenroseShapeMod_2_5, PenroseShapeMod_3_5, PenroseShapeMod_4_5, 
        PenroseShapeMod_1_6, PenroseShapeMod_2_6, PenroseShapeMod_3_6, PenroseShapeMod_4_6};
    public static SimilarityMeasure similarityMeasure=SimilarityMeasure.Cos;
    public static String similarityMeasureString = null;
    
    
    public static Pattern cosModPattern = Pattern.compile("CosMod.*");
    public static Pattern innerProdWPattern = Pattern.compile("InnerProdW.*");
    public static Pattern lModPattern = Pattern.compile("LMod.*");
    public static Pattern lWPattern = Pattern.compile("LW.*");
    public static Pattern dtvPattern = Pattern.compile("Dtv_.*");
    public static Pattern dtvWPattern = Pattern.compile("DtvW.*");
    public static Pattern pearsModPattern = Pattern.compile("PearsMod.*");
    public static Pattern penroseShapeModPattern = Pattern.compile("PenroseShapeMod.*");
    public static Pattern linModPattern = Pattern.compile("LinMod.*");
    public static Pattern linHindleRModPattern = Pattern.compile("LinHindleRMod.*");
    public static Pattern pearsCombPattern = Pattern.compile("Pears((Mb)|(AdjCos)|(PFMod)|(MbAdjCos)|(MbPFMod)|(AdjCosPFMod)|(MbAdjCosPFMod))");
    public static Pattern mbModPattern = Pattern.compile("MbMod.*");
    public static Pattern adjCosModPattern = Pattern.compile("AdjCosMod.*");
    public static Pattern pfModPattern = Pattern.compile("PFMod_.*");
    public static Pattern pearsMbAdjCosModPattern = Pattern.compile("PearsMbAdjCosMod.*");
    public static Pattern pearsMbAdjCosPfModPattern = Pattern.compile("PearsMbAdjCosPFMod.+");
    public static Pattern pearsMbModPattern = Pattern.compile("PearsMbMod.*");
    
    
    public static double zklGamma=5;
    public static double alphaSkewAlpha=0.99d;
    public static double smoothCosBeta=0.1d;
    public static double adjCosLambda=0.1d;
    public static double adjCosPFModLambda=1d;
    public static double normCosLambda=0.01d;
    public static double normCosGamma=0.05d;
    public static double SocPmiGamma=3d;
    public static double SocPmiMu=6.5d;
    public static double normModSocPmiLambda=0.125d;
    public static double normModSocPmiGamma=1d;
    public static double normModSocPmiDelta=0.3d;
    public static double rboP=0.995d;
    public static double apSynN=500;
    public static double apSynPP=0.1d;
    
    
    
    
    /**
     * This function calculates the numerical and Lin similarity of the feature vectors @param word1Map and @param word2Map (without normalization), with respect to one type of features,
     * so @param word1Map and @param word2Map consists of features of only one type.
     * @param <V> the type of keys in @param tuplesMap1 and in @param tuplesMap2
     * @param word1Map a map, in which the features of word1 are stored
     * @param word2Map a map, in which the features of word2 are stored
     * @param vectorMean1 the mean of the values in the feature vector of word1
     * @param vectorMean2 the mean of the values in the feature vector of word2
     * @param numberOfAllFeatures the number of all features of the given type
     * @param informationMap a map, in which the information content of features are stored
     * @return the pair, in which the numerical and Lin similarity is stored
     */
    public static <V> SimilarityScoreParts calculateSimilarityByRelation(HashMap<V, Double> word1Map, HashMap<V, Double> word2Map, Double vectorMean1, Double vectorMean2, long numberOfAllFeatures, HashMap<V, Double> informationMap){
        
        double d;
        
        Double d1 = null;
        Double d2 = null;
        
        Double scoreNumerator=null;
        Double scoreDenominator1=null;
        Double scoreDenominator2=null;
        Double scoreLinIntersect=null;
        Double scoreLin1=null;
        Double scoreLin2=null;
        
        if(method==Method.Lin){
            scoreLinIntersect=0d;
            scoreLin1=0d;
            scoreLin2=0d;
        }else if(similarityMeasureString.matches("Multiplicative.*")){
            scoreNumerator=1d;
        }else{
            scoreNumerator=0d;
            if(similarityMeasure==SimilarityMeasure.Jaccard_3 || similarityMeasure==SimilarityMeasure.Tanimoto_1 || 
                    similarityMeasure==SimilarityMeasure.Tanimoto_1Mod || 
                    similarityMeasure==SimilarityMeasure.Rsssn || similarityMeasure==SimilarityMeasure.AvgL1LInf || 
                    similarityMeasure==SimilarityMeasure.MaxSymChiSqMod || similarityMeasure==SimilarityMeasure.Kulczynski || 
                    similarityMeasureString.matches("(NormMod)?SocPmiMod") || similarityMeasure==SimilarityMeasure.Wo){
                scoreDenominator1=0d;
            }else if(similarityMeasure==SimilarityMeasure.Simpson_2Mod || 
                    similarityMeasure==SimilarityMeasure.NormCosMod || similarityMeasure==SimilarityMeasure.NcdMod_2 || 
                    similarityMeasureString.matches("(((Adj)?Cos)|(Mb)|(PF))Mod_(2|3|5|6)_.") || 
                    similarityMeasureString.matches("LMod_(4|5|6|7|(10)|(11)|(12)|(13)|(14)|(15)).*") || 
                    similarityMeasureString.matches("Dtv_(2|3|4|5|9|(10)|(11)|(12)).*") || similarityMeasureString.matches("Lin(HindleR)?Mod_(2|3|4|5|8|9)_(1|2).*")){
                scoreDenominator1=0d;
                scoreDenominator2=0d;
            }
        }
        
        //If neither of the feature vectors are null, then a loop iterates through each feature in @param word1Map, and computes the relevant scores.
        if(word1Map!=null){
            
            for(Map.Entry<V, Double> featureValuePair: word1Map.entrySet()){
                
                V feature = featureValuePair.getKey();
                d1 = featureValuePair.getValue();

                if(method==Method.Num){
                    if(word2Map!=null && (d2 = word2Map.get(feature))!=null){
                        d=calculateSimilarityNumaratorByRelationSubscore(d1, d2, vectorMean1, vectorMean2, numberOfAllFeatures);
                        if(similarityMeasure==SimilarityMeasure.LInf || similarityMeasure==SimilarityMeasure.RenyiDivModInf){
                            scoreNumerator=Math.max(scoreNumerator, d);
                        }else if(similarityMeasureString.matches("Multiplicative.*")){
                            scoreNumerator*=d;
                        }else{
                            scoreNumerator+=d;
                            if(similarityMeasure==SimilarityMeasure.Jaccard_3 || similarityMeasure==SimilarityMeasure.Tanimoto_1 || 
                                    similarityMeasure==SimilarityMeasure.Tanimoto_1Mod || 
                                    similarityMeasure==SimilarityMeasure.Rsssn || similarityMeasure==SimilarityMeasure.MaxSymChiSqMod || 
                                    similarityMeasure==SimilarityMeasure.Kulczynski || similarityMeasureString.matches("(NormMod)?SocPmiMod") || 
                                    similarityMeasure==SimilarityMeasure.Wo){
                                scoreDenominator1+=calculateSimilarityDenominator1ByRelationSubscore(d1, d2, vectorMean1, vectorMean2);
                            }else if(similarityMeasure==SimilarityMeasure.Simpson_2Mod || 
                                    similarityMeasure==SimilarityMeasure.NormCosMod || similarityMeasure==SimilarityMeasure.NcdMod_2 || 
                                    similarityMeasureString.matches("(((Adj)?Cos)|(Mb)|(PF))Mod_(2|3|5|6)_.") || 
                                    similarityMeasureString.matches("LMod_(4|5|6|7|(10)|(11)|(12)|(13)|(14)|(15)).*") || 
                                    similarityMeasureString.matches("Dtv_(2|3|4|5|9|(10)|(11)|(12)).*") || similarityMeasureString.matches("Lin(HindleR)?Mod_(2|3|4|5|8|9)_(1|2).*")){
                                scoreDenominator1+=calculateSimilarityDenominator1ByRelationSubscore(d1, d2, vectorMean1, vectorMean2);
                                scoreDenominator2+=calculateSimilarityDenominator2ByRelationSubscore(d1, d2, vectorMean1, vectorMean2);
                            }else if(similarityMeasure==SimilarityMeasure.AvgL1LInf){
                                scoreDenominator1=Math.max(scoreDenominator1, d);
                            }
                        }
                    }else if(similarityMeasure==SimilarityMeasure.Dice_1 || similarityMeasure==SimilarityMeasure.Dice_1Mod || 
                            similarityMeasure==SimilarityMeasure.ZklMod || similarityMeasure==SimilarityMeasure.ZklModSym || 
                            similarityMeasure==SimilarityMeasure.L05 || similarityMeasure==SimilarityMeasure.L1 || 
                            similarityMeasure==SimilarityMeasure.L2 || similarityMeasure==SimilarityMeasure.L3 || 
                            similarityMeasure==SimilarityMeasure.Canberra || similarityMeasure==SimilarityMeasure.Lorentzian || 
                            similarityMeasure==SimilarityMeasure.Jaccard_3 || similarityMeasure==SimilarityMeasure.ASkewMod || 
                            similarityMeasure==SimilarityMeasure.ASkewModSym || 
                            similarityMeasure==SimilarityMeasure.JensenShannonMod || similarityMeasure==SimilarityMeasure.JensenMod || 
                            similarityMeasure==SimilarityMeasure.LinKiela || similarityMeasure==SimilarityMeasure.Tanimoto_1 || 
                            similarityMeasure==SimilarityMeasure.Tanimoto_1Mod || 
                            similarityMeasure==SimilarityMeasure.HellingerMod || similarityMeasure==SimilarityMeasure.ChiSquareMod || 
                            similarityMeasure==SimilarityMeasure.PsChiSquareMod || similarityMeasure==SimilarityMeasure.ClarkMod || 
                            similarityMeasure==SimilarityMeasure.RenyiDivMod2 || similarityMeasure==SimilarityMeasure.Srsn || 
                            similarityMeasure==SimilarityMeasure.Rsssn || similarityMeasure==SimilarityMeasure.Sdsn || 
                            similarityMeasure==SimilarityMeasure.Srsmv || similarityMeasure==SimilarityMeasure.Srsm || 
                            similarityMeasure==SimilarityMeasure.Overlap || similarityMeasure==SimilarityMeasure.AvgL1LInf || 
                            similarityMeasure==SimilarityMeasure.VicWhMod || similarityMeasure==SimilarityMeasure.VicSymChiSqMod1 || 
                            similarityMeasure==SimilarityMeasure.VicSymChiSqMod2 || similarityMeasure==SimilarityMeasure.VicSymChiSqMod3 || 
                            similarityMeasure==SimilarityMeasure.MaxSymChiSqMod || similarityMeasure==SimilarityMeasure.Rms || 
                            similarityMeasure==SimilarityMeasure.ContraHMeanMod || similarityMeasure==SimilarityMeasure.Spline || 
                            similarityMeasure==SimilarityMeasure.Kulczynski || similarityMeasure==SimilarityMeasure.SorensenMod || 
                            similarityMeasure==SimilarityMeasure.Simpson_2Mod || similarityMeasure==SimilarityMeasure.NormCosMod || 
                            similarityMeasure==SimilarityMeasure.NcdMod_2 || similarityMeasure==SimilarityMeasure.MahalanobisMod || 
                            similarityMeasure==SimilarityMeasure.NgdMod || similarityMeasure==SimilarityMeasure.Lsmq || 
                            lModPattern.matcher(similarityMeasureString).matches() || lWPattern.matcher(similarityMeasureString).matches() || 
                            dtvPattern.matcher(similarityMeasureString).matches() || dtvWPattern.matcher(similarityMeasureString).matches() || 
                            similarityMeasureString.matches("(((Adj)?Cos)|(Mb)|(PF))Mod_(2|3|5|6)_.") || 
                            similarityMeasureString.matches("StdLike_.") || similarityMeasureString.matches("(NormMod)?SocPmiMod") || 
                            similarityMeasureString.matches("Lin(HindleR)?Mod_(2|3|4|5|8|9)_(1|2).*")){
                        scoreNumerator+=calculateSimilarityNumaratorByRelationSubscore(d1, 0d, vectorMean1, vectorMean2, numberOfAllFeatures);
                        if(similarityMeasure==SimilarityMeasure.Jaccard_3 || similarityMeasure==SimilarityMeasure.Tanimoto_1 || 
                                similarityMeasure==SimilarityMeasure.Tanimoto_1Mod || 
                                similarityMeasure==SimilarityMeasure.Rsssn || similarityMeasure==SimilarityMeasure.MaxSymChiSqMod || 
                                similarityMeasure==SimilarityMeasure.Kulczynski || similarityMeasureString.matches("(NormMod)?SocPmiMod")){
                            scoreDenominator1+=calculateSimilarityDenominator1ByRelationSubscore(d1, 0d, vectorMean1, vectorMean2);
                        }else if(similarityMeasure==SimilarityMeasure.Simpson_2Mod || 
                                similarityMeasure==SimilarityMeasure.NormCosMod || similarityMeasure==SimilarityMeasure.NcdMod_2 || 
                                similarityMeasureString.matches("(((Adj)?Cos)|(Mb)|(PF))Mod_(2|3|5|6)_.") || 
                                similarityMeasureString.matches("LMod_(4|5|6|7|(10)|(11)|(12)|(13)|(14)|(15)).*") || 
                                similarityMeasureString.matches("Dtv_(2|3|4|5|9|(10)|(11)|(12)).*") || similarityMeasureString.matches("Lin(HindleR)?Mod_(2|3|4|5|8|9)_(1|2).*")){
                            scoreDenominator1+=calculateSimilarityDenominator1ByRelationSubscore(d1, 0d, vectorMean1, vectorMean2);
                            scoreDenominator2+=calculateSimilarityDenominator2ByRelationSubscore(d1, 0d, vectorMean1, vectorMean2);
                        }else if(similarityMeasure==SimilarityMeasure.AvgL1LInf){
                            scoreDenominator1=Math.max(scoreDenominator1, calculateSimilarityNumaratorByRelationSubscore(d1, 0d, vectorMean1, vectorMean2, numberOfAllFeatures));
                        }
                    }else if(similarityMeasure==SimilarityMeasure.LInf || similarityMeasure==SimilarityMeasure.RenyiDivModInf){
                        scoreNumerator=Math.max(scoreNumerator, calculateSimilarityNumaratorByRelationSubscore(d1, 0d, vectorMean1, vectorMean2, numberOfAllFeatures));
                    }else if(similarityMeasureString.matches("Multiplicative.*")){
                        scoreNumerator*=calculateSimilarityNumaratorByRelationSubscore(d1, 0d, vectorMean1, vectorMean2, numberOfAllFeatures);
                    }
                }else if(method==Method.Lin){
                    d=informationMap.get(feature);
                    if(word2Map!=null && word2Map.containsKey(feature)){
                        scoreLinIntersect+=d;
                    }
                    scoreLin1+=d;
                }
                
            }
        
        }

        //If word2Map!=null, then the relevant scores are calculated from word2Map.
        if(word2Map!=null && (method==Method.Lin || (method==Method.Num && (similarityMeasure==SimilarityMeasure.Dice_1 || 
                similarityMeasure==SimilarityMeasure.Dice_1Mod || similarityMeasure==SimilarityMeasure.ZklModSym || 
                similarityMeasure==SimilarityMeasure.L05 || similarityMeasure==SimilarityMeasure.L1 || 
                similarityMeasure==SimilarityMeasure.L2 || similarityMeasure==SimilarityMeasure.L3 || 
                similarityMeasure==SimilarityMeasure.LInf || similarityMeasure==SimilarityMeasure.Canberra || 
                similarityMeasure==SimilarityMeasure.Lorentzian || similarityMeasure==SimilarityMeasure.Jaccard_3 || 
                similarityMeasure==SimilarityMeasure.ASkewModSym || similarityMeasure==SimilarityMeasure.JensenShannonMod || 
                similarityMeasure==SimilarityMeasure.JensenMod || similarityMeasure==SimilarityMeasure.LinKiela || 
                similarityMeasure==SimilarityMeasure.Tanimoto_1 || similarityMeasure==SimilarityMeasure.Tanimoto_1Mod || 
                similarityMeasure==SimilarityMeasure.HellingerMod || 
                similarityMeasure==SimilarityMeasure.ChiSquareMod || similarityMeasure==SimilarityMeasure.PsChiSquareMod || 
                similarityMeasure==SimilarityMeasure.ClarkMod || similarityMeasure==SimilarityMeasure.RenyiDivMod2 || 
                similarityMeasure==SimilarityMeasure.RenyiDivModInf || similarityMeasure==SimilarityMeasure.Srsn || 
                similarityMeasure==SimilarityMeasure.Rsssn || similarityMeasure==SimilarityMeasure.Sdsn || 
                similarityMeasure==SimilarityMeasure.Srsmv || similarityMeasure==SimilarityMeasure.Srsm || 
                similarityMeasure==SimilarityMeasure.Overlap || similarityMeasure==SimilarityMeasure.AvgL1LInf || 
                similarityMeasure==SimilarityMeasure.VicWhMod || similarityMeasure==SimilarityMeasure.VicSymChiSqMod1 || 
                similarityMeasure==SimilarityMeasure.VicSymChiSqMod2 || similarityMeasure==SimilarityMeasure.VicSymChiSqMod3 || 
                similarityMeasure==SimilarityMeasure.MaxSymChiSqMod || similarityMeasure==SimilarityMeasure.Rms || 
                similarityMeasure==SimilarityMeasure.ContraHMeanMod || similarityMeasure==SimilarityMeasure.Spline || 
                similarityMeasure==SimilarityMeasure.Kulczynski || similarityMeasure==SimilarityMeasure.SorensenMod || 
                similarityMeasure==SimilarityMeasure.Simpson_2Mod || similarityMeasure==SimilarityMeasure.NormCosMod || 
                similarityMeasure==SimilarityMeasure.NcdMod_2 || similarityMeasure==SimilarityMeasure.NgdMod || 
                similarityMeasure==SimilarityMeasure.Lsmq || similarityMeasure==SimilarityMeasure.MahalanobisMod || 
                lModPattern.matcher(similarityMeasureString).matches() || lWPattern.matcher(similarityMeasureString).matches() || 
                dtvPattern.matcher(similarityMeasureString).matches() || dtvWPattern.matcher(similarityMeasureString).matches() || 
                similarityMeasureString.matches("(((Adj)?Cos)|(Mb)|(PF))Mod_(2|3|5|6)_.") || similarityMeasureString.matches("Multiplicative.*") || 
                similarityMeasureString.matches("(NormMod)?SocPmiMod") || similarityMeasureString.matches("StdLike_.") || 
                similarityMeasureString.matches("Lin(HindleR)?Mod_(2|3|4|5|8|9)_(1|2).*"))))){
            
            for(Map.Entry<V, Double> featureValuePair: word2Map.entrySet()){
                
                V feature = featureValuePair.getKey();
                d2 = featureValuePair.getValue();
                
                if(method==Method.Num && (word1Map==null || !word1Map.containsKey(feature))){
                    d=calculateSimilarityNumaratorByRelationSubscore(0d, d2, vectorMean1, vectorMean2, numberOfAllFeatures);
                    if(similarityMeasure==SimilarityMeasure.LInf || similarityMeasure==SimilarityMeasure.RenyiDivModInf){
                        scoreNumerator=Math.max(scoreNumerator, d);
                    }else if(similarityMeasureString.matches("Multiplicative.*")){
                        scoreNumerator*=d;
                    }else{
                        scoreNumerator+=d;
                        if(similarityMeasure==SimilarityMeasure.Jaccard_3 || similarityMeasure==SimilarityMeasure.Tanimoto_1 || 
                                similarityMeasure==SimilarityMeasure.Tanimoto_1Mod || 
                                similarityMeasure==SimilarityMeasure.Rsssn || similarityMeasure==SimilarityMeasure.MaxSymChiSqMod || 
                                similarityMeasure==SimilarityMeasure.Kulczynski || similarityMeasureString.matches("(NormMod)?SocPmiMod")){
                            scoreDenominator1+=calculateSimilarityDenominator1ByRelationSubscore(0d, d2, vectorMean1, vectorMean2);
                        }else if(similarityMeasure==SimilarityMeasure.Simpson_2Mod || 
                                similarityMeasure==SimilarityMeasure.NormCosMod || similarityMeasure==SimilarityMeasure.NcdMod_2 || 
                                similarityMeasureString.matches("(((Adj)?Cos)|(Mb)|(PF))Mod_(2|3|5|6)_.") || 
                                similarityMeasureString.matches("LMod_(4|5|6|7|(10)|(11)|(12)|(13)|(14)|(15)).*") || 
                                similarityMeasureString.matches("Dtv_(2|3|4|5|9|(10)|(11)|(12)).*") || similarityMeasureString.matches("Lin(HindleR)?Mod_(2|3|4|5|8|9)_(1|2).*")){
                            scoreDenominator1+=calculateSimilarityDenominator1ByRelationSubscore(0d, d2, vectorMean1, vectorMean2);
                            scoreDenominator2+=calculateSimilarityDenominator2ByRelationSubscore(0d, d2, vectorMean1, vectorMean2);
                        }else if(similarityMeasure==SimilarityMeasure.AvgL1LInf){
                            scoreDenominator1=Math.max(scoreDenominator1, d);
                        }
                    }
                }else if(method==Method.Lin){
                    scoreLin2+=informationMap.get(feature);
                }
            }
            
        }
        
        return new SimilarityScoreParts(scoreNumerator, scoreDenominator1, scoreDenominator2, scoreLinIntersect, scoreLin1, scoreLin2);
        
    }
    
    
    
    
    
    /**
     * This function calculates the numerical similarity of the feature vectors @param word1Map and @param word2Map (without normalization), with respect to one type of features 
     * (so @param word1Map and @param word2Map consists of features of only one type), 
     * in case of such similarity measures, where the calculation should take place even if (d1==0 &amp;&amp; d2==0).
     * @param <V> the type of keys in @param tuplesMap1 and in @param tuplesMap2
     * @param word1Map a map, in which the features of word1 are stored
     * @param word2Map a map, in which the features of word2 are stored
     * @param featureMap a map, whose keyset contains all the possible features of a type for a word
     * @param vectorMean1 the mean of the values in the feature vector of word1
     * @param vectorMean2 the mean of the values in the feature vector of word2
     * @param numberOfAllFeatures the number of all features of the given type
     * @param informationMap a map, in which the information content of features are stored
     * @return the pair, in which the numerical and Lin similarity is stored
     */
    public static <V> SimilarityScoreParts calculateSimilarityByRelationForDoubleZeroValuesToo(HashMap<V, Double> word1Map, HashMap<V, Double> word2Map, HashMap<V, Long> featureMap, Double vectorMean1, Double vectorMean2, long numberOfAllFeatures, HashMap<V, Double> informationMap){
        
        Double d1 = null;
        Double d2 = null;
        
        Double scoreNumerator=null;
        Double scoreDenominator1=null;
        Double scoreDenominator2=null;
        if(method==Method.Lin){
            System.out.println("The calculateSimilarityByRelationForDoubleZeroValuesToo function should only be called in case of numerical similarity measures!");
            System.exit(1);
        }else{
            scoreNumerator=0d;
            if(pearsModPattern.matcher(similarityMeasureString).matches() || pearsCombPattern.matcher(similarityMeasureString).matches() || 
                    pearsMbAdjCosModPattern.matcher(similarityMeasureString).matches() || pearsMbAdjCosPfModPattern.matcher(similarityMeasureString).matches() || 
                    pearsMbModPattern.matcher(similarityMeasureString).matches()){
                scoreDenominator1=0d;
                scoreDenominator2=0d;
            }
        }
        
        //If neither of the feature vectors are null, then a loop iterates through each feature in @param word1Map, and computes the relevant scores.
        if(word1Map==null){
            word1Map = new HashMap<V, Double>();
        }
        
        if(word2Map==null){
            word2Map = new HashMap<V, Double>();
        }
        
        if(!(word1Map.isEmpty() && word2Map.isEmpty() && vectorMean1==0 && vectorMean2==0)){
                
            if(similarityMeasure==SimilarityMeasure.Dfvmb){

                for(V feature: featureMap.keySet()){

                    d1 = word1Map.get(feature);
                    if(d1==null){
                        d1 = 0d;
                    }

                    d2 = word2Map.get(feature);
                    if(d2==null){
                        d2 = 0d;
                    }

                    scoreNumerator += Math.pow(d1-vectorMean1, 2d)+Math.pow(d2-vectorMean2, 2d);

                }

            }else if(pearsModPattern.matcher(similarityMeasureString).matches()){

                int type = Integer.parseInt(similarityMeasureString.substring(similarityMeasureString.length()-1));

                if(similarityMeasureString.matches("PearsMod_(1|4)_.")){

                    for(V feature: featureMap.keySet()){

                        d1 = word1Map.get(feature);
                        if(d1==null){
                            d1 = 0d;
                        }

                        d2 = word2Map.get(feature);
                        if(d2==null){
                            d2 = 0d;
                        }

                        scoreNumerator += similarityMeasureFFunction(type, d1-vectorMean1)*similarityMeasureFFunction(type, d2-vectorMean2);

                        scoreDenominator1 += Math.pow(d1-vectorMean1, 2d);
                        scoreDenominator2 += Math.pow(d2-vectorMean2, 2d);

                    }

                }else if(similarityMeasureString.matches("PearsMod_(2|3|5|6)_.")){

                    for(V feature: featureMap.keySet()){

                        d1 = word1Map.get(feature);
                        if(d1==null){
                            d1 = 0d;
                        }

                        d2 = word2Map.get(feature);
                        if(d2==null){
                            d2 = 0d;
                        }

                        scoreNumerator += similarityMeasureFFunction(type, d1-vectorMean1)*similarityMeasureFFunction(type, d2-vectorMean2);

                        scoreDenominator1 += Math.pow(similarityMeasureFFunction(type, d1-vectorMean1), 2d);
                        scoreDenominator2 += Math.pow(similarityMeasureFFunction(type, d2-vectorMean2), 2d);

                    }

                }else{
                    System.out.println("No such SimilarityMeasure: " + similarityMeasure);
                    throw new RuntimeException();
                }

            }else if(similarityMeasureString.matches("PenroseShape(Mod_.*)?")){

                if(similarityMeasure==SimilarityMeasure.PenroseShape){

                    for(V feature: featureMap.keySet()){

                        d1 = word1Map.get(feature);
                        if(d1==null){
                            d1 = 0d;
                        }

                        d2 = word2Map.get(feature);
                        if(d2==null){
                            d2 = 0d;
                        }

                        scoreNumerator += Math.pow((d1-vectorMean1)-(d2-vectorMean2), 2d);

                    }

                }else if(similarityMeasureString.matches("PenroseShapeMod_(1|3)_.")){

                    int type = Integer.parseInt(similarityMeasureString.substring(similarityMeasureString.length()-1));

                    for(V feature: featureMap.keySet()){

                        d1 = word1Map.get(feature);
                        if(d1==null){
                            d1 = 0d;
                        }

                        d2 = word2Map.get(feature);
                        if(d2==null){
                            d2 = 0d;
                        }

                        scoreNumerator += similarityMeasureFFunction(type, Math.abs((d1-vectorMean1)-(d2-vectorMean2)));

                    }

                }else if(similarityMeasureString.matches("PenroseShapeMod_(2|4)_.")){

                    int type = Integer.parseInt(similarityMeasureString.substring(similarityMeasureString.length()-1));

                    for(V feature: featureMap.keySet()){

                        d1 = word1Map.get(feature);
                        if(d1==null){
                            d1 = 0d;
                        }

                        d2 = word2Map.get(feature);
                        if(d2==null){
                            d2 = 0d;
                        }

                        scoreNumerator += Math.abs(similarityMeasureFFunction(type, d1-vectorMean1)-similarityMeasureFFunction(type, d2-vectorMean2));

                    }

                }else{
                    System.out.println("No such SimilarityMeasure: " + similarityMeasure);
                    throw new RuntimeException();
                } 

            }else if(pearsCombPattern.matcher(similarityMeasureString).matches()){

                for(V feature: featureMap.keySet()){

                    d1 = word1Map.get(feature);
                    if(d1==null){
                        d1 = 0d;
                    }

                    d2 = word2Map.get(feature);
                    if(d2==null){
                        d2 = 0d;
                    }

                    scoreNumerator += (d1-vectorMean1)*(d2-vectorMean2);

                    scoreDenominator1 += Math.pow(d1-vectorMean1, 2d);
                    scoreDenominator2 += Math.pow(d2-vectorMean2, 2d);

                }

            }else if(pearsMbAdjCosModPattern.matcher(similarityMeasureString).matches() || pearsMbAdjCosPfModPattern.matcher(similarityMeasureString).matches() || 
                    pearsMbModPattern.matcher(similarityMeasureString).matches()){
                
                int type = Integer.parseInt(similarityMeasureString.substring(similarityMeasureString.length()-1));
                
                if(similarityMeasureString.matches("PearsMb(AdjCos(PF)?)?Mod_(1|4)_.")){

                    for(V feature: featureMap.keySet()){

                        d1 = word1Map.get(feature);
                        if(d1==null){
                            d1 = 0d;
                        }

                        d2 = word2Map.get(feature);
                        if(d2==null){
                            d2 = 0d;
                        }

                        scoreNumerator += similarityMeasureFFunction(type, d1-vectorMean1)*similarityMeasureFFunction(type, d2-vectorMean2);
                        
                        scoreDenominator1 += Math.pow(d1-vectorMean1, 2d);
                        scoreDenominator2 += Math.pow(d2-vectorMean2, 2d);

                    }
                    
                }else if(similarityMeasureString.matches("PearsMb(AdjCos(PF)?)?Mod_(2|3|5|6)_.")){
                    
                    for(V feature: featureMap.keySet()){

                        d1 = word1Map.get(feature);
                        if(d1==null){
                            d1 = 0d;
                        }

                        d2 = word2Map.get(feature);
                        if(d2==null){
                            d2 = 0d;
                        }

                        scoreNumerator += similarityMeasureFFunction(type, d1-vectorMean1)*similarityMeasureFFunction(type, d2-vectorMean2);
                        
                        scoreDenominator1 += Math.pow(similarityMeasureFFunction(type, d1-vectorMean1), 2d);
                        scoreDenominator2 += Math.pow(similarityMeasureFFunction(type, d2-vectorMean2), 2d);

                    }
                    
                }else{
                    System.out.println("No such SimilarityMeasure: " + similarityMeasure);
                    throw new RuntimeException();
                }

            }else{
                System.out.println("No such SimilarityMeasure: " + similarityMeasure);
                throw new RuntimeException();
            }
            
        }
        
        
        return new SimilarityScoreParts(scoreNumerator, scoreDenominator1, scoreDenominator2, null, null, null);
        
    }
    
    
    
    
    
    
    /**
     * Calculates a similarity numerator subscore based on two double values for the two word to be compared.
     * @param d1 the double value for the first word
     * @param d2 the double value for the second word
     * @param vectorMean1 the mean of the values in the feature vector of word1
     * @param vectorMean2 the mean of the values in the feature vector of word2
     * @param numberOfAllFeatures the number of all features of the given type
     * @return the similarity numerator subscore based on two double values
     */
    public static double calculateSimilarityNumaratorByRelationSubscore(double d1, double d2, Double vectorMean1, Double vectorMean2, long numberOfAllFeatures){
        
        double d;
        
        if(method==Method.Num){
            
            if(similarityMeasure==SimilarityMeasure.Cos || similarityMeasure==SimilarityMeasure.Dice_2 || 
                    similarityMeasure==SimilarityMeasure.Jaccard_1 || similarityMeasure==SimilarityMeasure.Jaccard_1Mod || 
                    similarityMeasure==SimilarityMeasure.Jaccard_2 || 
                    similarityMeasure==SimilarityMeasure.Tanimoto_2 || similarityMeasure==SimilarityMeasure.InnerProd || 
                    similarityMeasure.toString().matches("PseudoCos.*") || 
                    similarityMeasure==SimilarityMeasure.Mb || 
                    similarityMeasure==SimilarityMeasure.Simpson_1 || similarityMeasure==SimilarityMeasure.SmoothCos || 
                    similarityMeasure==SimilarityMeasure.AdjCos || 
                    similarityMeasure==SimilarityMeasure.NcdMod_2 || similarityMeasure==SimilarityMeasure.MbAdjCos || 
                    similarityMeasure==SimilarityMeasure.MbPFMod || similarityMeasure==SimilarityMeasure.MbAdjCosPFMod || 
                    similarityMeasure==SimilarityMeasure.AdjCosPFMod || similarityMeasureString.matches("Mb(Adj)?Cos((Am)|(Gm)|(Hm)|(Prod)|(LogProd))")){
                return d1*d2;
            }else if(similarityMeasure==SimilarityMeasure.Dice_1 || similarityMeasure==SimilarityMeasure.Dice_1Mod || 
                    similarityMeasure==SimilarityMeasure.Jaccard_3 || 
                    similarityMeasure==SimilarityMeasure.Overlap || similarityMeasure==SimilarityMeasure.Kulczynski){
                return Math.min(d1, d2);
            }else if(similarityMeasure==SimilarityMeasure.ZklMod){
                if(d1*d2>0d){
                    return d1*Math.log(d1/d2);
                }else{
                    return d1*zklGamma;
                }
            }else if(similarityMeasure==SimilarityMeasure.ZklModSym){
                d=0d;
                if(d1*d2>0d){
                    d+=d1*Math.log(d1/d2);
                }else{
                    d+=d1*zklGamma;
                }
                if(d2*d1>0d){
                    d+=d2*Math.log(d2/d1);
                }else{
                    d+=d2*zklGamma;
                }
                return d;
            }else if(similarityMeasure==SimilarityMeasure.L05){
                return Math.sqrt(Math.abs(d1-d2));
            }else if(similarityMeasure==SimilarityMeasure.L1 || similarityMeasure==SimilarityMeasure.AvgL1LInf || 
                    similarityMeasure==SimilarityMeasure.LInf || 
                    similarityMeasure==SimilarityMeasure.SorensenMod){
                return Math.abs(d1-d2);
            }else if(similarityMeasure==SimilarityMeasure.L2){
                return Math.pow(d1-d2, 2d);
            }else if(similarityMeasure==SimilarityMeasure.L3){
                return Math.pow(Math.abs(d1-d2), 3d);
            }else if(similarityMeasure==SimilarityMeasure.Canberra){
                d=Math.abs(d1)+Math.abs(d2);
                if(d!=0d){
                    return Math.abs(d1-d2)/(d);
                }else{
                    return 0d;
                }
            }else if(similarityMeasure==SimilarityMeasure.Lorentzian){
                return Math.log(1d+Math.abs(d1-d2));
            }else if(similarityMeasure==SimilarityMeasure.ASkewMod){
                if(d1!=0d && alphaSkewAlpha*d2+(1d-alphaSkewAlpha)*d1!=0d){
                    return Math.signum(d1-(alphaSkewAlpha*d2+(1d-alphaSkewAlpha)*d1))*Math.abs(d1*Math.log(Math.abs(d1/(alphaSkewAlpha*d2+(1d-alphaSkewAlpha)*d1))));
                }else{
                    return 0d;
                }
            }else if(similarityMeasure==SimilarityMeasure.ASkewModSym){
                d=0d;
                if(d1!=0d && alphaSkewAlpha*d2+(1d-alphaSkewAlpha)*d1!=0d){
                    d+=Math.signum(d1-(alphaSkewAlpha*d2+(1d-alphaSkewAlpha)*d1))*Math.abs(d1*Math.log(Math.abs(d1/(alphaSkewAlpha*d2+(1d-alphaSkewAlpha)*d1))));
                }
                if(d2!=0d && alphaSkewAlpha*d1+(1d-alphaSkewAlpha)*d2!=0d){
                    d+=Math.signum(d2-(alphaSkewAlpha*d1+(1d-alphaSkewAlpha)*d2))*Math.abs(d2*Math.log(Math.abs(d2/(alphaSkewAlpha*d1+(1d-alphaSkewAlpha)*d2))));
                }
                return d;
            }else if(similarityMeasure==SimilarityMeasure.JensenShannonMod){
                d=0d;
                if(d1!=0d && d1+d2!=0d){
                    d+=Math.signum(2*d1-(d1+d2))*Math.abs(d1*Math.log(Math.abs(2*d1/(d1+d2))));
                }
                if(d2!=0d && d1+d2!=0d){
                    d+=Math.signum(2*d2-(d1+d2))*Math.abs(d2*Math.log(Math.abs(2*d2/(d1+d2))));
                }
                return d;
            }else if(similarityMeasure==SimilarityMeasure.JensenMod){
                d=0d;
                if(d1!=0d){
                    d+=d1/2d*Math.log(Math.abs(d1));
                }
                if(d2!=0d){
                    d+=d2/2d*Math.log(Math.abs(d2));
                }
                if(d1+d2!=0d){
                    d-=((d1+d2)/2d)*Math.log(Math.abs((d1+d2)/2d));
                }
                return d;
            }else if(similarityMeasure==SimilarityMeasure.LinKiela){
                return d1+d2;
            }else if(similarityMeasure==SimilarityMeasure.Tanimoto_1 || similarityMeasure==SimilarityMeasure.Tanimoto_1Mod){
                return Math.max(d1, d2)-Math.min(d1, d2);
            }else if(similarityMeasure==SimilarityMeasure.HarmMeanMod){
                d=Math.abs(d1)+Math.abs(d2);
                if(d!=0d){
                    return d1*d2/d;
                }else{
                    return 0d;
                }
            }else if(similarityMeasure==SimilarityMeasure.FidelityMod){
                return Math.signum(d1*d2)*Math.sqrt(Math.abs(d1*d2));
            }else if(similarityMeasure==SimilarityMeasure.HellingerMod){
                return Math.pow(Math.signum(d1)*Math.sqrt(Math.abs(d1))-Math.signum(d2)*Math.sqrt(Math.abs(d2)), 2d);
            }else if(similarityMeasure==SimilarityMeasure.ChiSquareMod){
                if(d2!=0d){
                    return Math.pow(d1-d2, 2d)/Math.abs(d2);
                }else{
                    return 0d;
                }
            }else if(similarityMeasure==SimilarityMeasure.PsChiSquareMod){
                d=Math.abs(d1)+Math.abs(d2);
                if(d!=0d){
                    return Math.pow(d1-d2,2d)/d;
                }else{
                    return 0d;
                }
            }else if(similarityMeasure==SimilarityMeasure.ClarkMod){
                d=Math.abs(d1)+Math.abs(d2);
                if(d!=0d){
                    return Math.pow(Math.abs(d1-d2)/d,2d);
                }else{
                    return 0d;
                }
            }else if(similarityMeasure==SimilarityMeasure.RenyiDivMod2){
                if(d2!=0d){
                    return Math.pow(d1,2d)/Math.abs(d2);
                }else{
                    return 0d;
                }
            }else if(similarityMeasure==SimilarityMeasure.RenyiDivModInf){
                if(d2!=0d){
                    return Math.abs(d1/d2);
                }else{
                    return 0d;
                }
            }else if(similarityMeasure==SimilarityMeasure.StdLike_1){
                double mean = (d1+d2)/2d;
                return Math.sqrt(Math.abs(d1-mean))+Math.sqrt(Math.abs(d2-mean));
            }else if(similarityMeasure==SimilarityMeasure.StdLike_2){
                double mean = (d1+d2)/2d;
                return Math.pow(d1-mean,2d)+Math.pow(d2-mean,2d);
            }else if(similarityMeasure==SimilarityMeasure.StdLike_3){
                double mean = (d1+d2)/2d;
                return Math.pow(Math.abs(d1-mean),3d)+Math.pow(Math.abs(d2-mean),3d);
            }else if(similarityMeasure==SimilarityMeasure.StdLike_4){
                double mean = (d1+d2)/2d;
                return lb(Math.abs(d1-mean)+1d)+lb(Math.abs(d2-mean)+1d);
            }else if(similarityMeasure==SimilarityMeasure.StdLike_5){
                double mean = (d1+d2)/2d;
                return 1d/(2d+Math.expm1(-Math.abs(d1-mean)))-0.5d+1d/(2d+Math.expm1(-Math.abs(d2-mean)))-0.5d;
            }else if(similarityMeasure==SimilarityMeasure.Lsmq){
                if(d2!=0d){
                    return Math.abs(d1/d2)-1d;
                }else{
                    return 0d-1d;
                }
            }else if(similarityMeasure==SimilarityMeasure.Srsn){
                d = Math.abs(d1-d2);
                if(d!=0d){
                    return Math.min(d1, d2)/d;
                }else{
                    return 0d;
                }
            }else if(similarityMeasure==SimilarityMeasure.Rsssn){
                return Math.min(d1, d2);
            }else if(similarityMeasure==SimilarityMeasure.Sdsn){
                return Math.min(d1, d2)-Math.abs(d1-d2);
            }else if(similarityMeasure==SimilarityMeasure.Srsmv){
                d = Math.max(d1, d2);
                if(d!=0d){
                    return Math.min(d1, d2)/d;
                }else{
                    return 0d;
                }
            }else if(similarityMeasure==SimilarityMeasure.Srsm){
                d = (d1+d2)/2d;
                if(d!=0d){
                    return Math.min(d1, d2)/d;
                }else{
                    return 0d;
                }
            }else if(similarityMeasure==SimilarityMeasure.TanejaMod){
                if(d1+d2!=0d && d1*d2!=0d){
                    return (d1+d2)/2d*Math.log(Math.abs((d1+d2)/(2*Math.sqrt(Math.abs(d1*d2)))));
                }else{
                    return 0d;
                }
            }else if(similarityMeasure==SimilarityMeasure.KumarJohnsonMod){
                if(d1*d2!=0d){
                    return Math.pow(Math.signum(d1)*Math.pow(d1, 2d)-Math.signum(d2)*Math.pow(d2, 2d), 2d)/(2*Math.pow(Math.abs(d1*d2), 1.5d));
                }else{
                    return 0d;
                }
            }else if(similarityMeasure==SimilarityMeasure.VicWhMod){
                if(Math.min(d1,d2)!=0d){
                    return Math.abs(d1-d2)/Math.abs(Math.min(d1,d2));
                }else{
                    return 0d;
                }
            }else if(similarityMeasure==SimilarityMeasure.VicSymChiSqMod1){
                if(Math.min(d1,d2)!=0d){
                    return Math.pow((d1-d2)/Math.min(d1,d2),2d);
                }else{
                    return 0d;
                }
            }else if(similarityMeasure==SimilarityMeasure.VicSymChiSqMod2){
                if(Math.min(d1,d2)!=0d){
                    return Math.pow(d1-d2,2d)/Math.abs(Math.min(d1,d2));
                }else{
                    return 0d;
                }
            }else if(similarityMeasure==SimilarityMeasure.VicSymChiSqMod3){
                if(Math.max(d1,d2)!=0d){
                    return Math.pow(d1-d2,2d)/Math.abs(Math.max(d1,d2));
                }else{
                    return 0d;
                }
            }else if(similarityMeasure==SimilarityMeasure.MaxSymChiSqMod){
                if(d1!=0d){
                    return Math.pow(d1-d2,2d)/Math.abs(d1);
                }else{
                    return 0d;
                }
            }else if(similarityMeasure==SimilarityMeasure.Rms){
                return Math.sqrt((Math.pow(d1, 2d)+Math.pow(d2, 2d))/2d);
            }else if(similarityMeasure==SimilarityMeasure.ContraHMeanMod){
                d=Math.abs(d1)+Math.abs(d2);
                if(d!=0d){
                    return (Math.pow(d1, 2d)+Math.pow(d2, 2d))/d;
                }else{
                    return 0d;
                }
            }else if(similarityMeasure==SimilarityMeasure.Spline){
                return d1*d2+d1*d2*Math.min(d1,d2)-(d1+d2)/2d*Math.pow(Math.min(d1,d2),2d)+Math.pow(Math.min(d1,d2),3d)/3d;
            }else if(similarityMeasure==SimilarityMeasure.RobersMod){
                if(d1!=0d && d2!=0d){
                    return (d1+d2)*Math.min(d1,d2)/Math.max(d1,d2);
                }else{
                    return 0d;
                }
            }else if(similarityMeasure==SimilarityMeasure.Simpson_2Mod){
                return Math.signum(d1*d2)*lb(Math.abs(d1*d2)+1d);
            }else if(similarityMeasure==SimilarityMeasure.MahalanobisMod){
                d = Math.abs((d1-vectorMean1)*(d2-vectorMean2));
                if(d!=0d){
                    return Math.pow(d1-d2, 2d)/d;
                }else{
                    return 0d;
                }
            }else if(similarityMeasure==SimilarityMeasure.Multiplicative){
                return 1d+Math.abs(d1-d2);
            }else if(similarityMeasure==SimilarityMeasure.MultiplicativeMod1){
                return Math.pow(1d+Math.abs(d1-d2), 0.1d);
            }else if(similarityMeasure==SimilarityMeasure.MultiplicativeMod2){
                return lb(1d+Math.abs(d1-d2));
            }else if(similarityMeasure==SimilarityMeasure.NormCosMod){
                return Math.signum(d1*d2)*Math.pow(Math.abs(d1*d2), normCosGamma);
            }else if(similarityMeasure==SimilarityMeasure.SocPmiMod){
                if(d1>0d && d2>0d){
                    return Math.pow(d1, SocPmiGamma);
                }else{
                    return 0d;
                }
            }else if(similarityMeasure==SimilarityMeasure.NormModSocPmiMod){
                if(d1>0d && d2>0d){
                    return Math.pow(d1, normModSocPmiGamma);
                }else{
                    return 0d;
                }
            }else if(similarityMeasure==SimilarityMeasure.ChenCorr){
                d = d1+d2-d1*d2;
                if(d == 0d){
                    return 0d;
                }else{
                    return d1*d2/d;
                }
            }else if(similarityMeasure==SimilarityMeasure.NcdMod_1){
                d = Math.max(d1,d2);
                if(d == 0d){
                    return 0d;
                }else{
                    return (d1*d2-Math.min(d1,d2))/d;
                }
            }else if(similarityMeasure==SimilarityMeasure.NgdMod){
                return (Math.max(Math.signum(d1)*lb(Math.abs(d1)+1d), Math.signum(d2)*lb(Math.abs(d2)+1d))-Math.signum(d1*d2)*lb(Math.abs(d1*d2)+1d))/(lb(numberOfAllFeatures+1d)-Math.min(Math.signum(d1)*lb(Math.abs(d1)+1d), Math.signum(d2)*lb(Math.abs(d2)+1d)));
            }else if(similarityMeasure==SimilarityMeasure.ApSyn){
                if(d1!=0 && d2!=0){
                    return 2d/(d1+d2);
                }else{
                    return 0;
                }
            }else if(similarityMeasure==SimilarityMeasure.ApSynP){
                if(d1!=0 && d2!=0){
                    return 2d/(Math.pow(d1, apSynPP)+Math.pow(d2, apSynPP));
                }else{
                    return 0;
                }
            }else if(similarityMeasure==SimilarityMeasure.Wo){
                if(d1!=0 && d2!=0){
                    return 1d/(d1+d2);
                }else{
                    return 0;
                }
            }else if(cosModPattern.matcher(similarityMeasureString).matches()){
                int type = Integer.parseInt(similarityMeasureString.substring(similarityMeasureString.length()-1));
                return similarityMeasureFFunction(type, d1)*similarityMeasureFFunction(type, d2);
            }else if(mbModPattern.matcher(similarityMeasureString).matches()){
                int type = Integer.parseInt(similarityMeasureString.substring(similarityMeasureString.length()-1));
                return similarityMeasureFFunction(type, d1)*similarityMeasureFFunction(type, d2);
            }else if(adjCosModPattern.matcher(similarityMeasureString).matches()){
                int type = Integer.parseInt(similarityMeasureString.substring(similarityMeasureString.length()-1));
                return similarityMeasureFFunction(type, d1)*similarityMeasureFFunction(type, d2);
            }else if(pfModPattern.matcher(similarityMeasureString).matches()){
                int type = Integer.parseInt(similarityMeasureString.substring(similarityMeasureString.length()-1));
                return similarityMeasureFFunction(type, d1)*similarityMeasureFFunction(type, d2);
            }else if(lModPattern.matcher(similarityMeasureString).matches()){
                int type = Integer.parseInt(similarityMeasureString.substring(similarityMeasureString.length()-1));
                return similarityMeasureFFunction(type, Math.abs(d1-d2));
            }else if(lWPattern.matcher(similarityMeasureString).matches()){
                if((Math.abs(d1)+Math.abs(d2))!=0d){
                    int type = Integer.parseInt(similarityMeasureString.substring(similarityMeasureString.length()-1));
                    if(similarityMeasureString.matches("LW_1.*")){
                        return similarityMeasureFFunction(type, Math.abs(d1-d2))/(Math.abs(d1)+Math.abs(d2));
                    }else if(similarityMeasureString.matches("LW_2.*")){
                        return similarityMeasureFFunction(type, Math.abs(d1-d2))/(similarityMeasureFFunction(type, Math.abs(d1))+similarityMeasureFFunction(type, Math.abs(d2)));
                    }else{
                        System.out.println("No such SimilarityMeasure: " + similarityMeasure);
                        throw new RuntimeException();
                    }
                }else{
                    return 0d;
                }
            }else if(innerProdWPattern.matcher(similarityMeasureString).matches()){
                int type = Integer.parseInt(similarityMeasureString.substring(similarityMeasureString.length()-1));
                if(similarityMeasureString.matches("InnerProdW_(1|3)_.")){
                    d = (Math.abs(d1)+Math.abs(d2));
                    if(d == 0d){
                        return 0d;
                    }else{
                        return similarityMeasureFFunction(type, d1)*similarityMeasureFFunction(type, d2)/d;
                    }
                }else if(similarityMeasureString.matches("InnerProdW_(2|4)_.")){
                    double fd1 = similarityMeasureFFunction(type, d1);
                    double fd2 = similarityMeasureFFunction(type, d2);
                    d = (Math.abs(fd1)+Math.abs(fd2));
                    if(d == 0d){
                        return 0d;
                    }else{
                        return fd1*fd2/d;
                    }
                }else{
                    System.out.println("No such SimilarityMeasure: " + similarityMeasure);
                    throw new RuntimeException();
                }
            }else if(dtvPattern.matcher(similarityMeasureString).matches()){
                if(similarityMeasureString.matches("Dtv_(1|2|3|4|5|6|7).*")){
                    int type = Integer.parseInt(similarityMeasureString.substring(similarityMeasureString.length()-1));
                    return Math.abs(similarityMeasureFFunction(type, d1)-similarityMeasureFFunction(type, d2));
                }else if(similarityMeasureString.matches("Dtv_(8|9|(10)|(11)|(12)|(13)|(14)).*")){
                    int type = Integer.parseInt(similarityMeasureString.substring(similarityMeasureString.length()-1));
                    return similarityMeasureFInverseFunction(type, Math.abs(similarityMeasureFFunction(type, d1)-similarityMeasureFFunction(type, d2)), numberOfAllFeatures);
                }else{
                    System.out.println("No such SimilarityMeasure: " + similarityMeasure);
                    throw new RuntimeException();
                }
            }else if(dtvWPattern.matcher(similarityMeasureString).matches()){
                if((Math.abs(d1)+Math.abs(d2))!=0d){
                    int type = Integer.parseInt(similarityMeasureString.substring(similarityMeasureString.length()-1));
                    if(similarityMeasureString.matches("DtvW_1.*")){
                        return Math.abs(similarityMeasureFFunction(type, d1)-similarityMeasureFFunction(type, d2))/
                                (Math.abs(d1)+Math.abs(d2));
                    }else if(similarityMeasureString.matches("DtvW_2.*")){
                        return Math.abs(similarityMeasureFFunction(type, d1)-similarityMeasureFFunction(type, d2))/
                                (similarityMeasureFFunction(type, Math.abs(d1))+similarityMeasureFFunction(type, Math.abs(d2)));
                    }else if(similarityMeasureString.matches("DtvW_3.*")){
                        return similarityMeasureFInverseFunction(type, Math.abs(similarityMeasureFFunction(type, d1)-similarityMeasureFFunction(type, d2)), numberOfAllFeatures)/
                                (Math.abs(d1)+Math.abs(d2));
                    }else if(similarityMeasureString.matches("DtvW_4.*")){
                        return similarityMeasureFInverseFunction(type, Math.abs(similarityMeasureFFunction(type, d1)-similarityMeasureFFunction(type, d2)), numberOfAllFeatures)/
                                (similarityMeasureFFunction(type, Math.abs(d1))+similarityMeasureFFunction(type, Math.abs(d2)));
                    }else{
                        System.out.println("No such SimilarityMeasure: " + similarityMeasure);
                        throw new RuntimeException();
                    }
                }else{
                    return 0d;
                }
            }else if(similarityMeasure==SimilarityMeasure.Lin){
                if(d1!=0d && d2!=0d){
                    return d1+d2;
                }else{
                    return 0d;
                }
            }else if(linModPattern.matcher(similarityMeasureString).matches()){
                boolean b;
                if(similarityMeasureString.substring(similarityMeasureString.length()-5, similarityMeasureString.length()-4).equals("1")){
                    b = d1!=0d && d2!=0d;
                }else{
                    b = d1>0d && d2>0d;
                }
                if(b){
                    int type = Integer.parseInt(similarityMeasureString.substring(similarityMeasureString.length()-1));
                    return similarityMeasureFFunction(type, d1)+similarityMeasureFFunction(type, d2);
                }else{
                    return 0d;
                }
            }else if(linHindleRModPattern.matcher(similarityMeasureString).matches()){
                boolean b;
                if(similarityMeasureString.substring(similarityMeasureString.length()-5, similarityMeasureString.length()-4).equals("1")){
                    b = d1!=0d && d2!=0d;
                }else{
                    b = d1>0d && d2>0d;
                }
                if(b){
                    int type = Integer.parseInt(similarityMeasureString.substring(similarityMeasureString.length()-1));
                    return Math.min(similarityMeasureFFunction(type, d1),similarityMeasureFFunction(type, d2));
                }else{
                    return 0d;
                }
            }else{
                System.out.println("No such SimilarityMeasure: " + similarityMeasure);
                throw new RuntimeException();
            }
            
        }else{
            System.out.println("The function calculateSimilarityNumaratorByRelationSubscore should only be called in case of numerical vectors");
            throw new RuntimeException();
        }
        
    }
    
    
    
    /**
     * Calculates a similarity denominator1 subscore based on two double values for the two word to be compared.
     * @param d1 the double value for the first word
     * @param d2 the double value for the second word
     * @param vectorMean1 the mean of the values in the feature vector of word1
     * @param vectorMean2 the mean of the values in the feature vector of word2
     * @return the similarity denominator1 subscore based on two double values
     */
    public static double calculateSimilarityDenominator1ByRelationSubscore(double d1, double d2, Double vectorMean1, Double vectorMean2){
        
        if(method==Method.Num){
        
            if(similarityMeasure==SimilarityMeasure.Jaccard_3 || similarityMeasure==SimilarityMeasure.Tanimoto_1){

                return Math.max(d1, d2);

            }else if(similarityMeasure==SimilarityMeasure.Tanimoto_1Mod){

                return Math.abs(Math.max(d1, d2));

            }else if(similarityMeasure==SimilarityMeasure.AvgL1LInf || similarityMeasure==SimilarityMeasure.Rsssn || 
                    similarityMeasure==SimilarityMeasure.Kulczynski){
                return Math.abs(d1-d2);
            }else if(similarityMeasure==SimilarityMeasure.MaxSymChiSqMod){
                if(d2!=0d){
                    return Math.pow(d1-d2,2d)/Math.abs(d2);
                }else{
                    return 0d;
                }
            }else if(similarityMeasure==SimilarityMeasure.Simpson_2Mod){
                return lb(Math.abs(d1)+1d);
            }else if(similarityMeasure==SimilarityMeasure.NormCosMod){
                return Math.pow(Math.abs(d1), 2d*normCosGamma);
            }else if(similarityMeasure==SimilarityMeasure.SocPmiMod){
                if(d1>0d && d2>0d){
                    return Math.pow(d2, SocPmiGamma);
                }else{
                    return 0d;
                }
            }else if(similarityMeasure==SimilarityMeasure.NormModSocPmiMod){
                if(d1>0d && d2>0d){
                    return Math.pow(d2, normModSocPmiGamma);
                }else{
                    return 0d;
                }
            }else if(similarityMeasure==SimilarityMeasure.NcdMod_2){
                return Math.min(d1,d2);
            }else if(similarityMeasure==SimilarityMeasure.Wo){
                if(d1!=0 && d2!=0){
                    return 1d;
                }else{
                    return 0;
                }
            }else if(cosModPattern.matcher(similarityMeasureString).matches()){
                int type = Integer.parseInt(similarityMeasureString.substring(similarityMeasureString.length()-1));
                return Math.pow(similarityMeasureFFunction(type, d1), 2d);
            }else if(mbModPattern.matcher(similarityMeasureString).matches()){
                int type = Integer.parseInt(similarityMeasureString.substring(similarityMeasureString.length()-1));
                return Math.pow(similarityMeasureFFunction(type, d1), 2d);
            }else if(adjCosModPattern.matcher(similarityMeasureString).matches()){
                int type = Integer.parseInt(similarityMeasureString.substring(similarityMeasureString.length()-1));
                return Math.pow(similarityMeasureFFunction(type, d1), 2d);
            }else if(pfModPattern.matcher(similarityMeasureString).matches()){
                int type = Integer.parseInt(similarityMeasureString.substring(similarityMeasureString.length()-1));
                return Math.pow(similarityMeasureFFunction(type, d1), 2d);
            }else if(dtvPattern.matcher(similarityMeasureString).matches()){
                if(similarityMeasureString.matches("Dtv_(2|3|9|(10)).*")){

                    int type = Integer.parseInt(similarityMeasureString.substring(similarityMeasureString.length()-1));

                    return similarityMeasureFFunction(type, Math.abs(d1));

                }else if(similarityMeasureString.matches("Dtv_(4|5|(11)|(12)).*")){

                    int type = Integer.parseInt(similarityMeasureString.substring(similarityMeasureString.length()-1));

                    return Math.pow(similarityMeasureFFunction(type, d1), 2d);
                }else{
                    System.out.println("No such SimilarityMeasure: " + similarityMeasure);
                    throw new RuntimeException();
                }
            }else if(similarityMeasureString.matches("LMod_(4|5|6|7|(10)|(11)|(12)|(13)|(14)|(15)).*")){

                int type = Integer.parseInt(similarityMeasureString.substring(similarityMeasureString.length()-1));
                double d = similarityMeasureFFunction(type, d1);

                if(similarityMeasureString.matches("LMod_(4|5|(10)|(11)).*")){
                    d=Math.pow(d, 2d);
                }else if(similarityMeasureString.matches("LMod_(6|7|(12)|(13)).*")){
                    d=Math.abs(d);
                }

                return d;

            }else if(similarityMeasureString.matches("Lin(HindleR)?Mod_(2|3|4|5|8|9)_(1|2).*")){

                int type = Integer.parseInt(similarityMeasureString.substring(similarityMeasureString.length()-1));
                
                double d = similarityMeasureFFunction(type, d1);

                if(similarityMeasureString.matches("Lin(HindleR)?Mod_(2|3)_(1|2).*")){
                    d=Math.abs(d);
                }else if(similarityMeasureString.matches("Lin(HindleR)?Mod_(4|5)_(1|2).*")){
                    d=Math.pow(d, 2d);
                }

                return d;

            }else{
                System.out.println("The function calculateSimilarityDenominator1ByRelationSubscore cannot be called for this type of NUM SimilarityMeasure: " + similarityMeasure);
                throw new RuntimeException();
            }
        
        }else{
            System.out.println("The function calculateSimilarityDenominator1ByRelationSubscore should only be called in case of numerical vectors");
            throw new RuntimeException();
        }
        
    }
    
    
    
    
    
    /**
     * Calculates a similarity denominator2 subscore based on two double values for the two word to be compared.
     * @param d1 the double value for the first word
     * @param d2 the double value for the second word
     * @param vectorMean1 the mean of the values in the feature vector of word1
     * @param vectorMean2 the mean of the values in the feature vector of word2
     * @return the similarity denominator1 subscore based on two double values
     */
    public static double calculateSimilarityDenominator2ByRelationSubscore(double d1, double d2, Double vectorMean1, Double vectorMean2){
        
        if(method==Method.Num){
        
            if(similarityMeasure==SimilarityMeasure.Simpson_2Mod){
                return lb(Math.abs(d2)+1d);
            }else if(similarityMeasure==SimilarityMeasure.NormCosMod){
                return Math.pow(Math.abs(d2), 2d*normCosGamma);
            }else if(similarityMeasure==SimilarityMeasure.NcdMod_2){
                return Math.max(d1,d2);
            }else if(cosModPattern.matcher(similarityMeasureString).matches()){
                int type = Integer.parseInt(similarityMeasureString.substring(similarityMeasureString.length()-1));
                return Math.pow(similarityMeasureFFunction(type, d2), 2d);
            }else if(mbModPattern.matcher(similarityMeasureString).matches()){
                int type = Integer.parseInt(similarityMeasureString.substring(similarityMeasureString.length()-1));
                return Math.pow(similarityMeasureFFunction(type, d2), 2d);
            }else if(adjCosModPattern.matcher(similarityMeasureString).matches()){
                int type = Integer.parseInt(similarityMeasureString.substring(similarityMeasureString.length()-1));
                return Math.pow(similarityMeasureFFunction(type, d2), 2d);
            }else if(pfModPattern.matcher(similarityMeasureString).matches()){
                int type = Integer.parseInt(similarityMeasureString.substring(similarityMeasureString.length()-1));
                return Math.pow(similarityMeasureFFunction(type, d2), 2d);
            }else if(dtvPattern.matcher(similarityMeasureString).matches()){
                if(similarityMeasureString.matches("Dtv_(2|3|9|(10)).*")){

                    int type = Integer.parseInt(similarityMeasureString.substring(similarityMeasureString.length()-1));

                    return similarityMeasureFFunction(type, Math.abs(d2));

                }else if(similarityMeasureString.matches("Dtv_(4|5|(11)|(12)).*")){

                    int type = Integer.parseInt(similarityMeasureString.substring(similarityMeasureString.length()-1));

                    return Math.pow(similarityMeasureFFunction(type, d2), 2d);
                }else{
                    System.out.println("No such SimilarityMeasure: " + similarityMeasure);
                    throw new RuntimeException();
                }
            }else if(similarityMeasureString.matches("LMod_(4|5|6|7|(10)|(11)|(12)|(13)|(14)|(15)).*")){

                int type = Integer.parseInt(similarityMeasureString.substring(similarityMeasureString.length()-1));
                double d = similarityMeasureFFunction(type, d2);

                if(similarityMeasureString.matches("LMod_(4|5|(10)|(11)).*")){
                    d=Math.pow(d, 2d);
                }else if(similarityMeasureString.matches("LMod_(6|7|(12)|(13)).*")){
                    d=Math.abs(d);
                }

                return d;

            }else if(similarityMeasureString.matches("Lin(HindleR)?Mod_(2|3|4|5|8|9)_(1|2).*")){

                int type = Integer.parseInt(similarityMeasureString.substring(similarityMeasureString.length()-1));
                
                double d = similarityMeasureFFunction(type, d2);

                if(similarityMeasureString.matches("Lin(HindleR)?Mod_(2|3)_(1|2).*")){
                    d=Math.abs(d);
                }else if(similarityMeasureString.matches("Lin(HindleR)?Mod_(4|5)_(1|2).*")){
                    d=Math.pow(d, 2d);
                }

                return d;

            }else{
                System.out.println("The function calculateSimilarityDenominator2ByRelationSubscore cannot be called for this type of NUM SimilarityMeasure: " + similarityMeasure);
                throw new RuntimeException();
            }
        
        }else{
            System.out.println("The function calculateSimilarityDenominator2ByRelationSubscore should only be called in case of numerical vectors");
            throw new RuntimeException();
        }
        
    }
    
    
    

    /**
     * Calculates the final similarity score for two words based on the similarity score parts, the length of the vectors and the vector element sums.
     * @param word1 the first word
     * @param word2 the second word
     * @param similarityScoreParts1 the similarity score parts for the first type of relation
     * @param similarityScoreParts2 the similarity score parts for the second type of relation
     * @param similarityScoreParts3 the similarity score parts for the third type of relation
     * @param similarityScoreParts4 the similarity score parts for the fourth type of relation
     * @param wordFrequencies the map containing the frequency of words of a given POS
     * @param vectorElementSums a map, in which the sum of the elements in feature vectors of a given type are stored
     * @param vectorElementAbsValueSums a map, in which the sum of the absolute value of the elements in feature vectors of a given type are stored
     * @param vectorLengthSquares a map, in which the square of the length of feature vectors of a given type are stored
     * @param numberOfAllFeatures the number of all features of the given type
     * @param allWordTypeCount the number of all words with the given POS, for which a word vector in the extracted information exists
     * @return the final similarity score for two words
     */
    public static double calculateFinalSimilarityScore(String word1, String word2, SimilarityScoreParts similarityScoreParts1, 
            SimilarityScoreParts similarityScoreParts2, SimilarityScoreParts similarityScoreParts3, 
            SimilarityScoreParts similarityScoreParts4, HashMap<String, Double> wordFrequencies, HashMap<String, Double> vectorElementSums, 
            HashMap<String, Double> vectorElementAbsValueSums, HashMap<String, Double> vectorLengthSquares, long numberOfAllFeatures, 
            long allWordTypeCount){
        
        Double d=null;

        //The Lin or the numerical similarity score is normalized.
        if(method==Method.Lin){
            
            double linIntersect = similarityScoreParts1.linIntersect+similarityScoreParts2.linIntersect+similarityScoreParts3.linIntersect+similarityScoreParts4.linIntersect;
            double lin1 = similarityScoreParts1.lin1+similarityScoreParts2.lin1+similarityScoreParts3.lin1+similarityScoreParts4.lin1;
            double lin2 = similarityScoreParts1.lin2+similarityScoreParts2.lin2+similarityScoreParts3.lin2+similarityScoreParts4.lin2;
            
            double div = lin1+lin2;

            if(div == 0){
                d = 0d;
            }else{
                d = linIntersect*2/div;
            }

        }else{
            
            Double numerator;
            if(similarityMeasure==SimilarityMeasure.LInf || similarityMeasure==SimilarityMeasure.RenyiDivModInf){
                numerator = Math.max(Math.max(similarityScoreParts1.numerator,similarityScoreParts2.numerator),Math.max(similarityScoreParts3.numerator,similarityScoreParts4.numerator));
            }else if(similarityMeasureString.matches("Multiplicative.*")){
                numerator = similarityScoreParts1.numerator*similarityScoreParts2.numerator*similarityScoreParts3.numerator*similarityScoreParts4.numerator;
            }else{
                numerator = similarityScoreParts1.numerator+similarityScoreParts2.numerator+similarityScoreParts3.numerator+similarityScoreParts4.numerator;
            }
            
            Double denominator1 = null;
            Double denominator2 = null;
            if(similarityMeasure==SimilarityMeasure.Jaccard_3 || similarityMeasure==SimilarityMeasure.Tanimoto_1 || 
                    similarityMeasure==SimilarityMeasure.Tanimoto_1Mod || 
                    similarityMeasure==SimilarityMeasure.Rsssn || similarityMeasure==SimilarityMeasure.MaxSymChiSqMod || 
                    similarityMeasure==SimilarityMeasure.Kulczynski || similarityMeasureString.matches("(NormMod)?SocPmiMod") || 
                    similarityMeasure==SimilarityMeasure.Wo){
                denominator1 = similarityScoreParts1.denominator1+similarityScoreParts2.denominator1+similarityScoreParts3.denominator1+similarityScoreParts4.denominator1;
            }else if(similarityMeasure==SimilarityMeasure.Simpson_2Mod || 
                    similarityMeasure==SimilarityMeasure.NormCosMod || similarityMeasure==SimilarityMeasure.NcdMod_2 || pearsModPattern.matcher(similarityMeasureString).matches() || 
                    similarityMeasureString.matches("(((Adj)?Cos)|(Mb)|(PF))Mod_(2|3|5|6)_.") || 
                    similarityMeasureString.matches("LMod_(4|5|6|7|(10)|(11)|(12)|(13)|(14)|(15)).*") || 
                    similarityMeasureString.matches("Dtv_(2|3|4|5|9|(10)|(11)|(12)).*") || similarityMeasureString.matches("Lin(HindleR)?Mod_(2|3|4|5|8|9)_(1|2).*") || 
                    pearsCombPattern.matcher(similarityMeasureString).matches() || pearsMbAdjCosModPattern.matcher(similarityMeasureString).matches() || 
                    pearsMbAdjCosPfModPattern.matcher(similarityMeasureString).matches() || pearsMbModPattern.matcher(similarityMeasureString).matches()){
                denominator1 = similarityScoreParts1.denominator1+similarityScoreParts2.denominator1+similarityScoreParts3.denominator1+similarityScoreParts4.denominator1;
                denominator2 = similarityScoreParts1.denominator2+similarityScoreParts2.denominator2+similarityScoreParts3.denominator2+similarityScoreParts4.denominator2;
            }else if(similarityMeasure==SimilarityMeasure.AvgL1LInf){
                denominator1 = Math.max(Math.max(similarityScoreParts1.denominator1,similarityScoreParts2.denominator1),Math.max(similarityScoreParts3.denominator1,similarityScoreParts4.denominator1));
            }


            if(similarityMeasure==SimilarityMeasure.Cos){
                d = numerator/(Math.sqrt(vectorLengthSquares.get(word1))*Math.sqrt(vectorLengthSquares.get(word2)));
            }else if(similarityMeasure==SimilarityMeasure.Dice_2){
                d = 2d*numerator/(vectorLengthSquares.get(word1)+vectorLengthSquares.get(word2));
            }else if(similarityMeasure==SimilarityMeasure.Dice_1){
                double div = vectorElementSums.get(word1)+vectorElementSums.get(word2);
                if(div == 0d){
                    d = 0d;
                }else{
                    d = 2d*numerator/div;
                }
            }else if(similarityMeasure==SimilarityMeasure.Dice_1Mod){
                d = 2d*numerator/(vectorElementAbsValueSums.get(word1)+vectorElementAbsValueSums.get(word2));
            }else if(similarityMeasure==SimilarityMeasure.ZklMod || similarityMeasure==SimilarityMeasure.ZklModSym || similarityMeasure==SimilarityMeasure.LInf || 
                    similarityMeasure==SimilarityMeasure.Canberra || similarityMeasure==SimilarityMeasure.Lorentzian || 
                    similarityMeasure==SimilarityMeasure.ASkewMod || similarityMeasure==SimilarityMeasure.ASkewModSym || similarityMeasure==SimilarityMeasure.JensenMod || 
                    similarityMeasure==SimilarityMeasure.ChiSquareMod || similarityMeasure==SimilarityMeasure.TanejaMod || similarityMeasure==SimilarityMeasure.KumarJohnsonMod || 
                    similarityMeasure==SimilarityMeasure.VicWhMod || similarityMeasure==SimilarityMeasure.VicSymChiSqMod1 || 
                    similarityMeasure==SimilarityMeasure.VicSymChiSqMod2 || similarityMeasure==SimilarityMeasure.VicSymChiSqMod3 || 
                    similarityMeasure==SimilarityMeasure.NcdMod_1 || similarityMeasure==SimilarityMeasure.NgdMod){
                d = distanceToSimilarity(numerator);
            }else if(similarityMeasure==SimilarityMeasure.L05 || similarityMeasure==SimilarityMeasure.L1 || 
                    similarityMeasure==SimilarityMeasure.L2 || similarityMeasure==SimilarityMeasure.L3){
                
                if(similarityMeasure==SimilarityMeasure.L05){
                    numerator=Math.pow(numerator, 2d);
                }else if(similarityMeasure==SimilarityMeasure.L2){
                    numerator=Math.sqrt(numerator);
                }else if(similarityMeasure==SimilarityMeasure.L3){
                    numerator=Math.cbrt(numerator);
                }
                
                d = distanceToSimilarity(numerator);
                
            }else if(similarityMeasure==SimilarityMeasure.Jaccard_1 || similarityMeasure==SimilarityMeasure.Lin){
                double div = vectorElementSums.get(word1)+vectorElementSums.get(word2);
                if(div == 0d){
                    d = 0d;
                }else{
                    d = numerator/div;
                }
            }else if(similarityMeasure==SimilarityMeasure.Jaccard_1Mod){
                d = numerator/(vectorElementAbsValueSums.get(word1)+vectorElementAbsValueSums.get(word2));
            }else if(similarityMeasure==SimilarityMeasure.Jaccard_2){
                d = numerator/(vectorLengthSquares.get(word1)+vectorLengthSquares.get(word2)-numerator);
            }else if(similarityMeasure==SimilarityMeasure.Jaccard_3){
                d = numerator/denominator1;
            }else if(similarityMeasure==SimilarityMeasure.Rsssn || similarityMeasure==SimilarityMeasure.Kulczynski){
                if(denominator1 == 0d){
                    denominator1 = 0.000001d;
                }
                d = numerator/denominator1;
            }else if(similarityMeasure==SimilarityMeasure.JensenShannonMod){
                d = distanceToSimilarity(numerator/2d);
            }else if(similarityMeasure==SimilarityMeasure.LinKiela){
                d = numerator/(Math.sqrt(vectorLengthSquares.get(word1))+Math.sqrt(vectorLengthSquares.get(word2)));
            }else if(similarityMeasure==SimilarityMeasure.Tanimoto_1 || similarityMeasure==SimilarityMeasure.Tanimoto_1Mod){
                d = distanceToSimilarity(numerator/denominator1);
            }else if(similarityMeasure==SimilarityMeasure.Tanimoto_2){
                d = numerator/(Math.sqrt(vectorLengthSquares.get(word1))+Math.sqrt(vectorLengthSquares.get(word2))-numerator);
            }else if(similarityMeasure==SimilarityMeasure.HarmMeanMod){
                d = 2d*numerator;
            }else if(similarityMeasure==SimilarityMeasure.FidelityMod || similarityMeasure==SimilarityMeasure.InnerProd || 
                    similarityMeasure==SimilarityMeasure.Srsn || 
                    similarityMeasure==SimilarityMeasure.Sdsn || similarityMeasure==SimilarityMeasure.Srsmv || 
                    similarityMeasure==SimilarityMeasure.Srsm || 
                    similarityMeasure==SimilarityMeasure.Rms || similarityMeasure==SimilarityMeasure.ContraHMeanMod || 
                    similarityMeasure==SimilarityMeasure.ApSyn || 
                    similarityMeasure==SimilarityMeasure.ApSynP){
                d = numerator;
            }else if(similarityMeasure==SimilarityMeasure.HellingerMod){
                d = distanceToSimilarity(Math.sqrt(2*numerator));
            }else if(similarityMeasure==SimilarityMeasure.PsChiSquareMod){
                d = distanceToSimilarity(2*numerator);
            }else if(similarityMeasure==SimilarityMeasure.ClarkMod || similarityMeasure==SimilarityMeasure.MahalanobisMod || similarityMeasure==SimilarityMeasure.PenroseShape){
                d = distanceToSimilarity(Math.sqrt(numerator));
            }else if(similarityMeasure==SimilarityMeasure.PseudoCos){
                double div = vectorElementSums.get(word1)*vectorElementSums.get(word2);
                if(div == 0d){
                    d = 0d;
                }else{
                    d = numerator/div;
                }
            }else if(similarityMeasure==SimilarityMeasure.PseudoCosMod_1){
                d = numerator/(vectorElementAbsValueSums.get(word1)*vectorElementAbsValueSums.get(word2));
            }else if(similarityMeasure==SimilarityMeasure.PseudoCosMod_2){
                if(vectorElementSums.get(word1) > 0d && vectorElementSums.get(word2) > 0d){
                    d = numerator/(Math.sqrt(vectorElementSums.get(word1))*Math.sqrt(vectorElementSums.get(word2)));
                }else{
                    d = 0d;
                }
            }else if(similarityMeasure==SimilarityMeasure.PseudoCosMod_3){
                d = numerator/(Math.sqrt(vectorElementAbsValueSums.get(word1))*Math.sqrt(vectorElementAbsValueSums.get(word2)));
            }else if(similarityMeasure==SimilarityMeasure.RenyiDivMod2 || similarityMeasure==SimilarityMeasure.RenyiDivModInf){
                d = distanceToSimilarity(lb(numerator));
            }else if(similarityMeasure==SimilarityMeasure.Lsmq){
                d = distanceToSimilarity(lb((numberOfAllFeatures+numerator)/numberOfAllFeatures));
            }else if(similarityMeasure==SimilarityMeasure.Spline){
                d = numberOfAllFeatures+numerator;
            }else if(similarityMeasure==SimilarityMeasure.Overlap){
                double min = Math.min(vectorElementSums.get(word1), vectorElementSums.get(word2));
                if(min!=0d){
                    d = numerator/min;
                }else{
                    d = 0d;
                }
            }else if(similarityMeasure==SimilarityMeasure.AvgL1LInf){
                d = distanceToSimilarity((numerator+denominator1)/2d);
            }else if(similarityMeasure==SimilarityMeasure.MaxSymChiSqMod){
                d = distanceToSimilarity(Math.max(numerator,denominator1));
            }else if(similarityMeasure==SimilarityMeasure.RobersMod){
                d = numerator/(vectorElementAbsValueSums.get(word1)+vectorElementAbsValueSums.get(word2));
            }else if(similarityMeasure==SimilarityMeasure.Mb){
                d = 0.5d*(numerator/vectorLengthSquares.get(word1)+numerator/vectorLengthSquares.get(word2));
            }else if(similarityMeasure==SimilarityMeasure.Simpson_1){
                d = numerator/Math.min(vectorElementAbsValueSums.get(word1), vectorElementAbsValueSums.get(word2));
            }else if(similarityMeasure==SimilarityMeasure.Simpson_2Mod){
                d = numerator/Math.min(denominator1, denominator2);
            }else if(similarityMeasure==SimilarityMeasure.SmoothCos){
                d = (numerator + Math.pow(smoothCosBeta, 2d))/
                        (Math.sqrt(vectorLengthSquares.get(word1) + Math.pow(smoothCosBeta, 2d))*Math.sqrt(vectorLengthSquares.get(word2) + Math.pow(smoothCosBeta, 2d)));
            }else if(similarityMeasure==SimilarityMeasure.AdjCos){
                d = numerator/(Math.sqrt(vectorLengthSquares.get(word1))*Math.sqrt(vectorLengthSquares.get(word2)));
                if(d>=adjCosLambda){
                    d=1d;
                }else{
                    d=d/adjCosLambda;
                }
            }else if(similarityMeasure==SimilarityMeasure.NormCosMod){
                d = numerator/(Math.sqrt(denominator1)*Math.sqrt(denominator2));
                if(d>=normCosLambda){
                    d=1d;
                }else{
                    d=d/normCosLambda;
                }
            }else if(similarityMeasure==SimilarityMeasure.SocPmiMod){
                Double wf1 = wordFrequencies.get(word1);
                Double log1;
                if(wf1==null || wf1==0l){
                    log1 = 0d;
                }else{
                    log1 = Math.log10(wf1);
                }
                Double wf2 = wordFrequencies.get(word2);
                Double log2;
                if(wf2==null || wf2==0l){
                    log2 = 0d;
                }else{
                    log2 = Math.log10(wf2);
                }
                double b1 = Math.pow(log1, 2d)*lb(allWordTypeCount)/SocPmiMu;
                double b2 = Math.pow(log2, 2d)*lb(allWordTypeCount)/SocPmiMu;
                d = numerator/b1+denominator1/b2;
            }else if(similarityMeasure==SimilarityMeasure.NormModSocPmiMod){
                Double wf1 = wordFrequencies.get(word1);
                Double log1;
                if(wf1==null || wf1==0l){
                    log1 = 0d;
                }else{
                    log1 = Math.log10(wf1);
                }
                Double wf2 = wordFrequencies.get(word2);
                Double log2;
                if(wf2==null || wf2==0l){
                    log2 = 0d;
                }else{
                    log2 = Math.log10(wf2);
                }
                double b1 = Math.pow(log1, 2d)*lb(allWordTypeCount)/normModSocPmiDelta;
                double b2 = Math.pow(log2, 2d)*lb(allWordTypeCount)/normModSocPmiDelta;
                d = Math.log(numerator/b1+denominator1/b2+1d);
                if(d>=normModSocPmiLambda){
                    d=1d;
                }else{
                    d=d/normModSocPmiLambda;
                }
            }else if(similarityMeasure==SimilarityMeasure.ChenCorr){
                d=numerator/numberOfAllFeatures;
            }else if(similarityMeasure==SimilarityMeasure.NcdMod_2){
                d = distanceToSimilarity((numerator-denominator1)/denominator2);
            }else if(similarityMeasure==SimilarityMeasure.Wo){
                if(denominator1!=0){
                    double denSum = 0;
                    for(int i=1;i<=Math.round(denominator1);i++){
                        denSum+=1d/(2d*i);
                    }
                    d = numerator/denSum;
                }else{
                    d = 0d;
                }
            }else if(lModPattern.matcher(similarityMeasureString).matches() || dtvPattern.matcher(similarityMeasureString).matches() || similarityMeasure==SimilarityMeasure.SorensenMod){
                    
                int type;
                if(similarityMeasureString.startsWith("L")){
                    type = Integer.parseInt(similarityMeasureString.substring(similarityMeasureString.length()-1));
                }else if(similarityMeasureString.startsWith("D")){
                    type = Integer.parseInt(similarityMeasureString.substring(similarityMeasureString.length()-1));
                }else{
                    type = -1;
                }

                boolean withInverseFunction = similarityMeasure!=SimilarityMeasure.SorensenMod && Integer.parseInt(similarityMeasureString.substring(similarityMeasureString.length()-3, similarityMeasureString.length()-2))==2;

                Double numWWOIF=null;
                Double denWWOIF1=null;
                Double denWWOIF2=null;
                if(withInverseFunction){
                    if(!similarityMeasureString.matches("Dtv_(8|9|(10)|(11)|(12)|(13)|(14)).*")){
                        numWWOIF = similarityMeasureFInverseFunction(type, numerator, numberOfAllFeatures);
                    }else{
                        numWWOIF = numerator;
                    }
                    if(denominator1!=null){
                        denWWOIF1 = similarityMeasureFInverseFunction(type, denominator1, numberOfAllFeatures);
                    }
                    if(denominator2!=null){
                        denWWOIF2 = similarityMeasureFInverseFunction(type, denominator2, numberOfAllFeatures);
                    }
                }else{
                    numWWOIF = numerator;
                    denWWOIF1 = denominator1;
                    denWWOIF2 = denominator2;
                }

                if(similarityMeasureString.matches("(LMod_1.*)|(Dtv_(1|8)).*")){
                    d = distanceToSimilarity(numWWOIF);
                }else if(similarityMeasureString.matches("(LMod_2.*)|(Dtv_(7|(14)).*)")){
                    d = distanceToSimilarity(numWWOIF/(Math.sqrt(vectorLengthSquares.get(word1))*Math.sqrt(vectorLengthSquares.get(word2))));
                }else if(similarityMeasureString.matches("(LMod_3.*)|(Dtv_(6|(13)).*)")){
                    d = distanceToSimilarity(numWWOIF/(Math.sqrt(vectorLengthSquares.get(word1))+Math.sqrt(vectorLengthSquares.get(word2))));
                }else if(similarityMeasureString.matches("LMod_8.*")){
                    d = distanceToSimilarity(numWWOIF/(vectorLengthSquares.get(word1)*vectorLengthSquares.get(word2)));
                }else if(similarityMeasureString.matches("LMod_9.*")){
                    d = distanceToSimilarity(numWWOIF/(vectorLengthSquares.get(word1)+vectorLengthSquares.get(word2)));
                }else if(similarityMeasureString.matches("(LMod_(4|6).*)|(Dtv_(5|(12)).*)")){
                    d = distanceToSimilarity(numWWOIF/(Math.sqrt(denWWOIF1)*Math.sqrt(denWWOIF2)));
                }else if(similarityMeasureString.matches("(LMod_(5|7).*)|(Dtv_(4|(11)).*)")){
                    d = distanceToSimilarity(numWWOIF/(Math.sqrt(denWWOIF1)+Math.sqrt(denWWOIF2)));
                }else if(similarityMeasureString.matches("(LMod_((10)|(12)|(14)).*)|(Dtv_(3|(10)).*)")){
                    d = distanceToSimilarity(numWWOIF/(denWWOIF1*denWWOIF2));
                }else if(similarityMeasureString.matches("(LMod_((11)|(13)|(15)).*)|(Dtv_(2|9).*)")){
                    d = distanceToSimilarity(numWWOIF/(denWWOIF1+denWWOIF2));
                }else if(similarityMeasure==SimilarityMeasure.SorensenMod){
                    d = distanceToSimilarity(numerator/(vectorElementAbsValueSums.get(word1)+vectorElementAbsValueSums.get(word2)));
                }

            }else if(lWPattern.matcher(similarityMeasureString).matches()){
                    
                int type = Integer.parseInt(similarityMeasureString.substring(similarityMeasureString.length()-1));

                if(similarityMeasureString.matches("LW_._1_.")){
                    d = distanceToSimilarity(numerator);
                }else if(similarityMeasureString.matches("LW_._2_.")){
                    d = distanceToSimilarity(similarityMeasureFInverseFunction(type, numerator, numberOfAllFeatures));
                }

            }else if(dtvWPattern.matcher(similarityMeasureString).matches()){
                    
                int type = Integer.parseInt(similarityMeasureString.substring(similarityMeasureString.length()-1));

                if(similarityMeasureString.matches("DtvW_._1_.")){
                    d = distanceToSimilarity(numerator);
                }else if(similarityMeasureString.matches("DtvW_._2_.")){
                    d = distanceToSimilarity(similarityMeasureFInverseFunction(type, numerator, numberOfAllFeatures));
                }

            }else if(linModPattern.matcher(similarityMeasureString).matches() || linHindleRModPattern.matcher(similarityMeasureString).matches()){
                
                int type;
                if(similarityMeasureString.startsWith("LinM")){
                    type = Integer.parseInt(similarityMeasureString.substring(similarityMeasureString.length()-1));
                }else{
                    type = Integer.parseInt(similarityMeasureString.substring(similarityMeasureString.length()-1));
                }

                boolean withInverseFunction = Integer.parseInt(similarityMeasureString.substring(similarityMeasureString.length()-3, similarityMeasureString.length()-2))==2;

                Double numWWOIF=null;
                Double denWWOIF1=null;
                Double denWWOIF2=null;
                if(withInverseFunction){
                    numWWOIF = similarityMeasureFInverseFunction(type, numerator, numberOfAllFeatures);
                    if(denominator1!=null){
                        denWWOIF1 = similarityMeasureFInverseFunction(type, denominator1, numberOfAllFeatures);
                    }
                    if(denominator2!=null){
                        denWWOIF2 = similarityMeasureFInverseFunction(type, denominator2, numberOfAllFeatures);
                    }
                }else{
                    numWWOIF = numerator;
                    denWWOIF1 = denominator1;
                    denWWOIF2 = denominator2;
                }
                
                if(similarityMeasureString.matches("Lin(HindleR)?Mod_1.*")){
                    d = numWWOIF;
                }else if(similarityMeasureString.matches("Lin(HindleR)?Mod_2.*")){
                    d = numWWOIF/(denWWOIF1+denWWOIF2);
                }else if(similarityMeasureString.matches("Lin(HindleR)?Mod_8.*")){
                    double den = denWWOIF1+denWWOIF2;
                    if(den!=0d){
                        d = numWWOIF/den;
                    }else{
                        d=0d;
                    }
                }else if(similarityMeasureString.matches("Lin(HindleR)?Mod_3.*")){
                    d = numWWOIF/(denWWOIF1*denWWOIF2);
                }else if(similarityMeasureString.matches("Lin(HindleR)?Mod_9.*")){
                    double den = denWWOIF1*denWWOIF2;
                    if(den!=0d){
                        d = numWWOIF/den;
                    }else{
                        d=0d;
                    }
                }else if(similarityMeasureString.matches("Lin(HindleR)?Mod_4.*")){
                    d = numWWOIF/(Math.sqrt(denWWOIF1)+Math.sqrt(denWWOIF2));
                }else if(similarityMeasureString.matches("Lin(HindleR)?Mod_5.*")){
                    d = numWWOIF/(Math.sqrt(denWWOIF1)*Math.sqrt(denWWOIF2));
                }else if(similarityMeasureString.matches("Lin(HindleR)?Mod(_6.*)")){
                    d = numWWOIF/(Math.sqrt(vectorLengthSquares.get(word1))+Math.sqrt(vectorLengthSquares.get(word2)));
                }else if(similarityMeasureString.matches("Lin(HindleR)?Mod(_7.*)")){
                    d = numWWOIF/(Math.sqrt(vectorLengthSquares.get(word1))*Math.sqrt(vectorLengthSquares.get(word2)));
                }else{
                    System.out.println("No such SimilarityMeasure: " + similarityMeasure);
                    throw new RuntimeException();
                }
            }else if(similarityMeasureString.matches("Mb(Adj)?Cos((Am)|(Gm)|(Hm)|(Prod)|(LogProd))")){
                
                double d1 = 0.5d*(numerator/vectorLengthSquares.get(word1)+numerator/vectorLengthSquares.get(word2));
                double d2;
                
                if(similarityMeasureString.startsWith("MbC")){
                    d2 = numerator/(Math.sqrt(vectorLengthSquares.get(word1))*Math.sqrt(vectorLengthSquares.get(word2)));
                }else{
                    d2 = numerator/(Math.sqrt(vectorLengthSquares.get(word1))*Math.sqrt(vectorLengthSquares.get(word2)));
                    if(d2>=adjCosLambda){
                        d2=1d;
                    }else{
                        d2=d2/adjCosLambda;
                    }
                }
                
                if(similarityMeasureString.endsWith("Am")){
                    d = (d1+d2)/2;
                }else if(similarityMeasureString.endsWith("Gm")){
                    d = Math.signum(d1*d2)*Math.sqrt(Math.abs(d1*d2));
                }else if(similarityMeasureString.endsWith("Hm")){
                    double den = Math.abs(d1)+Math.abs(d2);
                    if(den==0d){
                        d = 0d;
                    }else{
                        d = 2*d1*d2/den;
                    }
                }else if(similarityMeasureString.endsWith("LogProd")){
                    d = Math.signum(d1)*lb(1d+Math.abs(d1))*Math.signum(d2)*lb(1d+Math.abs(d2));
                }else{
                    d = d1*d2;
                }
                
            }else if(cosModPattern.matcher(similarityMeasureString).matches() || pearsModPattern.matcher(similarityMeasureString).matches() || 
                    adjCosModPattern.matcher(similarityMeasureString).matches() || pfModPattern.matcher(similarityMeasureString).matches()){
                
                int type;
                if(similarityMeasureString.startsWith("C")){
                    type = Integer.parseInt(similarityMeasureString.substring(similarityMeasureString.length()-1));
                }else if(similarityMeasureString.startsWith("A")){
                    type = Integer.parseInt(similarityMeasureString.substring(similarityMeasureString.length()-1));
                }else if(similarityMeasureString.startsWith("Pe")){
                    type = Integer.parseInt(similarityMeasureString.substring(similarityMeasureString.length()-1));
                }else{
                    type = Integer.parseInt(similarityMeasureString.substring(similarityMeasureString.length()-1));
                }
                
                if(similarityMeasureString.matches("((((Adj)?Cos)|(PF))Mod_1.*)")){
                    d = numerator/(Math.sqrt(vectorLengthSquares.get(word1))*Math.sqrt(vectorLengthSquares.get(word2)));
                }else if(similarityMeasureString.matches("((((Adj)?Cos)|(PF))Mod_2_.)|(PearsMod_(1|2)_.)")){
                    d = numerator/(Math.sqrt(denominator1)*Math.sqrt(denominator2));
                }else if(similarityMeasureString.matches("(((Adj)?Cos)|(Pears)|(PF))Mod_3_.")){
                    d = numerator/(Math.sqrt(similarityMeasureFInverseFunction(type, denominator1, numberOfAllFeatures))*Math.sqrt(similarityMeasureFInverseFunction(type, denominator2, numberOfAllFeatures)));
                }else if(similarityMeasureString.matches("(((Adj)?Cos)|(PF))Mod_4_.")){
                    d = similarityMeasureFInverseFunction(type, numerator, numberOfAllFeatures)/(Math.sqrt(vectorLengthSquares.get(word1))*Math.sqrt(vectorLengthSquares.get(word2)));
                }else if(similarityMeasureString.matches("((((Adj)?Cos)|(PF))Mod_5_.)|(PearsMod_(4|5)_.)")){
                    d = similarityMeasureFInverseFunction(type, numerator, numberOfAllFeatures)/(Math.sqrt(denominator1)*Math.sqrt(denominator2));
               }else if(similarityMeasureString.matches("(((Adj)?Cos)|(Pears)|(PF))Mod_6_.")){
                    d = similarityMeasureFInverseFunction(type, numerator, numberOfAllFeatures)/(Math.sqrt(similarityMeasureFInverseFunction(type, denominator1, numberOfAllFeatures))*Math.sqrt(similarityMeasureFInverseFunction(type, denominator2, numberOfAllFeatures)));
                }else{
                    System.out.println("No such SimilarityMeasure: " + similarityMeasure);
                    throw new RuntimeException();
                }
                
                if(similarityMeasureString.startsWith("A")){
                    if(d>=adjCosLambda){
                        d=1d;
                    }else{
                        d=d/adjCosLambda;
                    }
                }else if(similarityMeasureString.startsWith("PF")){
                    Double wf1 = wordFrequencies.get(word1);
                    Double log1;
                    if(wf1==null || wf1==0l){
                        log1 = 0d;
                    }else{
                        log1 = lb(allWordCount/wf1);
                    }
                    Double wf2 = wordFrequencies.get(word2);
                    Double log2;
                    if(wf2==null || wf2==0l){
                        log2 = 0d;
                    }else{
                        log2 = lb(allWordCount/wf2);
                    }
                    d = d*(log1+log2)/2;
                }
                
            }else if(mbModPattern.matcher(similarityMeasureString).matches()){
                if(similarityMeasureString.matches("MbMod_1_.")){
                    d = 0.5d*(numerator/vectorLengthSquares.get(word1)+numerator/vectorLengthSquares.get(word2));
                }else if(similarityMeasureString.matches("MbMod_2_.")){
                    d = 0.5d*(numerator/denominator1+numerator/denominator2);
                }else if(similarityMeasureString.matches("MbMod_3_.")){
                    int type = Integer.parseInt(similarityMeasureString.substring(similarityMeasureString.length()-1));
                    d = 0.5d*(numerator/similarityMeasureFInverseFunction(type, denominator1, numberOfAllFeatures)+numerator/similarityMeasureFInverseFunction(type, denominator2, numberOfAllFeatures));
                }else if(similarityMeasureString.matches("MbMod_4_.")){
                    int type = Integer.parseInt(similarityMeasureString.substring(similarityMeasureString.length()-1));
                    d = 0.5d*(similarityMeasureFInverseFunction(type, numerator, numberOfAllFeatures)/vectorLengthSquares.get(word1)+similarityMeasureFInverseFunction(type, numerator, numberOfAllFeatures)/vectorLengthSquares.get(word2));
                }else if(similarityMeasureString.matches("MbMod_5_.")){
                    int type = Integer.parseInt(similarityMeasureString.substring(similarityMeasureString.length()-1));
                    d = 0.5d*(similarityMeasureFInverseFunction(type, numerator, numberOfAllFeatures)/denominator1+similarityMeasureFInverseFunction(type, numerator, numberOfAllFeatures)/denominator2);
                }else if(similarityMeasureString.matches("MbMod_6_.")){
                    int type = Integer.parseInt(similarityMeasureString.substring(similarityMeasureString.length()-1));
                    d = 0.5d*(similarityMeasureFInverseFunction(type, numerator, numberOfAllFeatures)/similarityMeasureFInverseFunction(type, denominator1, numberOfAllFeatures)+similarityMeasureFInverseFunction(type, numerator, numberOfAllFeatures)/similarityMeasureFInverseFunction(type, denominator2, numberOfAllFeatures));
                }else{
                    System.out.println("No such SimilarityMeasure: " + similarityMeasure);
                    throw new RuntimeException();
                }
            }else if(pearsMbAdjCosModPattern.matcher(similarityMeasureString).matches() || pearsMbAdjCosPfModPattern.matcher(similarityMeasureString).matches() || 
                    pearsMbModPattern.matcher(similarityMeasureString).matches()){
                
                int type = Integer.parseInt(similarityMeasureString.substring(similarityMeasureString.length()-1));
                
                if(similarityMeasureString.matches("PearsMb(AdjCos(PF)?)?Mod_(1|2)_.")){
                    d = 0.5d*(numerator/denominator1+numerator/denominator2);
                }else if(similarityMeasureString.matches("PearsMb(AdjCos(PF)?)?Mod_3_.")){
                    d = 0.5d*(numerator/similarityMeasureFInverseFunction(type, denominator1, numberOfAllFeatures)+numerator/similarityMeasureFInverseFunction(type, denominator2, numberOfAllFeatures));
                }else if(similarityMeasureString.matches("PearsMb(AdjCos(PF)?)?Mod_(4|5)_.")){
                    d = 0.5d*(similarityMeasureFInverseFunction(type, numerator, numberOfAllFeatures)/denominator1+similarityMeasureFInverseFunction(type, numerator, numberOfAllFeatures)/denominator2);
                }else if(similarityMeasureString.matches("PearsMb(AdjCos(PF)?)?Mod_6_.")){
                    d = 0.5d*(similarityMeasureFInverseFunction(type, numerator, numberOfAllFeatures)/similarityMeasureFInverseFunction(type, denominator1, numberOfAllFeatures)+similarityMeasureFInverseFunction(type, numerator, numberOfAllFeatures)/similarityMeasureFInverseFunction(type, denominator2, numberOfAllFeatures));
                }else{
                    System.out.println("No such SimilarityMeasure: " + similarityMeasure);
                    throw new RuntimeException();
                }
                
                if(similarityMeasureString.matches("PearsMbAdjCosPFMod.*")){
                    
                    Double wf1 = wordFrequencies.get(word1);
                    Double log1;
                    if(wf1==null || wf1==0l){
                        log1 = 0d;
                    }else{
                        log1 = lb(allWordCount/wf1);
                    }
                    Double wf2 = wordFrequencies.get(word2);
                    Double log2;
                    if(wf2==null || wf2==0l){
                        log2 = 0d;
                    }else{
                        log2 = lb(allWordCount/wf2);
                    }
                    d = d*(log1+log2)/2;
                    
                    if(d>=adjCosPFModLambda){
                        d=1d;
                    }else{
                        d=d/adjCosPFModLambda;
                    }
                    
                }else if(similarityMeasureString.matches("PearsMbAdjCosMod.*")){
                    
                    if(d>=adjCosLambda){
                        d=1d;
                    }else{
                        d=d/adjCosLambda;
                    }
                    
                }
                
            }else if(innerProdWPattern.matcher(similarityMeasureString).matches()){
                if(similarityMeasureString.matches("InnerProdW_(1|2)_.")){
                    d = numerator;
                }else if(similarityMeasureString.matches("InnerProdW_(3|4)_.")){
                    int type = Integer.parseInt(similarityMeasureString.substring(similarityMeasureString.length()-1));
                    d = similarityMeasureFInverseFunction(type, numerator, numberOfAllFeatures);
                 }else{
                    System.out.println("No such SimilarityMeasure: " + similarityMeasure);
                    throw new RuntimeException();
                }
            }else if(penroseShapeModPattern.matcher(similarityMeasureString).matches()){

                if(similarityMeasureString.matches("PenroseShapeMod_(1|2)_.")){
                    d = distanceToSimilarity(numerator);
                }else if(similarityMeasureString.matches("PenroseShapeMod_(3|4)_.")){
                    int type = Integer.parseInt(similarityMeasureString.substring(similarityMeasureString.length()-1));
                    d = distanceToSimilarity(similarityMeasureFInverseFunction(type, numerator, numberOfAllFeatures));
                }

            }else if(similarityMeasureString.matches("Multiplicative.*")){
                d = distanceToSimilarity(-1+numerator);
            }else if(similarityMeasureString.matches("StdLike_.") || similarityMeasure==SimilarityMeasure.Dfvmb){
                d = distanceToSimilarity(Math.sqrt(numerator/(2*numberOfAllFeatures)));
            }else if(similarityMeasure==SimilarityMeasure.PearsMb){
                d = 0.5d*(numerator/denominator1+numerator/denominator2);
            }else if(similarityMeasure==SimilarityMeasure.PearsAdjCos){
                d = numerator/(Math.sqrt(denominator1)*Math.sqrt(denominator2));
                if(d>=adjCosLambda){
                    d=1d;
                }else{
                    d=d/adjCosLambda;
                }
            }else if(similarityMeasure==SimilarityMeasure.PearsPFMod){
                Double wf1 = wordFrequencies.get(word1);
                Double log1;
                if(wf1==null || wf1==0l){
                    log1 = 0d;
                }else{
                    log1 = lb(allWordCount/wf1);
                }
                Double wf2 = wordFrequencies.get(word2);
                Double log2;
                if(wf2==null || wf2==0l){
                    log2 = 0d;
                }else{
                    log2 = lb(allWordCount/wf2);
                }
                d = numerator/(Math.sqrt(denominator1)*Math.sqrt(denominator2))*(log1+log2)/2;
            }else if(similarityMeasure==SimilarityMeasure.PearsMbAdjCos){
                d = 0.5d*(numerator/denominator1+numerator/denominator2);
                if(d>=adjCosLambda){
                    d=1d;
                }else{
                    d=d/adjCosLambda;
                }
            }else if(similarityMeasure==SimilarityMeasure.PearsMbPFMod){
                Double wf1 = wordFrequencies.get(word1);
                Double log1;
                if(wf1==null || wf1==0l){
                    log1 = 0d;
                }else{
                    log1 = lb(allWordCount/wf1);
                }
                Double wf2 = wordFrequencies.get(word2);
                Double log2;
                if(wf2==null || wf2==0l){
                    log2 = 0d;
                }else{
                    log2 = lb(allWordCount/wf2);
                }
                d = 0.5d*(numerator/denominator1+numerator/denominator2)*(log1+log2)/2;
            }else if(similarityMeasure==SimilarityMeasure.PearsAdjCosPFMod){
                Double wf1 = wordFrequencies.get(word1);
                Double log1;
                if(wf1==null || wf1==0l){
                    log1 = 0d;
                }else{
                    log1 = lb(allWordCount/wf1);
                }
                Double wf2 = wordFrequencies.get(word2);
                Double log2;
                if(wf2==null || wf2==0l){
                    log2 = 0d;
                }else{
                    log2 = lb(allWordCount/wf2);
                }
                d = numerator/(Math.sqrt(denominator1)*Math.sqrt(denominator2))*(log1+log2)/2;
                if(d>=adjCosPFModLambda){
                    d=1d;
                }else{
                    d=d/adjCosPFModLambda;
                }
            }else if(similarityMeasure==SimilarityMeasure.PearsMbAdjCosPFMod){
                Double wf1 = wordFrequencies.get(word1);
                Double log1;
                if(wf1==null || wf1==0l){
                    log1 = 0d;
                }else{
                    log1 = lb(allWordCount/wf1);
                }
                Double wf2 = wordFrequencies.get(word2);
                Double log2;
                if(wf2==null || wf2==0l){
                    log2 = 0d;
                }else{
                    log2 = lb(allWordCount/wf2);
                }
                d = 0.5d*(numerator/denominator1+numerator/denominator2)*(log1+log2)/2;
                if(d>=adjCosPFModLambda){
                    d=1d;
                }else{
                    d=d/adjCosPFModLambda;
                }
            }else if(similarityMeasure==SimilarityMeasure.MbAdjCos){
                d = 0.5d*(numerator/vectorLengthSquares.get(word1)+numerator/vectorLengthSquares.get(word2));
                if(d>=adjCosLambda){
                    d=1d;
                }else{
                    d=d/adjCosLambda;
                }
            }else if(similarityMeasure==SimilarityMeasure.MbPFMod){
                Double wf1 = wordFrequencies.get(word1);
                Double log1;
                if(wf1==null || wf1==0l){
                    log1 = 0d;
                }else{
                    log1 = lb(allWordCount/wf1);
                }
                Double wf2 = wordFrequencies.get(word2);
                Double log2;
                if(wf2==null || wf2==0l){
                    log2 = 0d;
                }else{
                    log2 = lb(allWordCount/wf2);
                }
                d = 0.5d*(numerator/vectorLengthSquares.get(word1)+numerator/vectorLengthSquares.get(word2))*(log1+log2)/2;
            }else if(similarityMeasure==SimilarityMeasure.MbAdjCosPFMod){
                Double wf1 = wordFrequencies.get(word1);
                Double log1;
                if(wf1==null || wf1==0l){
                    log1 = 0d;
                }else{
                    log1 = lb(allWordCount/wf1);
                }
                Double wf2 = wordFrequencies.get(word2);
                Double log2;
                if(wf2==null || wf2==0l){
                    log2 = 0d;
                }else{
                    log2 = lb(allWordCount/wf2);
                }
                d = 0.5d*(numerator/vectorLengthSquares.get(word1)+numerator/vectorLengthSquares.get(word2))*(log1+log2)/2;
                if(d>=adjCosPFModLambda){
                    d=1d;
                }else{
                    d=d/adjCosPFModLambda;
                }
            }else if(similarityMeasure==SimilarityMeasure.AdjCosPFMod){
                Double wf1 = wordFrequencies.get(word1);
                Double log1;
                if(wf1==null || wf1==0l){
                    log1 = 0d;
                }else{
                    log1 = lb(allWordCount/wf1);
                }
                Double wf2 = wordFrequencies.get(word2);
                Double log2;
                if(wf2==null || wf2==0l){
                    log2 = 0d;
                }else{
                    log2 = lb(allWordCount/wf2);
                }
                d = numerator/(Math.sqrt(vectorLengthSquares.get(word1))*Math.sqrt(vectorLengthSquares.get(word2)))*(log1+log2)/2;
                if(d>=adjCosPFModLambda){
                    d=1d;
                }else{
                    d=d/adjCosPFModLambda;
                }
            }else{
                System.out.println("No such SimilarityMeasure: " + similarityMeasure);
                throw new RuntimeException();
            }

        }
        
        return d;
        
    }
    
    
    
    
    
    
    
    /**
     * A function calculating the binary logarithm of a double with +1 smoothing, with the sign of the original value.
     * @param d the double
     * @return the binary logarithm of @param d+1, with the sign of the original value
     */
    public static double signedLbAbsP1(double d){
        
        return Math.signum(d)*Math.log(Math.abs(d)+1d)/LN2;
        
    }
    
    
    
    
    /**
     * A function calculating the binary exponential of a double multiplied with its sign, -1, with the sign of the original value (the inverse of the signedLbAbsP1 function)
     * @param d the double
     * @return the binary exponential of @param d multiplied with its sign, -1, with the sign of the original value
     */
    public static double signedExp2m1(double d){
        
        return Math.signum(d)*(Math.pow(2d, Math.abs(d))-1d);
        
    }
    
    
    
    
    /**
     * A function calculating the logistic value of a double, -0,5.
     * @param d the double
     * @return the logistic value of @param d, -0.5
     */
    public static double shiftedLogistic(double d){
        
        return 1d/(2d+Math.expm1(-d))-0.5d;
        
    }
    
    
    
    
    /**
     * A function calculating the logit of a (double+0.5). (the inverse of the shiftedLogistic function)
     * @param d the double
     * @return the logit of @param d+0.5
     */
    public static double shiftedLogit(double d){
        
        double shiftedD = d+0.5d;
        
        return Math.log(shiftedD/(1-shiftedD));
        
    }
    
    
    
    
    
    
    /**
     * Calculates the f function value for a similarity measure.
     * @param type the type of the f function
     * @param d the argument of the f function
     * @return the f function value of @param d
     */
    public static double similarityMeasureFFunction(int type, double d){
        
        switch (type) {
            case 1:
                return d;
            case 2:
                return Math.signum(d)*Math.sqrt(Math.abs(d));
            case 3:
                return d*Math.abs(d);
            case 4:
                return Math.pow(d, 3d);
            case 5:
                return signedLbAbsP1(d);
            case 6:
                return shiftedLogistic(d);
            default:
                System.out.println("No type of f function: " + type);
                throw new RuntimeException();
        }
        
    }
    
    
    
    
    /**
     * Calculates the f inverse function value for a similarity measure. In case of the signedExp2m1 function 
     * there are minimum and maximum upper bounds as otherwise it would often return Infinity values.
     * @param type the type of the f inverse function
     * @param d the argument of the f inverse function
     * @param numberOfAllFeatures the number of all features in the vector (needed for the shiftedLogit function because of its (-0.5, 0.5) domain)
     * @return the f inverse function value of @param d
     */
    public static double similarityMeasureFInverseFunction(int type, double d, long numberOfAllFeatures){
        
        switch (type) {
            case 1:
                return d;
            case 2:
                return d*Math.abs(d);
            case 3:
                return Math.signum(d)*Math.sqrt(Math.abs(d));
            case 4:
                return Math.cbrt(d);
            case 5: //In case of the signedExp2m1 function there are minimum and maximum upper bounds as otherwise it would often return Infinity values.
                return Math.min(Math.max(signedExp2m1(d), -Math.pow(2d, 100d)), Math.pow(2d, 100d));
            case 6:
                return shiftedLogit(d/numberOfAllFeatures);
            default:
                System.out.println("No type of f inverse function: " + type);
                throw new RuntimeException();
        }
        
    }
    
    
    /**
     * The function calculating a similarity score from a distance score
     * @param distance the distance score
     * @return the similarity score
     */
    public static double distanceToSimilarity(double distance){
        return 1d/(1d+Math.abs(distance));
    }

    
    
    /**
     * This function computes the Pearson correlation of the feature vectors of two words, considering all types of features. It first calculates the 
     * standard deviation for both feature vectors and the covariance of the two feature vectors by calling the stdAndCovarianceForAFeatureType function for 
     * every type of features, then it computes the pearson correlation based on the returned values.
     * @param <V> the type of keys in @param word1Map1, @param word2Map1 and in @param featureMap1
     * @param <X> the type of keys in @param word1Map2, @param word2Map2 and in @param featureMap2
     * @param <Y> the type of keys in @param word1Map3, @param word2Map3 and in @param featureMap3
     * @param <Z> the type of keys in @param word1Map4, @param word2Map4 and in @param featureMap4
     * @param word1Map1 a map, in which one type of features of word1 are stored with weights
     * @param word1Map2 a map, in which one type of features of word1 are stored with weights
     * @param word1Map3 a map, in which one type of features of word1 are stored with weights
     * @param word1Map4 a map, in which one type of features of word1 are stored with weights
     * @param word2Map1 a map, in which one type of features of word2 are stored with weights
     * @param word2Map2 a map, in which one type of features of word2 are stored with weights
     * @param word2Map3 a map, in which one type of features of word2 are stored with weights
     * @param word2Map4 a map, in which one type of features of word2 are stored with weights
     * @param featureMap1 a map, whose keyset contains all the possible features of a type for a word
     * @param featureMap2 a map, whose keyset contains all the possible features of a type for a word
     * @param featureMap3 a map, whose keyset contains all the possible features of a type for a word
     * @param featureMap4 a map, whose keyset contains all the possible features of a type for a word
     * @param expectedValue1 the expected value (mean) of the feature vector of word1
     * @param expectedValue2 the expected value (mean) of the feature vector of word2
     * @param originalPearson determines whether the original Pearson correlation or only the covariance for (Kiela and Clark, 2014)'s correlation measure is calculated
     * @return the Pearson correlation of the feature vectors of the two words
     */
    public static <V, X, Y, Z> double pearsonCorrelationForHashMaps(HashMap<V, Double> word1Map1, HashMap<X, Double> word1Map2, HashMap<Y, Double> word1Map3, HashMap<Z, Double> word1Map4, HashMap<V, Double> word2Map1, HashMap<X, Double> word2Map2, HashMap<Y, Double> word2Map3, HashMap<Z, Double> word2Map4, HashMap<V, Long> featureMap1, HashMap<X, Long> featureMap2, HashMap<Y, Long> featureMap3, HashMap<Z, Long> featureMap4, double expectedValue1, double expectedValue2, boolean originalPearson){
        
        ComparablePair<ComparablePair<Double, Double>, Double> pair1 = stdAndCovarianceForAFeatureType(word1Map1, word2Map1, featureMap1, expectedValue1, expectedValue2);
        ComparablePair<ComparablePair<Double, Double>, Double> pair2 = stdAndCovarianceForAFeatureType(word1Map2, word2Map2, featureMap2, expectedValue1, expectedValue2);
        ComparablePair<ComparablePair<Double, Double>, Double> pair3 = stdAndCovarianceForAFeatureType(word1Map3, word2Map3, featureMap3, expectedValue1, expectedValue2);
        ComparablePair<ComparablePair<Double, Double>, Double> pair4 = stdAndCovarianceForAFeatureType(word1Map4, word2Map4, featureMap4, expectedValue1, expectedValue2);
        
        double stdev1=pair1.first.first+pair2.first.first+pair3.first.first+pair4.first.first;
        double stdev2=pair1.first.second+pair2.first.second+pair3.first.second+pair4.first.second;
        double covariance=pair1.second+pair2.second+pair3.second+pair4.second;
        
        if(originalPearson){
            return covariance/(Math.sqrt(stdev1)*Math.sqrt(stdev2));
        }else{
            return covariance;
        }
        
    }
    
    
    /**
     * This function calculates the standard deviation of @param word1Map and of @param word2Map and their covariance. The feature vector contains only one type of features.
     * @param <V> the type of keys in @param word1Map, @param word2Map and in @param featureMap
     * @param word1Map a map, in which one type of features of word1 are stored with weights
     * @param word2Map a map, in which one type of features of word2 are stored with weights
     * @param featureMap a map, whose keyset contains all the possible features of a type for a word
     * @param expectedValue1 the expected value (mean) of word1
     * @param expectedValue2 the expected value (mean) of word2
     * @return the standard deviation of @param word1Map and of @param word2Map and their covariance
     */
    public static <V> ComparablePair<ComparablePair<Double, Double>, Double> stdAndCovarianceForAFeatureType(HashMap<V, Double> word1Map, HashMap<V, Double> word2Map, HashMap<V, Long> featureMap, double expectedValue1, double expectedValue2){
        
        double covariance=0d;
        double stdev1=0d;
        double stdev2=0d;
        
        for(V paraphrase: featureMap.keySet()){
            
            Double score1 = 0d;
            if(word1Map!=null){
                score1 = word1Map.get(paraphrase);
                if(score1==null){
                    score1=0d;
                }
            }
            Double score2 = 0d;
            if(word2Map!=null){
                score2 = word2Map.get(paraphrase);
                if(score2==null){
                    score2=0d;
                }
            }
            
            covariance+=(score1-expectedValue1)*(score2-expectedValue2);
            stdev1+=Math.pow(score1-expectedValue1, 2d);
            stdev2+=Math.pow(score2-expectedValue2, 2d);
            
        }
        
        return new ComparablePair<ComparablePair<Double, Double>, Double>(new ComparablePair<Double, Double>(stdev1, stdev2), covariance);
        
    }
    
    
    
    
    
    
    /**
     * This function converts the scores stored in @param scoreMap into ranks.
     * @param scoreMap a map, which contains instances of V with scores
     * @return a map, which contains the instances of V with ranks
     */
    public static double[] rankMapForSpearmanCorrelationFromArray(double[] scoreMap){
        
        //A list is created from the instances of V with scores.
        ArrayList<ComparablePair<Integer, Double>> scoreList = new ArrayList<ComparablePair<Integer, Double>>();
        for(int i=0; i<scoreMap.length;i++){
            scoreList.add(new ComparablePair<Integer, Double>(i, scoreMap[i]));
        }
        
        //The scores are sorted in descending order.
        Collections.sort(scoreList);
        Collections.reverse(scoreList);
        
        ArrayList<ComparablePair<Integer, Double>> rankArray = new ArrayList<ComparablePair<Integer, Double>>();
        
        //The scores are converted into ranks. Equal values have ascending ranks here.
        for(int i=0;i<scoreMap.length;i++){
            rankArray.add(new ComparablePair<Integer, Double>(scoreList.get(i).first, i+1d));
        }
        
        //Equal values are assigned equal ranks. The average of the original ranks is assigned to the equal values.
        for(int i=0;i<scoreMap.length;i++){
            
            int j=i+1;
            double sum=rankArray.get(i).second;
            int count=1;
            
            while(j<scoreMap.length && scoreList.get(i).second.equals(scoreList.get(j).second)){
                sum+=rankArray.get(j).second;
                count++;
                j++;
            }
            
            if(count>1){
                for(int k=i;k<j;k++){
                    rankArray.set(k, new ComparablePair<Integer, Double>(scoreList.get(k).first, sum/count));
                }
                i=j-1;
            }
            
        }
        
        //A map is created from the ranked instances of V.
        double[] rankMap = new double[scoreMap.length];
        for(int i=0;i<scoreMap.length;i++){
            rankMap[rankArray.get(i).first] = rankArray.get(i).second;
        }
        
        return rankMap;
        
    }
    
    
    
    
    
    
    
    
    /**
     * This function computes the Pearson correlation of two maps containing instances of V with scores. First the expected values are calculated, then the standard deviations and the covariance,
     * finally the Pearson correlation is calculated from the other values.
     * @param scoreMap1 the first map containing instances of V with scores
     * @param scoreMap2 the second map containing instances of V with scores
     * @param exp1 the expected value (mean) of @param scoreMap1
     * @param exp2 the expected value (mean) of @param scoreMap2
     * @return the Pearson correlation of the two maps
     */
    public static double pearsonCorrelationForArrays(double[] scoreMap1, double[] scoreMap2, double exp1, double exp2){
        
        double covariance=0d;
        double stdevGoldStandard=0d;
        double stdevOwn=0d;
        
        if(scoreMap1.length!=scoreMap2.length){
            System.out.println("Error in pearsonCorrelation method.");
            throw new RuntimeException();
        }
        
        
        for(int i=0;i<scoreMap1.length;i++){
            covariance+=(scoreMap2[i]-exp2)*(scoreMap1[i]-exp1);
            stdevGoldStandard+=Math.pow(scoreMap2[i]-exp2, 2d);
            stdevOwn+=Math.pow(scoreMap1[i]-exp1, 2d);
        }
        
        return covariance/(Math.sqrt(stdevGoldStandard)*Math.sqrt(stdevOwn));
        
    }
    
    
    /**
     * This function builds a concatenated feature vector for a word containing all the types of features, from several feature vectors containing
     * different types of features. The features with zero values are also included in this concatenated vector (it is needed for the Spearman correlation).
     * @param <V> the type of keys in @param map1 and in @param informationMap1
     * @param <X> the type of keys in @param map2 and in @param informationMap2
     * @param <Y> the type of keys in @param map3 and in @param informationMap3
     * @param <Z> the type of keys in @param map4 and in @param informationMap4
     * @param map1 a map, in which one type of features of word1 are stored with weights
     * @param map2 a map, in which one type of features of word1 are stored with weights
     * @param map3 a map, in which one type of features of word1 are stored with weights
     * @param map4 a map, in which one type of features of word1 are stored with weights
     * @param featureList1 a list, in which one type of features of word1 are stored (including features with weight 0)
     * @param featureList2 a list, in which one type of features of word1 are stored (including features with weight 0)
     * @param featureList3 a list, in which one type of features of word1 are stored (including features with weight 0)
     * @param featureList4 a list, in which one type of features of word1 are stored (including features with weight 0)
     * @return the concatenated feature vector for the word containing all the types of features
     */
    public static <V, X, Y, Z> double[] buildConcatenatedFeatureVector(HashMap<V, Double> map1, HashMap<X, Double> map2, HashMap<Y, Double> map3, HashMap<Z, Double> map4, V[] featureList1, X[] featureList2, Y[] featureList3, Z[] featureList4){
        
        double[] concatenatedFeatureMap = new double[featureList1.length + featureList2.length + featureList3.length + featureList4.length];
        
        addOneFeatureTypeToConcatenatedFeatureVector(concatenatedFeatureMap, 0, map1, featureList1);
        addOneFeatureTypeToConcatenatedFeatureVector(concatenatedFeatureMap, featureList1.length, map2, featureList2);
        addOneFeatureTypeToConcatenatedFeatureVector(concatenatedFeatureMap, featureList1.length+featureList2.length, map3, featureList3);
        addOneFeatureTypeToConcatenatedFeatureVector(concatenatedFeatureMap, featureList1.length+featureList2.length+featureList3.length, map4, featureList4);
        
        return concatenatedFeatureMap;
        
    }
    
    
    /**
     * This function concatenates one type of features to the original concatenated feature vector of a word (it modifies the original vector).
     * @param <V> the type of keys in @param map and in @param informationMap
     * @param concatenatedFeatureMap the original concatenated feature vector
     * @param startIndex a start index, from where the concatenated feature vector should be filled with new values
     * @param map a map, in which one type of features of word are stored with weights
     * @param featureList a list, in which one type of features of word1 are stored (including features with weight 0)
     */
    public static <V> void addOneFeatureTypeToConcatenatedFeatureVector(double[] concatenatedFeatureMap, int startIndex, HashMap<V, Double> map, V[] featureList){
        
        if(map!=null){
            for(int i=0;i<featureList.length;i++){
                Double d = map.get(featureList[i]);
                if(d!=null){
                    concatenatedFeatureMap[i+startIndex]=d;
                }
            }
        }
        
    }
    
    
    
    
    /**
     * This function computes the Rbo (Rank-Biased Overlap) similarity of the feature vectors of two words, considering all types of features.
     * @param <V> the type of keys in @param word1Map1, @param word2Map1 and in @param featureMap1
     * @param <X> the type of keys in @param word1Map2, @param word2Map2 and in @param featureMap2
     * @param <Y> the type of keys in @param word1Map3, @param word2Map3 and in @param featureMap3
     * @param <Z> the type of keys in @param word1Map4, @param word2Map4 and in @param featureMap4
     * @param word1Map1 a map, in which one type of features of word1 are stored with weights
     * @param word1Map2 a map, in which one type of features of word1 are stored with weights
     * @param word1Map3 a map, in which one type of features of word1 are stored with weights
     * @param word1Map4 a map, in which one type of features of word1 are stored with weights
     * @param word2Map1 a map, in which one type of features of word2 are stored with weights
     * @param word2Map2 a map, in which one type of features of word2 are stored with weights
     * @param word2Map3 a map, in which one type of features of word2 are stored with weights
     * @param word2Map4 a map, in which one type of features of word2 are stored with weights
     * @return the Rbo (Rank-Biased Overlap) similarity of the feature vectors of the two words
     */
    public static <V, X, Y, Z> double rbo(HashMap<V, Double> word1Map1, HashMap<X, Double> word1Map2, HashMap<Y, Double> word1Map3, HashMap<Z, Double> word1Map4, 
            HashMap<V, Double> word2Map1, HashMap<X, Double> word2Map2, HashMap<Y, Double> word2Map3, HashMap<Z, Double> word2Map4){
        
        ArrayList<ComparablePair<String, Double>> word1ConcatenatedSortedList = new ArrayList<ComparablePair<String, Double>>();
        
        addFeatureValuePairsToArrayList(word1ConcatenatedSortedList, word1Map1, "1_");
        addFeatureValuePairsToArrayList(word1ConcatenatedSortedList, word1Map2, "2_");
        addFeatureValuePairsToArrayList(word1ConcatenatedSortedList, word1Map3, "3_");
        addFeatureValuePairsToArrayList(word1ConcatenatedSortedList, word1Map4, "4_");
        
        Collections.sort(word1ConcatenatedSortedList);
        
        ArrayList<ComparablePair<String, Double>> word2ConcatenatedSortedList = new ArrayList<ComparablePair<String, Double>>();
        
        addFeatureValuePairsToArrayList(word2ConcatenatedSortedList, word2Map1, "1_");
        addFeatureValuePairsToArrayList(word2ConcatenatedSortedList, word2Map2, "2_");
        addFeatureValuePairsToArrayList(word2ConcatenatedSortedList, word2Map3, "3_");
        addFeatureValuePairsToArrayList(word2ConcatenatedSortedList, word2Map4, "4_");
        
        Collections.sort(word2ConcatenatedSortedList);
        
        HashMap<String, Double> word2ConcatenatedMapOfFirstNElements = convertFirstNElementsOfListToMap(word2ConcatenatedSortedList, null);
        
        int h = 0;
        
        for(ComparablePair<String, Double> word1Entry: word1ConcatenatedSortedList){
            if(word2ConcatenatedMapOfFirstNElements.containsKey(word1Entry.first)){
                h++;
            }
        }
        
        double score = 0d;
        word2ConcatenatedMapOfFirstNElements = new HashMap<String, Double>();
        
        for(int d=1;d<=h;d++){
            if(d<word2ConcatenatedSortedList.size()){
                ComparablePair<String, Double> pair = word2ConcatenatedSortedList.get(d-1);
                word2ConcatenatedMapOfFirstNElements.put(pair.first, pair.second);
            }
            int hdSize = 0;
            for(int i=0;i<d;i++){
                if(word2ConcatenatedMapOfFirstNElements.containsKey(word1ConcatenatedSortedList.get(i).first)){
                    hdSize++;
                }
            }
            score += Math.pow(rboP, d-1)*((double) hdSize)/d;
        }
        
        return (1-rboP)*score;
        
    }
    
    
    
}
