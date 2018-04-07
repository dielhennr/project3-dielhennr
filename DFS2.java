public class DFS2{

	public static double search(AdjacencyList graph, Vertex startVertex, Vertex destVertex) {

		//Object the maintains a boolean array of visited nodes, the sum of the edge costs, and the number of edges traversed
		int numPaths;
		int totalAverage;
		boolean[] visited = new boolean[graph.size()];
		EdgeTracker tracker;
		visited[startVertex.getVertexVal()] = true;
		for(Edge e : startVertex.getVertices()) {
			if (!visited[e.getDest()]);
			tracker = new EdgeTracker();
			tracker.bumpEdge();
			tracker.addCost(e.getDest());


		}

	}

	public static double visit(AdjacencyList graph, Vertex current, Vertex dest, boolean[] visited){
		//for each Edge connecting to (b)
		for (Edge e : current.getEdges()){
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