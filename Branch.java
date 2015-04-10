public class Branch<T extends Comparable> implements Bag<T>, Sequence {

    T data;
    int multiplicity;
    Bag<T> left;
    int leftHeight;
    Bag<T> right;
    int rightHeight;
    
    //Constructor for Branch. Takes an int for the value of the tree
    //at the point, and two Bags for the left and right branches of the tree
    public Branch(Bag<T> left, int leftHeight, T data, int multiplicity, Bag<T> right, int rightHeight) {
	this.left = left;
	this.leftHeight = leftHeight;
	this.right = right;
	this.rightHeight = rightHeight;
	this.data = data;
	this.multiplicity = multiplicity;
    }

    public boolean isEmptyHuh() {
        if(multiplicity > 0) {
	    return true;
	} else {
	    return left.isEmptyHuh() || right.isEmptyHuh();
	}
    }

    public int cardinality() {
	return this.multiplicity + left.cardinality() + right.cardinality();
    }
    
    public int numDistinctElts() {
	if(multiplicity != 0) {
	    return 1 + left.numDistinctElts() + right.numDistinctElts();
	} else {
	    return left.numDistinctElts() + right.numDistinctElts();
	}
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

    public int multiplicity(T elt)  {
	if(elt.compareTo(data) == 0) {
	    return this.multiplicity;
	} else if(elt.compareTo(data) < 0) {
	    return left.multiplicity(elt);
	} else {
	    return right.multiplicity(elt);
	}
    }

    
    private Branch<T> rotateLeft() {
	Branch<T> bRight = (Branch<T>) this.right;
	return new Branch<T>(new Branch<T>(this.left, this.leftHeight, this.data, this.multiplicity, bRight.left, bRight.leftHeight),
			     (1+Math.max(this.leftHeight, bRight.leftHeight)),
			     bRight.data,
			     bRight.multiplicity,
			     bRight.right,
			     bRight.rightHeight);
    }

    private Branch<T> rotateRight() {
	Branch<T> bLeft = (Branch<T>) this.left;
	return new Branch<T>(bLeft.left,
			     bLeft.leftHeight,
			     bLeft.data,
			     bLeft.multiplicity,
			     new Branch<T>(bLeft.right, bLeft.rightHeight, this.data, this.multiplicity, this.right, this.rightHeight),
			     (1+Math.max(bLeft.rightHeight, this.rightHeight)));
    } 

    private Branch<T> balance() {
	if(leftHeight - rightHeight >= 2) {
	    Branch<T> tempLeft = (Branch<T>) left;
	    //Left-Left leaning case. Simply rotate the top tree right
	    if(tempLeft.leftHeight > tempLeft.rightHeight) {
		return this.rotateRight();
		//Left-Right leaning case. First rotate subtree left, then whole tree right
	    } else {
		return new Branch<T>(tempLeft.rotateLeft(), leftHeight, data, multiplicity, right, rightHeight).rotateRight();
	    }
	} else if(rightHeight - leftHeight >= 2) {
	    Branch<T> tempRight = (Branch<T>) right;
	    //Right-Right leaning case. Simply rotate the top tree left
	    if(tempRight.rightHeight > tempRight.leftHeight) {
		return this.rotateLeft();
		//Right-Left leaning case. First rotate subtree right, then whole tree left
	    } else {
		return new Branch<T>(left, leftHeight, data, multiplicity, tempRight, rightHeight).rotateLeft();
	    }
	} else {
	    return this;
	}
    } 

    private int getHeight() {
	return Math.max(leftHeight, rightHeight);
    }

    private Branch<T> updateHeights() {
	if(left.isEmptyHuh() && right.isEmptyHuh()) {
	    return new Branch<T>(left, 0, data, multiplicity, right, 0);
	} else if(left.isEmptyHuh()) {
	    return new Branch<T>(left, 0, data, multiplicity, right, 1+((Branch<T>) right).getHeight());
	} else if(right.isEmptyHuh()) {
	    return new Branch<T>(left, 1+((Branch<T>) left).getHeight(), data, multiplicity, right, 0);
	} else {
	    return new Branch<T>(left, 1+((Branch<T>) left).getHeight(), data, multiplicity, right, 1+((Branch<T>) right).getHeight());
	}
    }

    public Bag<T> add(T elt, int n) {
	if(elt.compareTo(data) == 0) {
	    return new Branch<T>(left, leftHeight, elt, multiplicity + n, right, rightHeight);
	} else if(elt.compareTo(data) < 0) {
	    return new Branch<T>(left.add(elt, n), leftHeight, data, multiplicity, right, rightHeight).updateHeights().balance();
	} else {
	    return new Branch<T>(left, leftHeight, data, multiplicity, right.add(elt, n), rightHeight).updateHeights().balance();
	} 
    }
    
    public Bag<T> remove(T elt, int n) {
	if(elt.compareTo(data) < 0) {
	    return new Branch<T>(left.remove(elt, n), leftHeight, data, multiplicity, right, rightHeight);
	} else if(elt.compareTo(data) > 0) {
	    return new Branch<T>(left, leftHeight, data, multiplicity, right.remove(elt, n), rightHeight);
	} else {
	    return new Branch<T>(left, leftHeight, data, Math.max(multiplicity - n, 0), right, rightHeight);
	}
    }

    public Bag<T> sum(Bag<T> u) {
	return u.sum(left).sum(right).add(data, multiplicity);	
    }

    public Bag<T> union(Bag<T> u) {
	return u.union(left).union(right).add(data, Math.max(this.multiplicity - u.multiplicity(data), 0));
    }

    public Bag<T> inter(Bag<T> u) {
	if(u.member(data)) {
	    return left.inter(u).union(right.inter(u)).add(data, Math.min(multiplicity, u.multiplicity(data)));
	} else {
		return left.inter(u).union(right.inter(u));
	}
    }
    
    public Bag<T> diff(Bag<T> u) {
	Bag<T> newU = u.remove(data, multiplicity);
	return left.diff(newU).inter(right.diff(newU));
    }

    public boolean equal(Bag<T> u) {
	return this.subset(u) && u.subset(this);
    }
    
    public boolean subset(Bag<T> u) {
	return (multiplicity <= u.multiplicity(data)) && left.subset(u) && right.subset(u);
    }

    public String toString() {
	if(multiplicity > 0) {
	    return  "{(" + data + ":" + multiplicity + ")" + left.toString() +
		right.toString() + "}";
	} else {
	    return "";
	}
    }

    public Sequence<T> seq() {
	return this;
    }
    
    public T here() { 
	return data;
    }

    public boolean notEmpty() {
	return true;
    }

    public Sequence<T> next() {
	return new AppendSequence<T>(left.seq(), right.seq());
    }
} 
