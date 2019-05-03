import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class Edge
{
	int origin, destination, weight;

	Edge(int x, int y, int w)
	{
		this.origin = x;
		this.destination = y;
		this.weight = w;
	}

	void show()
	{
		System.out.print("Edge " + toChar(origin) + "--" + toChar(destination) + "(" + weight + ")" + "\n");
	}

	private char toChar(int u)
	{
		return (char) (u + 64);
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

	UnionFindSets(int vertexAmount)
	{
		heapSize = vertexAmount;
		treeParent = new int[vertexAmount + 1];

		for (int i = 1; i <= heapSize; i++)
		{
			treeParent[i] = i;
		}
	}


	int findSet(int vertex)
	{
		while (vertex != treeParent[vertex])
		{
			vertex = treeParent[vertex];
		}

		return vertex;
	}

	/*
	when we get two sets, we take the parent tree, we get the root vertex of the first set and we attach it to
	the other set at the specified root node
	*/
	void union(int set1, int set2)
	{
		int xRoot = findSet(set1);
		int yRoot = findSet(set2);
		treeParent[yRoot] = xRoot;
	}

	void showTrees()
	{
		for (int i = 1; i <= heapSize; ++i)
		{
			System.out.print(toChar(i) + "->" + toChar(treeParent[i]) + "  ");
		}
		System.out.print("\n");
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
		System.out.print("\n");
	}

	// at first every vertex points to itself and has a set for itself
	private void showSet(int root)
	{
		System.out.print("Set{");
		for (int v = 1; v <= heapSize; ++v)
		{
			if (findSet(v) == root)
			{
				System.out.print(toChar(v) + " ");
			}
		}
		System.out.print("}  ");
	}
}

class KruskalGraph extends CommonFunctionsK
{
	private int vertexAmount, edgeAmount;
	private Edge[] edge;
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
		edge = new Edge[edgeAmount + 1];

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

			System.out.println("Edge " + toChar(origin) + "--" + toChar(destination) + "(" + weight + ")");

			this.edge[edge] = new Edge(origin, destination, weight);
		}
	}

	public void display()
	{

		System.out.println("\n\n Displaying the all the edges");

		for (int v = 1; v <= edgeAmount; ++v)
		{
			edge[v].show();
		}
	}

	private void MSTKruskal()
	{
		int indexOfSmallestEdge, i = 0;
		Edge smallestEdge;
		int originSet, destinationSet;
		UnionFindSets partition;

		totalWeight = 0;

		mst = new Edge[vertexAmount - 1];
		HeapKruskal h = new HeapKruskal(edgeAmount, edge);
		partition = new UnionFindSets(vertexAmount);

		partition.showSets();

		while (i < vertexAmount - 1)
		{
			indexOfSmallestEdge = h.remove();
			smallestEdge = edge[indexOfSmallestEdge];

			originSet = partition.findSet(smallestEdge.origin);
			destinationSet = partition.findSet(smallestEdge.destination);

			if (originSet != destinationSet)
			{
				mst[i] = smallestEdge;
				i++;

				System.out.print("\n" + i + ": ");
				smallestEdge.show();

				partition.union(originSet, destinationSet);
				partition.showSets();
				partition.showTrees();

				totalWeight += smallestEdge.weight;
			}
			else
			{
				System.out.print("\nIgnoring the edge");
				smallestEdge.show();
			}
		}
	}

	private void showMST()
	{
		System.out.print("\nMinimum spanning tree build from following edges:\n");
		for (int edge = 0; edge < vertexAmount - 1; edge++)
		{
			mst[edge].show();
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