public interface Sequence<T extends Comparable> {
    /**
     * Returns the first element in the sequence
     * @return The first element held in the sequence
     */
    public T here();

    /**
     * Determines if the sequence is or is not empty
     * @return True if there is at least one item in the sequence,
     *         false otherwise
     */
    public boolean notEmpty();

    /**
     * Returns the rest of the elements in the sequence
     * @return A sequence composed of all the elements in this
     *         sequence except the first
     */
    public Sequence<T> next();
}
