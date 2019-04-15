package abstract_data_types;

public class Max_Heap_Sifting
{
	private int[] heap;       // heap array
	private int[] hPos;       // hPos[heap[k]] == k
	private int[] dist;    // dist[v] = priority of v
	private int last_element;

	private int heap_size;

	// The heap constructor gets passed from the Prim_Graph:
	//    1. maximum heap size
	//    2. reference to the dist[] array
	//    3. reference to the hPos[] array
	public Max_Heap_Sifting()//int maxSize, int[] _dist, int[] _hPos)
	{
		last_element = 0;
		heap_size = 100;
		heap = new int[heap_size + 1];
//		dist = _dist;
//		hPos = _hPos;
	}

	private void siftUp(int parent)
	{
		int value = heap[parent];
		heap[0] = Integer.MAX_VALUE;
		while (value > heap[parent / 2])
		{
			heap[parent] = heap[parent / 2];
//			hPos[a[parent]] = parent;
			// get parent of a child by dividing the child/parent by 2, either one of the children since its truncated
			parent = parent / 2;
		}
		heap[parent] = value;
//		hPos[value] = parent;
	}

	public void siftDown(int parent)
	{
		int value, child;

		value = heap[parent];

		// does this till the very last parent
		while (parent <= last_element / 2)
		{
			child = 2 * parent;
			if (child < last_element && heap[child] < heap[child + 1])
			{
				child++;
			}

			// this confirms the last position of the value
			if (value > heap[child])
			{
				break;
			}
			heap[parent] = heap[child];
			parent = child;
		}
		heap[parent] = value;
		hPos[value] = parent;


		// code yourself
		// must use hPos[] and dist[] arrays
	}

	public void insert(int value)
	{
		heap[++last_element] = value;
		siftUp(last_element);
	}

	public int remove()
	{
		int v = heap[1];
		hPos[v] = 0; // v is no longer in heap
		heap[last_element + 1] = 0;  // put null node into empty spot

		heap[1] = heap[last_element--];
		siftDown(1);

		return v;
	}

	private void display()
	{
		System.out.println("\nThe tree structure of the heaps is:");
		System.out.println(heap[1]);
		for (int i = 1; i <= last_element / 2; i = i * 2)
		{
			for (int j = 2 * i; j < 4 * i && j <= last_element; j++)
			{
				System.out.print(heap[j] + "  ");
			}
			System.out.println();
		}
		System.out.println();
	}

	public void test()
	{
		Max_Heap_Sifting h = new Max_Heap_Sifting();

		int[] array = new int[]{7, 5, 9, 8, 7, 5, 15, 6, 8, 9, 11};
		for (int i : array)
		{
			h.insert(i);
		}

		h.display();
	}
}

