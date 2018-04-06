public class Edge {
	
	private int dest;
	private int rating;

	public Edge(int dest, int rating) {
		this.dest = dest;
		this.rating = rating;
	}

	public int getDest() {
		return this.dest;
	}

	public int getRating() {
		return this.rating;
	}
}