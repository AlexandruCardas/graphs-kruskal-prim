package com.alexandru.Algorithms.MST;


import com.alexandru.Algorithms.DataStructures.Edge;
import com.alexandru.Algorithms.DataStructures.UnionFindSets;
import com.alexandru.Algorithms.Heaps.HeapKruskal;
import com.alexandru.Algorithms.Utils.CommonFunctions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Kruskal extends CommonFunctions
{
	private int vertexAmount, edgeAmount;
	private Edge[] edgeArray;
	private Edge[] mst;
	private int totalWeight;

	public Kruskal(String graphFile) throws IOException
	{
		FileReader fr = new FileReader(graphFile);
		BufferedReader reader = new BufferedReader(fr);

		String splits = "\\s+";
		String line = reader.readLine();
		String[] parts = line.split(splits);

		vertexAmount = Integer.parseInt(parts[0]);
		edgeAmount = Integer.parseInt(parts[1]);
		edgeArray = new Edge[edgeAmount + 1];

		System.out.println("Vertices = " + parts[0] + " Edges = " + parts[1]);
		System.out.println("Vertex[1] -- Vertex[2] (weight)");
		System.out.println("Reading edges from text file");

		for (int edge = 1; edge <= edgeAmount; ++edge)
		{
			line = reader.readLine();
			parts = line.split(splits);
			int origin = Integer.parseInt(parts[0]);
			int destination = Integer.parseInt(parts[1]);
			int weight = Integer.parseInt(parts[2]);

			System.out.println(toChar(origin) + "--" + toChar(destination) + "(" + weight + ")");

			this.edgeArray[edge] = new Edge(origin, destination, weight);
		}

		System.out.println();
	}

	public void MSTKruskal()
	{
		int indexOfSmallestEdge, vertex = 0;
		int firstSet, secondSet;
		Edge edge;
		UnionFindSets partition;

		totalWeight = 0;

		mst = new Edge[vertexAmount - 1];
		HeapKruskal heapKruskal = new HeapKruskal(edgeAmount, edgeArray);
		partition = new UnionFindSets(vertexAmount);

		partition.showSets();

		while (vertex < vertexAmount - 1)
		{
			indexOfSmallestEdge = heapKruskal.remove();
			edge = edgeArray[indexOfSmallestEdge];

			firstSet = partition.findSet(edge.origin);
			secondSet = partition.findSet(edge.destination);

			// check if the sets will create a circle
			if (firstSet != secondSet)
			{
				mst[vertex++] = edge;

				System.out.print("\n///// Edge " + vertex + ": ");
				edge.show();
				System.out.println();

				// create the union between the two sets using these partitions
				partition.union(firstSet, secondSet);
				partition.showSets();
				partition.showTrees();

				totalWeight += edge.weight;
			}
			else
			{
				System.out.print("\nIgnoring the edge\t");
				edge.show();
				System.out.println();
			}
		}
	}

	// display function
	public void showMST()
	{
		System.out.print("\nMinimum spanning tree build from following edges:\n");
		for (int edge = 0; edge < vertexAmount - 1; edge++)
		{
			mst[edge].show();
			System.out.println();
		}
		System.out.println("\nWeight of the MST = " + totalWeight);
	}

}
