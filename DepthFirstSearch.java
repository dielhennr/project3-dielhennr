import java.util.LinkedList;
/**
 * Searches paths from a starting user to a destination user and finds the average path ratings.
 * Uses a modified Depth First Search to do so.
 * @author dielhennr
 */
public class DepthFirstSearch{

	private LinkedList<Double> averagePathRatings;
	private AdjacencyList graph;

	public DepthFirstSearch(AdjacencyList graph) {
		this.graph = graph;
		averagePathRatings = new LinkedList<Double>();
	}

	public double search(Vertex startVertex, Vertex destVertex) {

		/**
		 * Plan: need to sum over costs of every possible path from startVertex to destVertex 
		 * and then average the costs. For instance if there are 3 paths from start vertex a to some destination
		 * vertex b, one with average rating of 5, another with 6, and another with 1, then the average rating is 4. (12/3)
		 * Start vertex contains a linked list of it's edges, from this we can get to new vertices in the graph and sum 
		 * across edge weights with a modified depth first search.
		 */

		/**
		 * Setup: need to keep track of the edge total edge costs along the path as well as the number of edges in the path.
		 * We will use a double to sum the costs and an int the count the edges. Also need to keep track of each paths individual
		 * average, we will use a linked list to store individual path averages. We add a value to the linked list once we reach the 
		 * destination vertex.
		 *
		 * For instance say we want to get from vertex 0 to vertex 3 and these are our edges formatted fromV -cost- toV 
		 * 0 -1- 1, 1 -4- 3, 1 -5- 5, 5 -2- 3, vertex one has two outgoing edges that are both valid paths to 3. This
		 * means that the path total from 0 to 1 should be multiplied by 2. this is because we count these edges twice since we 
		 * fork.   
		 */
		

		for (Edge e : startVertex.getEdges()) {
			//start path/edge count to 0 for each outgoing edge from the start vertex
			double pathCost = 0;
			int numEdges = 0;
			boolean[] visited = new boolean[graph.size()];

			//if the vertex right next to the start vertex is the destination then we just add than edges cost to the ll
			if (e.getDest() == destVertex.getVertexVal()) {
				averagePathRatings.add((double)e.getRating());

			}else/*otherwise we visit the next vertex*/{

				//add an edge and the edge cost to the counters
				pathCost += e.getRating();
				numEdges++;			
				//pass the edge count and path cost variables when visiting next vertex
				visit(graph.getVertex(e.getDest()), destVertex, pathCost, numEdges, visited);
			}
		}

		int countPaths = 0;
		double sumPathAvgs = 0;
		for (Double d : averagePathRatings) {
			sumPathAvgs += d;
			countPaths++;
		}

		return (((double)sumPathAvgs) / countPaths);
		
	}

	public void visit(Vertex startVertex, Vertex destVertex, double pathCost, int numEdges, boolean[] visited) {
		double tempCost;
		int tempEdges;
		if (visited[startVertex.getVertexVal()]) {
			return;
		}
		visited[startVertex.getVertexVal()] = true;
		for (Edge e : startVertex.getEdges()) {
			//use temp variables since we may be visiting multiple edges.
			tempCost = pathCost;
			tempEdges = numEdges;

			//if we reached the dest vertex 
			if (e.getDest() == destVertex.getVertexVal()) {
				//we found a valid path, add final edge rating and edge
				tempCost += e.getRating();
				tempEdges++;
				//add the average path cost to the ll
				averagePathRatings.add((double)(tempCost/tempEdges));
			}
			//visit until we find destVertex
			else {
				//summing costs
				tempEdges++;
				tempCost += e.getRating();
				visit(graph.getVertex(e.getDest()), destVertex, tempCost, tempEdges, visited);
			}
		}
	}

}