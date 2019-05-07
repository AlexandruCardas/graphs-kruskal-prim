package Algorithms.DataStructures;

import Algorithms.Exceptions.MyExceptions;

// interface used for the queue, was implemented at the same time with the queue
public interface Queue
{
	void enQueue(int x) throws MyExceptions;

	int deQueue() throws MyExceptions;

	boolean isEmpty();
}
