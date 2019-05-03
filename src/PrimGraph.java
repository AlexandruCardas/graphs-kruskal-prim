import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

class MyExceptions extends Exception
{
	MyExceptions(String message)
	{
		super(message);
	}
}


// stack class used from previous labs
class Stack extends CommonFunctionsP
{
	private Node top;

	Stack()
	{
		top = null;
	}

	void push(int value)
	{
		Node newNode = new Node();
		newNode.vertex = value;
		newNode.next = top;
		top = newNode;
	}

	int pop() throws MyExceptions
	{
		if (isEmpty())
		{
			throw new MyExceptions("Stack is empty");
		}

		int x = top.vertex;
		top = top.next;
		return x;
	}

	boolean isEmpty()
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

// regular node class
class Node
{
	int vertex;
	int wgt;
	Node next;
}

/**
 * Adjacency list without Sentinel, used for breadth and depth first search
 */
class AdjacencyList extends CommonFunctionsP
{
	private Node[] adjacencyList;
	private int vertexAmount;

	// use getters to pass the adjacency list down to the DFS and BFS search and they can make a copy of it
	int getVertexAmount()
	{
		return vertexAmount;
	}

	Node[] getAdjacencyList()
	{
		return adjacencyList;
	}

	public void setAdjacencyList(Node[] adjacencyList)
	{
		this.adjacencyList = adjacencyList;
	}

	public void setVertexAmount(int vertexAmount)
	{
		this.vertexAmount = vertexAmount;
	}

	AdjacencyList(String graphFile) throws IOException
	{
		FileReader fr = new FileReader(graphFile);
		BufferedReader reader = new BufferedReader(fr);

		String splits = "\\s+";  // multiple white-space as delimiter
		String line = reader.readLine();
		String[] parts = line.split(splits);
		System.out.println(parts[0] + " vertices " + parts[1] + " edges");

		// get the amount of edges and vertices
		vertexAmount = Integer.parseInt(parts[0]);
		int edgeAmount = Integer.parseInt(parts[1]);

		// we start at index 1 rather than 0
		adjacencyList = new Node[vertexAmount + 1];

		for (int edge = 0; edge < edgeAmount; edge++)
		{
			line = reader.readLine();
			parts = line.split(splits);
			int firstVertex = Integer.parseInt(parts[0]);
			int secondVertex = Integer.parseInt(parts[1]);
			int weight = Integer.parseInt(parts[2]);

			// create Edge object (ex: A--B)
			Node temp1 = new Node();
			temp1.vertex = secondVertex;
			temp1.next = adjacencyList[firstVertex];
			temp1.wgt = weight;

			adjacencyList[firstVertex] = temp1;

			// also create the reverse (ex: B--A)
			Node temp2 = new Node();
			temp2.vertex = firstVertex;
			temp2.next = adjacencyList[secondVertex];
			temp2.wgt = weight;

			adjacencyList[secondVertex] = temp2;
		}
	}

	void printAdj()
	{
		Node[] adj = adjacencyList.clone();
		System.out.println(String.format("%-3s %-2s %s", "Adj", "|||", "Connects to"));

		for (int i = 1; i < vertexAmount + 1; i++)
		{
			System.out.print("[" + toChar(i) + "] --> ");
			while (!isEmpty(adj[i]))
			{
				if (adj[i].next != null)
				{
					System.out.print("[" + toChar(adj[i].vertex) + "|" + adj[i].wgt + "]--");
				}
				else
				{
					System.out.print("[" + toChar(adj[i].vertex) + "|" + adj[i].wgt + "]");
				}

				adj[i] = adj[i].next;
			}
			System.out.println();
		}
	}

	// just in case
	public int size(int index)
	{
		Node[] top = adjacencyList.clone();
		int c = 0;
		while (top[index] != null)
		{
			c++;
			top[index] = top[index].next;
		}
		return c;
	}
}


// class taken from previous labs
class QueueLL extends CommonFunctionsP implements Queue
{
	private Node head;
	private Node tail;

	QueueLL()
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

// interface used for the queue, was implemented at the same time with the queue
interface Queue
{
	void enQueue(int x) throws MyExceptions;

	int deQueue() throws MyExceptions;

	boolean isEmpty();
}

/**
 * Helper class used for repeating functions
 */
class CommonFunctionsP
{
	static char toChar(int u)
	{
		return (char) (u + 64);
	}

	static boolean isEmpty(Node root)
	{
		return root == null;
	}

