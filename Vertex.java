import java.util.LinkedList;
public class Vertex {
	int vertexVal;
	LinkedList<Edge> edges;

	public Vertex(int vertexVal) {
		this.vertexVal = vertexVal;
		edges = new LinkedList<Edge>();
	}

	public LinkedList<Edge> getEdges() {
		return this.edges;
	}

	public int getNumEdges() {
		return this.edges.size();
	}

	public void addEdge(Edge e) {
		this.edges.add(e);
	}

	public int getVertexVal() {
		return this.vertexVal;
	}
}