package com.alexandru.Algorithms.DataStructures;


import com.alexandru.Algorithms.Exceptions.MyExceptions;
import com.alexandru.Algorithms.Utils.CommonFunctions;

// class taken from previous labs
public class LinkedListQueue extends CommonFunctions implements Queue
{
	private Node head;
	private Node tail;

	public LinkedListQueue()
	{
		head = null;
		tail = null;
	}

	// assume the queue is non-empty when this method is called, otherwise throw exception
	public void enQueue(int x)
	{
		Node newNode = new Node();
		newNode.vertex = x;
		newNode.next = null;

		if (isEmpty()) // case of empty list
		{
			head = newNode;
		}
		else // case of list not empty
		{
			tail.next = newNode;
		}

		tail = newNode; // new node is now at the tail
	}

	public int deQueue() throws MyExceptions
	{
		if (isEmpty())
		{
			throw new MyExceptions("Queue is empty");
		}
		int value = head.vertex;

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

	public void printQueue()
	{
		System.out.println("\nThe queue values are:\n");

		Node temp = head;
		while (temp != null)
		{
			System.out.print(toChar(temp.vertex) + "  ");
			temp = temp.next;
		}
		System.out.println("\n");
	}
}
