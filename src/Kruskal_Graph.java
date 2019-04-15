//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//
//public class Kruskal_Graph extends common.Common_Functions
//{
//	private int V, E;
//	private abstract_data_types.Edge[] edge;
//	private abstract_data_types.Edge[] mst;
//
//	public Kruskal_Graph(String graphFile) throws IOException
//	{
//		int u, v;
//		int w, e;
//
//		FileReader fr = new FileReader(graphFile);
//		BufferedReader reader = new BufferedReader(fr);
//
//		String splits = " +";  // multiple whitespace as delimiter
//		String line = reader.readLine();
//		String[] parts = line.split(splits);
//		System.out.println("Parts[] = " + parts[0] + " " + parts[1]);
//
//		V = Integer.parseInt(parts[0]);
//		E = Integer.parseInt(parts[1]);
//
//		// create edge array
//		edge = new abstract_data_types.Edge[E + 1];
//
//		// read the edges
//		System.out.println("Reading edges from text file");
//		for (e = 1; e <= E; e++)
//		{
//			line = reader.readLine();
//			parts = line.split(splits);
//			u = Integer.parseInt(parts[0]);
//			v = Integer.parseInt(parts[1]);
//			w = Integer.parseInt(parts[2]);
//
//			System.out.println("abstract_data_types.Edge " + toChar(u) + "--(" + w + ")--" + toChar(v));
//
//			// create abstract_data_types.Edge object
//			abstract_data_types.Node temp = new abstract_data_types.Node();
//			temp.vert = u;
//			temp.next = ;
//			temp.wgt = w;
//		}
//	}
//
//	public abstract_data_types.Edge[] MST_Kruskal()
//	{
//		int i = 0;
//		abstract_data_types.Edge e;
//		int uSet, vSet;
//		kruskal.UnionFindSets partition;
//
//		// create edge array to store MST
//		// Initially it has no edges.
//		mst = new abstract_data_types.Edge[V - 1];
//
//		// priority queue for indices of array of edges
//		abstract_data_types.Max_Heap_Sifting h = new abstract_data_types.Max_Heap_Sifting(E, edge);
//
//		// create partition of singleton sets for the vertices
//
//
//		return mst;
//	}
//
//	public void showMST()
//	{
//		System.out.print("\nMinimum spanning tree build from following edges:\n");
//		for (int edge = 0; edge < V - 1; edge++)
//		{
//			mst[edge].show();
//		}
//		System.out.println();
//
//	}
//}
