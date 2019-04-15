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

		Max_Heap heap = new Max_Heap();
		heap.test();

		//System.out.print("\nInput name of file with graph definition: ");
		//fname = Console.ReadLine();

//		Prim_Graph g = new Prim_Graph(fname);

		//g.MST_Kruskal();

		//g.showMST();
	}
}
