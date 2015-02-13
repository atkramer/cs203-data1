package data1;

public class Tests {

    public static FiniteSet randFiniteSet(int size) {
	FiniteSet temp = new Leaf();
	for(; size > 0; size--) {
	    temp = temp.add((int) ((Math.random()-.5) * 100));
	}
	return temp;
    }
    
    //Static methods for testing properties of FiniteSets
    
    //Tests: |t U u| <= |t| + |u|
    public static boolean testUnionSize(FiniteSet t, FiniteSet u) {
	return t.union(u).cardinality() <= t.cardinality() + u.cardinality();
    }
   
    //Tests: "member (add t x) y = true <-> x = y \/ member t y = true"
    public static boolean testAddMembership(FiniteSet t, int x, int y) {
	return t.add(x).member(y) == ( x == y || t.member(y) );
    } 

    //Tests: member (union s s') x = true <-> member s x = true \/ member s' x = true
    public static boolean testUnionMembership(FiniteSet t, FiniteSet u, int x) {
	return t.union(u).member(x) == ( t.member(x) || u.member(x) );
    }
    
    //Tests: member (inter s s') x = true <-> member s x = true /\ member s' x = true
    public static boolean testInterMembership(FiniteSet t, FiniteSet u, int x) {
	return t.inter(u).member(x) == ( t.member(x) && u.member(x) );
    }

    //Tests: (union s s') = s <-> (isEmpty s) /\ (isEmpty s')
    public static boolean testEmptyUnion(FiniteSet t, FiniteSet u) {
	return t.union(u).equals(t) == t.isEmptyHuh() || u.isEmptyHuh();
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



	FiniteSet finiteSetA = randFiniteSet(20);
	FiniteSet finiteSetB = randFiniteSet(20);
	FiniteSet emptySet = Leaf.empty();

	//Tests on properties of Finite Sets
	System.out.println("\n---Begin Property Tests---\n");

	System.out.println("testEmptyUnion(finiteSetA, emptytest) should return true, does return "
			   + testEmptyUnion(finiteSetA, emptySet));

	System.out.println("testEmptyUnion(finiteSetB, emptytest) should return true, does return "
			   + testEmptyUnion(finiteSetB, emptySet));

	System.out.println("testEmptyUnion(finiteSetA, finiteSetB) should return false, does return "
			   + testEmptyUnion(finiteSetA, finiteSetB));
	

	for(int i = 0; i < 100; i++) {
	    FiniteSet temp1 = randFiniteSet(10);
	    FiniteSet temp2 = randFiniteSet(10);
	    if(!testUnionSize(temp1, temp2)) 
		System.out.println("Union Size Failure with FiniteSets:\n" + temp1 + ",\n"
				   + temp2);
	    
	}

	
	for(int x = -10; x < 10; x++) {
	    for(int y = -10; y < 10; y++) {
		if(!testAddMembership(finiteSetA, x, y)) {
		    System.out.println("Add Membership Failure on FiniteSet:\n" + finiteSetA + 
				       "\nwhere x = " + x + ", y = " + y);
		}
	    }
	}

	for(int x = -10; x < 10; x++) {
	    if(!testUnionMembership(finiteSetA, finiteSetB, x)) {
		System.out.println("Union Membership Failure on FiniteSets\n" + finiteSetA +
				   ",\n" + finiteSetB + "\nx = " + x);
	    }
	}
	
	for(int x = -10; x < 10; x++) {
	    if(!testInterMembership(finiteSetA, finiteSetB, x)) {
		System.out.println("Intersection Membership Failure on FiniteSets\n" 
				   + finiteSetA +",\n" + finiteSetB + ",\nx = " + x);
	    }
	}
	
    }
}
