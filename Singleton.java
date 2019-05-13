package com.alexandru;

public class Singleton
{
	// static variable that holds the one instance of the class Singleton.
	private static Singleton uniqueInstance;

	// Other useful instance variables here.

	// Constructor declared private since only one Singleton can instantiate this class.
	private Singleton() {}

	// getInstance() gives a way to instantiate the class and also to return an instance of it.
	public static Singleton getInstance()
	{
		/*
		 * Variable holds ONE instance, remember it is a static variable.
		 * If variable is null, then an instance has not yet been created.
		 */
		if (uniqueInstance == null)
		{
			/*
			 * If it doesn't exist, we instantiate Singleton through its private constructor and assign it to
			 * uniqueInstance.
			 * If we never need the instance, it never gets created; this is lazy instantiation.
			 */
			uniqueInstance = new Singleton();
		}

		/*
		 * If uniqueInstance wasn't null, then it was previously created.
		 * Fall through to the return statement.
		 * By the time it hits this code, an instance is there and we return it.
		 */
		return uniqueInstance;
	}

	// Other useful methods here.
}