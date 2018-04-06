import java.util.Queue;
import java.util.LinkedList;
public class BreadthFirstSearch {

	public static void search(AdjacencyList graph, Vertex v, int minRating) {
		Queue<Vertex> vertexQ = new LinkedList<>();
		boolean[] visited = new boolean[graph.size()];
		
		vertexQ.offer(v);
		visited[v.getVertexVal()] = true;
		while(vertexQ.peek() != null) {
			v = vertexQ.remove();
			for (Edge e : v.getEdges()) {
				Vertex newV = graph.getVertex(e.getDest());
				if (!visited[newV.getVertexVal()] && e.getRating() >= minRating){
					visited[newV.getVertexVal()] = true;
					System.out.println("Visited: " + newV.getVertexVal());
					vertexQ.offer(newV);
				}
			}
		}
	}
}