package abstract_data_types;

import common.Common_Functions;

public class Min_Heap extends Common_Functions
{
	private int heapSize = 0;

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
	public void minHeapify(int[] A, int i)
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
			minHeapify(A, smallest);
		}
	}

	public void buildMinHeap(int[] A)
	{
		for (int i = (A.length - 1) / 2; i >= 1; i--)
		{
			heapSize = A.length - 1;
			minHeapify(A, i);
		}
	}
}