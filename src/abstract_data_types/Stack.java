package abstract_data_types;

import common.Common_Functions;

public class Stack extends Common_Functions
{
	private Node top;

	public Stack()
	{
		top = null;
	}

	public void push(int value)
	{
		Node new_node = new Node();
		new_node.vert = value;
		new_node.next = top;
		top = new_node;
	}

	public int pop() throws MyExceptions
	{
		if (isEmpty())
		{
			throw new MyExceptions("Stack is empty");
		}

		int x = top.vert;
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
