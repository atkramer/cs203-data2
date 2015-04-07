public class Leaf<T extends Comparable> implements Bag<T> {
    
    //Constuctor for Leaf
    //left blank since Leaf represents the empty set
    public Leaf() {}

    //Static method to create a new empty set
    public static <T extends Comparable> Bag<T> empty() {
	return new Leaf<T>();
    }

    public boolean isEmptyHuh() {
	return true;
   }

    public int cardinality() {
	return 0;
    }

    public int numDistinctElts() {
	return 0;
    }

    public boolean member(T elt) {
	return false;
    }
    
    public int multiplicity(T elt) {
	return 0;
    }

    public Bag<T> add(T elt, int n) {
	return new Branch<T>(this, 0, elt, n, this, 0);
    }

    public Bag<T> remove(T elt, int n) {
    	return this;
    }    

    public Bag<T> union(Bag<T> u) {
	return u;
    }

    public Bag<T> inter(Bag<T> u) {
	return this;
    }

    public Bag<T> diff(Bag<T> u) {
	return u;
    }

    public boolean equal(Bag<T> u) {
	return u.isEmptyHuh();
    }

    public boolean subset(Bag<T> u) {
	return true;
    }

    public String toString() {
	return "";
    }

    public Sequence<T> seq() {
	return new EmptySequence();
    }
}
