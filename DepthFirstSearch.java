public class DepthFirstSearch {

	public static void search(AdjacencyList graph) {
		boolean[] visited = new boolean[graph.size()];

		//for each edge connecting the start vertex (a)
		for (Vertex v : graph.getVertices()){
			//if the vertex on the other side of the edge hasn't been visited (b)
			if (!visited[v.getVertexVal()]){
				//mark this vertex (a) as visited
				visited[v.getVertexVal()] = true;
				//visit (b)
				//e.getDest is the Vertex the edge is pointing at. get that vertex from the graph
				visit(graph, v, visited);
			}
		}

	}

	public static void visit(AdjacencyList graph, Vertex b, boolean[] visited){
		//for each Edge connecting to (b)
		for (Edge e : b.getEdges()){
			//if the edge's other vertex hasn't been visited (c)
			if (!visited[e.getDest()]){
				//mark (b) as visited
				visited[e.getDest()] = true;
				System.out.println(b.getVertexVal() + " visiting " + e.getDest());
				//visit (c)
				visit(graph, graph.getVertex(e.getDest()), visited);
			}
		}

	}

}