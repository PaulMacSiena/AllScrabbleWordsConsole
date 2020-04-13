/**
   A "natural" Comparator for objects which implement the Comparable interface.
   This just uses the Comparable's compareTo method to implement the
   required compare method of the Comparator interface.
   Based on implementation by Duane A. Bailey, structure5.NaturalComparator
   @author Jim Teresco - This is a natural comparator Teresco gave us in Data Structures
*/

import java.util.Comparator;

public class NaturalComparator<T extends Comparable<T>> implements Comparator<T> {

    /**
       Compare two objects of type T, which must implement the Comparable
       interface.
       @param a the first object of type T
       @param b the second object of type T
       @return &lt; 0 if a &lt; b, 0 if a == b, and &gt; 0 if a &gt; b, all according to T's compareTo method
    */
    public int compare(T a, T b) {

	return a.compareTo(b);
    }
}
