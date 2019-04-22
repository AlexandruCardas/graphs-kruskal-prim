import abstract_data_types.Adjacency_List;
import abstract_data_types.Min_Heap;
import abstract_data_types.MyExceptions;

import java.io.IOException;

public class Main
{
	public static void main(String[] args) throws IOException
	{
		String file_name = "data/graph2.txt";
		Adjacency_List my_list = new Adjacency_List(file_name);
		my_list.print_adj();
		System.out.println();
		try
		{
			my_list.bfs(5);
		} catch (MyExceptions myExceptions)
		{
			myExceptions.printStackTrace();
		}
		System.out.println();
		my_list.dfsIterative(7);
		System.out.println();
		my_list.dfsRecursive(7);


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
////		maxHeap.print_heap();
//		minHeap.print_heap();


//		System.out.println(maxHeap.heapMaximum());

		//System.out.print("\nInput name of file with graph definition: ");
		//fname = Console.ReadLine();

//		Prim_Graph g = new Prim_Graph(fname);

		//g.MST_Kruskal();

		//g.showMST();
	}
}
