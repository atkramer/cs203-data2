public class Data2 {

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
    }
}
