package data1;

public class Tests {

    /**
     * Generates a random FiniteSet
     * @param sizeRange Integer specifying the range of sizes
     * @param intRange Integer specifying the range of integers to include
     *                 in the FiniteSet
     * @return A new FiniteSet with size between 0 and sizeRange containing integers
     *         between -1/2*intRange and 1/2*intRange
     */
    public static FiniteSet randFiniteSet(int sizeRange, int intRange) {
	FiniteSet temp = new Leaf();
	int size = (int) (sizeRange * Math.random());
	for(; size > 0; size--) {
	    temp = temp.add((int) ((Math.random()-.5) * intRange));
	}
	return temp;
    }
    
    //Static methods for testing properties of FiniteSets
    
    //Tests: |union(t u)| <= |t| + |u|
    public static boolean testUnionSize(FiniteSet t, FiniteSet u) {
	return t.union(u).cardinality() <= t.cardinality() + u.cardinality();
    }

    //Tests: |inter(t u)| <= |t| /\ |inter(t u)| <= |u|
    public static boolean testInterSize(FiniteSet t, FiniteSet u) {
	return t.inter(u).cardinality() <= t.cardinality() &&
	    t.inter(u).cardinality() <= u.cardinality();
    }
   
    //Tests: member(add(t x) y) = true <-> x = y \/ member(t y) = true"
    public static boolean testAddMembership(FiniteSet t, int x, int y) {
	return t.add(x).member(y) == ( x == y || t.member(y) );
    } 

    //Tests: member(remove(t x) y) = true <-> x =/= y /\ member(t y) = true
    public static boolean testRemoveMembership(FiniteSet t, int x, int y) {
	return t.remove(x).member(y) == ( x != y && t.member(y) ); 
    }

    //Tests: member(union(t u) x) = true <-> member(t x) = true \/ member(u x) = true
    public static boolean testUnionMembership(FiniteSet t, FiniteSet u, int x) {
	return t.union(u).member(x) == ( t.member(x) || u.member(x) );
    }
    
    //Tests: member (inter s s') x = true <-> member s x = true /\ member s' x = true
    public static boolean testInterMembership(FiniteSet t, FiniteSet u, int x) {
	return t.inter(u).member(x) == ( t.member(x) && u.member(x) );
    }

    //Tests: member(diff(t u) x) = true <-> member(u x) /\ not(member(t x)) = true
    public static boolean testDiffMembership(FiniteSet t, FiniteSet u, int x) {
	return t.diff(u).member(x) == ( u.member(x) && !t.member(x) );
    }

    //Tests: union(t u) = t <-> isEmpty(u)
    public static boolean testEmptyUnion(FiniteSet t, FiniteSet u) {
	return t.union(u).equals(t) == u.isEmptyHuh();
    }

    //Tests: equal(t u) <-> subset(t u) /\ subset(u t)
    public static boolean testEquality(FiniteSet t, FiniteSet u) {
	return t.equal(u) == ( t.subset(u) && u.subset(t) );
    }


