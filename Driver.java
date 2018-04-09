import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.LinkedList;

/**
 * Program that builds a directed graph of bitcoin users given a user inputed csv file. 
 * Breadth first search is performed on the graph given a starting user and a mimimum rating. 
 * Users are only visited if they have a rating greater than or equal to the minimum rating
 * 
 * Usage: java Driver <fileName>.csv startNode minimumRating
 * @author dielhennr
 */
public class Driver {

	public static void main(String[] args) {

		if (args.length != 4) {
			System.out.println("--------------------------------------------------------------------------------------------");
			System.out.println("Usage: java Driver <fileName>.csv startNode minimumRating algo");
			System.out.println("--------------------------------------------------------------------------------------------");
			System.out.println("This program reads a file of edges of a graph and finds all nodes reachable by a start node");
			System.out.println("given a minimum edge rating if 0 is entered for algo. If 1 is entered then minimumRating becomes");
			System.out.println("is instead the destination node and all path reputations from start to destination are averaged.");
			System.out.println("Each line of the file is an edge and each edge is formatted"); 
			System.out.println("with integer entries as follows: <source> , <destination> , <rating>");
			System.out.println("--------------------------------------------------------------------------------------------");
			
			System.exit(0);
		}

		int startNode = 0;
		int minRating = 0;
		int destNode = 0;
		int algo = 0;

		
		//Read in minimum rating and start node from command line args.
		try{
			startNode = Integer.parseInt(args[1]);
			minRating = Integer.parseInt(args[2]);
			algo = Integer.parseInt(args[3]);
			destNode = minRating;
		}catch(NumberFormatException e) {
			System.out.println("------------------------------------------------------");
			System.out.println("Usage: java Driver <fileName>.csv startNode minimumRating");
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
			
			if (algo == 0){
				int bfsVisits = BreadthFirstSearch.search(adjList, adjList.getVertex(startNode), minRating);
				System.out.println("Number of reachable nodes: " + bfsVisits);
			}
			else if (algo == 1){
				DepthFirstSearch pathfinder = new DepthFirstSearch(adjList);
				double average = pathfinder.search(adjList.getVertex(startNode), adjList.getVertex(destNode));
				System.out.println(average);
			}else{
				System.out.println("Enter 0 for BFS and 1 for DFS.");
				System.out.println("If using DFS enter the destination node instead of min rating");
				System.out.println("Usage: java Driver <file>.csv startNode minimumRating algo");

			}
		}catch(FileNotFoundException fnf) {
			System.out.println("File Not Found");
			System.out.println("Usage: java Driver <file>.csv startNode minimumRating algo");
			System.exit(0);
		}
		


	}
}