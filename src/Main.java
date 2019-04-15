import abstract_data_types.Adjacency_List;
import abstract_data_types.Max_Heap;

import java.io.IOException;

public class Main
{
	public static void main(String[] args) throws IOException
	{
		String file_name = "data/wGraph3.txt";
		Adjacency_List my_list = new Adjacency_List(file_name);
		my_list.print_adj();

//		Max_Heap_Sifting maxHeap = new Max_Heap_Sifting();
//		maxHeap.test();

		int[] A = {Integer.MIN_VALUE , 16, 14, 10, 8, 7, 9, 3, 2, 4, 1};
		Max_Heap maxHeap = new Max_Heap(A);
		maxHeap.buildMaxHeap();
//		abstract_data_types.Min_Heap minHeap = new abstract_data_types.Min_Heap();
//		minHeap.buildMinHeap(A);
		System.out.println(maxHeap.extractMaxHeap());
		System.out.println(maxHeap.extractMaxHeap());
		System.out.println(maxHeap.extractMaxHeap());
//		maxHeap.heapSort();
		System.out.println(maxHeap.extractMaxHeap());
//		maxHeap.heapSort();

		System.out.println();

		maxHeap.print_heap();



//		System.out.println(maxHeap.heapMaximum());

		//System.out.print("\nInput name of file with graph definition: ");
		//fname = Console.ReadLine();

//		Prim_Graph g = new Prim_Graph(fname);

		//g.MST_Kruskal();

		//g.showMST();
	}
}
