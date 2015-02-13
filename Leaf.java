package data1;

public class Leaf implements FiniteSet {
    
    //Constuctor for Leaf
    //left blank since Leaf represents the empty set
    public Leaf() {}

    //Static method to create a new empty set
    public static FiniteSet empty() {
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

    public FiniteSet add(int elt) {
	return new Branch(this, elt, this);
    }

    public FiniteSet remove(int elt) {
	return this;
    }
    

    public FiniteSet union(FiniteSet u) {
	return u;
    }

    public FiniteSet inter(FiniteSet u) {
	return this;
    }

    public FiniteSet diff(FiniteSet u) {
	return u;
    }


    public boolean equal(FiniteSet u) {
	return u.isEmptyHuh();
    }

    public boolean subset(FiniteSet u) {
	return true;
    }

    public String toString() {
	return "";
    }

}
