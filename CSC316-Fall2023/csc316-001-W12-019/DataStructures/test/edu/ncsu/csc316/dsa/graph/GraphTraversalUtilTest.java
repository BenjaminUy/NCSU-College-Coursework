package edu.ncsu.csc316.dsa.graph;

import org.junit.Test;
import static org.junit.Assert.*;

import org.junit.Before;

import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;
import edu.ncsu.csc316.dsa.map.Map;

/**
 * Tests the behavior of the GraphTraversalUtil class
 * @author Benjamin Uy
 */
public class GraphTraversalUtilTest {

    private Graph<String, Integer> undirectedGraph;
    private Graph<String, Integer> directedGraph;
    
    private Vertex<String> vertexA;
    private Vertex<String> vertexB;
    private Vertex<String> vertexC;
    private Vertex<String> vertexD;
    private Vertex<String> vertexE;
    
    private Edge<Integer> edge1;
    private Edge<Integer> edge2;
    private Edge<Integer> edge3;
    private Edge<Integer> edge4;
    private Edge<Integer> edge5;
    private Edge<Integer> edge6;
    private Edge<Integer> edge7;
    
    /**
     * Create a new instance of an edge list graph before each test case executes
     */ 
    @Before
    public void setUp() {
        undirectedGraph = new EdgeListGraph<String, Integer>();
        directedGraph = new EdgeListGraph<String, Integer>(true);
    }
    
    /**
     * Constructs the undirected graph and initializes the vertexes and edges for testing
     */
    private void buildUndirectedSample() {
    	vertexA = undirectedGraph.insertVertex("A");
        vertexB = undirectedGraph.insertVertex("B");
        vertexC = undirectedGraph.insertVertex("C");
        vertexD = undirectedGraph.insertVertex("D");
        
        edge1 = undirectedGraph.insertEdge(vertexA, vertexB, 5);
        edge2 = undirectedGraph.insertEdge(vertexA, vertexC, 10);
        edge3 = undirectedGraph.insertEdge(vertexA, vertexD, 15);
        edge4 = undirectedGraph.insertEdge(vertexB, vertexC, 25);
        edge5 = undirectedGraph.insertEdge(vertexB, vertexD, 30);
        edge6 = undirectedGraph.insertEdge(vertexC, vertexD, 40);
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
        
        edge1 = directedGraph.insertEdge(vertexA, vertexB, 5);
        edge2 = directedGraph.insertEdge(vertexB, vertexD, 10);
        edge3 = directedGraph.insertEdge(vertexE, vertexD, 15);
        edge4 = directedGraph.insertEdge(vertexA, vertexC, 20);
        edge5 = directedGraph.insertEdge(vertexA, vertexD, 25);
        edge6 = directedGraph.insertEdge(vertexD, vertexA, 30);
        edge7 = directedGraph.insertEdge(vertexD, vertexC, 35);
    }
    
    /**
     * Tests the behavior of depthFirstSearch for undirected and directed graph
     */
	@Test
	public void depthFirstSearchTest() {
		buildUndirectedSample();
		
		Map<Vertex<String>, Edge<Integer>> returnedMap = GraphTraversalUtil.depthFirstSearch(undirectedGraph, vertexA);
		assertEquals(3, returnedMap.size());
		assertEquals(edge1, returnedMap.get(vertexB));
		assertEquals(edge4, returnedMap.get(vertexC));
		assertEquals(edge6, returnedMap.get(vertexD));
		
		returnedMap = GraphTraversalUtil.depthFirstSearch(undirectedGraph, vertexB);
		assertEquals(3, returnedMap.size());
		assertEquals(edge1, returnedMap.get(vertexA));
		assertEquals(edge2, returnedMap.get(vertexC));
		assertEquals(edge6, returnedMap.get(vertexD));
		
		returnedMap = GraphTraversalUtil.depthFirstSearch(undirectedGraph, vertexC);
		assertEquals(3, returnedMap.size());
		assertEquals(edge2, returnedMap.get(vertexA));
		assertEquals(edge1, returnedMap.get(vertexB));
		assertEquals(edge5, returnedMap.get(vertexD));
		
		returnedMap = GraphTraversalUtil.depthFirstSearch(undirectedGraph, vertexD);
		assertEquals(3, returnedMap.size());
		assertEquals(edge3, returnedMap.get(vertexA));
		assertEquals(edge1, returnedMap.get(vertexB));
		assertEquals(edge4, returnedMap.get(vertexC));
		
		buildDirectedSample();
		returnedMap = GraphTraversalUtil.depthFirstSearch(directedGraph, vertexA);
		assertEquals(3, returnedMap.size());
		assertEquals(edge1, returnedMap.get(vertexB));
		assertEquals(edge2, returnedMap.get(vertexD));
		assertEquals(edge7, returnedMap.get(vertexC));
		
		buildDirectedSample();
		returnedMap = GraphTraversalUtil.depthFirstSearch(directedGraph, vertexE);
		assertEquals(4, returnedMap.size());
		assertEquals(edge3, returnedMap.get(vertexD));
		assertEquals(edge6, returnedMap.get(vertexA));
		assertEquals(edge1, returnedMap.get(vertexB));
		assertEquals(edge4, returnedMap.get(vertexC));
	}
	
	/**
	 * Tests the behavior of breadthFirstSearchTest
	 */
	@Test
	public void breadthFirstSearchTest() {
		buildUndirectedSample();
		
		Map<Vertex<String>, Edge<Integer>> returnedMap = GraphTraversalUtil.breadthFirstSearch(undirectedGraph, vertexA);
		assertEquals(3, returnedMap.size());
		assertEquals(edge1, returnedMap.get(vertexB));
		assertEquals(edge2, returnedMap.get(vertexC));
		assertEquals(edge3, returnedMap.get(vertexD));
		
		returnedMap = GraphTraversalUtil.breadthFirstSearch(undirectedGraph, vertexB);
		assertEquals(3, returnedMap.size());
		assertEquals(edge1, returnedMap.get(vertexA));
		assertEquals(edge4, returnedMap.get(vertexC));
		assertEquals(edge5, returnedMap.get(vertexD));
		
		returnedMap = GraphTraversalUtil.breadthFirstSearch(undirectedGraph, vertexC);
		assertEquals(3, returnedMap.size());
		assertEquals(edge2, returnedMap.get(vertexA));
		assertEquals(edge4, returnedMap.get(vertexB));
		assertEquals(edge6, returnedMap.get(vertexD));
		
		returnedMap = GraphTraversalUtil.breadthFirstSearch(undirectedGraph, vertexD);
		assertEquals(3, returnedMap.size());
		assertEquals(edge3, returnedMap.get(vertexA));
		assertEquals(edge5, returnedMap.get(vertexB));
		assertEquals(edge6, returnedMap.get(vertexC));
		
		buildDirectedSample();
		returnedMap = GraphTraversalUtil.breadthFirstSearch(directedGraph, vertexA);
		assertEquals(3, returnedMap.size());
		assertEquals(edge1, returnedMap.get(vertexB));
		assertEquals(edge5, returnedMap.get(vertexD));
		assertEquals(edge4, returnedMap.get(vertexC));
		
		buildDirectedSample();
		returnedMap = GraphTraversalUtil.breadthFirstSearch(directedGraph, vertexE);
		assertEquals(4, returnedMap.size());
		assertEquals(edge3, returnedMap.get(vertexD));
		assertEquals(edge6, returnedMap.get(vertexA));
		assertEquals(edge7, returnedMap.get(vertexC));
		assertEquals(edge1, returnedMap.get(vertexB));
	}
}
