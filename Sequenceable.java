public interface Sequenceable<T extends Comparable> {
    /**
     * @return A new sequence containing the same elements as
     *         the data structure implementing this interface
     */
    public Sequence<T> seq();
}
