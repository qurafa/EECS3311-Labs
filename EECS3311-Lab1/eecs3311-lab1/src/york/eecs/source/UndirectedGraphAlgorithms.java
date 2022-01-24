package york.eecs.source;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class UndirectedGraphAlgorithms<T extends Comparable<T>> 
								implements GraphAlgorithms<T> {

	/**
	 * Please implement BFS algorithm as described on the handout
	 * @param g: a graph
	 * @param initial: the starting vertex of the path
	 * @param destination: the destination vertex of the path
	 * @return the path from initial to destination in the form of
	 *         an ArrayList of vertices, with initial as the first
	 *         element, and destination as the last element of the 
	 *         ArrayList
	 */
	public List<T> findBFSpath(Graph<T> g, T initial, T destination) {
		// TODO : implement BFS path search
		List <T> output = new ArrayList<T>();
		Queue <T> open = new LinkedList<T>();
		
		
		
		return output; // this line must change
	}

}
