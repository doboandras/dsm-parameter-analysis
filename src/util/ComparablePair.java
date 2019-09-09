package util;


/**
 * This class represents a generic pair of values, whose instances can be compared to each other based on their second elements.
 * 
 * @author Dobó
 */
public class ComparablePair<T1, T2 extends Comparable<T2>> implements Comparable<ComparablePair<T1, T2>> {

    //The elements of the pair.
    public final T1 first;
    public final T2 second;

    /**
     * The constructor of a pair.
     * @param first the first element of the pair
     * @param second the second element of the pair
     */
    public ComparablePair(T1 first, T2 second){
        this.first=first;
        this.second=second;
    }


    /**
     * This function compares this pair to another pair, based on their second elements.
     * @param o the other pair
     * @return an integer, representing the relationship between the two pairs
     */
    @Override
    public int compareTo(ComparablePair<T1, T2> o) {
        return this.second.compareTo(o.second);
    }

    /**
     * This function returns, whether this pair is the same as another pair.
     * @param o the other pair
     * @return a boolean, which tells whether the two pairs are the same or not
     */
    @Override
    public boolean equals(Object o){
        if(o instanceof ComparablePair){
            ComparablePair p = (ComparablePair) o;
            return this.first.equals(p.first) && this.second.equals(p.second);
        }else{
            return false;
        }
    }

    /**
     * This function returns the hash code of the pair.
     * @return the hash code
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (this.first != null ? this.first.hashCode() : 0);
        hash = 97 * hash + (this.second != null ? this.second.hashCode() : 0);
        return hash;
    }

}
