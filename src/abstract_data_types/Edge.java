package abstract_data_types;

public class Edge
{
	private int u, v, wgt;

	public Edge()
	{
		u = 0;
		v = 0;
		wgt = 0;
	}

	public Edge(int x, int y, int w)
	{
		;// missing lines
	}

	public void show()
	{
		System.out.print("abstract_data_types.Edge " + toChar(u) + "--" + wgt + "--" + toChar(v) + "\n");
	}

	// convert vertex into char for pretty printing
	private char toChar(int u)
	{
		return (char) (u + 64);
	}
}
