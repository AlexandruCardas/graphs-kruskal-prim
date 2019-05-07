package Algorithms.Utils;

import Algorithms.DataStructures.Node;

public class CommonFunctions
{
	public static char toChar(int u)
	{
		return (char) (u + 64);
	}

	public static void swap(int[] a, int i, int j)
	{
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	public static boolean isEmpty(Node root)
	{
		return root == null;
	}

}