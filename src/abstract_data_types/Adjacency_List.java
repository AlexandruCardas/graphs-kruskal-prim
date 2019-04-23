package abstract_data_types;

import common.Common_Functions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Adjacency_List extends Common_Functions
{
	private Node[] adjacency_list;
	private int vertex_amount;

	public int getVertex_amount()
	{
		return vertex_amount;
	}

	public Node[] getAdjacency_list()
	{
		return adjacency_list;
	}

	public void setAdjacency_list(Node[] adjacency_list)
	{
		this.adjacency_list = adjacency_list;
	}

	public void setVertex_amount(int vertex_amount)
	{
		this.vertex_amount = vertex_amount;
	}

	public Adjacency_List(String graph_file) throws IOException
	{
		FileReader fr = new FileReader(graph_file);
		BufferedReader reader = new BufferedReader(fr);

		String splits = " +";  // multiple whitespace as delimiter
		String line = reader.readLine();
		String[] parts = line.split(splits);
		System.out.println(parts[0] + " vertices " + parts[1] + " edges");

		vertex_amount = Integer.parseInt(parts[0]);
		int edge_amount = Integer.parseInt(parts[1]);

		adjacency_list = new Node[vertex_amount + 1];

		for (int edge = 0; edge < edge_amount; edge++)
		{
			line = reader.readLine();
			parts = line.split(splits);
			int first_vertex = Integer.parseInt(parts[0]);
			int second_vertex = Integer.parseInt(parts[1]);
			int weight = Integer.parseInt(parts[2]);

			// create Edge object
			Node temp1 = new Node();
			temp1.vert = second_vertex;
			temp1.next = adjacency_list[first_vertex];
			temp1.wgt = weight;

			adjacency_list[first_vertex] = temp1;


			Node temp2 = new Node();
			temp2.vert = first_vertex;
			temp2.next = adjacency_list[second_vertex];
			temp2.wgt = weight;
//
			adjacency_list[second_vertex] = temp2;
		}
	}

	public void print_adj()
	{
		Node[] adj = adjacency_list.clone();
		System.out.println(String.format("%-3s %-2s %s", "Adj", "|||", "Connects to"));

		for (int i = 1; i < vertex_amount + 1; i++)
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
	// just in case
	public int size(int index)
	{
		Node[] top = adjacency_list.clone();
		int c = 0;
		while (top[index] != null)
		{
			c++;
			top[index] = top[index].next;
		}
		return c;
	}


}
