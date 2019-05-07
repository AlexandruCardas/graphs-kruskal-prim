package com.alexandru.Algorithms.DataStructures;//package abstract_data_types;
//
//
//public class Max_Heap extends Common_Functions
//{
//	private int[] A;
//	private int heapSize = 0;
//
//	public Max_Heap(int[] A)
//	{
//		this.A = A;
//	}
//
//	public int parent(int i)
//	{
//		return i / 2;
//	}
//
//	private int left(int i)
//	{
//		return 2 * i;
//	}
//
//	private int right(int i)
//	{
//		return 2 * i + 1;
//	}
//
//	private void maxHeapify(int i)
//	{
//		int l = left(i);
//		int r = right(i);
//		int largest;
//		if (l <= heapSize && A[l] > A[i])
//		{
//			largest = l;
//		}
//		else
//		{
//			largest = i;
//		}
//
//		if (r <= heapSize && A[r] > A[largest])
//		{
//			largest = r;
//		}
//		if (largest != i)
//		{
//			swap(A, i, largest);
//			maxHeapify(largest);
//		}
//	}
//
//	private void buildMaxHeap()
//	{
//		for (int i = (A.length - 1) / 2; i >= 1; i--)
//		{
//			heapSize = A.length - 1;
//			maxHeapify(i);
//		}
//	}
//
//	public int heapMaximum()
//	{
//		return A[1];
//	}
//
//	public int extractMaxHeap()
//	{
//		int max;
//
//		if (heapSize < 1)
//		{
//			System.exit(-1);
//		}
//		max = A[1];
//		A[1] = A[heapSize];
//		heapSize--;
//		maxHeapify(1);
//		return max;
//	}
//
//	public void heapSort()
//	{
//		buildMaxHeap();
//		for (int i = A.length - 1; i >= 2; i--)
//		{
//			swap(this.A, 1, i);
//			heapSize--;
//			maxHeapify(1);
//		}
//	}
//
//	public void print_heap()
//	{
//		heapSize = A.length - 1;
//
//		for (int i = 1; i <= heapSize; i++)
//		{
//			System.out.println(A[i]);
//		}
//	}
//}
//
