package abstract_data_types;

import common.Common_Functions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Adjacency_List extends Common_Functions
{
	private Node[] adjacency_list;
	private Node[] rec;
	private boolean[] visited;
	private int vertex_amount;
	private QueueLL queue = new QueueLL();
	private Stack stack = new Stack();


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

	public void bfs(int s) throws MyExceptions
	{
		Node[] adj = adjacency_list.clone();
		int v;
		visited = new boolean[vertex_amount + 1];

		queue.enQueue(s);

		while (!queue.isEmpty())
		{
			v = queue.deQueue();

			if (!visited[v])
			{
				visited[v] = true;
				System.out.println(toChar(v));

				while (!isEmpty(adj[v]))
				{
					if (!visited[adj[v].vert])
					{
						queue.enQueue(adj[v].vert);
					}
					adj[v] = adj[v].next;
				}
			}
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

	public void dfsIterative(int s)
	{
		Node[] adj = adjacency_list.clone();
		visited = new boolean[vertex_amount + 1];

		stack.push(s);
		int v = 0;
		while (!stack.isEmpty())
		{
			try
			{
				v = stack.pop();
			} catch (MyExceptions myExceptions)
			{
				myExceptions.printStackTrace();
			}

			if (!visited[v])
			{
				visited[v] = true;
				System.out.println(toChar(v));
				while (!isEmpty(adj[v]))
				{
					if (!visited[adj[v].vert])
					{
						stack.push(adj[v].vert);
					}
					adj[v] = adj[v].next;
				}
			}
		}
	}

	public void dfsRecursive(int s)
	{
		rec = adjacency_list.clone();
		visited = new boolean[vertex_amount + 1];
		dfsVisit(s, visited);
	}

	private void dfsVisit(int v, boolean[] visited)
	{
		visited[v] = true;
		System.out.println(toChar(v));

		while (!isEmpty(rec[v]))
		{
			int destination = rec[v].vert;
			rec[v] = rec[v].next;

			if (!visited[destination])
			{
				dfsVisit(destination, visited);
			}
		}
	}
}
