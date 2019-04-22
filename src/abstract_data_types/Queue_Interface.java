package abstract_data_types;

interface Queue
{
	void enQueue(int x) throws MyExceptions;

	int deQueue() throws MyExceptions;

	boolean isEmpty();
}