	protected static void swap(int[] a, int i, int j)
	{
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
}

class DFS extends CommonFunctionsP
{
	private Node[] adjacencyList;
	private int vertexAmount;
	private Stack stack = new Stack();
	private Node[] adj;

	DFS(Node[] adjacencyList, int vertexAmount)
	{
		this.adjacencyList = adjacencyList;
		this.vertexAmount = vertexAmount;
	}

	/**
	 * This method uses stacks to achieve its traversal. Not needed for this assignment but I implemented it anyway.
	 *
	 * @param s used for specifying where to start the graph traversal from
	 */
	void dfsIterative(int s)
	{
		Node[] adj = adjacencyList.clone();
		boolean[] visited = new boolean[vertexAmount + 1];

		// push the starting point of the graph onto the stack
		stack.push(s);
		int v = 0;

		while (!stack.isEmpty())
		{
			try
			{
				v = stack.pop();
			} catch (MyExceptions myExceptions)
			{
				myExceptions.printStackTrace();
			}

			if (!visited[v])
			{
				visited[v] = true;

				System.out.println(toChar(v));

				while (!isEmpty(adj[v]))
				{
					if (!visited[adj[v].vertex])
					{
						// push the child of the node onto the stack
						stack.push(adj[v].vertex);
					}

					// go to the next node linked to it
					adj[v] = adj[v].next;
				}
			}
		}
	}

	/**
	 * This class doesn't use a stack, but it does use recursion which resembles a stack for operations.
	 * Everything surrounded by square brackets constitutes the final traversal. ex: [A] marked as visited
	 *
	 * @param s used as the starting node for the graph traversal
	 */
	void dfsRecursive(int s)
	{
		// we create a clone of adjacency list because we do not want to modify anything from the original adj
		adj = adjacencyList.clone();
		boolean[] visited = new boolean[vertexAmount + 1];

		System.out.println("Add root " + toChar(s));

		dfsVisit(s, visited);
	}

	private void dfsVisit(int v, boolean[] visited)
	{
		// every vertex that gets passed into the method and is also inserted into visited array
		visited[v] = true;

		System.out.println("\n///// [" + toChar(v) + "] marked as visited /////\n");

		// check the children of that vertex
		while (!isEmpty(adj[v]))
		{
			// get the immediate child
			int destination = adj[v].vertex;

			System.out.println("Point to the next vertex connected to " + toChar(v));

			// go to the next vertex
			adj[v] = adj[v].next;

			if (visited[destination])
			{
				System.out.println("\t" + toChar(destination) + " has already been visited so ignore");
			}

			// check if the current vertex has been visited
			if (!visited[destination])
			{
				// make a recursive call
				System.out.println("--- Make recursive call with " + toChar(destination) + " ---");
				dfsVisit(destination, visited);
			}
		}
	}
}

/**
 * Almost identical to the DFS but it uses queues instead of stacks or recursion
 */
class BFS extends CommonFunctionsP
{
	private Node[] adjacencyList;
	private int vertexAmount;
	private QueueLL queue = new QueueLL();

	BFS(Node[] adjacencyList, int vertexAmount)
	{
		this.adjacencyList = adjacencyList;
		this.vertexAmount = vertexAmount;
	}

	/**
	 * This method will print out the traversal and the correct order is drawn from the vertices that have been visited.
	 * Everything surrounded by square brackets constitutes the final traversal. ex: [A] marked as visited
	 *
	 * @param s starting vertex from where the traversal begins
	 * @throws MyExceptions for demo purposes
	 */
	void breadthFirstSearch(int s) throws MyExceptions
	{
		// create a clone of the adjacency list because we do not want to modify anything from the original adj
		Node[] adj = adjacencyList.clone();
		int v;
		boolean[] visited = new boolean[vertexAmount + 1];

		// put the starting node on the queue
		System.out.println("Enqueue root " + toChar(s));
		queue.enQueue(s);

		// run until queue is empty
		while (!queue.isEmpty())
		{
			// remove the vertex from the queue and traverse to his children
			v = queue.deQueue();

			System.out.println("--- Dequeue vertex " + toChar(v) + " ---");

			if (visited[v])
			{
				System.out.println("\t" + toChar(v) + " has already been visited so ignore");
			}

			// check if the current vertex has been visited
			if (!visited[v])
			{
				// mark the current vertex as visited
				visited[v] = true;

				System.out.println("\n///// [" + toChar(v) + "] marked as visited /////\n");

				// check the vertices attached to the current one
				while (!isEmpty(adj[v]))
				{
					if (!visited[adj[v].vertex])
					{
						// put the child onto the queue
						System.out.println("Enqueue the connected vertex " + toChar(adj[v].vertex));
						queue.enQueue(adj[v].vertex);
					}
					// go to the next node linked to it
					adj[v] = adj[v].next;
				}
			}
		}
	}
}

class HeapPrim
{
	private int[] heap;
	private int[] heapPosition; // used as a lookup table
	private int[] dist;

