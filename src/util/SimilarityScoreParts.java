package util;

/**
 * This is a class for holding parts of a similarity score.
 * 
 * @author Dobó
 */
public class SimilarityScoreParts {
    
    public Double numerator;
    public Double denominator1;
    public Double denominator2;
    public Double linIntersect;
    public Double lin1;
    public Double lin2;
    
    public SimilarityScoreParts(Double numerator, Double denominator1, Double denominator2, Double linIntersect, Double lin1, Double lin2){
        this.numerator=numerator;
        this.denominator1=denominator1;
        this.denominator2=denominator2;
        this.linIntersect=linIntersect;
        this.lin1=lin1;
        this.lin2=lin2;
    }
    
}
