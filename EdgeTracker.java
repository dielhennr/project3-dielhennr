public class EdgeTracker {

	//sum of edge costs
	int sumCosts;
	//number of edges traversed
	int numEdges;

	public EdgeTracker(){
		sumCosts = 0;
		numEdges = 0;
	}

	public EdgeTracker(EdgeTracker tracker) {
		this.sumCosts = tracker.getSumCosts();
		this.numEdges = tracker.getNumEdges();
	}

	public void addCost(int cost) {
		this.sumCosts += cost;
	}

	public void bumpEdges() {
		this.numEdges += 1;
	}

	public int getNumEdges() {
		return this.numEdges;
	}

	public int getSumCosts() {
		return this.sumCosts;
	}



}