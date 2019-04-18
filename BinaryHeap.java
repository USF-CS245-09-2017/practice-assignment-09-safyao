import java.util.NoSuchElementException;

public class BinaryHeap
{
	private int[] data;
	private int size;

	//Constructor
	public BinaryHeap()
	{
		data = new int[10];
		size = 0;
	}

	//Adds an item into the heap.
	public void add(int item)
	{
		if ((size + 1) > data.length)		//If the array is too small, then we make the array larger.
		{
			grow_array();
		}
		data[size++] = item;		//Inserts the item into the next empty space in heap and increments size.
		int current = size - 1;		//Current is the index of the newly-inserted element.	
		int parent = (current - 1) / 2;		//Parent is the index of current's parent.
		while (data[current] < data[parent] && current != 0)	//As long as the new element is not the root, we swap it with its parent if it's smaller.
		{
			swap(data, current, parent);
			current = parent;				//Re-initializes current and parent.
			parent = (parent-1)/2;
		}
	}

	//Removes the smallest element from the heap.
	public int remove()
	{
		if (size == 0)		//Makes sure the heap isn't empty.
		{
			throw new NoSuchElementException();
		}
		swap(data, 0, size - 1);	//Swaps the value at the root with the right-most child of the last row.
		size--;
		if (size > 0)			//Recursively swaps until the heap is correct.
		{
			shiftdown(0);
		}
		return data[size];		//Returns the smallest element.
	}

	//Doubles the size of the array.
	private void grow_array()
	{
		int[] temp = new int[data.length * 2];		//Creates a temporary array double the size of the previous one.

		for (int i = 0; i < data.length; i++)		//Copies every element in original array into temp array.
		{
			temp[i] = data[i];
		}

		data = temp;	//Makes the temp array the main array.
	}

	//Recursively pushes down an element at a given position into the correct position for a minheap.
	private void shiftdown(int pos)
	{
		int smallestchild;
		while (!leaf(pos))		//Loops until we reach an element without a child.
		{
			smallestchild = pos * 2 + 1;	//Sets the smallest child to the left child.
			if ((smallestchild < size) && (data[smallestchild] > data[smallestchild+1]))	//If left child is greater than right, and we have a valid index for smallestchild.
			{
				smallestchild = smallestchild + 1;	//Changes smallest to right child
			}
			if (data[pos] <= data[smallestchild]) return;	//Does nothing if the smallest child isn't smaller than its parent.
			swap (data, pos, smallestchild);	//Otherwise swaps the parent with its smaller child.
			pos = smallestchild;	//Sets the new position to that of the smaller child.
		}
	}

	//Returns true if the element has no children.
	private boolean leaf(int pos)
	{
		if ((pos >= (size-1)/2) && (pos < size))	//Pos must be a valid index value, and greater than/equal to the parent of the last element.
		{
			return true;
		}
		return false;
	}

	//Swaps two values in an array.
	private void swap(int[] a, int i, int j)
	{
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
}