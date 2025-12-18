package edu.ncsu.csc316.dsa.tree;

import edu.ncsu.csc316.dsa.Position;

/**
 * A skeletal implementation of the Binary Tree abstract data type. This class
 * provides implementation for common methods that can be implemented the same
 * no matter what specific type of concrete data structure is used to implement
 * the binary tree abstract data type.
 * 
 * @author Dr. King
 * @author Benjamin Uy
 * @param <E> the type of elements stored in the binary tree
 */
public abstract class AbstractBinaryTree<E> extends AbstractTree<E> implements BinaryTree<E> {
    
    
    @Override
    public Iterable<Position<E>> inOrder() {
        PositionCollection traversal = new PositionCollection();
        if (!isEmpty()) {
            inOrderHelper(root(), traversal);
        }
        return traversal;
    }
    
    private void inOrderHelper(Position<E> p, PositionCollection traversal) {
    	if (left(p) != null) {
    		inOrderHelper(left(p), traversal);
    	}
        traversal.add(p);
    	if (right(p) != null) {
    		inOrderHelper(right(p), traversal);
    	}
    }
    
    /**
     * Returns the number of children of the given Position
     * @return number of children of the given Position
     */
    @Override
    public int numChildren(Position<E> p) {
    	int totalChildren = 0;
        AbstractTreeNode<E> a = validate(p);
        
        if (left(a) != null) {
        	totalChildren++;
        } 
    	if (right(a) != null) {
    		totalChildren++;  
    	}
        
    	return totalChildren;
    }
    
    /**
     * Returns the position that is the sibling of the provided position, p
     * 
     * @param p the position for which to return the sibling of the position
     * @return the position that is the sibling of the provided position
     * @throws IllegalArgumentException if the position, p, does not represent a
     *                                  valid tree position
     */
    @Override
    public Position<E> sibling(Position<E> p) {
    	// Throw IAE if the given Position is not a leaf, branch, nor a root
        if (!isLeaf(p) && !isInternal(p) && !isRoot(p)) {
        	throw new IllegalArgumentException();
        }
    	
        Position<E> parent = parent(p);
        // If the given Position is the only child or is the root, p has no siblings
        if (parent == null || numChildren(parent) == 1) {
        	return null;
        }
        // At this point, the parent must have two children, so we must find the Position
        // that is different from the given p.
        if (left(parent) != p) {
        	return left(parent);
        } else {
        	return right(parent);
        }
    }
    
    @Override
    public Iterable<Position<E>> children(Position<E> p) {
        AbstractTreeNode<E> node = validate(p);
        PositionCollection childrenCollection = new PositionCollection();
        if (left(node) != null) {
            childrenCollection.add(left(node));
        }
        if (right(node) != null) {
            childrenCollection.add(right(node));
        }
        return childrenCollection;
    }
}