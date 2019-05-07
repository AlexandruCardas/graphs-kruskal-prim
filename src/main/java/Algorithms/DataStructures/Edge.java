package Algorithms.DataStructures;

import Algorithms.Utils.CommonFunctions;

public class Edge extends CommonFunctions
{
	public int origin;
	public int destination;
	public int weight;

	public Edge(int origin, int destination, int weight)
	{
		this.origin = origin;
		this.destination = destination;
		this.weight = weight;
	}

	public void show()
	{
		System.out.print(toChar(origin) + "--" + toChar(destination) + "(" + weight + ")");
	}
}
