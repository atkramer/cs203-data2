public class Leaf<T extends Comparable> implements FiniteSet<T> {
    
    //Constuctor for Leaf
    //left blank since Leaf represents the empty set
    public Leaf() {}

    //Static method to create a new empty set
    public static FiniteSet empty() {
	return new Leaf();
    }

    public int cardinality() {
	return 0;
    }

    public boolean isEmptyHuh() {
	return true;
   }

    public boolean member(T elt) {
	return false;
    }

    public FiniteSet add(T elt) {
	return new Branch(this, elt, this);
    }

     public FiniteSet remove(T elt) {
    	return this;
    }
    

    public FiniteSet union(FiniteSet u) {
	return u;
    }

    public FiniteSet inter(FiniteSet u) {
	return this;
    }

    public FiniteSet diff(FiniteSet u) {
	return u;
    }


    public boolean equal(FiniteSet u) {
	return u.isEmptyHuh();
    }

    public boolean subset(FiniteSet u) {
	return true;
    }

    public String toString() {
	return "";
    }

}
