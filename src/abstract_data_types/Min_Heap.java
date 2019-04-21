package abstract_data_types;

import common.Common_Functions;

public class Min_Heap extends Common_Functions
{
	public int[] A;
	private int heapSize = 0;

	public Min_Heap(int[] data)
	{
		this.A = data;
	}

	public int parent(int i)
	{
		return i / 2;
	}

	public int left(int i)
	{
		return 2 * i;
	}

	public int right(int i)
	{
		return 2 * i + 1;
	}

	// i is the parent index
	public void minHeapify(int i)
	{
		int l = left(i);
		int r = right(i);
		int smallest = i;
		if (l <= heapSize && A[l] < A[smallest])
		{
			smallest = l;
		}
		if (r <= heapSize && A[r] < A[smallest])
		{
			smallest = r;
		}

		if (smallest != i)
		{
			swap(A, i, smallest);
			minHeapify(smallest);
		}
	}

	public void buildMinHeap()
	{
		for (int i = (A.length - 1) / 2; i >= 1; i--)
		{
			heapSize = A.length - 1;
			minHeapify(i);
		}
	}

	public int extractMinHeap()
	{
		int min;

		if (heapSize < 1)
		{
			System.exit(-1);
		}
		min = A[1];
		A[1] = A[heapSize];
		heapSize--;
		minHeapify(1);
		return min;
	}

	public int heapMinimum()
	{
		return A[1];
	}

	public void heapSort()
	{
		buildMinHeap();
		for (int i = A.length - 1; i >= 2; i--)
		{
			swap(A, 1, i);
			heapSize--;
			minHeapify(1);
		}
	}

	public void print_heap()
	{
		heapSize = A.length - 1;
		for (int i = 1; i <= heapSize; i++)
		{
			System.out.println(A[i]);
		}
	}


}