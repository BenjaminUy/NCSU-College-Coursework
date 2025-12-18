package edu.ncsu.csc316.dsa.graph;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.Weighted;
import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;
import edu.ncsu.csc316.dsa.map.Map;

/**
 * Test class for ShortestPathUtil
 * Checks the expected outputs of Dijksra's algorithm
 * and the shortest path tree construction method
 *
 * @author Dr. King
 * @author Benjamin Uy
 */
public class ShortestPathUtilTest {
	
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
//    private Edge<Highway> edge4;
//    private Edge<Highway> edge5;
    private Edge<Highway> edge6;
    private Edge<Highway> edge7;
    
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
        undirectedGraph.insertEdge(vertexB, vertexC, new Highway("BC", 20));
        undirectedGraph.insertEdge(vertexB, vertexD, new Highway("BD", 25));
        edge6 = undirectedGraph.insertEdge(vertexC, vertexD, new Highway("CD", 30));
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
        edge3 = directedGraph.insertEdge(vertexE, vertexD, new Highway("ED", 15));
        directedGraph.insertEdge(vertexA, vertexC, new Highway("AC", 20));
        directedGraph.insertEdge(vertexA, vertexD, new Highway("AD", 25));
        edge6 = directedGraph.insertEdge(vertexD, vertexA, new Highway("DA", 30));
        edge7 = directedGraph.insertEdge(vertexD, vertexC, new Highway("DC", 35));
    }

    /**
     * Test the output of Dijkstra's algorithm
     */ 
    @Test
    public void testDijkstra() {
         buildUndirectedSample();
         
         Map<Vertex<String>, Integer> map = ShortestPathUtil.dijkstra(undirectedGraph, vertexA);
         assertEquals(4, map.size());
         assertEquals(0, (int) map.get(vertexA));
         assertEquals(5, (int) map.get(vertexB));
         assertEquals(10, (int) map.get(vertexC));
         assertEquals(15, (int) map.get(vertexD));
         
         map = ShortestPathUtil.dijkstra(undirectedGraph, vertexB);
         assertEquals(4, map.size());
         assertEquals(0, (int) map.get(vertexB));
         assertEquals(5, (int) map.get(vertexA));
         assertEquals(15, (int) map.get(vertexC));
         assertEquals(20, (int) map.get(vertexD));
         
         map = ShortestPathUtil.dijkstra(undirectedGraph, vertexC);
         assertEquals(4, map.size());
         assertEquals(0, (int) map.get(vertexC));
         assertEquals(10, (int) map.get(vertexA));
         assertEquals(15, (int) map.get(vertexB));
         assertEquals(25, (int) map.get(vertexD));
         
         map = ShortestPathUtil.dijkstra(undirectedGraph, vertexD);
         assertEquals(4, map.size());
         assertEquals(0, (int) map.get(vertexD));
         assertEquals(20, (int) map.get(vertexB));
         assertEquals(25, (int) map.get(vertexC));
         assertEquals(15, (int) map.get(vertexA));
         
         buildDirectedSample();
         map = ShortestPathUtil.dijkstra(directedGraph, vertexE);
         assertEquals(5, map.size());
         assertEquals(0, (int) map.get(vertexE));
         assertEquals(15, (int) map.get(vertexD));
         assertEquals(45, (int) map.get(vertexA));
         assertEquals(50, (int) map.get(vertexC));
         assertEquals(50, (int) map.get(vertexB));
    }
    
    /**
     * Test the output of the shortest path tree construction method
     */ 
    @Test
    public void testShortestPathTree() {
        buildUndirectedSample();
        
        Map<Vertex<String>, Integer> costs = ShortestPathUtil.dijkstra(undirectedGraph, vertexA);
        Map<Vertex<String>, Edge<Highway>> map = ShortestPathUtil.shortestPathTree(undirectedGraph, vertexA, costs);
        
        assertEquals(3, map.size());
        assertEquals("AB", map.get(vertexB).getElement().name);
        assertEquals("AC", map.get(vertexC).getElement().name);
        assertEquals("AD", map.get(vertexD).getElement().name);
        
        costs = ShortestPathUtil.dijkstra(undirectedGraph, vertexD);
        map = ShortestPathUtil.shortestPathTree(undirectedGraph, vertexD, costs);
        assertEquals(3, map.size());
        assertEquals(edge3, map.get(vertexA));
        assertEquals(edge1, map.get(vertexB));
        assertEquals(edge2, map.get(vertexC));
        
        buildDirectedSample();
        costs = ShortestPathUtil.dijkstra(directedGraph, vertexE);
        map = ShortestPathUtil.shortestPathTree(directedGraph, vertexE, costs);
        assertEquals(4, map.size());
        assertEquals(edge3, map.get(vertexD));
        assertEquals(edge6, map.get(vertexA));
        assertEquals(edge7, map.get(vertexC));
        assertEquals(edge1, map.get(vertexB));
    }
    
}