package com.alexandru.Algorithms.DataStructures;

import com.alexandru.Algorithms.Exceptions.MyExceptions;
import com.alexandru.Algorithms.Utils.CommonFunctions;

// stack class used from previous labs
public class Stack extends CommonFunctions
{
	private Node top;

	public Stack()
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
			throw new MyExceptions("Algorithms.MST.Stack is empty");
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
