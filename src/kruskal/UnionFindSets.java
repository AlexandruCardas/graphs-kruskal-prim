package kruskal;

import common.Common_Functions;

class UnionFindSets extends Common_Functions
{
	private int[] treeParent;
	private int N;

	public UnionFindSets( int V)
	{
		N = V;
		treeParent = new int[V+1];
		// missing lines
	}

	public int findSet( int vertex)
	{
		// missing lines
		return 0;
	}

	public void union( int set1, int set2)
	{
		// missing
	}

	public void showTrees()
	{
		int i;
		for(i=1; i<=N; ++i)
			System.out.print(toChar(i) + "->" + toChar(treeParent[i]) + "  " );
		System.out.print("\n");
	}

	public void showSets()
	{
		int u, root;
		int[] shown = new int[N+1];
		for (u=1; u<=N; ++u)
		{
			root = findSet(u);
			if(shown[root] != 1) {
				showSet(root);
				shown[root] = 1;
			}
		}
		System.out.print("\n");
	}

	private void showSet(int root)
	{
		int v;
		System.out.print("Set{");
		for(v=1; v<=N; ++v)
			if(findSet(v) == root)
				System.out.print(toChar(v) + " ");
		System.out.print("}  ");

	}
}