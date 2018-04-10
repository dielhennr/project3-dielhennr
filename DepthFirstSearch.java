import java.util.LinkedList;
/**
 * Searches paths from a starting user to a destination user and finds the average path ratings.
 * Uses a modified Depth First Search to do so.
 * @author dielhennr
 */
public class DepthFirstSearch{
	/**
	 * Plan: need to sum over costs of every possible path from startVertex to destVertex 
	 * and then average the costs. For instance if there are 3 paths from start vertex a to some destination
	 * vertex b, one with average rating of 5, another with 6, and another with 1, then the average rating is 4. (12/3)
	 * Start vertex contains a linked list of it's edges, from this we can get to new vertices in the graph and sum 
	 * across edge weights with a modified depth first search.
	 */

	/**
	 * Setup: need to keep track of each paths individual average, we will use a linked list to store individual path averages. 
	 * We add a value to the linked list once we reach the destination vertex.
	 */
	private LinkedList<Double> averagePathRatings;
	private AdjacencyList graph;

	/**
	 * Constructor that accepts the graph that will be searched on and instantiates the linked
	 * list for path averages.
	 * @param graph
	 */
	public DepthFirstSearch(AdjacencyList graph) {
		this.graph = graph;
		averagePathRatings = new LinkedList<Double>();
	}

	/**
	 * Begins a search of paths from startVertex to destVertex 
	 * @param startVertex, destVertex
	 * @return sumPathAvgs / countPaths (average of all average path ratings)
	 */
	public double search(Vertex startVertex, Vertex destVertex) {

		double pathCost = 0;
		int numEdges = 0;
		boolean[] visited = new boolean[this.graph.size()];
		String pathStr = startVertex.getVertexVal() + "";
		visited[startVertex.getVertexVal()] = true;
		System.out.println("----------------");
		visit(startVertex,destVertex, pathCost, numEdges, visited, pathStr);

		//once we are done searched we have all average path ratings from startVertex to destVertex in the linked list
		int countPaths = 0;
		double sumPathAvgs = 0;
		System.out.printf("Number of paths from vertex %d to %d is %d\n",startVertex.getVertexVal(), 
			destVertex.getVertexVal(), averagePathRatings.size());
		for (Double d : averagePathRatings) {
			sumPathAvgs += d;
			countPaths++;
		}
		//return the average of each individual path average
		return (((double)sumPathAvgs) / countPaths);
		
	}

	/**
	 * Visits a new vertex, goes the that vertex's outgoing edges and then visits new vertices if they have not been visited.
	 * Sums across path ratings and counts edges.
	 * @param startVertex, destVertex, pathCost, numEdges, visited
	 *
	 */
	public void visit(Vertex startVertex, Vertex destVertex, double pathCost, int numEdges, boolean[] visited, String pathStr) {
		//Create new temp counters of the current path cost and number of edges since every edge needs the original counts.
		double tempCost;
		int tempEdges;
		String tempStr;
		//for every outgoing edge
		for (Edge e : startVertex.getEdges()) {
			//reset temp variables
			tempCost = pathCost;
			tempEdges = numEdges;
			tempStr = pathStr;

			//if we reached the dest vertex 
			if (e.getDest() == destVertex.getVertexVal()) {
				//we found a valid path, add final edge rating and edge
				tempCost += e.getRating();
				tempEdges++;
				//add the average path cost to the ll
				tempStr += "->" + e.getDest();
				System.out.printf("Path: %s\n", tempStr);
				System.out.printf("Cost: %.2f ", tempCost);
				averagePathRatings.add((double)(tempCost/tempEdges));
				System.out.printf("Averaged with number of edges (%d): %.2f\n", tempEdges, (double)(tempCost/tempEdges));
				System.out.println("----------------");

			}
			//visit until we find destVertex
			else if (!visited[e.getDest()]){
				//summing costs
				tempEdges++;
				tempCost += e.getRating();
				//set this vertex the visited since we don't want to cycle back to it
				tempStr += "->" + e.getDest(); 
				visited[e.getDest()] = true;
				visit(graph.getVertex(e.getDest()), destVertex, tempCost, tempEdges, visited, tempStr);
				//reset the node the be not visited since the next edge in the iteration might lead back
				//to this vertex
				visited[e.getDest()] = false;
			}
		}
	}

}