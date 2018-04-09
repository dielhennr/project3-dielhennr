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

		double pathCost;
		int numEdges;
		
		//for every outgoing edge from startVertex
		for (Edge e : startVertex.getEdges()) {
			//start path/edge count to 0 for each outgoing edge from the start vertex
			pathCost = 0;
			numEdges = 0;

			//if the vertex right next to the start vertex is the destination then we just add than edges cost to the ll
			if (e.getDest() == destVertex.getVertexVal()) {
				averagePathRatings.add((double)e.getRating());

			}else/*otherwise we visit the next vertex*/{

				//add an edge and the edge cost to the counters
				pathCost += e.getRating();
				numEdges++;			
				//pass the edge count and path cost variables when visiting next vertex
				visit(graph.getVertex(e.getDest()), destVertex, pathCost, numEdges);
			}
		}

		//once we are done searched we have all average path ratings from startVertex to destVertex in the linked list
		int countPaths = 0;
		double sumPathAvgs = 0;
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
	public void visit(Vertex startVertex, Vertex destVertex, double pathCost, int numEdges) {
		//Create new temp counters of the current path cost and number of edges since every edge needs the original counts.
		double tempCost;
		int tempEdges;


		//for every outgoing edge
		for (Edge e : startVertex.getEdges()) {
			//reset temp variables
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
				visit(graph.getVertex(e.getDest()), destVertex, tempCost, tempEdges);
			}
		}
	}

}