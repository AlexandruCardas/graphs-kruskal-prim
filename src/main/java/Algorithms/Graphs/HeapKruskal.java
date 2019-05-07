package Algorithms.Graphs;

import Algorithms.DataStructures.Edge;

public class HeapKruskal
{
	private int heapSize;
	private Edge[] edge;
	private int[] heap;

	public HeapKruskal(int heapSize, Edge[] edge)
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

	public int remove()
	{
		heap[0] = heap[1];
		heap[1] = heap[heapSize--];
		siftDown(1);
		return heap[0];
	}
}
