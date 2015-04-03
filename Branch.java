public class Branch<T extends Comparable> implements Bag<T> {

    T data;
    int multiplicity;
    Bag<T> left;
    Bag<T> right;
    
    //Constructor for Branch. Takes an int for the value of the tree
    //at the point, and two Bags for the left and right branches of the tree
    public Branch(Bag<T> left, T data, int multiplicity, Bag<T> right) {
	this.left = left;
	this.right = right;
	this.data = data;
	this.multiplicity = multiplicity;
    }

    public boolean isEmptyHuh() {
	return false;
    }

    public int cardinality() {
	return this.multiplicity + left.cardinality() + right.cardinality();
    }
    
    public int numDistinctElts() {
	return 1 + left.numDistinctElts() + right.numDistinctElts();
    }

    public boolean member(T elt) {
	if(elt.compareTo(data) == 0 && this.multiplicity != 0) {
	    return true;
	} else if(elt.compareTo(data) < 0) {
	    return left.member(elt);
	} else {
	    return right.member(elt);
	}
    }

    public Bag<T> add(T elt, int n) {
	if(elt.compareTo(data) == 0) {
	    return new Branch<T>(left, elt, multiplicity + n, right);
	} else if(elt.compareTo(data) < 0) {
	    return new Branch<T>(left.add(elt, n), data, multiplicity, right);
	} else {
	    return new Branch<T>(left, data, multiplicity, right.add(elt, n));
	} 
    }
    
    public Bag<T> remove(T elt, int n) {
	if(elt.compareTo(data) < 0) {
	    return new Branch<T>(left.remove(elt, n), data, multiplicity, right);
	} else if(elt.compareTo(data) > 0) {
	    return new Branch<T>(left, data, multiplicity, right.remove(elt, n));
	} else {
	    return new Branch<T>(left, data, Math.max(multiplicity - n, 0), right);
	}
    }

    public Bag<T> union(Bag<T> u) {
	return u.union(left).union(right).add(data);	
    }

    public Bag<T> inter(Bag<T> u) {
	if(u.member(data)) {
	    return new Branch<T>(left.inter(u), data, right.inter(u));
	} else {
	    return left.union(right).inter(u);
	}
    }
    
    public Bag<T> diff(Bag<T> u) {
	return left.union(right).diff(u.remove(data));
    }

    public boolean equal(Bag<T> u) {
	return this.subset(u) && u.subset(this);
    }
    
    
    public boolean subset(Bag<T> u) {
	return u.member(data) && left.subset(u) && right.subset(u);
    }

    public String toString() {
	return  "{" + data + left.toString() +
	    right.toString() + "}";
    }

} 
