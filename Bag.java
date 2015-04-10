public interface Bag<T extends Comparable> extends Sequenceable<T>{

    /**
     * Determines if a Bag is empty
     * @return true if the Bag is empty, false otherwise
     */
    public boolean isEmptyHuh();

    /**
     * Returns the number of elements in this Bag
     * @return An int representing the number of elements are in the Bag
     */
    public int cardinality();
    
    /**
     * Returns the number of distinct elements in this Bag
     * @return An int representing the number of unique elements in this
     */
    public int numDistinctElts();
  
    /**
     * Determines if a particular element is a member of a Bag
     * @param elt an element of type T to check for in the Bag
     * @return true if the Bag contains elt, false otherwise
     */
    public boolean member(T elt);

    /**
     * Returns the number of times an element appears in this Bag
     * @param elt The element of type T to check for
     * @return An int representing the number of times elt appears in
     *         this Bag
     */
    public int multiplicity(T elt);

    /**
     * Adds n copies of an element to a Bag.
     * @param elt The element of type T to add to the Bag
     * @param n The number of occurences of elt to add
     * @return A new Bag that contains all the old members plus elt
     */
    public Bag<T> add(T elt, int n);

    /**
     * Removes n copies of an elt from a Bag
     * @param elt The element to remove from the Bag
     * @param n The number of occurences of elt to remove
     * @return A new Bag that contains all the elements of the old Bag
     *         with n instances of elt removed
     */
    public Bag<T> remove(T elt, int n);

    /**
     * Finds the sum of two Bags
     * @param u A Bag
     * @return A new Bag that contains all the elements of both the 
     *         Bag it is called on and u
     */
    public Bag<T> sum(Bag<T> u);

    /**
     * Finds the union of two Bags
     * @param u a Bag
     * @return A new Bag that, for each element a in this or u, contains the
     *         maximum multiplicity of a in this and u
     */
    public Bag<T> union(Bag<T> u);

    /**
     * Finds the intersection of two Bags
     * @param u A Bag
     * @return A new Bag that contains only the elements which appear in both
     *         this and u, and the multiplicity of each of these elements is equal to
     *         the minimum of its multiplicity in each of the two sets
     */
    public Bag<T> inter(Bag<T> u);

    /**
     * Determines the difference between two Bags
     * @param u A Bag
     * @return A new Bag containing each element a in u n times, where n
     *         is equal to the multiplicity of a in u minus the multiplicity of
     *         a in this Bag
     */
    public Bag<T> diff(Bag<T> u);

    /**
     * Determines if two Bags contain the same elements.
     * The two Bags do not need to have the same structure
     * @param u A Bag
     * @return true if both u and the Bag this is called on contain exactly
     *         the same elements and each element has the same multiplicity in 
     *         both sets, false otherwise
     */
    public boolean equal(Bag<T> u);

    /**
     * Determines if one Bag is the subset of another
     * @param u A Bag
     * @return true if all the elements in the Bag this is called on also appear
     *         in u and each element's multiplicity in this Bag is less than its 
     *         multiplicity in u, false otherwise
     */
    public boolean subset(Bag<T> u);
}
