package prim;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class Heap
{
	private int[] h; // heap array
	private int[] heapPosition; // heapPosition[h[k]] == k
	private int[] dist; // dist[v] = priority of v

	private int heapSize;

	Heap(int maxSize, int[] dist, int[] heapPosition)
	{
		heapSize = 0;
		h = new int[maxSize + 1];
		this.dist = dist;
		this.heapPosition = heapPosition;
	}

	boolean isEmpty()
	{
		return heapSize == 0;
	}

	void siftUp(int k)
	{
		int v = h[k];
		dist[0] = 0;

		while (dist[v] < dist[h[k / 2]])
		{
			h[k] = h[k / 2];
			heapPosition[h[k]] = k;
			k = k / 2;
		}

		h[k] = v;
		heapPosition[v] = k;
	}

	private void siftDown(int k)
	{
		int v, j;
		v = h[k];
		j = 2 * k;

		while (j <= heapSize)
		{

			if ((j + 1 <= heapSize) && dist[h[j]] > dist[h[j + 1]])
			{
				j++;
			}

			if (dist[h[j]] >= dist[v])
			{
				break;
			}
			else
			{

				h[k] = h[j];
				k = j;
				j = k * 2;
			}
		}

		h[k] = v;
		heapPosition[v] = k;
	}

	void insert(int x)
	{
		h[++heapSize] = x;
		siftUp(heapSize);
	}

	int remove()
	{
		int v = h[1];
		heapPosition[v] = 0;
		h[heapSize + 1] = 0;

		h[1] = h[heapSize--];
		siftDown(1);

		return v;
	}
}

public class Prim_Graph
{
	private int vertexAmount;
	private Node[] adj;
	private Node sentinel;
	private int[] mst;

	public Prim_Graph(String graphFile) throws IOException
	{
		int origin, destination;
		int weight;
		Node temp;

		FileReader fr = new FileReader(graphFile);
		BufferedReader reader = new BufferedReader(fr);

		String splits = "\\s+";
		String line = reader.readLine();
		String[] parts = line.split(splits);
		System.out.println("Parts[] = " + parts[0] + " " + parts[1]);

		vertexAmount = Integer.parseInt(parts[0]);
		int edgeAmount = Integer.parseInt(parts[1]);

		sentinel = new Node();
		sentinel.next = sentinel;

		adj = new Node[vertexAmount + 1];
		for (destination = 1; destination <= vertexAmount; destination++)
		{
			adj[destination] = sentinel;
		}

		System.out.println("Reading edges from text file");
		for (int edge = 1; edge <= edgeAmount; edge++)
		{
			line = reader.readLine();
			parts = line.split(splits);
			origin = Integer.parseInt(parts[0]);
			destination = Integer.parseInt(parts[1]);
			weight = Integer.parseInt(parts[2]);

			System.out.println("Edge " + toChar(origin) + "--(" + weight + ")--" + toChar(destination));

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

	private char toChar(int u)
	{
		return (char) (u + 64);
	}

	public void display()
	{
		Node n;

		for (int v = 1; v <= vertexAmount; ++v)
		{
			System.out.print("\nadj[" + toChar(v) + "] ->");
			for (n = adj[v]; n != sentinel; n = n.next)
			{
				System.out.print(" |" + toChar(n.vertex) + " | " + n.wgt + "| ->");
			}
		}
		System.out.println();
	}

	public void MST_Prim(int s)
	{
		int wgt_sum = 0;
		int[] distance, parent, heapPosition;

		distance = new int[vertexAmount + 1];
		parent = new int[vertexAmount + 1];
		heapPosition = new int[vertexAmount + 1];

		for (int v = 0; v <= vertexAmount; v++)
		{
			distance[v] = Integer.MAX_VALUE;
			parent[v] = 0;
			heapPosition[v] = 0;
		}

		distance[s] = 0;

		Heap primHeap = new Heap(vertexAmount, distance, heapPosition);
		primHeap.insert(s);

		while (!primHeap.isEmpty())
		{
			int vertexRemoved = primHeap.remove();
			distance[vertexRemoved] = 0;

			Node node = adj[vertexRemoved];

			while (node != sentinel)
			{
				int u = node.vertex;
				int weight = node.wgt;

				if (weight < distance[u])
				{
					if (distance[u] != Integer.MAX_VALUE)
					{
						wgt_sum -= distance[u];
					}

					distance[u] = weight;
					parent[u] = vertexRemoved;
					wgt_sum += weight;
					System.out.println(wgt_sum);

					if (heapPosition[u] == 0)
					{
						primHeap.insert(u);
					}
					else
					{
						primHeap.siftUp(heapPosition[u]);
					}
				}
				node = node.next;
			}
		}

		System.out.print("\n\nWeight of MST = " + wgt_sum + "\n");

		mst = parent;
	}

	public void showMST()
	{
		System.out.print("\n\nMinimum Spanning tree parent array is:\n");
		for (int v = 1; v <= vertexAmount; ++v)
		{
			System.out.println(toChar(v) + " -> " + toChar(mst[v]));
		}
		System.out.println();
	}

	class Node
	{
		int vertex;
		int wgt;
		Node next;
	}
}