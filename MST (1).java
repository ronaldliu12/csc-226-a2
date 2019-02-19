/* MST.java
   CSC 226 - Fall 2018
   Problem Set 2 - Template for Minimum Spanning Tree algorithm
   
   The assignment is to implement the mst() method below, using Kruskal's algorithm
   equipped with the Weighted Quick-Union version of Union-Find. The mst() method computes
   a minimum spanning tree of the provided graph and returns the total weight
   of the tree. To receive full marks, the implementation must run in time O(m log m)
   on a graph with n vertices and m edges.
   
   This template includes some testing code to help verify the implementation.
   Input graphs can be provided with standard input or read from a file.
   
   To provide test inputs with standard input, run the program with
       java MST
   To terminate the input, use Ctrl-D (which signals EOF).
   
   To read test inputs from a file (e.g. graphs.txt), run the program with
       java MST graphs.txt
   
   The input format for both methods is the same. Input consists
   of a series of graphs in the following format:
   
       <number of vertices>
       <adjacency matrix row 1>
       ...
       <adjacency matrix row n>
   	
   For example, a path on 3 vertices where one edge has weight 1 and the other
   edge has weight 2 would be represented by the following
   
   3
   0 1 0
   1 0 2
   0 2 0
   	
   An input file can contain an unlimited number of graphs; each will be processed separately.
   
   NOTE: For the purpose of marking, we consider the runtime (time complexity)
         of your implementation to be based only on the work done starting from
	 the mst() method. That is, do not not be concerned with the fact that
	 the current main method reads in a file that encodes graphs via an
	 adjacency matrix (which takes time O(n^2) for a graph of n vertices).
   
   (originally from B. Bird - 03/11/2012)
   (revised by N. Mehta - 10/9/2018)
   
   edited by Ronald Liu V00838627
*/

import java.util.Scanner;
import java.io.File;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Comparator;

class weightComparator implements Comparator<Edge>
{

	public int compare(Edge e1, Edge e2){
		
		if ( e1.weight() < e2.weight() )
			return -1;
		else if ( e1.weight() > e2.weight() )
			return +1;
		else 
			return 0;
	}
}

class Edge{
	
	private final int v,w;
	private final int weight;
	
	public Edge(int v, int w, int weight){
	
		this.v = v;
		this.w = w;
		this.weight = weight;
	
	}
	

	
	public int v(){
		return v;
	}
	
	public int w(){
		return w;
	}
	
	public int weight(){
		return weight;
	}
	
	
	
	public int either(){
		return v;
	}	
	public int other(int vertex){
		
		if ( vertex == v ){
			return w;
		}else{
			return v;
		}
	}
	
	/*
	public int compareTo(Edge that){
		
		if ( this.weight < that.weight )
			return -1;
		else if ( this.weight > that.weight )
			return +1;
		else 
			return 0;
	}
	*/
}


class QuickUnionUF{
	
	private static int[] id;
	private static int[] sz;
	
	public QuickUnionUF ( int N ){
	
		id = new int[N];
		sz = new int[N];
		
		for ( int i = 0 ; i < N ; i++ ) {
			id[i] = i;
			sz[i] = 1;
		}
	}
	
	public static int find ( int i ){
		
		while( i != id[i] ){
			i = id[i];
		}
		return i;
	}
	
	public static void union ( int p, int q){
		
		int i = find(p);
		int j = find(q);
		
		//id[i] = j;
		
		if ( i == j ) return;
		
		if ( sz[i] < sz[i] ){
			id[i] = j;
			sz[j] += sz[i];
		}else{
			id[j] = i;
			sz[i] += sz[j];
		}
	}
	
	
	public static boolean connected ( int p, int q ){
		
		return find(p) == find(q);
	}
	
}




public class MST {


