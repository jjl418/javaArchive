package juliannelee_CSCI201_lab2;

/*
 * Lab02.
 *
 *
*  Adding extra helper functions is allowed and would be a good idea.
 * Extra class variables can be added but are not required to solve this lab.
 *
 * It's always a good idea to split your logic into multiple functions since each
 * function should ideally do one thing and do it well.
 *
 * Good luck, have fun! If you're not having fun ask CPs for help! :)
*/


public class Uba<T>  implements UbaInterface<T>
{
    private static int DEFAULT_CAPACITY = 5;

    private T[] items;
    private int size; // the current number of elements in the array

  /**
	*  Constructs an empty array of default capacity
	*/
	@SuppressWarnings("unchecked")
	public Uba()
	{
        // Todo: Initialize variables
		items = (T[])new Object[DEFAULT_CAPACITY];
		//items = new T[DEFAULT_CAPACITY];
		size = 0;
	}


  /**
	*  Adds an item to this collection, at the end.
	*/
	public void add(T x)
	{
        // Todo: Implement
		//check if right size
		//if it hasnt reached maximum capacity
		if(size < DEFAULT_CAPACITY)
		{
			items[size] = x;
			size++;
		}
		//if maximum capacity reached
		else if(size == DEFAULT_CAPACITY)
		{
			//create new array with double size
			T[] n_array = (T[]) new Object[(int)(DEFAULT_CAPACITY * 2)];
			//copy items to new array
			for (int i = 0; i < DEFAULT_CAPACITY; i++)
			{
				n_array[i] = items[i];
				//items = n_array;
				//DEFAULT_CAPACITY *= 2;
			}
			//assign old array to new array
			items = n_array;
			DEFAULT_CAPACITY *= 2;
			items[size] = x;
			size++;
		}
	}

  /**
	*   Removes the last item from the list and returns it
	*/
	public T remove()
	{
        // Todo: Implement
		//items[size - 1] = null;
		//System.out.println();
		size--;
		if(size != 0)
		{
			return items[size];
			//items[size - 1] = null;
		}
        return null;
	}


  /**
	*  Returns a string representation of the array 
	*/
	@Override
	public String toString( )
	{
        // Todo: Implement
		System.out.print("[");
		for(int i = 0; i < size; i++)
		{
			System.out.print(items[i]);
			if(i != size-1) 
			{
				System.out.print(", ");
			}
			//System.out.print(", ");
		}
		System.out.print("]");
		return "";
	}
	

	public static void main(String[] args)
	{
		Uba<Integer> tmp = new Uba<Integer> ();
		System.out.println(tmp);

		for(int i = 0; i < 50; i++) tmp.add(i);
		System.out.println(tmp);

		System.out.println(tmp.remove());
		System.out.println(tmp);

		Uba< String > tmp1 = new Uba<String> ();
		for(int i = 0; i < 6; i++) tmp1.add("uba" + i);
		System.out.println(tmp1);

		System.out.println(tmp1.remove());
		System.out.println(tmp1);

	}

}

