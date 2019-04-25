package prim;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class Heap
{
	private int[] heap;
	private int[] heapPosition;
	private int[] dist;

	private int heapSize;

	Heap(int maxSize, int[] dist, int[] heapPosition)
	{
		heapSize = 0;
		heap = new int[maxSize + 1];
		this.dist = dist;
		this.heapPosition = heapPosition;
	}

	boolean isEmpty()
	{
		return heapSize == 0;
	}

	void siftUp(int k)
	{
		int v = heap[k];
		dist[0] = 0;

		while (dist[v] < dist[heap[k / 2]])
		{
			heap[k] = heap[k / 2];
			heapPosition[heap[k]] = k;
			k = k / 2;
		}

		heap[k] = v;
		heapPosition[v] = k;
	}

	private void siftDown(int k)
	{
		int v, j;
		v = heap[k];
		j = 2 * k;

		while (j <= heapSize)
		{

			if ((j + 1 <= heapSize) && dist[heap[j]] > dist[heap[j + 1]])
			{
				j++;
			}

			if (dist[heap[j]] >= dist[v])
			{
				break;
			}
			else
			{

				heap[k] = heap[j];
				k = j;
				j = k * 2;
			}
		}

		heap[k] = v;
		heapPosition[v] = k;
	}

	void insert(int x)
	{
		heap[++heapSize] = x;
		siftUp(heapSize);
	}

	int remove()
	{
		int vertex = heap[1];
		heapPosition[vertex] = 0;
		heap[heapSize + 1] = 0;

		heap[1] = heap[heapSize--];
		siftDown(1);

		return vertex;
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

		System.out.println("Vertices = " + parts[0] + " Edges = " + parts[1]);

		vertexAmount = Integer.parseInt(parts[0]);
		int edgeAmount = Integer.parseInt(parts[1]);

		sentinel = new Node();
		sentinel.next = sentinel;

		adj = new Node[vertexAmount + 1];
		for (destination = 1; destination <= vertexAmount; destination++)
		{
			adj[destination] = sentinel;
		}

		System.out.println("Vertex[1] -- Vertex[2] (weight)");

		for (int edge = 1; edge <= edgeAmount; edge++)
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

	private char toChar(int u)
	{
		return (char) (u + 64);
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

	public void MST_Prim(int s)
	{
		int weight_sum = 0;
		int[] distance, parent, heapPosition;

		distance = new int[vertexAmount + 1];
		parent = new int[vertexAmount + 1];
		heapPosition = new int[vertexAmount + 1];

		for (int vertex = 0; vertex <= vertexAmount; vertex++)
		{
			distance[vertex] = Integer.MAX_VALUE;
			parent[vertex] = 0;
			heapPosition[vertex] = 0;
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
						weight_sum -= distance[u];
					}

					distance[u] = weight;
					parent[u] = vertexRemoved;
					weight_sum += weight;

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
		System.out.println("\nWeight of MST = " + weight_sum);

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

	class Node
	{
		int vertex;
		int wgt;
		Node next;
	}
}