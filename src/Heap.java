import abstract_data_types.Edge;
import abstract_data_types.Node;
import common.Common_Functions;

public class Heap extends Common_Functions
{
	private int[] h;
	int N, Nmax;
	Edge[] edge;

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

	public void maxHeapify(int[] A, int i)
	{
		int l = left(i);
		int r = right(i);
		int largest;
		if (l <= A.length && A[l] > A[i])
		{
			largest = l;
		}
		else
		{
			largest = i;
		}

		if (r <= A.length && A[r] > A[largest])
		{
			largest = r;
		}
		if (largest != i)
		{
			swap(A, i, largest);
			maxHeapify(A, largest);
		}
	}

	public void buildMaxHeap(int[] A)
	{
		A.
	}

}

