package util;


/**
 * This class represents a paraphtase.
 * @author Andras
 */
public class Paraphrase {

    //These parameters store the information of the paraphrase.
    public boolean passive;
    public String verb;
    public String prep;

    /**
     * This is the constructor of a paraphrase.
     * @param passive it tells, whether the paraphrase is passive or not
     * @param verb the verb of the paraphrase
     * @param prep the prepositions of the paraphrase
     */
    public Paraphrase(boolean passive, String verb, String prep){
        this.passive=passive;
        this.verb=verb;
        this.prep=prep;
    }

    /**
     * This function returns, whether this paraphrase is the same as another paraphrase.
     * @param o the other paraphrase
     * @return a boolean, which tells whether the two paraphrases are the same or not
     */
    @Override
    public boolean equals(Object o){
        if(o instanceof Paraphrase){
            Paraphrase p = (Paraphrase) o;
            if(this.passive==p.passive && this.verb.equals(p.verb) && ((this.prep==null && p.prep==null) ||(this.prep!=null && p.prep!=null && this.prep.equals(p.prep)))){
                return true;
            }
        }
        return false;
    }

    /**
     * This function returns the hash code of the paraphrase.
     * @return the hash code
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + (this.passive ? 1 : 0);
        hash = 41 * hash + (this.verb != null ? this.verb.hashCode() : 0);
        hash = 41 * hash + (this.prep != null ? this.prep.hashCode() : 0);
        return hash;
    }

    /**
     * This function converts the paraphrase to a string (infinitive form).
     * @return the string representing the paraphrase (infinitive form).
     */
    @Override
    public String toString(){
        return infinitive();
    }

    /**
     * This function returns the string representing the infinitive form of the paraphrase.
     * @return the string representing the infinitive form of the paraphrase
     */
    public String infinitive(){
        return (passive ? ("be " + Inflections.pastParticiples.get(verb)) : verb) + (prep==null ? "" : (" " + prep));
    }

    /**
     * This function returns the string representing the present third person singular form of the paraphrase.
     * @return the string representing the present third person singular form of the paraphrase
     */
    public String thirdPersonSingular(){
        return (passive ? ("is " + Inflections.pastParticiples.get(verb)) : Inflections.thirdPersonForms.get(verb)) + (prep==null ? "" : (" " + prep));
    }

    /**
     * This function returns the string representing the present plural form of the paraphrase.
     * @return the string representing the present plural form of the paraphrase
     */
    public String plural(){
        if(passive){
            return "are " + Inflections.pastParticiples.get(verb) + (prep==null ? "" : (" " + prep));
        }else if(verb.equals("be")){
            return "are" + (prep==null ? "" : (" " + prep));
        }else{
            return verb + (prep==null ? "" : (" " + prep));
        }
    }

    /**
     * This function returns the string representing the past tense singular form of the paraphrase.
     * @return the string representing the the past tense singular form of the paraphrase
     */
    public String pastSingular(){
        if(passive){
            return "was " + Inflections.pastParticiples.get(verb) + (prep==null ? "" : (" " + prep));
        }else if(verb.equals("be")){
            return "was" + (prep==null ? "" : (" " + prep));
        }else{
            return Inflections.pastForms.get(verb) + (prep==null ? "" : (" " + prep));
        }
    }

    /**
     * This function returns the string representing the past tense plural form of the paraphrase.
     * @return the string representing the past tense plural form of the paraphrase
     */
    public String pastPlural(){
        if(passive){
            return "were " + Inflections.pastParticiples.get(verb) + (prep==null ? "" : (" " + prep));
        }else if(verb.equals("be")){
            return "were" + (prep==null ? "" : (" " + prep));
        }else{
            return Inflections.pastForms.get(verb) + (prep==null ? "" : (" " + prep));
        }
    }

    /**
     * This function returns the string representing the present perfect singular form of the paraphrase.
     * @return the string representing the present perfect singular form of the paraphrase
     */
    public String presentPerfectSingular(){
        return "has " + (passive ? ("been " + Inflections.pastParticiples.get(verb)) : Inflections.pastParticiples.get(verb)) + (prep==null ? "" : (" " + prep));
    }

    /**
     * This function returns the string representing the present perfect plural form of the paraphrase.
     * @return the string representing the present perfect plural form of the paraphrase
     */
    public String presentPerfectPlural(){
        return "have " + (passive ? ("been " + Inflections.pastParticiples.get(verb)) : Inflections.pastParticiples.get(verb)) + (prep==null ? "" : (" " + prep));
    }

    /**
     * This function returns the string representing the present progressive singular form of the paraphrase.
     * @return the string representing the present progressive singular form of the paraphrase
     */
    public String progressiveSingular(){
        return "is " + (passive ? ("being " + Inflections.pastParticiples.get(verb)) : Inflections.progressiveForms.get(verb)) + (prep==null ? "" : (" " + prep));
    }

    /**
     * This function returns the string representing the present progressive plural form of the paraphrase.
     * @return the string representing the present progressive plural form of the paraphrase
     */
    public String progressivePlural(){
        return "are " + (passive ? ("being " + Inflections.pastParticiples.get(verb)) : Inflections.progressiveForms.get(verb)) + (prep==null ? "" : (" " + prep));
    }

    /**
     * This function returns the string representing the future form of the paraphrase.
     * @return the string representing the future form of the paraphrase
     */
    public String future(){
        return "will " + infinitive();
    }

}
