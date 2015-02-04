interface BST {
    
    //All the methods that the finite sets should be able to accomodate,
    //and should therefore be possible for both Leafs and Branches
    //except for empty since it is static

    /**
     * Method to determine how many numbers are in a BST
     * @return An int representing how many numbers are in the BST
     */
    int cardinality();
   
    /**
     * Determines if a BST is empty
     * @return true if the BST is empty, false otherwise
     */
    boolean isEmptyHuh();
  
  /**
     * Determines if a particular int
     * is a member of a BST
     * @param elt an int to check for in the BST
     * @return true if the BST contains elt, false otherwise
     */
    boolean member(int elt);

    /**
     * Adds an element to a BST.
     * Because BSTs are representing sets here, add will not
     * duplicate an element that already exists in the BST
     * @param elt The element to add to the BST
     * @return A new BST that contains all the old members plus elt
     */
    BST add(int elt);

    /**
     * Removes an elt from a BST
     * @param elt The element to remove from the BST
     * @return A new BST that contains all the elements of the old BST
     *         except elt
     */
    BST remove(int elt);

    /**
     * Finds the union of two BSTS
     * @param u A BST
     * @return A new BST that contains all the elements of both the 
     *         BST it is called on and u
     */
    BST union(BST u);

    /**
     * Finds the intersection of two BSTS
     * @param u A BST
     * @return A new BST that contains only the elements that appear both
     *         in the BST it was called on and u.
     */
    BST inter(BST u);

    /**
     * Determines the difference between two BSTs
     * @param u A BST
     * @return A new BST that contains only the elements that appear in u,
     *         but not in the BST this method is called on
     */
    BST diff(BST u);

    /**
     * Determines if two BSTs contain the same elements.
     * The two BSTs do not need to have the same structure
     * @param u A BST
     * @return true if both u and the BST this is called on contain exactly
     *         the same elements, false otherwise
     */
    boolean equal(BST u);

    /**
     * Determines if one BST is the subset of another
     * @param u A BST
     * @return true if all the elements in the BST this is called on also appear
     *         in u, false otherwise
     */
    boolean subset(BST u);
}

class Leaf implements BST {
    
    //Constuctor for Leaf
    //left blank since Leaf represents the empty set
    public Leaf() {}

    //Static method to create a new empty set
    public static BST empty() {
	return new Leaf();
    }

    public int cardinality() {
	return 0;
    }

    public boolean isEmptyHuh() {
	return true;
   }

    public boolean member(int elt) {
	return false;
    }

    public BST add(int elt) {
	return new Branch(this, elt, this);
    }

    public BST remove(int elt) {
	return this;
    }
    

    public BST union(BST u) {
	return u;
    }

    public BST inter(BST u) {
	return this;
    }

    public BST diff(BST u) {
	return u;
    }


    public boolean equal(BST u) {
	return u.isEmptyHuh();
    }

    public boolean subset(BST u) {
	return true;
    }

    public String toString() {
	return "";
    }

}

class Branch implements BST {

    int key;
    BST left;
    BST right;
    
    //Constructor for Branch. Takes an int for the value of the tree
    //at the point, and two BSTs for the left and right branches of the tree
    public Branch(BST left, int key, BST right) {
	this.left = left;
	this.right = right;
	this.key = key;
    }

    public int cardinality() {
	return 1 + left.cardinality() + right.cardinality();
    }

    public boolean isEmptyHuh() {
	return false;
    }

    public boolean member(int elt) {
	if(elt == key) {
	    return true;
	} else if(elt < key) {
	    return left.member(elt);
	} else {
	    return right.member(elt);
	}
    }

    public BST add(int elt) {
	if(elt == key) {
	    return this; 
	} else if(elt < key) {
	    return new Branch(left.add(elt), key, right);
	} else {
	    return new Branch(left, key, right.add(elt));
	}
    }

    public BST remove(int elt) {
	if(elt < key) {
	    return new Branch(left.remove(elt), key, right);
	} else if(elt > key) {
	    return new Branch(left, key, right.remove(elt));
	} else {
	    return left.union(right);
	}
    }

    public BST union(BST u) {
	return u.union(left).union(right).add(key);	
    }

    public BST inter(BST u) {
	if(u.member(key)) {
	    return new Branch(left.inter(u), key, right.inter(u));
	} else {
	    return left.union(right).inter(u);
	}
    }

    public BST diff(BST u) {
	if(u.member(key)) {
	    return left.union(right).diff(u);
	} else {
	    return new Branch(left.diff(u), key, right.diff(u));
	}
    }

    public boolean equal(BST u) {
	return this.subset(u) && u.subset(this);
    }
    
    
    public boolean subset(BST u) {
	return u.member(key) && left.subset(u) && right.subset(u);
    }

    public String toString() {
	return  "{" + key + " " + left.toString() + 
	    " " + right.toString() + "}";
    }

} 

class Tests {

    public static void main(String[] args) {
	Leaf l = new Leaf();
	Branch b1 = new Branch(l, 1, l);
	Branch b3 = new Branch(l, 3, l);
	Branch b7 = new Branch(l, 7, l);
	Branch b9 = new Branch(l, 9, l);
	Branch b2 = new Branch(b1, 2, b3);
	Branch b8 = new Branch(b7, 8, b9);
	Branch b5 = new Branch(b2, 5, b8);

	BST c = new Leaf();
	c = c.add(5).add(2).add(3).add(7).add(1);
	BST d = c.add(3).add(1).add(2).add(5).add(7);

	
	System.out.println("b5 should contain 1,2,3,7,9,2,8,5 - Does contain:\n" + b5);
	System.out.println("should add 4 to previous BST to the left of 5"
			   + b5.add(4));
	System.out.println("Cardinality of b5 should be 7, is " + b5.cardinality());
	System.out.println("b5.member(7) should return true, does return:\n"
			   + b5.member(7));
	System.out.println("b5.member(12) should return false, does return:\n"
			   + b5.member(12));
	System.out.println("c should contain 1,2,3,5,7 - Does contain:\n" + c);
	System.out.println("b5.subset(c4) should return false, does return:\n"
			   + b5.subset(c));
	System.out.println("c.subset(b5) should return true, does return:\n"
			   + c.subset(b5));
	System.out.println("c.union(b5) should return {1,2,3,5,7,8,9}, does return:\n"
			   + c.union(b5));
	System.out.println("c.inter(b5) should return c, does return:\n"
			   + c.inter(b5));
	System.out.println("c.equal(d) should return true, does return:\n " 
			   + c.equal(d));
	System.out.println("c.remove(5) should return {1,2,3,7}, does return:\n"
			   + c.remove(5));
    }
}
