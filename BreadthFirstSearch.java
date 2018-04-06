import java.util.Queue;
import java.util.LinkedList;

/**
 * Class to search a directed graph of bitcoin users. 
 * @author dielhennr
 */
public class BreadthFirstSearch {

	/**
	 * Preforms BFS on a graph starting at vertex v and visiting all nodes that
	 * have rating greater than or equal to minRating
	 * @param graph, v, minRating
	 */
	public static void search(AdjacencyList graph, Vertex v, int minRating) {

		//instantiate vertex queue and boolean array
		Queue<Vertex> vertexQ = new LinkedList<>();
		boolean[] visited = new boolean[graph.size()];
		
		//add start vertex to queue and visit it.
		vertexQ.offer(v);
		visited[v.getVertexVal()] = true;

		//while the queue is not empty
		while(vertexQ.peek() != null) {
			v = vertexQ.remove();

			//iterate through edges of the vertex at the front of the queue
			for (Edge e : v.getEdges()) {
				Vertex newV = graph.getVertex(e.getDest());
				//visit a neighboring vertex if it has not been visited and satisfies the minimum rating
				if (!visited[newV.getVertexVal()] && e.getRating() >= minRating){
					visited[newV.getVertexVal()] = true;
					System.out.println("Visited: " + newV.getVertexVal());
					//add visited vertex to queue
					vertexQ.offer(newV);
				}
			}
		}
	}
}