    /* mst(adj)
       Given an adjacency matrix adj for an undirected, weighted graph, return the total weight
       of all edges in a minimum spanning tree.

       The number of vertices is adj.length = n
       For vertex i:
         adj[i].length is the number of edges connected to vertex i
         adj[i][j] is an int[2] that stores the j'th edge for vertex i, where:
           the edge has endpoints i and adj[i][j][0]
           the edge weight is adj[i][j][1] and assumed to be a positive integer
    */
    static int mst(int[][][] adj) {
		
		int n = adj.length;
	

		/* Find a minimum spanning tree using Kruskal's algorithm */
		/* (You may add extra functions if necessary) */
			
		/* ... Your code here ... */
		
		
		LinkedList<Edge> minST = new LinkedList<Edge>();  

		PriorityQueue<Edge> queue = new PriorityQueue<Edge>( 2, new weightComparator() );	

		
		for(int i = 0; i < n; i++) {
	     	for(int j = 0; j < adj[i].length; j++) {
				
				Edge e1 = new Edge(i , adj[i][j][0] , adj[i][j][1]);
	     	    queue.add( e1 );
	     	}
	    }
		
		
		QuickUnionUF uf = new QuickUnionUF( n );
		
		
		while ( !queue.isEmpty() && minST.size() < n-1 ){
		
			Edge e = queue.poll();

			//System.out.println(e.v() + " + " + e.w() + " + " + e.weight());
		
			
			int v = e.either();
			int w = e.other(v);
			
			if ( !QuickUnionUF.connected(v,w)){
				
				QuickUnionUF.union(v,w);
				minST.add(e);
			}
		
		
		}

			
		/* Add the weight of each edge in the minimum spanning tree
		   to totalWeight, which will store the total weight of the tree.
		*/
		
		
		
		
		int totalWeight = 0;
		/* ... Your code here ... */
		
		// for loop - minST
		
		while ( !minST.isEmpty() ){
		
			Edge e = minST.poll();

			//System.out.println(e.v() + " + " + e.w() + " + " + e.weight());	
			
			totalWeight += e.weight();
		}

		
		return totalWeight;
			
    }


    public static void main(String[] args) {
	/* Code to test your implementation */
	/* You may modify this, but nothing in this function will be marked */

	int graphNum = 0;
	Scanner s;

	if (args.length > 0) {
	    //If a file argument was provided on the command line, read from the file
	    try {
		s = new Scanner(new File(args[0]));
	    }
	    catch(java.io.FileNotFoundException e) {
		System.out.printf("Unable to open %s\n",args[0]);
		return;
	    }
	    System.out.printf("Reading input values from %s.\n",args[0]);
	}
	else {
	    //Otherwise, read from standard input
	    s = new Scanner(System.in);
	    System.out.printf("Reading input values from stdin.\n");
	}
		
	//Read graphs until EOF is encountered (or an error occurs)
	while(true) {
	    graphNum++;
	    if(!s.hasNextInt()) {
		break;
	    }
	    System.out.printf("Reading graph %d\n",graphNum);
	    int n = s.nextInt();

	    int[][][] adj = new int[n][][];
	    
	    
	    
	    
	    int valuesRead = 0;
	    for (int i = 0; i < n && s.hasNextInt(); i++) {
		LinkedList<int[]> edgeList = new LinkedList<int[]>(); 
		for (int j = 0; j < n && s.hasNextInt(); j++) {
		    int weight = s.nextInt();
		    if(weight > 0) {
			edgeList.add(new int[]{j, weight});
		    }
		    valuesRead++;
		}
		adj[i] = new int[edgeList.size()][2];
		Iterator it = edgeList.iterator();
		for(int k = 0; k < edgeList.size(); k++) {
		    adj[i][k] = (int[]) it.next();
		}
	    }
	    if (valuesRead < n * n) {
		System.out.printf("Adjacency matrix for graph %d contains too few values.\n",graphNum);
		break;
	    }

	     // output the adjacency list representation of the graph
	    /*for(int i = 0; i < n; i++) {
	     	System.out.print(i + ": ");
	     	for(int j = 0; j < adj[i].length; j++) {
	     	    System.out.print("(" + adj[i][j][0] + ", " + adj[i][j][1] + ") ");
	     	}
	     	System.out.print("\n");
	     }
		 */

	    int totalWeight = mst(adj);
	    System.out.printf("Graph %d: Total weight of MST is %d\n",graphNum,totalWeight);

				
	}
    }

    
}
