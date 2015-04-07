public class Data2 {

    public static Bag<Integer> randIntBag(int numElements) {
	Bag<Integer> temp = Leaf.<Integer>empty();
	for(; numElements > 0; numElements--) {
	    temp = temp.add((int) (Math.random() * 100), (int) (Math.random() * 5) + 1);
	}
	return temp;
    }

    public static boolean balancedHuh(Bag bag) {
	return bag.isEmptyHuh() ||
	    (Math.abs(((Branch) bag).leftHeight - ((Branch) bag).rightHeight) <= 1 &&
	     (balancedHuh(((Branch) bag).left) && balancedHuh(((Branch) bag).right)));
	    
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
	System.out.println("test1.inter(test2) should have (1:2),(2:1),(3:1),(5:2)\n and is " + test1.inter(test2));
	System.out.println("test1.union(test2) should have (1:7),(2:4),(3:4),(4:2),(5:5),(6:3),(9:4)\n and is " + test1.union(test2));

	Bag<Integer> rand1 = randIntBag(25);
	System.out.println("rand1 is \n" + rand1);
	System.out.println("rand1 balanced? == " + balancedHuh(rand1));

	Bag<Integer> rand2 = randIntBag(500);
	System.out.println("rand2 balanced? == " + balancedHuh(rand2));
    }
}
