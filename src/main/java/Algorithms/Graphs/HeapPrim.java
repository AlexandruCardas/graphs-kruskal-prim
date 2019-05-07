package Algorithms.Graphs;

/**
 * Helper class used for repeating functions
 */


public class HeapPrim
{
	private int[] heap;
	private int[] heapPosition; // used as a lookup table
	private int[] dist;

	private int heapSize;

	// constructor
	public HeapPrim(int maxSize, int[] dist, int[] heapPosition)
	{
		heapSize = 0;
		heap = new int[maxSize + 1];
		this.dist = dist;
		this.heapPosition = heapPosition;
	}

	public boolean isEmpty()
	{
		return heapSize == 0;
	}

	// every time a node is inserted, it is inserted at the end of the heap, and its correct position must be found
	public void siftUp(int k)
	{
		int value = heap[k];
		dist[0] = 0;

		// check if the child is smaller than the parent
		while (dist[value] < dist[heap[k / 2]])
		{
			// swap the parent with the child
			heap[k] = heap[k / 2];
			heapPosition[heap[k]] = k;

			// continue going up into the heap
			k = k / 2;
		}

		heap[k] = value;
		heapPosition[value] = k;
	}

	void siftDown(int k)
	{
		int value, node;

		value = heap[k];

		// get the left child of that node by multiplying by 2
		node = 2 * k;

		while (node <= heapSize)
		{

			if ((node + 1 <= heapSize) && dist[heap[node]] > dist[heap[node + 1]])
			{
				// go to the right child
				node++;
			}

			// check if the child has a smaller distance than the parent
			if (dist[heap[node]] >= dist[value])
			{
				break;
			}
			else
			{
				// swapping of the nodes happen here
				heap[k] = heap[node];
				k = node;
				node = k * 2;
			}
		}

		heap[k] = value;
		heapPosition[value] = k;
	}

	// always insert at the end of the heap
	public void insert(int x)
	{
		heap[++heapSize] = x;
		siftUp(heapSize);
	}

	// removal only happens at the root index (1)
	public int remove()
	{
		int vertex = heap[1];
		heapPosition[vertex] = 0;
		heap[heapSize + 1] = 0;

		heap[1] = heap[heapSize--];
		siftDown(1);

		return vertex;
	}
}
