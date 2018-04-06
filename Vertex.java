import java.util.LinkedList;
/**
 * Represents a Vertex in a Directed Graph.
 * @author dielhennr
 */
public class Vertex {
	int vertexVal;
	LinkedList<Edge> edges;
	/**
	 * Vertex Constructor. Sets the vertex value and instantiates a linked list of edges.
	 * @param vertexVal
	 */
	public Vertex(int vertexVal) {
		this.vertexVal = vertexVal;
		edges = new LinkedList<Edge>();
	}

	/**
	 * Returns this vertex's edges as a LinkedList of Edge objects
	 * @return edges
	 */
	public LinkedList<Edge> getEdges() {
		return this.edges;
	}

	/**
	 * Returns the number of edges that a vertex has
	 * @return edges.size()
	 */
	public int getNumEdges() {
		return this.edges.size();
	}

	/**
	 * Adds an edge to a vertex
	 * @param e
	 */
	public void addEdge(Edge e) {
		this.edges.add(e);
	}

	/**
	 * Returns this Vertex's value
	 * @return vertexVal
	 */
	public int getVertexVal() {
		return this.vertexVal;
	}
}