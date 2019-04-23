package abstract_data_types;

public class Heap
{
	private int[] h;       // heap array
	private int[] hPos;       // hPos[h[k]] == k
	private int[] dist;    // dist[v] = priority of v

	private int N;         // heap size

	// The heap constructor gets passed from the Graph:
	//    1. maximum heap size
	//    2. reference to the dist[] array
	//    3. reference to the hPos[] array
	public Heap(int maxSize, int[] _dist, int[] _hPos)
	{
		N = 0;
		h = new int[maxSize + 1];
		dist = _dist;
		hPos = _hPos;
	}


	public boolean isEmpty()
	{
		return N == 0;
	}


	public void siftUp(int k)
	{
		int v = h[k];

		// code yourself
		// must use hPos[] and dist[] arrays
	}


	public void siftDown(int k)
	{
		int v, j;

		v = h[k];

		// code yourself
		// must use hPos[] and dist[] arrays
	}


	public void insert(int x)
	{
		h[++N] = x;
		siftUp(N);
	}


	public int remove()
	{
		int v = h[1];
		hPos[v] = 0; // v is no longer in heap
		h[N + 1] = 0;  // put null node into empty spot

		h[1] = h[N--];
		siftDown(1);

		return v;
	}
}
