package com.alexandru.Algorithms.Traversal;


import com.alexandru.Algorithms.DataStructures.Node;
import com.alexandru.Algorithms.DataStructures.Stack;
import com.alexandru.Algorithms.Exceptions.MyExceptions;
import com.alexandru.Algorithms.Utils.CommonFunctions;

public class DFS extends CommonFunctions
{
	private Node[] adjacencyList;
	private int vertexAmount;
	private Stack stack = new Stack();
	private Node[] adj;

	public DFS(Node[] adjacencyList, int vertexAmount)
	{
		this.adjacencyList = adjacencyList;
		this.vertexAmount = vertexAmount;
	}

	/**
	 * This method uses stacks to achieve its traversal. Not needed for this assignment but I implemented it anyway.
	 *
	 * @param s used for specifying where to start the graph traversal from
	 */
	void dfsIterative(int s)
	{
		Node[] adj = adjacencyList.clone();
		boolean[] visited = new boolean[vertexAmount + 1];

		// push the starting point of the graph onto the stack
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
					if (!visited[adj[v].vertex])
					{
						// push the child of the node onto the stack
						stack.push(adj[v].vertex);
					}

					// go to the next node linked to it
					adj[v] = adj[v].next;
				}
			}
		}
	}

	/**
	 * This class doesn't use a stack, but it does use recursion which resembles a stack for operations.
	 * Everything surrounded by square brackets constitutes the final traversal. ex: [A] marked as visited
	 *
	 * @param s used as the starting node for the graph traversal
	 */
	public void dfsRecursive(int s)
	{
		// we create a clone of adjacency list because we do not want to modify anything from the original adj
		adj = adjacencyList.clone();
		boolean[] visited = new boolean[vertexAmount + 1];

		System.out.println("Add root " + toChar(s));

		dfsVisit(s, visited);
	}

	private void dfsVisit(int v, boolean[] visited)
	{
		// every vertex that gets passed into the method and is also inserted into visited array
		visited[v] = true;

		System.out.println("\n///// [" + toChar(v) + "] marked as visited /////\n");

		// check the children of that vertex
		while (!isEmpty(adj[v]))
		{
			// get the immediate child
			int destination = adj[v].vertex;

			System.out.println("Point to the next vertex connected to " + toChar(v));

			// go to the next vertex
			adj[v] = adj[v].next;

			if (visited[destination])
			{
				System.out.println("\t" + toChar(destination) + " has already been visited so ignore");
			}

			// check if the current vertex has been visited
			if (!visited[destination])
			{
				// make a recursive call
				System.out.println("--- Make recursive call with " + toChar(destination) + " ---");
				dfsVisit(destination, visited);
			}
		}
	}
}