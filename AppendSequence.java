public class AppendSequence<T extends Comparable> implements Sequence<T> {
    Sequence<T> left;
    Sequence<T> right;

    public AppendSequence(Sequence<T> left, Sequence<T> right) {
	this.left = left;
	this.right = right;
    }

    public boolean notEmpty() {
	return left.notEmpty() || right.notEmpty();
    }

    public T here() {
	if(left.notEmpty()) {
	    return left.here();
	} else {
	    return right.here();
	}
    }

    public Sequence<T> next() {
	if(left.notEmpty()) {
	    return new AppendSequence<T>(left.next(), right);
	} else {
	    return new AppendSequence<T>(left, right.next());
	}
    }
}
