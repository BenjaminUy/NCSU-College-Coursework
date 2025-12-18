package edu.ncsu.csc316.dsa.disjoint_set;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.Position;

/**
 * Test class for UpTreeDisjointSetForest
 * Checks the expected outputs of the Disjoint Set abstract data type 
 * behaviors when using an up-tree data structure
 *
 * @author Dr. King
 * @author Benjamin Uy
 */
public class UpTreeDisjointSetForestTest {

    private DisjointSetForest<String> set;

    /**
     * Create a new instance of a up-tree forest before each test case executes
     */     
    @Before
    public void setUp() {
        set = new UpTreeDisjointSetForest<>();
    }
    
    /**
     * Test the output of the makeSet behavior
     */ 
    @Test
    public void testMakeSet() {
        Position<String> one = set.makeSet("one");
        assertEquals("one", one.getElement());
        Position<String> two = set.makeSet("two");
        assertEquals("two", two.getElement());
        Position<String> three = set.makeSet("three");
        assertEquals("three", three.getElement());
    }

    /**
     * Test the output of the union-find behaviors
     */     
    @Test
    public void testUnionFind() {
        Position<String> one = set.makeSet("one");
        Position<String> two = set.makeSet("two");
        Position<String> three = set.makeSet("three");
        Position<String> four = set.makeSet("four");
        Position<String> five = set.makeSet("five");
//        Position<String> six = set.makeSet("six");
//        Position<String> seven = set.makeSet("seven");
//        Position<String> eight = set.makeSet("eight");
//        Position<String> nine = set.makeSet("nine");
//        Position<String> ten = set.makeSet("ten");
        
        assertEquals(one, set.find("one"));
        
        // Union of two disjoint sets with the same size, thus two will be the root
        set.union(one, two); 
        assertEquals(two, set.find("one"));
        assertEquals(two, set.find("two"));
        
        // Union of two sets with different sizes, the set with root position two 
        // should remain as the root
        set.union(two, three);
        assertEquals(two, set.find("one"));
        assertEquals(two, set.find("two"));
        assertEquals(two, set.find("three"));
        
        // Union of two disjoint sets with the same size, thus five will be the root
        set.union(four, five);
        assertEquals(five, set.find("four"));
        assertEquals(five, set.find("five"));
        
        // Union of two sets with different sizes, the set with root position two 
        // should remain as the root
        set.union(two, five);
        assertEquals(two, set.find("one"));
        assertEquals(two, set.find("two"));
        assertEquals(two, set.find("three"));
        assertEquals(two, set.find("four"));
        assertEquals(two, set.find("five"));
    }
}