    public static void main(String[] args) {

	FiniteSet l = Leaf.empty();
	FiniteSet b = l.add(5).add(2).add(8).add(1).add(3).add(7).add(9);
	FiniteSet c = l.add(5).add(2).add(3).add(7).add(1);
	FiniteSet d = l.add(3).add(1).add(2).add(5).add(7);
	FiniteSet e = l.add(5).add(2).add(3).add(4);

	// Single Case tests for FiniteSet methods
	System.out.println("\n---Begin Single Case Tests---\n");

	System.out.println("FiniteSet b is " + b);
	System.out.println("FiniteSet c is " + c);
	System.out.println("FiniteSet d is " + d);
	System.out.println("FiniteSet e is " + e);
	
	System.out.println("b.add(4) should add 4 to previous FiniteSet to the left of 5:\n"
			   + b.add(4));
	System.out.println("Cardinality of b should be 7, is:\n" + b.cardinality());
	System.out.println("b.member(7) should be true, is:\n"
			   + b.member(7));
	System.out.println("b.member(12) should be false, is:\n"
			   + b.member(12));
	System.out.println("b.subset(c) should be false, is:\n"
			   + b.subset(c));
	System.out.println("c.subset(b) should be true, is:\n"
			   + c.subset(b));
	System.out.println("c.union(b) should be {1,2,3,5,7,8,9}, is:\n"
			   + c.union(b));
	System.out.println("c.inter(e) should be {5,2,3}, is:\n"
			   + c.inter(e));
	System.out.println("c.equal(d) should be true, is:\n " 
			   + c.equal(d));
	System.out.println("c.remove(5) should be {1,2,3,7}, is:\n"
			   + c.remove(5));
	System.out.println("e.diff(c) should be {1,7}, is:\n"
			   + e.diff(c));

	System.out.println("\n5 random FiniteSets generated with randFiniteSet(20,50)\n" +
			   randFiniteSet(20,50) + "\n" +
			   randFiniteSet(20,50) + "\n" +
			   randFiniteSet(20,50) + "\n" +
			   randFiniteSet(20,50) + "\n" +
			   randFiniteSet(20,50) + "\n");

	//Tests on properties of Finite Sets
	System.out.println("\n---Begin Property Tests---\n");

	int testsFailed = 0;		
	int sizeRange = 20;
	int intRange = 50;
	
	//Run testUnionSize with random sets
	for(int i = 0; i < 1000; i++) {
	    FiniteSet temp1 = randFiniteSet(sizeRange, intRange);
	    FiniteSet temp2 = randFiniteSet(sizeRange, intRange);
	    if(!testUnionSize(temp1, temp2)) {
		System.out.println("Union Size Failure with FiniteSets:\n" + temp1 + ",\n"
				   + temp2);
		testsFailed++;
	    }
	}

	//Run testInterSize with random sets
	for(int i = 0; i < 1000; i++) {
	    FiniteSet temp1 = randFiniteSet(sizeRange, intRange);
	    FiniteSet temp2 = randFiniteSet(sizeRange, intRange);
	    if(!testInterSize(temp1, temp2)) {
		System.out.println("Intersection Size Failure with FiniteSets:\n" + temp1 + ",\n"
				   + temp2);
		testsFailed++;
	    }
	}


	FiniteSet finiteSetA = randFiniteSet(sizeRange, intRange);

	//Run testAddMembership with a single set, exhaustively testing all values
	//of x,y in a range slightly larger than the range of the ints in the set
	for(int x = -30; x < 30; x++) {
	    for(int y = -30; y < 30; y++) {
		if(!testAddMembership(finiteSetA, x, y)) {
		    System.out.println("Add Membership Failure on FiniteSet:\n" + finiteSetA + 
				       "\nwhere x = " + x + ", y = " + y);
		    testsFailed++;
		}
	    }
	}

	FiniteSet finiteSetB = randFiniteSet(sizeRange, intRange);

	//Run testRemoveMembership with a single set, exhaustively testing all values
	//of x,y in a range slightly larger than the range of the ints in the set
	for(int x = -30; x < 30; x++) {
	    for(int y = -30; y < 30; y++) {
		if(!testRemoveMembership(finiteSetA, x, y)) {
		    System.out.println("Remove Membership Failure on FiniteSet:\n" + finiteSetB + 
				       "\nwhere x = " + x + ", y = " + y);
		    testsFailed++;
		}
	    }
	}

	//Run testUnionMembership with random sets and random values of x within
	//the possible intRange of the sets
	for(int i = 0; i < 1000; i++) {
	    int x = (int) (Math.random() - .5 * intRange);
	    FiniteSet temp1 = randFiniteSet(sizeRange, intRange);
	    FiniteSet temp2 = randFiniteSet(sizeRange, intRange);
	    if(!testUnionMembership(finiteSetA, finiteSetB, x)) {
		System.out.println("Union Membership Failure on FiniteSets\n" + temp1 +
				   ",\n" + temp2 + "\nx = " + x);
		testsFailed++;
	    }
	}
	
	//Run testInterMembership with random sets and random values of x within
	//the possible intRange of the sets
	for(int i = 0; i < 1000; i++) {
	    int x = (int) (Math.random() - .5 * intRange);
	    FiniteSet temp1 = randFiniteSet(sizeRange, intRange);
	    FiniteSet temp2 = randFiniteSet(sizeRange, intRange);
	    if(!testInterMembership(finiteSetA, finiteSetB, x)) {
		System.out.println("Intersection Membership Failure on FiniteSets\n" + temp1 +
				   ",\n" + temp2 + "\nx = " + x);
		testsFailed++;
	    }
	}

	//Run testDiffMembership with random sets and random values of x within
	//the possible intRange of the sets
	for(int i = 0; i < 1000; i++) {
	    int x = (int) (Math.random() - .5 * intRange);
	    FiniteSet temp1 = randFiniteSet(sizeRange, intRange);
	    FiniteSet temp2 = randFiniteSet(sizeRange, intRange);
	    if(!testDiffMembership(finiteSetA, finiteSetB, x)) {
		System.out.println("Difference Membership Failure on FiniteSets\n" + temp1 +
				   ",\n" + temp2 + "\nx = " + x);
		testsFailed++;
	    }
	}

	if(testsFailed == 0) {
	    System.out.println("All tests passed!");
	} else {
	    System.out.println(testsFailed + " tests failed. See printed errors");
	}
	
    }
}
