package data1;

public interface FiniteSet {
    
    //All the methods that the finite sets should be able to accomodate,
    //and should therefore be possible for both Leafs and Branches
    //except for empty since it is static


    /**
     * Method to determine how many numbers are in a FiniteSet
     * @return An int representing how many numbers are in the FiniteSet
     */
    int cardinality();
   
    /**
     * Determines if a FiniteSet is empty
     * @return true if the FiniteSet is empty, false otherwise
     */
    boolean isEmptyHuh();
  
  /**
     * Determines if a particular int
     * is a member of a FiniteSet
     * @param elt an int to check for in the FiniteSet
     * @return true if the FiniteSet contains elt, false otherwise
     */
    boolean member(int elt);

    /**
     * Adds an element to a FiniteSet.
     * Because FiniteSets are representing sets here, add will not
     * duplicate an element that already exists in the FiniteSet
     * @param elt The element to add to the FiniteSet
     * @return A new FiniteSet that contains all the old members plus elt
     */
    FiniteSet add(int elt);

    /**
     * Removes an elt from a FiniteSet
     * @param elt The element to remove from the FiniteSet
     * @return A new FiniteSet that contains all the elements of the old FiniteSet
     *         except elt
     */
    FiniteSet remove(int elt);

    /**
     * Finds the union of two FiniteSetS
     * @param u A FiniteSet
     * @return A new FiniteSet that contains all the elements of both the 
     *         FiniteSet it is called on and u
     */
    FiniteSet union(FiniteSet u);

    /**
     * Finds the intersection of two FiniteSetS
     * @param u A FiniteSet
     * @return A new FiniteSet that contains only the elements that appear both
     *         in the FiniteSet it was called on and u.
     */
    FiniteSet inter(FiniteSet u);

    /**
     * Determines the difference between two FiniteSets
     * @param u A FiniteSet
     * @return A new FiniteSet that contains only the elements that appear in u,
     *         but not in the FiniteSet this method is called on
     */
    FiniteSet diff(FiniteSet u);

    /**
     * Determines if two FiniteSets contain the same elements.
     * The two FiniteSets do not need to have the same structure
     * @param u A FiniteSet
     * @return true if both u and the FiniteSet this is called on contain exactly
     *         the same elements, false otherwise
     */
    boolean equal(FiniteSet u);

    /**
     * Determines if one FiniteSet is the subset of another
     * @param u A FiniteSet
     * @return true if all the elements in the FiniteSet this is called on also appear
     *         in u, false otherwise
     */
    boolean subset(FiniteSet u);
}
