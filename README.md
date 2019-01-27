# Code Written in Spring 2018

# Bitcoin user network

## Compile

javac *.java

## Usage

java Driver filename.csv startNode minimumRating 0

or

java Driver filename.csv startNode destNode 1

The first runs Breadth First Search and the second runs Depth First Search. See below for detailed descriptions.

### filename.csv
Csv file of user-edge pairings formatted as follows: 

sourceID, destinationID, edgeRating

Preview of file:

```
6,2,4
6,5,2
1,15,1
4,3,7
13,16,8
13,10,8
7,5,1
2,21,5
```

sourceID will point to destitationID in a directed graph of bitcoin users. edgeRating will be the reputation of this path.

### startNode

Node that user wants to start search from

### minimumRating

If Breadth First Search is selected as the algorithm (0 as last argument), then the minimumRating specifies which nodes are allowed to be visited during BFS.

### destNode

If Depth First Search is selected as the algorithm (1 as last argument), then we look for all possible paths from startNode to destNode and find the average rating of all the paths.

## Breakdown of Searches

### Breadth First Search
Given a starting user id and a minimum rating, we find all reachable nodes from the start node such that we never cross an edge whose cost is less than the minimum rating.

### Depth First Search
Given a starting user id and a destination id, we find the average rating across all possible paths with up to 6 edges to the destination id. Paths with more than 6 edges are not counted to reduce the runtime of the algorithm.

**NOTE TO USER**

This version only counts paths with <= 6 edges (for running time's sake). The code that does this is on line 73-75 of the DepthFirstSearch.java file. If commented out, the algorithm will run normally.
