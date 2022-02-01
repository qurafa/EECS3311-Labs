package york.eecs.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import york.eecs.source.UndirectedGraph;
import york.eecs.source.UndirectedGraphAlgorithms;
import york.eecs.source.VertexExistsException;

public class StudentTest {

	 /**
	  * TODO: Please write at least 5 test cases for testing @UndirectedGraph.
	  * TODO: Please write at least 5 test cases for testing @UndirectedGraphAlgorithms.
	  */
	
	UndirectedGraphAlgorithms<String> uga;
	@Before
	public void setUp() throws Exception {
		uga = new UndirectedGraphAlgorithms<>();
	}
	
	 ///////////////////////////
	////UndirectGraph Tests////
   ///////////////////////////
	
	@Test
	public void graphEmptyTest()
	{
		//testing whether an empty graph returns the right boolean value
		//here we expect false since the graph is empty
		UndirectedGraph<String> ug = new UndirectedGraph<>();;
		boolean result = ug.isEmpty();
		boolean expected = false;
		assertEquals(result, expected);
	}
	
	@Test
	public void graphEmptySizeTest()
	{
		//testing whether an empty graph returns 0 as it's size
		//here we expect 0 since the graph is empty
		UndirectedGraph<String> ug = new UndirectedGraph<>();;
		int result = ug.getSize();
		int expected = 0;
		assertEquals(result, expected);
	}
	
	@Test
	public void graphEmptyStringTest()
	{
		//testing the string an empty graph returns
		//here expect Graph:\n since the graph is empty
		UndirectedGraph<String> ug = new UndirectedGraph<>();;
		String result = ug.toString();
		String expected = "Graph:\n";
		assertEquals(result, expected);
	}
	
	@Test
	public void getEmptyVerticesTest()
	{
		//testing the number of vertices that an empty graph returns
		//here we return an empty List
		UndirectedGraph<String> ug = new UndirectedGraph<>();;
		List<String> result = ug.getVertices();
		List<String> expected = new ArrayList<>();
		assertEquals(result, expected);
	}
	
	@Test
	public void graphVertexExistsTest()
	{
		//testing what is returned if an existing vertex is inserted
		//here we expect a VertexExistsException Exception
		UndirectedGraph<String> ug = new UndirectedGraph<>();;
		boolean vertexExceptionThrown =  false;
		try 
		{
			ug.addVertex("A");
			ug.addVertex("B");
			ug.addVertex("C");
			ug.addEdge("A", "B");
			ug.addEdge("A", "C");
			ug.addVertex("A");
		}
		catch (VertexExistsException e) 
		{
			vertexExceptionThrown =  true;
		}
		assertTrue(vertexExceptionThrown);
	}
	 
	@Test
	public void graphSameVertexEdgeTest()
	{
		//testing what happens when we add as edge between the same vertex e.g C-C
		//here we expect 1
		UndirectedGraph<String> ug = new UndirectedGraph<>();;
		
		try 
		{
			ug.addVertex("A");
			ug.addVertex("B");
			ug.addVertex("C");
			ug.addEdge("C", "C");
		}
		catch (VertexExistsException e) 
		{
			e.printStackTrace();
		}
		int expected = 1;
		int result = ug.getAdjacent("C").size();
		assertEquals(expected, result);
	}
	
	 ///////////////////////////////////////
	////UndirectedGraphAlgorithms Tests////
   ///////////////////////////////////////
	
	@Test(expected = NullPointerException.class)
	public void algorithmEmptyTest()
	{
		//testing what is returned if the graph is empty
		//here we expect a NullPointerException
		UndirectedGraph<String> ug = new UndirectedGraph<>();;
		uga.findBFSpath(ug, "B", "C");
	}
	
