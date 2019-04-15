//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//
//class Prim_Graph extends common.Common_Functions
//{
//	// node_list[] is the adjacency lists array
//
//	private int vertex_amount, edge_amount;
//	private abstract_data_types.Node[] node_list;
//	private abstract_data_types.Node sentinel_node;
//	private int[] mst;
//
//	// used for traversing graph
//	private int[] visited;
//	private int id;
//
//
//	// default constructor
//	Prim_Graph(String graphFile) throws IOException
//	{
//		int first_vertex, second_vertex;
//		int edge_weight;
//		abstract_data_types.Node t;
//
//		FileReader fr = new FileReader(graphFile);
//		BufferedReader reader = new BufferedReader(fr);
//
//		String splits = " +";  // multiple whitespace as delimiter
//		String line = reader.readLine();
//		String[] parts = line.split(splits);
//		System.out.println("Parts[] = " + parts[0] + " " + parts[1]);
//
//		vertex_amount = Integer.parseInt(parts[0]);
//		edge_amount = Integer.parseInt(parts[1]);
//
//		// create sentinel node
//		sentinel_node = new abstract_data_types.Node();
//		sentinel_node.next = sentinel_node;
//
//		// create adjacency lists, initialised to sentinel node sentinel_node
//		node_list = new abstract_data_types.Node[vertex_amount + 1];
//		for (second_vertex = 1; second_vertex <= vertex_amount; second_vertex++)
//		{
//			node_list[second_vertex] = sentinel_node;
//		}
//
//		// read the edges
//		System.out.println("Reading edges from text file");
//		for (int edges = 1; edges <= edge_amount; edges++)
//		{
//			line = reader.readLine();
//			parts = line.split(splits);
//			first_vertex = Integer.parseInt(parts[0]);
//			second_vertex = Integer.parseInt(parts[1]);
//			edge_weight = Integer.parseInt(parts[2]);
//
//			System.out.println("abstract_data_types.Edge " + toChar(first_vertex) + "--(" + edge_weight + ")--" + toChar(second_vertex));
//
//			// write code to put edge into adjacency matrix
//
//		}
//	}
//
//	// method to display the graph representation
//	void display()
//	{
//		for (int vertex = 1; vertex <= vertex_amount; vertex++)
//		{
//			System.out.print("\nnode_list[" + toChar(vertex) + "] ->");
//			for (abstract_data_types.Node n = node_list[vertex]; n != sentinel_node; n = n.next)
//			{
//				System.out.print(" |" + toChar(n.vert) + " | " + n.wgt + "| ->");
//			}
//		}
//		System.out.println("");
//	}
//
//
//	public void MST_Prim(int s)
//	{
//		int v, u;
//		int wgt, wgt_sum = 0;
//		int[] dist, parent, hPos;
//		abstract_data_types.Node t;
//
//		//code here
//
//		dist[s] = 0;
//
//		abstract_data_types.Max_Heap h = new abstract_data_types.Max_Heap(vertex_amount, dist, hPos);
//		h.insert(s);
//
//		while ( ...)
//		{
//			// most of alg here
//
//		}
//		System.out.print("\n\nWeight of MST = " + wgt_sum + "\n");
//
//		mst = parent;
//	}
//
//	public void showMST()
//	{
//		System.out.print("\n\nMinimum Spanning tree parent array is:\n");
//		for (int v = 1; v <= vertex_amount; ++v)
//		{
//			System.out.println(toChar(v) + " -> " + toChar(mst[v]));
//		}
//		System.out.println("");
//	}
//}
