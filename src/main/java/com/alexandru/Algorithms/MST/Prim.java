package com.alexandru.Algorithms.MST;

import com.alexandru.Algorithms.DataStructures.Node;
import com.alexandru.Algorithms.Heaps.HeapPrim;
import com.alexandru.Algorithms.Utils.CommonFunctions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;


/**
 * Very similar adjacency list implementation as the previous one, but now we also
 * use a sentinel to mark the end of the list for each parent vertex.
 */
public class Prim extends CommonFunctions
{
	private int vertexAmount;
	private Node[] adj;
	private Node sentinel;
	private int[] mst;

	public Prim(String graphFile) throws IOException
	{
		int origin, destination;
		int weight;
		Node temp;

		FileReader fr = new FileReader(graphFile);
		BufferedReader reader = new BufferedReader(fr);

		String splits = "\\s+";
		String line = reader.readLine();
		String[] parts = line.split(splits);

		vertexAmount = Integer.parseInt(parts[0]);
		int edgeAmount = Integer.parseInt(parts[1]);

		sentinel = new Node();
		sentinel.next = sentinel;


		// here the sentinel gets placed at the end of the other vertices
		adj = new Node[vertexAmount + 1];
		for (destination = 1; destination <= vertexAmount; destination++)
		{
			adj[destination] = sentinel;
		}

		System.out.println("Vertices = " + parts[0] + " Edges = " + parts[1]);
		System.out.println("Vertex[1] -- Vertex[2] (weight)");
		System.out.println("Reading edges from text file");

		for (int edge = 0; edge < edgeAmount; edge++)
		{
			line = reader.readLine();
			parts = line.split(splits);
			origin = Integer.parseInt(parts[0]);
			destination = Integer.parseInt(parts[1]);
			weight = Integer.parseInt(parts[2]);

			System.out.println(toChar(origin) + "--" + toChar(destination) + "(" + weight + ")");

			temp = new Node();
			temp.vertex = destination;
			temp.wgt = weight;
			temp.next = adj[origin];
			adj[origin] = temp;

			temp = new Node();
			temp.vertex = origin;
			temp.wgt = weight;
			temp.next = adj[destination];
			adj[destination] = temp;
		}
	}

	public void display()
	{
		Node node;

		for (int v = 1; v <= vertexAmount; ++v)
		{
			System.out.print("\nadj[" + toChar(v) + "] ->");

			for (node = adj[v]; node != sentinel; node = node.next)
			{
				if (node.next == sentinel)
				{
					System.out.print(" |" + toChar(node.vertex) + " | " + node.wgt + "|");
				}
				else
				{
					System.out.print(" |" + toChar(node.vertex) + " | " + node.wgt + "| ->");
				}
			}
		}
		System.out.println();
	}

	public void MSTPrim(int s)
	{
		int weightSum = 0;

		int[] distance = new int[vertexAmount + 1];
		int[] parent = new int[vertexAmount + 1];
		int[] heapPosition = new int[vertexAmount + 1];

		// fill the distance array with max values
		Arrays.fill(distance, Integer.MAX_VALUE);

		// initialise the distance of the starting point to 0
		distance[s] = 0;

		// create the heap and pass the vertex amount and the distance array along with the lookup table
		HeapPrim primHeap = new HeapPrim(vertexAmount, distance, heapPosition);

		// insert the starting point at the root of the heap
		primHeap.insert(s);

		System.out.println("\nInsert root node of the heap " + toChar(s));

		while (!primHeap.isEmpty())
		{
			// get the smallest element of the heap which is the root
			int vertexRemoved = primHeap.remove();

			distance[vertexRemoved] = 0;

			Node node = adj[vertexRemoved];

			while (node != sentinel)
			{
				int vertex = node.vertex;
				int weight = node.wgt;

				// check if the current node's weight value is smaller than the weight stored in the distance array
				if (weight < distance[vertex])
				{
					parent[vertex] = vertexRemoved;

					System.out.println("\n///// ========== PARENT [" + toChar(parent[vertex]) + "] to adjacent vertex " + toChar(vertex));

					System.out.println("\n///// Traverse from origin [" + toChar(parent[vertex]) + "] to adjacent vertex " + toChar(vertex) + " with a distance of " + weight);

					System.out.print("Add the smallest weighted edge \t\t sum + " + weight);

					// increment the weight of mst with the smaller weight
					weightSum += weight;

					System.out.println(" and results in a weight of " + weightSum);

					// remove the previous larger weight from the sum
					if (distance[vertex] != Integer.MAX_VALUE)
					{
						System.out.print("Remove the bigger weighted edge \t sum - " + distance[vertex]);
						weightSum -= distance[vertex];
						System.out.println(" and results in a weight of " + weightSum);
					}
					else
					{
						// swap if smaller
						distance[vertex] = weight;
					}

					// check if the current vertex is inside the heap or else insert it
					if (heapPosition[vertex] == 0)
					{
						primHeap.insert(vertex);
					}
					else
					{
						System.out.println("\n///// Sift up " + toChar(heapPosition[vertex]));

						primHeap.siftUp(heapPosition[vertex]);
					}
				}

				// move pointer to the next node
				node = node.next;
			}
		}
		System.out.println("\nWeight of MST = " + weightSum);

		// make the mst array point to the parent array so it can be used with printing
		mst = parent;
	}

	public void showMST()
	{
		System.out.println("\nMinimum Spanning tree parent array is:");
		for (int vertex = 1; vertex <= vertexAmount; vertex++)
		{
			if (toChar(mst[vertex]) == '@')
			{
				System.out.println(toChar(vertex));
			}
			else
			{
				System.out.println(toChar(vertex) + " -> " + toChar(mst[vertex]));
			}
		}
		System.out.println();
	}

}