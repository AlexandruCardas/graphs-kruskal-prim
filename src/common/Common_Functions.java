package common;

import abstract_data_types.Node;

public class Common_Functions
{
	protected static char toChar(int u)
	{
		return (char) (u + 64);
	}

	protected static boolean isEmpty(Node root)
	{
		return root == null;
	}

	protected static void swap(int[] a, int i, int j)
	{
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
}
