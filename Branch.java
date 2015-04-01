public class Branch<T extends Comparable> implements FiniteSet<T> {

    T data;
    FiniteSet left;
    FiniteSet right;
    
    //Constructor for Branch. Takes an int for the value of the tree
    //at the point, and two FiniteSets for the left and right branches of the tree
    public Branch(FiniteSet left, T data, FiniteSet right) {
	this.left = left;
	this.right = right;
	this.data = data;
    }

    public int cardinality() {
	return 1 + left.cardinality() + right.cardinality();
    }

    public boolean isEmptyHuh() {
	return false;
    }

    public boolean member(T elt) {
	if(elt.compareTo(data) == 0) {
	    return true;
	} else if(elt.compareTo(data) < 0) {
	    return left.member(elt);
	} else {
	    return right.member(elt);
	}
    }

    public FiniteSet add(T elt) {
	if(elt.compareTo(data) == 0) {
	    return this; 
	} else if(elt.compareTo(data) < 0) {
	    return new Branch(left.add(elt), data, right);
	} else {
	    return new Branch(left, data, right.add(elt));
	}
    }
    
    @Override
    public FiniteSet remove(T elt) {
	if(elt.compareTo(data) < 0) {
	    return new Branch(left.remove(elt), data, right);
	} else if(elt.compareTo(data) > 0) {
	    return new Branch(left, data, right.remove(elt));
	} else {
	    return left.union(right);
	}
    }

    public FiniteSet union(FiniteSet u) {
	return u.union(left).union(right).add(data);	
    }

    public FiniteSet inter(FiniteSet u) {
	if(u.member(data)) {
	    return new Branch(left.inter(u), data, right.inter(u));
	} else {
	    return left.union(right).inter(u);
	}
    }
    
    public FiniteSet diff(FiniteSet u) {
	return left.union(right).diff(u.remove(data));
    }

    public boolean equal(FiniteSet u) {
	return this.subset(u) && u.subset(this);
    }
    
    
    public boolean subset(FiniteSet u) {
	return u.member(data) && left.subset(u) && right.subset(u);
    }

    public String toString() {
	return  "{" + data + left.toString() +
	    right.toString() + "}";
    }

} 
