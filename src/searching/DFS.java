package searching;

import abstract_data_types.MyExceptions;
import abstract_data_types.Node;
import abstract_data_types.Stack;
import common.Common_Functions;

public class DFS extends Common_Functions
{
	private Node[] adjacency_list;
	private int vertex_amount;
	private Stack stack = new Stack();
	private Node[] rec;

	public DFS(Node[] adjacency_list, int vertex_amount)
	{
		this.adjacency_list = adjacency_list;
		this.vertex_amount = vertex_amount;
	}

	public void dfsIterative(int s)
	{
		Node[] adj = adjacency_list.clone();
		boolean[] visited = new boolean[vertex_amount + 1];

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
		boolean[] visited = new boolean[vertex_amount + 1];
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
