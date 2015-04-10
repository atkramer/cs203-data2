public class Data2 {

    public static Bag<Integer> randIntBag(int numElements) {
	Bag<Integer> temp = Leaf.<Integer>empty();
	for(; numElements > 0; numElements--) {
	    temp = temp.add((int) (Math.random() * 100), (int) (Math.random() * 5) + 1);
	}
	return temp;
    }

    public static Bag<Character> randCharBag(int numElements) {
	Bag<Character> temp = Leaf.<Character>empty();
	for(; numElements > 0; numElements--) {
	    temp = temp.add((char) ((int) (Math.random() * 57) + 65), (int) (Math.random() * 5) + 1);
	}
	return temp;
    }

    public static boolean balancedHuh(Bag bag) {
	return bag.isEmptyHuh() ||
	    (Math.abs(((Branch) bag).leftHeight - ((Branch) bag).rightHeight) <= 1 &&
	     (balancedHuh(((Branch) bag).left) && balancedHuh(((Branch) bag).right)));
	    
    }

    //Test Functions

    //An element should be a member of A-B iff it its multiplicity in A is greater than
    //its muliplicity in B
    public static <T extends Comparable> boolean testDiffMembership(Bag<T> bagOne, Bag<T> bagTwo, T elt) {
	return bagTwo.diff(bagOne).member(elt) == ( bagOne.multiplicity(elt) > bagTwo.multiplicity(elt) );
    }

    //An element should be a member of A intersect B iff it is a member of A and a member of B
    public static <T extends Comparable> boolean testInterMembership(Bag<T> bagOne, Bag<T> bagTwo, T elt) {
	return bagOne.inter(bagTwo).member(elt) == ( bagOne.member(elt) && bagTwo.member(elt) );
    }

    //An element should be a member of A union B iff it is a member of A or a member of B
    public static <T extends Comparable> boolean testUnionMembership(Bag<T> bagOne, Bag<T> bagTwo, T elt) {
	return bagOne.union(bagTwo).member(elt) == ( bagOne.member(elt) || bagTwo.member(elt) );
    }

    //An element should be a member of A sum B iff it is a member of A or a member of B
    public static <T extends Comparable> boolean testSumMembership(Bag<T> bagOne, Bag<T> bagTwo, T elt) {
	return bagOne.sum(bagTwo).member(elt) == ( bagOne.member(elt) || bagTwo.member(elt) );
    }

    //When removing an element from a bag, Cardinality of the bag should decrease iff
    //that element appeared at least once already in the bag
    public static <T extends Comparable> boolean testRemoveSize(Bag<T> bag, T elt) {
	return ( bag.remove(elt, 1).cardinality() < bag.cardinality() ) ==
	    (bag.multiplicity(elt) >= 1 );
    }

    //When adding an element to a bag, its Cardinality should always increase
    public static <T extends Comparable> boolean testAddSize(Bag<T> bag, T elt) {
	return bag.add(elt, 1).cardinality() > bag.cardinality();
    }

    //Two bags should be equal iff they are both subsets of one another
    public static <T extends Comparable> boolean testEquality(Bag<T> bagOne, Bag<T> bagTwo) {
	return bagOne.equal(bagTwo) == (bagOne.subset(bagTwo) && bagTwo.subset(bagOne));
    }

    //Adding an element should increase the number of distinct elements in a set iff
    //that element was not already a member of the set
    public static <T extends Comparable> boolean testNumDistinctElts(Bag<T> bag, T elt) {
	return ( bag.numDistinctElts() < bag.add(elt, 1).numDistinctElts() ) == !bag.member(elt);
    }

    public static void main(String[] args) {
	Bag<Integer> test1 = new Leaf<Integer>();
	test1 = test1.add(3, 1);
	test1 = test1.add(2, 3);
	test1 = test1.add(5, 1);
	test1 = test1.add(9, 4);
	test1 = test1.add(5, 2);
	test1 = test1.add(1, 2);

	Bag<Integer> test2 = Leaf.<Integer>empty();
	test2 = test2.add(3, 3);
	test2 = test2.add(1, 1);
	test2 = test2.add(4, 2);
	test2 = test2.add(6, 3);
	test2 = test2.add(5, 2);
	test2 = test2.add(2, 1);
	test2 = test2.add(1, 4);

	System.out.println("test1 is " + test1);
	System.out.println("test2 is " + test2);
	System.out.println("test1.inter(test2) should be (1:2),(2:1),(3:1),(5:2)\n and is " + test1.inter(test2));
	System.out.println("test1.sum(test2) should be (1:7),(2:4),(3:4),(4:2),(5:5),(6:3),(9:4)\n and is " + test1.sum(test2));
	System.out.println("test1.union(test2) should be (1:5),(2:3),(3:3),(4:2),(5:3),(6:3),(9:4)\n and is " + test1.union(test2));
	System.out.println("test1.diff(test2) should be (1:3),(3:2),(4:2),(6:3)\n and is " + test1.diff(test2));
	System.out.println("test2.diff(test1) should be (2:2),(5:1),(9:4)\n and is " + test2.diff(test1));


	Bag<Integer> rand1 = randIntBag(25);
	System.out.println("rand1 is \n" + rand1);
	System.out.println("rand1 balanced? == " + balancedHuh(rand1));
	
	Bag<Integer> rand2 = randIntBag(500);
	System.out.println("rand2 balanced? == " + balancedHuh(rand2));
	
	Bag<Character> randChars = randCharBag(25);
	System.out.println(randChars);
	
	//INVARIANT TESTS
	
	int failedTests = 0;
	for(int i=0; i < 100; i++) {
	    Bag<Integer> temp1 = randIntBag(10);
	    Bag<Integer> temp2 = randIntBag(10);
	    
	    for(int x=0; x < 100; x = x+10) {
		
		if(!testInterMembership(temp1, temp2, x)) {
		    System.out.println("Intersection Membership Failure");
		    failedTests++;
		}
		if(!testUnionMembership(temp1, temp2, x)) {
		    System.out.println("Union Membership Failure");
		    failedTests++;
		}
		if(!testDiffMembership(temp1, temp2, x)) {
		    System.out.println("Difference membership Failure");
		    failedTests++;
		}
		if(!testSumMembership(temp1, temp2, x)) {
		    System.out.println("Sum membership Failure");
		    failedTests++;
		}
		if(!testAddSize(temp1, x)) {
		    System.out.println("Add size failure");
		    failedTests++;
		}
		if(!testRemoveSize(temp2, x)) {
		    System.out.println("Remove size failure");
		    failedTests++;
		}
		if(!testEquality(temp1, temp2)) {
		    System.out.println("Equality Failure");
		    failedTests++;
		}
		if(!testNumDistinctElts(temp1, x)) {
		    System.out.println("Distinct Element Failure");
		    failedTests++;
		}
		if(!balancedHuh(temp1)) {
		    System.out.println("Self balancing failure");
		    failedTests++;
		}
	    }
	}
	System.out.println(failedTests + " tests failed");
    }

}

