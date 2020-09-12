package com.alexandru.Algorithms.DataStructures;

import com.alexandru.Algorithms.Exceptions.MyExceptions;

class CircularBufferQueue implements Queue
{
    private int size;
    private final int queueMax;
    private int[] queue;
    private int back;
    private int front;

    CircularBufferQueue()
    {
        this.queueMax = 6;
        this.size = this.front = this.back = 0;
        this.queue = new int[queueMax];
    }

    public void enQueue(int x) throws MyExceptions
    {
        if (queueMax == size)
        {
            throw new MyExceptions("Buffer full.");
        } else
        {
            queue[back] = x;
            back = (back + 1) % queueMax;
            size++;
        }
    }

    public int deQueue() throws MyExceptions
    {
        if (size == 0)
        {
            throw new MyExceptions("Buffer empty.");
        } else
        {
            int value = queue[front];
            front = (front + 1) % queueMax;
            size--;
            return value;
        }
    }

    public boolean isEmpty()
    {
        return size == 0;
    }

    public void printQueue()
    {
        for (int g : queue)
        {
            System.out.println(g);
        }
    }
}
