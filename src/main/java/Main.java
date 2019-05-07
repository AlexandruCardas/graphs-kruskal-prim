import Algorithms.DataStructures.AdjacencyList;
import Algorithms.DataStructures.UnionFindSets;
import Algorithms.Exceptions.MyExceptions;
import Algorithms.Graphs.HeapKruskal;
import Algorithms.MST.*;
import Algorithms.Traversal.BFS;
import Algorithms.Traversal.DFS;

import java.io.IOException;
import java.util.Scanner;

public class Main
{
	public static void main(String[] args) throws IOException
	{
		int starVert;

		String fileName1 = "/home/calexc95/Documents/GitHub/graphs/src/main/resources/myGraph.txt";
		AdjacencyList myList = new AdjacencyList(fileName1);


		BFS bfs = new BFS(myList.getAdjacencyList(), myList.getVertexAmount());
		DFS dfs = new DFS(myList.getAdjacencyList(), myList.getVertexAmount());

		System.out.println("\n///// Adjacency List /////\n");
		// print the adjacency list
		myList.printAdj();

		System.out.println();
		System.out.println("||| ================================================================ |||");
		System.out.println();

		System.out.println("///// Breadth-First Search /////\n");

		try
		{
			bfs.breadthFirstSearch(3);
		} catch (MyExceptions myExceptions)
		{
			myExceptions.printStackTrace();
		}

		System.out.println();
		System.out.println("||| ================================================================ |||");
		System.out.println();
		System.out.println("///// Depth-First Search /////\n");

		dfs.dfsRecursive(3);

		Scanner textScanner = new Scanner(System.in);

		String fileName2 = "/home/calexc95/Documents/GitHub/graphs/src/main/resources/myGraph.txt";
		System.out.print("\nEnter starting vertex: ");
		starVert = textScanner.nextInt();

		Prim graph = new Prim(fileName2);

		graph.display();
		graph.MSTPrim(starVert);
		graph.showMST();

		KruskalGraph graph2 = new KruskalGraph(fileName1);

		graph2.MSTKruskal();
		graph2.showMST();
	}
}
