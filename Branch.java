package data1;

public class Branch implements FiniteSet {

    int key;
    FiniteSet left;
    FiniteSet right;
    
    //Constructor for Branch. Takes an int for the value of the tree
    //at the point, and two FiniteSets for the left and right branches of the tree
    public Branch(FiniteSet left, int key, FiniteSet right) {
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

    public FiniteSet add(int elt) {
	if(elt == key) {
	    return this; 
	} else if(elt < key) {
	    return new Branch(left.add(elt), key, right);
	} else {
	    return new Branch(left, key, right.add(elt));
	}
    }

    public FiniteSet remove(int elt) {
	if(elt < key) {
	    return new Branch(left.remove(elt), key, right);
	} else if(elt > key) {
	    return new Branch(left, key, right.remove(elt));
	} else {
	    return left.union(right);
	}
    }

    public FiniteSet union(FiniteSet u) {
	return u.union(left).union(right).add(key);	
    }

    public FiniteSet inter(FiniteSet u) {
	if(u.member(key)) {
	    return new Branch(left.inter(u), key, right.inter(u));
	} else {
	    return left.union(right).inter(u);
	}
    }
    
    public FiniteSet diff(FiniteSet u) {
	return left.union(right).diff(u.remove(key));
    }

    public boolean equal(FiniteSet u) {
	return this.subset(u) && u.subset(this);
    }
    
    
    public boolean subset(FiniteSet u) {
	return u.member(key) && left.subset(u) && right.subset(u);
    }

    public String toString() {
	return  "{" + key + left.toString() +
	    right.toString() + "}";
    }

} 
