package york.eecs.source;

import java.util.ArrayList;
import java.util.Collections;
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
		List <T> path = new ArrayList<T>();
		Map <T, T> from = new HashMap<T, T>();//the vertex we visited from
		Map <T, Integer> color = new HashMap<T, Integer>();//the color of the edge, where 0 is gray and 1 is black
		Queue <T> open = new LinkedList<T>();
		boolean found = false;
		
		from.put(initial, null);
		color.put(initial, 0);
		open.add(initial);
		
		while(!open.isEmpty()) {
			T n = open.remove();
			
			if(n == destination) {
				found = true;
				break;
			}
			
			for(T to : g.getAdjacent(n)) {
				if(!color.containsKey(to)) {//if we haven't visited the vertex yet
					color.put(to, 0);
					from.put(to, n);//adding where we visited a vertex from
					open.add(to);
				}
			}
			
			color.put(n, 1);
		}
		
		if(found) {//if we found the destination design the path
			T p = destination;
			while(p != null){
				path.add(p);
				p = from.get(p);
			}
			Collections.reverse(path);
		}
		
		System.out.println(path.toString());
		return path; // this line must change
	}

}