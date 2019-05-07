package Algorithms.DataStructures;

import Algorithms.Utils.CommonFunctions;

import java.io.*;

/**
 * Adjacency list without Sentinel, used for breadth and depth first search
 */
public class AdjacencyList extends CommonFunctions
{
	private Node[] adjacencyList;
	private int vertexAmount;


	// use getters to pass the adjacency list down to the Algorithms.MST.DFS and Algorithms.MST.BFS search and they can make a copy of it
	public int getVertexAmount()
	{
		return vertexAmount;
	}

	public Node[] getAdjacencyList()
	{
		return adjacencyList;
	}

	public void setAdjacencyList(Node[] adjacencyList)
	{
		this.adjacencyList = adjacencyList;
	}

	public void setVertexAmount(int vertexAmount)
	{
		this.vertexAmount = vertexAmount;
	}

	public AdjacencyList(String graphFile) throws IOException
	{
		FileReader fr = new FileReader(graphFile);
		BufferedReader reader = new BufferedReader(fr);

		String splits = "\\s+";  // multiple white-space as delimiter
		String line = reader.readLine();
		String[] parts = line.split(splits);
		System.out.println(parts[0] + " vertices " + parts[1] + " edges");

		// get the amount of edges and vertices
		vertexAmount = Integer.parseInt(parts[0]);
		int edgeAmount = Integer.parseInt(parts[1]);

		// we start at index 1 rather than 0
		adjacencyList = new Node[vertexAmount + 1];

		for (int edge = 0; edge < edgeAmount; edge++)
		{
			line = reader.readLine();
			parts = line.split(splits);
			int firstVertex = Integer.parseInt(parts[0]);
			int secondVertex = Integer.parseInt(parts[1]);
			int weight = Integer.parseInt(parts[2]);

			// create Edge object (ex: A--B)
			Node temp1 = new Node();
			temp1.vertex = secondVertex;
			temp1.next = adjacencyList[firstVertex];
			temp1.wgt = weight;

			adjacencyList[firstVertex] = temp1;

			// also create the reverse (ex: B--A)
			Node temp2 = new Node();
			temp2.vertex = firstVertex;
			temp2.next = adjacencyList[secondVertex];
			temp2.wgt = weight;

			adjacencyList[secondVertex] = temp2;
		}
	}

	public void printAdj()
	{
		Node[] adj = adjacencyList.clone();
		System.out.println(String.format("%-3s %-2s %s", "Adj", "|||", "Connects to"));

		for (int i = 1; i < vertexAmount + 1; i++)
		{
			System.out.print("[" + toChar(i) + "] --> ");
			while (!isEmpty(adj[i]))
			{
				if (adj[i].next != null)
				{
					System.out.print("[" + toChar(adj[i].vertex) + "|" + adj[i].wgt + "]--");
				}
				else
				{
					System.out.print("[" + toChar(adj[i].vertex) + "|" + adj[i].wgt + "]");
				}

				adj[i] = adj[i].next;
			}
			System.out.println();
		}
	}

	// just in case
	public int size(int index)
	{
		Node[] top = adjacencyList.clone();
		int c = 0;
		while (top[index] != null)
		{
			c++;
			top[index] = top[index].next;
		}
		return c;
	}
}