	private int heapSize;

	// constructor
	HeapPrim(int maxSize, int[] dist, int[] heapPosition)
	{
		heapSize = 0;
		heap = new int[maxSize + 1];
		this.dist = dist;
		this.heapPosition = heapPosition;
	}

	boolean isEmpty()
	{
		return heapSize == 0;
	}

	// every time a node is inserted, it is inserted at the end of the heap, and its correct position must be found
	void siftUp(int k)
	{
		int value = heap[k];
		dist[0] = 0;

		// check if the child is smaller than the parent
		while (dist[value] < dist[heap[k / 2]])
		{
			// swap the parent with the child
			heap[k] = heap[k / 2];
			heapPosition[heap[k]] = k;

			// continue going up into the heap
			k = k / 2;
		}

		heap[k] = value;
		heapPosition[value] = k;
	}

	private void siftDown(int k)
	{
		int value, node;

		value = heap[k];

		// get the left child of that node by multiplying by 2
		node = 2 * k;

		while (node <= heapSize)
		{

			if ((node + 1 <= heapSize) && dist[heap[node]] > dist[heap[node + 1]])
			{
				// go to the right child
				node++;
			}

			// check if the child has a smaller distance than the parent
			if (dist[heap[node]] >= dist[value])
			{
				break;
			}
			else
			{
				// swapping of the nodes happen here
				heap[k] = heap[node];
				k = node;
				node = k * 2;
			}
		}

		heap[k] = value;
		heapPosition[value] = k;
	}

	// always insert at the end of the heap
	void insert(int x)
	{
		heap[++heapSize] = x;
		siftUp(heapSize);
	}

	// removal only happens at the root index (1)
	int remove()
	{
		int vertex = heap[1];
		heapPosition[vertex] = 0;
		heap[heapSize + 1] = 0;

		heap[1] = heap[heapSize--];
		siftDown(1);

		return vertex;
	}
}

/**
 * Very similar adjacency list implementation as the previous one, but now we also use a sentinel to mark the end of the list for each parent vertex.
 */
public class PrimGraph extends CommonFunctionsP
{
	private int vertexAmount;
	private Node[] adj;
	private Node sentinel;
	private int[] mst;

	private PrimGraph(String graphFile) throws IOException
	{
		int origin, destination;
		int weight;
		Node temp;

		FileReader fr = new FileReader(graphFile);
		BufferedReader reader = new BufferedReader(fr);

		String splits = "\\s+";
		String line = reader.readLine();
		String[] parts = line.split(splits);

		vertexAmount = Integer.parseInt(parts[0]);
		int edgeAmount = Integer.parseInt(parts[1]);

		sentinel = new Node();
		sentinel.next = sentinel;


		// here the sentinel gets placed at the end of the other vertices
		adj = new Node[vertexAmount + 1];
		for (destination = 1; destination <= vertexAmount; destination++)
		{
			adj[destination] = sentinel;
		}

		System.out.println("Vertices = " + parts[0] + " Edges = " + parts[1]);
		System.out.println("Vertex[1] -- Vertex[2] (weight)");
		System.out.println("Reading edges from text file");

		for (int edge = 0; edge < edgeAmount; edge++)
		{
			line = reader.readLine();
			parts = line.split(splits);
			origin = Integer.parseInt(parts[0]);
			destination = Integer.parseInt(parts[1]);
			weight = Integer.parseInt(parts[2]);

			System.out.println(toChar(origin) + "--" + toChar(destination) + "(" + weight + ")");

			temp = new Node();
			temp.vertex = destination;
			temp.wgt = weight;
			temp.next = adj[origin];
			adj[origin] = temp;

			temp = new Node();
			temp.vertex = origin;
			temp.wgt = weight;
			temp.next = adj[destination];
			adj[destination] = temp;
		}
	}

