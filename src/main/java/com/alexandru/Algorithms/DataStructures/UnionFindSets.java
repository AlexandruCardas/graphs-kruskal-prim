package com.alexandru.Algorithms.DataStructures;


import com.alexandru.Algorithms.Utils.CommonFunctions;

public class UnionFindSets extends CommonFunctions
{
	private int[] treeParent;
	private int heapSize;

	// constructor which makes all the vertices point to themselves first
	public UnionFindSets(int vertexAmount)
	{
		heapSize = vertexAmount;
		treeParent = new int[vertexAmount + 1];

		for (int i = 1; i <= heapSize; i++)
		{
			treeParent[i] = i;
		}
	}

	/**
	 * Determine which subset a particular element is in. It returns the root element of it's cluster.
	 * It can be determined whether two elements are in the same subset by comparing
	 * the result of two findSet operations.
	 *
	 * @param vertex self explanatory
	 * @return root node
	 */
	public int findSet(int vertex)
	{
		int root = vertex;
		while (root != treeParent[root])
		{
			root = treeParent[root];
		}

		/*
		this operation is called path compression, compress the path leading back to
		the root which gives amortised time complexity
		 */
		while (vertex != root)
		{
			int newRoot = treeParent[vertex];
			treeParent[vertex] = root;
			vertex = newRoot;
		}

		return root;
	}

	/*
	when we get two sets, we take the parent tree, we get the root vertex of the first set and we attach it to
	the other set at the specified root node
	*/
	public void union(int set1, int set2)
	{
		int firstRoot = findSet(set1);
		int secondRoot = findSet(set2);

		// merge the two sets together
		treeParent[secondRoot] = firstRoot;
	}

	// display function to show what each vertex is attached to
	public void showTrees()
	{
		for (int i = 1; i <= heapSize; ++i)
		{
			System.out.print(toChar(treeParent[i]) + "<-" + toChar(i) + "\t");
		}
		System.out.println();
	}

	/**
	 * Similar to Algorithms.MST's visited edge function.
	 */
	public void showSets()
	{
		int u, root;
		boolean[] visited = new boolean[heapSize + 1];
		for (u = 1; u <= heapSize; ++u)
		{
			root = findSet(u);
			if (!visited[root])
			{
				showSet(root);
				visited[root] = true;
			}
		}
		System.out.println();
	}

	// display the elements of each set
	private void showSet(int root)
	{
		System.out.print("Set{");
		for (int vertex = 1; vertex <= heapSize; vertex++)
		{
			if (findSet(vertex) == root)
			{
				System.out.print(toChar(vertex) + " ");
			}
		}
		System.out.print("} ");
	}
}

