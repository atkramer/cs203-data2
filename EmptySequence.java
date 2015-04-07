public class EmptySequence<T extends Comparable> implements Sequence<T> {
     
    public T here() {
	throw new EmptySequenceException("No elements in an empty sequence");
    }
    
    public boolean notEmpty() {
	return false;
    }
    
    public Sequence<T> next() {
	return this;
    }
    
}