	private void display()
	{
		Node node;

		for (int v = 1; v <= vertexAmount; ++v)
		{
			System.out.print("\nadj[" + toChar(v) + "] ->");

			for (node = adj[v]; node != sentinel; node = node.next)
			{
				if (node.next == sentinel)
				{
					System.out.print(" |" + toChar(node.vertex) + " | " + node.wgt + "|");
				}
				else
				{
					System.out.print(" |" + toChar(node.vertex) + " | " + node.wgt + "| ->");
				}
			}
		}
		System.out.println();
	}

	private void MSTPrim(int s)
	{
		int weightSum = 0;

		int[] distance = new int[vertexAmount + 1];
		int[] parent = new int[vertexAmount + 1];
		int[] heapPosition = new int[vertexAmount + 1];

		// fill the distance array with max values
		Arrays.fill(distance, Integer.MAX_VALUE);

		// initialise the distance of the starting point to 0
		distance[s] = 0;

		// create the heap and pass the vertex amount and the distance array along with the lookup table
		HeapPrim primHeap = new HeapPrim(vertexAmount, distance, heapPosition);

		// insert the starting point at the root of the heap
		primHeap.insert(s);

		System.out.println("\nInsert root node of the heap " + toChar(s));

		while (!primHeap.isEmpty())
		{
			int vertexRemoved = primHeap.remove();

			distance[vertexRemoved] = 0;

			Node node = adj[vertexRemoved];

			while (node != sentinel)
			{
				int u = node.vertex;
				int weight = node.wgt;

				// check if the current node's weight value is smaller than the weight stored in the distance array
				if (weight < distance[u])
				{
					parent[u] = vertexRemoved;

					System.out.println("\n///// Traverse from origin [" + toChar(parent[u]) + "] to adjacent vertex " + toChar(u) + " with a distance of " + weight);

					System.out.print("Add the smallest weighted edge \t\t sum + " + weight);

					// increment the weight of mst with the smaller weight
					weightSum += weight;

					System.out.println(" and results in a weight of " + weightSum);


					// remove the previous larger weight from the sum
					if (distance[u] != Integer.MAX_VALUE)
					{
						System.out.print("Remove the bigger weighted edge \t sum - " + distance[u]);
						weightSum -= distance[u];
						System.out.println(" and results in a weight of " + weightSum);
					}
					else
					{
						// swap if smaller
						distance[u] = weight;
						// populate the parent array
					}

					// check if the current vertex is inside the heap or else insert it
					if (heapPosition[u] == 0)
					{
						primHeap.insert(u);
					}
					else
					{
						System.out.println("\n///// Sift up " + toChar(heapPosition[u]));

						primHeap.siftUp(heapPosition[u]);
					}
				}

				// move pointer to the next node
				node = node.next;
			}
		}
		System.out.println("\nWeight of MST = " + weightSum);

		// make the mst array point to the parent array so it can be used with printing
		mst = parent;
	}

	private void showMST()
	{
		System.out.println("\nMinimum Spanning tree parent array is:");
		for (int vertex = 1; vertex <= vertexAmount; vertex++)
		{
			if (toChar(mst[vertex]) == '@')
			{
				System.out.println(toChar(vertex));
			}
			else
			{
				System.out.println(toChar(vertex) + " -> " + toChar(mst[vertex]));
			}
		}
		System.out.println();
	}

	public static void main(String[] args) throws IOException
	{
		int starVert;

		String fileName1 = "myGraph.txt";
		AdjacencyList myList = new AdjacencyList(fileName1);

		BFS bfs = new BFS(myList.getAdjacencyList(), myList.getVertexAmount());
		DFS dfs = new DFS(myList.getAdjacencyList(), myList.getVertexAmount());

		System.out.println("\n///// Adjacency List /////\n");
		// print the adjacency list
		myList.printAdj();

		System.out.println();
		System.out.println("||| ================================================================ |||");
		System.out.println();

		System.out.println("///// Breadth-First Search /////\n");

		try
		{
			bfs.breadthFirstSearch(3);
		} catch (MyExceptions myExceptions)
		{
			myExceptions.printStackTrace();
		}

		System.out.println();
		System.out.println("||| ================================================================ |||");
		System.out.println();
		System.out.println("///// Depth-First Search /////\n");

		dfs.dfsRecursive(3);

		Scanner textScanner = new Scanner(System.in);
		String fileName2 = "myGraph.txt";
		System.out.print("\nEnter starting vertex: ");
		starVert = textScanner.nextInt();

		PrimGraph graph = new PrimGraph(fileName2);

		graph.display();
		graph.MSTPrim(starVert);
		graph.showMST();
	}
}