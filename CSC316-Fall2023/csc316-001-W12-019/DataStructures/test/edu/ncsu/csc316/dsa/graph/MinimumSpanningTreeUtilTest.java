package edu.ncsu.csc316.dsa.graph;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.Weighted;
import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;
import edu.ncsu.csc316.dsa.list.positional.PositionalList;

/**
 * Test class for MinimumSpanningTreeUtil
 * Checks the expected outputs of Prim-Jarnik's algorithm
 * and Kruskal's algorithm
 *
 * @author Dr. King
 * @author Benjamin Uy
 */
public class MinimumSpanningTreeUtilTest {

    private Graph<String, Highway> undirectedGraph;
    private Graph<String, Highway> directedGraph;
    
    private Vertex<String> vertexA;
    private Vertex<String> vertexB;
    private Vertex<String> vertexC;
    private Vertex<String> vertexD;
    private Vertex<String> vertexE;
    
    private Edge<Highway> edge1;
    private Edge<Highway> edge2;
    private Edge<Highway> edge3;
    private Edge<Highway> edge4;
//    private Edge<Highway> edge5;
//    private Edge<Highway> edge6;
//    private Edge<Highway> edge7;
    
    /**
     * Private inner class based off of Workshop provided code of a custom object that implements the Weighted interface.
     * This object will be used for testing purposes.
     * @author Benjamin Uy
     */
    private class Highway implements Weighted {
        private String name;
        private int length;
        
        public Highway(String n, int l) {
            setName(n);
            setLength(l);
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getLength() {
            return length;
        }

        public void setLength(int length) {
            this.length = length;
        }

        @Override
        public int getWeight() {
            return getLength();
        }
    }
    
    /**
     * Create a new instance of an edge list graph before each test case executes
     */ 
    @Before
    public void setUp() {
        undirectedGraph = new EdgeListGraph<String, Highway>();
        directedGraph = new EdgeListGraph<String, Highway>(true);
    }
    
    /**
     * Constructs the undirected graph and initializes the vertexes and edges for testing
     */
    private void buildUndirectedSample() {
    	vertexA = undirectedGraph.insertVertex("A");
        vertexB = undirectedGraph.insertVertex("B");
        vertexC = undirectedGraph.insertVertex("C");
        vertexD = undirectedGraph.insertVertex("D");
        
        edge1 = undirectedGraph.insertEdge(vertexA, vertexB, new Highway("AB", 5));
        edge2 = undirectedGraph.insertEdge(vertexA, vertexC, new Highway("AC", 10));
        edge3 = undirectedGraph.insertEdge(vertexA, vertexD, new Highway("AD", 15));
        edge4 = undirectedGraph.insertEdge(vertexB, vertexC, new Highway("BC", 20));
        undirectedGraph.insertEdge(vertexB, vertexD, new Highway("BD", 25));
        undirectedGraph.insertEdge(vertexC, vertexD, new Highway("CD", 30));
    }
    
    /**
     * Constructs the directed graph and initializes the vertexes and edges for testing
     */
    private void buildDirectedSample() {
    	vertexA = directedGraph.insertVertex("A");
        vertexB = directedGraph.insertVertex("B");
        vertexC = directedGraph.insertVertex("C");
        vertexD = directedGraph.insertVertex("D");
        vertexE = directedGraph.insertVertex("E");
        
        edge1 = directedGraph.insertEdge(vertexA, vertexB, new Highway("AB", 5));
        edge2 = directedGraph.insertEdge(vertexB, vertexD, new Highway("BD", 10));
        edge3 = directedGraph.insertEdge(vertexD, vertexE, new Highway("DE", 15));
        edge4 = directedGraph.insertEdge(vertexA, vertexC, new Highway("AC", 20));
        directedGraph.insertEdge(vertexA, vertexD, new Highway("AD", 25));
        directedGraph.insertEdge(vertexD, vertexA, new Highway("DA", 30));
        directedGraph.insertEdge(vertexD, vertexC, new Highway("DC", 35));
    }
	
    /**
     * Test the output of Prim-Jarnik's algorithm
     */ 
    @Test
    public void testPrimJarnik() {
         buildUndirectedSample();
         PositionalList<Edge<Highway>> tree = MinimumSpanningTreeUtil.primJarnik(undirectedGraph);
         
         assertEquals(3, tree.size());
         Iterator<Edge<Highway>> it = tree.iterator();
         assertEquals(edge1, it.next());
         assertEquals(edge2, it.next());
         assertEquals(edge3, it.next());
         assertFalse(it.hasNext());
         
         buildDirectedSample();
         tree = MinimumSpanningTreeUtil.primJarnik(directedGraph);
         
         assertEquals(4, tree.size());
         it = tree.iterator();
         assertEquals(edge1, it.next());
         assertEquals(edge2, it.next());
         assertEquals(edge3, it.next());
         assertEquals(edge4, it.next());
         assertFalse(it.hasNext());
    }
    
    /**
     * Test the output of Kruskal's algorithm
     */ 
    @Test
    public void testKruskal() {
    	buildUndirectedSample();
        PositionalList<Edge<Highway>> tree = MinimumSpanningTreeUtil.kruskal(undirectedGraph);
        
        assertEquals(3, tree.size());
        Iterator<Edge<Highway>> it = tree.iterator();
        assertEquals(edge1, it.next());
        assertEquals(edge2, it.next());
        assertEquals(edge3, it.next());
        assertFalse(it.hasNext());
        
        buildDirectedSample();
        tree = MinimumSpanningTreeUtil.kruskal(directedGraph);
        
        assertEquals(4, tree.size());
        it = tree.iterator();
        assertEquals("AB", it.next().getElement().name);
        assertEquals("BD", it.next().getElement().name);
        assertEquals("DE", it.next().getElement().name);
        assertEquals("AC", it.next().getElement().name);
        assertFalse(it.hasNext());
    }
    
}