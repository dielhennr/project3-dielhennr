import java.util.LinkedList;
/**
 * Adjacency List representation of a directed graph.
 * @author dielhennr
 */
public class AdjacencyList {

	private Vertex[] vertexArr;

	/**
	 * Constructor for an adjacency list that is the size of the 
	 * number of vertices we have.
	 * @param vertices
	 */
	@SuppressWarnings({"unchecked", "rawtypes"})
	public AdjacencyList(int vertices) {
		//add one to size so that the vertex at position i will have value i
		vertexArr = new Vertex[vertices + 1];
		//instantiate vertices
		for (int i = 0; i <= vertices; i++) {
			vertexArr[i] = new Vertex(i);
		}
	} 

	/**
	 * Returns Vertex object at a given position in the adjacency list
	 * @param vertex
	 * @return vertexArr[vertex] (Vertex object at given position)
	 */
	public Vertex getVertex(int vertex) {
		return vertexArr[vertex];
	}

	/**
	 * Adds an edge to a given vertex.
	 * @param e
	 */
	public void addEdge(int vertex, Edge e) {
		vertexArr[vertex].addEdge(e);
	}

	/**
	 * Returns number of vertices in adjacency list
	 * @return vertexArr.length (length of vertexArr will be number of vetices unless we have empty spaces in the array)
	 */
	public int size() {
		return vertexArr.length;
	}

	/**
	 * Returns number of edges that a given vertex has
	 * @param vertex
	 * @return number of edges
	 */
	public int numEdges(int vertex) {
		return vertexArr[vertex].getNumEdges();
	}
}