	@Test
	public void algorithmNoEdgeTest()
	{
		//testing for if there are no edges between the vertices in the graph
		//here we expect an empty List
		UndirectedGraph<String> ug = new UndirectedGraph<>();;
		try 
		{
			ug.addVertex("A");
			ug.addVertex("B");
			ug.addVertex("C");
		}
		catch(VertexExistsException e)
		{
			e.printStackTrace();
		}
		List<String> result = (ArrayList<String>) uga.findBFSpath(ug, "B", "C");
		List<String> expected = new ArrayList<String>();
		assertEquals(result, expected);
	}
	
	@Test
	public void algorithmDisconnectedEdgeTest()
	{
		//testing for if the destination edge is disconnected from the starting edge
		//here we expect an empty List
		UndirectedGraph<String> ug = new UndirectedGraph<>();;
		try 
		{
			ug.addVertex("A");
			ug.addVertex("B");
			ug.addVertex("C");
			ug.addEdge("A", "B");
		}
		catch(VertexExistsException e)
		{
			e.printStackTrace();
		}
		List<String> result = (ArrayList<String>) uga.findBFSpath(ug, "B", "C");
		List<String> expected = new ArrayList<String>();
		assertEquals(result, expected);
	}
	
	@Test(expected = NullPointerException.class)
	public void algorithmStartDoesNotExistTest()
	{
		//testing what is returned if the starting node does not exist
		//here we expect a NullPointerException since the vertex does not exist
		UndirectedGraph<String> ug = new UndirectedGraph<>();;
		try 
		{
			ug.addVertex("A");
			ug.addVertex("B");
			ug.addVertex("C");
			ug.addEdge("A", "B");
		}
		catch(VertexExistsException e)
		{
			e.printStackTrace();
		}
		uga.findBFSpath(ug, "E", "C");
	}
	
	@Test
	public void algortihmEndDoesNotExistTest()
	{
		//testing what is returned if the destination node does not exist
		//here we expect an empty List
		UndirectedGraph<String> ug = new UndirectedGraph<>();;
		try 
		{
			ug.addVertex("A");
			ug.addVertex("B");
			ug.addVertex("C");
			ug.addEdge("A", "B");
			ug.addEdge("B", "C");
		}
		catch(VertexExistsException e)
		{
			e.printStackTrace();
		}
		List<String> result = (ArrayList<String>) uga.findBFSpath(ug, "B", "E");
		List<String> expected = new ArrayList<String>();
		assertEquals(result, expected);
	}
	
	@Test
	public void algorithmStartEqualEndTest()
	{
		//testing what we return if the start and end are equal
		//here we expect a List with only the start vertex
		UndirectedGraph<String> ug = new UndirectedGraph<>();;
		try 
		{
			ug.addVertex("A");
			ug.addVertex("B");
			ug.addVertex("C");
			ug.addEdge("A", "B");
			ug.addEdge("B", "C");
		}
		catch(VertexExistsException e)
		{
			e.printStackTrace();
		}
		List<String> result = (ArrayList<String>) uga.findBFSpath(ug, "B", "B");
		List<String> expected = new ArrayList<String>();
		expected.add("B");
		assertEquals(result, expected);
	}
	
	@Test
	public void algorithmBFSTest()
	{
		//testing what we return when we run the BFS on a normal graph
		//here we exoect a list of vertices from start to finish
		UndirectedGraph<String> ug = new UndirectedGraph<>();;
		try {
			ug.addVertex("A");
			ug.addVertex("B");
			ug.addVertex("C");
			ug.addVertex("D");
			ug.addVertex("E");
			ug.addVertex("F");
			ug.addEdge("A", "B");
			ug.addEdge("A", "C");
			ug.addEdge("B", "D");
			ug.addEdge("C", "D");
			ug.addEdge("C", "E");
			ug.addEdge("D", "E");
			ug.addEdge("E", "F");
		} catch (VertexExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<String> result = (ArrayList<String>) uga.findBFSpath(ug, "A", "F");
		List<String> expected = new ArrayList<>();
		expected.add("A");
		expected.add("C");
		expected.add("E");
		expected.add("F");
		assertEquals(result, expected);
	}
}
