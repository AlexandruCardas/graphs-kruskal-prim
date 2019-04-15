package abstract_data_types;

import common.Common_Functions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Adjacency_List extends Common_Functions
{
	private Node[] adj;
	private int vertex_amount, edge_amount;

	public Adjacency_List(String graph_file) throws IOException
	{
		FileReader fr = new FileReader(graph_file);
		BufferedReader reader = new BufferedReader(fr);

		String splits = " +";  // multiple whitespace as delimiter
		String line = reader.readLine();
		String[] parts = line.split(splits);
		System.out.println(parts[0] + " vertices " + parts[1] + " edges");

		vertex_amount = Integer.parseInt(parts[0]);
		edge_amount = Integer.parseInt(parts[1]);

		adj = new Node[vertex_amount];

		for (int edge = 0; edge < edge_amount; edge++)
		{
			line = reader.readLine();
			parts = line.split(splits);
			int first_vertex = Integer.parseInt(parts[0]);
			int second_vertex = Integer.parseInt(parts[1]);
			int weight = Integer.parseInt(parts[2]);

			// create Edge object
			Node temp = new Node();
			temp.vert = second_vertex;
			temp.next = adj[first_vertex];
			temp.wgt = weight;

			adj[first_vertex] = temp;
		}
	}

	public void print_adj()
	{
		System.out.println(String.format("%-3s %-2s %s", "Adj", "|||", "Connects to"));

		for (int i = 1; i < vertex_amount; i++)
		{
			System.out.print("[" + toChar(i) + "] --> ");
			while (!isEmpty(adj[i]))
			{
				if (adj[i].next != null)
				{
					System.out.print("[" + toChar(adj[i].vert) + "|" + adj[i].wgt + "]--");
				}
				else
				{
					System.out.print("[" + toChar(adj[i].vert) + "|" + adj[i].wgt + "]");
				}

				adj[i] = adj[i].next;
			}
			System.out.println();
		}
	}
}
