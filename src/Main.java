import abstract_data_types.Adjacency_List;
import abstract_data_types.Min_Heap;
import abstract_data_types.MyExceptions;
import prim.Prim_Graph;
import searching.BFS;
import searching.DFS;

import java.io.IOException;
import java.util.Scanner;

public class Main
{
	public static void main(String[] args) throws IOException
	{
		// init files
		String file_name = "data/graph2.txt";
		Adjacency_List my_list = new Adjacency_List(file_name);

		BFS bfs = new BFS(my_list.getAdjacency_list(), my_list.getVertex_amount());
		DFS dfs = new DFS(my_list.getAdjacency_list(), my_list.getVertex_amount());

		// print the adjacency list
		my_list.print_adj();
		System.out.println();

		System.out.println();
		try
		{
			bfs.breadthFirstSearch(5);
		} catch (MyExceptions myExceptions)
		{
			myExceptions.printStackTrace();
		}

		System.out.println();
		dfs.dfsIterative(7);
		System.out.println();
		dfs.dfsRecursive(7);

//		Max_Heap_Sifting maxHeap = new Max_Heap_Sifting();
//		maxHeap.test();

		int[] A = {Integer.MIN_VALUE, 16, 14, 10, 8, 7, 9, 3, 2, 4, 1};
//		Max_Heap maxHeap = new Max_Heap(A);
//		maxHeap.buildMaxHeap();
		Min_Heap minHeap = new Min_Heap(A);
		minHeap.buildMinHeap();
//		System.out.println(maxHeap.extractMaxHeap());
//		System.out.println(maxHeap.extractMaxHeap());
//		System.out.println(maxHeap.extractMaxHeap());
//		maxHeap.heapSort();
//		System.out.println(minHeap.extractMinHeap());
//		System.out.println(minHeap.extractMinHeap());
//		System.out.println(minHeap.extractMinHeap());
//		System.out.println(minHeap.extractMinHeap());
//		maxHeap.heapSort();
//		minHeap.heapSort();
//
//		System.out.println();
//
//		maxHeap.print_heap();
//		minHeap.print_heap();

		Scanner textScanner = new Scanner(System.in);
		String fname1;
		int starVert;
		System.out.print("\nInput name of file with graph definition: ");
		fname1 = textScanner.nextLine();
		System.out.print("\nEnter starting vertext: ");
		starVert = textScanner.nextInt();

		Prim_Graph g = new Prim_Graph(fname1);

		g.display();
		g.MST_Prim(starVert);
		g.showMST();

		// kruskal
		Scanner graphName = new Scanner(System.in);
		String fname2;// = "myGraph.txt";
		System.out.print("\nInput name of file with graph definition: ");
		fname2 = graphName.nextLine();

		Graph g1 = new Graph(fname2);

		g1.MST_Kruskal();
		g1.showMST();
	}
}