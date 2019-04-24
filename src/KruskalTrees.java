import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class Edge
{
	int origin, destination, wgt;

	Edge(int x, int y, int w)
	{
		this.origin = x;
		this.destination = y;
		this.wgt = w;
	}

	void show()
	{
		System.out.print("Edge " + toChar(origin) + "--" + wgt + "--" + toChar(destination) + "\n");
	}

	private char toChar(int u)
	{
		return (char) (u + 64);
	}
}

class Heap
{
	private int heapSize;
	private Edge[] edge;
	private int[] h;

	Heap(int heapSize, Edge[] edge)
	{
		this.heapSize = heapSize;
		h = new int[heapSize + 1];
		this.edge = edge;

		for (int i = 0; i <= heapSize; ++i)
		{
			h[i] = i;
		}

		for (int i = heapSize / 2; i > 0; i--)
		{
			siftDown(i);
		}
	}

	private void siftDown(int k)
	{
		int e, j;

		e = h[k];
		while (2 * k <= heapSize)
		{

			j = 2 * k;

			if ((j < heapSize) && (edge[h[j + 1]].wgt < edge[h[j]].wgt))
			{
				j++;
			}

			if (edge[e].wgt <= edge[h[j]].wgt)
			{
				break;
			}

			h[k] = h[j];
			k = j;
		}
		h[k] = e;
	}

	int remove()
	{
		h[0] = h[1];
		h[1] = h[heapSize--];
		siftDown(1);
		return h[0];
	}
}

class UnionFindSets
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

	void union(int set1, int set2)
	{
		int xRoot = findSet(set1);
		int yRoot = findSet(set2);
		treeParent[yRoot] = xRoot;
	}

	void showTrees()
	{
		int i;
		for (i = 1; i <= heapSize; ++i)
		{
			System.out.print(toChar(i) + "->" + toChar(treeParent[i]) + "  ");
		}
		System.out.print("\n");
	}

	void showSets()
	{
		int u, root;
		int[] shown = new int[heapSize + 1];
		for (u = 1; u <= heapSize; ++u)
		{
			root = findSet(u);
			if (shown[root] != 1)
			{
				showSet(root);
				shown[root] = 1;
			}
		}
		System.out.print("\n");
	}

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

	private char toChar(int u)
	{
		return (char) (u + 64);
	}
}

class Graph
{
	private int V, E;
	private Edge[] edge;
	private Edge[] mst;
	private int totalWeight;

	Graph(String graphFile) throws IOException
	{
		int u, v;
		int w, e;

		FileReader fr = new FileReader(graphFile);
		BufferedReader reader = new BufferedReader(fr);

		String splits = "\\s+";
		String line = reader.readLine();
		String[] parts = line.split(splits);
		System.out.println("Parts[] = " + parts[0] + " " + parts[1]);

		V = Integer.parseInt(parts[0]);
		E = Integer.parseInt(parts[1]);

		edge = new Edge[E + 1];

		System.out.println("Reading edges from text file");
		for (e = 1; e <= E; ++e)
		{
			line = reader.readLine();
			parts = line.split(splits);
			u = Integer.parseInt(parts[0]);
			v = Integer.parseInt(parts[1]);
			w = Integer.parseInt(parts[2]);

			System.out.println("Edge " + toChar(u) + "--(" + w + ")--" + toChar(v));

			edge[e] = new Edge(u, v, w);
		}
	}

	public void display()
	{
		int v;

		System.out.println("\n\n Displaying the all the edges");

		for (v = 1; v <= E; ++v)
		{
			edge[v].show();
		}
	}

	void MST_Kruskal()
	{
		int ei, i = 0;
		Edge e;
		int originSet, destinationSet;
		UnionFindSets partition;

		totalWeight = 0;

		mst = new Edge[V - 1];
		Heap h = new Heap(E, edge);
		partition = new UnionFindSets(V);

		partition.showSets();

		while (i < V - 1)
		{
			ei = h.remove();
			e = edge[ei];

			originSet = partition.findSet(e.origin);
			destinationSet = partition.findSet(e.destination);

			if (originSet != destinationSet)
			{
				mst[i] = e;
				i++;

				System.out.print("\n" + i + ": ");
				e.show();

				partition.union(originSet, destinationSet);
				partition.showSets();
				partition.showTrees();

				totalWeight += e.wgt;
			}
			else
			{
				System.out.print("\nIgnoring the edge");
				e.show();
			}
		}
	}

	private char toChar(int u)
	{
		return (char) (u + 64);
	}

	void showMST()
	{
		System.out.print("\nMinimum spanning tree build from following edges:\n");
		for (int e = 0; e < V - 1; e++)
		{
			mst[e].show();
		}
		System.out.println("The total weight of the MST is: " + totalWeight);
	}
}
