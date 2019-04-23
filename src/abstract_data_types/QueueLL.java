package abstract_data_types;

import common.Common_Functions;

public class QueueLL extends Common_Functions implements Queue
{
	private Node head;
	private Node tail;

	public QueueLL()
	{
		head = null;
		tail = null;
	}

	// assume the queue is non-empty when this method is called, otherwise thro exception
	public void enQueue(int x)
	{
		Node new_node = new Node();
		new_node.vert = x;
		new_node.next = null;

		if (isEmpty()) // case of empty list
		{
			head = new_node;
		}
		else // case of list not empty
		{
			tail.next = new_node;
		}

		tail = new_node; // new node is now at the tail
	}

	public int deQueue() throws MyExceptions
	{
		if (isEmpty())
		{
			throw new MyExceptions("Queue is empty");
		}
		int value = head.vert;

		if (head == tail)
		{
			tail = null;
		}

		head = head.next;
		return value;
	}

	public boolean isEmpty()
	{
		return head == null;
	}

	public void print_queue()
	{
		System.out.println("\nThe queue values are:\n");

		Node temp = head;
		while (temp != null)
		{
			System.out.print(toChar(temp.vert) + "  ");
			temp = temp.next;
		}
		System.out.println("\n");
	}
}