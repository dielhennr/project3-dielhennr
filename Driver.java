import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.LinkedList;
public class Driver {

	public static void main(String[] args) {

		if (args.length != 3) {
			System.out.println("--------------------------------------------------------------------------------------------");
			System.out.println("Usage: java Driver <file>.csv startNode minimumRating");
			System.out.println("--------------------------------------------------------------------------------------------");
			System.out.println("This program reads a file of edges of a graph and finds all nodes reachable by a start node");
			System.out.println("given a minimum edge rating. Each line of the file is an edge and each edge is formatted with");
			System.out.println("with integer entries as follows: <source> , <destination> , <rating>");
			System.out.println("--------------------------------------------------------------------------------------------");
			
			System.exit(0);
		}

		int startNode = 0;
		int minRating = 0;

		
		//Read in minimum rating and start node from command line args.
		try{
			startNode = Integer.parseInt(args[1]);
			minRating = Integer.parseInt(args[2]);
		}catch(NumberFormatException e) {
			System.out.println("------------------------------------------------------");
			System.out.println("Usage: java Driver <file>.csv startNode minimumRating");
			System.out.println("------------------------------------------------------");
			System.out.println("Please enter Integers for startNode and minimumRating");
			System.out.println("------------------------------------------------------");
			
			System.exit(0);
		}

		//read file to find largest vertex
		int max = Integer.MIN_VALUE;
		try(Scanner scan1 = new Scanner(new File(args[0])).useDelimiter("\n|,")){
			while(scan1.hasNextLine()){
				String[] line = scan1.nextLine().split(",");
				int source = Integer.parseInt(line[0]); 
				int dest = Integer.parseInt(line[1]); 
				if (source > max || dest > max) {
					max = Math.max(source, dest);
				}
			}
			if (max < startNode) {
				System.out.println("Start Node not in the graph");
				System.exit(0);
			}
			Scanner scan2 = new Scanner(new File(args[0])).useDelimiter("\n|,");
			AdjacencyList adjList = new AdjacencyList(max);
			while (scan2.hasNextLine()) {
				String[] line = scan2.nextLine().split(",");
				int source = Integer.parseInt(line[0]); 
				int dest = Integer.parseInt(line[1]); 
				int rating = Integer.parseInt(line[2]); 
				Edge newE = new Edge(dest, rating);
				adjList.addEdge(source, newE);
			}
			/*
			for (int i = 0; i < adjList.size(); i++) {
				LinkedList<Edge> vertex = adjList.getVertex(i).getEdges();
				if (vertex.peekFirst() != null){
					try{
						System.out.println("Vertex: " + i);
						for (Edge e : vertex) {
							System.out.println("dest: " + e.getDest() + "\nrating: " + e.getRating());
						}
						System.out.println("-----------");
					}catch(NoSuchElementException e){
						System.out.println("catch");
					}
				}else{
					System.out.println("Vertex: " + i + " has no edges");
				}

			}
			*/
			
			BreadthFirstSearch.search(adjList, adjList.getVertex(startNode), minRating);
		}catch(FileNotFoundException fnf) {
			System.out.println("File Not Found");
			System.out.println("Usage: java Driver <file>.csv startNode minimumRating");
			System.exit(0);
		}
		


	}
}