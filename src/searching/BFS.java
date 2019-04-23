package searching;

import abstract_data_types.MyExceptions;
import abstract_data_types.Node;
import abstract_data_types.QueueLL;
import common.Common_Functions;

public class BFS extends Common_Functions
{
	private Node[] adjacency_list;
	private int vertex_amount;
	private QueueLL queue = new QueueLL();

	public BFS(Node[] adjacency_list, int vertex_amount)
	{
		this.adjacency_list = adjacency_list;
		this.vertex_amount = vertex_amount;
	}

	public void breadthFirstSearch(int s) throws MyExceptions
	{
		Node[] adj = adjacency_list.clone();
		int v;
		boolean[] visited = new boolean[vertex_amount + 1];

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
}
