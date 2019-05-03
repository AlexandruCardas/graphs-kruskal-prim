import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class Edge extends CommonFunctionsK
{
	int origin, destination, weight;

	Edge(int origin, int destination, int weight)
	{
		this.origin = origin;
		this.destination = destination;
		this.weight = weight;
	}

	void show()
	{
		System.out.print(toChar(origin) + "--" + toChar(destination) + "(" + weight + ")");
	}
}

class HeapKruskal
{
	private int heapSize;
	private Edge[] edge;
	private int[] heap;

	HeapKruskal(int heapSize, Edge[] edge)
	{
		this.heapSize = heapSize;
		heap = new int[heapSize + 1];
		this.edge = edge;

		for (int i = 0; i <= heapSize; ++i)
		{
			heap[i] = i;
		}

		for (int i = heapSize / 2; i > 0; i--)
		{
			siftDown(i);
		}
	}

	private void siftDown(int k)
	{
		int parent, child;

		parent = heap[k];
		while (2 * k <= heapSize)
		{

			child = 2 * k;

			if ((child < heapSize) && (edge[heap[child + 1]].weight < edge[heap[child]].weight))
			{
				child++;
			}

			if (edge[parent].weight <= edge[heap[child]].weight)
			{
				break;
			}

			heap[k] = heap[child];
			k = child;
		}
		heap[k] = parent;
	}

	int remove()
	{
		heap[0] = heap[1];
		heap[1] = heap[heapSize--];
		siftDown(1);
		return heap[0];
	}
}

class CommonFunctionsK
{
	static char toChar(int u)
	{
		return (char) (u + 64);
	}

	static boolean isEmpty(Node root)
	{
		return root == null;
	}

	protected static void swap(int[] a, int i, int j)
	{
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
}

class UnionFindSets extends CommonFunctionsK
{
	private int[] treeParent;
	private int heapSize;

	// constructor which makes all the vertices point to themselves first
	UnionFindSets(int vertexAmount)
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
	int findSet(int vertex)
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
	void union(int set1, int set2)
	{
		int firstRoot = findSet(set1);
		int secondRoot = findSet(set2);

		// merge the two sets together
		treeParent[secondRoot] = firstRoot;
	}

	// display function to show what each vertex is attached to
	void showTrees()
	{
		for (int i = 1; i <= heapSize; ++i)
		{
			System.out.print(toChar(treeParent[i]) + "<-" + toChar(i) + "\t");
		}
		System.out.println();
	}

	/**
	 * Similar to Prim's visited edge function.
	 */
	void showSets()
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

class KruskalGraph extends CommonFunctionsK
{
	private int vertexAmount, edgeAmount;
	private Edge[] edgeArray;
	private Edge[] mst;
	private int totalWeight;

	private KruskalGraph(String graphFile) throws IOException
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

	private void MSTKruskal()
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
	private void showMST()
	{
		System.out.print("\nMinimum spanning tree build from following edges:\n");
		for (int edge = 0; edge < vertexAmount - 1; edge++)
		{
			mst[edge].show();
			System.out.println();
		}
		System.out.println("\nWeight of the MST = " + totalWeight);
	}

	public static void main(String[] args) throws IOException
	{
		String fname2 = "myGraph.txt";

		KruskalGraph graph = new KruskalGraph(fname2);

		graph.MSTKruskal();
		graph.showMST();
	}
}