/**
 * Represents an edge of a vertex in a directed graph.
 * @author dielhennr
 */
public class Edge {

	private int dest;
	private int rating;

	/**
	 * Edge Constructor
	 * @param dest, rating
	 */
	public Edge(int dest, int rating) {
		this.dest = dest;
		this.rating = rating;
	}

	/**
	 * Returns the value of the vertex connected to the edge.
	 * @return dest
	 */
	public int getDest() {
		return this.dest;
	}

	/**
	 * Returns the rating of the edge.
	 * @return rating
	 */
	public int getRating() {
		return this.rating;
	}
}