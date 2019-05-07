package com.alexandru.Algorithms.Traversal;


import com.alexandru.Algorithms.DataStructures.Node;
import com.alexandru.Algorithms.DataStructures.QueueLL;
import com.alexandru.Algorithms.Exceptions.MyExceptions;
import com.alexandru.Algorithms.Utils.CommonFunctions;

/**
 * Almost identical to the Algorithms.MST.DFS but it uses queues instead of stacks or recursion
 */
public class BFS extends CommonFunctions
{
	private Node[] adjacencyList;
	private int vertexAmount;
	private QueueLL queue = new QueueLL();

	public BFS(Node[] adjacencyList, int vertexAmount)
	{
		this.adjacencyList = adjacencyList;
		this.vertexAmount = vertexAmount;
	}

	/**
	 * This method will print out the traversal and the correct order is drawn from the vertices that have been visited.
	 * Everything surrounded by square brackets constitutes the final traversal. ex: [A] marked as visited
	 *
	 * @param s starting vertex from where the traversal begins
	 * @throws MyExceptions for demo purposes
	 */
	public void breadthFirstSearch(int s) throws MyExceptions
	{
		// create a clone of the adjacency list because we do not want to modify anything from the original adj
		Node[] adj = adjacencyList.clone();
		int v;
		boolean[] visited = new boolean[vertexAmount + 1];

		// put the starting node on the queue
		System.out.println("Enqueue root " + toChar(s));
		queue.enQueue(s);

		// run until queue is empty
		while (!queue.isEmpty())
		{
			// remove the vertex from the queue and traverse to his children
			v = queue.deQueue();

			System.out.println("--- Dequeue vertex " + toChar(v) + " ---");

			if (visited[v])
			{
				System.out.println("\t" + toChar(v) + " has already been visited so ignore");
			}

			// check if the current vertex has been visited
			if (!visited[v])
			{
				// mark the current vertex as visited
				visited[v] = true;

				System.out.println("\n///// [" + toChar(v) + "] marked as visited /////\n");

				// check the vertices attached to the current one
				while (!isEmpty(adj[v]))
				{
					if (!visited[adj[v].vertex])
					{
						// put the child onto the queue
						System.out.println("Enqueue the connected vertex " + toChar(adj[v].vertex));
						queue.enQueue(adj[v].vertex);
					}
					// go to the next node linked to it
					adj[v] = adj[v].next;
				}
			}
		}
	}
}
