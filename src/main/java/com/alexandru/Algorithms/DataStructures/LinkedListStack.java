package com.alexandru.Algorithms.DataStructures;

import com.alexandru.Algorithms.Exceptions.MyExceptions;
import com.alexandru.Algorithms.Utils.CommonFunctions;

// stack class used from previous labs
public class LinkedListStack extends CommonFunctions
{
	private Node top;

	public LinkedListStack()
	{
		top = null;
	}

	public void push(int value)
	{
		Node newNode = new Node();
		newNode.vertex = value;
		newNode.next = top;
		top = newNode;
	}

	public int pop() throws MyExceptions
	{
		if (isEmpty())
		{
			throw new MyExceptions("Algorithms.MST.LinkedListStack is empty");
		}

		int x = top.vertex;
		top = top.next;
		return x;
	}

	public boolean isEmpty()
	{
		return top == null;
	}

	int size()
	{
		int c = 0;
		Node t = top;
		while (t != null)
		{
			c++;
			t = t.next;
		}
		return c;
	}
}
